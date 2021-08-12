package br.com.zupedu.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.MediaType.APPLICATION_XML
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client("https://viacep.com.br/ws/")
interface EnderecoClient {

    @Get("{cep}/json/")
    fun consulta(@PathVariable cep: String) : HttpResponse<EnderecoResponse>

    //@Get("{cep}/xml/",consumes = [APPLICATION_XML])
    //fun consulta(@PathVariable cep: String) : HttpResponse<EnderecoResponse>
}