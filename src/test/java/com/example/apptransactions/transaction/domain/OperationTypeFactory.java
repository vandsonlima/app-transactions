package com.example.apptransactions.transaction.domain;

public class OperationTypeFactory {

    public OperationType compraAVista(){
        return new OperationType(1L, "Compra A vista", SignOperation.NEGATIVE);
    }
    public OperationType compraParcelada(){
        return new OperationType(2L, "Compra Parcelada", SignOperation.NEGATIVE);
    }
    public OperationType saque() {
        return new OperationType(3L, "Saque", SignOperation.NEGATIVE);
    }
    public OperationType pagamento(){
        return new OperationType(4L, "Pagamento", SignOperation.POSITIVE);
    }

    public OperationType invalid(){
        return new OperationType(5L, "invalid", SignOperation.NEGATIVE);
    }
}
