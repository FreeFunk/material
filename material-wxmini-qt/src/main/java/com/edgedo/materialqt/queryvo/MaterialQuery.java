package com.edgedo.materialqt.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialView queryObj = new MaterialView();

	public MaterialView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialView queryObj) {
		this.queryObj = queryObj;
	}
}
