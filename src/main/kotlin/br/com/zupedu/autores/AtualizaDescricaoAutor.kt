package br.com.zupedu.autores

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put

@Controller("/autores/{id}")
class AtualizaDescricaoAutor(val autorRepository: AutorRepository) {

    @Put
    fun atualizaDescricao(@PathVariable id:Long,descricao:String): HttpResponse<Any>{

        var autorEncontrado = autorRepository.findById(id)

        //buscar autor atraves do ID
        if(autorEncontrado.isEmpty){
            return HttpResponse.notFound()
        }
        var autor = autorEncontrado.get()
        autor.descricao = descricao
        //verificar se existe
        //chama update
        autorRepository.update(autor)
        return HttpResponse.ok(DetalhesDoAutorResponse(autor.nome,autor.email,autor.descricao))
    }
}