package Repository

import Interfaces.ICrudProveedor
import model.Proveedor

class ProveedorRepository(
    val entradaYSalida: EntradaYSalida
) : ICrudProveedor {

    override fun insertProveedor(proveedor: Proveedor){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {

            var proveedorFind : Proveedor? = selectAllProveedors().find { it.nombre == proveedor.nombre && it.direccion == proveedor.direccion }

            if (proveedorFind == null){
                em.persist(proveedor)
                em.transaction.commit()
            }else{
                entradaYSalida.mostrarMensaje("El Proveedor que se ha introducido ya existe en la base de datos")
                em.transaction.rollback()
            }
        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()
    }

    override fun selectProveedor(proveedorId: Long): Proveedor?{
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        var proveedorToReturn: Proveedor? = null

        try {
            proveedorToReturn = em.find(Proveedor::class.java,proveedorId)
            em.transaction.commit()
        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()

        return proveedorToReturn
    }


    override fun selectAllProveedors(): List<Proveedor>{

        val em = EntityManagerGenerator.generateEntityManager()
        em.transaction.begin()

        var proveedorList : List<Proveedor> = listOf()

        try {
            proveedorList = em.createQuery("FROM Proveedor", Proveedor::class.java).resultList
            em.transaction.commit()
        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()

        return proveedorList
    }


    override fun updateProveedorDir(proveedorId: Long, newDir: String){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {

            val proveedorToMod = em.find(Proveedor::class.java,proveedorId)

            if (proveedorToMod != null){
                proveedorToMod.direccion = newDir
                em.transaction.commit()
            }else {
                entradaYSalida.mostrarMensaje("El Proveedor que intentas modificar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()
    }


    override fun deleteProveedor(proveedorId: Long){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {
            val proveedorToDel = em.find(Proveedor::class.java,proveedorId)

            if (proveedorToDel != null){
                em.remove(proveedorToDel)
                em.transaction.commit()
            }else {
                entradaYSalida.mostrarMensaje("El Proveedor que intentas eliminar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()
    }
}