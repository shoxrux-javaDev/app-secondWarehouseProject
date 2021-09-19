package uz.spring.appanotherwarehouseproject.Service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.spring.appanotherwarehouseproject.Dto.Response;
import uz.spring.appanotherwarehouseproject.Entity.Attachment;
import uz.spring.appanotherwarehouseproject.Entity.AttachmentContent;
import uz.spring.appanotherwarehouseproject.Repository.AttachmentContentRepository;
import uz.spring.appanotherwarehouseproject.Repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AttachmentService {
    final AttachmentRepository attachmentRepository;
    final AttachmentContentRepository attachmentContentRepository;

    //bu usulda faqat bitta file upload qilsak bo'ladi
    @SneakyThrows
    public Response addAllWithServlet(MultipartHttpServletRequest request) {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            Attachment attachment = new Attachment(
                    originalFilename, size, contentType);
            Attachment savedAttachment = attachmentRepository.save(attachment);

            AttachmentContent attachmentContent = new AttachmentContent(
                    file.getBytes(), savedAttachment
            );
            AttachmentContent attachmentContent1 = attachmentContentRepository.save(attachmentContent);
            return new Response("successfully upload to Db", true);
        }
        return new Response("Error", false);
    }

    //bu usul samarali usul
    public Response addAll(List<MultipartFile> files, List<MultipartFile> images) {
        files.stream().filter(Objects::nonNull).forEach(this::uploadToDb);
        images.stream().filter(Objects::nonNull).forEach(this::uploadToDb);
        return new Response("files upload to Db", true);
    }

    @SneakyThrows
    private void uploadToDb(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        Attachment attachment = new Attachment(originalFilename, file.getSize(), file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);
        AttachmentContent attachmentContent = new AttachmentContent(file.getBytes(), savedAttachment);
        attachmentContentRepository.save(attachmentContent);
    }

    @SneakyThrows
    public Response getAttachment(Integer id, HttpServletResponse response) {
        Optional<Attachment> attachmentOptional = attachmentRepository.findById(id);
        if (attachmentOptional.isPresent()) {
            Attachment attachment = attachmentOptional.get();
            Optional<AttachmentContent> contentOptional = attachmentContentRepository.findAllByAttachment_Id(id);
            if (contentOptional.isPresent()) {
                AttachmentContent attachmentContent = contentOptional.get();
                response.setContentType(attachment.getContentType());
                response.setHeader("content-type", "File:" + attachment.getName());
                FileCopyUtils.copy(attachmentContent.getAByte(), response.getOutputStream());
                response.getOutputStream().flush();
                response.getOutputStream().close();
                return new Response("download",true,response);
            }
        }
        return new Response("Error",false);
    }
}
