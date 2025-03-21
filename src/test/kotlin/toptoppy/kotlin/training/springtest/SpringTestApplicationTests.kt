package toptoppy.kotlin.training.springtest

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import toptoppy.kotlin.training.springtest.entity.ExchangeRateEntity
import toptoppy.kotlin.training.springtest.repository.ExchangeRateRepository
import kotlin.test.assertEquals

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class SpringTestApplicationTests (
    @Autowired
    private val exchangeRateRepository: ExchangeRateRepository,
    @Autowired
    private val mockMvc: MockMvc
) {

    companion object {
        @Container
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
        assertEquals(listOf(ExchangeRateEntity(currency="USD", sellRate=35.0, buyRate=34.5), ExchangeRateEntity(currency="EUR", sellRate=40.0, buyRate=39.5), ExchangeRateEntity(currency="JPY", sellRate=0.3, buyRate=0.29)), exchangeRateRepository.findAll())
    }

    @Test
    fun `should return balance of the given account number in THB` (){
        // Given
        val accountNumber = "A123"
        val accountNumber2 = "B456"

        // When & Then
        mockMvc.get("/fx/$accountNumber/balance-thb") {
        }.andExpect {
            status {isOk()}
            jsonPath("$.balanceInTHB") { value(3500.0) }
        }
        mockMvc.get("/fx/$accountNumber2/balance-thb") {
        }.andExpect {
            status {isOk()}
            jsonPath("$.balanceInTHB") { value(8000.0) }
        }

    }

}
