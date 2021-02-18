package com.edgedo.materialqt.queryvo;

import com.edgedo.materialqt.entity.MaterialProductPrice;

import java.util.List;

public class MaterialProductPriceView extends MaterialProductPrice {

    private List<MaterialProductPriceFileView> productPriceFileViewList;

    public List<MaterialProductPriceFileView> getProductPriceFileViewList() {
        return productPriceFileViewList;
    }

    public void setProductPriceFileViewList(List<MaterialProductPriceFileView> productPriceFileViewList) {
        this.productPriceFileViewList = productPriceFileViewList;
    }
}
