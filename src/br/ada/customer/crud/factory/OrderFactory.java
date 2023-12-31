package br.ada.customer.crud.factory;

import br.ada.customer.crud.integration.database.MemoryDatabase;
import br.ada.customer.crud.integration.email.OrderEmailNotifierImpl;
import br.ada.customer.crud.integration.memoryrepository.MergingEntityOrder;
import br.ada.customer.crud.integration.memoryrepository.OrderMemoryRepositoryImpl;
import br.ada.customer.crud.integration.sms.OrderSmsNotifierImpl;
import br.ada.customer.crud.integration.sms.SendSms;
import br.ada.customer.crud.usecases.*;
import br.ada.customer.crud.usecases.impl.*;
import br.ada.customer.crud.usecases.repository.OrderRepository;
import br.ada.customer.crud.integration.email.SendEmail;
import br.ada.customer.crud.usecases.impl.StatusValidationOrderImpl;
import br.ada.customer.crud.usecases.impl.GetOrderValidationUseCaseImpl;

public class OrderFactory {

    public static ICreateOrderUseCase createUseCase() {
        return new CreateOrderUseCaseImpl(
                createRepository(),
                CustomerFactory.createRepository()
        );
    }

    public static IOrderAddItemUseCase orderAddItemUseCase() {
        return new OrderAddItemUseCaseImpl(
                createRepository()
        );
    }

    public static IOrderChangeItemUseCase orderChangeItemUseCase() {
        return new OrderItemChangingUseCaseImpl(
                createRepository(),
                new StatusValidationOrderImpl());
    }

    public static IOrderRemoveItemUseCase orderRemoveItemUseCase() {
        return new OrderRemoveItemUseCaseImpl(
                createRepository(),
                new StatusValidationOrderImpl());
    }

    public static IPlaceOrderUseCase placeOrderUseCase() {
        return new OrderPlaceUseCaseImpl(
                createRepository(),
                new StatusValidationOrderImpl(),
                new GetOrderValidationUseCaseImpl(),
                new OrderEmailNotifierImpl(new SendEmail()),
                new OrderSmsNotifierImpl(new SendSms()));
    }

    public static IPayOrderUseCase payOrderUseCase() {
        return new OrderPaymentUseCaseImpl(
                createRepository(),
                new OrderEmailNotifierImpl(new SendEmail()),
                new OrderSmsNotifierImpl(new SendSms()),
                new StatusValidationOrderImpl());
    }

    public static IShippingOrderUseCase shippingUseCase() {
        return new OrderShippingUseCaseImpl(
                createRepository(),
                new OrderEmailNotifierImpl(new SendEmail()),
                new OrderSmsNotifierImpl(new SendSms()),
                new StatusValidationOrderImpl());
    }

    public static OrderRepository createRepository() {
        return new OrderMemoryRepositoryImpl(
                MemoryDatabase.getInstance(),
                new MergingEntityOrder(MemoryDatabase.getInstance())
        );
    }
}
