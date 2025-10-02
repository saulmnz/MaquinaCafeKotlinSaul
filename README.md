# Máquina de Café - Máquina de Estados en Kotlin

El proyecto **máquina de café** utiliza el patrón **máquina de estados**, con clases selladas, herencia y transiciones.  

## Características

- **Clases selladas** para modelar estados de forma.
- Uso de `object` para estados sin datos y `data class` para estados con información (marca, recipiente, mensaje de error).
- **Herencia**: extensión mediante `MaquinaCafePremium`.
- Gestión de crédito, errores y reinicio.
- Código comentado con **KDoc**.

## Diagrama de Estados

![Máquina de café](Diagrama_estados.jpg)
