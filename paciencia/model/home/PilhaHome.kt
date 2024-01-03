package paciencia.model.home

import paciencia.exceptions.InvalidMoveException
import paciencia.model.NoCarta
import paciencia.model.interfaces.IBase
import paciencia.model.interfaces.IHome

/**
 *
 * @author willy
 */
class PilhaHome(nome: String) : PilhaCarta(nome), IHome {
    var x = 0
    var y = 0
    private var base: IBase? = null

    init {
        this.cartas = arrayOfNulls(13)
    }

    override fun setBase(base: IBase) {
        this.base = base
    }

    override fun getBase(): IBase? {
        return this.base
    }

    /**
     * Insere uma carta no topo da pilha de cartas.
     * Só podemos inserir a carta se ela for um A (se a pilha for vazia)
     * ou se ela for a proxima carta da sequencia.
     *
     * @param carta
     * @return
     */
    @Throws(InvalidMoveException::class)
    override fun receberNo(carta: NoCarta): Boolean {
        val cartaTopo = this.elementoTopo()
        if ((this.pilhaVazia() && carta.numero == 1) ||
            (!this.pilhaVazia() &&
                    cartaTopo.numero == carta.numero - 1 &&
                    cartaTopo.naipe == carta.naipe)
        ) {
            val homeFrom = carta.home as IHome
            homeFrom.remover(carta)
            carta.home = this
            return this.empilhar(carta)
        }

        // Movimento inválido
        throw InvalidMoveException(InvalidMoveException.PILHA_NO_SEQUENCIA_INVALIDA)
    }

    /**
     * Remove uma carta do topo do baralho (se disponível)
     *
     * @return Retorna a ultima carta do baralho
     */
    override fun remover(nc: NoCarta) {
        this.desempilhar()
    }
}
