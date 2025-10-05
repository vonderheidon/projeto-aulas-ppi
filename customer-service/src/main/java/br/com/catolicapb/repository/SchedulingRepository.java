package br.com.catolicapb.repository;

import br.com.catolicapb.domain.Scheduling;
import br.com.catolicapb.enums.ScheduleShift;
import br.com.catolicapb.enums.ScheduleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SchedulingRepository extends JpaRepository<Scheduling, Long> {

    @Query("""
            select count(s) from Scheduling s
            where s.dateSchedule = :dateSchedule
            and s.scheduleShift  = :shift
            and s.vetId = :vetId
            and s.scheduleStatus != :excludedStatus
            """)
    Long verifyScheduleAvailable(
            LocalDate dateSchedule,
            ScheduleShift shift,
            Long vetId,
            ScheduleStatus excludedStatus);
}
