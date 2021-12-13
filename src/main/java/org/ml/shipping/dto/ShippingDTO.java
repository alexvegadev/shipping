package org.ml.shipping.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "ItemDTO", description = "Representación de el apartado \"shippinh\" de la API de ML.")
public class ShippingDTO implements Serializable {
    private String logistic_type;
}
