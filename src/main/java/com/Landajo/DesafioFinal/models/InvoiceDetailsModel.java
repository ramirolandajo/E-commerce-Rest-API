package com.Landajo.DesafioFinal.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "invoices_details")
@Data
@NoArgsConstructor
public class InvoiceDetailsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoice_detail_id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private InvoiceModel invoice_id;
    @Min(0)
    private int amount;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product_id;
    @Min(0)
    private double price;

}
