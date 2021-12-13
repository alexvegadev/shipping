package org.ml.shipping.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "ItemDTO", description = "Representación de un producto de la API de ML.")
public class ItemDTO implements Serializable {
    private String id;
    private String category_id;
    private int available_quantity;
    private ShippingDTO shipping;
}
