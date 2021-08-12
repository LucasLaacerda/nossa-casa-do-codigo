package br.com.zupedu.autores

import io.micronaut.http.HttpResponse.ok
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class BuscaDetalhesDoAutorControllerTest {
    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient


    lateinit var autor: Autor


    @BeforeEach
    fun setUp() {
        val enderecoResponse = EnderecoResponse("Rua teste","Teste","Teste")
        val endereco = Endereco(enderecoResponse,"11222-333","04",)
        autor = Autor(nome = "Rafael Ponte", email = "rafael.ponte@zup.com.br",descricao = "Teste",endereco = endereco)
        autorRepository.save(autor)
    }

    @AfterEach
    fun tearDown() {
        autorRepository.delete(autor)
    }

    @Test
    internal fun `deve buscar um autor quando um email valido eh informado`() {

        val clientResponse = client.toBlocking().exchange("/autores?email=${autor.email}",DetalhesDoAutorResponse::class.java)
        Assertions.assertEquals(HttpStatus.OK,clientResponse.status)
        assertNotNull(clientResponse.body())
        assertEquals(autor.nome,clientResponse.body()!!.nome)
        assertEquals(autor.descricao,clientResponse.body()!!.descricao)
        assertEquals(autor.email,clientResponse.body()!!.email)

    }
}