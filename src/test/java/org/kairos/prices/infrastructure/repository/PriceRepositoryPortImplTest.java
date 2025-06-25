package org.kairos.prices.infrastructure.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kairos.prices.price.domain.model.Price;
import org.kairos.prices.price.infrastructure.entity.PriceEntity;
import org.kairos.prices.price.infrastructure.mapper.PriceMapper;
import org.kairos.prices.price.infrastructure.repository.JpaPriceRepository;
import org.kairos.prices.price.infrastructure.repository.PriceRepositoryPortImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PriceRepositoryPortImplTest {

    @Mock
    private JpaPriceRepository jpaPriceRepository;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PriceRepositoryPortImpl priceRepositoryPort;

    @Test
    @DisplayName("Returns price when valid parameters are provided")
    void findApplicablePriceReturnsPriceWhenMatchExists() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        PriceEntity priceEntity = new PriceEntity();
        Price expectedPrice = Price.builder().brandId(brandId).productId(productId).price(BigDecimal.valueOf(35.50)).build();

        when(jpaPriceRepository.findTopByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(applicationDate, applicationDate, productId, brandId)).thenReturn(Optional.of(priceEntity));
        when(priceMapper.toDomain(priceEntity)).thenReturn(expectedPrice);

        Optional<Price> result = priceRepositoryPort.findApplicablePrice(applicationDate, productId, brandId);

        assertTrue(result.isPresent());
        assertEquals(result.get(), expectedPrice);
    }

    @Test
    @DisplayName("Returns empty when no match exists")
    void findApplicablePriceReturnsEmptyWhenNoMatchExists() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        when(jpaPriceRepository.findTopByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(applicationDate, applicationDate, productId, brandId)).thenReturn(Optional.empty());

        Optional<Price> result = priceRepositoryPort.findApplicablePrice(applicationDate, productId, brandId);

        assertFalse(result.isPresent());
    }
}