package com.Landajo.DesafioFinal.repositories;

import com.Landajo.DesafioFinal.models.InvoiceDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetailsModel, Long> {
}
