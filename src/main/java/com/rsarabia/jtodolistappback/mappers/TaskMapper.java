package com.rsarabia.jtodolistappback.mappers;

import com.rsarabia.jtodolistappback.dto.task.TaskResponseDto;
import com.rsarabia.jtodolistappback.dto.task.TaskToCreateDto;
import com.rsarabia.jtodolistappback.dto.task.TaskToPatchDto;
import com.rsarabia.jtodolistappback.entities.DbTask;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskResponseDto toDto(DbTask dbTask);

    List<TaskResponseDto> toResponseDtoList(List<DbTask> dbTasks);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void patchFromDto(TaskToPatchDto dto, @MappingTarget DbTask entity);

    void createFromDto(TaskToCreateDto dto, @MappingTarget DbTask entity);
}