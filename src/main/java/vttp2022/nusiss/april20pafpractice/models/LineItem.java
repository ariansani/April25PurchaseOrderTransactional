package vttp2022.nusiss.april20pafpractice.models;

import javax.sound.sampled.Line;

import jakarta.json.JsonObject;

public class LineItem {

    private Integer itemId;
    private String description;
    private Integer quantity;
    private Double unitPrice;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public static LineItem create(JsonObject o) {
        final LineItem li = new LineItem();
        li.setDescription(o.getString("description"));
        li.setQuantity(o.getInt("quantity"));
        li.setUnitPrice((double)o.getJsonNumber("unitPrice").doubleValue());
        return li;
    }

}
