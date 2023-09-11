package com.utn.persistenciatp1.entities;

import com.utn.persistenciatp1.enumeraciones.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Usuario extends BaseEntidad {

    private String nombre;
    private String password;
    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    public void agregarPedido(Pedido p){
        pedidos.add(p);
    }

    public void mostrarUsuario(){
        System.out.println("Nombre: " + nombre);
        System.out.println("Contraseña: " + password);
        System.out.println("Rol: " + rol);
        System.out.println("Pedidos: ");
        for (Pedido pedido : pedidos) {
            System.out.println("//////////////////////////////////");
            pedido.mostrarPedido();
        }
    }

}
