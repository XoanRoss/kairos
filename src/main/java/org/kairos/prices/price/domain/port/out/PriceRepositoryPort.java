package org.kairos.prices.price.domain.port.out;

import org.kairos.prices.price.domain.model.Price;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepositoryPort {
    List<Price> findApplicablePrices(LocalDateTime applicationDate, Long productId, Long brandId);
}
