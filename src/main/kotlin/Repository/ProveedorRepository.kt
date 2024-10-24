package Repository

import model.Proveedor

class ProveedorRepository {

    fun insertProveedor(proveedor: Proveedor){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {
            val proveedorFind = em.find(Proveedor::class.java,proveedor.id)
            if (proveedorFind == null){
                em.persist(proveedor)
                em.transaction.commit()
            }else{
                println("El Proveedor que se ha introducido ya existe en la base de datos")
                em.transaction.rollback()
            }
        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }

        em.close()
    }


    fun selectProveedor(proveedorId: Long): Proveedor?{
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        var proveedorToReturn: Proveedor? = null

        try {
            proveedorToReturn = em.find(Proveedor::class.java,proveedorId)
            em.transaction.commit()
        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }

        em.close()

        return proveedorToReturn
    }


    fun selectAllProveedors(): List<Proveedor>{

        val em = EntityManagerGenerator.generateEntityManager()
        em.transaction.begin()

        var proveedorList : List<Proveedor> = listOf()

        try {
            proveedorList = em.createQuery("FROM Proveedor", Proveedor::class.java).resultList
            em.transaction.commit()
        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }

        em.close()

        return proveedorList
    }


    fun updateProveedorDir(proveedorId: Long, newDir: String){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {

            val proveedorToMod = em.find(Proveedor::class.java,proveedorId)

            if (proveedorToMod != null){
                proveedorToMod.direccion = newDir
                em.transaction.commit()
            }else {
                println("El Proveedor que intentas modificar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }

        em.close()
    }


    fun deleteProveedor(proveedorId: Long){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {
            val proveedorToDel = em.find(Proveedor::class.java,proveedorId)

            if (proveedorToDel != null){
                em.remove(proveedorToDel)
                em.transaction.commit()
            }else {
                println("El Proveedor que intentas eliminar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            em.transaction.rollback()
        }

        em.close()
    }
}