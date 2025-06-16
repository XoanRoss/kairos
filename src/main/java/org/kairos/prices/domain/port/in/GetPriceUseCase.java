package org.kairos.prices.domain.port.in;

import org.kairos.prices.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GetPriceUseCase {
    Optional<Price> findApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
