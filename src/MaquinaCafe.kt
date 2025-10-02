/**
 * Clase base que representa una máquina de café con gestión de estados mediante una máquina de estados.
 * Esta clase es abierta para permitir la extensión de su funcionalidad mediante herencia.
 */
open class MaquinaCafe {
    /**
     * Estado actual de la máquina. Inicia en [EstadoMaquinaCafe.Apagada].
     */
    protected var estadoActual: EstadoMaquinaCafe = EstadoMaquinaCafe.Apagada

    /**
     * Crédito actual (INICIAL) insertado por el usuario, en euros.
     */
    protected var creditoActual: Double = 0.0

    /**
     * Muestra el estado actual de la máquina y el crédito disponible.
     */
    fun mostrarEstado() {
        println("ESTADO ACTUAL: $estadoActual")
        println("CRÉDITO ACTUAL: €${"%.2f".format(creditoActual)}")
    }

    /**
     * Enciende la máquina si está apagada.
     */
    fun encender() {
        when (estadoActual) {
            is EstadoMaquinaCafe.Apagada -> {
                println("MÁQUINA ENCENDIDA !!")
                estadoActual = EstadoMaquinaCafe.SeleccionandoProducto
            }
            else -> println("LA MÁQUINA YA ESTABA ENCENDIDA")
        }
    }

    /**
     * Inserta una cantidad de crédito en la máquina.
     * Solo se permite desde el estado [EstadoMaquinaCafe.SeleccionandoProducto].
     *
     * @param cantidad -> Es la cantidad en euros a insertar.
     */
    fun insertarCredito(cantidad: Double) {
        if (cantidad <= 0) {
            println("CRÉDITO INVÁLIDO")
            return
        }

        if (estadoActual !is EstadoMaquinaCafe.SeleccionandoProducto) {
            println("NO PUEDES INSERTAR CREDITO EN EL ESTADO ACTUAL: $estadoActual")
            return
        }

        creditoActual += cantidad
        println("CRÉDITO INSERTADO: €${"%.2f".format(cantidad)}")
    }

    /**
     * Selecciona un producto (café) y gestiona la preparación si hay suficiente crédito.
     *
     * @param -> valorProducto Precio del café en euros.
     * @param -> marca del café a servir.
     */
    fun seleccionarProducto(valorProducto: Double, marca: String = "MILKAFÉ") {
        if (estadoActual !is EstadoMaquinaCafe.SeleccionandoProducto) {
            println("ERROR: DEBES ESTAR EN 'SeleccionandoProducto'. TU ESTADO: $estadoActual")
            return
        }

        println("PRODUCTO SELECCIONADO: €${"%.2f".format(valorProducto)} ($marca)")

        if (creditoActual >= valorProducto) {
            creditoActual -= valorProducto
            println("CRÉDITO VÁLIDO. PREPARANDO CAFÉ...")
            estadoActual = EstadoMaquinaCafe.PreparandoCafe
            prepararCafe(marca)
        } else {
            val faltante = valorProducto - creditoActual
            println("CRÉDITO INSUFICIENTE. TE FALTAN €${"%.2f".format(faltante)}")
            estadoActual = EstadoMaquinaCafe.Error("CRÉDITO INSUFICIENTE")
        }
    }

    /**
     * Método que simula la preparación del café.
     *
     * @param marca Marca del café a preparar.
     */
    protected open fun prepararCafe(marca: String) {
        try {
            println("PREPARANDO CAFÉ...")
            Thread.sleep(2000) // tiempo de preparación
            servirCafe(marca, "TAZA ESTANDAR")
        } catch (e: InterruptedException) {
            estadoActual = EstadoMaquinaCafe.Error("INTERRUPCIÓN DURANTE PREPARACIÓN")
            Thread.currentThread().interrupt()
        }
    }

    /**
     * Método que sirve el café y actualiza el estado.
     * Puede ser sobrescrito para cambiar el tipo de recipiente.
     *
     * @param marca Marca del café.
     * @param recipiente Tipo de recipiente.
     */
    protected open fun servirCafe(marca: String, recipiente: String) {
        println("SIRVIENDO CAFÉ... ¡ NO LO JUNTES CON UN SIGARRO jjj !")
        estadoActual = EstadoMaquinaCafe.SirviendoCafe(marca, recipiente)
    }

    /**
     * Apaga la máquina y restablece el crédito.
     */
    fun apagar() {
        println("APAGANDO LA MÁQUINA.")
        estadoActual = EstadoMaquinaCafe.Apagada
        creditoActual = 0.0
    }

    /**
     * Reinicia la máquina al estado inicial (Apagada) y restablece el crédito.
     */
    fun reset() {
        println("REINICIANDO MÁQUINA...")
        estadoActual = EstadoMaquinaCafe.Apagada
        creditoActual = 0.0
    }
}

/**
 * Herencia y reutilización de código.
 */
class MaquinaCafePremium : MaquinaCafe() {
    /**
     * Sobrescribe el método servirCafe para utilizar una taza de cerámica diferente "premium".
     */
    override fun servirCafe(marca: String, recipiente: String) {
        super.servirCafe(marca, "TAZA CERÁMICA PREMIUM")
    }
}