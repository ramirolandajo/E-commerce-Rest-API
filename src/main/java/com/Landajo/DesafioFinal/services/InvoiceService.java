package com.Landajo.DesafioFinal.services;

import com.Landajo.DesafioFinal.exceptions.IdNotValidException;
import com.Landajo.DesafioFinal.exceptions.InvoiceExceptions.InvoiceEmptyException;
import com.Landajo.DesafioFinal.exceptions.InvoiceExceptions.InvoiceNotFoundException;
import com.Landajo.DesafioFinal.models.InvoiceDetailsModel;
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

    public InvoiceModel createInvoice(InvoiceModel newInvoice) throws InvoiceEmptyException {
        if (newInvoice == null){
            log.info("No se puede crear un comprobante vacio");
            throw new InvoiceEmptyException("No se puede crear un comprobante vacio");
        } else {
            int total = calcularTotal(newInvoice.getItems());
            newInvoice.setTotal(total);
            return this.invoiceRepository.save(newInvoice);
        }
    }

    public InvoiceModel findInvoiceById(Long id) throws IdNotValidException, InvoiceNotFoundException {
        if (id <= 0){
            log.info("El id ingresado no es valido");
            throw new IdNotValidException("El id ingresado no es valido");
        }
        Optional<InvoiceModel> invoiceOp = this.invoiceRepository.findById(id);
        if (invoiceOp.isEmpty()){
            log.info("Ningun invoice de id:" + id + " fue encontrado");
            throw new InvoiceNotFoundException("El comprobante que esta intentando acceder no fue encontrado");
        } else {
            return invoiceOp.get();
        }
    }
    public List<InvoiceModel> findAllInvoices(){
        return this.invoiceRepository.findAll();
    }

    public int calcularTotal(List<InvoiceDetailsModel> listaInvoiceDetails){
        int total = 0;
        for (InvoiceDetailsModel invoice: listaInvoiceDetails) {
            total += invoice.getAmount() * invoice.getPrice();
        }
        return total;
    }
}
