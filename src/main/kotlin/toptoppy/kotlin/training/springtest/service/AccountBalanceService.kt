package toptoppy.kotlin.training.springtest.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import toptoppy.kotlin.training.springtest.dto.AccountBalanceResponse

@Service
class AccountBalanceService {
    fun getBalanceThaiBath(accountNumber: String): ResponseEntity<AccountBalanceResponse> =
        ResponseEntity.ok(
            AccountBalanceResponse(
                when (accountNumber) {
                    "A123" -> 3500.0
                    "B456" -> 8000.0
                    else -> 0.0
                }
            )
        )
}