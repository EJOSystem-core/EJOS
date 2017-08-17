/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.entity.Student;
import cn.edu.sdut.softlab.util.CSVUtil;
import cn.edu.sdut.softlab.util.StringUtil;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author huanlu
 */
@ManagedBean(name = "fileController")
@ApplicationScoped
public class FileController {
    
    @Inject
    Logger logger;
    
    @Inject
    StringUtil stringUtil;
    
    @Inject
    CSVUtil csv;
    
    public String getWildflyRootPath(){
        return System.getProperty("user.dir");
    }

    public void getUploadFile(FileUploadEvent event) throws Exception {
        String filecontenttype = event.getFile().getContentType();
        UploadedFile file =  event.getFile();
        csv.read(file, ',', false);
    }
    
    public List<String> getUploadHead(UploadedFile file){
        String tableheader = new String(file.getContents(),0,55);
        String[] headers = stringUtil.stringAnalytical(tableheader, ',');
        return Arrays.asList(headers);
    }
    
    public Map<String,Object> test(List<String> headers,Student stu){
        Map<String,Object> map = new HashMap<>();
        for (String header : headers) {
            if (header.equals("Id")) {
                map.put("Id", stu.getId());
            }if (header.equals("学生姓名")) {
                map.put("Name", stu.getName());
            }if (header.equals("密码")) {
                map.put("Passwod", stu.getPassword());
            }if (header.equals("身份证号")) {
                map.put("IdCard", stu.getIdCard());
            }if (header.equals("学号")) {
                map.put("StuNum", stu.getStudentNum());
            }if (header.equals("所在班级")) {
                map.put("Team", stu.getTeam());
            }
        }
        return map;
    }

    public Map<Map<String, String>, Object> getUploadHeader(UploadedFile file) {
        String headers = new String(file.getContents(), 0, 55);
        String[] headStrings = stringUtil.stringAnalytical(headers, ',');
        Map<String, String> header = new HashMap<String, String>();
        return null;
    }
}
