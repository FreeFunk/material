package com.edgedo.material.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialUserFootmarkQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialUserFootmarkView queryObj = new MaterialUserFootmarkView();

	public MaterialUserFootmarkView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialUserFootmarkView queryObj) {
		this.queryObj = queryObj;
	}
}
