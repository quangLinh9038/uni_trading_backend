package com.rmit.trading_backend.inventory.delivery.service;

import com.rmit.trading_backend.inventory.delivery.model.DeliveryDetail;
import com.rmit.trading_backend.inventory.delivery.repository.DeliveryDetailRepository;
import com.rmit.trading_backend.sale.model.SaleDetail;
import com.rmit.trading_backend.sale.repository.SaleDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class DeliveryDetailService {

    @Autowired
    private DeliveryDetailRepository deliveryDetailRepository;

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    // ADD DETAIL OF DELIVERY NOTE
    public void addDeliveryDetail(DeliveryDetail deliveryDetail) {

        Optional<SaleDetail> checkSaleDetail = saleDetailRepository.findById(deliveryDetail.getSaleDetail().getId());

        if (checkSaleDetail.isPresent()) {

            SaleDetail _saleDetail = checkSaleDetail.get();

            // get Product data from Sale Detail
            deliveryDetail.setProduct(_saleDetail.getProduct());

            deliveryDetailRepository.save(deliveryDetail);
            System.out.println("Success");
        } else {
            System.out.println("Order Details not found!");
        }
    }

}
