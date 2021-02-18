package com.edgedo.check.checkService;


import com.edgedo.check.entity.SysCheck;

public interface CheckOperatorService {

    /**
     * 更新审核信息
     * @author: ZhangCC
     * @time: 2020/8/20 19:24
     */
    String updateVoVerifyInfo(SysCheck checkVo);

}
