package az.neotech.neoeats.payment.domain.mapper;

import az.neotech.neoeats.order.domain.entity.Order;
import az.neotech.neoeats.payment.domain.entity.Payment;
import az.neotech.neoeats.payment.domain.request.PaymentRequest;
import az.neotech.neoeats.payment.domain.response.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment toPaymentEntity(PaymentRequest request, Order order);

    @Mapping(target = "orderId", source = "order.id")
    PaymentResponse mapToResponse(Payment payment);
}

