package Repository

import model.Usuario

class UserRepository {

    fun insertUser(user: Usuario){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {
            val userFind = em.find(Usuario::class.java,user.nombreUsuario)
            if (userFind == null){
                em.persist(user)
                em.transaction.commit()
            }else{
                println("El usuario que se ha introducido ya existe en la base de datos")
                em.transaction.rollback()
            }
        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }

        em.close()
    }


    fun selectUser(userName: String): Usuario?{
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        var userToReturn: Usuario? = null

        try {
            userToReturn = em.find(Usuario::class.java,userName)
            em.transaction.commit()
        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }

        em.close()

        return userToReturn
    }


    fun selectAllUsers(): List<Usuario>{

        val em = EntityManagerGenerator.generateEntityManager()
        em.transaction.begin()

        var userList : List<Usuario> = listOf()

        try {
            userList = em.createQuery("FROM Usuario", Usuario::class.java).resultList
            em.transaction.commit()
        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }

        em.close()

        return userList
    }


    fun updateUserPass(userName: String,newPass: String){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {

            val userToMod = em.find(Usuario::class.java,userName)

            if (userToMod != null){
                userToMod.password = newPass
                em.transaction.commit()
            }else {
                println("El usuario que intentas modificar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            println(e.message)
            em.transaction.rollback()
        }

        em.close()
    }


    fun deleteUser(userName: String){
        val em = EntityManagerGenerator.generateEntityManager()

        em.transaction.begin()

        try {
            val userToDel = em.find(Usuario::class.java,userName)

            if (userToDel != null){
                em.remove(userToDel)
                em.transaction.commit()
            }else {
                println("El usuario que intentas eliminar no existe")
                em.transaction.rollback()
            }

        }catch (e: Exception){
            em.transaction.rollback()
        }

        em.close()
    }


}