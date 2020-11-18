package com.marcoscoutozup.ecommercekt.categoria

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/categorias")
class CadastrarCategoriaController (val entityManager: EntityManager) {

    val log: Logger = LoggerFactory.getLogger(CadastrarCategoriaController::class.java)

    @PostMapping
    @Transactional                                      //1
    fun cadastrarCategoria(@RequestBody @Valid request: CategoriaRequest,
                           uri: UriComponentsBuilder): ResponseEntity<Any> {

        log.info("[CADASTRO DE CATEGORIA] Solicitação de criação de categoria")

                                    //2
        val categoria = request.toCategoria(entityManager)
        entityManager.persist(categoria)

        log.info("[CADASTRO DE CATEGORIA] Categoria criada: {}", categoria.id)

        return ResponseEntity
                .created(uri.path("/categorias/{id}")
                .buildAndExpand(categoria.id)
                .toUri())
                .build()
    }

}