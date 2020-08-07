package org.zhouhc.InnterClass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OuterClassNoInterfaceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(OuterClassNoInterfaceTest.class);


    public static void main(String[] args) {
        //创建外部类对象和内部类对象
        OuterClassNoInterface ocf = new OuterClassNoInterface();
        OuterClassNoInterface.InnerClassNoInterface icf = ocf.new InnerClassNoInterface();
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

        Object outerInterface = ocf.classInMenthod().getOuterClass();


    }
}
