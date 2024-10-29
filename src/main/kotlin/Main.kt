import Repository.ProductoRepository
import Repository.ProveedorRepository
import Repository.UserRepository
import jakarta.persistence.Persistence
import model.Proveedor
import model.Usuario

fun main() {

    //para instanciar el objeto que genera los em
    EntityManagerGenerator


    //Instancias necesarias para el programa principal
    val consola = EntradaYSalida()
    val usuarios = UserRepository(consola)
    val proveedores = ProveedorRepository(consola)
    val productos = ProductoRepository(consola)

    //Programa principal
    val app = AppManager(usuarios,productos,proveedores,consola)

    //Controla las opciones
    val opManager = OptionManager(app,consola)

    //Empieza el programa
    opManager.startProgram()
}