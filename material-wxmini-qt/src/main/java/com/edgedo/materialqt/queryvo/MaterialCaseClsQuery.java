package com.edgedo.materialqt.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialCaseClsQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialCaseClsView queryObj = new MaterialCaseClsView();

	public MaterialCaseClsView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialCaseClsView queryObj) {
		this.queryObj = queryObj;
	}
}
