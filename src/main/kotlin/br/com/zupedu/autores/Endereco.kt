package br.com.zupedu.autores

import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime
import javax.persistence.Embeddable
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Embeddable
class Endereco(enderecoResponse: EnderecoResponse, val cep: String, val numero: String) {

    val rua = enderecoResponse.rua
    val cidade = enderecoResponse.cidade
    val estado = enderecoResponse.estado

//    @Id
//    @GeneratedValue
//    var id: Long? = null
//
//    val criadoEm: LocalDateTime = LocalDateTime.now()
}
