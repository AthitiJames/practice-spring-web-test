package toptoppy.kotlin.training.springtest.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class FxAccountService {
    public fun getBalanceThaiBath(accountNumber: String): ResponseEntity<Unit> = ResponseEntity.ok().build()
}