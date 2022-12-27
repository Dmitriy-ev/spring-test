package com.example.springtest.controller

import com.example.springtest.models.BankAccount
import com.example.springtest.service.BankService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.intellij.lang.annotations.Language
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
internal class BankAccountControllerTest {

    @Autowired
    lateinit var bankAccountController: BankAccountController

    @Autowired
    lateinit var webMvc: MockMvc

    @MockkBean
    lateinit var bankService: BankService

    @Test
    fun `check getAll incorrect for argument`() {
        every { bankService.getAll() } throws RuntimeException("someError")

        webMvc.perform(
            get("/bank-accounts")
        ).andExpect(
            status().is4xxClientError
        ).andExpect(
            content().string("error")
        )
    }

    @Test
    fun `check getAll correct for argument`() {
        every { bankService.getAll() } returns listOf(BankAccount("someName", 13.5.toBigDecimal()))

        @Language("JSON")
        val expectedMessage = """
            [{
            "name": "someName",
            "sum": 13.5
            }]
        """.trimIndent()

        webMvc.perform(
            get("/bank-accounts")
        ).andExpect (
            content().json(expectedMessage)
        ).andExpect(
            status().isOk
        )
        verify(exactly = 1) { bankService.getAll() }
    }
}