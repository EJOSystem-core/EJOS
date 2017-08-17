/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.entity.Record;
import cn.edu.sdut.softlab.service.QuestionFacade;
import cn.edu.sdut.softlab.service.RecordFacade;
import cn.edu.sdut.softlab.util.DateUtil;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.UserTransaction;

/**
 *
 * @author huanlu
 */
@RequestScoped
@Named(value = "recordController")
public class RecordController {
    
    @Inject
    Logger logger;
    
    @Inject
    RecordFacade recordService;
    
    @Inject
    QuestionFacade questionService;
    
    @Inject
    DateUtil dateUtil;
    
    @Inject
    UserTransaction utx;
    
    public List<Record> getRecordsLessFour() throws Exception{
        try {
            utx.begin();
            List<Record> curentrecords = recordService.findRecordsByStatus("未完成");
            List<Record> allowRecords;
            for (Record curentrecord : curentrecords) {
//                if (dateUtil.getTwoDay(dateUtil.dateToStr(curentrecord.getTime(),
//                        questionService.findQuestionsById(curentrecord.getQuestionId()).getDeadline())) {
//                    
//                }
            }
            return null;
        } finally{
            utx.commit();
        }
    }
}
