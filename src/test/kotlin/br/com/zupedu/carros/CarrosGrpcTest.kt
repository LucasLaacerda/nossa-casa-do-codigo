package br.com.zupedu.carros

import io.micronaut.test.annotation.TransactionMode
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest(
    rollback = false,//colocar coommit no lugar do rollback
    transactionMode = TransactionMode.SINGLE_TRANSACTION,//Roda o test e o BeforeEacah na mesma transação
    transactional = false)//cada chamada de repository vai ter um commit no final

internal class CarrosGrpcTest {

    /**
     * Estrategias
     * louça: sujou,limpou -> @AfterEach
     * louça: limpou, usou -> @BeforeEach [x]
     * louça: usa louça descartavel -> rollback=true nunca suja o banco
     * louça: usa louça, joga fora, compra nova -> recriar o banco a cada teste
     */


    @Inject
    lateinit var repository: CarroRepository

    @BeforeEach
    fun setUp() {
        repository.deleteAll()
    }

    @AfterEach
    fun tearDown() {
        repository.deleteAll()
    }

    @Test
    fun `deve inserir um novo carro`(){
        //cenario
        repository.save(Carro(modelo = "Gol",placa = "NDW3389"))
        //acao

        //validacao
        assertEquals(1,repository.count())
    }//roollback como false torna o rollback em commit

    @Test
    fun `deve encontrar carro por placa`(){
        //cenario
        repository.save(Carro(modelo = "Gol",placa = "MEJ6158"))
        //acao
        val encontrado = repository.existsByPlaca("MEJ6158")
        //validacao
        assertTrue(encontrado)
    }
}