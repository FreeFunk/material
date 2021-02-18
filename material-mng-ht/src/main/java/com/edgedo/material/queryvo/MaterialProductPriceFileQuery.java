package com.edgedo.material.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialProductPriceFileQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialProductPriceFileView queryObj = new MaterialProductPriceFileView();

	public MaterialProductPriceFileView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialProductPriceFileView queryObj) {
		this.queryObj = queryObj;
	}
}
