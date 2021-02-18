package com.edgedo.materialqt.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialProductStyleQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialProductStyleView queryObj = new MaterialProductStyleView();

	public MaterialProductStyleView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialProductStyleView queryObj) {
		this.queryObj = queryObj;
	}
}
