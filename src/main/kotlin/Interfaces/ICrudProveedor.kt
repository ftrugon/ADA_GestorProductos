package Interfaces

import model.Proveedor

interface ICrudProveedor {

    fun insertProveedor(proveedor: Proveedor)

    fun selectProveedor(proveedorId: Long): Proveedor?

    fun selectAllProveedors(): List<Proveedor>

    fun updateProveedorDir(proveedorId: Long, newDir: String)

    fun deleteProveedor(proveedorId: Long)

}