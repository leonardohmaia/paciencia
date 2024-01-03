import javax.swing.JFrame
import javax.swing.JOptionPane
import javax.swing.UIManager
import paciencia.model.Baralho
import paciencia.model.NoCarta
import paciencia.model.home.ListaHome
import paciencia.model.home.MonteHome
import paciencia.model.home.PilhaHome

fun main() {
    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())

        // Inicia o jogo
        novoJogo()
    } catch (e: Exception) {
        JOptionPane.showMessageDialog(null, "Ocorreu algum erro ao iniciar o jogo.\nSeu sistema operacional não é suportado.")
    }
}

fun novoJogo() {
    // Se já existir um ui criado, remover
    // @TODO trocar
    if (Paciencia.f != null)
        Paciencia.f.dispose()

    // Inicia o jogo
    Paciencia()
}

class Paciencia {
    private val baralho: Baralho
    private val bases = Array(4) { PilhaHome("Pilha ${it + 1}") }
    private val listas = Array(7) { ListaHome("Lista ${it + 1}") }
    private val monteHome: MonteHome
    companion object {
        lateinit var f: JFrame
    }

    init {
        // Inicia o jogo
        baralho = Baralho()

        // Cria as pilhas base (que terão as cartas finais montadas)
        bases[0] = PilhaHome("Pilha 1")
        bases[1] = PilhaHome("Pilha 2")
        bases[2] = PilhaHome("Pilha 3")
        bases[3] = PilhaHome("Pilha 4")

        // Inicia as listas
        iniciaMonte()
        iniciaListas()

        // Inicia a UI
        f = paciencia.ui.PacienciaUI(this)
        f.isVisible = true
    }

    /**
     * Inicia os montes de carta
     */
    private fun iniciaMonte() {
        // Separa os 8 montes de 3 cartas que não vão pras listas
        monteHome = MonteHome()
        repeat(24) {
            val carta = baralho.retiraCartaTopo()
            monteHome.inserir(carta)
        }
    }

    /**
     * Inicia as listas de cartas
     */
    private fun iniciaListas() {
        repeat(7) { i ->
            val lista = ListaHome("Lista ${i + 1}")

            // Retira do baralho e receberNo nas listas de cartas
            repeat(i + 1) {
                val nc = baralho.retiraCartaTopo()
                nc.setHome(lista)
                lista.inserir(nc)
            }

            listas[i] = lista
        }
    }

    /**
     * Valida se a carta1 é a sequencia da carta2 (crescentemente).
     *
     * @param carta1
     * @param carta2
     * @return
     */
    fun cartaSequenciaValida(carta1: NoCarta?, carta2: NoCarta?): Boolean {
        if (carta1 == null || carta2 == null)
            return false

        return ((carta1.getNumero() == carta2.getNumero() + 1)
                && (carta1.getNaipe().getCor() != carta2.getNaipe().getCor()))
    }

    fun getListas(): Array<ListaHome> {
        return listas
    }

    fun getMonteHome(): MonteHome {
        return monteHome
    }

    fun getBases(): Array<PilhaHome> {
        return bases
    }

    fun getBaralho(): Baralho {
        return baralho
    }

    /**
     * Verifica se o jogo acabou.
     * O jogo termina quando o topo de todas as pilhas-base são K
     */
    fun verificaFimDeJogo() {
        var numK = 0
        bases.forEach { ph ->
            if (ph.elementoTopo() != null && ph.elementoTopo().getNumRep() == "K") {
                numK++
            }
        }

        if (numK == 4) {
            JOptionPane.showMessageDialog(null, "Parabéns, você terminou o jogo!")
            novoJogo()
        }
    }
}
