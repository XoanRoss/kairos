package org.kairos.prices.price.domain.port.out;

import org.kairos.prices.price.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepositoryPort {
    Optional<Price> findApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
