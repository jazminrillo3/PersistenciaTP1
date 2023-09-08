# Trabajo Práctico de Persistencia
Este repositorio contiene un proyecto de Java que demuestra conceptos de persistencia y relaciones en un contexto de pedidos de comida. 
A continuación se explica el código y su funcionamiento.

# Descripción del Proyecto
El proyecto está construido sobre el framework Spring Boot y utiliza un conjunto de entidades para representar productos, clientes, pedidos, facturas y más.

# Requisitos
Para ejecutar este proyecto, necesitas tener instalado Java y Gradle. Además, se supone yo poseo una base de datos configurada en application.properties antes de ejecutar la aplicación.

# Estructura de Paquetes
com.utn.persistenciatp1.entities: Contiene las clases que representan las entidades del sistema (ej. Producto, Rubro, Pedido, etc.).
com.utn.persistenciatp1.repositories: Contiene las interfaces y clases de repositorio que facilitan la interacción con la base de datos.
com.utn.persistenciatp1: Contiene la clase principal PersistenciaTp1Application que inicia la aplicación y contiene el código de inicialización.

# Persistencia y Relaciones
Se utilizan anotaciones como @OneToOne, @ManyToOne, @OneToMany para establecer relaciones entre las entidades.
Las relaciones entre entidades están configuradas para garantizar la integridad referencial en la base de datos.

# Inicialización de Datos
La clase PersistenciaTp1Application implementa CommandLineRunner y contiene un método init que se ejecuta al iniciar la aplicación.
En este método se crean instancias de productos, rubros, clientes, pedidos, facturas, detalles y usuario, y se establecen las relaciones entre ellos.
Luego se persisten en la base de datos usando los repositorios correspondientes.

# Recuperación de Datos
Después de inicializar los datos, se muestra un ejemplo de cómo recuperar información desde la base de datos.
Se recuperan productos, rubros, usuarios y clientes, junto con sus relaciones.

#Notas
Es importante asegurarse de tener una base de datos configurada y que las dependencias necesarias estén agregadas al proyecto (por ejemplo, yo usé una base de datos en H2.).
Este proyecto proporciona un ejemplo básico de cómo trabajar con persistencia y relaciones en una aplicación Spring Boot.
