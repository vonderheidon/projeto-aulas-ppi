package br.com.catolicapb.mapper;

import br.com.catolicapb.domain.Scheduling;
import br.com.catolicapb.dto.ScheduleRequestDTO;
import br.com.catolicapb.dto.SchedulingDetailResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ScheduleMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "medicalCare", ignore = true)
    Scheduling dtoToEntity(ScheduleRequestDTO requestDTO);

    SchedulingDetailResponseDTO entityToDetailDto(Scheduling scheduling);
}
