package Service

import Interfaces.ICrudUsuario
import Repository.UserRepository
import model.Usuario

class UserService(
    val userRepository: ICrudUsuario
) {

    fun insertUser(user: Usuario){
        return userRepository.insertUser(user)
    }

    fun selectUser(userName: String): Usuario?{
        return userRepository.selectUser(userName)
    }

    fun selectAllUsers(): List<Usuario>{
        return userRepository.selectAllUsers()
    }

    fun updateUserPass(userName: String,newPass: String){
        return userRepository.updateUserPass(userName,newPass)
    }

    fun deleteUser(userName: String){
        return userRepository.deleteUser(userName)
    }
}