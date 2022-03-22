package com.javasm.commons.utils;

import com.javasm.sys.entity.CrmUser;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @auther: Jin
 * @classname: Zhe
 * @description:
 * @data: 2021/12/30 16:51
 * @version: 0.1
 * @since: 1.8
 */
public class LoginUser {
    private static final String LOGIN_USER="login_user";

    private  static ThreadLocal<CrmUser> loginUser =new ThreadLocal<>();

    public static void setLoginUser(CrmUser u){

        loginUser.set(u);
        HttpSession session = ServletUtil.getSession();
        session.setAttribute(LOGIN_USER,u);
    }
    public static String getLOGIN_USER(){ return LOGIN_USER ;}



    public static String getLoginUserName(){
        return getLoginUser().getLoginUser();
    }


    public  static CrmUser getLoginUser(){
        CrmUser crmUser1 = loginUser.get();

        if (crmUser1==null){
            CrmUser crmUser = new CrmUser();
            crmUser.setId("U002");
            crmUser.setUserName("李逵");
            crmUser.setUserPass("abc1234");
            crmUser.setLoginUser("testUser");
            crmUser.setUserEmail("651+6161");
            crmUser.setUserPhone("555555");
            crmUser.setCommonPost("1");
            crmUser.setDepartmentId("2");
            crmUser.setRoleId("1");
            return crmUser;

        }
        return  crmUser1;
    }

}
