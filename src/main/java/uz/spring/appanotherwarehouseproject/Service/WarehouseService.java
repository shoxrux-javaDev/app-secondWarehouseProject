package uz.spring.appanotherwarehouseproject.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Entity.Warehouse;
import uz.spring.appanotherwarehouseproject.Repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }


    public Response addAll(List<Warehouse> warehouseList) {
        List<Warehouse> list = warehouseRepository.saveAll(warehouseList);
        if (list.isEmpty()) return new Response("empty",false);
        return new Response("add all", true);
    }

    public Response addOne(Warehouse warehouse) {
        warehouseRepository.save(warehouse);
        return new Response("added", true);
    }

    public Response getAll() {
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        if (warehouseList.isEmpty()) return new Response("empty", false);
        return new Response("success", true, warehouseList);
    }

    public Response getOne(Integer id) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(id);
        if (warehouseOptional.isEmpty()) return new Response("empty", false);
        return new Response("success", true);
    }

    public Response editOne(Integer id, Warehouse warehouse) {
        Optional<Warehouse> warehouseOptional = warehouseRepository.findById(id);
        if (warehouseOptional.isEmpty()) return new Response("empty", false);
        Warehouse newWarehouse = warehouseOptional.get();
        newWarehouse.setName(warehouse.getName());
        newWarehouse.setActive(warehouse.isActive());
        warehouseRepository.save(newWarehouse);
        return new Response("update", true);
    }

    public Response deleteAll() {
        warehouseRepository.deleteAll();
        return new Response("deleted all", true);
    }

    public Response deleteOne(Integer id) {
        warehouseRepository.deleteById(id);
        return new Response("delete", true);
    }

}
