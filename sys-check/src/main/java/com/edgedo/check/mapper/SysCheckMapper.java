package com.edgedo.check.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.check.entity.SysCheck;
import com.edgedo.check.queryvo.SysCheckQuery;
import com.edgedo.check.queryvo.SysCheckView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface SysCheckMapper  extends BaseMapper<SysCheck>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysCheckView> listPage(SysCheckQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<SysCheckView> listByObj(SysCheckQuery query);

	

}