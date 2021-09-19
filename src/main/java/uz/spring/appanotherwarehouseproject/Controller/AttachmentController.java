package uz.spring.appanotherwarehouseproject.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Entity.Attachment;
import uz.spring.appanotherwarehouseproject.Service.AttachmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(path = "/api/attachment")
@AllArgsConstructor
public class AttachmentController {
    final AttachmentService attachmentService;

    @PreAuthorize(value = "hasAuthority('POST_ALL')")
    @PostMapping
    public HttpEntity<?> addAllAttachment(@RequestParam List<MultipartFile> files, @RequestParam List<MultipartFile> images) {
        Response response = attachmentService.addAll(files, images);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response.getMessage());
    }

    @PreAuthorize(value = "hasAnyAuthority('GET_ALL','GET_ONE')")
    @GetMapping("/{id}")
    public HttpEntity<?> getAttachment(@PathVariable Integer id, HttpServletResponse resp) {
        Response response = attachmentService.getAttachment(id, resp);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.NOT_FOUND).body(response);
    }
}
