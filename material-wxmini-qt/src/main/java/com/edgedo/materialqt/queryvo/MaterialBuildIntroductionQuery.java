package com.edgedo.materialqt.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialBuildIntroductionQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialBuildIntroductionView queryObj = new MaterialBuildIntroductionView();

	public MaterialBuildIntroductionView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialBuildIntroductionView queryObj) {
		this.queryObj = queryObj;
	}
}
