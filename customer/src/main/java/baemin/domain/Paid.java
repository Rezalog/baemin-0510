package baemin.domain;

import baemin.infra.AbstractEvent;
import java.util.*;
import lombok.Data;

@Data
public class Paid extends AbstractEvent {

    private Long id;
    private String orderId;
    private String status;
}
