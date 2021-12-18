package com.mastery.java.task;

import com.mastery.java.task.thread.MyThread;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleWebApplication.class, args);

//        Thread myThread = new Thread(new MyThread());
//        myThread.start();
    }
}
