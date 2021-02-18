package com.edgedo.materialqt.queryvo;

import com.edgedo.materialqt.entity.MaterialCase;
import com.edgedo.materialqt.entity.MaterialCaseImage;

import java.util.List;

public class MaterialCaseView extends MaterialCase {

    /*是否收藏*/
    private String isCollection;

    /*图片集合*/
    private List<MaterialCaseImage> materialCaseImageList;


    public List<MaterialCaseImage> getMaterialCaseImageList() {
        return materialCaseImageList;
    }

    public void setMaterialCaseImageList(List<MaterialCaseImage> materialCaseImageList) {
        this.materialCaseImageList = materialCaseImageList;
    }

    public String getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(String isCollection) {
        this.isCollection = isCollection;
    }
}
