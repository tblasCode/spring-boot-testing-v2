package com.jos.sbt;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Main Application.
 *
 */
@SpringBootApplication
public class MyApplication {
    /**
     * MAX_POOL_SIZE.
     */
    private static final int MAX_POOL_SIZE = 2;
    /**
     * CORE_POOL_SIZE.
     */
    private static final int CORE_POOL_SIZE = 2;
    /**
     * QUEUE_CAPACITY.
     */
    private static final int QUEUE_CAPACITY = 500;
    /**
     * THREAD_NAME_PREFIX.
     */
    private static final String THREAD_NAME_PREFIX = "GithubLookup-";

    /**
     *
     * Main entry point.
     * @param args - main arguments
     */
    public static void main(final String... args) {
        SpringApplication.run(MyApplication.class, args);
    }

    /**
     *
     * taskExecutor.
     * @return Executor
     */
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        executor.initialize();
        return executor;
    }
}
