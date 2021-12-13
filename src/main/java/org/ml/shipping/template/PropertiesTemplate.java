package org.ml.shipping.template;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class PropertiesTemplate {

    @Value("${ml-shipping.deposit-limit}")
    private int depositLimit;
}
