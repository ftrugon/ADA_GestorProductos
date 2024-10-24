package model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table
class Proveedor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column
    var nombre: String = "",

    @Column
    var direccion: String = "",

    @OneToMany(mappedBy = "proveedor")
    var productos: List<Producto>? = null

) {
    constructor() : this(
        id = null,
        nombre = "",
        direccion = "",
        productos = null
    )
}