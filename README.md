# STOCKIFY
CS 2031 **Desarrollo Basado en Plataformas**

**Integrantes**

- Espinoza Torres, Hector Miguel
- Medina Reyes, Patrick Ricardo
- Teran Taica, Mauricio Eduardo
- Torres Ccencho, Leo Alexander
- Vizcardo Chavez, Juan Diego

## Índice

1. [Introducción](#1-Introducción)

- [Medidas de Seguridad Implementadas](#6-medidas-de-seguridad-implementadas)

## 1. Introducción


## Identificación del Problema o Necesidad


## 3. Descripción de la Solución


## 4. Modelo de Entidades

|Entidad|Atributos|Relaciones|Descripción|
|-------|---------|----------|-----------|
|**Usuario**|- id: Long<br> - nombre: String<br> - apellido: String<br> - email: String<br> - password: String<br> - rol: Rol<br> - telefono: String<br> - sede: String<br> - fechaRegistro: Date<br> - activo: Boolean| - Movimiento: OneToMany <br> - Valorizacion: OneToMany|Entidad destinada a guardar la información de los usuarios que interactúen con la aplicación, a esta se le agrega configuraciones de seguridad.|
|**Producto**|- id: Long<br> - nombre: String<br> - unidadMedida: String<br> - categoria: String<br> - stockMinimo: Double<br> - stockActual: Double<br> - activo: Boolean<br> - fechaCreacion: Date<br> - ultimoActualizado: Date| - Lote: OneToMany<br> - Movimiento: OneToMany<br> - AlertaStock: OneToMany<br> - RecetaDetalle: OneToMany|Esta entidad almacena todo lo relacionado a los productos e insumos que maneja el negocio.|
|**Lote**|- id: Long<br> - codigoLote: String<br> - costoUnitario: Double<br> - costoTotal: Double<br> - cantidadInicial: Double<br> - cantidadDisponible: Double<br> - fechaCompra: Date<br> - fechaVencimiento: Date<br> - estado: Estado| - Producto: ManyToOne<br> - Almacen: ManyToOne|Entidad encargada de registrar la compra de productos, con detalles específicos.|
|**Movimiento**|- id: Long<br> - tipoMovimiento: TipoMovimiento<br> - cantidad: Double<br> - costoUnitario: Double<br> - costoTotal: Double<br> - fechaMovimiento: Date<br> - observacion: String<br> - origen: String<br> - anulado: Boolean|- Producto: ManyToOne<br> - Lote: ManyToOne<br> - Usuario: ManyToOne<br> - Almacen: ManyToOne|Entidad que registra cada una de las entradas y salidas que ocurren al inventario.|
|**Almacen**|- id: Long<br> - nombre: String<br> - ubicacion: String<br> - responsable: String<br> - capacidadMaxima: Double<br> - activo: Boolean<br> - fechaCreacion: Date<br> - ultimoActualizado: Date|- Lote: OneToMany<br> - Movimiento: OneToMany|Entidad encargada guardar la infomación necesaria de los lugares donde físicamente se guardan los productos.|
|**ValorizacionPeriodo**|- id: Long<br> - periodo: String<br> - metodoValorizacion: MetodoValorizacion<br> - valorInventario: Double<br> - costoVentas: Double<br> - observacion: String<br> - fechaValorizacion: Date<br> - cerrado: Boolean|- Usuario: ManyToOne<br>|Entidad que guarda los resultados de los cálculos del valor del inventario en un periodo.|
|**AlertaStock**|- id: Long<br> - mensaje: String<br> - fechaAlerta: Date<br> - atendido: Boolean<br> - prioridad: Prioridad|- Producto: ManyToOne|Entidad detecta y hace un registro de las situaciones críticas que se presentan en el inventario.|
|**RecetaBase**|- id: Long<br> - nombrePlato: String<br> - descripcion: String<br> - porcionesBase: Integer<br> - unidadPoricion: String<br> - fechaCreacion: Date|- RecetaDetalle: OneToMany|Es la entidad que se encarga de guardar las receta base para su preparación en el restaurante|
|**RecetaDetalle**|- id: Long<br> - cantidadNecesaria: Double<br> - unidadMedida: String|- RecetaBase: ManyToOne<br> - Producto: ManyToOne|Entidad que registra los detalles de una preparación, como la cantidad a elaborar con la cantidad de los productos|
|**Reporte**|- id: Long<br> - periodo: String<br> - fechaGeneracion: Date<br> - formato: FormatoReporte<br> - nombreArchivo: String<br> - observaciones: String<br>|- Usuario: ManyToOne|Entidad encargada de generar y registrara reportes que hagan más visibles y profesionales el estado del inventario.|

## 5. Testing y Manejo de Errores

Durante la ejecucción del proyecto se ha implementado el manejo de múltiples tipos de errores que se podrían presentar, como el uso de credenciales inválidas o de conflictos de varios tipos. Estas son en su mayoría
intervenidas por el mismo spring boot. Sin embargo, la información del código del status http o la información, no es la más precisa para el usuario, por lo que se decidió modificar varios de estos para mejorar la
expeciencia del usuario.

## 6. Medidas de Seguridad Implementadas

La aplicación recurre al uso de un filtro de autenticación y autorización para el acceso a la información. Esto es debido a que, la gran mayoría de los endpoint están bajo resguardo y solo son accesibles si el usuario
se encuentra registrado y en ciertos casos, si tiene el permiso requerido.

## 7. GitHub & Managment

Git Hub ha sido una herramienta de suma utilidad. Esta nos permitió controlar el cómo iba progresando el proyecto, ya que, cada integrante del equipo podía estar al tanto de como se prograsaraba con el desarrollo del
aplicativo. Sin embargo, se pudo mejorar el flujo de este con el uso de issues o tasks, que la misma herramienta provee, para poder tener un mejor mapeo de que realizaría cada integrante ya que la forma de organización
fue más a la antigua si se podría decir.

## Conclusión







##
