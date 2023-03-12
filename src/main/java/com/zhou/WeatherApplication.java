package com.zhou;

import com.zhou.service.Pusher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling // 开启定时任务
public class WeatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
        Pusher.push();
    }

//    @Scheduled(cron = "0 30 7 * * ?")
//    public void goodMorning(){
//        Pusher.push();
//    }
}
