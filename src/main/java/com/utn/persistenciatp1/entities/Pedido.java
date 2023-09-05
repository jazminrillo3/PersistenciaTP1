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
public class Pedido extends BaseEntidad{

    private String fecha;
    private Estado estado;
    private String horaEstimadaEntrega;
    private TipoEnvio tipoEnvio;
    private Double total;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name="factura_id")
    private Factura factura;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> detalles = new ArrayList<>();

    private enum Estado{
        INICIADO, PREPARACION, ENTREGADO;
    }

    private enum TipoEnvio{
        DELIVERY, RETIRA;
    }

}
