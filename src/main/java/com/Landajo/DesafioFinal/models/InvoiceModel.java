package com.Landajo.DesafioFinal.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
public class InvoiceModel {
    @Id
    @Column(name = "invoice_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client;
    @NotNull
    @JsonIgnore
    private LocalDateTime created_at; // YYYY-MM-DD ...
    @NotNull
    @OneToMany(mappedBy = "invoice")
    private List<InvoiceDetailsModel> items;    //lista de los detalles del comprobante (cantidad de producto comprada, y cuales compro)
    @Min(0)
    @NotNull
    @JsonIgnore
    private double total;

}
