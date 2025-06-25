package org.kairos.prices.price.application.usecase;

import lombok.RequiredArgsConstructor;
import org.kairos.prices.exception.domain.exception.ResourceNotFoundException;
import org.kairos.prices.price.domain.model.Price;
import org.kairos.prices.price.domain.port.in.GetPriceUseCase;
import org.kairos.prices.price.domain.port.out.PriceRepositoryPort;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class GetPriceUseCaseImpl implements GetPriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    @Override
    public Price findApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return priceRepositoryPort.findApplicablePrice(applicationDate, productId, brandId).orElseThrow(() -> new ResourceNotFoundException("Price"));
    }
}
