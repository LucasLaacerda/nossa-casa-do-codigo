package br.com.zupedu.autores

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpRequest.POST
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import javax.inject.Inject

@MicronautTest
internal class CadastraAutorControllerTest{

    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @Test
    fun `deve cadastrar um novo autor`(){
        //cenario
        val novoAutorRequest = NovoRequest(
            nome = "Rafael Ponte",
            email = "rafael.ponte@zup.com.br",
            descricao = "Teste",
            cep = "11222-33",
            numero = "37"
        )

        val enderecoResponse = EnderecoResponse("Rua teste", "Teste", "Teste")

        Mockito.`when`(enderecoClient.consulta(novoAutorRequest.cep)).thenReturn(HttpResponse.ok(enderecoResponse))

        val request = HttpRequest.POST("/autores",novoAutorRequest)
        //acao
        val response = client.toBlocking().exchange(request,Any::class.java)

        assertEquals(HttpStatus.CREATED,response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location")!!.matches("/autores/\\d+".toRegex()))
    }

    @MockBean(EnderecoClient::class)
    fun enderecoMock(): EnderecoClient{
        return Mockito.mock(EnderecoClient::class.java)
    }

}