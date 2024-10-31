package Service

import Interfaces.ICrudProducto
import Repository.ProductoRepository
import model.Producto
import model.Proveedor

class ProductoService(
    val productoRepository: ICrudProducto
) {
    fun insertProducto(producto: Producto){
        return productoRepository.insertProducto(producto)
    }

    fun getProducto(id: String): Producto?{
        return productoRepository.getProducto(id)
    }

    fun getAllProductos(): List<Producto>{
        return productoRepository.getAllProductos()
    }

    fun getAllProductosWithStock(): List<Producto>{
        return productoRepository.getAllProductosWithStock()
    }

    fun getAllProductsWithoutStock(): List<Producto>{
        return productoRepository.getAllProductsWithoutStock()
    }

    fun getProveedorFromProducto(id: String): Proveedor?{
        return productoRepository.getProveedorFromProducto(id)
    }

    fun updateNombre(id: String,newNombre: String){
        return productoRepository.updateNombre(id,newNombre)
    }

    fun modStock(id: String, newStock: Int){
        return productoRepository.modStock(id,newStock)
    }

    fun restarStock(id: String, toDecrease: Int){
        return productoRepository.restarStock(id,toDecrease)
    }

    fun sumarStock(id: String, toIncrease: Int){
        return productoRepository.sumarStock(id,toIncrease)
    }

    fun deleteProducto(id: String){
        return productoRepository.deleteProducto(id)
    }
}