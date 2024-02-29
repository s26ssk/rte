package com.rte.model.service;

import com.rte.dto.request.ReqProductDTO;
import com.rte.model.dao.ProductDAOImpl;
import com.rte.model.entity.Category;
import com.rte.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAOImpl productDAO;
    @Value("C:\\Users\\OWNER\\md4\\rte\\src\\main\\webapp\\uploads\\")
    private String uploadPath;

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Boolean create(Product product) {
        return productDAO.create(product);
    }

    @Override
    public Boolean update(Product product, Integer productId) {
        return productDAO.update(product, productId);
    }

    @Override
    public Product findId(Integer productId) {
        return productDAO.findId(productId);
    }

    @Override
    public void delete(Integer productId) {
        productDAO.delete(productId);
    }

    @Override
    public void createPro(ReqProductDTO productDTO, List<MultipartFile> multipartFiles) {
        Product product = mapReqProductDTOToProduct(productDTO);
        for (int i = 0; i < multipartFiles.size(); i++) {
            String fileName = multipartFiles.get(i).getOriginalFilename();

            if (i == 0) {
                product.setImage1(fileName);
            } else if (i == 1) {
                product.setImage2(fileName);
            }

            try {
                FileCopyUtils.copy(multipartFiles.get(i).getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        productDAO.create(product);
    }

    @Override
    public void updatePro(ReqProductDTO productDTO, List<MultipartFile> multipartFiles) {
        Product updatedProduct = mapReqProductDTOToProduct(productDTO);
        updatedProduct.setProductId(productDTO.getProductId());
        if (!Objects.equals(multipartFiles.get(0).getOriginalFilename(), "")) {
            for (int i = 0; i < multipartFiles.size(); i++) {
                String fileName = multipartFiles.get(i).getOriginalFilename();
                if (i == 0) {
                    updatedProduct.setImage1(fileName);
                } else if (i == 1) {
                    updatedProduct.setImage2(fileName);
                }

                try {
                    FileCopyUtils.copy(multipartFiles.get(i).getBytes(), new File(uploadPath + fileName));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            Product product = productDAO.findId(productDTO.getProductId());
            updatedProduct.setImage1(product.getImage1());
            updatedProduct.setImage2(product.getImage2());
        }
            update(updatedProduct, updatedProduct.getProductId());
    }

    @Override
    public List<Product> findByProductName(String productName) {
        return productDAO.findByProductName(productName);
    }

    @Override
    public List<Product> findAllPage(int page, int size) {
        return productDAO.findAllPage(page, size);
    }

    @Override
    public int countAllProducts() {
        return productDAO.countAllProducts();
    }

    @Override
    public Boolean updateStock(Integer productId, Integer quantity) {
        return productDAO.updateStock(productId, quantity);
    }

    @Override
    public List<Product> findAllPageSortedByExportPrice(int page, int size, String order) {
        // Có thể cần thêm kiểm tra đối với tham số 'order' để ngăn chặn SQL injection.
        return productDAO.findAllPageSortedByExportPrice(page, size, order);
    }
    public static Product mapReqProductDTOToProduct(ReqProductDTO productDTO) {
        Product product = new Product();

        product.setProductName(productDTO.getProductName());
        product.setImport_price(productDTO.getImport_price());
        product.setExport_price(productDTO.getExport_price());
        product.setDescription(productDTO.getDescription());
        product.setStock(productDTO.getStock());
        product.setStatus(productDTO.getStatus());

        if (productDTO.getCategory() != null) {
            Category category = new Category();
            category.setCategoryId(productDTO.getCategory().getCategoryId());
            category.setCategoryName(productDTO.getCategory().getCategoryName());
            category.setStatus(productDTO.getCategory().getStatus());
            product.setCategory(category);
        }
        return product;
    }
}
