import Repository.ProductoRepository
import Repository.ProveedorRepository
import Repository.UserRepository
import Service.ProductoService
import Service.ProveedorService
import Service.UserService
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

    //Crea los servicios
    val userService = UserService(usuarios)
    val productService = ProductoService(productos)
    val proveedorService = ProveedorService(proveedores)


    //Programa principal
    val app = AppManager(userService,productService,proveedorService,consola)

    //Controla las opciones
    val opManager = OptionManager(app,consola)

    //Empieza el programa
    opManager.startProgram()
}