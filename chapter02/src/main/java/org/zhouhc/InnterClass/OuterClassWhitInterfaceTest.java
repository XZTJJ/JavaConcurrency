package org.zhouhc.InnterClass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OuterClassWhitInterfaceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(OuterClassNoInterfaceTest.class);


    public static void main(String[] args) {
        //创建外部类对象和内部类对象
        OuterClassWhitInterface ocf = new OuterClassWhitInterface();
        OuterInterface icf = ocf.getOuterInterfaceInstance();
        //通过外部类获取对象
        Object outerClass = icf.getOuterClass();

        if(ocf == outerClass)
            LOGGER.info("同一个实例");

        //进行值的比较
        for(int i = 0;i<5;i++){
            ocf.addItem("ocf"+i);
            ocf.getItem();
            icf.addItem("icf"+i);
            icf.getItem();
        }
    }
}
