package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.Imgs;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 附件图片表 服务类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
public interface ImgsService extends IService<Imgs> {
    boolean saveBatch(List<Imgs> entityList);
    boolean saveBatch(List<Imgs> entityList, int batchSize);
    boolean saveOrUpdateBatch(List<Imgs> entityList);
    boolean saveOrUpdateBatch(List<Imgs> entityList, int batchSize);
    List<Imgs> getImgsByPid(Integer pid);

}
