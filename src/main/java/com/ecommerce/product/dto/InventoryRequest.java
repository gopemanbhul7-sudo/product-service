package com.ecommerce.product.dto;

public class InventoryRequest { 
    private Long productId;
    private Integer quantityChange;

    public InventoryRequest(Long productId, Integer quantityChange) {
        this.productId = productId;
        this.quantityChange = quantityChange;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantityChange() {
        return quantityChange;
    }

    public void setQuantityChange(Integer quantityChange) {
        this.quantityChange = quantityChange;
    }

}
