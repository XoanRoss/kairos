package org.kairos.prices.infrastructure.mapper;

import org.kairos.prices.domain.model.Price;
import org.kairos.prices.infrastructure.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(target = "brandId", source = "brand.id")
    Price toDomain(PriceEntity priceEntity);
}
