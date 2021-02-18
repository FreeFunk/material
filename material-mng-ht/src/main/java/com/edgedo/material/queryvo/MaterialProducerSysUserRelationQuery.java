package com.edgedo.material.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialProducerSysUserRelationQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialProducerSysUserRelationView queryObj = new MaterialProducerSysUserRelationView();

	public MaterialProducerSysUserRelationView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialProducerSysUserRelationView queryObj) {
		this.queryObj = queryObj;
	}
}
