package com.marcoscoutozup.ecommercekt.usuario

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
@RequestMapping("/usuarios")
class CadastrarUsuarioController(val entityManager: EntityManager){

    val log: Logger = LoggerFactory.getLogger(CadastrarUsuarioController::class.java)

    @PostMapping
    @Transactional                                          //1
    fun cadastrarUsuario(@RequestBody @Valid request:UsuarioRequest,
                         uri: UriComponentsBuilder): ResponseEntity<Any> {

        log.info("[CRIAÇÃO DE USUÁRIO] Solicitação de criação de usuário")

                                //2
        val usuario = request.toUsuario()
        entityManager.persist(usuario);

        log.info("[CRIAÇÃO DE USUÁRIO] Usuário criado: {}", usuario.id)

        return ResponseEntity
                .created(uri.path("/usuarios/{id}")
                .buildAndExpand(usuario.id).toUri())
                .build()

    }

}
