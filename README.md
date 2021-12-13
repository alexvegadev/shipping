# Desaf�o t�cnico ML

## Ejecuci�n del proyecto
### Requerimientos:
Postgres >= 10
JDK 8
### Ejecuci�n
A trav�s de la consola, usar el comando: **mvn spring-boot:run**
A trav�s del IDE, ejecutar la clase: **ShippingApplication**
## Requerimiento
### Objetivo 
El Negocio de MercadoLibre se sigue expandiendo y ahora decidi� incursionar en el almacenamiento de los productos de los vendedores. Para ese fin, nos fue requerido construir un microservicio para mantener un control de stock de los productos. Esta aplicaci�n debe mantener la relaci�n entre los productos publicados en el marketplace y su ubicaci�n dentro de cada dep�sito.
### Requerimientos
1. Exponer un endpoint REST para agregar productos en una ubicaci�n.
	2. Se nos indicar� el Dep�sito, producto, cantidad y ubicaci�n donde quiere colocar. 
	3. Validar que la direcci�n tenga el patr�n correcto. 
	4. Que el producto/item sea almacenado en nuestros dep�sitos. 
	5. No se pueden colocar m�s de 3 productos distintos en una ubicaci�n. 
	6. La suma de las cantidades de los productos que hubiera en una ubicaci�n no puede ser mayor a 100 unidades. 
2. Exponer un endpoint para poder retirar productos de una ubicaci�n. Se nos indicar� el dep�sito, producto, cantidad y ubicaci�n de donde sacarla. 
3. Exponer un endpoint de lectura. Se nos indica un dep�sito y una ubicaci�n, y este liste los productos y cantidad que hay en el mismo. 
4. Exponer un endpoint de b�squeda. Se nos indica el dep�sito y producto, y este nos devuelva las posibles ubicaciones y cantidad en las mismas.

## Detalle de la soluci�n
Para hacer las consultas a la API de mercado libre se utiliz� la clase RestTemplate de la librer�a Spring Boot web client, se crearon DTO's en donde solo se traen los campos que son imprescindibles, se cre� una entidad para manejar el dep�sito y se incluyeron las validaciones especificadas para la direcci�n del dep�sito.

## Deploy
El deploy se hizo en Heroku, la direcci�n del sitio es https://mldesafio.herokuapp.com
## Documentaci�n
Se realiz� la documentaci�n con Swagger, la direcci�n para verla est� en https://mldesafio.herokuapp.com/swagger-ui.html
