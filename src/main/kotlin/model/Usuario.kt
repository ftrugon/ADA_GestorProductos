package model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import sun.security.util.Password

@Entity
@Table(name = "Usuarios")
class Usuario(

    @Id
    var nombreUsuario: String,

    @Column(nullable = false, length = 20)
    var password: String

) {
    constructor():this("","")

}