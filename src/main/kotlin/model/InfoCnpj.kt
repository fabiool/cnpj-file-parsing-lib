package model

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

interface InfoCnpj {
    val PATTERN : String
        get() = "yyyyMMdd"

    @Throws(ParseException::class)
    fun parseDate(source : ByteArray) : Date {
        val sdf = SimpleDateFormat(PATTERN, Locale.getDefault())
        return sdf.parse(String(source))
    }

    fun parseInt(source: ByteArray) : Int {return Integer.parseInt(String(source))}

    fun parseLong(source: ByteArray) : Long {return (String(source).toLong())}
}