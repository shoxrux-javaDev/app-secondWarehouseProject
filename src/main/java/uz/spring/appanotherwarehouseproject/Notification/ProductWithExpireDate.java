package uz.spring.appanotherwarehouseproject.Notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.spring.appanotherwarehouseproject.Entity.InputProduct;
import uz.spring.appanotherwarehouseproject.Repository.InputProductRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@Component
public class ProductWithExpireDate {
    final InputProductRepository inputProductRepository;
    @Scheduled(initialDelayString = "PT1S",fixedDelay =1000L /*cron = "0 0 * * * *"*/)
    public void nearFixedTime() {
        int number = 1;
        Map<Integer,Object> listOfProducts = new HashMap<>();
        List<InputProduct> list = inputProductRepository.findAll();
        for (InputProduct inputProduct : list) {
            LocalDate expireDate = inputProduct.getExpireDate();
            long between = ChronoUnit.DAYS.between(expireDate, LocalDate.now());
            if (between < 30) {
                listOfProducts.put(number++,inputProduct.getProductId());
            }
        }
    }
}


