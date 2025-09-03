package az.restopia.payment.domain.mapper;

import az.restopia.payment.domain.entity.Payment;
import az.restopia.payment.domain.request.PaymentRequest;
import az.restopia.payment.domain.response.PaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    Payment toPaymentEntity(PaymentRequest request);

    @Mapping(target = "orderId", source = "order.id")
    PaymentResponse mapToResponse(Payment payment);
}

