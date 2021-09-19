package uz.spring.appanotherwarehouseproject.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AttachmentContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private byte[] aByte;

    @ManyToOne
    private Attachment attachment;

    public AttachmentContent(byte[] aByte, Attachment attachment) {
        this.aByte = aByte;
        this.attachment = attachment;
    }
}
