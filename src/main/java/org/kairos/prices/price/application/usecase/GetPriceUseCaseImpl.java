package org.kairos.prices.price.application.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kairos.prices.exception.domain.exception.ResourceNotFoundException;
import org.kairos.prices.price.domain.model.Price;
import org.kairos.prices.price.domain.port.in.GetPriceUseCase;
import org.kairos.prices.price.domain.port.out.PriceRepositoryPort;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class GetPriceUseCaseImpl implements GetPriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    @Override
    public Price findApplicablePrice(LocalDateTime applicationDate, Long productId, Long brandId) {
        log.info("Retrieving applicable price with highest priority");

        List<Price> applicablePrices = priceRepositoryPort.findApplicablePrices(applicationDate, productId, brandId);
        return applicablePrices.stream()
                .max(Comparator.comparingInt(Price::getPriceList))
                .orElseThrow(() -> new ResourceNotFoundException("No applicable price found for application date: " + applicationDate + ", product ID: " + productId + ", brand ID: " + brandId));
    }
}
