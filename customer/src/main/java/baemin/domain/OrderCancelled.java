package baemin.domain;

import baemin.infra.AbstractEvent;
import java.util.*;
import lombok.Data;

@Data
public class OrderCancelled extends AbstractEvent {

    private Long id;
    private String foodId;
    private String customerId;
    private List<String> options;
    private String address;
}
