package org.kairos.prices.price.infrastructure.repository;

import org.kairos.prices.price.infrastructure.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JpaPriceRepository extends JpaRepository<PriceEntity, Long> {
    List<PriceEntity> findByStartDateBeforeAndEndDateAfterAndProductIdAndBrandId(LocalDateTime startDate, LocalDateTime endDate, Long productId, Long brandId);
}
