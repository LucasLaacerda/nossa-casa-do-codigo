package br.com.zupedu.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraAutorController(
    val autorRepository: AutorRepository,
    val enderecoClient: EnderecoClient
) {

    @Post
    fun cadastra(@Body @Valid request: NovoRequest) : HttpResponse<Any>{
        println(request)

        val enderecoResponse = enderecoClient.consulta(request.cep)

        if(enderecoResponse.body() == null){
            return HttpResponse.badRequest()
        }

        val autor = request.toModel(enderecoResponse.body()!!) //Garante que o body possui algo
        autorRepository.save(autor)

        val uri = UriBuilder.of("/autores/{id}")
            .expand(mutableMapOf(Pair("id",autor.id)))
        return HttpResponse.created(uri)
    }

}