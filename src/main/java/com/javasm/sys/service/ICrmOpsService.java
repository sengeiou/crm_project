package com.javasm.sys.service;

import com.javasm.sys.entity.CrmOps;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2022-02-21
 */
public interface ICrmOpsService extends IService<CrmOps> {
    String getId(String name);

}
