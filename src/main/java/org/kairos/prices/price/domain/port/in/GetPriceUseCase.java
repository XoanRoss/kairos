package org.kairos.prices.price.domain.port.in;

import org.kairos.prices.price.domain.model.Price;

import java.time.LocalDateTime;

public interface GetPriceUseCase {
    Price findApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId);
}
