package com.edgedo.material.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialProducerRelationQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialProducerRelationView queryObj = new MaterialProducerRelationView();

	public MaterialProducerRelationView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialProducerRelationView queryObj) {
		this.queryObj = queryObj;
	}
}
