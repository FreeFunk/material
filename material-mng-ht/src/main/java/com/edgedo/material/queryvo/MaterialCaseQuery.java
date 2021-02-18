package com.edgedo.material.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import java.util.List;

public class MaterialCaseQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialCaseView queryObj = new MaterialCaseView();
	private List<String> materIdList;

	public List<String> getMaterIdList() {
		return materIdList;
	}

	public void setMaterIdList(List<String> materIdList) {
		this.materIdList = materIdList;
	}
	public MaterialCaseView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialCaseView queryObj) {
		this.queryObj = queryObj;
	}
}
