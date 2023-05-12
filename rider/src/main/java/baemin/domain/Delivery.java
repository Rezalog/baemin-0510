package baemin.domain;

import baemin.RiderApplication;
import baemin.domain.Delivered;
import baemin.domain.Picked;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;

    private String orderId;

    private String status;

    private String storeId;

    private String customerId;

    @PostPersist
    public void onPostPersist() {
        Picked picked = new Picked(this);
        picked.publishAfterCommit();

        Delivered delivered = new Delivered(this);
        delivered.publishAfterCommit();
    }

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = RiderApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public static void updateStatus(CookFinished cookFinished) {
       

        repository().findById(Long.valueOf(cookFinished.getOrderId())).ifPresent(delivery->{
            
            delivery.setStatus("cookFinished");
            repository().save(delivery);


         });
        

    }
}
