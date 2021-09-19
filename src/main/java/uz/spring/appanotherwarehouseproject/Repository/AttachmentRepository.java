package uz.spring.appanotherwarehouseproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.spring.appanotherwarehouseproject.Entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Integer> {

}
