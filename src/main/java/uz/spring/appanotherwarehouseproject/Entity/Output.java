package uz.spring.appanotherwarehouseproject.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Currency currency;

    @Column(nullable = false)
    private String factureNumber;

    @Column(nullable = false)
    private String code;

    @ManyToOne
    private Client client;
}
