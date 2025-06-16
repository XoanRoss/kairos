package org.kairos.prices.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.kairos.prices.domain.model.Price;
import org.kairos.prices.domain.port.out.PriceRepositoryPort;
import org.kairos.prices.infrastructure.entity.PriceEntity;
import org.kairos.prices.infrastructure.mapper.PriceMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PriceRepositoryPortImpl implements PriceRepositoryPort {

    private final JpaPriceRepository jpaPriceRepository;
    private final PriceMapper priceMapper;

    @Override
    public Optional<Price> findApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        Optional<PriceEntity> price = jpaPriceRepository.findTopByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(applicationDate, applicationDate, productId, brandId);
        return price.map(priceMapper::toDomain);
    }
}
