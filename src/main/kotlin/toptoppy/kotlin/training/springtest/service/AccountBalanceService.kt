package toptoppy.kotlin.training.springtest.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import toptoppy.kotlin.training.springtest.dto.AccountBalanceResponse
import toptoppy.kotlin.training.springtest.repository.ExchangeRateRepository

@Service
class AccountBalanceService(

    private val exchangeRateRepository: ExchangeRateRepository) {

    fun getAccountBalanceOf(accountNumber: String): AccountBalanceResponse {
        exchangeRateRepository.findByCurrency("USD")
        return AccountBalanceResponse(
            if (accountNumber == "A123") 3500.0 else 8000.0
        )
    }
}