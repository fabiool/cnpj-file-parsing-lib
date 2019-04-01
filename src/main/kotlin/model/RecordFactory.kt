package model

import com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER
import java.lang.IllegalArgumentException
import java.text.ParseException
import java.util.logging.Level

class RecordFactory {

    @Throws(ParseException::class)
    fun getRecord(source: ByteArray): InfoCnpj {

        when (source[0]) {
            48.toByte() -> return Header().parse(source)
            49.toByte() -> return DadosCadastrais().parse(source)
            50.toByte() -> return Socio().parse(source)
            54.toByte() -> return CnaeSecundaria().parse(source)
            57.toByte() -> return Trailler().parse(source)
            else -> {
                throw IllegalArgumentException("Unknown type of registry")
            }

        }
    }
}