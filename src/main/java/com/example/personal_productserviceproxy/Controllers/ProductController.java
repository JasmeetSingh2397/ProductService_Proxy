package com.example.personal_productserviceproxy.Controllers;

import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Exceptions.ProductNotFoundException;
import com.example.personal_productserviceproxy.Factories.ResponseFactory;
import com.example.personal_productserviceproxy.Models.Product;
import com.example.personal_productserviceproxy.Services.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProducts(@Nullable @RequestHeader("AUTH-TOKEN") String token,
                                                           @Nullable @RequestHeader("USER_ID") Long userId) {


        List<Product> products= productService.getAllProducts();
        return ResponseFactory.getResponseEntityForGetAllProducts(products, token,userId);

    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable("id") Long ProductId) throws ProductNotFoundException {


        Product product= productService.getSingleProduct(ProductId);
        return ResponseFactory.getResponseEntityForGetSingleProduct(product);


    }



    @PostMapping()
    public ResponseEntity<ProductDto> addNewProduct(@RequestBody ProductDto productDto){

        Product newProduct= productService.addNewProduct(Product.mapProductDtoToProduct(productDto));
        return ResponseFactory.getResponseEntityForAddNewProduct(newProduct);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> patchProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto) throws ProductNotFoundException {
        Product product = Product.mapProductDtoToProduct(productDto);
        Product updatedProduct = productService.updateProduct(productId, product);
        return ResponseFactory.getResponseEntityForPutProduct(updatedProduct);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> putProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto) throws ProductNotFoundException {
        Product product = Product.mapProductDtoToProduct(productDto);
        Product replacedProduct= productService.replaceProduct(productId, product);

        return ResponseFactory.getResponseEntityForPatchProduct(replacedProduct);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {

        Product product= productService.deleteProduct(productId);
        return ResponseFactory.getResponseEntityForDeleteProduct(product);

    }

}
