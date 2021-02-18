package com.edgedo.materialqt.mapper;

import java.util.List;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.edgedo.materialqt.entity.MaterialUserCollection;
import com.edgedo.materialqt.queryvo.MaterialUserCollectionQuery;
import com.edgedo.materialqt.queryvo.MaterialUserCollectionView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface MaterialUserCollectionMapper  extends BaseMapper<MaterialUserCollection>{
	
	/**
	 * 分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialUserCollectionView> listPage(MaterialUserCollectionQuery query);
	
	/**
	 * 不分页条件查询
	 * @param query
	 * @return
	 */
	public List<MaterialUserCollectionView> listByObj(MaterialUserCollectionQuery query);

	/*查询收藏是否已经存在*/
    MaterialUserCollection loadByVo(MaterialUserCollection userCollection);
	/*查询收藏*/
    MaterialUserCollection loadByCaseIdAndUserId(@Param("caseId") String caseId,
												 @Param("userId")String userId);

    /*用户删除接口*/
	void deleteByUserIdAndRelationId(@Param("userId") String userId,
									 @Param("relationId")String relationId);
}