package uz.spring.appanotherwarehouseproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.spring.appanotherwarehouseproject.Entity.Input;

import java.util.List;

@Repository
public interface InputRepository extends JpaRepository<Input,Integer> {

}
