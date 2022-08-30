package spring.function.study.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncService {

//    @Async // 비동기로 작동하기 위한 애노테이션 (AOP 기반이기 때문에 Proxy 패턴을 탄다. 때문에 public 메서드에만 장착 가능)
    @Async("async-thread") // ThreadPoolTaskExecutor를 Bean으로 등록한 Thread를 사용할 수 있다.
    public CompletableFuture run() {
        return new AsyncResult(this.hello()).completable();
    }

    private String hello() {

        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
                log.info("thread sleep...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("hello service end");
        return "async hello";
    }
}
