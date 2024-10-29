package Interfaces

import model.Producto
import model.Proveedor

interface ICrudProducto {

    fun insertProducto(producto: Producto)

    fun getProducto(id: String): Producto?

    fun getAllProductos(): List<Producto>

    fun getAllProductosWithStock(): List<Producto>

    fun getAllProductsWithoutStock(): List<Producto>

    fun getProveedorFromProducto(id: String): Proveedor?

    fun updateNombre(id: String,newNombre: String)

    fun modStock(id: String, newStock: Int)

    fun restarStock(id: String, toDecrease: Int)

    fun sumarStock(id: String, toIncrease: Int)

    fun deleteProducto(id: String)


}