package paciencia.model.base

import paciencia.model.interfaces.IBase
import java.awt.Point
import paciencia.helper.ResourceHelper
import paciencia.model.home.PilhaHome

/**
 * Elemento base da pilha.
 * @author Willy G. M. Barro Raffel
 */
class PilhaBase(x: Int, y: Int, var home: PilhaHome) : IBase() {

    init {
        this.x = x
        this.y = y
        this.icon = ResourceHelper.getCarta("border_full.png")
        this.setBounds(0, 0, 70, 100)
        this.location = Point(this.x, this.y)
    }

    /**
     * Retorna a próxima posição disponível para uma carta (em x,y)
     * @return Point
     */
    override fun getNextCardPoint(): Point {
        return Point(this.x, this.y)
    }

    override fun getHome(): PilhaHome {
        return this.home
    }
}
