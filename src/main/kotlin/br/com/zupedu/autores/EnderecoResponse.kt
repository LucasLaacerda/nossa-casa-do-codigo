package br.com.zupedu.autores

import com.fasterxml.jackson.annotation.JsonProperty
import io.micronaut.core.annotation.Introspected

class EnderecoResponse(@JsonProperty("logradouro") val rua: String,
                       @JsonProperty("localidade") val cidade: String,
                       @JsonProperty("uf") val estado: String)  {

}
