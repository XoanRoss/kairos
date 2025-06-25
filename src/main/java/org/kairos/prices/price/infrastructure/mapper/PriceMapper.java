package org.kairos.prices.price.infrastructure.mapper;

import org.kairos.prices.price.domain.model.Price;
import org.kairos.prices.price.infrastructure.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(target = "brandId", source = "brand.id")
    Price toDomain(PriceEntity priceEntity);

    List<Price> toDomainList(List<PriceEntity> prices);
}
