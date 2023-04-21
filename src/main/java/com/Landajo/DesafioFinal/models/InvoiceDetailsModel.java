package com.Landajo.DesafioFinal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @Column(name = "invoice_detail_id")
    private Long id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    @ToString.Exclude
    private InvoiceModel invoice;   //comprobante al que pertenece
    @Min(0)
    private int amount;  //cantidad del producto comprado
    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;

}
