package com.Landajo.DesafioFinal.controllers;

import com.Landajo.DesafioFinal.exceptions.IdNotValidException;
import com.Landajo.DesafioFinal.models.InvoiceDetailsModel;
import com.Landajo.DesafioFinal.services.InvoiceDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/invoice_details")
public class InvoiceDetailsController {
    
    @Autowired
    InvoiceDetailsService invoiceDetailsService;

    @PostMapping(path = "/")
    public ResponseEntity<InvoiceDetailsModel> createInvoiceDetails(@RequestBody InvoiceDetailsModel newInvoiceDetails) {
        return new ResponseEntity<>(this.invoiceDetailsService.createInvoiceDetails(newInvoiceDetails), HttpStatus.OK);
    }
    @PutMapping(path = "/{id}")
    public ResponseEntity<InvoiceDetailsModel> updateInvoiceDetails (@RequestBody InvoiceDetailsModel client, @PathVariable Long id) throws IdNotValidException {
        return new ResponseEntity<>(this.invoiceDetailsService.updateInvoiceDetails(client, id), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<InvoiceDetailsModel> findInvoiceDetailsById(@PathVariable Long id) throws IdNotValidException {
        return new ResponseEntity<>(this.invoiceDetailsService.findInvoiceDetailsById(id), HttpStatus.OK);
    }
    @GetMapping(path = "/")
    public ResponseEntity<List<InvoiceDetailsModel>> findAllInvoiceDetails(){
        return new ResponseEntity<>(this.invoiceDetailsService.findAllInvoiceDetails(), HttpStatus.OK);
    }
}
