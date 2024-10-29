package Interfaces

import model.Usuario

interface ICrudUsuario {

    fun insertUser(user: Usuario)

    fun selectUser(userName: String): Usuario?

    fun selectAllUsers(): List<Usuario>

    fun updateUserPass(userName: String,newPass: String)

    fun deleteUser(userName: String)

}