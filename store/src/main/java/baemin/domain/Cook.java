package baemin.domain;

import baemin.StoreApplication;
import baemin.domain.CookFinished;
import baemin.domain.CookStarted;
import baemin.domain.OrderRejected;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Cook_table")
@Data
public class Cook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String status;

    private String foodId;

    private String storeId;

    private String customerId;

    @ElementCollection
    private List<String> options;

    private String orderId;

    @PostPersist
    public void onPostPersist() {
        CookFinished cookFinished = new CookFinished(this);
        cookFinished.publishAfterCommit();

        CookStarted cookStarted = new CookStarted(this);
        cookStarted.publishAfterCommit();

        OrderRejected orderRejected = new OrderRejected(this);
        orderRejected.publishAfterCommit();
    }

    public static CookRepository repository() {
        CookRepository cookRepository = StoreApplication.applicationContext.getBean(
            CookRepository.class
        );
        return cookRepository;
    }

    public void accept(AcceptCommand acceptCommand) {
        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.publishAfterCommit();
    }

    public static void updateStatus(Paid paid) {
        
        
        repository().findById(Long.valueOf(paid.getOrderId())).ifPresent(cook->{
            
            cook.setOrderId(paid.getOrderId());
            cook.setOptions(paid.getOptions());
            cook.setStatus("cooked");

            repository().save(cook);

         });
        

    }

    public static void updateStatus(OrderCancelled orderCancelled) {
        
        repository().findById(orderCancelled.getId()).ifPresent(cook->{
            
            cook.setStatus("cancelled");
            repository().save(cook);

            
         });
       

    }
}
