package com.example.demo;

public class demo1 {
    public static void main(String[] args) {


        myThread myThread = new myThread();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.print("i="+i +"A  ");
                myThread.print();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                System.out.print("i="+i + "B  ");
                myThread.print();
            }
        },"B").start();


    }


}

class myThread{

    public synchronized void print(){
        System.out.println("hello world");
    }

}
