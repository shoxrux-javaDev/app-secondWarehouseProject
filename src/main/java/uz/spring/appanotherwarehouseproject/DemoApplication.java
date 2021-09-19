package uz.spring.appanotherwarehouseproject;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);


        /*int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        matrix[1] = new int[]{44, 44, 44};
        System.out.println(Arrays.deepToString(matrix));*/
/*@Scheduled(fixedDelay = 1000L )
    void someJob() throws InterruptedException {
        System.out.println("First start " + new Date().getSeconds());
        Thread.sleep(3000L);
        System.out.println("First finish " + new Date().getSeconds());
    }*/


        /*@Configuration
        @EnableScheduling
        @ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
        class SchedulingConfiguration {

        }*/

    }
}
