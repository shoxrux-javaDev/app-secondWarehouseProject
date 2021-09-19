package uz.spring.appanotherwarehouseproject.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.spring.appanotherwarehouseproject.Entity.ExpiredProduct;

@Repository
public interface ExpireProductRepository extends JpaRepository<ExpiredProduct,Integer> {

}
