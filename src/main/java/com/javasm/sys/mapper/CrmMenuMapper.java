package com.javasm.sys.mapper;

import com.javasm.sys.entity.CrmMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CrmMenuMapper extends BaseMapper<CrmMenu> {

    List<CrmMenu> queryMenu(String parentId);

}
