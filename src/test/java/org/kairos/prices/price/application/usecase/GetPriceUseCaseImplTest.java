package org.kairos.prices.price.application.usecase;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kairos.prices.exception.domain.exception.ResourceNotFoundException;
import org.kairos.prices.price.domain.model.Price;
import org.kairos.prices.price.domain.port.in.GetPriceUseCase;
import org.kairos.prices.price.domain.port.out.PriceRepositoryPort;
import org.kairos.prices.price.infrastructure.repository.JpaPriceRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class GetPriceUseCaseImplTest {

    @Mock
    private PriceRepositoryPort priceRepositoryPort;

    @InjectMocks
    private GetPriceUseCaseImpl getPriceUseCase;

    @Test
    @DisplayName("Returns the price with the highest priority when multiple applicable prices exist")
    void returnsPriceWithHighestPriorityWhenMultipleApplicablePricesExist() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        Price price1 = Price.builder().priceList(1).brandId(brandId).productId(productId).price(BigDecimal.valueOf(25.50)).build();
        Price price2 = Price.builder().priceList(2).brandId(brandId).productId(productId).price(BigDecimal.valueOf(35.50)).build();

        when(priceRepositoryPort.findApplicablePrices(applicationDate, productId, brandId)).thenReturn(List.of(price1, price2));

        Price result = getPriceUseCase.findApplicablePrice(applicationDate, productId, brandId);

        assertEquals(price2, result);
    }

    @Test
    @DisplayName("Throws ResourceNotFoundException when no applicable prices exist")
    void throwsExceptionWhenNoApplicablePricesExist() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        when(priceRepositoryPort.findApplicablePrices(applicationDate, productId, brandId)).thenReturn(List.of());

        assertThrows(ResourceNotFoundException.class, () -> getPriceUseCase.findApplicablePrice(applicationDate, productId, brandId));
    }
}