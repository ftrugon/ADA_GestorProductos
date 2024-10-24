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
    val nombreUsuario: String,

    @Column(nullable = false, length = 20)
    val password: String

) {
}