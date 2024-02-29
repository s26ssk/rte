package com.rte.model.service;

import com.rte.dto.request.ReqProductDTO;
import com.rte.model.entity.Category;
import com.rte.model.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService extends IGenericService<Product, Integer> {
    void createPro(ReqProductDTO productDTO, List<MultipartFile> multipartFiles);
    void updatePro(ReqProductDTO productDTO, List<MultipartFile> multipartFiles);
    List<Product> findByProductName(String productName);
    List<Product> findAllPage(int page, int size);
    int countAllProducts();
    Boolean updateStock(Integer productId, Integer quantity);
    List<Product> findAllPageSortedByExportPrice(int page, int size, String order);
}
