package toptoppy.kotlin.training.springtest.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import toptoppy.kotlin.training.springtest.dto.AccountBalanceResponse
import toptoppy.kotlin.training.springtest.service.FxAccountService

@RestController
@RequestMapping(value = ["/fx"])
class FxAccountController (
    @Autowired
    fxAccountService: FxAccountService
){

    @GetMapping("/{accountNumber}/balance-thb")
    fun getBalanceThaiBath(@PathVariable accountNumber: String): ResponseEntity<AccountBalanceResponse> =
        ResponseEntity.ok(AccountBalanceResponse(
            when (accountNumber){
                "A123" -> 3500.0
                "B456" -> 8000.0
                else -> 0.0
            }
        ))
}