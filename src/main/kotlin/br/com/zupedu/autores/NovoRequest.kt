package br.com.zupedu.autores

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NovoRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max = 400) val descricao: String,
    @field:NotBlank val cep: String,
    @field:NotBlank val numero: String
) {
    fun toModel(enderecoResponse: EnderecoResponse): Autor {

        val endereco = Endereco(enderecoResponse,cep,numero)
        return Autor(nome,email,descricao,endereco)
    }
}