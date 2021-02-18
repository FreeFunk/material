package com.edgedo.material.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.material.entity.MaterialSysMessage;
import com.edgedo.material.queryvo.MaterialSysMessageQuery;
import com.edgedo.material.queryvo.MaterialSysMessageView;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface MaterialSysMessageMapper  extends BaseMapper<MaterialSysMessage>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialSysMessageView> listPage(MaterialSysMessageQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialSysMessageView> listByObj(MaterialSysMessageQuery query);

	/**
	 * 逻辑删除
	 * @return
	 */
	int logicDeleteById(String id);

}