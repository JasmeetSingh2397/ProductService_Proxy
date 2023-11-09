package com.example.personal_productserviceproxy.Controllers;

import com.example.personal_productserviceproxy.DTOs.ProductDto;
import com.example.personal_productserviceproxy.Exceptions.ProductNotFoundException;
import com.example.personal_productserviceproxy.Factories.HTTPStatusFactory;
import com.example.personal_productserviceproxy.Models.Product;
import com.example.personal_productserviceproxy.Services.IProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProducts() {

        List<Product> products= productService.getAllProducts();
        return HTTPStatusFactory.getResponseEntityForGetAllProducts(products);

    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable("id") Long ProductId) throws ProductNotFoundException {


        Product product= productService.getSingleProduct(ProductId);
        return HTTPStatusFactory.getResponseEntityForGetSingleProduct(product);


    }



    @PostMapping()
    public ResponseEntity<ProductDto> addNewProduct(@RequestBody ProductDto productDto){

        Product newProduct= productService.addNewProduct(Product.mapProductDtoToProduct(productDto));
        return HTTPStatusFactory.getResponseEntityForAddNewProduct(newProduct);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductDto> patchProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto) throws ProductNotFoundException {
        Product product = Product.mapProductDtoToProduct(productDto);
        Product updatedProduct = productService.updateProduct(productId, product);
        return HTTPStatusFactory.getResponseEntityForPutProduct(updatedProduct);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> putProduct(@PathVariable("id") Long productId, @RequestBody ProductDto productDto) throws ProductNotFoundException {
        Product product = Product.mapProductDtoToProduct(productDto);
        Product replacedProduct= productService.replaceProduct(productId, product);

        return HTTPStatusFactory.getResponseEntityForPatchProduct(replacedProduct);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {

        Product product= productService.deleteProduct(productId);
        return HTTPStatusFactory.getResponseEntityForDeleteProduct(product);

    }

}
