package com.javasm.commons.utils;

import com.javasm.crm.entity.GoodsType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:
 * @className:TreeUtils
 * @description:
 * @date:2022/1/1 14:42
 * @version:0.1
 * @since:1.8
 */
public class TreeUtils<T> {


    public static List<GoodsType> createGoodsTypeTree(List<GoodsType> menus, String parentId){

        List<GoodsType> list = new ArrayList<>();
        for (GoodsType menu : menus) {
            String t = menu.getParentId();
            if(t!=null&&t.equals(parentId)){
                String id = menu.getId();
                List<GoodsType> childs = createGoodsTypeTree(menus, id);
                if(childs!=null && childs.size()>0){
                    menu.setChilds(childs);
                }
                list.add(menu);
            }
        }
        return list;
    }


}
