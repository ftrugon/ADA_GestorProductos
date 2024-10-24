package model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import model.Usuario
import java.util.Date

@Entity
@Table
class Producto(

    @Id
    val id: String = "",

    @Column
    var categoria: String = "",

    @Column
    var nombre: String = "",

    @Column
    var descripcion: String = "",

    @Column
    var precioSinIva: Float = 0.0f,

    @Column
    var precionConIva: Float = 0.0f,

    @Column
    val fecha_alta: Date = Date(),

    @Column
    var stock: Int = 0,

    @ManyToOne
    @JoinColumn(name = "id_Proveedor")
    var proveedor: Proveedor? = null

) {
    constructor() : this(
        id = "",
        categoria = "",
        nombre = "",
        descripcion = "",
        precioSinIva = 0.0f,
        precionConIva = 0.0f,
        fecha_alta = Date(),
        stock = 0,
        proveedor = null
    )
}