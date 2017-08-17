/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.service;

import cn.edu.sdut.softlab.entity.Record;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;

/**
 *
 * @author huanlu
 */
@Named
public class RecordFacade extends AbstractFacade<Record> {

    public RecordFacade() {
        super(Record.class);
    }

    public List<Record> findRecordsByStatus(String status) {
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("status", status);
        return findByNamedQuery("Record.findByStatus", parameters);
    }
    
    public List<Record> findRecordsByStuId(Integer stu_id){
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("stu_id", stu_id);
        return findByNamedQuery("Record.findByStuId", parameters);
    }
    
    public List<Record> findRecordsByStuIdAndStatus(String status,Integer stu_id){
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("status", status);
        parameters.put("stu_id", stu_id);
        return findByNamedQuery("Record.findByStuIdAndStatus", parameters);
    }
    
    public Record findRecordByStuIdAndQuestion(Integer stu_id,Integer ques_id){
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("questionId", ques_id);
        parameters.put("stu_id", stu_id);
        return findSingleByNamedQuery("Record.findByStuIdAndQuestion", parameters, Record.class).get();
    }
}
