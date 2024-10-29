package Repository

import Interfaces.ICrudUsuario
import model.Usuario

class UserRepository(
    val entradaYSalida: EntradaYSalida
): ICrudUsuario {

    override fun insertUser(user: Usuario){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {
            val userFind = em.find(Usuario::class.java,user.nombreUsuario)
            if (userFind == null){
                em.persist(user)
                em.transaction.commit()
            }else{
                entradaYSalida.mostrarMensaje("El usuario que se ha introducido ya existe en la base de datos")
                em.transaction.rollback()
            }
        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()
    }


    override fun selectUser(userName: String): Usuario?{
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        var userToReturn: Usuario? = null

        try {
            userToReturn = em.find(Usuario::class.java,userName)
            em.transaction.commit()
        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()

        return userToReturn
    }


    override fun selectAllUsers(): List<Usuario>{

        val em = EntityManagerGenerator.generateEntityManager()
        em.transaction.begin()

        var userList : List<Usuario> = listOf()

        try {
            userList = em.createQuery("FROM Usuario", Usuario::class.java).resultList
            em.transaction.commit()
        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()

        return userList
    }


    override fun updateUserPass(userName: String,newPass: String){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {

            val userToMod = em.find(Usuario::class.java,userName)

            if (userToMod != null){
                userToMod.password = newPass
                em.transaction.commit()
            }else {
                entradaYSalida.mostrarMensaje("El usuario que intentas modificar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()
    }


    override fun deleteUser(userName: String){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {
            val userToDel = em.find(Usuario::class.java,userName)

            if (userToDel != null){
                em.remove(userToDel)
                em.transaction.commit()
            }else {
                entradaYSalida.mostrarMensaje("El usuario que intentas eliminar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            entradaYSalida.mostrarMensaje(e.message)
            em.transaction.rollback()
        }

        em.close()
    }


}