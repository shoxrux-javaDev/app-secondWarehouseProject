package uz.spring.appanotherwarehouseproject.Service;

import org.springframework.stereotype.Service;
import uz.spring.appanotherwarehouseproject.Dto.OutputDto;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Entity.Client;
import uz.spring.appanotherwarehouseproject.Entity.Currency;
import uz.spring.appanotherwarehouseproject.Entity.Output;
import uz.spring.appanotherwarehouseproject.Entity.Warehouse;
import uz.spring.appanotherwarehouseproject.Repository.ClientRepository;
import uz.spring.appanotherwarehouseproject.Repository.CurrencyRepository;
import uz.spring.appanotherwarehouseproject.Repository.OutputRepository;
import uz.spring.appanotherwarehouseproject.Repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OutputService {
    final OutputRepository outputRepository;
    final WarehouseRepository warehouseRepository;
    final CurrencyRepository currencyRepository;
    final ClientRepository clientRepository;

    public OutputService(OutputRepository outputRepository, WarehouseRepository warehouseRepository,
                         CurrencyRepository currencyRepository, ClientRepository clientRepository) {
        this.outputRepository = outputRepository;
        this.warehouseRepository = warehouseRepository;
        this.currencyRepository = currencyRepository;
        this.clientRepository = clientRepository;
    }

    public Response addAll(List<OutputDto> outputDtoList) {
        for (OutputDto outputDto : outputDtoList) {
            Optional<Client> clientOptional = clientRepository.findById(outputDto.getClientId());
            if (clientOptional.isEmpty()) return new Response("empty",false);
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
            if (optionalWarehouse.isEmpty()) return new Response("empty",false);
            Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
            if (optionalCurrency.isEmpty()) return new Response("empty",false);
            Output output = new Output();
            output.setCode(outputDto.getCode());
            output.setDate(outputDto.getDate());
            output.setClient(clientOptional.get());
            output.setCurrency(optionalCurrency.get());
            output.setFactureNumber(outputDto.getFactureNumber());
            output.setWarehouse(optionalWarehouse.get());
            outputRepository.save(output);
        }
        return new Response("added all",true);
    }

    public Response addOne(OutputDto outputDto) {
        Optional<Client> clientOptional = clientRepository.findById(outputDto.getClientId());
        if (clientOptional.isEmpty()) return new Response("empty",false);
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) return new Response("empty",false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()) return new Response("empty",false);
        Output output = new Output();
        output.setCode(outputDto.getCode());
        output.setDate(outputDto.getDate());
        output.setClient(clientOptional.get());
        output.setCurrency(optionalCurrency.get());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setWarehouse(optionalWarehouse.get());
        outputRepository.save(output);
        return new Response("added all",true);
    }

    public Response getAll() {
        List<Output> outputList = outputRepository.findAll();
        if (outputList.isEmpty()) return new Response("empty",false);
        return new Response("success",true,outputList);
    }

    public Response getOne(Integer id) {
        Optional<Output> outputOptional = outputRepository.findById(id);
        if (outputOptional.isEmpty()) return new Response("empty",false);
        return new Response("success",true,outputOptional.get());
    }

    public Response editOne(Integer id, OutputDto outputDto) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        if (optionalCurrency.isEmpty()) return new Response("empty",false);
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) return new Response("empty",false);
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());
        if (optionalClient.isEmpty()) return new Response("empty",false);
        Optional<Output> outputOptional = outputRepository.findById(id);
        if (outputOptional.isEmpty()) return new Response("empty",false);
        Output output = outputOptional.get();
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setDate(outputDto.getDate());
        output.setWarehouse(optionalWarehouse.get());
        output.setCurrency(optionalCurrency.get());
        output.setClient(optionalClient.get());
        output.setCode(outputDto.getCode());
        outputRepository.save(output);
        return new Response("edited",true);
    }

    public Response deleteAll() {
        outputRepository.deleteAll();
        return new Response("deleted all",false);
    }

    public Response deleteOne(Integer id) {
        outputRepository.deleteById(id);
        return new Response("deleted",true);
    }
}
