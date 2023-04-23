package com.Landajo.DesafioFinal.controllers;

import com.Landajo.DesafioFinal.exceptions.IdNotValidException;
import com.Landajo.DesafioFinal.exceptions.InvoiceExceptions.InvoiceNotFoundException;
import com.Landajo.DesafioFinal.models.InvoiceModel;
import com.Landajo.DesafioFinal.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/invoice")
public class InvoiceController {
    
    @Autowired
    InvoiceService invoiceService;

    @PostMapping(path = "/")
    public ResponseEntity<InvoiceModel> createInvoice(@RequestBody InvoiceModel newInvoice) throws Exception {
        return new ResponseEntity<>(this.invoiceService.createInvoice(newInvoice), HttpStatus.OK);
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity<InvoiceModel> findInvoiceById(@PathVariable Long id) throws IdNotValidException, InvoiceNotFoundException {
        return new ResponseEntity<>(this.invoiceService.findInvoiceById(id), HttpStatus.OK);
    }
    @GetMapping(path = "/")
    public ResponseEntity<List<InvoiceModel>> findAllInvoices(){
        return new ResponseEntity<>(this.invoiceService.findAllInvoices(), HttpStatus.OK);
    }
}
//EJEMPLO DE REQUEST DE POSTMAN PARA TESTEAR EL POST
//{
//    "client": {
//        "id": 1,
//        "name": "Ramiro",
//        "lastname": "Landajo",
//        "docnumber": "11111111"
//    },
//    "items": [
//        {
//            "amount": 20,
//            "product": {
//            "id": 1,
//            "description": "Control Remoto",
//            "code": "123",
//            "stock": 50,
//            "price": 100.00
//            }
//        },
//        {
//            "amount": 2,
//            "product": {
//            "id": 2,
//            "description": "PlayStation 5",
//            "code": "567",
//            "stock": 2,
//            "price": 2000.00
//            }
//        }
//    ]
//}