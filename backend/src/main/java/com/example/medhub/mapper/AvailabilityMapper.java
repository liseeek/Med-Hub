package com.example.medhub.mapper;

import com.example.medhub.dto.AvailabilityDto;
import com.example.medhub.entity.AvailabilityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AvailabilityMapper {
    AvailabilityMapper AVAILABILITY_MAPPER = Mappers.getMapper(AvailabilityMapper.class);

    @Mapping(source = "doctorId.doctorId", target = "doctorId")
    AvailabilityDto entityToDto(AvailabilityEntity entity);
}
