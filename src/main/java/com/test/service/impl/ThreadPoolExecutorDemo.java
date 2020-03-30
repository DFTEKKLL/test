package com.test.service.impl;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

/**
 * @author yudeng
 * @date 2020/01/15
 */
@Configuration
@EnableAsync
public class ThreadPoolExecutorDemo {

    /**
     * 线程池核心线程池大小
     */
    private static final int CORE_POOL_SIZE = 20;

    /**
     * 线程池最大线程池大小
     */
    private static final int MAX_POOL_SIZE = 100;

    /**
     * 线程最大空闲时间
     */
    private static final long KEEP_ALIVE = 60L;

    /**
     * 缓存队列
     */
    private static final int QUEUE_CAPACITY = 1000;

    /**
     * 阻塞队列
     * SynchronousQueue:直接提交队列
     * ArrayBlockingQueue:有界的任务队列
     * LinkedBlockingQueue:无界的任务队列
     * PriorityBlockingQueue:优先任务队列
     */
    private static final BlockingQueue<Runnable> BLOCKING_QUEUE = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    /**
     * 拒绝执行处理程序
     * AbortPolicy（默认）：直接抛弃
     * CallerRunsPolicy：用调用者的线程执行任务
     * DiscardOldestPolicy：抛弃队列中最久的任务
     * DiscardPolicy：抛弃当前任务
     */
    private static final RejectedExecutionHandler REJECTED_EXECUTION_HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();

    @Bean
    public ThreadPoolExecutor openThreadPool() {
        return new ThreadPoolExecutor(CORE_POOL_SIZE,
                                      MAX_POOL_SIZE,
                                      KEEP_ALIVE,
                                      TimeUnit.SECONDS,
                                      BLOCKING_QUEUE,
                                      new ThreadFactoryBuilder().setNameFormat("thread-pool-%d").build(),
                                      REJECTED_EXECUTION_HANDLER);
    }
}