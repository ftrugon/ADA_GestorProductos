class OptionManager(
    val appManager: AppManager,
    val entradaYSalida: EntradaYSalida
) {

    fun loginOrCreate(): Boolean{
        entradaYSalida.mostrarMensaje("Que quieres hacer: ")
        entradaYSalida.mostrarMensaje("1. Login")
        entradaYSalida.mostrarMensaje("2. Crear usuario")

        val op = entradaYSalida.elegirEntreRangos(1,2)

        if (op == 1) {
            val nombre = entradaYSalida.pedirString("Dime el nombre de el usuario: ")
            val contrasena = entradaYSalida.pedirString("Dime la contraseña de el usuario: ")
            return appManager.login(nombre,contrasena)
        }else appManager.createUser()

        return false

    }

    fun mostrarMenu(){
        entradaYSalida.mostrarMensaje("Que quieres hacer: ")
        entradaYSalida.mostrarMensaje("1. Dar de alta un producto")
        entradaYSalida.mostrarMensaje("2. Dar de baja un producto")
        entradaYSalida.mostrarMensaje("3. Modificar el nombre de un producto ")
        entradaYSalida.mostrarMensaje("4. Modificar el stock de un proucto ")
        entradaYSalida.mostrarMensaje("5. Mostrar producto")
        entradaYSalida.mostrarMensaje("6. Mostrar productos con stock ")
        entradaYSalida.mostrarMensaje("7. Mostrar productos sin stock ")
        entradaYSalida.mostrarMensaje("8. Mostrar proveedor de 1 producto ")
        entradaYSalida.mostrarMensaje("9. Mostrar todos los proveedores")
        entradaYSalida.mostrarMensaje("10. Salir del programa ")
    }

    fun startProgram(){
        var canEnter = loginOrCreate()

        while (canEnter == false){
            canEnter = loginOrCreate()
        }


        var op: Int

        do {
            mostrarMenu()
            op = entradaYSalida.elegirEntreRangos(1,10)

            when(op){
                1->{
                    appManager.addProduct()
                }
                2->{
                    appManager.deleteProduct()
                }
                3->{
                    appManager.modNombreProduct()
                }
                4->{
                    appManager.modStock()
                }
                5->{
                    appManager.obtenerProducto()
                }
                6->{
                    appManager.obtenerProductosConStock()
                }
                7->{
                    appManager.obtenerProductosSinStock()
                }
                8->{
                    appManager.getProveedorDeProducto()
                }
                9-> {
                    appManager.getAllProveedores()
                }
                10->{
                    entradaYSalida.mostrarMensaje("Saliendo del programa")
                }
                else -> entradaYSalida.mostrarMensaje("Opcion no valida")
            }

        }while (op != 10)
    }

}