package com.utn.persistenciatp1;

import com.utn.persistenciatp1.entities.Producto;
import com.utn.persistenciatp1.entities.Rubro;
import com.utn.persistenciatp1.repositories.ProductoRepository;
import com.utn.persistenciatp1.repositories.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class PersistenciaTp1Application {

	@Autowired
	public RubroRepository rubroRepository;
	@Autowired
	public ProductoRepository productoRepository;

	public static void main(String[] args) {
		SpringApplication.run(PersistenciaTp1Application.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
			System.out.println("-----------------ESTOY FUNCIONANDO---------");

			// Creo los productos
			Producto muzza = Producto.builder()
					.tipo(Producto.Tipo.MANUFACTURADO)
					.tiempoEstimadoCocina(8)
					.denominacion("Pizza muzzarella")
					.precioVenta(2500d)
					.precioCompra(1200d)
					.stockActual(30)
					.stockMinimo(1)
					.unidadMedida("g")
					.foto("../../../../img/muzza.jpg")
					.receta("Se elabora con una base de masa fresca y crujiente, cubierta con una suave capa de salsa de tomate casera. Luego, añadimos generosas porciones de mozzarella y la horneamos, creando una combinación perfecta de sabores y texturas.")
					.build();

			Producto napo = Producto.builder()
					.tipo(Producto.Tipo.MANUFACTURADO)
					.tiempoEstimadoCocina(10)
					.denominacion("Pizza napolitana")
					.precioVenta(3000d)
					.precioCompra(1500d)
					.stockActual(20)
					.stockMinimo(1)
					.unidadMedida("g")
					.foto("../../../../img/napo.jpg")
					.receta("Preparada con una masa fina y esponjosa, se cubre con salsa de tomate, mozzarella de bufala, hojas de albahaca y rodajas de tomate fresco. Cocida en horno de leña, emerge con un sabor auténtico y simple.")
					.build();

			Producto jugo = Producto.builder()
					.tipo(Producto.Tipo.INSUMO)
					.tiempoEstimadoCocina(1)
					.denominacion("Jugo de naranja")
					.precioVenta(500d)
					.precioCompra(200d)
					.stockActual(15)
					.stockMinimo(1)
					.unidadMedida("l")
					.foto("../../../../img/jugo.jpg")
					.receta("Utilizamos naranjas maduras y jugosas, exprimidas cuidadosamente para obtener el jugo más puro y refrescante. Sin conservantes ni azúcares añadidos.")
					.build();

			// Creo los rubros
			Rubro bebida = Rubro.builder()
					.denominacion("Bebida")
					.build();

			Rubro comida = Rubro.builder()
					.denominacion("Comida")
					.build();

			// Asocio los productos al rubro
			bebida.agregarProducto(jugo);
			comida.agregarProducto(muzza);
			comida.agregarProducto(napo);

			// Guardo los objeto Rubro en la base de datos
			rubroRepository.save(bebida);
			rubroRepository.save(comida);

			// Guardo los producto en la base de datos
			productoRepository.save(muzza);
			productoRepository.save(napo);
			productoRepository.save(jugo);

			// Recupero los rubros desde la base de datos
			List<Producto> productos = productoRepository.findAll();
			if (!productos.isEmpty()){
				System.out.println("Información recuperada de los productos:");
				for (Producto producto : productos) {
					producto.mostrarProducto();
					System.out.println("----------------------------------------------");
				}
			}

			// Recupero los rubros desde la base de datos

			List<Rubro> rubros = rubroRepository.findAll();
			if (!rubros.isEmpty()){
				System.out.println("Información recuperada de los rubros:");
				for (Rubro rubro : rubros) {
					rubro.mostrarRubro();
					System.out.println("-----------------------------------");
				}
			}


		};

	}

}
