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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public int getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(int available_quantity) {
        this.available_quantity = available_quantity;
    }

    public ShippingDTO getShipping() {
        return shipping;
    }

    public void setShipping(ShippingDTO shipping) {
        this.shipping = shipping;
    }
}
