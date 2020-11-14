package com.marcoscoutozup.ecommercekt.seguranca

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm.HS512
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtils (
        @Value("\${jwt.chave}") val chave: String,
        @Value("\${jwt.tempodeexpiracao}") val tempoDeExpiracao: Int
) {

    fun gerarToken(email: String): String = Jwts
            .builder()
            .setSubject(email)
            .setExpiration(dataDeExpiracao())
            .signWith(HS512, chave)
            .compact()

    fun dataDeExpiracao(): Date =
            Date(System.currentTimeMillis() + (tempoDeExpiracao*1000))

    fun verificarSeTokenEValido(token: String): Boolean {
        val claims = getClaims(token)
        val email = claims.subject
        val dataDeExpiracao = claims.expiration
        return email.isNotBlank() && Date().before(dataDeExpiracao)
    }

    fun getClaims(token: String): Claims =
            Jwts.parser()
                    .setSigningKey(chave)
                    .parseClaimsJws(token)
                    .body

    fun capturarEmailDoUsuarioLogado(token: String): String =
            getClaims(token).subject

}