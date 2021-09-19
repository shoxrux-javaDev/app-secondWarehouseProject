package uz.spring.appanotherwarehouseproject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentContentDto {
    private byte[] bytes;
    private Integer attachmentId;
}
