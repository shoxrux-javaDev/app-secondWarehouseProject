package uz.spring.appanotherwarehouseproject.Service;

import org.springframework.stereotype.Service;
import uz.spring.appanotherwarehouseproject.Config.AutoGenerationMethod;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Dto.UserDto;
import uz.spring.appanotherwarehouseproject.Entity.User;
import uz.spring.appanotherwarehouseproject.Entity.Warehouse;
import uz.spring.appanotherwarehouseproject.Repository.UserRepository;
import uz.spring.appanotherwarehouseproject.Repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    final UserRepository userRepository;
    final WarehouseRepository warehouseRepository;
    final AutoGenerationMethod auto;
    public UserService(UserRepository userRepository, WarehouseRepository warehouseRepository, AutoGenerationMethod auto) {
        this.userRepository = userRepository;
        this.warehouseRepository = warehouseRepository;
        this.auto = auto;
    }

    public Response addAll(List<UserDto> userDtoList) {
        for (UserDto userDto : userDtoList) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(userDto.getWarehouseId());
            if(optionalWarehouse.isEmpty()) return new Response("empty",false);
            User user = new User();
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setPassword(userDto.getPassword());
            user.setActive(userDto.isActive());
            user.setCode(auto.AutoGeneration());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setWarehouse(optionalWarehouse.get());
            userRepository.save(user);
        }
        return new Response("add all",true);
    }

    public Response addOne(UserDto userDto) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(userDto.getWarehouseId());
        if(optionalWarehouse.isEmpty()) return new Response("empty",false);
        User user = new User();
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(userDto.getPassword());
        user.setActive(userDto.isActive());
        user.setCode(auto.AutoGeneration());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setWarehouse(optionalWarehouse.get());
        userRepository.save(user);
        return new Response("added", true);
    }

    public Response getAll() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) return new Response("empty", false);
        return new Response("success", true, userList);
    }

    public Response getOne(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) return new Response("empty", false);
        return new Response("success", true, userOptional);
    }

    public Response editOne(Integer id, UserDto userDto) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(userDto.getWarehouseId());
        if (optionalWarehouse.isEmpty()) return new Response("empty", false);
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) return new Response("empty", false);
        User user = userOptional.get();
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setCode(auto.AutoGeneration());
        user.setActive(user.isActive());
        user.setPassword(user.getPassword());
        user.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(user);
        return new Response("updated", true);
    }

    public Response deleteAll() {
        userRepository.deleteAll();
        return new Response("deleted all", true);
    }

    public Response deleteOne(Integer id) {
        userRepository.deleteById(id);
        return new Response("deleted", true);
    }
}
