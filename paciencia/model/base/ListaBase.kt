package paciencia.model.base

import paciencia.model.home.ListaHome
import paciencia.model.interfaces.IBase
import java.awt.Point
import paciencia.helper.ResourceHelper
import paciencia.ui.PacienciaUI

/**
 * Elemento base da lista (primeiro buraco da lista, normalmente terá uma carta sobre ele)
 * @author Willy G. M. Barro Raffel
 */
class ListaBase(x: Int, y: Int, var home: ListaHome) : IBase() {

    init {
        this.x = x
        this.y = y
        this.icon = ResourceHelper.getCarta("border_half.png")
        this.setBounds(0, 0, 70, 100)
        this.location = Point(this.x, this.y)
    }

    /**
     * Retorna a próxima posição disponível para uma carta (em x,y)
     * @return Point
     */
    override fun getNextCardPoint(): Point {
        var nextY = this.baseY
        if (this.home.contarNos() > 0)
            nextY += (PacienciaUI.cardYOffset * (this.home.contarNos() + 1))

        return Point(this.baseX, nextY)
    }

    override fun getHome(): ListaHome {
        return this.home
    }
}
