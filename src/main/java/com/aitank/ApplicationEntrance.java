package com.aitank;

import com.aitank.server.TcpServer;
import com.aitank.server.config.RedisManager;
import com.aitank.server.context.SpringContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ApplicationEntrance {

    class TestObject {
        private int a ;
        private int b ;
        private String c ;

        public TestObject(int a, int b ,String c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
        public TestObject(){
            System.out.println("Create TestObject");
        }
        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public String getC() {
            return c;
        }
        public void setA(int a){
            this.a = a;
        }
        public void setB(int b){
            this.b = b;
        }
        public void setC(String c){
            this.c = c;
        }
    }
    public static void main(String args[]){
        System.out.println("Welcome to AI Tank !");
        ApplicationEntrance applicationEntrance = new ApplicationEntrance();
        applicationEntrance.runServer();
    }

    public void runServer(){
        ConfigurableApplicationContext context = new FileSystemXmlApplicationContext("classpath:/spring/applicationContext.xml");

        RedisManager redisManager = SpringContext.getRedisManager();
        redisManager.set("AiTank","object");
        TestObject testObject = new TestObject(5,10,"Netflax");
        redisManager.set("testObject",testObject);

        new TcpServer().startTcpServer();
    }
}
