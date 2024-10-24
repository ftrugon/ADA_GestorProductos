import jakarta.persistence.EntityManager
import jakarta.persistence.Persistence

object EntityManagerGenerator {
    val emf = Persistence.createEntityManagerFactory("unidadMySQL")

    fun generateEntityManager(): EntityManager{
        return emf.createEntityManager()
    }
}