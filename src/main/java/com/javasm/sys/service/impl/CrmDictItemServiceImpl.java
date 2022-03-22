package com.javasm.sys.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.utils.Keys;
import com.javasm.sys.entity.CrmDictItem;
import com.javasm.sys.entity.CrmDictType;
import com.javasm.sys.mapper.CrmDictItemMapper;
import com.javasm.sys.mapper.CrmDictTypeMapper;
import com.javasm.sys.service.ICrmDictItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统管理-后台字典管理 服务实现类
 * </p>
 *
 * @author admin
 * @since 2022-02-19
 */
@Service
public class CrmDictItemServiceImpl extends ServiceImpl<CrmDictItemMapper, CrmDictItem> implements ICrmDictItemService {


    @Resource
    private ICrmDictItemService iCrmDictItemService;

    @Resource
    private CrmDictTypeMapper dictTypeMapper;

    @Resource
    private CrmDictItemMapper mapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public List<CrmDictItem> findByDcitTypeId(String dictId) {

        CrmDictItem item = new CrmDictItem();
        item.setTypeId(dictId);
        QueryWrapper<CrmDictItem> w = new QueryWrapper<>(item);
        w.equals(item.getTypeId());
        w.ne("dict_exist","0");
        List<CrmDictItem> list = iCrmDictItemService.list(w);
        for (CrmDictItem crmDictItem : list) {
            System.out.println(crmDictItem);
        }


        return list;
    }

    @Override
    public List<CrmDictItem> queryByCodeAndName(String code, String name) {
        ValueOperations<String,Object> ops = redisTemplate.opsForValue();
        Boolean absent = ops.setIfAbsent(Keys.DICTS_HASH+code, "0");
        if (absent){
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("dict_type_code",code);
            queryWrapper.eq("dict_type_name",name);
            CrmDictType crmDictType = dictTypeMapper.selectOne(queryWrapper);
            String dictTypeId = crmDictType.getDictTypeId();
            QueryWrapper queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("type_id",dictTypeId);
            queryWrapper1.orderByDesc("dict_sort");
            List<CrmDictItem> list = iCrmDictItemService.list(queryWrapper1);
            Map<String,Object> map=new HashMap<>();
            map.put(code,list);
            String json = JSON.toJSONString(map);
            ops.set(Keys.DICTS_HASH+code,json);
            return list;
        }else {
            String o = (String)ops.get(Keys.DICTS_HASH + code);
            JSONObject jsonObject = JSONObject.parseObject(o);
            JSONArray array = jsonObject.getJSONArray(code);
            String string = JSON.toJSONString(array);
            List<CrmDictItem> list = JSONArray.parseArray(string, CrmDictItem.class);
            return list;
        }
    }

    @Override
    public int deleteExist(String id) {

        int i = mapper.delentExist(id);

        return i;
    }

    @Override
    public List<Map<String, Object>> getDictItem(String dictType) {
        ValueOperations<String,Object> ops = redisTemplate.opsForValue();
        Boolean absent = ops.setIfAbsent("dictionary:" + dictType, 0);
        if(absent){
            List<Map<String,Object>> list = mapper.getDictItem(dictType);
            ops.set("dictionary:"+dictType,list);
            return list;
        }else {
            List<Map<String,Object>> list= (List<Map<String, Object>>) ops.get("dictionary:" + dictType);
            return list;
        }
    }

}
