package com.utn.persistenciatp1.entities;

import com.utn.persistenciatp1.enumeraciones.Estado;
import com.utn.persistenciatp1.enumeraciones.TipoEnvio;
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
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private String horaEstimadaEntrega;
    @Enumerated(EnumType.STRING)
    private TipoEnvio tipoEnvio;
    private Double total;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name="factura_id")
    private Factura factura;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> detalles = new ArrayList<>();

    public void agregarDetalle(DetallePedido d){
        detalles.add(d);
    }

    public void mostrarPedido(){
        System.out.println("Fecha: " + fecha);
        System.out.println("Estado: " + estado);
        System.out.println("Hora estimada de entrega: " + horaEstimadaEntrega);
        System.out.println("Envío: " + tipoEnvio);
        System.out.println("Total: $" + total);
        System.out.println("********************");
        System.out.println("Factura:");
        factura.mostrarFactura();
        System.out.println("********************");
        System.out.println("Detalles:");
        for (DetallePedido detalle : detalles) {
            detalle.mostrarDetalle();
        }
    }

    public void calcularTotal(){
        double suma = 0;
        for (DetallePedido detalle : detalles) {
            suma += detalle.getSubtotal();
        }
        total = suma;
    }

}
