package uz.spring.appanotherwarehouseproject.Service;

import org.springframework.stereotype.Service;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Entity.Client;
import uz.spring.appanotherwarehouseproject.Repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Response addAll(List<Client> clientList) {
        List<Client> list = clientRepository.saveAll(clientList);
        if (list.isEmpty()) return new Response("empty",false);
        return new Response("added all",true);
    }

    public Response addOne(Client client) {
        clientRepository.save(client);
        return new Response("added One",true);
    }

    public Response getAll() {
        List<Client> clientList = clientRepository.findAll();
        if (clientList.isEmpty()) return new Response("empty",false);
        return new Response("success",true,clientList);
    }

    public Response getOne(Integer id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) return new Response("empty",false);
        return new Response("success",true,clientOptional.get());
    }

    public Response editOne(Integer id, Client client) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isEmpty()) return new Response("empty",false);
        Client client1 = clientOptional.get();
        client1.setName(client.getName());
        client1.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(client);
        return new Response("edited",true);
    }

    public Response deleteAll() {
        clientRepository.deleteAll();
        return new Response("delete all",true);
    }

    public Response deleteOne(Integer id) {
        clientRepository.deleteById(id);
        return new Response("delete one",true);
    }
}
