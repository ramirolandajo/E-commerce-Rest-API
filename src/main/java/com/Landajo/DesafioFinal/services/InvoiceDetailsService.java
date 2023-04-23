package com.Landajo.DesafioFinal.services;

import com.Landajo.DesafioFinal.exceptions.IdNotValidException;
import com.Landajo.DesafioFinal.models.InvoiceDetailsModel;
import com.Landajo.DesafioFinal.repositories.InvoiceDetailsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class InvoiceDetailsService {

    @Autowired
    InvoiceDetailsRepository invoiceDetailsRepository;

    public InvoiceDetailsModel findInvoiceDetailsById(Long id) throws Exception {
        if (id <= 0){
            log.info("El id ingresado no es valido");
            throw new IdNotValidException("El id ingresado no es valido");
        }
        Optional<InvoiceDetailsModel> invoiceDetailsOp = this.invoiceDetailsRepository.findById(id);

        if (invoiceDetailsOp.isEmpty()){
            log.info("Detalle de comprobante no se encuentra en la base de datos");
            throw new Exception("Detalle de comprobante no se encuentra en la base de datos");
        } else {
            return invoiceDetailsOp.get();
        }
    }

    public List<InvoiceDetailsModel> findAllInvoiceDetails(){
        return this.invoiceDetailsRepository.findAll();
    }
}
