package com.rte.model.dao;

import com.rte.dto.request.ReqProductDTO;
import com.rte.model.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductDAO extends IGenericDAO<Product, Integer> {
    List<Product> findByProductName(String productName);
    List<Product> findAllPage(int page, int size);
    int countAllProducts();
    Boolean updateStock(Integer productId, Integer quantity);
    List<Product> findAllPageSortedByExportPrice(int page, int size, String order);
}
