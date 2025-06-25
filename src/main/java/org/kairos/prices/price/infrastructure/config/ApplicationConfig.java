package org.kairos.prices.price.infrastructure.config;

import org.kairos.prices.price.application.usecase.GetPriceUseCaseImpl;
import org.kairos.prices.price.domain.port.in.GetPriceUseCase;
import org.kairos.prices.price.domain.port.out.PriceRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public GetPriceUseCase getPriceUseCase(PriceRepositoryPort priceRepositoryPort) {
        return new GetPriceUseCaseImpl(priceRepositoryPort);
    }
}

