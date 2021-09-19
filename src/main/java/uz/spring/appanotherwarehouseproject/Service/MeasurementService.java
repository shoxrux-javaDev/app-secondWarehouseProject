package uz.spring.appanotherwarehouseproject.Service;

import org.springframework.stereotype.Service;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Entity.Measurement;
import uz.spring.appanotherwarehouseproject.Repository.MeasurementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {
    final MeasurementRepository measurementRepository;

    public MeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public Response addAll(List<Measurement> measurementList) {
        List<Measurement> list = measurementRepository.saveAll(measurementList);
        if (list.isEmpty()) return new Response("empty",false);
        return new Response("added all",true);
    }

    public Response addOne(Measurement measurement) {
        measurementRepository.save(measurement);
        return new Response("added",true);
    }

    public Response getAll() {
        List<Measurement> measurementList = measurementRepository.findAll();
        if (measurementList.isEmpty()) return new Response("empty",false);
        return new Response("success",true,measurementList);
    }

    public Response getOne(Integer id) {
        Optional<Measurement> measurementOptional = measurementRepository.findById(id);
        if (measurementOptional.isEmpty()) return new Response("empty",false);
        return new Response("success",true);
    }

    public Response editOne(Integer id, Measurement measurement) {
        Optional<Measurement> measurementOptional = measurementRepository.findById(id);
        if (measurementOptional.isEmpty()) return new Response("empty",false);
        Measurement measurement1 = measurementOptional.get();
        measurement1.setName(measurement.getName());
        measurement1.setActive(measurement.isActive());
        measurementRepository.save(measurement1);
        return new Response("edited",true);
    }

    public Response deleteAll() {
        measurementRepository.deleteAll();
        return new Response("deleted all",true);
    }

    public Response deleteOne(Integer id) {
        measurementRepository.deleteById(id);
        return new Response("deleted",true);
    }
}
