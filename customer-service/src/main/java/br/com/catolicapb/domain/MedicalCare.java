package br.com.catolicapb.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalCare extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime timeCare;

    @OneToOne
    @JoinColumn(name = "schedule_id")
    private Scheduling scheduling;

    private BigDecimal total;

    private BigDecimal procedureValue;

    @OneToMany(mappedBy = "medicalCare", cascade = CascadeType.ALL)
    private List<ItemProduct> products;
}
