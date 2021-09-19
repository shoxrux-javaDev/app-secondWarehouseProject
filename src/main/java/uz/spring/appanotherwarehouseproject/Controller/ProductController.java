package uz.spring.appanotherwarehouseproject.Controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.spring.appanotherwarehouseproject.Dto.OutputProductDto;
import uz.spring.appanotherwarehouseproject.Dto.ProductDto;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Service.ProductService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/product")
public class ProductController {

    final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize(value="hasAuthority('POST_ALL')")
    @PostMapping
    public HttpEntity<?> addAllCategory(@RequestBody List<ProductDto> productDtoList) {
        Response response = productService.addAll(productDtoList);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('POST_ONE')")
    @PostMapping("/one")
    public HttpEntity<?> addOneCategory(@RequestBody ProductDto productDto) {
        Response response = productService.addOne(productDto);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('GET_ALL')")
    @GetMapping
    public HttpEntity<?> getALlCategory() {
        Response response = productService.getAll();
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response);
    }

    @PreAuthorize(value = "hasAuthority('GET_ALL')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOneCategory(@PathVariable Integer id) {
        Response response = productService.getOne(id);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response);
    }

    @PreAuthorize(value = "hasAuthority('EDIT')")
    @PutMapping("{id}")
    public HttpEntity<?> editCategory(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        Response response = productService.editOne(id, productDto);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value="hasAuthority('DELETE_ALL')")
    @DeleteMapping
    public HttpEntity<?> deleteAllCategory() {
        Response response = productService.deleteAll();
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('DELETE_ONE')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOneCategory(@PathVariable Integer id) {
        Response response = productService.deleteOne(id);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }
}
