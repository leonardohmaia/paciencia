package paciencia.model.home

import paciencia.exceptions.InvalidMoveException
import paciencia.model.NoCarta
import paciencia.model.interfaces.IBase
import paciencia.model.interfaces.IHome
import java.util.*

/**
 * Classe de monte de cartas.
 * Os montes possuem 3 cartas e são mostrados no topo esquerdo do jogo.
 *
 * @author Willy G. M. Barro Raffel
 */
class MonteHome : IHome {
    private val montes: MutableList<Stack<NoCarta>> = ArrayList()

    override fun receberNo(carta: NoCarta): Boolean {
        return false
    }

    override fun remover(nc: NoCarta) {
        var i = 0
        var monteRemover: Stack<NoCarta>? = null
        for (monte in montes) {
            if (monte.contains(nc)) monteRemover = monte
            i++
        }

        // Remove a carta do monte, se ele esvaziar, remove o monte do array original
        if (monteRemover != null) {
            monteRemover.remove(nc)

            if (monteRemover.size > 0) {
                val carta = monteRemover[monteRemover.size - 1]
                carta.isOpen = true
            } else {
                montes.remove(monteRemover)
            }
        }
    }

    private var monteAtual: Stack<NoCarta>? = null
    fun inserir(nc: NoCarta) {
        if (monteAtual == null || monteAtual!!.size == 3) {
            monteAtual = Stack()
            montes.add(monteAtual!!)
        }

        nc.home = this
        monteAtual!!.add(nc)
    }

    private var numMonteAtivo = 0
    fun retira3Cartas(): Stack<NoCarta>? {
        if (montes.isEmpty()) return null

        numMonteAtivo = (numMonteAtivo + 1) % montes.size
        return montes[numMonteAtivo]
    }

    fun getNumMonteAtivo(): Int {
        return numMonteAtivo
    }

    fun getMontes(): List<Stack<NoCarta>> {
        return montes
    }

    override fun getBase(): IBase? {
        return null
    }

    override fun setBase(base: IBase) {
        // Do nothing
    }
}
