package com.numble.performancereservation.payment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment saveIfNotFound(PaymentDto paymentDto) {
        return paymentRepository.findByCardNumber(paymentDto.getCardNumber())
            .orElse(save(paymentDto));
    }

    public Payment save(PaymentDto paymentDto) {
        Payment payment = Payment.builder()
            .cardNumber(paymentDto.getCardNumber())
            .cardExpiration(paymentDto.getCardExpiration())
            .cardCVV(paymentDto.getCardCVV())
            .paymentMethod(paymentDto.getPaymentMethod()).build();
        return paymentRepository.save(payment);
    }

    public void processPayment() {
        try {
            log.info("결제 진행");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
