package com.edgedo.materialqt.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialCaseQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialCaseView queryObj = new MaterialCaseView();

	public MaterialCaseView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialCaseView queryObj) {
		this.queryObj = queryObj;
	}
}
