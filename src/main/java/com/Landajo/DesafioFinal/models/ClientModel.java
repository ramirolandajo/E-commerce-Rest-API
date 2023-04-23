package com.Landajo.DesafioFinal.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
public class ClientModel {
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String lastname;
    @NotNull
    private String docnumber;

}
