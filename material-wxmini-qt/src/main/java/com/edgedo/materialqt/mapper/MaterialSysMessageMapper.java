package com.edgedo.materialqt.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.materialqt.entity.MaterialSysMessage;
import com.edgedo.materialqt.queryvo.MaterialSysMessageQuery;
import com.edgedo.materialqt.queryvo.MaterialSysMessageView;
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
	
	

}