package vttp2022.nusiss.april20pafpractice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp2022.nusiss.april20pafpractice.exceptions.OrderTooLargeException;
import vttp2022.nusiss.april20pafpractice.models.LineItem;
import vttp2022.nusiss.april20pafpractice.models.PurchaseOrder;
import vttp2022.nusiss.april20pafpractice.repository.LineItemRepo;
import vttp2022.nusiss.april20pafpractice.repository.PurchaseOrderRepo;

@Service
public class PurchaseOrderService {
    
    @Autowired
    private PurchaseOrderRepo poRepo;

    @Autowired
    private LineItemRepo lineItemRepo;

    @Transactional(rollbackFor = OrderTooLargeException.class)
    public Integer createPurchaseOrder(final PurchaseOrder po) throws OrderTooLargeException{
        final Integer orderId = poRepo.insertPurchaseOrder(po);
        Double totalUnitPrice = 0d;

        for(LineItem li : po.getLineItemList()){
            totalUnitPrice = li.getQuantity() * li.getUnitPrice();
            if(totalUnitPrice > 200){
                OrderTooLargeException ex =
                new OrderTooLargeException("Order exceed SGD200: %,.2f".formatted(totalUnitPrice));
                ex.setPo(po);
                throw ex;
            }

        }

        lineItemRepo.addLineItem(orderId,po.getLineItemList());

        return orderId;

    }

}
