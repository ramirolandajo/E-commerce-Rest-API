package com.Landajo.DesafioFinal.services;

import com.Landajo.DesafioFinal.exceptions.IdNotValidException;
import com.Landajo.DesafioFinal.models.ClientModel;
import com.Landajo.DesafioFinal.models.InvoiceModel;
import com.Landajo.DesafioFinal.repositories.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    public InvoiceModel createInvoice(InvoiceModel newInvoice){
        return this.invoiceRepository.save(newInvoice);
    }

    public InvoiceModel updateInvoice(InvoiceModel invoice, Long id) throws IdNotValidException {
        log.info("Id ingresado: " + id);
        if (id <= 0){
            log.info("El id ingresado no es valido.");
            throw new IdNotValidException("El id ingresado no es valido");
        }

        Optional<InvoiceModel> invoiceOp = this.invoiceRepository.findById(id);
        InvoiceModel invoiceDb = invoiceOp.get();

        invoiceDb.setClient_id(invoice.getClient_id());
        invoiceDb.setCreated_at(invoice.getCreated_at());
        invoiceDb.setTotal(invoice.getTotal());

        log.info("El invoice actualizado: " + invoiceDb);
        return this.invoiceRepository.save(invoiceDb);

    }

    public InvoiceModel findInvoiceById(Long id) throws IdNotValidException {
        if (id <= 0){
            log.info("El id ingresado no es valido");
            throw new IdNotValidException("El id ingresado no es valido");
        }
        Optional<InvoiceModel> invoice = this.invoiceRepository.findById(id);
        return invoice.get();
    }

    public List<InvoiceModel> findAllInvoices(){
        return this.invoiceRepository.findAll();
    }
}
