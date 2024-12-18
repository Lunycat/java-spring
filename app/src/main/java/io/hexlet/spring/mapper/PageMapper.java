package io.hexlet.spring.mapper;

import io.hexlet.spring.dto.PageCreateDTO;
import io.hexlet.spring.dto.PageDTO;
import io.hexlet.spring.dto.PageUpdateDTO;
import io.hexlet.spring.model.Page;
import org.mapstruct.*;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public abstract class PageMapper {
    public abstract Page map(PageCreateDTO dto);
    public abstract PageDTO map(Page model);
    public abstract void update(PageUpdateDTO dto, @MappingTarget Page model);
}
