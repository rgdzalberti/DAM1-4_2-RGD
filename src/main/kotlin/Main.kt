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
        val ClientesPorCiudad = mutableMapOf<Ciudad, List<Clientes>>()
        val set = mutableSetOf<Ciudad>()
        clientes.forEach {set.add(it.ciudad)}
        set.forEach {ClientesPorCiudad[it]}
        clientes.forEach {cliente -> set.forEach {if (it == cliente.ciudad) {ClientesPorCiudad[it] = clientes.filter { cliente2 -> cliente2.ciudad == it }} } }
        return ClientesPorCiudad
    }
    fun obtenerProductosPedidosPorTodos(): Set<Producto>
    {
        return clientes.flatMap {it.pedidos.flatMap {it.productos}}.toSet()
    }

    fun mapeaNombreACliente(): Map<String, Clientes>{
        return clientes.associateBy { it.nombre }
    }

    fun mapeaClienteACiudad(): Map<Clientes, Ciudad>{
        return clientes.associateWith { it.ciudad }
    }

    fun Tienda.mapeaNombreClienteACiudad(): Map<String, Ciudad>{
        return clientes.associate { it.nombre to it.ciudad }
    }



}

data class Clientes(val nombre: String, val ciudad: Ciudad, val pedidos: List<Pedido>) {
    override fun toString() = "$nombre from ${ciudad.nombre}"

    fun obtenerProductosPedidos(): List<Producto> {
        return pedidos.flatMap {it.productos.toList()}
    }

    fun encuentraProductoMasCaro(): Producto?
    {
        var ProductoPrecio =  mutableListOf<Pair<Producto,Double>>()
        pedidos.forEach {pedido -> if (pedido.estaEntregado == true) {pedido.productos.forEach {ProductoPrecio.add(Pair(it,it.precio))}}}
        return ProductoPrecio.find { Pair -> Pair.second == ProductoPrecio.maxOf { it.second } }?.first
    }

    fun dineroGastado(): Double {
        var listaDineroGastado = mutableListOf<Double>()
        pedidos.forEach {pedido -> pedido.productos.forEach {listaDineroGastado.add(it.precio)}}
        return listaDineroGastado.sum()
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