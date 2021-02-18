package com.edgedo.materialqt.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialProductPriceQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialProductPriceView queryObj = new MaterialProductPriceView();

	public MaterialProductPriceView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialProductPriceView queryObj) {
		this.queryObj = queryObj;
	}
}
