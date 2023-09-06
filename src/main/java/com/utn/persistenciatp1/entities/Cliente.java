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
public class Cliente extends BaseEntidad {

    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    //Me daba error si ponía CascadeType.ALL
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    public void agregarPedido(Pedido p){
        pedidos.add(p);
    }

    public void mostrarCliente(){
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido);
        System.out.println("Teléfono: " + telefono);
        System.out.println("Email: " + email);
        System.out.println("Pedidos: ");
        for (Pedido pedido: pedidos) {
            System.out.println("//////////////////////////////////");
            pedido.mostrarPedido();
        }
    }

}
