package vttp2022.nusiss.april20pafpractice.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class PurchaseOrder {

    private Integer orderId;
    private String name;
    private String email;
    private List<LineItem> lineItemList = new LinkedList<>();

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<LineItem> getLineItemList() {
        return lineItemList;
    }

    public void setLineItemList(List<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }

    public static PurchaseOrder create(String json) throws IOException {

        final PurchaseOrder po = new PurchaseOrder();

        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            
            po.setName(o.getString("name"));
            po.setEmail(o.getString("email"));
            List<LineItem> lineItemList = o.getJsonArray("lineItems")
                .stream()
                .map(li -> LineItem.create((JsonObject)li))
                .toList();
            po.setLineItemList(lineItemList);
       
        }

        return po;
    }

}
