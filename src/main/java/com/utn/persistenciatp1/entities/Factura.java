package com.utn.persistenciatp1.entities;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Factura extends BaseEntidad {

    private String fecha;
    private int numero;
    private Double descuento;
    private FormaPago formaPago;
    private int total;

    private enum FormaPago{
        EFECTIVO, MERCADO_PAGO, DEBITO, CREDITO,
    }

}
