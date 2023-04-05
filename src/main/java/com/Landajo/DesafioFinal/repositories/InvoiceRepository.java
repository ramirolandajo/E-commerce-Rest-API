package com.Landajo.DesafioFinal.repositories;

import com.Landajo.DesafioFinal.models.InvoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceModel, Long> {
}
