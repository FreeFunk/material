package com.edgedo.material.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialUserCollectionQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialUserCollectionView queryObj = new MaterialUserCollectionView();

	public MaterialUserCollectionView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialUserCollectionView queryObj) {
		this.queryObj = queryObj;
	}
}
