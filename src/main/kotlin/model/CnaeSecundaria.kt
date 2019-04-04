package model

import com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER
import java.util.*
import java.util.logging.Level

class CnaeSecundaria constructor(): InfoCnpj {

    var tipoDoRegistro: Char? = null
    var indicadorFullDiario: Char? = null
    var tipoAtualização: Char? = null
    lateinit var cnpj: String
    lateinit var cnaeSecundaria: String
    lateinit var filler: String
    var fimDeRegistro: Char? = null

    fun parse(source: ByteArray): CnaeSecundaria {

        LOGGER.log(Level.INFO, "Trying to parse CnaeSecundaria instance")

        return CnaeSecundaria(
                String(Arrays.copyOfRange(source, 0, 1))[0],
                String(Arrays.copyOfRange(source, 1, 2))[0],
                String(Arrays.copyOfRange(source, 2, 3))[0],
                String(Arrays.copyOfRange(source, 3, 17)),
                String(Arrays.copyOfRange(source, 17, 488)),
                String(Arrays.copyOfRange(source, 488, 1199)),
                String(Arrays.copyOfRange(source, 1199, 1200))[0]
        )
    }

    constructor(tipoDoRegistro: Char, indicadorFullDiario: Char,
                tipoAtualização: Char, cnpj: String,
                cnaeSecundaria: String, filler: String,
                fimDeRegistro: Char) : this() {
        this.tipoDoRegistro = tipoDoRegistro
        this.indicadorFullDiario = indicadorFullDiario
        this.tipoAtualização = tipoAtualização
        this.cnpj = cnpj
        this.cnaeSecundaria = cnaeSecundaria
        this.filler = filler
        this.fimDeRegistro = fimDeRegistro
    }
}