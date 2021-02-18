package com.edgedo.materialqt.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.materialqt.entity.MaterialUser;
import com.edgedo.materialqt.queryvo.MaterialUserQuery;
import com.edgedo.materialqt.queryvo.MaterialUserView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface MaterialUserMapper  extends BaseMapper<MaterialUser>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialUserView> listPage(MaterialUserQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialUserView> listByObj(MaterialUserQuery query);

	//根据miniOpenId查询用户是否存在
    MaterialUser loadByMiniOpenId(String miniOpenId);
}