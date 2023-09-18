package br.ada.customer.crud.integration.sms;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.INotifierOrderUseCase;

public class OrderSmsNotifierImpl implements INotifierOrderUseCase {
    private final SendSms sendSms;

    public OrderSmsNotifierImpl(SendSms sendSms) {
        this.sendSms = sendSms;
    }

    @Override
    public void shippedOrder(Order order) {
        sendSms.send("998877665", order.getCustomer().getTelephone(), "Olá, vi aqui que a nota fiscal do seu produto ja foi emitida, e seu produto já está a caminho. Em breve ele chegará em sua casa!");
        System.out.println();
    }

    @Override
    public void updatePayment(Order order) {
        sendSms.send("998877665", order.getCustomer().getTelephone(), "Obá, Seu pagamento foi aprovado e em breve emitiremos a nota.");
        System.out.println();
    }

    @Override
    public void pendingPayment(Order order) {
        sendSms.send("998877665", order.getCustomer().getTelephone(), "Estamos quase lá. Efetue o pagamento para finalizar sua compra.");
        System.out.println();
    }

}
