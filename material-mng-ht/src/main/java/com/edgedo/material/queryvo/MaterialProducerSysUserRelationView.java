package com.edgedo.material.queryvo;

import com.edgedo.material.entity.MaterialProducerSysUserRelation;

public class MaterialProducerSysUserRelationView extends MaterialProducerSysUserRelation {

    private String password;

    private String confirmPwd;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }
}
