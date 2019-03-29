package model

import java.lang.IllegalArgumentException
import java.text.ParseException

class RecordFactory {

    @Throws(ParseException::class)
    fun getRecord(source: ByteArray): InfoCnpj {

        when (source[0]) {
            48.toByte() -> return Header().parse(source)
            //        1.toByte() -> return DadosCadastrais.parse(source)
            //        2.toByte() -> return Socio.parse(source)
            //        6.toByte() -> return CnaeSecundaria.parse(source)
            //        9.toByte() -> return Trailler.parse(source)
            else -> {
                throw IllegalArgumentException("Unknown type of registry")
            }

        }
    }
}