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
public class ConfiguracionGeneral extends BaseEntidad {

    private int cantidadCocineros;
    private String emailEmpresa;
    private String tokenMercadoPago;
}
