package br.ada.customer.crud.usecases;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.Product;

public interface IOrderChangeItemUseCase {
    void amountChanging(Order order, Product product, Integer amount);
}
