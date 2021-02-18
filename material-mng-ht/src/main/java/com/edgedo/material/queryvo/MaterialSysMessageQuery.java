package com.edgedo.material.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialSysMessageQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialSysMessageView queryObj = new MaterialSysMessageView();

	public MaterialSysMessageView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialSysMessageView queryObj) {
		this.queryObj = queryObj;
	}
}
