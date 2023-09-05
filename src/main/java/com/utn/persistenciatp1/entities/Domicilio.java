package com.utn.persistenciatp1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Domicilio extends BaseEntidad {

    private String calle;
    private int numero;
    private String localidad;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="domicilio_id")
    private List<Pedido> pedidos = new ArrayList<>();

    //Para mi tiene mas sentido hacerlo desde la entidad Cliente, que tenga una lista de domicilios
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

}
