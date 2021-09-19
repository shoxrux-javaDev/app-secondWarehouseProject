package uz.spring.appanotherwarehouseproject.Controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.spring.appanotherwarehouseproject.Dto.CategoryDto;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Entity.Client;
import uz.spring.appanotherwarehouseproject.Service.ClientService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/client")
public class ClientController {
    final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PreAuthorize(value = "hasAuthority('POST_ALL')")
    @PostMapping
    public HttpEntity<?> addAllCategory(@RequestBody List<Client> clientList) {
        Response response = clientService.addAll(clientList);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('POST_ONE')")
    @PostMapping("/one")
    public HttpEntity<?> addOneCategory(@RequestBody Client client) {
        Response response = clientService.addOne(client);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('GET_ALL')")
    @GetMapping
    public HttpEntity<?> getALlCategory() {
        Response response = clientService.getAll();
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response);
    }

    @PreAuthorize(value = "hasAuthority('GET_ONE')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOneCategory(@PathVariable Integer id) {
        Response response = clientService.getOne(id);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response);
    }

    @PreAuthorize(value = "hasAuthority('EDIT')")
    @PutMapping("{id}")
    public HttpEntity<?> editCategory(@PathVariable Integer id, @RequestBody Client client) {
        Response response = clientService.editOne(id, client);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('DELETE_ALL')")
    @DeleteMapping
    public HttpEntity<?> deleteAllCategory() {
        Response response = clientService.deleteAll();
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('DELETE_ONE')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOneCategory(@PathVariable Integer id) {
        Response response = clientService.deleteOne(id);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }
}
