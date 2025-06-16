package org.kairos.prices.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.kairos.prices.domain.model.Price;
import org.kairos.prices.domain.port.in.GetPriceUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceController {

    private final GetPriceUseCase getPriceUseCase;

    @GetMapping
    public ResponseEntity<Price> getPrice2(@RequestParam LocalDateTime applicationDate,
                                           @RequestParam Long brandId,
                                           @RequestParam Long productId) {
        return getPriceUseCase.findApplicablePrice(applicationDate, productId, brandId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
