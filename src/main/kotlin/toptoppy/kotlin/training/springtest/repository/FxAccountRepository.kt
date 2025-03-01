package toptoppy.kotlin.training.springtest.repository

import org.springframework.data.jpa.repository.JpaRepository
import toptoppy.kotlin.training.springtest.entity.FxAccountEntity
import java.util.*

interface FxAccountRepository : JpaRepository<FxAccountEntity, String> {
    fun findByAccountId(accountId: String): Optional<FxAccountEntity>
}