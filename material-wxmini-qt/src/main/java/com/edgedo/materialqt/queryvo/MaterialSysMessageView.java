package com.edgedo.materialqt.queryvo;

import com.edgedo.materialqt.entity.MaterialSysMessage;

public class MaterialSysMessageView extends MaterialSysMessage {

    //是否已读
    private String isRead;

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }
}
