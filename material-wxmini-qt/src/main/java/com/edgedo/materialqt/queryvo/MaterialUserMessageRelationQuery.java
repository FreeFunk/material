package com.edgedo.materialqt.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialUserMessageRelationQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialUserMessageRelationView queryObj = new MaterialUserMessageRelationView();

	public MaterialUserMessageRelationView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialUserMessageRelationView queryObj) {
		this.queryObj = queryObj;
	}
}
