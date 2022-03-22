package com.javasm.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import com.javasm.commons.utils.IDUtil;
import com.javasm.commons.utils.Keys;

import com.javasm.commons.utils.LoginUser;
import com.javasm.sys.entity.CrmUser;
import com.javasm.sys.mapper.CrmUserMapper;
import com.javasm.sys.service.ICrmUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Service
public class CrmUserServiceImpl extends ServiceImpl<CrmUserMapper, CrmUser> implements ICrmUserService {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private ICrmUserService iCrmUserService;





    @Override
    public CrmUser queryByPhone(String phone) {
        ValueOperations<String, Object> redis = redisTemplate.opsForValue();
        String key = Keys.VALICODE_STRING+LoginUser.getLOGIN_USER()+phone;
        String s = (String) redis.get(key);
        if (s==null){
            CrmUser crmUser = new CrmUser();
            crmUser.setUserPhone(phone);
            QueryWrapper<CrmUser> w = new QueryWrapper<>(crmUser);
            w.equals(phone);
            List<CrmUser> list = list(w);


            for (CrmUser user : list) {
                if (user!=null){
                    redis.set(key,JSON.toJSONString(user), 10,TimeUnit.MINUTES);
                }else {
                    redis.set(key,"",30,TimeUnit.MINUTES);
                }
                return user;
            }
        }
        CrmUser user = JSON.parseObject(s,CrmUser.class);
        return user;

    }

    /**
     *  传入一个CrmUser
     * @param crmUser
     * @return 返回一个符合条件的审核人集合
     */
    @Override
    public List<CrmUser> getAllCheckerByUserPost(CrmUser crmUser) {
        List<CrmUser> list = new ArrayList<>();
        switch (crmUser.getCommonPost()){
            case "1":
                CrmUser crmUser1 = new CrmUser();
                crmUser1.setDepartmentId(crmUser.getDepartmentId());
                crmUser1.setCommonPost("2");
                QueryWrapper<CrmUser> w = new QueryWrapper<>(crmUser1);
                list = iCrmUserService.list(w);
                break;
            case "2":
                CrmUser crmUser2 = new CrmUser();
                crmUser2.setDepartmentId(crmUser.getDepartmentId());
                crmUser2.setCommonPost("3");
                QueryWrapper<CrmUser> wrapper = new QueryWrapper<>(crmUser2);

                list = iCrmUserService.list(wrapper);
                break;
            case "3":
                list.add(crmUser);
                break;
        }
        return list;
    }


}
