package br.ada.customer.crud.usecases;

import br.ada.customer.crud.model.Order;

public interface IValidationPlaceOrder {
    void cardValidation(Order order);
    void validationTotal(Order order);
    void addItemValidation(Order order);

}
