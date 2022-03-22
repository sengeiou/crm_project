package com.javasm.order.controller;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.javasm.commons.annotation.Logs;
import com.javasm.commons.utils.DocxUtils;
import com.javasm.order.entity.CrmOrderDetail;
import com.javasm.order.service.ICrmOrderDetailService;
import com.javasm.sys.entity.CrmDictItem;
import com.javasm.sys.service.ICrmDictItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.javasm.commons.entity.AxiosResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.util.*;
import com.javasm.order.entity.CrmOrderInfo;
import com.javasm.order.service.ICrmOrderInfoService;
import org.springframework.web.bind.annotation.RestController;
import com.javasm.commons.base.BaseController;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
@RestController
@RequestMapping("/order/crm-order-info")
public class CrmOrderInfoController extends BaseController {
    @Resource
    private ICrmOrderInfoService iCrmOrderInfoService;
    @Resource
    private ICrmOrderDetailService iCrmOrderDetailService;
    @Resource
    private ICrmDictItemService dictItemService;

    //模板文件下载   down?filename=产品数据.xlsx
//    @GetMapping("down")
//    public ResponseEntity downloadExcel(String filename) throws Exception {
//        HttpServletRequest request = ServletUtil.getRequest();
//        String realPath = request.getServletContext().getRealPath("/");
//        String path = realPath+"/static/excels/"+filename;
//        byte[] bytes = IoUtil.readBytes(new FileInputStream(path));//模板文件内容
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-disposition","attachment;filename="+URLEncoder.encode(filename,"UTF-8"));
//        return new ResponseEntity(bytes,headers,HttpStatus.OK);
//    }

    //模板文件数据导入
    @PostMapping("import")
    @Logs(model = "订单查询",ops = "文件导入")
    public AxiosResult importExcel(MultipartFile dataFile) throws Exception {
        //先去字典中获取集合数据
        //orderType
        List<CrmDictItem> orderTypes = dictItemService.queryByCodeAndName("order_type", "订单类型");
        //orderAction
        List<CrmDictItem> orderActions = dictItemService.queryByCodeAndName("order_action", "订单动作");
        //businessType
        List<CrmDictItem> businessTypes = dictItemService.queryByCodeAndName("business_type", "业务类型");
        //payType
        List<CrmDictItem> payTypes = dictItemService.queryByCodeAndName("pay_type", "支付方式");
        //orderStatus
        List<CrmDictItem> orderStatusList = dictItemService.queryByCodeAndName("order_status", "订单状态");
        Map<String, Object> result = new HashMap<>();
        InputStream inputStream = dataFile.getInputStream();
        //1.读取excel
        ExcelReader reader = ExcelUtil.getReader(inputStream, "sheet0");
        reader.addHeaderAlias("订单编号", "orderNo");
        reader.addHeaderAlias("订单类型", "orderType");
        reader.addHeaderAlias("订单动作", "orderAction");
        reader.addHeaderAlias("业务类型", "businessType");
        reader.addHeaderAlias("支付方式", "payType");
        reader.addHeaderAlias("配送方式", "sendType");
        reader.addHeaderAlias("订单状态", "orderStatus");
        reader.addHeaderAlias("下单日期", "orderTime");
        reader.addHeaderAlias("收货人姓名", "name");
        reader.addHeaderAlias("会员账号", "account");
        reader.addHeaderAlias("联系电话", "phone");
        reader.addHeaderAlias("联系手机", "smartPhone");
        reader.addHeaderAlias("邮政编码", "mailCode");
        reader.addHeaderAlias("收货地址", "address");
        reader.addHeaderAlias("配送地区", "sendArea");
        reader.addHeaderAlias("配送费用", "sendPrice");
        reader.addHeaderAlias("自提时间", "getTime");
        reader.addHeaderAlias("电子邮箱", "email");
        reader.addHeaderAlias("备注", "remarks");
        InputStream inputStream1 = dataFile.getInputStream();
        ExcelReader reader1 = ExcelUtil.getReader(inputStream1, "sheet1");
        reader1.addHeaderAlias("商品编号", "goodsNumber");
        reader1.addHeaderAlias("数量", "prodNum");
        reader1.addHeaderAlias("订单编号", "orderNo");
        reader1.addHeaderAlias("商品串码", "prodCode");

        List<CrmOrderInfo> infos = reader.readAll(CrmOrderInfo.class);
        List<CrmOrderDetail> details = reader1.readAll(CrmOrderDetail.class);
        result.put("count", infos.size());
        //2.筛选products集合，去掉数据库中已有的编码数据.失败数据
        List<String> currentOrderNos = iCrmOrderInfoService.getAllOrderNo();
        List<CrmOrderInfo> importOrder = new ArrayList<>();
        List<CrmOrderInfo> exists = new ArrayList<>();
        List<CrmOrderDetail> importOrderDetail = new ArrayList<>();
        for (CrmOrderInfo info : infos) {
            String OrderNo = info.getOrderNo();
            if (currentOrderNos.contains(OrderNo)) {
                exists.add(info);
            } else {
                for (CrmDictItem orderType : orderTypes) {
                    if (info.getOrderType().equals(orderType.getDictName())){
                        info.setOrderType(orderType.getDictValue());
                        break;
                    }
                }
                for (CrmDictItem orderAction : orderActions) {
                    if (info.getOrderAction().equals(orderAction.getDictName())){
                        info.setOrderAction(orderAction.getDictValue());
                        break;
                    }
                }
                for (CrmDictItem businessType : businessTypes) {
                    if (info.getBusinessType().equals(businessType.getDictName())){
                        info.setBusinessType(businessType.getDictValue());
                        break;
                    }
                }
                for (CrmDictItem payType : payTypes) {
                    if (info.getPayType().equals(payType.getDictName())){
                        info.setPayType(payType.getDictValue());
                        break;
                    }
                }
                for (CrmDictItem orderStatus : orderStatusList) {
                    if (info.getOrderStatus().equals(orderStatus.getDictName())){
                        info.setOrderStatus(orderStatus.getDictValue());
                        break;
                    }
                }
                importOrder.add(info);
            }
        }
        for (CrmOrderDetail detail : details) {
            String orderNo = detail.getOrderNo();
            if (!currentOrderNos.contains(orderNo)) {
                detail.setTemp2("0");
                importOrderDetail.add(detail);
            }
        }
        result.put("errorCount", exists.size());
        result.put("errorCodes", exists);
        if (importOrder.size() > 0) {
            iCrmOrderInfoService.saveBatch(importOrder);
            iCrmOrderDetailService.saveBatch(importOrderDetail);
        }
        result.put("sucCount", importOrder.size());
        return suc(result);
    }


    @GetMapping("{id}")
    @Logs(model = "订单查询",ops = "详情查询")
    public AxiosResult getById(@PathVariable String id) {
        CrmOrderInfo u = iCrmOrderInfoService.getDetailInfoById(id);
        return suc(u);
    }

    @GetMapping("outBound/{id}")
    @Logs(model = "出库管理",ops = "生成出库单")
    public AxiosResult exportDocx(@PathVariable String id) throws Exception {
        CrmOrderInfo u = iCrmOrderInfoService.ExportDocxById(id);
        u.setOrderStatus("2");
//        QueryWrapper<CrmOrderInfo> wrapper = new QueryWrapper<>();
//        wrapper.eq("id", u.getId());
        String documentNo = IdUtil.getSnowflakeNextIdStr();
        u.setDocumentNo(documentNo);
        boolean update = iCrmOrderInfoService.updateById(u);
        Double total = 0D;
        if (update) {
            HashMap<String, Object> map = new HashMap<>();
            for (CrmOrderDetail detail : u.getDetails()) {
                total += Double.valueOf(detail.getTemp1());
            }
            map.put("details", u.getDetails());
            map.put("name", u.getName());
            map.put("outTime", DateUtil.now());
            map.put("documentNo", documentNo);
            map.put("maker", "admin");
            map.put("totalMoney", total);
            String fileName="D:/出库单"+u.getOrderNo();
            OutputStream writer = new FileOutputStream(fileName+".docx");
            DocxUtils.createDocx(map, "出库单.xml", writer);
            return suc(u);
        }
        return suc();
    }

    @GetMapping
    public AxiosResult getList(CrmOrderInfo obj) {
        startPage();
        QueryWrapper<CrmOrderInfo> w = new QueryWrapper<>(obj);
        w.orderByDesc("create_time");
        List<CrmOrderInfo> list = iCrmOrderInfoService.list(w);
        return tabledatas(list);
    }

    @GetMapping("outbound")
    public AxiosResult getOutBoundList(CrmOrderInfo obj) {
        startPage();
        QueryWrapper<CrmOrderInfo> w = new QueryWrapper<>(obj);
        w.eq("order_status", "1");
        w.orderByDesc("create_time");
        List<CrmOrderInfo> list = iCrmOrderInfoService.list(w);
        return tabledatas(list);
    }

    @PostMapping
    public AxiosResult add(@RequestBody CrmOrderInfo obj) {
        boolean isok = iCrmOrderInfoService.save(obj);
        return suc(isok);
    }

    @PutMapping
    public AxiosResult updateById(@RequestBody CrmOrderInfo obj) {
        boolean isok = iCrmOrderInfoService.updateById(obj);
        return suc(isok);
    }

    @DeleteMapping("{ids}")
    public AxiosResult delByIds(@PathVariable String ids) {
        String[] strs = ids.split(",");
        boolean isok = iCrmOrderInfoService.removeByIds(Arrays.asList(strs));
        return suc(isok);
    }
}
