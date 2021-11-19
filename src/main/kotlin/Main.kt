package un4.collections

data class Tienda(val nombre: String, val clientes: List<Clientes>)
{
    fun obtenerConjuntoDeClientes(): Set<Clientes> {
        return clientes.toSet()
    }

    fun obtenerCiudadesDeClientes(): Set<Ciudad>
    {
        val ciudades = mutableListOf<Ciudad>()
        clientes.forEach {ciudades.add(it.ciudad)}
        return ciudades.toSet()
    }
    fun obtenerClientesPorCiudad(Ciudad:Ciudad): List<Clientes>
    {
        val clientesPorCiudad = mutableListOf<Clientes>()
        clientes.forEach {if (Ciudad == it.ciudad){clientesPorCiudad.add(it)}}
        return clientesPorCiudad
    }
    fun checkTodosClientesSonDe(ciudad : Ciudad): Boolean {
        return clientes.all {it.ciudad == ciudad}
    }
    fun hayClientesDe(ciudad: Ciudad): Boolean {
        return clientes.any {it.ciudad == ciudad}
    }
    fun cuentaClientesDe(ciudad: Ciudad): Int {
        return clientes.count {it.ciudad == ciudad}
    }
    fun encuentraClienteDe(ciudad: Ciudad): Clientes? {
        return clientes.find {it.ciudad == ciudad}
    }
    fun obtenerClientesOrdenadosPorPedidos(): List<Clientes> {
        return clientes.sortedBy {it.pedidos.size}
    }
    fun obtenerClientesConPedidosSinEntregar(): Set<Clientes> {
        return clientes.filter {it.pedidos.any { !it.estaEntregado }}.toSet()
    }

    fun obtenerProductosPedidos(): Set<Producto> {
        return clientes.flatMap {it.pedidos.flatMap {it.productos}}.toSet()
    }

    fun obtenerNumeroVecesProductoPedido(producto: Producto): Int {
        var NumeroVecesProductoPedido: Int = 0
        clientes.forEach {it.pedidos.forEach {it.productos.forEach {if (it==producto) {NumeroVecesProductoPedido++} } } }
        return NumeroVecesProductoPedido
    }

    fun agrupaClientesPorCiudad(): Map<Ciudad, List<Clientes>> {

    }



}

data class Clientes(val nombre: String, val ciudad: Ciudad, val pedidos: List<Pedido>) {
    override fun toString() = "$nombre from ${ciudad.nombre}"

    fun obtenerProductosPedidos(): List<Producto> {
        return pedidos.flatMap {it.productos.toList()}
    }

    fun encuentraProductoMasCaro(): Producto?
    {
        //var juja = pedidos.filter {it.productos.find {  }}
        //var juja = pedidos.filter {it.estaEntregado}
        return juja
    }

    fun dineroGastado(): Double {
        var arrayNumeros = pedidos.flatMap {it.productos.filter}
        //arrayNumeros.sum()
        return arrayNumeros
    }


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