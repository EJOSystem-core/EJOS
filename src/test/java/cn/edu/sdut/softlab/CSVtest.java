/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab;

import com.csvreader.CsvReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Test;

/**
 *
 * @author huanlu
 */
public class CSVtest {

    @Inject
    Logger logger;

    @Inject
    EntityManager em;

//    @Test
//    public void readerTest() throws Exception{
//        CsvReader csvReader = new CsvReader("/home/huanlu/Users.csv");
//        logger.info(csvReader.get(0));
//    }
    @Test
    public void read() throws FileNotFoundException, IOException {
        try {
            CsvReader csvReader = new CsvReader("/home/huanlu/Users.csv", ',', Charset.forName("UTF-8"));

            //选择是否读取表头
            if (true) {
            } else {
                csvReader.readHeaders();
            }
            while (csvReader.readRecord()) {//逐行读入数据
                System.out.print(csvReader.getRawRecord());
                System.out.print(csvReader.get("用户名"));
            }
            csvReader.close();
        } catch (IOException e) {
        }
    }

    @Test
    public void headerSort() throws Exception {
        //EntityManagerFactory factory = Persistence.createEntityManagerFactory("labUnit");
        //EntityManager em = factory.createEntityManager();
        UserTransaction transaction = (UserTransaction) em.getTransaction();
        transaction.begin();

        String sql = "select * from teacher";
        PreparedStatement ps = em.unwrap(Connection.class).prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();
        ResultSetMetaData md = resultSet.getMetaData();
        int columnCount = md.getColumnCount();
        System.out.print("返回结果字段个数:" + columnCount);

        transaction.commit();
        //em.close();
        //factory.close();
    }

}
