package com.Landajo.DesafioFinal.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @NotNull
    private String code;
    @Min(0)
    private int stock;
    private double price;

}
