package br.ada.customer.crud.usecases;

import br.ada.customer.crud.model.Order;

public interface IShippingNotifierUseCase {
    void shipped(Order order);

}
