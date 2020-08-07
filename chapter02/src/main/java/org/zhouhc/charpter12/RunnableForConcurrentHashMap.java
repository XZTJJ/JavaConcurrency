package org.zhouhc.charpter12;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

//测试数据的安全性
public class RunnableForConcurrentHashMap implements Runnable {
    private final static Logger LOGGER = LoggerFactory.getLogger(RunnableForConcurrentHashMap.class);

    private ConcurrentMap<String, Object> concurrentMap;
    private String clid;
    private String value;
    private Integer total;

    public RunnableForConcurrentHashMap(ConcurrentMap<String, Object> concurrentMap, String clid, String value, Integer total) {
        this.concurrentMap = concurrentMap;
        this.clid = clid;
        this.value = value;
        this.total = total;
    }

    @Override
    public void run() {
        LOGGER.info("输入的数据为:" + clid);
        String[] s = StringUtils.split(clid, "_");
        String key = s[0];
        int num = Integer.parseInt(s[1]);
        //放入总量
        AtomicInteger fist = new AtomicInteger(1);
        String testVaule = (String) concurrentMap.putIfAbsent(key + "_" + num, value);
        if (testVaule != null)
            return;
        AtomicInteger second = (AtomicInteger) concurrentMap.putIfAbsent(key + "_now", fist);
        if (second == null)
            return;
        int nowTotal = second.addAndGet(1);

        if (nowTotal != total)
            return;

        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i <= total; i++) {
            String o = (String) concurrentMap.get(key + "_" + i);
            stringBuffer.append(o);
        }

        LOGGER.info("key为:" + key + ",数据内容为:" + stringBuffer.toString());
    }


}
