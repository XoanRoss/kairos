package org.kairos.prices.price.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kairos.prices.price.domain.model.Price;
import org.kairos.prices.price.domain.port.out.PriceRepositoryPort;
import org.kairos.prices.price.infrastructure.entity.PriceEntity;
import org.kairos.prices.price.infrastructure.mapper.PriceMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class PriceRepositoryPortImpl implements PriceRepositoryPort {

    private final JpaPriceRepository jpaPriceRepository;
    private final PriceMapper priceMapper;

    @Override
    public List<Price> findApplicablePrices(LocalDateTime applicationDate, Long productId, Long brandId) {
        log.info("Retrieving applicable prices by application date: {}, product ID: {}, and brand ID: {}", applicationDate, productId, brandId);

        List<PriceEntity> prices = jpaPriceRepository.findByStartDateBeforeAndEndDateAfterAndProductIdAndBrandId(applicationDate, applicationDate, productId, brandId);
        return priceMapper.toDomainList(prices);
    }
}
