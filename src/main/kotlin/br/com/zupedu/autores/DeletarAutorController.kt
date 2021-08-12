package br.com.zupedu.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put

@Controller("/autores/{id}")
class DeletarAutorController(val autorRepository: AutorRepository) {

    @Delete
    fun deletaAutor(@PathVariable id:Long,descricao:String): HttpResponse<Any>{

        var autorEncontrado = autorRepository.findById(id)

        //buscar autor atraves do ID
        if(autorEncontrado.isEmpty){
            return HttpResponse.notFound()
        }
        var autor = autorEncontrado.get()
        //verificar se existe
        //chama delete
        autorRepository.deleteById(id)
        return HttpResponse.ok(DetalhesDoAutorResponse(
            autor.nome,
            autor.email,
            autor.descricao
        ))
    }
}