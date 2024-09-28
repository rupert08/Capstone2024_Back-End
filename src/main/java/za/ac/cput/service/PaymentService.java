package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Payment;
import za.ac.cput.repository.PaymentRepository;
import za.ac.cput.service.interfaces.IPaymentService;

@Service
public class PaymentService implements IPaymentService {

    PaymentRepository paymentRepository;

    @Autowired
    PaymentService(PaymentRepository paymentRepository) {this.paymentRepository = paymentRepository;}

    @Override
    public Payment create(Payment obj) {
        return paymentRepository.save(obj);
    }

    @Override
    public Payment read(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public Payment update(Payment obj) {
        return paymentRepository.save(obj);
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}
