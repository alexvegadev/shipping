package org.ml.shipping.controllers;

import io.swagger.annotations.*;
import org.ml.shipping.entity.ProductStock;
import org.ml.shipping.exception.DepositLimitException;
import org.ml.shipping.exception.ProductMaxTypesReachException;
import org.ml.shipping.exception.ProductNotFoundException;
import org.ml.shipping.exception.ProductNotValidException;
import org.ml.shipping.response.ProductStockResponse;
import org.ml.shipping.service.ProductStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/** STATIC IMPORTS **/
import static org.ml.shipping.shared.RestConstants.DOC_DEPOSIT_PARAM;
import static org.ml.shipping.shared.RestConstants.DOC_LOCATION_PARAM;
import static org.ml.shipping.shared.RestConstants.DOC_PRODUCT_STOCK_PARAM;
import static org.ml.shipping.shared.RestConstants.DOC_POST_PRODUCT_OP;
import static org.ml.shipping.shared.RestConstants.DOC_GET_PRODUCT_OP;
import static org.ml.shipping.shared.RestConstants.DOC_PRODUCT_ID_PARAM;
import static org.ml.shipping.shared.RestConstants.DOC_GET_FIND_PRODUCT_OP;
import static org.ml.shipping.shared.RestConstants.DOC_PUT_PRODUCT_OP;

@RestController
@CrossOrigin
@RequestMapping("products")
@Api(value = "productstock", tags = {"ProductStockController"})
public class ProductStockController {

    @Autowired
    private ProductStockService productStockService;

    @ApiOperation(value = DOC_POST_PRODUCT_OP, response = ProductStock.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Nos devuelve el producto guardado en la base de datos."),
            @ApiResponse(code = 400, message = "Patrón erróneo del depósito."),
            @ApiResponse(code = 404, message = "El producto no pudo ser encontrado o no existe.")
    })
    @PostMapping
    public ResponseEntity<ProductStockResponse> addProduct(@ApiParam(value = DOC_PRODUCT_STOCK_PARAM, required = true)
                                                               @Valid @RequestBody ProductStock productStock) throws ProductNotFoundException, ProductMaxTypesReachException, DepositLimitException, ProductNotValidException {
        return productStockService.addProduct(productStock);
    }

    @ApiOperation(value = DOC_PUT_PRODUCT_OP, response = ProductStock.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Nos devuelve el producto retirado con la cantidad restante."),
            @ApiResponse(code = 404, message = "El producto no pudo ser encontrado o no existe.")
    })
    @PutMapping
    public ResponseEntity<ProductStockResponse> drawOutProduct(@ApiParam(value = DOC_PRODUCT_STOCK_PARAM, required = true)
                                                           @Valid @RequestBody ProductStock productStock) {
        return productStockService.drawOutProduct(productStock);
    }

    @ApiOperation(value = DOC_GET_PRODUCT_OP, response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Nos devuelve un listado de productos en el depósito y ubicación especificados.")
    })
    @GetMapping
    public ResponseEntity<List<ProductStock>> getProductsList(@ApiParam(value = DOC_DEPOSIT_PARAM, required = true) @RequestParam String deposit, @ApiParam(value = DOC_LOCATION_PARAM, required = true) @RequestParam String location) {
        return ResponseEntity.ok(productStockService.getProductsList(deposit, location));
    }

    @ApiOperation(value = DOC_GET_FIND_PRODUCT_OP, response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Nos devuelve un listado de productos en los depósitos")
    })
    @GetMapping("find")
    public ResponseEntity<List<ProductStock>> findProduct(@ApiParam(value = DOC_DEPOSIT_PARAM, required = true) @RequestParam String deposit, @ApiParam(value = DOC_PRODUCT_ID_PARAM, required = true) @RequestParam String productId) {
        return ResponseEntity.ok(productStockService.findProducts(deposit, productId));
    }

}
