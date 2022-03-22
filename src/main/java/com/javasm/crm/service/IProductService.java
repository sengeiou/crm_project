package com.javasm.crm.service;

import com.javasm.crm.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.File;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-18
 */
public interface IProductService extends IService<Product> {


    List<String> getProdNumber();

    String getMaxId();

    String addItem(String goods_color, String prodColor, Integer size);

    File writerExcel(List<Product> prodlist, String tempFile);
}
