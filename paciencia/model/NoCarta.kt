package paciencia.model

import paciencia.model.interfaces.IHome
import java.awt.Cursor
import javax.swing.ImageIcon
import javax.swing.JLabel
import paciencia.helper.ResourceHelper

/**
 *
 * author Willy G. M. Barro Raffel
 */
class NoCarta(private val numero: Int, private val naipe: Naipe) : JLabel() {
    private var prox: NoCarta? = null
    private var aberta = false
    private var draggable = false

    /**
     * Instancia da classe pai (home), pode ser uma ListaHome ua PilhaHome ou Baralho
     */
    private var home: IHome? = null

    private val openedIcon: ImageIcon = ResourceHelper.getCarta("${getNumRep().toLowerCase()}-${getNaipeRep()}.png")

    init {
        this.setBounds(0, 0, 70, 100)
        this.setOpen(false)
    }

    fun getCountProx(): Int {
        var totalProx = 0
        var aux: NoCarta? = this
        while (aux?.getProx() != null) {
            totalProx++
            aux = aux.getProx()
        }

        return totalProx
    }

    fun getProx(): NoCarta? {
        return this.prox
    }

    fun setProx(prox: NoCarta?) {
        this.prox = prox
    }

    fun isOpen(): Boolean {
        return this.aberta
    }

    fun isDraggable(): Boolean {
        return this.draggable
    }

    fun setOpen(open: Boolean) {
        setOpen(open, true)
    }

    fun setDraggable(draggable: Boolean) {
        this.draggable = draggable
    }

    fun setOpen(open: Boolean, draggable: Boolean) {
        if (open) {
            this.icon = openedIcon
            this.cursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
        } else {
            this.icon = Baralho.cartaFechada
            this.cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)
        }

        this.aberta = open
        setDraggable(draggable)
    }

    fun getNumero(): Int {
        return this.numero
    }

    /**
     * Recupera a representação do número da carta, por ex:
     * Ao invés de 13, 12, 11, 1 será retornado K, Q, J, A
     *
     * @return String
     */
    fun getNumRep(): String {
        return when (this.numero) {
            1 -> "A"
            11 -> "J"
            12 -> "Q"
            13 -> "K"
            else -> this.numero.toString()
        }
    }

    fun getNaipeRep(): String {
        return when (this.naipe.getNaipe()) {
            Naipe.ENaipe.COPAS -> "c"
            Naipe.ENaipe.ESPADAS -> "e"
            Naipe.ENaipe.OURO -> "o"
            Naipe.ENaipe.PAUS -> "p"
        }
    }

    fun getNaipe(): Naipe {
        return this.naipe
    }

    /**
     * Mostra uma representação do objeto da carta no padrão: "A de COPAS"
     *
     * @return String
     */
    override fun toString(): String {
        return "${getNumRep()} de ${this.naipe.getNaipe().name}"
    }

    /**
     * Seta a home da carta (e também de todos as cartas filhas).
     * Pode ser uma ListaHome, PilhaHome ou Baralho
     * @param home
     */
    fun setHome(home: IHome?) {
        this.home = home

        this.getProx()?.setHome(home)
    }

    fun getHome(): IHome? {
        return this.home
    }
}
