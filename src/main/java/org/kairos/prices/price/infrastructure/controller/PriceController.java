package org.kairos.prices.price.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import org.kairos.prices.exception.domain.exception.MissingParameterException;
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
    public ResponseEntity<Price> getPrice(@RequestParam(required = false) LocalDateTime applicationDate,
                                          @RequestParam(required = false) Long brandId,
                                          @RequestParam(required = false) Long productId) {
        if (applicationDate == null) throw new MissingParameterException("Application date");
        if (brandId == null) throw new MissingParameterException("Brand ID");
        if (productId == null) throw new MissingParameterException("Product ID");
        return ResponseEntity.ok(getPriceUseCase.findApplicablePrice(applicationDate, productId, brandId));
    }
}
