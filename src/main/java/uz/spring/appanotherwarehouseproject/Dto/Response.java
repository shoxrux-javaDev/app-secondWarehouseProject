package uz.spring.appanotherwarehouseproject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private String message;
    private boolean success;
    private Object object;
    private Map<String, Object> map;


    public Response(String message, boolean success, Map<String, Object> map) {
        this.message = message;
        this.success = success;
        this.map = map;
    }


    public Response(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public Response(String message, boolean success, Object object) {
        this.message = message;
        this.success = success;
        this.object = object;
    }
}
