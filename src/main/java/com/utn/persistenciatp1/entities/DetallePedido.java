package com.utn.persistenciatp1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class DetallePedido extends BaseEntidad {

    private int cantidad;
    private Double subtotal;

    //Si le ponía CascadeType me daba error al asignar el producto al detalle
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public void mostrarDetalle(){
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Subtotal: $" + subtotal);
        System.out.println("Producto: " + producto.getDenominacion());
    }

    public void calcularSubtotal(){
        subtotal = cantidad * producto.getPrecioVenta();
    }

}
