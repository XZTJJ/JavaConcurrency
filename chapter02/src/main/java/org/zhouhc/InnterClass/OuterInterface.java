package org.zhouhc.InnterClass;

import java.util.Arrays;

public interface OuterInterface {
    //获取外部类实例
    public Object getOuterClass();
    //读取和设置实例对象
    public void addItem(Object t);

    //读取属性
    public void getItem();
}
