package model

import com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER
import java.util.Arrays.copyOfRange
import java.util.logging.Level

class Socio constructor() : InfoCnpj {
    private var tipoDoRegistro: Char? = null
    private var indicadorFullDiario: Char? = null
    private var tipoAtualização: Char? = null
    private lateinit var cnpj: String
    private var identificadorDeSocio: Char? = null
    private lateinit var nomeSocio: String
    private lateinit var cnpjCpf: String
    private lateinit var qualificacaoSocio: String
    private lateinit var percentualCapitalSocial: String
    private lateinit var dataEntradaSociedade: String
    private lateinit var codigoPais: String
    private lateinit var nomePaisSocio: String
    private lateinit var cpfRepresentanteLegal: String
    private lateinit var nomeRepresentante: String
    private lateinit var qualificacaoRepresentante: String
    private lateinit var filler: String
    private var fimDeRegistro: Char? = null

    fun parse(source: ByteArray) : Socio {

        LOGGER.log(Level.INFO, String.format("Trying to parse Socio instance - %s ", String(copyOfRange(source, 18, 168))))

        return Socio(
                String(copyOfRange(source, 0, 1))[0],
                String(copyOfRange(source, 1, 2))[0],
                String(copyOfRange(source, 2, 3))[0],
                String(copyOfRange(source, 3, 17)),
                String(copyOfRange(source, 17, 18))[0],
                String(copyOfRange(source, 18, 168)),
                String(copyOfRange(source, 168, 182)),
                String(copyOfRange(source, 182, 184)),
                String(copyOfRange(source, 184, 189)),
                String(copyOfRange(source, 189, 197)),
                String(copyOfRange(source, 197, 200)),
                String(copyOfRange(source, 200, 270)),
                String(copyOfRange(source, 270, 281)),
                String(copyOfRange(source, 281, 341)),
                String(copyOfRange(source, 341, 343)),
                String(copyOfRange(source, 343, 1198)),
                String(copyOfRange(source, 1198, 1199))[0]
        )
    }

    constructor(tipoDoRegistro: Char, indicadorFullDiario: Char,
              tipoAtualização: Char, cnpj: String,
              identificadorDeSocio: Char, nomeSocio: String,
              cnpjCpf: String, qualificacaoSocio: String,
              percentualCapitalSocial: String, dataEntradaSociedade: String,
              codigoPais: String, nomePaisSocio: String,
              cpfRepresentanteLegal: String, nomeRepresentante: String,
              qualificacaoRepresentante: String, filler: String,
              fimDeRegistro: Char): this() {
        this.tipoDoRegistro = tipoDoRegistro
        this.indicadorFullDiario = indicadorFullDiario
        this.tipoAtualização = tipoAtualização
        this.cnpj = cnpj
        this.identificadorDeSocio = identificadorDeSocio
        this.nomeSocio = nomeSocio
        this.cnpjCpf = cnpjCpf
        this.qualificacaoSocio = qualificacaoSocio
        this.percentualCapitalSocial = percentualCapitalSocial
        this.dataEntradaSociedade = dataEntradaSociedade
        this.codigoPais = codigoPais
        this.nomePaisSocio = nomePaisSocio
        this.cpfRepresentanteLegal = cpfRepresentanteLegal
        this.nomeRepresentante = nomeRepresentante
        this.qualificacaoRepresentante = qualificacaoRepresentante
        this.filler = filler
        this.fimDeRegistro = fimDeRegistro
    }


}