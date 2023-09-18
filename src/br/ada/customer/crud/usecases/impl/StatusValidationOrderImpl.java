package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.IValidationStatusOrder;

public class StatusValidationOrderImpl implements IValidationStatusOrder {
    @Override
    public void statusValidation(Order order, OrderStatus status) {
        OrderStatus currentStatus = order.getStatus();

        if (order.getStatus() != currentStatus) {
            throw new IllegalStateException("Erro! O Status " + currentStatus + " do pedido não permite essa operação!");
        }
    }

    @Override
    public void paymentValidation(Order order) {
        if (order.getStatus() != OrderStatus.PENDING_PAYMENT) {
            throw new IllegalStateException("Estamos quase lá! Efetue o pagamento para concluir a compra!");
        }
    }
}
