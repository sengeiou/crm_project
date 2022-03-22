package com.javasm.sup.controller;


import com.javasm.sys.entity.CrmUser;
import com.javasm.sys.service.ICrmUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.javasm.sup.entity.CrmGoodsPrice;
import com.javasm.sup.service.ICrmGoodsPriceService;
import org.springframework.web.bind.annotation.RestController;
import com.javasm.commons.base.BaseController;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-02-23
 */
@RestController
@RequestMapping("/sup/crm-goods-price")
public class CrmGoodsPriceController extends BaseController {
    @Autowired
    private ICrmGoodsPriceService iCrmGoodsPriceService;

    @Resource
    private ICrmUserService iCrmUserService;

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        CrmGoodsPrice u = iCrmGoodsPriceService.getById(id);
        return suc(u);
    }

    @GetMapping
    public AxiosResult getList(CrmGoodsPrice obj){
        startPage();
        QueryWrapper<CrmGoodsPrice> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<CrmGoodsPrice> list = iCrmGoodsPriceService.list(w);
        return tabledatas(list);
    }

    @PostMapping
    public AxiosResult add(@RequestBody CrmGoodsPrice obj){
        boolean isok = iCrmGoodsPriceService.save(obj);
        return suc(isok);
    }

    @PutMapping
    public AxiosResult updateById(@RequestBody CrmGoodsPrice obj){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String format = dateFormat.format(date);

        if(obj.getCheckStatus().equals("1")){
            obj.setCheckTime(format);
        }
        boolean isok = iCrmGoodsPriceService.updateById(obj);
        return suc(isok);
    }

    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids){
        String[] strs = ids.split(",");
        boolean isok = iCrmGoodsPriceService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }

    @PutMapping("/getChecker")
    public AxiosResult getChecker(@RequestBody CrmUser crmUser){
        List<CrmUser> list = iCrmUserService.list();
        String commonPost = crmUser.getCommonPost();
        CrmUser checker = null;

        switch (commonPost){
            case "1":
                for (int i = 0; i < list.size(); i++) {
                    if(!list.get(i).getCommonPost().equals("2")){
                        checker =list.get(i);
                    }
                }
                break;
            case "2":
                for (int i = 0; i < list.size(); i++) {
                    if(!list.get(i).getCommonPost().equals("3")){
                        checker =list.get(i);
                    }
                }
                break;
            case "3":
                checker = crmUser;
                break;
        }
        if(checker == null ){
            for (int i = 0; i < list.size(); i++) {
                if(!list.get(i).getId().equals(crmUser.getId())){
                    checker =list.get(i);
                }
            }
        }

        return suc(checker);

    }
}
