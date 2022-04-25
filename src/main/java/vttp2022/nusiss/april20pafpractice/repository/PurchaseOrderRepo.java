package vttp2022.nusiss.april20pafpractice.repository;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import vttp2022.nusiss.april20pafpractice.models.PurchaseOrder;

@Repository
public class PurchaseOrderRepo {
    
    @Autowired
    private JdbcTemplate template;

    public Integer insertPurchaseOrder(final PurchaseOrder po){
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(Queries.SQL_INSERT_PURCHASE_ORDER,
            Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, po.getName());
            ps.setString(2, po.getEmail());
            return ps;
            
        },keyHolder);

        BigInteger bigint = (BigInteger) keyHolder.getKey();
        return bigint.intValue();
        
        
    }

}
