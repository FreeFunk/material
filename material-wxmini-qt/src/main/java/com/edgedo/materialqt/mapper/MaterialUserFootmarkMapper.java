package com.edgedo.materialqt.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.materialqt.entity.MaterialUserFootmark;
import com.edgedo.materialqt.queryvo.MaterialUserFootmarkQuery;
import com.edgedo.materialqt.queryvo.MaterialUserFootmarkView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MaterialUserFootmarkMapper  extends BaseMapper<MaterialUserFootmark>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialUserFootmarkView> listPage(MaterialUserFootmarkQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialUserFootmarkView> listByObj(MaterialUserFootmarkQuery query);


    MaterialUserFootmark loadByVo(MaterialUserFootmark userFootmark);
    /*清除用户足迹*/
	void deleteVo(@Param("userId") String userId,
				  @Param("dType") String dType);
}