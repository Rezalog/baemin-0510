package baemin.domain;

import baemin.FrontApplication;
import baemin.domain.Paid;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Payment_table")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderId;

    private String status;

    private String foodId;

    @ElementCollection
    private List<String> options;

    private String address;

    @PostPersist
    public void onPostPersist() {
        Paid paid = new Paid(this);
        paid.publishAfterCommit();
    }

    public static PaymentRepository repository() {
        PaymentRepository paymentRepository = FrontApplication.applicationContext.getBean(
            PaymentRepository.class
        );
        return paymentRepository;
    }

    public static void pay(OrderPlaced orderPlaced) {
        
        repository().findById(orderPlaced.getId()).ifPresent(payment->{
            
            payment.setAddress(orderPlaced.getAddress());
            payment.setFoodId(orderPlaced.getFoodId());
            payment.setOrderId(String.valueOf(orderPlaced.getId()));
            payment.setOptions(orderPlaced.getOptions());
            payment.setStatus("paid");
            
            repository().save(payment);

            Paid paid = new Paid(payment);
            paid.publishAfterCommit();

         });
       

    }
}
