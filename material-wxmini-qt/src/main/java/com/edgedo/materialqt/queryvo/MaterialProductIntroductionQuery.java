package com.edgedo.materialqt.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class MaterialProductIntroductionQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private MaterialProductIntroductionView queryObj = new MaterialProductIntroductionView();

	public MaterialProductIntroductionView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(MaterialProductIntroductionView queryObj) {
		this.queryObj = queryObj;
	}
}
