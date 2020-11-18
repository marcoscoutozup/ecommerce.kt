package com.marcoscoutozup.ecommercekt.produto

import com.marcoscoutozup.ecommercekt.caracteristica.Caracteristica
import com.marcoscoutozup.ecommercekt.categoria.Categoria
import com.marcoscoutozup.ecommercekt.usuario.Usuario
import com.marcoscoutozup.ecommercekt.validator.registrosminimos.RegistrosMinimos
import org.hibernate.annotations.CreationTimestamp
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.*
import kotlin.math.max

@Entity
@Table(name = "tb_produto")
class Produto {

    @Id
    @GeneratedValue(generator = "uuid")
    val id: UUID? = null

    @NotBlank
    val nome: String

    @NotNull
    @Positive
    val valor: BigDecimal

    @NotNull
    @PositiveOrZero
    val quantidade: Int

    @NotBlank
    @Size(max = 1000)
    @Column(columnDefinition = "TEXT")
    val descricao: String

    @NotEmpty
    @ElementCollection
    val caracteristicas: Set<@Valid Caracteristica>

    @NotNull
    @ManyToOne
    val categoria: Categoria

    @NotNull
    @ManyToOne
    val vendedor: Usuario

    @CreationTimestamp
    val criadoEm: LocalDateTime? = null

    constructor(nome: String, valor: BigDecimal, quantidade: Int, descricao: String, caracteristicas: Set<Caracteristica>, categoria: Categoria, vendedor: Usuario) {
        this.nome = nome
        this.valor = valor
        this.quantidade = quantidade
        this.descricao = descricao
        this.caracteristicas = caracteristicas
        this.categoria = categoria
        this.vendedor = vendedor
    }
}