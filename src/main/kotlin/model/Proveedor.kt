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
    val id:Long? = null,

    @Column
    val nombre: String,

    @Column
    val direccion: String,

    @OneToMany(mappedBy = "proveedor")
    val productos: List<Producto>? = null

) {
}