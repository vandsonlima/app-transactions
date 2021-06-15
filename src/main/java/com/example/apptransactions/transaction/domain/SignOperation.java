package com.example.apptransactions.transaction.domain;

import java.math.BigDecimal;

public enum SignOperation {
    POSITIVE{
        @Override
        public BigDecimal calcvalue(BigDecimal valuePositive) {
            return valuePositive;
        }
    }, NEGATIVE {
        @Override
        public BigDecimal calcvalue(BigDecimal valueNegative) {
            return valueNegative.negate();
        }
    };

    public abstract BigDecimal calcvalue(BigDecimal value);
}
