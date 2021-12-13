package org.ml.shipping.service;

import org.ml.shipping.dao.ProductStockDAO;
import org.ml.shipping.dto.ItemDTO;
import org.ml.shipping.id.ProductId;
import org.ml.shipping.entity.ProductStock;
import org.ml.shipping.exception.DepositLimitException;
import org.ml.shipping.exception.ProductMaxTypesReachException;
import org.ml.shipping.exception.ProductNotFoundException;
import org.ml.shipping.exception.ProductNotValidException;
import org.ml.shipping.response.ProductStockResponse;
import org.ml.shipping.template.MercadoLibreItemsTemplate;
import org.ml.shipping.template.PropertiesTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductStockService {

    @Autowired
    private ProductStockDAO productRepository;

    @Autowired
    private MercadoLibreItemsTemplate mercadoLibreRest;

    @Autowired
    private PropertiesTemplate _propertiesTemplate;

    public ResponseEntity<ProductStockResponse> addProduct(ProductStock productStock) throws ProductNotFoundException, ProductMaxTypesReachException, DepositLimitException, ProductNotValidException {
        final int depositLimit = _propertiesTemplate.getDepositLimit();
        final String productsDepositedStr = productRepository.sumAmountInLocation(productStock.getLocation());
        final long productsDeposited = productsDepositedStr != null ? Long.parseLong(productsDepositedStr) : 0;
        final List<String> storedTypes = productRepository.findDistinctByProductIdAndLocationEquals(productStock.getLocation(), productStock.getDeposit());
        final long newLen = productsDeposited + productStock.getQuantity();
        if(storedTypes.size() >= 3 && !storedTypes.contains(productStock.getProductId())) {
            throw new ProductMaxTypesReachException();
        }
        else if(newLen > depositLimit){
            throw new DepositLimitException();
        }
        final ItemDTO item = getProductML(productStock.getProductId());
        if(!item.getShipping().getLogistic_type().equals("fulfillment")) {
            throw new ProductNotValidException(depositLimit);
        }
        final Optional<ProductStock> psBefore = productRepository.findById(new ProductId(productStock.getProductId(), productStock.getDeposit(), productStock.getLocation()));
        if(psBefore.isPresent()){
            final ProductStock psHolder = psBefore.get();
            final int oldLen = productStock.getQuantity();
            productStock.setQuantity(psHolder.getQuantity() + oldLen);
        }
        final ProductStock ps = productRepository.save(productStock);
        return ResponseEntity.ok(new ProductStockResponse(200, "Creado.", ps));
    }

    public ResponseEntity<ProductStockResponse> drawOutProduct(ProductStock productStock) {
        final Optional<ProductStock> ps = productRepository.findById(new ProductId(productStock.getProductId(), productStock.getDeposit(), productStock.getLocation()));
        if(ps.isPresent()) {
            final ProductStock prs = ps.get();
            if(prs.getQuantity() == productStock.getQuantity()) {
                productRepository.delete(prs);
                productStock.setQuantity(0);
                return ResponseEntity.ok(new ProductStockResponse(200, "Se retiraron todos los productos.", productStock));
            }
            else if(productStock.getQuantity() < prs.getQuantity()) {
                final int newLen = prs.getQuantity() - productStock.getQuantity();
                prs.setQuantity(newLen);
                final ProductStock newProduct = productRepository.save(prs);
                return ResponseEntity.ok(new ProductStockResponse(200, String.format("Se retiraron %d unidades del producto especificado", productStock.getQuantity()), newProduct));
            }
        }
        return ResponseEntity.notFound().build();
    }

    private ItemDTO getProductML(String itemId) throws ProductNotFoundException {
        ItemDTO item = null;
        try {
            item = mercadoLibreRest.getItemResponse(itemId);
        } catch (Exception e){
            throw new ProductNotFoundException();
        }
        return item;
    }

    public List<ProductStock> getProductsList(String deposit, String location) {
        return productRepository.findByDepositAndLocation(deposit, location);
    }

    public List<ProductStock> findProducts(String deposit, String productId) {
        return productRepository.findByDepositAndProductId(deposit, productId);
    }

}
