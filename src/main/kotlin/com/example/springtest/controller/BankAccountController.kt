package com.example.springtest.controller

import com.example.springtest.models.BankAccount
import com.example.springtest.service.BankService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("bank-accounts")
class BankAccountController(
    @Autowired
    private val bankService: BankService
) {

    @RequestMapping(method = [RequestMethod.GET])
    fun getAllBankAccounts(): List<BankAccount> {
        return bankService.getAll()
    }

    @RequestMapping(method = [RequestMethod.POST])
    fun addBankAccount(@RequestBody bankAccount: BankAccount): BankAccount {
        return bankService.addBankAccount(bankAccount)
    }
}