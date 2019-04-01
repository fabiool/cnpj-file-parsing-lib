package model

import java.util.*

class CnaeSecundaria constructor(): InfoCnpj {

    private var tipoDoRegistro: Char? = null
    private var indicadorFullDiario: Char? = null
    private var tipoAtualização: Char? = null
    private lateinit var cnpj: String
    private lateinit var cnaeSecundaria: String
    private lateinit var filler: String
    private var fimDeRegistro: Char? = null

    fun parse(source: ByteArray): CnaeSecundaria {
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