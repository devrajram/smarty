package biharidelights.smarty.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InboundMsg {
    String key;
    List<Product> data;
    String zipCode;
    String profile;
}
