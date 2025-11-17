package com.ecommerceapp.E_commerceApp.services;


import com.ecommerceapp.E_commerceApp.model.Product;
import com.ecommerceapp.E_commerceApp.repositary.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;


@Service
public class ProductServices {
    @Autowired
    private ProductRepo repo;


    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile image) throws IOException {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        product.setImageData(image.getBytes());

        return repo.save(product);
    }

    public void deleteProductById(int productId) {
        if (!repo.existsById(productId)) {
            throw new RuntimeException("Product not found with id: " + productId);
        }
        repo.deleteById(productId);
    }

    public Product updateProduct(int productId, MultipartFile imageFile, Product product) {
        Product product1 = repo.findById(productId).orElse(null);
        if (product1 == null) {
            throw new RuntimeException("Product not found with id: " + productId);
        } else {
            try {

                if (imageFile != null && !imageFile.isEmpty()) {
                    product1.setImageName(imageFile.getOriginalFilename());
                    product1.setImageType(imageFile.getContentType());
                    product1.setImageData(imageFile.getBytes());
                }


                product1.setName(product.getName());
                product1.setDescription(product.getDescription());
                product1.setCategory(product.getCategory());
                product1.setBrand(product.getBrand());
                product1.setPrice(product.getPrice());
                product1.setQuantity(product.getQuantity());

                product1.setReleaseDate(product.getReleaseDate());
                product1.setAvailability(product.getQuantity() > 0);

                return repo.save(product1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    public List<Product> searchProductByQuery(String query) {
        return repo.getProductByQuery(query);
    }
}
