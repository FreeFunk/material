package com.edgedo.materialqt.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialBannerQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialBannerView queryObj = new MaterialBannerView();

	public MaterialBannerView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialBannerView queryObj) {
		this.queryObj = queryObj;
	}
}
