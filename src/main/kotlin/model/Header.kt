package model

import java.text.ParseException
import java.util.*
import java.util.Arrays.copyOfRange
import java.util.logging.Level
import java.util.logging.Logger

class Header constructor(): InfoCnpj {

    val LOGGER : Logger = Logger.getLogger(javaClass.toString())

    private var tipoDoRegistro : Char? = null
    private lateinit var filler01 : String
    private lateinit var nomeDoArquivo : String
    private lateinit var dataDeGravacao : Date
    private var numeroDaRemessa : Int = -1
    private lateinit var filler02 : String
    private var fimDeRegistro : Char? = null

    @Throws(ParseException::class)
    fun parse(source : ByteArray) : Header {

        return Header(
            String(copyOfRange(source, 0, 1))[0],
                String(copyOfRange(source, 0, 17)),
                String(copyOfRange(source, 17, 28)),
                parseDate(copyOfRange(source, 28, 36)),
                parseInt(copyOfRange(source, 36, 44)),
                String(copyOfRange(source, 44, 1199)),
                String(copyOfRange(source, 1199, 1200))[0]
        )
    }

    private constructor(tipoDoRegistro: Char, filler01 : String,
                        nomeDoArquivo : String, dataDeGravacao : Date,
                        numeroDaRemessa : Int, filler02: String,
                        fimDeRegistro : Char) : this() {
        this.tipoDoRegistro = tipoDoRegistro
        this.filler01 = filler01
        this.nomeDoArquivo = nomeDoArquivo
        this.dataDeGravacao = dataDeGravacao
        this.numeroDaRemessa = numeroDaRemessa
        this.filler02 = filler02
        this.fimDeRegistro = fimDeRegistro
    }
}