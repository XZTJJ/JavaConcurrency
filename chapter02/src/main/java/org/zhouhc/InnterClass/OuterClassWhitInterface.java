package org.zhouhc.InnterClass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class OuterClassWhitInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(OuterClassWhitInterface.class);
    private Object[] item;
    private int index;

    public OuterClassWhitInterface(int size) {
        this.item = new Object[size];
        index = 0;
    }

    public OuterClassWhitInterface() {
        this.item = new Object[10];
        index = 0;
    }

    //添加方法
    public void addItem(Object t) {
        if (index < item.length) {
            item[index] = t;
            index++;
        }
    }

    //读取属性
    public void getItem(){
        LOGGER.info(Arrays.toString(item));
        LOGGER.info("index:"+index);
    }

    //获取内部类的方式
    public OuterInterface getOuterInterfaceInstance(){
        return new InnerClassWithInterface();
    }


    //内部类
    private class InnerClassWithInterface implements OuterInterface{
        private int indexOfInner;
        //创建构造函数
        public InnerClassWithInterface() {
            indexOfInner = index;
        }

        //获取外部类实例
        public Object getOuterClass(){
            return OuterClassWhitInterface.this;
        }
        //读取和设置实例对象
        public void addItem(Object t) {
            indexOfInner = index;
            if (indexOfInner < item.length) {
                item[indexOfInner] = t;
                indexOfInner++;
                index = indexOfInner;
            }
        }

        //读取属性
        public void getItem(){
            LOGGER.info(Arrays.toString(item));
            LOGGER.info("indexOfInner:"+indexOfInner);
        }
    }
}
