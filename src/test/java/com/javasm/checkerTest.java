package com.javasm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.javasm.commons.utils.LoginUser;
import com.javasm.sys.entity.CrmUser;
import com.javasm.sys.service.ICrmUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: XuZhou
 * @className: checkerTest
 * @description:
 * @date: 2022/2/24 19:08
 * @version: 0.1
 * @since: 1.8
 */
@SpringBootTest
public class checkerTest {

    @Resource
    private ICrmUserService iCrmUserService;


    @Test
    void checkerTest1(){
        CrmUser crmUser1 = new CrmUser();//传入的
        CrmUser crmUser2 = new CrmUser();//根据条件查的
        QueryWrapper<CrmUser> w = new QueryWrapper<>(crmUser2);
        crmUser1.setCommonPost("1");
        crmUser1.setDepartmentId("2");
        List<CrmUser> list = new ArrayList<>();

        switch (crmUser1.getCommonPost()){
            case "1":
                crmUser2.setDepartmentId(crmUser1.getDepartmentId());
                crmUser2.setCommonPost("2");
                list = iCrmUserService.list(w);
                break;
            case "2":
                crmUser2.setDepartmentId(crmUser1.getDepartmentId());
                crmUser2.setCommonPost("3");
                list = iCrmUserService.list(w);
                break;
            case "3":
                list.add(crmUser1);
                break;
        }

    }
}
