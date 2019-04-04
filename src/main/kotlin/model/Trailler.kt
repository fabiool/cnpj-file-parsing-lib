package model

import com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER
import java.util.Arrays.copyOfRange
import java.util.logging.Level

class Trailler constructor() : InfoCnpj {
    var tipoDoRegistro: Char? = null
    lateinit var filler01: String
    var totalRegistrosT1: Int = -1
    var totalRegistrosT2: Int = -1
    var totalRegistrosT3: Int = -1
    var totalRegistros: Long = -1L
    lateinit var filler02: String
    var fimDeRegistro: Char? = null

    fun parse(source: ByteArray): Trailler {

        LOGGER.log(Level.INFO, "Trying to parse Trailler instance")

        return Trailler(
                String(copyOfRange(source, 0, 1))[0],
                String(copyOfRange(source, 1, 17)),
                parseInt(copyOfRange(source, 17, 26)),
                parseInt(copyOfRange(source, 26, 35)),
                parseInt(copyOfRange(source, 35, 44)),
                parseLong(copyOfRange(source, 44, 55)),
                String(copyOfRange(source, 55, 1199)),
                String(copyOfRange(source, 1199, 1200))[0]
        )
    }

    constructor(tipoDoRegistro: Char, filler01: String,
                totalRegistrosT1: Int, totalRegistrosT2: Int,
                totalRegistrosT3: Int, totalRegistros: Long,
                filler02: String, fimDeRegistro: Char): this() {
        this.tipoDoRegistro = tipoDoRegistro
        this.filler01 = filler01
        this.totalRegistrosT1 = totalRegistrosT1
        this.totalRegistrosT2 = totalRegistrosT2
        this.totalRegistrosT3 = totalRegistrosT3
        this.totalRegistros = totalRegistros
        this.filler02 = filler02
        this.fimDeRegistro = fimDeRegistro
    }

}