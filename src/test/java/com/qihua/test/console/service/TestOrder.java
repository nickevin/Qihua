package com.qihua.test.console.service;

import org.joda.time.DateTime;

public class TestOrder extends Thread {

    public TestOrder(String name) {
        super(name);
    }

    public void run() {
        try {
            System.out.println("TestOrder.run()" + generateOrderNo());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new TestOrder(String.valueOf(i)).start();
        }
    }

    public static synchronized String generateOrderNo() throws Exception {
        Thread.sleep(1);

        return new DateTime().toString("yyyyMMddHHmmssSSS");
    }
}
