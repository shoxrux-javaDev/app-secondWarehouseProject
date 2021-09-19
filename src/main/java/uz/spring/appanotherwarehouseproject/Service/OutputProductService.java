package uz.spring.appanotherwarehouseproject.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.spring.appanotherwarehouseproject.Dto.OutputProductDto;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Entity.Output;
import uz.spring.appanotherwarehouseproject.Entity.OutputProduct;
import uz.spring.appanotherwarehouseproject.Entity.Product;
import uz.spring.appanotherwarehouseproject.Repository.OutputProductRepository;
import uz.spring.appanotherwarehouseproject.Repository.OutputRepository;
import uz.spring.appanotherwarehouseproject.Repository.ProductRepository;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class OutputProductService {
    final OutputProductRepository outputProductRepository;
    final ProductRepository productRepository;
    final OutputRepository outputRepository;

    public Response addAll(List<OutputProductDto> outputProductDtoList) {
        for (OutputProductDto outputProductDto : outputProductDtoList) {
            Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
            if (optionalOutput.isEmpty()) return new Response("empty", false);
            Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
            if (optionalProduct.isEmpty()) return new Response("empty", false);
            OutputProduct outputProduct = new OutputProduct();
            outputProduct.setAmount(outputProductDto.getAmount());
            outputProduct.setPrice(outputProductDto.getPrice());
            outputProduct.setOutput(optionalOutput.get());
            outputProduct.setProduct(optionalProduct.get());
            outputProductRepository.save(outputProduct);
        }
        return new Response("added all", true);
    }

    public Response addOne(OutputProductDto outputProductDto) {
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());
        if (optionalOutput.isEmpty()) return new Response("empty", false);
        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        if (optionalProduct.isEmpty()) return new Response("empty", false);
        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setOutput(optionalOutput.get());
        outputProductRepository.save(outputProduct);
        return new Response("added all", true);
    }

    public Response getAll() {
        List<OutputProduct> productList = outputProductRepository.findAll();
        if (productList.isEmpty()) return new Response("empty", false);
        return new Response("success", true, productList);
    }

    public Response getOne(Integer id) {
        Optional<OutputProduct> productOptional = outputProductRepository.findById(id);
        if (productOptional.isEmpty()) return new Response("empty", false);
        return new Response("success", true, productOptional.get());
    }

    public Response editOne(Integer id, OutputProductDto outputProductDto) {
        Optional<Output> outputOptional = outputRepository.findById(id);
        if (outputOptional.isEmpty()) return new Response("empty", false);
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isEmpty()) return new Response("empty", false);
        Optional<OutputProduct> outputProduct = outputProductRepository.findById(id);
        if (outputProduct.isEmpty()) return new Response("empty", false);
        OutputProduct outputProduct1 = outputProduct.get();
        outputProduct1.setAmount(outputProductDto.getAmount());
        outputProduct1.setPrice(outputProductDto.getPrice());
        outputProduct1.setProduct(productOptional.get());
        outputProduct1.setOutput(outputOptional.get());
        outputProductRepository.save(outputProduct1);
        return new Response("edited", true);
    }

    public Response deleteAll() {
        outputProductRepository.deleteAll();
        return new Response("deleted all", true);
    }

    public Response deleteOne(Integer id) {
        outputProductRepository.deleteById(id);
        return new Response("deleted", true);
    }

    public Response getAllOutputProduct(LocalDate localdate) {
        int generator = 0;
        LinkedHashMap<String,Integer> reverseSortedMap =new LinkedHashMap<>();
        HashMap<String, Integer> object = new HashMap<>();
        List<OutputProduct> productList = outputProductRepository.getAllBestProductOnThisDay(localdate);
        if (productList.isEmpty()) return new Response("empty", false);
        for (OutputProduct outputProduct : productList) {
            String name = outputProduct.getProduct().getName();
            if (generator==0) object.put(name, 1);
            if (generator > 1) {
                if (object.containsKey(name)) {
                    object.compute(name, (key, val) -> (val == null) ? 1 : val +1);
                } else {
                    object.put(name,1);
                }
            }
            generator++;
            if (productList.size() == generator) {
                object.entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
            }
        }
        return new Response("success", true, reverseSortedMap);
    }
}
