package uz.spring.appanotherwarehouseproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.spring.appanotherwarehouseproject.Entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
}
