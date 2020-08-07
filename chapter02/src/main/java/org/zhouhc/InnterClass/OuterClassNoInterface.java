package org.zhouhc.InnterClass;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

//没有实现其他接口的内部类
public class OuterClassNoInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(OuterClassNoInterface.class);
    private Object[] item;
    private final Set<String> testSet = new TreeSet<String>();
    private int index;

    public OuterClassNoInterface(int size) {
        this.item = new Object[size];
        index = 0;
        testSet.add("222");
        testSet.add("222");
        testSet.add("222");
    }

    public OuterClassNoInterface() {
        this.item = new Object[10];
        index = 0;
        testSet.add("222");
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


    //测试在方法中定义的内部类
    public OuterInterface classInMenthod(){
        class MethodClass implements OuterInterface{
            @Override
            public Object getOuterClass() {
                return OuterClassNoInterface.this;
            }

            @Override
            public void addItem(Object t) {

            }

            @Override
            public void getItem() {

            }
        }
        return new MethodClass();
    }

    //内部类
    public  class InnerClassNoInterface{
        private int indexOfInner;
        //创建构造函数
        public InnerClassNoInterface() {
            indexOfInner = index;
        }

        //获取外部类实例
        public Object getOuterClass(){
            return OuterClassNoInterface.this;
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
