package com.edgedo.material.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialConfigQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialConfigView queryObj = new MaterialConfigView();

	public MaterialConfigView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialConfigView queryObj) {
		this.queryObj = queryObj;
	}
}
