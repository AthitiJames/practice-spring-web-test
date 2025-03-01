package toptoppy.kotlin.training.springtest.service

import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import toptoppy.kotlin.training.springtest.dto.AccountBalanceResponse
import toptoppy.kotlin.training.springtest.repository.ExchangeRateRepository
import toptoppy.kotlin.training.springtest.repository.FxAccountRepository


class AccountBalanceServiceTest {

    lateinit var service: AccountBalanceService
    lateinit var exchangeRateRepository: ExchangeRateRepository
    lateinit var fxAccountRepository: FxAccountRepository

    @BeforeEach
    fun setup() {
        exchangeRateRepository = mockk(relaxed = true)
        fxAccountRepository = mockk(relaxed = true)

        service = AccountBalanceService(
            exchangeRateRepository,
            fxAccountRepository
        )
    }

    // Get account from FxAccountRepository
    // Get currency from account
    // calculate balance
    // return account balance object
    @Test
    fun `return calculated accountbalance object`() {

        val result = service.getAccountBalanceOf("A123")

        assertEquals( result,
            AccountBalanceResponse(3500.0))

        verify { exchangeRateRepository.findByCurrency("USD") }
        verify { fxAccountRepository.findByAccountId("A123") }
    }

}