package Repository

import model.Producto
import model.Proveedor

class ProductoRepository {

    fun insertProducto(producto: Producto){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {
            val productFind = em.find(Producto::class.java,producto.id)
            if (productFind == null){
                em.persist(producto)
                em.transaction.commit()
            }else{
                println("El producto que se ha introducido ya existe en la base de datos")
                em.transaction.rollback()
            }
        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }


    }

    fun getProducto(id: String): Producto?{
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        var productToReturn: Producto? = null

        try {
            productToReturn = em.find(Producto::class.java,id)
            em.transaction.commit()
        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }

        em.close()

        return productToReturn
    }

    fun getAllProductos(): List<Producto>{
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        var productList : List<Producto> = listOf()

        try {
            productList = em.createQuery("FROM Producto", Producto::class.java).resultList
            em.transaction.commit()
        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }

        em.close()

        return productList
    }

    fun getAllProductosWithStock(): List<Producto>{
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        var productList : List<Producto> = listOf()

        try {
            productList = em.createQuery("FROM Producto", Producto::class.java).resultList.filter { it.stock > 0 }
            em.transaction.commit()
        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }

        em.close()

        return productList
    }

    fun getAllProductsWithoutStock(): List<Producto>{
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        var productList : List<Producto> = listOf()

        try {
            productList = em.createQuery("FROM Producto", Producto::class.java).resultList.filter { it.stock <= 0 }
            em.transaction.commit()
        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }

        em.close()

        return productList
    }

    fun getProveedorFromProducto(id: String): Proveedor?{
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        var provToReturn : Proveedor? = null

        try {
            val producto = getProducto(id)
            if (producto == null){
                println("El producto no existe")
                em.transaction.rollback()
            }else {
                provToReturn = producto.proveedor
                em.transaction.commit()
            }
        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }

        em.close()

        return provToReturn

    }

    fun updateNombre(id: String,newNombre: String){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {

            val productToMod = em.find(Producto::class.java,id)

            if (productToMod != null){
                productToMod.nombre = newNombre
                em.transaction.commit()
            }else {
                println("El producto que intentas modificar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }

        em.close()
    }

    fun modStock(id: String, newStock: Int){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {

            val productToMod = em.find(Producto::class.java,id)

            if (productToMod != null){
                productToMod.stock = newStock
                em.transaction.commit()
            }else {
                println("El producto que intentas modificar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }

        em.close()
    }

    fun restarStock(id: String, toDecrease: Int){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {

            val productToMod = em.find(Producto::class.java,id)

            if (productToMod != null){
                productToMod.stock -= toDecrease
                em.transaction.commit()
            }else {
                println("El producto que intentas modificar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }

        em.close()
    }

    fun sumarStock(id: String, toIncrease: Int){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {

            val productToMod = em.find(Producto::class.java,id)

            if (productToMod != null){
                productToMod.stock += toIncrease
                em.transaction.commit()
            }else {
                println("El producto que intentas modificar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }

        em.close()
    }

    fun deleteProducto(id: String){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {
            val productToDel = em.find(Producto::class.java,id)

            if (productToDel != null){
                em.remove(productToDel)
                em.transaction.commit()
            }else {
                println("El producto que intentas eliminar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            em.transaction.rollback()
        }

        em.close()
    }
}