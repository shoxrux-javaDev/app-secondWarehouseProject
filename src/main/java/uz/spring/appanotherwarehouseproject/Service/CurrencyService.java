package uz.spring.appanotherwarehouseproject.Service;

import org.springframework.stereotype.Service;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Entity.Currency;
import uz.spring.appanotherwarehouseproject.Repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public Response addAll(List<Currency> currencyList) {
        List<Currency> list = currencyRepository.saveAll(currencyList);
        if (list.isEmpty()) return new Response("empty",false);
        return new Response("added all",true);
    }

    public Response addOne(Currency currency) {
        currencyRepository.save(currency);
        return new Response("added one",true);
    }

    public Response getAll() {
        List<Currency> currencyList = currencyRepository.findAll();
        if (currencyList.isEmpty()) return new Response("empty",false);
        return new Response("success",true,currencyList);
    }

    public Response getOne(Integer id) {
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        if (currencyOptional.isEmpty()) return new Response("empty",false);
        return new Response("success",true,currencyOptional.get());
    }

    public Response editOne(Integer id, Currency currency) {
        Optional<Currency> currencyOptional = currencyRepository.findById(id);
        if (currencyOptional.isEmpty()) return new Response("empty",false);
        Currency currency1 = currencyOptional.get();
        currency1.setName(currency.getName());
        currency1.setActive(currency.isActive());
        currencyRepository.save(currency1);
        return new Response("edited",true);
    }

    public Response deleteAll() {
        currencyRepository.deleteAll();
        return new Response("deleted all",true);
    }

    public Response deleteOne(Integer id) {
        currencyRepository.deleteById(id);
        return new Response("deleted one",true);
    }
}
