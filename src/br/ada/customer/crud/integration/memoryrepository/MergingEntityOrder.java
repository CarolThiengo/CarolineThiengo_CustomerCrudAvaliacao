package br.ada.customer.crud.integration.memoryrepository;

import br.ada.customer.crud.integration.database.MemoryDatabase;
import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.Product;

import java.util.Objects;

public class MergingEntityOrder {

    private MemoryDatabase database;

    public MergingEntityOrder(MemoryDatabase database) {
        this.database = database;
    }

    public void merge(Order order) {
        Customer customerRegistered = customerfinder(order.getCustomer().getId());
        order.setCustomer(customerRegistered);
    }

    private Customer customerfinder(Long id) {
        Customer found = database.find(
                Customer.class,
                it -> Objects.equals(id, it.getId())
        ).stream().findFirst().orElse(null);
        return found;
    }

    public void merge(OrderItem item) {
        Product productRegistered = productfinder(item.getProduct().getId());
        item.setProduct(productRegistered);
    }

    private Product productfinder(Long id) {
        Product found = database.find(
                Product.class,
                it -> Objects.equals(id, it.getId())
        ).stream().findFirst().orElse(null);
        return found;
    }
}
