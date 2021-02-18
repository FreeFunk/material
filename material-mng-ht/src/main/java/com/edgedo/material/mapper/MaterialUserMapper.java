package com.edgedo.material.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialUser;
import com.edgedo.material.queryvo.MaterialUserQuery;
import com.edgedo.material.queryvo.MaterialUserView;
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

	/**
	 * 根据手机号查询
	 * @param phoneNum
	 * @return
	 */
	MaterialUser selectVoByPhoneNum(String phoneNum);

	/**
	 * 逻辑删除
	 * @param id
	 * @return
	 */
	int logicDeleteById(String id);

}