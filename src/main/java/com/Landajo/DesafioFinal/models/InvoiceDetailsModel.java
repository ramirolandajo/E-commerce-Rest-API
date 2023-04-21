package com.Landajo.DesafioFinal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private InvoiceModel invoice;
    @Min(0)
    private int amount;  //cantidad del producto comprado
    @NotNull
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;

}
