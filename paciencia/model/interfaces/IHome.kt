package paciencia.model.interfaces

import paciencia.exceptions.InvalidMoveException
import paciencia.model.NoCarta

/**
 * Interface dos componentes "Home"
 * Todos eles devem poder receber uma carta
 */
interface IHome {
    @Throws(InvalidMoveException::class)
    fun receberNo(carta: NoCarta): Boolean

    fun remover(nc: NoCarta)

    fun getBase(): IBase?

    fun setBase(base: IBase)
}
