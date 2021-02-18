package com.edgedo.materialqt.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialUserQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialUserView queryObj = new MaterialUserView();

	public MaterialUserView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialUserView queryObj) {
		this.queryObj = queryObj;
	}
}
