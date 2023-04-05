package com.Landajo.DesafioFinal.repositories;

import com.Landajo.DesafioFinal.models.ClientModel;
import com.Landajo.DesafioFinal.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    Optional<ProductModel> findByCode(String code);

}
