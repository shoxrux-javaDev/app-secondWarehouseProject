package uz.spring.appanotherwarehouseproject.Notification;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.spring.appanotherwarehouseproject.Entity.ExpiredProduct;
import uz.spring.appanotherwarehouseproject.Entity.InputProduct;
import uz.spring.appanotherwarehouseproject.Repository.InputProductRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@AllArgsConstructor
@Component
public class RememberProductWhichNearExpireDate {
    final InputProductRepository inputProductRepository;
    final ExpiredProduct expiredProduct;
    public static String time;
    @Scheduled(fixedDelay=1000L /*cron = "0 0 * * * *"*/)
    public void rememberProducts() {
        int count = 1;
        List<InputProduct> list = inputProductRepository.findAll();
        for (InputProduct inputProduct : list) {
            LocalDate expireDate = inputProduct.getExpireDate();
            long between = ChronoUnit.DAYS.between(expireDate, LocalDate.now());
            if (between < 30) {
                String object = "sizning "+inputProduct.getProductId()+
                        " id li maxsulotingiz srogi tugashiga "+between+" kun qoldi";

            }
        }
    }
}
