package com.Landajo.DesafioFinal.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "invoices")
@Data
@NoArgsConstructor
public class InvoiceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientModel client_id;
    @NotNull
    private LocalDate created_at; // YYYY-MM-DD
    @Min(0)
    private double total;

}
