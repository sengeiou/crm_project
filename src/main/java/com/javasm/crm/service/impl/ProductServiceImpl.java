package com.javasm.crm.service.impl;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.utils.CreateIdUtils;
import com.javasm.crm.entity.GoodsBrand;
import com.javasm.crm.entity.GoodsType;
import com.javasm.crm.entity.Product;
import com.javasm.crm.mapper.ProductMapper;
import com.javasm.crm.service.IGoodsBrandService;
import com.javasm.crm.service.IGoodsTypeService;
import com.javasm.crm.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javasm.sys.entity.CrmDictItem;
import com.javasm.sys.mapper.CrmDictItemMapper;
import com.javasm.sys.service.ICrmDictItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *  1程序运行加载字典项到redis  2.把品牌，类型都存到redis   3.写一个方法批量查询字典项list；
 *
 * @author admin
 * @since 2022-02-18
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Autowired
    private IGoodsBrandService iGoodsBrandService;
    @Autowired
    private IGoodsTypeService iGoodsTypeService;
    @Autowired
    private ICrmDictItemService iCrmDictItemService;
    @Resource
    private ProductMapper mapper;
    @Resource
    private CrmDictItemMapper CDIMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public List<String> getProdNumber() {

        QueryWrapper qw = new QueryWrapper<>();
        qw.select("prod_number");
        List<String> list = listObjs(qw);
        return list;
    }
    @Override
    public String getMaxId() {
        return  mapper.getMaxId();
    }



    @Override
    public String addItem(String goods_color, String prodColor, Integer size) {
        CrmDictItem crmDictItem = new CrmDictItem();
        crmDictItem.setDictId(CreateIdUtils.createId2(CDIMapper.getMaxId()));
        crmDictItem.setDictType(goods_color);
        crmDictItem.setDictName(prodColor);
        crmDictItem.setDictSort(String.valueOf(size+1));
        String value = String.valueOf(size + 1);
        crmDictItem.setDictValue(value);
        CDIMapper.insert(crmDictItem);
        redisTemplate.delete("dictionary:"+goods_color);
        redisTemplate.delete("dictis:"+goods_color);
        return value;
    }

    public File writerExcel(List<Product> prodlist, String tempFile){
        //转换 value转name
        for (int i = 0; i < prodlist.size(); i++) {
            List<Map<String, Object>> list = iCrmDictItemService.getDictItem("goods_color");
            for (Map<String, Object> map : list) {
                if(map.get("dict_value").equals(prodlist.get(i).getProdColor())){
                    prodlist.get(i).setProdColor((String) map.get("dict_name"));
                }
            }
            List<GoodsBrand> list1 = iGoodsBrandService.list();
            for (GoodsBrand goodsBrand : list1) {
                if(goodsBrand.getId().equals(prodlist.get(i).getBrandId())){
                    prodlist.get(i).setBrandId(goodsBrand.getBrandName());
                }
            }
            List<GoodsType> list2 = iGoodsTypeService.list();
            for (GoodsType goodsType : list2) {
                if(goodsType.getId().equals(prodlist.get(i).getProdClassify())){
                    prodlist.get(i).setProdClassify(goodsType.getTypeName());
                }
            }
        }
        File file = new File(tempFile);
        ExcelWriter writer = ExcelUtil.getWriter(file);
        writer.addHeaderAlias("prodNumber","存货档案编码");
        writer.addHeaderAlias("prodType","基础库型号");
        writer.addHeaderAlias("prodClassify","基础库分类");
        writer.addHeaderAlias("brandId","基础库品牌");
        writer.addHeaderAlias("prodColor","基础库颜色");
        writer.addHeaderAlias("prodPurchasingPattern","基础库采购模式");
        writer.addHeaderAlias("prodDistribution","基础库铺货");
        writer.addHeaderAlias("prodStandby","基础库备机");
        writer.setOnlyAlias(true);//只写有别名的

        writer.write(prodlist,true);
        writer.close();
        return file;
    }



}
