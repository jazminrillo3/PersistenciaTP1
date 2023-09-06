package com.utn.persistenciatp1;

import com.utn.persistenciatp1.entities.*;
import com.utn.persistenciatp1.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PersistenciaTp1Application {

	@Autowired
	public RubroRepository rubroRepository;
	@Autowired
	public ProductoRepository productoRepository;
	@Autowired
	public UsuarioRepository usuarioRepository;
	@Autowired
	public DomicilioRepository domicilioRepository;
	@Autowired
	public ClienteRepository clienteRepository;
	@Autowired
	public PedidoRepository pedidoRepository;
	@Autowired
	public DetallePedidoRepository detallePedidoRepository;
	@Autowired
	public FacturaRepository facturaRepository;

	public static void main(String[] args) {
		SpringApplication.run(PersistenciaTp1Application.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
			System.out.println("-----------------ESTOY FUNCIONANDO----------------");

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

			// Creo un usuario
			Usuario usuario = Usuario.builder()
					.nombre("jazRillo")
					.password("12345")
					.rol(Usuario.Rol.CLIENTE)
					.build();

			// Creo PEDIDOS, sus FACTURAS y sus DETALLES

			// Creo detalles de pedido
			DetallePedido detalle1pedido1 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(500d)
					.producto(jugo)
					.build();
			detalle1pedido1.calcularSubtotal();

			DetallePedido detalle2pedido1 = DetallePedido.builder()
					.cantidad(1)
					.producto(napo)
					.build();
			detalle2pedido1.calcularSubtotal();

			Pedido pedido1 = Pedido.builder()
					.fecha("06/09/2023")
					.estado(Pedido.Estado.ENTREGADO)
					.horaEstimadaEntrega("12:30")
					.tipoEnvio(Pedido.TipoEnvio.RETIRA)
					.build();

			// Asigno la factura y los detalles al pedido
			pedido1.agregarDetalle(detalle1pedido1);
			pedido1.agregarDetalle(detalle2pedido1);
			pedido1.calcularTotal();

			// Creo factura
			Factura facturaPedido1 = Factura.builder()
					.fecha(pedido1.getFecha())
					.numero(1)
					.descuento(0d)
					.formaPago(Factura.FormaPago.MERCADO_PAGO)
					.build();
			facturaPedido1.calcularTotal(pedido1);

			pedido1.setFactura(facturaPedido1);

			usuario.agregarPedido(pedido1);

			//CREO SEGUNDO PEDIDO

			DetallePedido detalle1pedido2 = DetallePedido.builder()
					.cantidad(2)
					.producto(muzza)
					.build();
			detalle1pedido2.calcularSubtotal();

			Pedido pedido2 = Pedido.builder()
					.fecha("08/09/2023")
					.estado(Pedido.Estado.INICIADO)
					.horaEstimadaEntrega("22:30")
					.tipoEnvio(Pedido.TipoEnvio.DELIVERY)
					.build();

			//Asocio el detalle al pedido
			pedido2.agregarDetalle(detalle1pedido2);
			pedido2.calcularTotal();

			// Creo factura
			Factura facturaPedido2 = Factura.builder()
					.fecha(pedido2.getFecha())
					.numero(1)
					.descuento(300d)
					.formaPago(Factura.FormaPago.DEBITO)
					.build();
			facturaPedido2.calcularTotal(pedido2);

			pedido2.setFactura(facturaPedido2);

			usuario.agregarPedido(pedido2);

			// Creo DOMICILIOS y se los asigno a un CLIENTE

			Cliente cliente = Cliente.builder()
					.nombre("Jazmín")
					.apellido("Rillo")
					.email("jazmin.rillo@gmail.com")
					.telefono("261 2545962")
					.build();

			cliente.agregarPedido(pedido1);
			cliente.agregarPedido(pedido2);

			Domicilio domicilio1 = Domicilio.builder()
					.calle("San Martín")
					.numero(1234)
					.localidad("Mendoza")
					.build();

			Domicilio domicilio2 = Domicilio.builder()
					.calle("9 de Julio")
					.numero(6433)
					.localidad("San Isidro")
					.build();

			domicilioRepository.save(domicilio1);
			domicilioRepository.save(domicilio2);

			domicilio1.setCliente(cliente);
			domicilio2.setCliente(cliente);

			domicilio1.agregarPedido(pedido1);
			domicilio2.agregarPedido(pedido2);

			usuarioRepository.save(usuario);

			pedidoRepository.save(pedido1);
			pedidoRepository.save(pedido2);

			clienteRepository.save(cliente);

			domicilioRepository.save(domicilio1);
			domicilioRepository.save(domicilio2);

			facturaRepository.save(facturaPedido1);
			facturaRepository.save(facturaPedido2);

			detallePedidoRepository.save(detalle1pedido1);
			detallePedidoRepository.save(detalle2pedido1);
			detallePedidoRepository.save(detalle1pedido2);

			recuperarDatos();

		};

	}

	private void recuperarDatos(){

		// Recupero los productos desde la base de datos
		List<Producto> productos = productoRepository.findAll();
		if (!productos.isEmpty()){
			System.out.println("---- Información recuperada de los productos: ----");
			for (Producto producto : productos) {
				producto.mostrarProducto();
				System.out.println("----------------------------------------------");
			}
		}

		// Recupero los rubros desde la base de datos

		List<Rubro> rubros = rubroRepository.findAll();
		if (!rubros.isEmpty()){
			System.out.println("---- Información recuperada de los rubros: ----");
			for (Rubro rubro : rubros) {
				rubro.mostrarRubro();
				System.out.println("-----------------------------------");
			}
		}

		// Recupero los usuarios desde la base de datos

		// A su vez recupero sus pedidos y sus respectivos detalles y facturas desde la base de datos

		List<Usuario> usuarios = usuarioRepository.findAll();
		if (!usuarios.isEmpty()){
			System.out.println("---- Información recuperada de los usuarios: ----");
			for (Usuario usuario : usuarios) {
				usuario.mostrarUsuario();
				System.out.println("-----------------------------------");
			}
		}

		// Recupero los clientes desde la base de datos

		// A su vez recupero sus pedidos y sus respectivos detalles y facturas desde la base de datos

		//Recupero domicilios
		List<Domicilio> domicilios = domicilioRepository.findAll();

		List<Cliente> clientes = clienteRepository.findAll();
		if (!clientes.isEmpty()){
			System.out.println("---- Información recuperada de los clientes: ----");
			for (Cliente cliente : clientes) {
				System.out.println("Domicilios: ");
				List<Domicilio> domiciliosCliente = encontrarDomicilios(domicilios, cliente);
				if (!domiciliosCliente.isEmpty()){
					for (Domicilio d:domiciliosCliente) {
						d.mostrarDomicilio();
					};
				}else{
					System.out.println("-");
				}

				cliente.mostrarCliente();
				System.out.println("-----------------------------------");
			}
		}


	}

	public List<Domicilio> encontrarDomicilios(List<Domicilio> domicilios, Cliente cliente){
		List<Domicilio> encontrados = new ArrayList<>();
		for (Domicilio domicilio : domicilios) {
			if (domicilio.getCliente().getId().equals(cliente.getId())) encontrados.add(domicilio);
		}
		return encontrados;
	}

}
