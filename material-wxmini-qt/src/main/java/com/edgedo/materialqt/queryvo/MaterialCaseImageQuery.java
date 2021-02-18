package com.edgedo.materialqt.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialCaseImageQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialCaseImageView queryObj = new MaterialCaseImageView();

	public MaterialCaseImageView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialCaseImageView queryObj) {
		this.queryObj = queryObj;
	}
}
