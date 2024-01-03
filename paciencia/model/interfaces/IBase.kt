package paciencia.model.interfaces

import java.awt.Point
import javax.swing.JLabel

abstract class IBase : JLabel() {
    protected var x = 0
    protected var y = 0

    abstract fun getHome(): IHome

    fun setBaseX(x: Int) {
        this.x = x
    }

    fun getBaseX(): Int {
        return x
    }

    fun setBaseY(y: Int) {
        this.y = y
    }

    fun getBaseY(): Int {
        return y
    }

    abstract fun getNextCardPoint(): Point
}
