package Repository

import Interfaces.ICrudProducto
import model.Producto
import model.Proveedor

class ProductoRepository(
    val entradaYSalida: EntradaYSalida
): ICrudProducto {

    override fun insertProducto(producto: Producto){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {
            val productFind = em.find(Producto::class.java,producto.id)
            if (productFind == null){
                em.persist(producto)
                em.transaction.commit()
            }else{
                entradaYSalida.mostrarMensaje("El producto que se ha introducido ya existe en la base de datos")
                em.transaction.rollback()
            }
        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }


    }

    override fun getProducto(id: String): Producto?{
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        var productToReturn: Producto? = null

        try {
            productToReturn = em.find(Producto::class.java,id)
            em.transaction.commit()
        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()

        return productToReturn
    }

    override fun getAllProductos(): List<Producto>{
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        var productList : List<Producto> = listOf()

        try {
            productList = em.createQuery("FROM Producto", Producto::class.java).resultList
            em.transaction.commit()
        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()

        return productList
    }

    override fun getAllProductosWithStock(): List<Producto>{
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        var productList : List<Producto> = listOf()

        try {
            productList = em.createQuery("FROM Producto", Producto::class.java).resultList.filter { it.stock > 0 }
            em.transaction.commit()
        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()

        return productList
    }

    override fun getAllProductsWithoutStock(): List<Producto>{
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        var productList : List<Producto> = listOf()

        try {
            productList = em.createQuery("FROM Producto", Producto::class.java).resultList.filter { it.stock <= 0 }
            em.transaction.commit()
        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()

        return productList
    }

    override fun getProveedorFromProducto(id: String): Proveedor?{
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        var provToReturn : Proveedor? = null

        try {
            val producto = getProducto(id)
            if (producto == null){
                entradaYSalida.mostrarMensaje("El producto no existe")
                em.transaction.rollback()
            }else {
                provToReturn = producto.proveedor
                em.transaction.commit()
            }
        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()

        return provToReturn

    }

    override fun updateNombre(id: String,newNombre: String){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {

            val productToMod = em.find(Producto::class.java,id)

            if (productToMod != null){
                productToMod.nombre = newNombre
                em.transaction.commit()
            }else {
                entradaYSalida.mostrarMensaje("El producto que intentas modificar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()
    }

    override fun modStock(id: String, newStock: Int){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {

            val productToMod = em.find(Producto::class.java,id)

            if (productToMod != null){
                productToMod.stock = newStock
                em.transaction.commit()
            }else {
                entradaYSalida.mostrarMensaje("El producto que intentas modificar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()
    }

    override fun restarStock(id: String, toDecrease: Int){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {

            val productToMod = em.find(Producto::class.java,id)

            if (productToMod != null){
                productToMod.stock -= toDecrease
                em.transaction.commit()
            }else {
                entradaYSalida.mostrarMensaje("El producto que intentas modificar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()
    }

    override fun sumarStock(id: String, toIncrease: Int){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {

            val productToMod = em.find(Producto::class.java,id)

            if (productToMod != null){
                productToMod.stock += toIncrease
                em.transaction.commit()
            }else {
                entradaYSalida.mostrarMensaje("El producto que intentas modificar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()
    }

    override fun deleteProducto(id: String){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {
            val productToDel = em.find(Producto::class.java,id)

            if (productToDel != null){
                em.remove(productToDel)
                em.transaction.commit()
            }else {
                entradaYSalida.mostrarMensaje("El producto que intentas eliminar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()
    }
}