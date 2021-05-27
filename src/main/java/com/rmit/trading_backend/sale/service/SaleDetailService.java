package com.rmit.trading_backend.sale.service;

import com.rmit.trading_backend.product.model.Product;
import com.rmit.trading_backend.product.repository.ProductRepository;
import com.rmit.trading_backend.sale.model.SaleDetail;
import com.rmit.trading_backend.sale.model.SaleInvoice;
import com.rmit.trading_backend.sale.repository.SaleDetailRepository;
import com.rmit.trading_backend.sale.repository.SaleInvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class SaleDetailService {

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    @Autowired
    private SaleInvoiceRepository saleInvoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    // ADD DETAIL OF ONE INVOICE
    public void addSaleDetail(SaleDetail saleDetail) {

        Optional<SaleInvoice> checkSaleInvoice = saleInvoiceRepository.findById(saleDetail.getSaleInvoice().getId());
        Optional<Product> checkProduct = productRepository.findById(saleDetail.getProduct().getId());

        if (checkSaleInvoice.isPresent() && checkProduct.isPresent()) {

            SaleInvoice _saleInvoice = checkSaleInvoice.get();
            Product _product = checkProduct.get();

            saleDetail.setSaleInvoice(_saleInvoice);
            saleDetail.setProduct(_product);
            saleDetail.setPrice(_product.getPrice());
            saleDetail.setTotalValue(_product.getPrice() * saleDetail.getQuantity());

            saleDetailRepository.save(saleDetail);
            System.out.println("Add detail successfully");
        }
        System.out.println("Sale invoice or product not found");
    }
}
