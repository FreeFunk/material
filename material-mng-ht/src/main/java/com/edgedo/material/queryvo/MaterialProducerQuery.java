package com.edgedo.material.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialProducerQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialProducerView queryObj = new MaterialProducerView();

	public MaterialProducerView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialProducerView queryObj) {
		this.queryObj = queryObj;
	}
}
