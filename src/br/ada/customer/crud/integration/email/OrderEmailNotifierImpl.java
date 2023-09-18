package br.ada.customer.crud.integration.email;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.INotifierOrderUseCase;

public class OrderEmailNotifierImpl implements INotifierOrderUseCase {

    private final SendEmail sendEmail;

    public OrderEmailNotifierImpl(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Override
    public void shippedOrder(Order order) {
        sendEmail.send("adamarketplace@ada.com", order.getCustomer().getEmail(), "Olá, vi aqui que a nota fiscal do seu produto ja foi emitida, e seu produto já está a caminho. Em breve ele chegará em sua casa!");
        System.out.println();
    }

    @Override
    public void updatePayment(Order order) {
        sendEmail.send("adamarketplace@ada.com", order.getCustomer().getEmail(), "Obá, Seu pagamento foi aprovado e em breve emitiremos a nota.");
        System.out.println();
    }

    @Override
    public void pendingPayment(Order order) {
        sendEmail.send("adamarketplace@ada.com", order.getCustomer().getEmail(), "Estamos quase lá. Efetue o pagamento para finalizar sua compra.");
        System.out.println();
    }
}
