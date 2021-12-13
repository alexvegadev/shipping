package org.ml.shipping.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.ml.shipping.id.ProductId;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

import static org.ml.shipping.shared.RestConstants.BAD_PATTERN_MESSAGE;

@Entity
@Table(name = "product_stock")
@IdClass(ProductId.class)
@ApiModel(value = "ProductStock", description = "Representación del depósito.")
public class ProductStock {

    @Id
    @Column(name="product_id")
    @ApiModelProperty(notes = "El ID del producto de la API de ML.")
    private String productId;

    @Id
    @Column(length=12)
    @Pattern(flags = Pattern.Flag.CASE_INSENSITIVE, regexp = "^([a-z]{2,}-(\\d{2,})-(\\d{2,})-(DE|IZ))$", message = BAD_PATTERN_MESSAGE)
    @ApiModelProperty(notes = "La dirección del depósito debe tener un patrón {Area}-{Pasillo}-{Fila}-{Cara} con 2 dígitos para cada parte.")
    private String deposit;

    @Column
    @ApiModelProperty(notes = "Cantidad de productos a almacenar.")
    private int quantity;

    @Id
    @Column(length=4)
    @ApiModelProperty(notes = "Ubicación del depósito")
    private String location;


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
