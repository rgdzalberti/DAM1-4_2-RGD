package un4.collections

data class Tienda(val nombre: String, val clientes: List<Clientes>)
{
    fun Tienda.obtenerConjuntoDeClientes(): Set<Clientes> {
        return clientes.toSet()
    }

    fun Tienda.obtenerCiudadesDeClientes(): Set<Ciudad>
    {
        val ciudades = mutableListOf<Ciudad>()
        clientes.forEach {ciudades.add(it.ciudad)}
        return ciudades.toSet()
    }



}

data class Clientes(val nombre: String, val ciudad: Ciudad, val pedidos: List<Pedido>) {
    override fun toString() = "$nombre from ${ciudad.nombre}"
}

data class Pedido(val productos: List<Producto>, val estaEntregado: Boolean)

data class Producto(val nombre: String, val precio: Double) {
    override fun toString() = "'$nombre' for $precio"
}

data class Ciudad(val nombre: String) {
    override fun toString() = nombre
}

fun main() {

}