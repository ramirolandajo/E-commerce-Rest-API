package com.Landajo.DesafioFinal.services;

import com.Landajo.DesafioFinal.exceptions.IdNotValidException;
import com.Landajo.DesafioFinal.exceptions.InvoiceExceptions.InvoiceEmptyException;
import com.Landajo.DesafioFinal.exceptions.InvoiceExceptions.InvoiceNotFoundException;
import com.Landajo.DesafioFinal.models.InvoiceDetailsModel;
import com.Landajo.DesafioFinal.models.InvoiceModel;
import com.Landajo.DesafioFinal.repositories.InvoiceDetailsRepository;
import com.Landajo.DesafioFinal.repositories.InvoiceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ClientService clientService;
    @Autowired
    InvoiceDetailsRepository invoiceDetailsRepository;
    @Autowired
    ProductService productService;

    public InvoiceModel createInvoice(InvoiceModel newInvoice) throws Exception {
        if (newInvoice == null){        // para chequear que el comprobante no este vacio
            log.info("No se puede crear un comprobante vacio");
            throw new InvoiceEmptyException("No se puede crear un comprobante vacio");
        }
        this.clientService.findClientById(newInvoice.getClient().getId());  //chequear que el cliente existe en la BD

        for (InvoiceDetailsModel item : newInvoice.getItems()){
            if (item.getProduct().getStock() - item.getAmount() < 0){
                log.info("Stock insuficiente del producto: " + item.getProduct());
                throw new Exception("Stock insuficiente del producto: " + item.getProduct());
            }
        }
        double total = calcularTotal(newInvoice.getItems()); //calculamos el total a abonar

        // seteamos los valores de fecha y total a abonar
        newInvoice.setCreated_at(LocalDateTime.now());
        newInvoice.setTotal(total);

        //guardamos el comprobante y obtenemos el id
        this.invoiceRepository.save(newInvoice);
        log.info("El comprobante guardado: " + newInvoice);

        //creamos una lista para guardar los detalles de cada producto del comprobante en el invoiceDetailsRepository
        List<InvoiceDetailsModel> listDetails = new ArrayList<>();
        for (InvoiceDetailsModel item : newInvoice.getItems()){
            item.setInvoice(newInvoice); // se le asigna el invoice para que el detalle sepa a que factura pertenece
            listDetails.add(this.invoiceDetailsRepository.save(item));
        }
        newInvoice.setItems(listDetails);
        log.info("INVOICE COMPLETE : " + newInvoice);

        //actualizamos el stock
        this.productService.updateStock(newInvoice);

        return newInvoice;
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

    public double calcularTotal(List<InvoiceDetailsModel> listaInvoiceDetails){
        double total = 0;
        for (InvoiceDetailsModel item: listaInvoiceDetails) {
            total += item.getAmount() * item.getProduct().getPrice();
        }
        return total;
    }
}
