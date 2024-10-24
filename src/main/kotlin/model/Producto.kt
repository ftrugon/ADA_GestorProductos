package model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.util.Date

@Entity
@Table
class Producto(

    @Id
    val id: String,

    @Column
    val categoria: String,

    @Column
    val nombre: String,

    @Column
    val descripcion: String,

    @Column
    val precioSinIva: Float,

    @Column
    val precionConIva: Float,

    @Column
    val fecha_alta:Date,

    @Column
    val stock: Int,

    @ManyToOne
    @JoinColumn(name = "id_Proveedor")
    val proveedor: Proveedor

) {
}