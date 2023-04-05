package com.Landajo.DesafioFinal.controllers;

import com.Landajo.DesafioFinal.exceptions.IdNotValidException;
import com.Landajo.DesafioFinal.exceptions.ProductExceptions.ProductAlreadyExistsException;
import com.Landajo.DesafioFinal.exceptions.ProductExceptions.ProductNotFoundException;
import com.Landajo.DesafioFinal.models.ProductModel;
import com.Landajo.DesafioFinal.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(path = "/")
    public ResponseEntity<ProductModel> createProduct(@RequestBody ProductModel newProduct) throws ProductAlreadyExistsException {
        return new ResponseEntity<>(this.productService.createProduct(newProduct), HttpStatus.OK);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<ProductModel> updateProduct(@RequestBody ProductModel product, @PathVariable Long id) throws IdNotValidException, ProductNotFoundException {
        return new ResponseEntity<>(this.productService.updateProduct(product, id), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductModel> findProductById(@PathVariable Long id) throws IdNotValidException, ProductNotFoundException {
        return new ResponseEntity<>(this.productService.findProductById(id), HttpStatus.OK);
    }
    @GetMapping(path = "/")
    public ResponseEntity<List<ProductModel>> findAllProducts(){
        return new ResponseEntity<>(this.productService.findAllProducts(), HttpStatus.OK);
    }
}
