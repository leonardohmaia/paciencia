/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paciencia.helper

import javax.swing.ImageIcon

/**
 *
 * @author willy
 */
object ResourceHelper {

    fun getCarta(nomeArquivo: String): ImageIcon {
        return ImageIcon(ClassLoader.getSystemResource("paciencia/resources/cartas/$nomeArquivo"))
    }

    fun getUi(nomeArquivo: String): ImageIcon {
        return ImageIcon(ClassLoader.getSystemResource("paciencia/resources/ui/$nomeArquivo"))
    }
}
