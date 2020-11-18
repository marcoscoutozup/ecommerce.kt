package com.marcoscoutozup.ecommercekt.produto

import com.marcoscoutozup.ecommercekt.seguranca.JwtUtils
import com.marcoscoutozup.ecommercekt.usuario.CadastrarUsuarioController
import com.marcoscoutozup.ecommercekt.usuario.Usuario
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/produtos")
class CadastrarProdutoController (val entityManager: EntityManager,
                                  val jwtUtils: JwtUtils) {

    val log: Logger = LoggerFactory.getLogger(CadastrarProdutoController::class.java)

    @PostMapping
    @Transactional
    fun cadastrarNovoProduto(@RequestBody @Valid request: ProdutoRequest,
                             @RequestHeader("Authorization") token: String,
                             uri: UriComponentsBuilder): ResponseEntity<Any>{

        log.info("[CADASTRO DE PRODUTO] Solicitação de cadastro de produto")

        val email = jwtUtils.capturarEmailDoUsuarioLogado(token.substring(7))

        val vendedor = entityManager.createNamedQuery("findUsuarioByEmail", Usuario::class.java)
                .setParameter("email", email)
                .resultList

        if(vendedor.isEmpty()){
            log.info("[CADASTRO DE PRODUTO] Usuário não encontrado")
            var response: HashMap<String, String> = HashMap()
            response["message"] = "Usuário não encontrado"
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response)
        }

        val produto = request.toProduto(vendedor[0], entityManager)
        entityManager.persist(produto)

        log.info("[CADASTRO DE PRODUTO] Produto cadastrado: {}", produto.id)

        return ResponseEntity
                .created(uri.path("/produtos/{id}")
                .buildAndExpand(produto.id)
                .toUri())
                .build()
    }

}