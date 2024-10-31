import Interfaces.ICrudProducto
import Interfaces.ICrudProveedor
import Interfaces.ICrudUsuario
import Service.ProductoService
import Service.ProveedorService
import Service.UserService
import model.Producto
import model.Proveedor
import model.Usuario
import java.util.Date
import kotlin.math.absoluteValue

class AppManager(
    val userService: UserService,
    val productoService: ProductoService,
    val proveedorService: ProveedorService,
    val entradaYSalida: EntradaYSalida
) {

    fun createUser(){
        val nombre = entradaYSalida.pedirString("Dime el nombre del usuario a registrar: ")
        val contrasena = entradaYSalida.pedirString("Dime la contraseña del usuario a registrar: ")

        userService.insertUser(Usuario(nombre,contrasena))

    }

    fun login(userName: String,pass: String): Boolean{
        val userExist = userService.selectUser(userName)

        if (userExist != null){
            return if (userExist.nombreUsuario == userName && userExist.password == pass){
                true
            }else {
                entradaYSalida.mostrarMensaje("La contraseña no es correcta")
                false
            }
        }else {
            entradaYSalida.mostrarMensaje("El usuario no existe")
            return false
        }
    }

    private fun calcularId(cat: String, nombre: String,proovedorName: String): String{
        return cat.take(3) + nombre.take(3) + proovedorName.take(3)
    }

    fun addProduct(){

        //Pide los datos del producto
        val cat = entradaYSalida.pedirString("Dime la categoria del producto a añadir: ")
        val nom = entradaYSalida.pedirString("Dime el nombre del producto a añadir: ")
        val desc = entradaYSalida.pedirString("Dime la descripcion del producto a añadir: ")
        val precio = entradaYSalida.pedirFloat("Dime el precio sin iva del producto a añadir: ")
        val stock = entradaYSalida.pedirInt("Dime el stock del producto a añadir: ")

        //Obtiene los proveedores y los muestra
        val proveedores = proveedorService.selectAllProveedors()

        entradaYSalida.mostrarMensaje("Estos son los proveedores que hay --> ")

        proveedores.forEach {
            entradaYSalida.mostrarMensaje("${it.id}, ${it.nombre}, ${it.direccion}")
        }

        //Pide los datos del proveedor
        val proovedorName = entradaYSalida.pedirString("Dime el nombre del proveedor: ")
        val dir = entradaYSalida.pedirString("Dime la direccion de el proveedor: ")

        //Dependiendo si el proveedor existe te pergunta de crear uno nuevo o no
        val proveedor = proveedores.find { it.nombre == proovedorName && it.direccion == dir}

        if (proveedor == null){

            entradaYSalida.mostrarMensaje("Ese proveedor no existe, quieres crearlo?")
            entradaYSalida.mostrarMensaje("1 .Si")
            entradaYSalida.mostrarMensaje("2. No")
            var createOp = entradaYSalida.elegirEntreRangos(1,2)

            if (createOp == 1) {

                val proov = Proveedor(null,nombre = proovedorName, direccion = dir,null)
                proveedorService.insertProveedor(proov)
                val productoToInsert =
                    Producto(
                        id = calcularId(cat,nom,proovedorName),
                        categoria = cat,
                        nombre = nom,
                        descripcion =  desc,
                        precioSinIva =  precio,
                        precionConIva = ((precio + (precio * 0.21)).toFloat()),
                        fecha_alta =  Date(),
                        stock =  stock,
                        proveedor = proov)
                productoService.insertProducto(productoToInsert)
            }else{
                entradaYSalida.mostrarMensaje("No se ha creado ni el proveedor ni el producto")
            }
        }else{
            val productoToInsert =
                Producto(
                    id = calcularId(cat,nom,proovedorName),
                    categoria = cat,
                    nombre = nom,
                    descripcion =  desc,
                    precioSinIva =  precio,
                    precionConIva = ((precio + (precio * 0.21)).toFloat()),
                    fecha_alta =  Date(),
                    stock =  stock,
                    proveedor = proveedor)
            productoService.insertProducto(productoToInsert)
        }
    }

    private fun mostrarProductos(){
        val productos = productoService.getAllProductos()

        entradaYSalida.mostrarMensaje("Estos son todos los productos")

        productos.forEach {
            entradaYSalida.mostrarMensaje("${it.id}, ${it.categoria}, ${it.nombre}, proveedor -> ${it.proveedor?.nombre}")
        }
    }

    fun deleteProduct(){

        mostrarProductos()

        val id = entradaYSalida.pedirString("Dime la id del producto que quieres eliminar: ")

        productoService.deleteProducto(id)

    }

    fun modNombreProduct(){

        mostrarProductos()

        val id = entradaYSalida.pedirString("Dime la id del producto que quieres modificar el nombre: ")

        val newName = entradaYSalida.pedirString("Dime el nuevo nombre del producto: ")

        productoService.updateNombre(id,newName)

    }

    fun modStock(){

        mostrarProductos()

        val id = entradaYSalida.pedirString("Dime la id del producto que quieres modificar el stock: ")

        entradaYSalida.mostrarMensaje("Que quieres hacer: ")
        entradaYSalida.mostrarMensaje("1. Modificar stock")
        entradaYSalida.mostrarMensaje("2. Sumar al stock")
        entradaYSalida.mostrarMensaje("3. Restar al stock")

        val op = entradaYSalida.elegirEntreRangos(1,3)
        val nuevoNum = entradaYSalida.pedirInt("Introduce el numero que vayas a modificar,sumar o resar: ").absoluteValue


        when (op){
            1 ->{
                productoService.modStock(id,nuevoNum)
            }
            2 ->{
                productoService.sumarStock(id,nuevoNum)
            }
            3 ->{
                productoService.restarStock(id,nuevoNum)
            }
        }
    }

    fun obtenerProducto(){

        mostrarProductos()

        val id = entradaYSalida.pedirString("Dime la id del producto que quieres ver toda la informacion: ")

        val product = productoService.getProducto(id)

        if (product != null){
            entradaYSalida.mostrarMensaje("${product.id}, ${product.categoria}, ${product.nombre}, ${product.precionConIva}, ${product.proveedor} ")
        }

    }

    fun obtenerProductosConStock(){
        entradaYSalida.mostrarMensaje("Estos son todos los productos con stock: ")
        productoService.getAllProductosWithStock().forEach { product ->
            entradaYSalida.mostrarMensaje("${product.id}, ${product.categoria}, ${product.nombre}, ${product.precionConIva}, ${product.proveedor} ")

        }
    }

    fun obtenerProductosSinStock(){
        entradaYSalida.mostrarMensaje("Estos son todos los productos sin stock: ")
        productoService.getAllProductsWithoutStock().forEach { product ->
            entradaYSalida.mostrarMensaje("${product.id}, ${product.categoria}, ${product.nombre}, ${product.precionConIva}, ${product.proveedor} ")
        }
    }

    fun getProveedorDeProducto(){
        mostrarProductos()

        val id = entradaYSalida.pedirString("Dime la id del producto que quieres ver toda la informacion: ")

        val proveedor = productoService.getProducto(id)?.proveedor

        if (proveedor != null){
            entradaYSalida.mostrarMensaje("${proveedor.id}, ${proveedor.nombre}, ${proveedor.direccion} ")
        }
    }

    fun getAllProveedores(){
        entradaYSalida.mostrarMensaje("Todos los proveedores: ")

        proveedorService.selectAllProveedors().forEach { proveedor ->
            entradaYSalida.mostrarMensaje("${proveedor.id}, ${proveedor.nombre}, ${proveedor.direccion} ")

        }

    }

}