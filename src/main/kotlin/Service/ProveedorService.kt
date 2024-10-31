package Service

import Interfaces.ICrudProveedor
import Repository.ProveedorRepository
import model.Proveedor

class ProveedorService(
    val proveedorRepository: ICrudProveedor
) {

    fun insertProveedor(proveedor: Proveedor){
        return proveedorRepository.insertProveedor(proveedor)
    }

    fun selectProveedor(proveedorId: Long): Proveedor?{
        return proveedorRepository.selectProveedor(proveedorId)
    }

    fun selectAllProveedors(): List<Proveedor>{
        return proveedorRepository.selectAllProveedors()
    }

    fun updateProveedorDir(proveedorId: Long, newDir: String){
        return proveedorRepository.updateProveedorDir(proveedorId,newDir)
    }

    fun deleteProveedor(proveedorId: Long){
        return proveedorRepository.deleteProveedor(proveedorId)
    }


}