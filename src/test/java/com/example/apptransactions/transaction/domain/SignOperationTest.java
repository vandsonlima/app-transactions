package com.example.apptransactions.transaction.domain;

import com.example.apptransactions.GenericTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SignOperationTest extends GenericTest {


    @Test
    @DisplayName("a negative calc must have return a negative number")
    void calcvalueNegativeMustHaveNegativeNumber() {
        var signOperation = SignOperation.NEGATIVE;
        assertEquals(new BigDecimal("3").negate(), signOperation.calcvalue(new BigDecimal("3")));
    }

    @Test
    @DisplayName("a positive calc must have return a positive number")
    void calcValuePositiveMustHavePositiveNumber() {
        var signOperation = SignOperation.POSITIVE;
        assertEquals(new BigDecimal("3"), signOperation.calcvalue(new BigDecimal("3")));
    }
}