package com.edgedo.material.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.util.List;

public class MaterialProductStyleQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialProductStyleView queryObj = new MaterialProductStyleView();
	private List<String> materIdList;

	public List<String> getMaterIdList() {
		return materIdList;
	}

	public void setMaterIdList(List<String> materIdList) {
		this.materIdList = materIdList;
	}
	public MaterialProductStyleView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialProductStyleView queryObj) {
		this.queryObj = queryObj;
	}
}
