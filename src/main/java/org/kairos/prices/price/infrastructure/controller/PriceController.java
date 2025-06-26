package org.kairos.prices.price.infrastructure.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.kairos.prices.price.domain.model.Price;
import org.kairos.prices.price.domain.port.in.GetPriceUseCase;
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
    public ResponseEntity<Price> getPrice(@RequestParam @Schema(example = "2020-06-14T21:00:00") LocalDateTime applicationDate,
                                          @RequestParam @Schema(example = "1") Long brandId,
                                          @RequestParam @Schema(example = "35455") Long productId) {
        return ResponseEntity.ok(getPriceUseCase.findApplicablePrice(applicationDate, productId, brandId));
    }
}
