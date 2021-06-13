package com.example.apptransactions.transaction.domain;

import java.math.BigDecimal;

public enum SignOperation {
    POSITIVE{
        @Override
        BigDecimal calcvalue(BigDecimal valuePositive) {
            return valuePositive;
        }
    }, NEGATIVE {
        @Override
        BigDecimal calcvalue(BigDecimal valueNegative) {
            return valueNegative.negate();
        }
    };

    abstract BigDecimal calcvalue(BigDecimal value);
}
