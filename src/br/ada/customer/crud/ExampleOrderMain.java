package br.ada.customer.crud;

import br.ada.customer.crud.factory.CustomerFactory;
import br.ada.customer.crud.factory.OrderFactory;
import br.ada.customer.crud.factory.ProductFactory;
import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.Product;
import br.ada.customer.crud.usecases.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

public class ExampleOrderMain {

    public static void main(String[] args) {
        IProductUseCase productUseCase = ProductFactory.createUseCase();
        ICustomerUseCase customerUseCase = CustomerFactory.createUseCase();
        ICreateOrderUseCase orderUseCase = OrderFactory.createUseCase();
        IOrderAddItemUseCase orderAddItemUseCase = OrderFactory.orderAddItemUseCase();
        IOrderChangeItemUseCase orderChangeItemUseCase = OrderFactory.orderChangeItemUseCase();
        IOrderRemoveItemUseCase orderRemoveItemUseCase = OrderFactory.orderRemoveItemUseCase();
        IPlaceOrderUseCase orderPlaceUseCase = OrderFactory.placeOrderUseCase();
        IPayOrderUseCase orderPayUseCase = OrderFactory.payOrderUseCase();
        IShippingOrderUseCase orderShipping = OrderFactory.shippingUseCase();

        Customer customer = new Customer();
        customer.setName("Carol Thiengo");
        customer.setDocument("22222222222");
        customer.setTelephone(Collections.singletonList("77777777"));
        customer.setEmail(Collections.singletonList("raquel.thiengo@gmail.com"));
        customer.setBirthDate(LocalDate.ofEpochDay(11/24/1988));
        customerUseCase.create(customer);

        Product item1st = new Product();
        item1st.setDescription("Apple Iphone 13");
        productUseCase.create(item1st);

        Product item2nd = new Product();
        item2nd.setDescription("Acer Aspire V3-571");
        productUseCase.create(item2nd);

        Product item3rd = new Product();
        item3rd.setDescription("Xiaomi MI Watch Color Sport");
        productUseCase.create(item3rd);

        Order order = orderUseCase.create(customer);
        orderAddItemUseCase.addItem(order, item1st, BigDecimal.TEN, 1);
        orderAddItemUseCase.addItem(order, item2nd, BigDecimal.TEN, 2);
        orderAddItemUseCase.addItem(order, item3rd, BigDecimal.TEN, 3);
        orderChangeItemUseCase.amountChanging(order, item2nd, 5);
        orderChangeItemUseCase.amountChanging(order, item3rd, 7);
        orderRemoveItemUseCase.removeItem(order, item1st);
        orderPlaceUseCase.placeOrder(order);
        orderPayUseCase.pay(order);
        orderShipping.shippingOrder(order);
    }

}
