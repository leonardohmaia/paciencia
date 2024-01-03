package paciencia.model.base

import paciencia.model.NoCarta

/**
 * @author Willy G. M. Barro Raffel
 */
class ListaCarta(private val nome: String) {
    var primeiro: NoCarta? = null
    var ultimo: NoCarta? = null

    fun listaVazia(): Boolean {
        return primeiro == null
    }

    protected fun inserirInicio(novoNo: NoCarta) {
        if (listaVazia()) {
            ultimo = novoNo
        } else {
            novoNo.prox = primeiro
        }

        primeiro = novoNo
    }

    protected fun inserirFinal(novoNo: NoCarta) {
        var novoUlt = novoNo
        if (listaVazia()) {
            primeiro = novoNo
        } else {
            var aux = primeiro
            while (aux?.prox != null) {
                aux = aux.prox
            }
            aux?.prox = novoNo
        }

        ultimo = novoUlt
    }

    fun contarNos(): Int {
        var i = 0
        var aux = primeiro
        while (aux != null) {
            aux = aux.prox
            i++
        }

        return i
    }

    protected fun inserirMeio(novoNo: NoCarta, posicao: Int) {
        val noTemp = primeiro
        val nroNos = contarNos()
        var posAux = 1

        if (nroNos <= 1) {
            inserirInicio(novoNo)
        } else {
            if (posicao > nroNos) {
                inserirFinal(novoNo)
            }
        }
    }

    protected fun buscarNo(nc: NoCarta): Int {
        var pos = 0
        var aux = primeiro
        while (aux != null) {
            pos++
            if (aux == nc)
                return pos

            aux = aux.prox
        }

        return 0
    }

    protected fun buscarNo(num: Int): NoCarta? {
        var pos = 0
        var aux = primeiro
        while (pos < num) {
            pos++
            if (pos == num)
                return aux

            aux = aux?.prox
        }

        return null
    }

    fun recuperaNo(num: Int): NoCarta? {
        var pos = 1
        var aux = primeiro
        while (aux != null) {
            if (pos == num)
                return aux

            aux = aux.prox
            pos++
        }

        return null
    }

    protected fun remover(nc: NoCarta) {
        val pos = buscarNo(nc)
        var aux = primeiro

        if (pos > 0) {
            if (pos == 1) {
                primeiro = null
                ultimo = null
            } else {
                for (i in 1 until pos - 1) {
                    aux = aux?.prox
                }
                aux?.prox = null
                ultimo = aux
            }
        }
    }

    fun elementoInicio(): NoCarta? {
        return if (!listaVazia()) {
            primeiro
        } else {
            null
        }
    }

    fun elementoFinal(): NoCarta? {
        return if (!listaVazia()) {
            ultimo
        } else {
            null
        }
    }

    fun getNome(): String {
        return nome
    }

    override fun toString(): String {
        return toString(false)
    }

    fun toString(showName: Boolean): String {
        var cards = ""
        if (showName) {
            cards += "$nome: "
        }
        var aux = primeiro
        while (aux != null) {
            cards += "${aux.numero}${aux.naipe.naipe.name},"
            aux = aux.prox
        }
        return cards
    }
}
