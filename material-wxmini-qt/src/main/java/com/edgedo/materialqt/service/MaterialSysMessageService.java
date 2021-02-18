package com.edgedo.materialqt.service;
		
import java.util.List;

import com.edgedo.common.util.Guid;
import com.edgedo.materialqt.entity.MaterialSysMessage;
import com.edgedo.materialqt.entity.MaterialUser;
import com.edgedo.materialqt.mapper.MaterialSysMessageMapper;
import com.edgedo.materialqt.queryvo.MaterialSysMessageQuery;
import com.edgedo.materialqt.queryvo.MaterialSysMessageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
public class MaterialSysMessageService {
	
	
	@Autowired
	private MaterialSysMessageMapper materialSysMessageMapper;
	@Autowired
	private MaterialUserMessageRelationService userMessageRelationService;

	public List<MaterialSysMessageView> listPage(MaterialSysMessageQuery materialSysMessageQuery,String userId){
		List<MaterialSysMessageView> list = materialSysMessageMapper.listPage(materialSysMessageQuery);
		for (MaterialSysMessageView msg:list){
			int count = userMessageRelationService.countByUserIdAndMsgId(userId,msg.getId());
			if (count == 0){
				msg.setIsRead("0");
			}else {
				msg.setIsRead("1");
			}
		}
		materialSysMessageQuery.setList(list);
		return list;
	}
	
	/***
	 * 新增方法
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String insert(MaterialSysMessage materialSysMessage) {
		materialSysMessage.setId(Guid.guid());
		materialSysMessageMapper.insert(materialSysMessage);
		return "";
	}
	
	/***
	 * 动态修改方法
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String update(MaterialSysMessage materialSysMessage) {
		materialSysMessageMapper.updateById(materialSysMessage);
		return "";
	}
	
	/***
	 * 全修改
	 * @param
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public String updateAll(MaterialSysMessage materialSysMessage) {
		materialSysMessageMapper.updateAllColumnById(materialSysMessage);
		return "";
	}
	
	
	
	/**
	 * 单个删除
	 * @param id
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int delete(String id) {
		
		return materialSysMessageMapper.deleteById(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public int deleteByIds(List<String> ids) {
		
		return materialSysMessageMapper.deleteBatchIds(ids);
	}
	
	
	
	/**
	 * 加载单个
	 * @param id
	 */
	public MaterialSysMessage loadById(String id) {
		return materialSysMessageMapper.selectById(id);
	}

	/*查询用户未读公告数*/
	public int unReadMsgNum(String userId) {
		//查询系统所有的公告
		int unReadMsgNum = 0;
		MaterialSysMessageQuery sysMessageQuery = new MaterialSysMessageQuery();
		sysMessageQuery.getQueryObj().setDataState("1");
		List<MaterialSysMessageView> sysMessageViewList  = materialSysMessageMapper.listByObj(sysMessageQuery);
		for (MaterialSysMessageView msg:sysMessageViewList){
			String msgId = msg.getId();
			/*根据用户id和公告id统计*/
			int count = userMessageRelationService.countByUserIdAndMsgId(userId,msgId);
			if (count==0){
				unReadMsgNum  = unReadMsgNum + 1;
			}
		};
		return unReadMsgNum;
	}
}
