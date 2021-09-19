package uz.spring.appanotherwarehouseproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.spring.appanotherwarehouseproject.Entity.OutputProduct;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OutputProductRepository extends JpaRepository<OutputProduct, Integer> {
    @Query(value = "select * from output_product op join output o on op.output_id = o.id where o.date=:date ", nativeQuery = true)
    List<OutputProduct> getAllBestProductOnThisDay(LocalDate date);
}
