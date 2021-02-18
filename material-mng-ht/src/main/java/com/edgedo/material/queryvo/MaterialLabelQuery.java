package com.edgedo.material.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialLabelQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialLabelView queryObj = new MaterialLabelView();

	public MaterialLabelView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialLabelView queryObj) {
		this.queryObj = queryObj;
	}
}
