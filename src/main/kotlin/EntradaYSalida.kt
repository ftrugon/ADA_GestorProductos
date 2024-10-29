class EntradaYSalida {


    fun mostrarMensaje(mensaje: String?,saltoLinea: Boolean = true){
        if (saltoLinea){
            println(mensaje)
        } else print(mensaje)
    }

    fun pedirString(mensajeImprimir: String,saltoLinea: Boolean = false): String{
        if (saltoLinea){
            println(mensajeImprimir)
        } else print(mensajeImprimir)

        return readln()
    }

    fun pedirInt(mensajeImprimir: String,saltoLinea: Boolean = false): Int{

        if (saltoLinea){
            println(mensajeImprimir)
        } else print(mensajeImprimir)

        var op: Int? = readln().toIntOrNull()

        while (op == null){
            mostrarMensaje("opcion invalida")
            op = readln().toIntOrNull()
        }

        return op
    }

    fun pedirFloat(mensajeImprimir: String,saltoLinea: Boolean = false): Float{

        if (saltoLinea){
            println(mensajeImprimir)
        } else print(mensajeImprimir)

        var op: Float? = readln().toFloatOrNull()

        while (op == null){
            mostrarMensaje("opcion invalida")
            op = readln().toFloatOrNull()
        }

        return op
    }

    fun elegirEntreRangos(min: Int,max: Int): Int{
        var op = pedirInt("")

        while (op !in min..max){
            mostrarMensaje("Opcion incorrecta")
            op = pedirInt("")
        }

        return op

    }

}