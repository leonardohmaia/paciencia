package paciencia.model

import java.util.*

/**
 * Baralho de cartas, possui todas as 52 cartas e objeto de carta virada.
 * @author Willy G. M. Barro Raffel
 */
class Baralho {
    val cartas: Stack<NoCarta> = Stack()

    enum class ENaipe {
        COPAS, OURO, ESPADAS, PAUS
    }

    enum class ECor {
        VERMELHO, PRETO
    }

    companion object {
        val naipes: Array<Naipe> = arrayOf(
            Naipe(ENaipe.COPAS, ECor.VERMELHO),
            Naipe(ENaipe.OURO, ECor.VERMELHO),
            Naipe(ENaipe.ESPADAS, ECor.PRETO),
            Naipe(ENaipe.PAUS, ECor.PRETO)
        )

        // Carrega o icone da carta fechada
        val cartaFechada: ImageIcon = ImageIcon(ClassLoader.getSystemResource("paciencia/resources/cartas/back.png"))
    }

    init {
        // Cria as cartas
        var i = 0
        for (numCarta in 0 until 13) {
            for (naipe in naipes) {
                val nc = NoCarta(numCarta + 1, naipe)
                cartas.push(nc)
                i++
            }
        }

        // Embaralha
        embaralhar()
    }

    fun embaralhar() {
        val r = Random()
        for (i in 0 until 99999) {
            val rand1 = r.nextInt(cartas.size)
            val rand2 = r.nextInt(cartas.size)

            val aux = cartas[rand1]
            cartas[rand1] = cartas[rand2]
            cartas[rand2] = aux
        }
    }

    fun retiraCartaTopo(): NoCarta? {
        return if (cartas.isEmpty()) null else cartas.pop()
    }
}
