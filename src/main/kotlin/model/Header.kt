package model

import java.text.ParseException
import java.util.*
import java.util.Arrays.copyOfRange
import java.util.logging.Level
import java.util.logging.Logger

class Header constructor(): InfoCnpj {

    val LOGGER : Logger = Logger.getLogger(javaClass.toString())

    private var registryType : Char? = null
    private lateinit var filler01 : String
    private lateinit var fileName : String
    private lateinit var recordDate : Date
    private var shippingNumber : Int = -1
    private lateinit var filler02 : String
    private var registryEnd : Char? = null

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

    private constructor(registryType: Char, filler01 : String,
                        fileName : String, recordDate : Date,
                        shippingNumber : Int, filler02: String,
                        registryEnd : Char) : this() {
        this.registryType = registryType
        this.filler01 = filler01
        this.fileName = fileName
        this.recordDate = recordDate
        this.shippingNumber = shippingNumber
        this.filler02 = filler02
        this.registryEnd = registryEnd
    }
}