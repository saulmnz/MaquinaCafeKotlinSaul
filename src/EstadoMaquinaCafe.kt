/**
 * Clase sellada que representa todos los posibles estados de la máquina de café.
 *
 * Se utilizan 'object' para estados sin datos y 'data class' para aquellos que
 * necesitan almacenar información relevante ya sea la marca de café o el mensaje de error.
 *
 */
sealed class EstadoMaquinaCafe {
    /**
     * Estado en el que la máquina está apagada o inactiva.
     */
    object Apagada : EstadoMaquinaCafe()

    /**
     * Estado en el que la máquina está encendida y lista para que el usuario seleccione un producto.
     */
    object SeleccionandoProducto : EstadoMaquinaCafe()

    /**
     * Estado temporal mientras se prepara el café.
     */
    object PreparandoCafe : EstadoMaquinaCafe()

    /**
     * Estado en el que el café ha sido preparado y se está sirviendo.
     * Almacena la marca del café y el tipo de recipiente utilizado.
     *
     * @property marca del café servido.
     * @property recipiente en el que se sirve.
     */
    data class SirviendoCafe(val marca: String, val recipiente: String) : EstadoMaquinaCafe()

    /**
     * Estado que representa un fallo en la operación de la máquina.
     *
     * @property mensaje descriptivo del error ocurrido.
     */
    data class Error(val mensaje: String) : EstadoMaquinaCafe()
}