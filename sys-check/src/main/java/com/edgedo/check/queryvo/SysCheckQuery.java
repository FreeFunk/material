package com.edgedo.check.queryvo;
import com.edgedo.common.base.QueryObj;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

public class SysCheckQuery extends QueryObj{
	@JsonSerialize(include=Inclusion.NON_EMPTY) 
	private SysCheckView queryObj = new SysCheckView();

	public SysCheckView getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(SysCheckView queryObj) {
		this.queryObj = queryObj;
	}
}
