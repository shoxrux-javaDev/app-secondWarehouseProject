package uz.spring.appanotherwarehouseproject.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.spring.appanotherwarehouseproject.Dto.InputDto;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Entity.Currency;
import uz.spring.appanotherwarehouseproject.Entity.Input;
import uz.spring.appanotherwarehouseproject.Entity.Supplier;
import uz.spring.appanotherwarehouseproject.Entity.Warehouse;
import uz.spring.appanotherwarehouseproject.Repository.*;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InputService {
    final InputRepository inputRepository;
    final SupplierRepository supplierRepository;
    final WarehouseRepository warehouseRepository;
    final CurrencyRepository currencyRepository;
    final InputProductRepository inputProductRepository;


    public Response addAll(List<InputDto> inputDtoList) {
        for (InputDto inputDto : inputDtoList) {
            Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
            if (optionalSupplier.isEmpty()) return new Response("empty",false);
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
            if (optionalWarehouse.isEmpty()) return new Response("empty",false);
            Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
            if (optionalCurrency.isEmpty()) return new Response("empty",false);
            Input input = new Input();
            input.setFactureNumber(inputDto.getFactureNumber());
            input.setDate(inputDto.getDate());
            input.setCode(inputDto.getCode());
            input.setCurrency(optionalCurrency.get());
            input.setSupplier(optionalSupplier.get());
            input.setWarehouse(optionalWarehouse.get());
            inputRepository.save(input);
        }
        return new Response("added all",true);
    }

    public Response addOne(InputDto inputDto) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        if (optionalSupplier.isEmpty()) return new Response("empty",false);
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) return new Response("empty",false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()) return new Response("empty",false);
        Input input = new Input();
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setDate(inputDto.getDate());
        input.setCode(inputDto.getCode());
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        input.setWarehouse(optionalWarehouse.get());
        inputRepository.save(input);
        return new Response("added one",true);
    }

    public Response getAll() {
        List<Input> inputList = inputRepository.findAll();
        if (inputList.isEmpty()) return new Response("empty",false);
        return new Response("success",true,inputList);
    }

    public Response getOne(Integer id) {
        Optional<Input> inputOptional = inputRepository.findById(id);
        if (inputOptional.isEmpty()) return new Response("empty",false);
        return new Response("success",true,inputOptional.get());
    }

    public Response editOne(Integer id, InputDto inputDto) {
        Optional<Currency> currencyOptional = currencyRepository.findById(inputDto.getCurrencyId());
        if (currencyOptional.isEmpty()) return new Response("empty",false);
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(inputDto.getWarehouseId());
        if (warehouseOptional.isEmpty()) return new Response("emtpy",false);
        Optional<Supplier> supplierOptional = supplierRepository.findById(inputDto.getSupplierId());
        if (supplierOptional.isEmpty()) return new Response("empty",false);
        Optional<Input> inputOptional = inputRepository.findById(id);
        if (inputOptional.isEmpty()) return new Response("empty",false);
        Input input = inputOptional.get();
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setDate(inputDto.getDate());
        input.setCode(inputDto.getCode());
        input.setWarehouse(warehouseOptional.get());
        input.setCurrency(currencyOptional.get());
        input.setSupplier(supplierOptional.get());
        inputRepository.save(input);
        return new Response("edited",true);
    }

    public Response deleteAll() {
        inputRepository.deleteAll();
        return new Response("delted all",true);
    }

    public Response deleteOne(Integer id) {
        inputRepository.deleteById(id);
        return new Response("delted one",true);
    }
}
