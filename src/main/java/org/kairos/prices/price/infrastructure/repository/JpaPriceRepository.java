package org.kairos.prices.price.infrastructure.repository;

import org.kairos.prices.price.infrastructure.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {
    Optional<PriceEntity> findTopByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(LocalDateTime applicationDate, LocalDateTime applicationDate1, Long productId, Long brandId);
}
