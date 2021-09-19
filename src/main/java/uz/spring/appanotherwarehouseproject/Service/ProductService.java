package uz.spring.appanotherwarehouseproject.Service;

import org.springframework.stereotype.Service;
import uz.spring.appanotherwarehouseproject.Dto.ProductDto;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Entity.Attachment;
import uz.spring.appanotherwarehouseproject.Entity.Category;
import uz.spring.appanotherwarehouseproject.Entity.Measurement;
import uz.spring.appanotherwarehouseproject.Entity.Product;
import uz.spring.appanotherwarehouseproject.Repository.AttachmentRepository;
import uz.spring.appanotherwarehouseproject.Repository.CategoryRepository;
import uz.spring.appanotherwarehouseproject.Repository.MeasurementRepository;
import uz.spring.appanotherwarehouseproject.Repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;
    final AttachmentRepository attachmentRepository;
    final MeasurementRepository measurementRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository,
                          AttachmentRepository attachmentRepository, MeasurementRepository measurementRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.attachmentRepository = attachmentRepository;
        this.measurementRepository = measurementRepository;
    }

    public Response addAll(List<ProductDto> productDtoList) {
        for (ProductDto productDto : productDtoList) {
            Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoryId());
            if(categoryOptional.isEmpty()) return new Response("empty",false);
            Optional<Attachment> attachmentOptional = attachmentRepository.findById(productDto.getPhotoId());
            if(attachmentOptional.isEmpty()) return new Response("empty",false);
            Optional<Measurement> measurementOptional = measurementRepository.findById(productDto.getMeasurementId());
            if(measurementOptional.isEmpty()) return new Response("empty",false);
            Product product = new Product();
            product.setActive(productDto.isActive());
            product.setCode(productDto.getCode());
            product.setName(productDto.getName());
            product.setPhoto(attachmentOptional.get());
            product.setCategory(categoryOptional.get());
            product.setMeasurement(measurementOptional.get());
            product.setActive(productDto.isActive());
            productRepository.save(product);
        }
        return new Response("added all",true);
    }

    public Response addOne(ProductDto productDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoryId());
        if(categoryOptional.isEmpty()) return new Response("empty",false);
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(productDto.getPhotoId());
        if(attachmentOptional.isEmpty()) return new Response("empty",false);
        Optional<Measurement> measurementOptional = measurementRepository.findById(productDto.getMeasurementId());
        if(measurementOptional.isEmpty()) return new Response("empty",false);
        Product product = new Product();
        product.setActive(productDto.isActive());
        product.setCode(productDto.getCode());
        product.setName(productDto.getName());
        product.setPhoto(attachmentOptional.get());
        product.setCategory(categoryOptional.get());
        product.setMeasurement(measurementOptional.get());
        product.setActive(productDto.isActive());
        productRepository.save(product);
        return new Response("added all",true);
    }

    public Response getAll() {
        List<Product> productList = productRepository.findAll();
        if(productList.isEmpty()) return new Response("empty",false);
        return new Response("success",true,productList);
    }

    public Response getOne(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()) return new Response("empty",false);
        return new Response("empty",true,productOptional.get());
    }

    public Response editOne(Integer id, ProductDto productDto) {
        Optional<Category> categoryOptional = categoryRepository.findById(productDto.getCategoryId());
        if(categoryOptional.isEmpty()) return new Response("empty",false);
        Optional<Measurement> measurementList = measurementRepository.findById(productDto.getMeasurementId());
        if(measurementList.isEmpty()) return new Response("empty",false);
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(productDto.getPhotoId());
        if(attachmentOptional.isEmpty()) return new Response("empty",false);
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) return new Response("empty",false);
        Product product = productOptional.get();
        product.setPhoto(attachmentOptional.get());
        product.setMeasurement(measurementList.get());
        product.setActive(productDto.isActive());
        product.setCategory(categoryOptional.get());
        product.setName(productDto.getName());
        product.setCode(productDto.getCode());
        productRepository.save(product);
        return new Response("edited",true);
    }

    public Response deleteAll() {
        productRepository.deleteAll();
        return new Response("deleted all",true);
    }

    public Response deleteOne(Integer id) {
        productRepository.deleteById(id);
        return new Response("deleted",true);
    }
}
