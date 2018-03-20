package org.kx.nick;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CountDownLatch;

/**
 * create by sunkx on 2018/3/17
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)
public class MvccTest {

    @Autowired
    UserManager userManager;

    @Autowired
    UserDAO userDAO;



    @Test
    public void test1() throws InterruptedException {
         CountDownLatch countDownLatch = new CountDownLatch(2);


        Thread thread1 = new Thread(new Runnable(){

            @Override
            public void run() {
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userManager.select2();
            }
        });

        Thread thread2 = new Thread(new Runnable(){

            @Override
            public void run() {
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userManager.update2();
            }
        });

        thread1.start();
        thread2.start();

        //Thread.currentThread().join();
        Thread.sleep(23500l);
    }


    @Test
    public void test12() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);


        Thread thread1 = new Thread(new Runnable(){

            @Override
            public void run() {
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userManager.forUpdate1();

            }
        });

        Thread thread2 = new Thread(new Runnable(){

            @Override
            public void run() {
                countDownLatch.countDown();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1500l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userManager.forUpdate2();
            }
        });

        thread1.start();
        thread2.start();

        //Thread.currentThread().join();
        Thread.sleep(5500l);
    }


}