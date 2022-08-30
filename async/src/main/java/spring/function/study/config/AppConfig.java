package spring.function.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AppConfig {

    /**
     * 새로운 Thread를 만들어줄 수 있다.
     */
    @Bean("async-thread")
    public Executor asyncThread() {
        // Thread 설정값은 경험이 충분히 쌓인 다음에 하는 것이 좋다.
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(100);
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setQueueCapacity(10);
        // 위 설정값을 풀어 설명하자면... CorePool이 10개 차면 -> Queue에 쌓기 시작하고 -> Queue가 10개 차면 -> CorePool이 20개까지 늘어나고 -> 또 CorePool이 10개 차면 -> Queue에 쌓기 시작하고... -> maxPoolSize까지만 가능하다는 뜻이다.
        threadPoolTaskExecutor.setThreadNamePrefix("Async-");
        return threadPoolTaskExecutor;
    }
}
