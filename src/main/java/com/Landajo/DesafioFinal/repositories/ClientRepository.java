package com.Landajo.DesafioFinal.repositories;

import com.Landajo.DesafioFinal.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {
    Optional<ClientModel> findByDocnumber(String docnumber);
}
