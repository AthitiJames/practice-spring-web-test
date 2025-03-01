package toptoppy.kotlin.training.springtest.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import toptoppy.kotlin.training.springtest.dto.AccountBalanceResponse
import toptoppy.kotlin.training.springtest.service.AccountBalanceService

@RestController
@RequestMapping(value = ["/fx"])
class FxAccountController (
    private val fxAccountService: AccountBalanceService
){

    @GetMapping("/{accountNumber}/balance-thb")
    fun getBalanceByAccountNumber(@PathVariable accountNumber: String): AccountBalanceResponse = fxAccountService.getAccountBalanceOf(accountNumber)
}