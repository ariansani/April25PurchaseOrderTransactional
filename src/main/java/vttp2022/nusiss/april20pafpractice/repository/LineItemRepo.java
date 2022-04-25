package vttp2022.nusiss.april20pafpractice.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vttp2022.nusiss.april20pafpractice.models.LineItem;

@Repository
public class LineItemRepo {
    
    @Autowired
    private JdbcTemplate template;

    public int[] addLineItem(Integer orderId, Collection<LineItem> lineItems){
        
        List<Object[]> params = lineItems
        .stream()
        .map(v-> {
            Object[] row = new Object[4];
            row[0] = orderId;
            row[1] = v.getUnitPrice();
            row[2] = v.getQuantity();
            row[3] = v.getDescription();
            return row;
        })
        .toList(); 
        
        return template.batchUpdate(Queries.SQL_INSERT_LINE_ITEM, params);

    }

    
}
