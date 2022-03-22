package com.javasm.commons.utils;

/**
 * @author:
 * @className:CreateIdUtils
 * @description:
 * @date:2022/1/1 15:12
 * @version:0.1
 * @since:1.8
 */
public class CreateIdUtils {

    //lastIndex 表示有几个字母 例如 ("",1,null)
    public static String createId(String id,Integer lastIndex,String pid){


        if (pid!=null && !"".equals(pid)  ){
            String idNum = id.substring(pid.length(), id.length());
            int i = Integer.parseInt(idNum);
            i++;
            return pid+String.format("%03d",i);
        }else {
            String idWord = id.substring(0, lastIndex);
            String idNum = id.substring(lastIndex, id.length());
            int i = Integer.parseInt(idNum);
            i++;
            return idWord+String.format("%03d",i);
        }
    }
    //仅支持id后三位是数字，例如："P001"--->"P002"
    public static String createId2(String id){


            String idLetter = id.substring(0, id.length()-3);
            String idNum = id.substring(id.length()-3, id.length());

            int i = Integer.parseInt(idNum);
            i++;

            return idLetter+String.format("%03d",i);

    }

}
