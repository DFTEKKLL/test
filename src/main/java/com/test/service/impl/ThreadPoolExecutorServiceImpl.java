package com.test.service.impl;


import com.google.common.collect.Lists;
import com.test.mapper.TestMapper;
import com.test.service.ThreadPoolExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Service
public class ThreadPoolExecutorServiceImpl implements ThreadPoolExecutorService {

    @Resource
    ThreadPoolExecutorDemo threadPoolExecutorDemo;

    @Override
    public ThreadPoolExecutor exService() {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        List<List<Object>> partitionList = Lists.partition(list, 100);
        //ArrayList<List<Object>> list = new ArrayList<>();
        long l = System.currentTimeMillis();
        partitionList.stream().forEach(e -> {
            e.stream().forEach(ee -> {
                threadPoolExecutorDemo.openThreadPool().execute(() -> {
                    try {
                        //Thread.sleep(1000);
                        //testMapper.testccc(ee.toString());
                        //System.out.println(Thread.currentThread().getName());
                    } catch (Exception eee) {
                        eee.printStackTrace();
                    }
                });
            });
        });
        long l1 = System.currentTimeMillis();
        System.out.println((l1 - l));
        log.info("运行时间",l1 - l);
        return null;
    }
}