package uz.spring.appanotherwarehouseproject.Controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.spring.appanotherwarehouseproject.Dto.ProductDto;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Entity.Supplier;
import uz.spring.appanotherwarehouseproject.Service.SupplierService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/supplier")
public class SupplierController {
    final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PreAuthorize(value = "hasAuthority('POST_ALL')")
    @PostMapping
    public HttpEntity<?> addAllCategory(@RequestBody List<Supplier> supplierList) {
        Response response = supplierService.addAll(supplierList);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('POST_ONE')")
    @PostMapping("/one")
    public HttpEntity<?> addOneCategory(@RequestBody Supplier supplier) {
        Response response = supplierService.addOne(supplier);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('GET_ALL')")
    @GetMapping
    public HttpEntity<?> getALlCategory() {
        Response response = supplierService.getAll();
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response);
    }

    @PreAuthorize(value = "hasAuthority('GET_ONE')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOneCategory(@PathVariable Integer id) {
        Response response = supplierService.getOne(id);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response);
    }

    @PreAuthorize(value = "hasAuthority('EDIT')")
    @PutMapping("{id}")
    public HttpEntity<?> editCategory(@PathVariable Integer id, @RequestBody Supplier supplier) {
        Response response = supplierService.editOne(id, supplier);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('DELTE_ALL')")
    @DeleteMapping
    public HttpEntity<?> deleteAllCategory() {
        Response response = supplierService.deleteAll();
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('DELETE_ONE')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOneCategory(@PathVariable Integer id) {
        Response response = supplierService.deleteOne(id);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }
}
