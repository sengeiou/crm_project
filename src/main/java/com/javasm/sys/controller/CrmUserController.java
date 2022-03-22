package com.javasm.sys.controller;


import com.javasm.commons.annotation.Logs;
import com.javasm.commons.entity.E;
import com.javasm.commons.exception.MvcException;
import com.javasm.commons.utils.IDUtil;
import com.javasm.commons.utils.JwtUtils;
import com.javasm.commons.utils.Keys;
import com.javasm.commons.utils.LoginUser;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.javasm.sys.entity.CrmUser;
import com.javasm.sys.service.ICrmUserService;
import org.springframework.web.bind.annotation.RestController;
import com.javasm.commons.base.BaseController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/sys/crm-user")
public class CrmUserController extends BaseController {
    @Autowired
    private ICrmUserService iCrmUserService;


    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        CrmUser u = iCrmUserService.getById(id);
        return suc(u);
    }



    @GetMapping("phone/{phone}")
    public AxiosResult getvaliCode(@PathVariable String phone){
        ValueOperations<String, Object> redis = redisTemplate.opsForValue();
        if (phone==null) return AxiosResult.error(E.VALIDATE_ERROR);
        String code = IDUtil.getCode();
        String key= Keys.VALICODE_STRING+phone;
        redis.set(key,code,3,TimeUnit.MINUTES);
        return AxiosResult.suc(code);
    }



    @GetMapping("login/{phone}/{code}")
    public ResponseEntity getvaliCode(@PathVariable String phone,@PathVariable String code) {
        ValueOperations<String, Object> redis = redisTemplate.opsForValue();
        String key = Keys.VALICODE_STRING+phone;
        String loginCode = (String) redis.get(key);
        if (loginCode==null) throw new MvcException(E.VALIDATE_ERROR);
        if (!loginCode.equals(code))  throw new MvcException(E.VALIDATE_ERROR);
         redisTemplate.delete(key);

        CrmUser crmUser = iCrmUserService.queryByPhone(phone);
        if (crmUser!=null){
            /*      ServletUtil.getSession().setAttribute("login_user",LoginUser);*/
            String jwt = JwtUtils.generate(phone);
            HttpHeaders headers = new HttpHeaders();
            headers.add("admin-token",jwt);
            return new ResponseEntity(suc(),headers,HttpStatus.OK);
        }else {
            //后台系统，返回手机号错误
            throw new MvcException(E.PHONE_ERROR);
        }
    }

    @GetMapping("passLogin/{loginUser}/{code}")
    public ResponseEntity passLogin(@PathVariable String loginUser,@PathVariable String code) {
        QueryWrapper<CrmUser> w =new QueryWrapper<>();
        w.eq("user_phone",loginUser);
        w.eq("user_pass",code);
        List<CrmUser> list = iCrmUserService.list(w);
        if (list.size()==0){
            throw new MvcException(E.USERNAME_OE_PASS);
        }
        String jwt = JwtUtils.generate(loginUser);
        HttpHeaders headers = new HttpHeaders();
        headers.add("admin-token",jwt);
        return new ResponseEntity(suc(),headers,HttpStatus.OK);
    }




    @GetMapping
    public AxiosResult getList(CrmUser obj){
        startPage();
        QueryWrapper<CrmUser> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<CrmUser> list = iCrmUserService.list(w);
        return tabledatas(list);
    }



    @Logs(model = "人员管理",ops="查询操作")
    @GetMapping("selectOps/{id}")
    public AxiosResult selectOps(@PathVariable String id){

        return suc();
    }




    @Logs(model = "人员管理",ops="添加操作")
    @PostMapping("add")
    public AxiosResult add(@RequestBody CrmUser obj){
        obj.setCteateBy(LoginUser.getLoginUserName());
        boolean isok = iCrmUserService.save(obj);
        return suc(isok);
    }


    @Logs(model = "人员管理",ops="修改操作")
    @PutMapping
    public AxiosResult updateById(@RequestBody CrmUser obj){
        String loginUserName = LoginUser.getLoginUserName();
        obj.setUpdateBy(loginUserName);
        boolean isok = iCrmUserService.updateById(obj);
        return suc(isok);
    }

    @Logs(model = "人员管理",ops="进入界面")
    @GetMapping("into/{id}")
    public AxiosResult into(@PathVariable String id){
        return suc();
    }


    @Logs(model = "人员管理",ops="删除操作")
    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids){
        String[] strs = ids.split(",");
        boolean isok = iCrmUserService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }

    @GetMapping("/getLoginUser")
    public AxiosResult getLoginUser(){
        CrmUser loginUser = LoginUser.getLoginUser();
        return suc(loginUser);
    }


}
