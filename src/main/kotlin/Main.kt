import Repository.UserRepository
import jakarta.persistence.Persistence
import model.Usuario

fun main() {

    val uR = UserRepository()

    val userToInsert = Usuario("fran","fran")

    //uR.insertUser(userToInsert)

    uR.updateUserPass("fran","paco")
    uR.deleteUser("")
}