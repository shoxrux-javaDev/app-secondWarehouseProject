package uz.spring.appanotherwarehouseproject.Controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.spring.appanotherwarehouseproject.Dto.InputProductDto;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Entity.Measurement;
import uz.spring.appanotherwarehouseproject.Service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/measurement")
public class MeasurementController {
    final MeasurementService measurementService;

    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    @PreAuthorize(value = "hasAuthority('POST_ALL')")
    @PostMapping
    public HttpEntity<?> addAllCategory(@RequestBody List<Measurement> measurementList) {
        Response response = measurementService.addAll(measurementList);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('POST_ONE')")
    @PostMapping("/one")
    public HttpEntity<?> addOneCategory(@RequestBody Measurement measurement) {
        Response response = measurementService.addOne(measurement);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('GET_ALL')")
    @GetMapping
    public HttpEntity<?> getALlCategory() {
        Response response = measurementService.getAll();
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response);
    }

    @PreAuthorize(value = "hasAuthority('GET_ONE')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOneCategory(@PathVariable Integer id) {
        Response response = measurementService.getOne(id);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response);
    }

    @PreAuthorize(value = "hasAuthority('EDIT')")
    @PutMapping("{id}")
    public HttpEntity<?> editCategory(@PathVariable Integer id, @RequestBody Measurement measurement) {
        Response response = measurementService.editOne(id, measurement);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('DELETE_ALL')")
    @DeleteMapping
    public HttpEntity<?> deleteAllCategory() {
        Response response = measurementService.deleteAll();
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('DELETE_ONE')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOneCategory(@PathVariable Integer id) {
        Response response = measurementService.deleteOne(id);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }
}
