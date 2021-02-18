package com.edgedo.material.timetask;

import com.edgedo.material.entity.MaterialCase;
import com.edgedo.material.entity.MaterialConfig;
import com.edgedo.material.queryvo.MaterialCaseView;
import com.edgedo.material.service.MaterialCaseService;
import com.edgedo.material.service.MaterialConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/*
*发现页案例随机定时任务
* */
@Component
public class RandomFindCaseTask {

    @Autowired
    MaterialCaseService caseService;

    @Autowired
    MaterialConfigService materialConfigService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void randomFindCase(){
        Date dTime = new Date();
        MaterialConfig materialConfig = materialConfigService.loadById("FIND_RANDOM_NUM");
        if (materialConfig == null){
            materialConfig = new MaterialConfig();
            materialConfig.setValue("2");
        }
        String findRandomNumStr = materialConfig.getValue();
        int findRandomNum = Integer.parseInt(findRandomNumStr);
        //查询所有的案例
        List<MaterialCaseView> materialCaseList = caseService.listAll();
        //随机排序
        Collections.shuffle(materialCaseList);
        for(int i= 0;i<materialCaseList.size();i++){
            MaterialCaseView caseView = materialCaseList.get(i);
            Date updateTime = caseView.getUpdateTime();
            if (updateTime == null){
                updateTime = dTime;
            }
            if (compareDate(updateTime,dTime,findRandomNum)){
                //判断时间差
                caseView.setFindOrderNum(new BigDecimal((i + 1) + ""));
                caseView.setUpdateTime(dTime);
                caseService.update(caseView);
            }else {
                break;
            }
        }
    }

    public boolean compareDate(Date updateTime,Date dTime,int num ){
        Calendar cal = Calendar.getInstance();
        cal.setTime(updateTime);
        cal.add(Calendar.DATE,num);
        Date startDate = cal.getTime();
        return  startDate.getTime() <= dTime.getTime();
    }
}
