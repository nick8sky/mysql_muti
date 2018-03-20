package org.kx.nick;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * create by sunkx on 2018/3/17
 */
@Component
public class UserManager {

    @Autowired
    UserDAO userDAO;

    long id =22l;

    @Transactional
    public  void select2(){
        System.out.println("start "+new Date());
        //userDAO.insert("nick","hello");

        User user = userDAO.selectByUserId(id-1);
        System.out.println("start 1 "+user);

        try {

            Thread.sleep(7000L);
            System.out.println("select2 "+new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
          user = userDAO.selectByUserId(id);
        System.out.println("start 2 "+user);

    }


    public  void update2(){
        try {
            System.out.println("update2 "+new Date());
            int done = userDAO.udpateById("sknx4","123456",id);
            System.out.println("update2 select "+new Date());
            User user = userDAO.selectByUserId(id);
            System.out.println("start 3  "+user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public  void delete1(){

        int done = userDAO.deleteById(id);
        System.out.println("delete "+ done);

    }


    @Transactional
    public  void forUpdate1(){

        userDAO.selectByUserIdForUpdate(5l);
        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userDAO.udpateById("25","25",5l);
        System.out.println("forUpdate1 query "+userDAO.selectByUserId(5l));
    }

    @Transactional
    public  void forUpdate2(){
        userDAO.selectByUserIdForUpdate(5l);
        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("forUpdate2 query "+userDAO.selectByUserId(5l));
        userDAO.udpateById("24","24",5l);
        System.out.println("forUpdate2 query "+userDAO.selectByUserId(5l));
    }
}
