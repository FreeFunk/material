package com.edgedo.materialqt.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.materialqt.entity.MaterialUserMessageRelation;
import com.edgedo.materialqt.queryvo.MaterialUserMessageRelationQuery;
import com.edgedo.materialqt.queryvo.MaterialUserMessageRelationView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MaterialUserMessageRelationMapper  extends BaseMapper<MaterialUserMessageRelation>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialUserMessageRelationView> listPage(MaterialUserMessageRelationQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialUserMessageRelationView> listByObj(MaterialUserMessageRelationQuery query);

	/*根据用户id和公告id查询*/
    int countByUserIdAndMsgId(@Param("userId") String userId,
							  @Param("msgId")String msgId);
}