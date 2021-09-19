package uz.spring.appanotherwarehouseproject.Service;

import org.springframework.stereotype.Service;
import uz.spring.appanotherwarehouseproject.Dto.InputProductDto;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Entity.Input;
import uz.spring.appanotherwarehouseproject.Entity.InputProduct;
import uz.spring.appanotherwarehouseproject.Repository.InputProductRepository;
import uz.spring.appanotherwarehouseproject.Repository.InputRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InputProductService {
    final InputProductRepository inputProductRepository;
    final InputRepository inputRepository;

    public InputProductService(InputProductRepository inputProductRepository, InputRepository inputRepository) {
        this.inputProductRepository = inputProductRepository;
        this.inputRepository = inputRepository;
    }

    public Response addAll(List<InputProductDto> inputProductDtoList) {
        for (InputProductDto inputProductDto : inputProductDtoList) {
            Optional<Input> inputOptional = inputRepository.findById(inputProductDto.getInputId());
            if (inputOptional.isEmpty()) return new Response("empty", false);
            InputProduct inputProduct = new InputProduct();
            inputProduct.setAmount(inputProductDto.getAmount());
            inputProduct.setInput(inputOptional.get());
            inputProduct.setPrice(inputProductDto.getPrice());
            inputProduct.setExpireDate(inputProductDto.getExpireDate());
            inputProduct.setProductId(inputProductDto.getProductId());
            inputProductRepository.save(inputProduct);
        }
        return new Response("added all", true);
    }

    public Response addOne(InputProductDto inputProductDto) {
        Optional<Input> inputOptional = inputRepository.findById(inputProductDto.getInputId());
        if (inputOptional.isEmpty()) return new Response("empty", false);
        InputProduct inputProduct = new InputProduct();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setInput(inputOptional.get());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setProductId(inputProductDto.getProductId());
        inputProductRepository.save(inputProduct);
        return new Response("added all", true);
    }

    public Response getAll() {
        List<InputProduct> productList = inputProductRepository.findAll();
        if (productList.isEmpty()) return new Response("empty", false);
        return new Response("success", true, productList);
    }

    public Response getOne(Integer id) {
        Optional<InputProduct> productOptional = inputProductRepository.findById(id);
        if (productOptional.isEmpty()) return new Response("empty", false);
        return new Response("success", true, productOptional.get());
    }

    public Response editOne(Integer id, InputProductDto inputProductDto) {
        Optional<Input> inputOptional = inputRepository.findById(inputProductDto.getInputId());
        if (inputOptional.isEmpty()) return new Response("empty", false);
        Optional<InputProduct> productOptional = inputProductRepository.findById(id);
        if (productOptional.isEmpty()) return new Response("empty", false);
        InputProduct inputProduct = productOptional.get();
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setProductId(inputProductDto.getProductId());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());
        inputProduct.setInput(inputOptional.get());
        inputProductRepository.save(inputProduct);
        return new Response("edited", true);
    }

    public Response deleteAll() {
        inputProductRepository.deleteAll();
        return new Response("deleted all", true);
    }

    public Response deleteOne(Integer id) {
        inputProductRepository.deleteById(id);
        return new Response("deleted", true);
    }

    public Response getAllCalculatedValue(LocalDate date) {
        int allPrice = 0;
        double allAmount = 0;
        List<InputProduct> inputProductList = inputProductRepository.getAllProductWithData(date);
        if (inputProductList.isEmpty()) return new Response("date not found", false);
        for (InputProduct inputProduct : inputProductList) {
            Integer amount1 = inputProduct.getAmount();
            allAmount += amount1;
            String price = inputProduct.getPrice();
            allPrice += Double.parseDouble(price);
        }
        Map<String, Object> object = new HashMap<>();
        object.put("soni", allAmount);
        object.put("sum", allPrice);
        return new Response("success", true, object);

    }
}
