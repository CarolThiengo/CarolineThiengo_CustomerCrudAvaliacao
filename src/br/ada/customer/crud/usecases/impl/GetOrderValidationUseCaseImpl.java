package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.usecases.ICalculateTotalOrder;
import br.ada.customer.crud.usecases.IValidationPlaceOrder;

import java.math.BigDecimal;

public class GetOrderValidationUseCaseImpl implements IValidationPlaceOrder, ICalculateTotalOrder {
    @Override
    public void cardValidation(Order order) {
        if (order.getItems().isEmpty() || order.getItems() == null) {
            throw new IllegalStateException("Desculpe, nao encontramos produtos em seu carrinho!");
        }
    }

    @Override
    public void validationTotal(Order order) {
        if (orderTotalCalculation(order).compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Atenção! O valor total do pedido deve ser maior que zero!");
        }
    }

    @Override
    public BigDecimal orderTotalCalculation(Order order) {
        BigDecimal totalOrder = BigDecimal.ZERO;

        for (OrderItem item : order.getItems()) {
            totalOrder = totalOrder.add(item.getSaleValue());
        }

        return totalOrder;
    }

    @Override
    public void addItemValidation(Order order) {
        if (order.getItems().isEmpty()) {
            throw new IllegalStateException("Não desista, estamos quase lá! Adicione itens ao carrinho!");
        }
    }
}
