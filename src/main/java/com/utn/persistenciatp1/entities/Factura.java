package com.utn.persistenciatp1.entities;

import com.utn.persistenciatp1.enumeraciones.FormaPago;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;
    private Double total;

    public void calcularTotal(Pedido p){
        total = p.getTotal() - descuento;
    }

    public void mostrarFactura(){
        System.out.println("Fecha: " + fecha);
        System.out.println("Número: " + numero);
        System.out.println("Descuento: $" + descuento);
        System.out.println("Forma de pago: " + formaPago);
        System.out.println("Total: " + total);
    }

}
