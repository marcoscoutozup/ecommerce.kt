package com.marcoscoutozup.ecommercekt.categoria

import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "tb_categoria")
class Categoria {

    @Id
    @GeneratedValue(generator = "uuid")
    val id: UUID? = null

    @NotBlank
    @Column(unique = true)
    val nome: String

    @OneToOne
    var categoria: Categoria? = null

    constructor(nome: String) {
        this.nome = nome
    }

    fun associarSuperCategoria(categoria: Categoria){
        this.categoria = categoria
    }

}



