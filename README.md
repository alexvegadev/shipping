
# Desafío técnico ML

## Ejecución del proyecto
### Requerimientos:
**Postgres >= 10**
**JDK >= 8**
### Ejecución
Clonar el repositorio: **git clone https://github.com/alexvegadev/shipping.git
A través de la consola, usar el comando: **mvn spring-boot:run**
A través del IDE, ejecutar la clase: **ShippingApplication**
## Requerimiento
### Objetivo 
El Negocio de MercadoLibre se sigue expandiendo y ahora decidió incursionar en el almacenamiento de los productos de los vendedores. Para ese fin, nos fue requerido construir un microservicio para mantener un control de stock de los productos. Esta aplicación debe mantener la relación entre los productos publicados en el marketplace y su ubicación dentro de cada depósito.
### Requerimientos
1. Exponer un endpoint REST para agregar productos en una ubicación.
	2. Se nos indicará el Depósito, producto, cantidad y ubicación donde quiere colocar. 
	3. Validar que la dirección tenga el patrón correcto. 
	4. Que el producto/item sea almacenado en nuestros depósitos. 
	5. No se pueden colocar más de 3 productos distintos en una ubicación. 
	6. La suma de las cantidades de los productos que hubiera en una ubicación no puede ser mayor a 100 unidades. 
2. Exponer un endpoint para poder retirar productos de una ubicación. Se nos indicará el depósito, producto, cantidad y ubicación de donde sacarla. 
3. Exponer un endpoint de lectura. Se nos indica un depósito y una ubicación, y este liste los productos y cantidad que hay en el mismo. 
4. Exponer un endpoint de búsqueda. Se nos indica el depósito y producto, y este nos devuelva las posibles ubicaciones y cantidad en las mismas.

## Detalle de la solución
Para hacer las consultas a la API de mercado libre se utilizó la clase RestTemplate de la librería Spring Boot web client, se crearon DTO's en donde solo se traen los campos que son imprescindibles, se creó una entidad para manejar el depósito y se incluyeron las validaciones especificadas para la dirección del depósito.

## Deploy
El deploy se hizo en Heroku, la dirección del sitio es https://mldesafio.herokuapp.com
## Documentación
Se realizó la documentación con Swagger, la dirección para verla está en https://mldesafio.herokuapp.com/swagger-ui.html
