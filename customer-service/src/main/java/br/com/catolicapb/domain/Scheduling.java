package br.com.catolicapb.domain;

import br.com.catolicapb.enums.ScheduleShift;
import br.com.catolicapb.enums.ScheduleStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Scheduling extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long vetId;

    private Long petId;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateSchedule;

    @Enumerated(EnumType.STRING)
    private ScheduleShift scheduleShift;

    @Enumerated(EnumType.STRING)
    private ScheduleStatus scheduleStatus;

    @OneToOne(mappedBy = "scheduling")
    private MedicalCare medicalCare;
}
