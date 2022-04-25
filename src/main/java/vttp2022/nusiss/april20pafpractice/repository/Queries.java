package vttp2022.nusiss.april20pafpractice.repository;

public interface Queries {
    
    public static final String SQL_INSERT_PURCHASE_ORDER = "INSERT INTO po(name,email) values(?,?)";
    public static final String SQL_INSERT_LINE_ITEM = "insert into line_item(ord_id, quantity, unit_price, description) values (?,?,?,?)";
    
}
