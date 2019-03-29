package model

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

interface InfoCnpj {
    val PATTERN : String
        get() = "yyyyMMdd"

    @Throws(ParseException::class)
    fun parseDate(source : ByteArray) : Date {
        return SimpleDateFormat(PATTERN).parse(source.toString())
    }

    fun parseInt(source: ByteArray) : Int {return Integer.parseInt(source.toString())}

    fun parseLong(source: ByteArray) : Long {return (source.toString()).toLong()}
}