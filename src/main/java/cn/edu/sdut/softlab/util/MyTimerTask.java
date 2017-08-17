/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.util;

import cn.edu.sdut.softlab.controller.NewsController;
import cn.edu.sdut.softlab.controller.QuestionController;
import cn.edu.sdut.softlab.entity.Record;
import cn.edu.sdut.softlab.service.RecordFacade;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author huanlu
 */
public class MyTimerTask extends TimerTask {

    @Inject
    Logger logger;

    @Inject
    NewsController newsController;

    @Inject
    QuestionController questionController;
    
    @Inject
    RecordFacade recordService;

    @Inject
    DateUtil dateUtil;

    @Override
    public void run() {
        Calendar calendar = Calendar.getInstance();
        //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info(dateUtil.getNowDate().toString());
        logger.info("TimerTask Called:");
        
        recordService.findAll();
    }

}
