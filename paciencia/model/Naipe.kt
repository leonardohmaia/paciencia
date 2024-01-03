package paciencia.model

/**
 *
 * author willy
 */
class Naipe(private val naipe: Baralho.ENaipe, private val cor: Baralho.ECor) {

    fun getNaipe(): Baralho.ENaipe {
        return naipe
    }

    fun getCor(): Baralho.ECor {
        return cor
    }
}
