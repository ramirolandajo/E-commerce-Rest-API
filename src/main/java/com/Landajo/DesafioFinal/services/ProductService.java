package com.Landajo.DesafioFinal.services;

import com.Landajo.DesafioFinal.exceptions.ProductExceptions.ProductAlreadyExistsException;
import com.Landajo.DesafioFinal.exceptions.ProductExceptions.ProductNotFoundException;
import com.Landajo.DesafioFinal.exceptions.IdNotValidException;
import com.Landajo.DesafioFinal.models.InvoiceDetailsModel;
import com.Landajo.DesafioFinal.models.InvoiceModel;
import com.Landajo.DesafioFinal.models.ProductModel;
import com.Landajo.DesafioFinal.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductModel createProduct(ProductModel newProduct) throws ProductAlreadyExistsException {
        Optional<ProductModel> productOp = this.productRepository.findByCode(newProduct.getCode());

        if (productOp.isPresent()){
            log.info("El producto que esta intentando crear ya existe");
            throw new ProductAlreadyExistsException("El producto que esta intentado crear ya existe");
        } else {
            return this.productRepository.save(newProduct);
        }
    }

    public ProductModel updateProduct(ProductModel product, Long id) throws ProductNotFoundException, IdNotValidException {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.info("El id ingresado no es valido.");
            throw new IdNotValidException("El id ingresado no es valido");
        }

        Optional<ProductModel> productOp = this.productRepository.findById(id);

        if (productOp.isEmpty()){
            log.info("El producto que intenta actualizar no se encuentra en la base de datos");
            throw new ProductNotFoundException("El producto que intenta actualizar no se encuentra en la base de datos");
        } else {
            ProductModel productDb = productOp.get();
            productDb.setDescription(product.getDescription());
            productDb.setCode(product.getCode());
            productDb.setStock(product.getStock());
            productDb.setPrice(product.getPrice());

            log.info("El producto actualizado: " + productDb);
            return this.productRepository.save(productDb);
        }
    }

    public ProductModel findProductById(Long id) throws IdNotValidException, ProductNotFoundException {
        if (id <= 0){
            log.info("El id ingresado no es valido");
            throw new IdNotValidException("El id ingresado no es valido");
        }
        Optional<ProductModel> productOp = this.productRepository.findById(id);

        if (productOp.isEmpty()) {
            log.info("El producto que esta intentando acceder no esta registrado en la base de datos");
            throw new ProductNotFoundException("El producto que esta intentando acceder no se encuentra en la base de datos");
        } else {
            return productOp.get();
        }
    }

    public List<ProductModel> findAllProducts(){
        return this.productRepository.findAll();
    }

    public void updateStock(InvoiceModel invoiceModel) throws ProductNotFoundException, IdNotValidException {
        for (InvoiceDetailsModel item: invoiceModel.getItems()){
            ProductModel product = item.getProduct();
            product.setStock(product.getStock() - item.getAmount());
            updateProduct(product, product.getId());
        }
    }
}
