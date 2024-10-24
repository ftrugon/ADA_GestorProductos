import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence

object EntityManagerGenerator {
    var emf = Persistence.createEntityManagerFactory("unidadMySQL")

    /*
    fun getEmf(namePersistence: String): EntityManagerFactory {
        return if(this::emf.isInitialized && emf.isOpen) {
            emf
        } else {
            emf = Persistence.createEntityManagerFactory(namePersistence)
            emf
        }

    }*/

    fun generateEntityManager(): EntityManager{
        return emf.createEntityManager()
    }
}