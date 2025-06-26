package org.kairos.prices.infrastructure.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kairos.prices.exception.domain.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class PriceControllerTest {

    private static final String TEST_1 = "Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (XXXX)";
    private static final String TEST_2 = "Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (XXXX)";
    private static final String TEST_3 = "Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (XXXX)";
    private static final String TEST_4 = "Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (XXXX)";
    private static final String TEST_5 = "Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (XXXX)";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName(TEST_1)
    void test1ReturnsPriceList1() throws Exception {
        String result = mockMvc.perform(get("/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        log.info(TEST_1);
        log.info("Test 1 - result: {}", result);
    }

    @Test
    @DisplayName(TEST_2)
    void test2ReturnsPriceList2() throws Exception {
        String result = mockMvc.perform(get("/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14T16:00:00"))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(25.45))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        log.info(TEST_2);
        log.info("Test 2 - result: {}", result);
    }

    @Test
    @DisplayName(TEST_3)
    void test3ReturnsPriceList1() throws Exception {
        String result = mockMvc.perform(get("/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14T21:00:00"))
                .andExpect(jsonPath("$.priceList").value(1))
                .andExpect(jsonPath("$.price").value(35.50))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        log.info(TEST_3);
        log.info("Test 3 - result: {}", result);
    }

    @Test
    @DisplayName(TEST_4)
    void test4ReturnsPriceList3() throws Exception {
        String result = mockMvc.perform(get("/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-15T10:00:00"))
                .andExpect(jsonPath("$.priceList").value(3))
                .andExpect(jsonPath("$.price").value(30.50))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        log.info(TEST_4);
        log.info("Test 4 - result: {}", result);
    }

    @Test
    @DisplayName(TEST_5)
    void test5ReturnsPriceList4() throws Exception {
        String result = mockMvc.perform(get("/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-16T21:00:00"))
                .andExpect(jsonPath("$.priceList").value(4))
                .andExpect(jsonPath("$.price").value(38.95))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        log.info(TEST_5);
        log.info("Test 5 - result: {}", result);
    }

    @Test
    @DisplayName("Throws MissingServletRequestParameterException when application date is missing")
    void throwsExceptionWhenApplicationDateIsMissing() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("productId", "35455")
                        .param("brandId", "1"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(MissingServletRequestParameterException.class, result.getResolvedException()))
                .andExpect(jsonPath("$.error").value("Parameter missing: applicationDate"))
                .andExpect(jsonPath("$.path").value("/prices"));
    }

    @Test
    @DisplayName("Throws MethodArgumentTypeMismatchException when application date is not valid")
    void throwsExceptionWhenApplicationDateIsNotValid() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .param("applicationDate", "a"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(MethodArgumentTypeMismatchException.class, result.getResolvedException()))
                .andExpect(jsonPath("$.error").value("Parameter not valid: applicationDate"))
                .andExpect(jsonPath("$.path").value("/prices"));
    }

    @Test
    @DisplayName("Throws MissingServletRequestParameterException when brand ID is missing")
    void throwsExceptionWhenBrandIdIsMissing() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("productId", "35455")
                        .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(MissingServletRequestParameterException.class, result.getResolvedException()))
                .andExpect(jsonPath("$.error").value("Parameter missing: brandId"))
                .andExpect(jsonPath("$.path").value("/prices"));
    }

    @Test
    @DisplayName("Throws MethodArgumentTypeMismatchException when brand ID is not valid")
    void throwsExceptionWhenBrandIdIsNotValid() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("productId", "35455")
                        .param("brandId", "a")
                        .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(MethodArgumentTypeMismatchException.class, result.getResolvedException()))
                .andExpect(jsonPath("$.error").value("Parameter not valid: brandId"))
                .andExpect(jsonPath("$.path").value("/prices"));
    }

    @Test
    @DisplayName("Throws MissingServletRequestParameterException when product ID is missing")
    void throwsExceptionWhenProductIdIsMissing() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(MissingServletRequestParameterException.class, result.getResolvedException()))
                .andExpect(jsonPath("$.error").value("Parameter missing: productId"))
                .andExpect(jsonPath("$.path").value("/prices"));
    }

    @Test
    @DisplayName("Throws MethodArgumentTypeMismatchException when product ID is not valid")
    void throwsExceptionWhenProductIdIsNotValid() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("productId", "a")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertInstanceOf(MethodArgumentTypeMismatchException.class, result.getResolvedException()))
                .andExpect(jsonPath("$.error").value("Parameter not valid: productId"))
                .andExpect(jsonPath("$.path").value("/prices"));
    }

    @Test
    @DisplayName("Returns 404 when no applicable price is found")
    void returnsNotFoundWhenNoApplicablePriceIsFound() throws Exception {
        mockMvc.perform(get("/prices")
                        .param("productId", "99999")
                        .param("brandId", "1")
                        .param("applicationDate", "2020-06-14T10:00:00"))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertInstanceOf(ResourceNotFoundException.class, result.getResolvedException()))
                .andExpect(result -> assertEquals("Resource not found: No applicable price found for application date: 2020-06-14T10:00, product ID: 99999, brand ID: 1", result.getResolvedException().getMessage()));
    }
}