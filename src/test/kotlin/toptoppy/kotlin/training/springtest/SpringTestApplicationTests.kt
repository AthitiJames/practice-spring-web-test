package toptoppy.kotlin.training.springtest

import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@Testcontainers
@SpringBootTest
class SpringTestApplicationTests {

    companion object{
        private val psql = PostgreSQLContainer(DockerImageName.parse("postgres:16.4"))

        @DynamicPropertySource
        @JvmStatic
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", psql::getJdbcUrl)
            registry.add("spring.datasource.username", psql::getUsername)
            registry.add("spring.datasource.password", psql::getPassword)
        }
    }

    @Test
    fun contextLoads() {
//        assertEquals(exchangeRateRepository.findAll(), listOf())
    }

}
