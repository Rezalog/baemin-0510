package baemin.domain;

import baemin.domain.*;
import baemin.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class Paid extends AbstractEvent {

    private Long id;
    private String orderId;
    private String status;
    private List<String> options;
}
