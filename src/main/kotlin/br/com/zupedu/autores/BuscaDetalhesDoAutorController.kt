package br.com.zupedu.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/autores")
class BuscaDetalhesDoAutorController(val autorRepository: AutorRepository){

    // /autores/email?email@email.com.br
    @Get
    fun lista(@QueryValue(defaultValue = "") email: String): HttpResponse<Any>{

        if(email.isBlank()) {
            val autores = autorRepository.findAll()

            val resposta = autores.map { autor -> DetalhesDoAutorResponse(autor.nome,autor.email,autor.descricao) }

            return HttpResponse.ok(resposta)
        }

        val possivelAutor = autorRepository.findByEmail(email)
        if(possivelAutor.isEmpty){
            return HttpResponse.notFound()
        }
        val autorEncontrado = possivelAutor.get()
        return HttpResponse.ok(DetalhesDoAutorResponse(autorEncontrado.nome,autorEncontrado.email,autorEncontrado.descricao))
    }
}