package paciencia.exceptions

import java.io.IOException

/**
 * Exception que será jogada quando algum movimento inválido for jogado
 * @author willy
 */
class InvalidMoveException(private val code: Int) : IOException() {

    companion object {
        const val GENERICO = 0
        const val LISTA_NO_FECHADO = 1
        const val LISTA_NO_INVALIDO = 2
        const val LISTA_VAZIA_APENAS_REIS = 3
        const val LISTA_SEQUENCIA_INVALIDA = 4
        const val PILHA_NO_SEQUENCIA_INVALIDA = 5
    }

    fun getCode(): Int {
        return this.code
    }

    override fun getMessage(): String {
        var msg = "<span style=\"color: #0000ff;\">Movimento Inválido</span><br />"
        when (this.code) {
            LISTA_NO_FECHADO -> msg += "Você não pode arrastar um nó fechado."
            LISTA_NO_INVALIDO -> msg += "O nó que você está tentando<br>arrastar está na ordem errada."
            LISTA_VAZIA_APENAS_REIS -> msg += "Você só pode iniciar uma lista com um REIS."
            LISTA_SEQUENCIA_INVALIDA -> msg += "Você deve alternar entre as cores e<br>seguir a sequencia: K, Q, J, 10, 9, 8, 7, 6, 5, 4, 3, 2, A."
            PILHA_NO_SEQUENCIA_INVALIDA -> msg += "Você só pode iniciar uma pilha com<br>Ases."
            GENERICO, else -> msg += "Movimento inválido."
        }

        return msg
    }
}
