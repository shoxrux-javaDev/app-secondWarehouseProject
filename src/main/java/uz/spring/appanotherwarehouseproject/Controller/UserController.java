package uz.spring.appanotherwarehouseproject.Controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.spring.appanotherwarehouseproject.Dto.ProductDto;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Dto.UserDto;
import uz.spring.appanotherwarehouseproject.Entity.User;
import uz.spring.appanotherwarehouseproject.Service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize(value = "hasAuthority('POST_ALL')")
    @PostMapping
    public HttpEntity<?> addAllCategory(@RequestBody List<UserDto> userDtoList) {
        Response response = userService.addAll(userDtoList);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('POST_ONE')")
    @PostMapping("/one")
    public HttpEntity<?> addOneCategory(@RequestBody UserDto userdto) {
        Response response = userService.addOne(userdto);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('GET_ALL')")
    @GetMapping
    public HttpEntity<?> getALlCategory() {
        Response response = userService.getAll();
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response);
    }

    @PreAuthorize(value = "hasAuthority('GET_ONE')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOneCategory(@PathVariable Integer id) {
        Response response = userService.getOne(id);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response);
    }

    @PreAuthorize(value = "hasAuthority('EDIT')")
    @PutMapping("{id}")
    public HttpEntity<?> editCategory(@PathVariable Integer id, @RequestBody UserDto userdto) {
        Response response = userService.editOne(id, userdto);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('DELETE_ALL')")
    @DeleteMapping
    public HttpEntity<?> deleteAllCategory() {
        Response response = userService.deleteAll();
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAuthority('DELETE_ONE')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteOneCategory(@PathVariable Integer id) {
        Response response = userService.deleteOne(id);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }
    //bu yerda exception hendler deb validation ni
    //nutnull ga message mizni userga qaytarishi uchun yozildi

   /* @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }*/
}
