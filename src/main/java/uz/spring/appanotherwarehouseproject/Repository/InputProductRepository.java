package uz.spring.appanotherwarehouseproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.spring.appanotherwarehouseproject.Entity.InputProduct;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InputProductRepository extends JpaRepository<InputProduct,Integer> {
    @Query(value="select * from input_product ip join input i on ip.input_id=i.id where i.date=:date",nativeQuery = true)
    List<InputProduct> getAllProductWithData(LocalDate date);
}
