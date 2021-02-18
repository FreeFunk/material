package com.edgedo.materialqt.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialAboutUsQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialAboutUsView queryObj = new MaterialAboutUsView();

	public MaterialAboutUsView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialAboutUsView queryObj) {
		this.queryObj = queryObj;
	}
}
