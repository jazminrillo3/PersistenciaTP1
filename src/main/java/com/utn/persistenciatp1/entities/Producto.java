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
public class Producto extends BaseEntidad{

    private Tipo tipo;
    private int tiempoEstimadoCocina;
    private String denominacion;
    private Double precioVenta;
    private Double precioCompra;
    private int stockActual;
    private int stockMinimo;
    private String unidadMedida;
    private String foto;
    private String receta;

    public enum Tipo{
        MANUFACTURADO, INSUMO;
    }

    public void mostrarProducto(){
        System.out.println("Tipo: " + tipo);
        System.out.println("Denominación: " + denominacion);
        System.out.println("Tiempo estimado: " + tiempoEstimadoCocina);
        System.out.println("Precio de venta: " + precioVenta);
        System.out.println("Precio de compra: " + precioCompra);
        System.out.println("Stock disponible: " + stockActual);
        System.out.println("Stock mínimo: " + stockMinimo);
        System.out.println("Unidad de medida: " + unidadMedida);
        System.out.println("Foto: " + foto);
        System.out.println("Receta: " + receta);
    }

}
