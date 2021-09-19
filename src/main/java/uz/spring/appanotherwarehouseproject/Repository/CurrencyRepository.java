package uz.spring.appanotherwarehouseproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.spring.appanotherwarehouseproject.Entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
}
