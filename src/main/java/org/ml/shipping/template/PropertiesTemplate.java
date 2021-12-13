package org.ml.shipping.template;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesTemplate {

    @Value("${ml-shipping.deposit-limit}")
    private int depositLimit;

    public int getDepositLimit() {
        return depositLimit;
    }
}
