/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.util;

import cn.edu.sdut.softlab.controller.StudentController;
import cn.edu.sdut.softlab.entity.Student;
import cn.edu.sdut.softlab.service.StudentFacade;
import cn.edu.sdut.softlab.service.TeamFacade;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author huanlu
 */
@Named(value = "csvUtil")
@ApplicationScoped
public class CSVUtil {

    @Inject
    Logger logger;

    @Inject
    StudentFacade studentService;
    
    @Inject
    TeamFacade teamService;
    
    @Inject
    StringUtil stringUtil;
    
    @Inject
    StudentController stuContoller;
    
    @Inject
    FacesContext facesContext;
    
    /**
     *
     * @param file 上传的文件
     * @param delimiter 选择csv文件的分隔符
     * @param isNeedHeader 选择是否读取表头
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void read(UploadedFile file, char delimiter, boolean isNeedHeader) throws FileNotFoundException, IOException, Exception {
        try {
            CsvReader csvReader = new CsvReader(file.getInputstream(), delimiter, Charset.forName("UTF-8"));
            //选择是否读取表头
            if (isNeedHeader) {
            } else {
                csvReader.readHeaders();
            }
            List<Student> stulist = new ArrayList<>();
            while (csvReader.readRecord()) {//逐行读入数据
                //去掉数据中的""引号,并将其根据分隔符转换成字符串
                String data = stringUtil.stripSpaces(csvReader.getRawRecord(),'"');
                String[] stuInf = stringUtil.stringAnalytical(data, ',');
                for (int i = 0; i < stuInf.length; i++) {
                    System.out.println(stuInf[i]);
                }
                Student stu = createStudent(stuInf);
                stuContoller.addSingleStudentByList(stu);
            }
            csvReader.close();
        } 
        finally{
            facesContext.addMessage(null, new FacesMessage("添加成功!"));
        }
    }
    
    /**
     * 
     * @param s
     * @return 
     */
    public String[] toStrings(Student s){
        String[] stu = {Integer.toString(s.getId()),s.getName(),s.getPassword()
                ,s.getIdCard(),s.getStudentNum().toString(),s.getTeam().getName()};  
        return stu;
    }
    
    /**
     * 
     * @param path  生成csv文件所选路径
     * @param delimiter 选择分隔符h
     * @param stu_team_id 学生班级id
     * @throws Exception 
     */
    public void writeWithStudentByTeam(String path,char delimiter,Integer stu_team_id) throws Exception{
        try {
            File f = new File("/home/huanlu/" + path + "/" + teamService.findById(stu_team_id).getName() + ".csv");
            OutputStream output = new FileOutputStream(f);
            CsvWriter csvWriter = new CsvWriter(output, delimiter, Charset.forName("UTF-8"));
            String[] tableheader = {"Id","学生姓名","密码","身份证号","学号","所在班级"};
            csvWriter.writeRecord(tableheader);
            List<Student> stus = studentService.findByTeam(stu_team_id);
            for (Student s:stus) {
                csvWriter.writeRecord(this.toStrings(s));
            }
            csvWriter.flush();
            csvWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ResultSetMetaData getResultSetMetaData(String table_name) throws Exception {
        String sql = "select * from " + table_name;
        PreparedStatement ps = studentService.getEm().unwrap(Connection.class).prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        return resultSet.getMetaData();
    }
    
    /**
     * 获取数据表的表头信息
     * @param table_name
     * @return String[] 返回表头信息
     * @throws Exception 
     */
//    public Map<String,Object> getColumnName(String table_name) throws Exception {
//        String sql = "select * from " + table_name;
//        PreparedStatement ps = em.unwrap(java.sql.Connection.class).prepareStatement(sql);
//        ResultSet resultSet = ps.executeQuery();
//        ResultSetMetaData md = resultSet.getMetaData();
//        int columnCount = md.getColumnCount();
//        JSONArray columnName = new JSONArray();
//        for(int i = 1; i <= columnCount; i++)
//        {
//            JSONObject object = new JSONObject();
//            logger.info(md.getColumnName(i));
//            columnName.add(object);
//        }
//        return null;
//    }
    
//    public void test(String table_name) throws SQLException{
//        EntityManagerFactory emf = em.getEntityManagerFactory();
//        Map<String,Object> emfproperties = emf.getProperties();
//        //logger.info(emfproperties.toString());
//        List<String> key = new ArrayList<>(emfproperties.keySet());
//        for (String s : key) {
//            System.out.println("000 " + s);
//        }
//        DatabaseMetaData metadata = null;
//    }

    private Student createStudent(String[] stuInf) {
        Student stu = new Student();
        stu.setId(Integer.valueOf(stuInf[0]));
        stu.setName(stuInf[1]);
        stu.setPassword(stuInf[2]);
        stu.setIdCard(stuInf[3]);
        stu.setStudentNum(new BigInteger(stuInf[4]));
        stu.setTeam(teamService.findByName(stuInf[5]));
        return stu;
    }
    
}
