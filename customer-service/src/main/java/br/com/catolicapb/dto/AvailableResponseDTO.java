package br.com.catolicapb.dto;

import br.com.catolicapb.enums.ScheduleShift;

public record AvailableResponseDTO(
        ScheduleShift shift,
        int availableNumber) {
}
