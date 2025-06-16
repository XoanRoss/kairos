package org.kairos.prices.application.usecase;

import lombok.RequiredArgsConstructor;
import org.kairos.prices.domain.model.Price;
import org.kairos.prices.domain.port.in.GetPriceUseCase;
import org.kairos.prices.domain.port.out.PriceRepositoryPort;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
public class GetPriceUseCaseImpl implements GetPriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    @Override
    public Optional<Price> findApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        return priceRepositoryPort.findApplicablePrice(applicationDate, productId, brandId);
    }
}
