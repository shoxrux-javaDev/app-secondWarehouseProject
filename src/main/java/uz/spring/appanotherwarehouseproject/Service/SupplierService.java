package uz.spring.appanotherwarehouseproject.Service;

import org.springframework.stereotype.Service;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Entity.Supplier;
import uz.spring.appanotherwarehouseproject.Repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Response addAll(List<Supplier> supplierList) {
        List<Supplier> list = supplierRepository.saveAll(supplierList);
        if (list.isEmpty()) return new Response("empty", false);
        return new Response("add all", true);
    }

    public Response addOne(Supplier supplier) {
        supplierRepository.save(supplier);
        return new Response("added", true);
    }

    public Response getAll() {
        List<Supplier> supplierList = supplierRepository.findAll();
        if (supplierList.isEmpty()) return new Response("empty", false);
        return new Response("success", true, supplierList);
    }

    public Response getOne(Integer id) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isEmpty()) return new Response("empty", false);
        return new Response("success", true, supplierOptional.get());
    }

    public Response editOne(Integer id, Supplier supplier) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(id);
        if (supplierOptional.isEmpty()) return new Response("false", false);
        Supplier supplier1 = supplierOptional.get();
        supplier1.setName(supplier.getName());
        supplier1.setPhoneNumber(supplier.getPhoneNumber());
        supplier1.setActive(supplier.isActive());
        supplierRepository.save(supplier1);
        return new Response("updated", true);
    }

    public Response deleteAll() {
        supplierRepository.deleteAll();
        return new Response("deleted all", true);
    }

    public Response deleteOne(Integer id) {
        supplierRepository.deleteById(id);
        return new Response("deleted", true);
    }
}
