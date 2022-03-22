package com.javasm.crm.controller;


import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.javasm.commons.annotation.Logs;
import com.javasm.commons.utils.CreateIdUtils;
import com.javasm.commons.utils.ServletUtil;
import com.javasm.crm.entity.GoodsBrand;
import com.javasm.crm.entity.GoodsType;
import com.javasm.crm.service.IGoodsBrandService;
import com.javasm.crm.service.IGoodsTypeService;
import com.javasm.sys.mapper.CrmDictItemMapper;
import com.javasm.sys.service.ICrmDictItemService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

import com.javasm.crm.entity.Product;
import com.javasm.crm.service.IProductService;
import org.springframework.web.bind.annotation.RestController;
import com.javasm.commons.base.BaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/*
*              yes_or_no,[{是,1}，{否，2}]
*
* */

@RestController
@RequestMapping("/crm/product")
public class ProductController extends BaseController {
    @Autowired
    private IGoodsBrandService iGoodsBrandService;
    @Autowired
    private IGoodsTypeService iGoodsTypeService;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICrmDictItemService iCrmDictItemService;

    //模板文件下载   down?filename=产品数据.xlsx
    @Logs(model = "产品查询",ops="下载模板")
    @GetMapping("down")
    public ResponseEntity downloadExcel() throws Exception {
        String filename = "产品导入模板.xlsx";
        //1.项目跟路径
        HttpServletRequest request = ServletUtil.getRequest();
        String realPath = request.getServletContext().getRealPath("/");
        String path = realPath+ "/excels/" +filename;
        //2.文件字节流
        byte[] bytes = IoUtil.readBytes(new FileInputStream(path));//模板文件内容
        //3.设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-disposition","attachment;filename="+URLEncoder.encode(filename,"UTF-8"));
        return new ResponseEntity(bytes,headers,HttpStatus.OK);
    }

    //模板文件数据导入
    @Logs(model = "产品查询",ops="导入Excel")
    @PostMapping("import")
    public AxiosResult importExcel(MultipartFile dataFile) throws Exception {

        InputStream inputStream = dataFile.getInputStream();
        //1.读取excel
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        reader.addHeaderAlias("存货档案编码","prodNumber");
        reader.addHeaderAlias("基础库型号","prodType");
        reader.addHeaderAlias("基础库分类","prodClassify");
        reader.addHeaderAlias("基础库品牌","brandId");
        reader.addHeaderAlias("基础库颜色","prodColor");
        reader.addHeaderAlias("基础库采购模式","prodPurchasingPattern");
        reader.addHeaderAlias("基础库铺货","prodDistribution");
        reader.addHeaderAlias("基础库备机","prodStandby");
        List<Product> products = reader.readAll(Product.class);//[1,2,3,4,6,7,8,9,10]


        //2.筛选products集合，去掉数据库中已有的编码数据.失败数据
        List<String> currentCodes = iProductService.getProdNumber();//[1,2,3,4,5,6,7]
        List<Product> importCodes= new ArrayList<>();//[5,8,9,10]   导入成功的
        List<String> exists= new ArrayList<>();//[1,2,3,4,5,6,7]   导入失败的

        String maxId = iProductService.getMaxId();
        for (Product product : products) {
            String pcode = product.getProdNumber();
            if(currentCodes.contains(pcode)){
                exists.add(product.getProdNumber());
            }else{
                maxId = CreateIdUtils.createId2(maxId);
                product.setId(maxId);
                product.setCreateBy("Excel导入");
                importCodes.add(product);
            }
        }
        //转换 name转value
        for (int i = 0; i < importCodes.size(); i++) {
            //先去字典中获取goods_color的集合数据
            List<Map<String, Object>> list = iCrmDictItemService.getDictItem("goods_color");
            boolean isExistColor = false;
            for (Map<String, Object> map : list) {
                if(map.get("dict_name").equals(importCodes.get(i).getProdColor())){
                    importCodes.get(i).setProdColor((String) map.get("dict_value"));
                    isExistColor = true;
                }
            }
            if(!isExistColor){
                String value = iProductService.addItem("goods_color", importCodes.get(i).getProdColor(), list.size());
                importCodes.get(i).setProdColor(value);
                list = iCrmDictItemService.getDictItem("goods_color");
            }

            boolean isExistBrand = false;
            List<GoodsBrand> list1 = iGoodsBrandService.list();
            for (GoodsBrand goodsBrand : list1) {
                if(goodsBrand.getBrandName().equals(importCodes.get(i).getBrandId())){
                    importCodes.get(i).setBrandId(goodsBrand.getId());
                    isExistBrand =true;
                }
            }
            if(!isExistBrand){
                GoodsBrand goodsBrand = new GoodsBrand();
                goodsBrand.setBrandName(importCodes.get(i).getBrandId());
                goodsBrand.setCreateBy("Excel导入");
                iGoodsBrandService.add(goodsBrand);
                list1 = iGoodsBrandService.list();
            }
            boolean isExistType = false;
            List<GoodsType> list2 = iGoodsTypeService.list();
            for (GoodsType goodsType : list2) {
                if(goodsType.getTypeName().equals(importCodes.get(i).getProdClassify())){
                    importCodes.get(i).setProdClassify(goodsType.getId());
                    isExistBrand =true;
                }
            }
            if(!isExistBrand){
                GoodsType goodsType = new GoodsType();
                goodsType.setTypeName(importCodes.get(i).getProdClassify());
                goodsType.setParentId("0");
                goodsType.setCreateBy("Excel导入");
                iGoodsTypeService.add(goodsType);
                list2 = iGoodsTypeService.list();
            }
        }

        //3.返回 共10条，成功4条，失败6条。导入失败数据:[{},{},{}]

        Map<String,Object> result = new HashMap<>();

        result.put("count",products.size());//总数
        result.put("errorCount",exists.size());//错误数
        result.put("errorCodes",exists);

        if(importCodes.size()>0){
            iProductService.saveBatch(importCodes);
            result.put("sucCount",importCodes.size());//成功数

        }
        return suc(result);
    }
    //导出
    @Logs(model = "产品查询",ops="导出Excel")
    @GetMapping("export")
    public ResponseEntity export() throws Exception {
        //1.生成excel文件
        QueryWrapper<Product> w = new QueryWrapper<>();
        w.orderByDesc("update_time");
        List<Product> prodlist = iProductService.list(w);

        String tempFile="D:/files/产品导出.xlsx";
        File file = iProductService.writerExcel(prodlist, tempFile);
        //2.传输文件
        byte[] bytes = IoUtil.readBytes(new FileInputStream(tempFile));//模板文件内容
        //3.设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-disposition","attachment;filename="+URLEncoder.encode(file.getName(),"UTF-8"));
        return new ResponseEntity(bytes,headers,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public AxiosResult getById(@PathVariable String id){
        Product u = iProductService.getById(id);
        return suc(u);
    }

    @GetMapping
    @Logs(model = "产品查询",ops="查询产品")
    public AxiosResult getList(Product obj){
        startPage();
        QueryWrapper<Product> w = new QueryWrapper<>(obj);
        w.orderByDesc("update_time");
        List<Product> list = iProductService.list(w);
        return tabledatas(list);
    }

    @PostMapping
    public AxiosResult add(@RequestBody Product obj){
        boolean isok = iProductService.save(obj);
        return suc(isok);
    }

    @PutMapping
    public AxiosResult updateById(@RequestBody Product obj){
        boolean isok = iProductService.updateById(obj);
        return suc(isok);
    }

    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids){
        String[] strs = ids.split(",");
        boolean isok = iProductService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }
}
