# Máquina de Café - Máquina de Estados en Kotlin

El proyecto **máquina de café** utiliza el patrón **máquina de estados**, con clases selladas, herencia y transiciones.  

## Características

- **Clases selladas** para modelar estados de forma.
- Uso de `object` para estados sin datos y `data class` para estados con información (marca, recipiente, mensaje de error).
- **Herencia**: extensión mediante `MaquinaCafePremium`.
- Gestión de crédito, errores y reinicio.
- Código comentado con **KDoc**.

## Diagrama de Estados

```mermaid
stateDiagram-v2
    [*] --> Apagada
    Apagada --> SeleccionandoProducto : encender()
    SeleccionandoProducto --> SeleccionandoProducto : insertarCredito()
    SeleccionandoProducto --> PreparandoCafe : seleccionarProducto() (crédito ≥ precio)
    SeleccionandoProducto --> Error : seleccionarProducto() (crédito < precio)
    PreparandoCafe --> SirviendoCafe : prepararCafe() (éxito)
    PreparandoCafe --> Error : fallo en preparación
    SirviendoCafe --> Apagada : apagar() / reset()
    Error --> Apagada : reset()
