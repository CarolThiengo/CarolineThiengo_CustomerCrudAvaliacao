package br.ada.customer.crud.usecases;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.Product;

import java.math.BigDecimal;

public interface IOrderAddItemUseCase {
    OrderItem addItem(Order order, Product product, BigDecimal price, Integer amount);
}
