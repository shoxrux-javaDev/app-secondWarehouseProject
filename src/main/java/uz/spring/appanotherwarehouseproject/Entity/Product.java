package uz.spring.appanotherwarehouseproject.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Attachment photo;

    @Column(nullable = false)
    private String code;

    @ManyToOne
    private Measurement measurement;

    @Column(nullable = false)
    private boolean active;
}
