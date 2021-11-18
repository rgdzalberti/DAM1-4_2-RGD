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

    fun Tienda.obtenerClientesPorCiudad(Ciudad:Ciudad): List<Clientes>
    {
        val clientesPorCiudad = mutableListOf<Clientes>()
        clientes.forEach {if (Ciudad == it.ciudad){clientesPorCiudad.add(it)}}
        return clientesPorCiudad
    }

    fun Tienda.checkTodosClientesSonDe(ciudad : Ciudad): Boolean {
        return clientes.all {it.ciudad == ciudad}
    }

    fun Tienda.hayClientesDe(ciudad: Ciudad): Boolean {
        return clientes.any {it.ciudad == ciudad}
    }

    fun Tienda.cuentaClientesDe(ciudad: Ciudad): Int {
        return clientes.count {it.ciudad == ciudad}
    }

    fun Tienda.encuentraClienteDe(ciudad: Ciudad): Clientes? {
        return clientes.find {it.ciudad == ciudad}
    }

    fun Tienda.obtenerClientesOrdenadosPorPedidos(): List<Clientes> {
        return clientes.sortedBy {it.pedidos.size}
    }
    fun Tienda.obtenerClientesConPedidosSinEntregar(): Set<Clientes> {
        return clientes.filter { it -> it.pedidos.any { !it.estaEntregado }}.toSet()
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