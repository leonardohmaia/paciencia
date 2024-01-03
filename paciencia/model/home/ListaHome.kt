package paciencia.model.base

import paciencia.model.NoCarta

class PilhaCarta(private val nome: String) {
    private var topo = -1
    protected lateinit var cartas: Array<NoCarta?>

    init {
        this.cartas = arrayOfNulls(52)
    }

    fun pilhaVazia(): Boolean {
        return topo < 0
    }

    fun pilhaCheia(): Boolean {
        return topo >= cartas.size - 1
    }

    fun elementoTopo(): NoCarta? {
        return if (!pilhaVazia()) {
            cartas[topo]
        } else {
            null
        }
    }

    protected fun empilhar(elemento: NoCarta): Boolean {
        return if (pilhaCheia()) {
            false
        } else {
            cartas[++topo] = elemento
            true
        }
    }

    protected fun desempilhar(): NoCarta? {
        return if (pilhaVazia()) {
            null
        } else {
            cartas[topo--]
        }
    }

    fun mostrar() {
        var i = topo
        while (i >= 0) {
            println(i)
            i--
        }
    }

    fun getNome(): String {
        return nome
    }
}
