package com.example.springtest.service

import com.example.springtest.models.BankAccount
import org.springframework.stereotype.Service

@Service
class BankService(
    private val bankAccounts: MutableList<BankAccount> = mutableListOf()
) {

    fun addBankAccount(bankAccount: BankAccount): BankAccount {
        return bankAccount.also {
            bankAccounts.add(bankAccount)
        }
    }

    fun getAll(): List<BankAccount> {
        return bankAccounts
    }
}