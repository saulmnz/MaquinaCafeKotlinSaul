/**
 * Prueba de la máquina de café y su máquina de estados.
 * Probamos los estados, transiciones, la herencia y los posibles errores que puedan surgir.
 */
fun main() {
    println(" PRUEBA BÁSICO FUNCIONAMIENTO DE LA MÁQUINA")
    val maquina = MaquinaCafe()

    maquina.mostrarEstado()
    maquina.encender()
    maquina.mostrarEstado()

    maquina.insertarCredito(2.50)
    maquina.mostrarEstado()

    maquina.seleccionarProducto(2.50, "MILKAFÉ")
    maquina.mostrarEstado()

    maquina.apagar()
    maquina.mostrarEstado()

    println("\n PRUEBA AVANZADA ( CON HERENCIA ) ")
    val maquinaPremium = MaquinaCafePremium()

    maquinaPremium.encender()
    maquinaPremium.insertarCredito(3.0)
    maquinaPremium.seleccionarProducto(2.50, "CAFÉ PREMIUM")
    maquinaPremium.mostrarEstado()

    println("\n COMPROBAR EL ESTADO CUANDO EL CRÉDITO ES INSUFICIENTE ")
    val maquinaError = MaquinaCafe()
    maquinaError.encender()
    maquinaError.insertarCredito(1.0)
    maquinaError.seleccionarProducto(2.50)
    maquinaError.mostrarEstado()

    println("\n SE REINICIA LA MÁQUINA")
    maquinaError.reset()
    maquinaError.mostrarEstado()
}