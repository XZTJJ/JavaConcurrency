package org.zhouhc.charpter07;

public class ThrowException {


    public static void main(String[] args) {
        testReturn();
    }

    public static Integer testReturn(){
        try {
            while (true) {
                try {
                    return throwExceptionTest();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            System.out.println("测试finally");
        }
    }

    public static Integer throwExceptionTest() throws InterruptedException{
        throw new InterruptedException("中断异常");
    }
}
