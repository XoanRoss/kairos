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
import java.util.List;

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
    void findApplicablePriceReturnsPricesListWhenMatchesExist() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        PriceEntity priceEntity1 = new PriceEntity();
        PriceEntity priceEntity2 = new PriceEntity();
        Price price1 = Price.builder().brandId(brandId).productId(productId).price(BigDecimal.valueOf(35.50)).build();
        Price price2 = Price.builder().brandId(brandId).productId(productId).price(BigDecimal.valueOf(25.50)).build();

        when(jpaPriceRepository.findByStartDateBeforeAndEndDateAfterAndProductIdAndBrandId(applicationDate, applicationDate, productId, brandId)).thenReturn(List.of(priceEntity1, priceEntity2));
        when(priceMapper.toDomainList(List.of(priceEntity1, priceEntity2))).thenReturn(List.of(price1, price2));

        List<Price> result = priceRepositoryPort.findApplicablePrices(applicationDate, productId, brandId);

        assertEquals(2, result.size());
        assertTrue(result.contains(price1));
        assertTrue(result.contains(price2));
    }

    @Test
    @DisplayName("Returns empty when no match exists")
    void findApplicablePricesReturnsEmptyListWhenNoMatchesExist() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0);
        Long productId = 35455L;
        Long brandId = 1L;

        when(jpaPriceRepository.findByStartDateBeforeAndEndDateAfterAndProductIdAndBrandId(applicationDate, applicationDate, productId, brandId)).thenReturn(List.of());
        when(priceMapper.toDomainList(List.of())).thenReturn(List.of());

        List<Price> result = priceRepositoryPort.findApplicablePrices(applicationDate, productId, brandId);

        assertTrue(result.isEmpty());
    }
}