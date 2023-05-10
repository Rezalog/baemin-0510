package baemin.domain;

import baemin.domain.*;
import baemin.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderAccepted extends AbstractEvent {

    private Long id;
    private String status;
    private String foodId;
    private String storeId;
    private String customerId;
    private List<String> options;
    private String orderId;

    public OrderAccepted(Cook aggregate) {
        super(aggregate);
    }

    public OrderAccepted() {
        super();
    }
}
