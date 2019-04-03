package model

import com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER
import java.text.ParseException
import java.util.*
import java.util.Arrays.copyOfRange
import java.util.logging.Level

class DadosCadastrais constructor(): InfoCnpj {
    var tipoDoRegistro: Char? = null
    var indicadorFullDiario: Char? = null
    var tipoAtualização: Char? = null
    lateinit var cnpj: String
    var indetificadorMatrizFilial: Char? = null
    lateinit var razaoSocial: String
    lateinit var nomeFantasia: String
    lateinit var situacaoCadastral: String
    var dataSituacaoCadastral: Date? = null
    lateinit var motivoSituacaoCadastral: String
    lateinit var nomeCidadeExterior: String
    lateinit var codigoPais: String
    lateinit var nomePais: String
    lateinit var codigoNaturezaJuridica: String
    var dataInicioAtividade: Date? = null
    lateinit var cnaeFiscal: String
    lateinit var descricaoTipoLogradouro: String
    lateinit var logradouro: String
    lateinit var numero: String
    lateinit var complemento: String
    lateinit var bairro: String
    lateinit var cep: String
    lateinit var uf: String
    lateinit var codigoMunicipio: String
    lateinit var municipio: String
    lateinit var dddTelefone1: String
    lateinit var ddd1: String
    lateinit var telefone1: String
    lateinit var dddTelefone2: String
    lateinit var ddd2: String
    lateinit var telefone2: String
    lateinit var dddFax: String
    lateinit var numeroDddFax: String
    lateinit var numeroFax: String
    lateinit var correioEletronico: String
    lateinit var qualificacaoResponsavel: String
    lateinit var capitalSocial: String
    lateinit var porteEmpresa: String
    var opcaoSimples: Char? = null
    var dataOpcaoSimples: Date? = null
    var dataExclusaoSimples: Date? = null
    var opcaoMei: Char? = null
    lateinit var situaçãoEspecial: String
    var dataSituaçãoEspecial: Date? = null
    lateinit var filler: String
    var fimDeRegistro: Char? = null

    @Throws(ParseException::class)
    fun parse(source : ByteArray) : DadosCadastrais {

        LOGGER.log(Level.INFO, String.format("Trying to parse DadosCadastrais instance - %s - %s", String(copyOfRange(source, 18, 168)), String(copyOfRange(source, 944, 952))))

        return DadosCadastrais(
                String(copyOfRange(source, 0, 1))[0],
                String(copyOfRange(source, 1, 2))[0],
                String(copyOfRange(source, 2, 3))[0],
                String(copyOfRange(source, 3, 17)),
                String(copyOfRange(source, 17, 18))[0],
                String(copyOfRange(source, 18, 168)),
                String(copyOfRange(source, 168, 223)),
                String(copyOfRange(source, 223, 225)),
                if (shouldParseDate(copyOfRange(source, 225, 233))) parseDate(copyOfRange(source, 225, 233)) else null,
                String(copyOfRange(source, 233, 235)),
                String(copyOfRange(source, 235, 290)),
                String(copyOfRange(source, 290, 293)),
                String(copyOfRange(source, 293, 363)),
                String(copyOfRange(source, 363, 367)),
                if (shouldParseDate(copyOfRange(source, 367, 375))) parseDate(copyOfRange(source, 367, 375)) else null,
                String(copyOfRange(source, 375, 382)),
                String(copyOfRange(source, 382, 402)),
                String(copyOfRange(source, 402, 462)),
                String(copyOfRange(source, 462, 468)),
                String(copyOfRange(source, 468, 624)),
                String(copyOfRange(source, 624, 674)),
                String(copyOfRange(source, 674, 682)),
                String(copyOfRange(source, 682, 684)),
                String(copyOfRange(source, 684, 688)),
                String(copyOfRange(source, 688, 738)),
                String(copyOfRange(source, 738, 750)),
                String(copyOfRange(source, 738, 742)),
                String(copyOfRange(source, 742, 750)),
                String(copyOfRange(source, 750, 762)),
                String(copyOfRange(source, 750, 754)),
                String(copyOfRange(source, 754, 762)),
                String(copyOfRange(source, 762, 774)),
                String(copyOfRange(source, 762, 766)),
                String(copyOfRange(source, 766, 774)),
                String(copyOfRange(source, 774, 889)),
                String(copyOfRange(source, 891, 905)),
                String(copyOfRange(source, 905, 907)),
                String(copyOfRange(source, 907, 908)),
                String(copyOfRange(source, 907, 908))[0],
                if (shouldParseDate(copyOfRange(source, 909, 916))) parseDate(copyOfRange(source, 909, 916)) else null,
                if (shouldParseDate(copyOfRange(source, 916, 924))) parseDate(copyOfRange(source, 916, 924)) else null,
                String(copyOfRange(source, 924, 925))[0],
                String(copyOfRange(source, 925, 948)),
                if (shouldParseDate(copyOfRange(source, 949, 956))) parseDate(copyOfRange(source, 949, 956)) else null,
                String(copyOfRange(source, 956, 1199)),
                String(copyOfRange(source, 1199, 1200))[0]
        )
    }

    constructor(tipoDoRegistro: Char,
                indicadorFullDiario: Char,
                tipoAtualização: Char,
                cnpj: String,
                indetificadorMatrizFilial: Char,
                razaoSocial: String,
                nomeFantasia: String,
                situacaoCadastral: String,
                dataSituacaoCadastral: Date?,
                motivoSituacaoCadastral: String,
                nomeCidadeExterior: String, codigoPais:
                                String, nomePais: String,
                codigoNaturezaJuridica: String,
                dataInicioAtividade: Date?,
                cnaeFiscal: String,
                descricaoTipoLogradouro: String,
                logradouro: String,
                numero: String,
                complemento: String,
                bairro: String,
                cep: String,
                uf: String,
                codigoMunicipio: String,
                municipio: String,
                dddTelefone1: String,
                ddd1: String,
                telefone1: String,
                dddTelefone2: String,
                ddd2: String,
                telefone2: String,
                dddFax: String,
                numeroDddFax: String,
                numeroFax: String,
                correioEletronico: String,
                qualificacaoResponsavel: String,
                capitalSocial: String,
                porteEmpresa: String,
                opcaoSimples: Char,
                dataOpcaoSimples: Date?,
                dataExclusaoSimples: Date?,
                opcaoMei: Char,
                situaçãoEspecial: String,
                dataSituaçãoEspecial: Date?,
                filler: String,
                fimDeRegistro: Char) : this(){
        this.tipoDoRegistro = tipoDoRegistro
        this.indicadorFullDiario = indicadorFullDiario
        this.tipoAtualização = tipoAtualização
        this.cnpj = cnpj
        this.indetificadorMatrizFilial = indetificadorMatrizFilial
        this.razaoSocial = razaoSocial
        this.nomeFantasia = nomeFantasia
        this.situacaoCadastral = situacaoCadastral
        this.dataSituacaoCadastral = dataSituacaoCadastral
        this.motivoSituacaoCadastral = motivoSituacaoCadastral
        this.nomeCidadeExterior = nomeCidadeExterior
        this.codigoPais = codigoPais
        this.nomePais = nomePais
        this.codigoNaturezaJuridica = codigoNaturezaJuridica
        this.dataInicioAtividade = dataInicioAtividade
        this.cnaeFiscal = cnaeFiscal
        this.descricaoTipoLogradouro = descricaoTipoLogradouro
        this.logradouro = logradouro
        this.numero = numero
        this.complemento = complemento
        this.bairro = bairro
        this.cep = cep
        this.uf = uf
        this.codigoMunicipio = codigoMunicipio
        this.municipio = municipio
        this.dddTelefone1 = dddTelefone1
        this.ddd1 = ddd1
        this.telefone1 = telefone1
        this.dddTelefone2 = dddTelefone2
        this.ddd2 = ddd2
        this.telefone2 = telefone2
        this.dddFax = dddFax
        this.numeroDddFax = numeroDddFax
        this.numeroFax = numeroFax
        this.correioEletronico = correioEletronico
        this.qualificacaoResponsavel = qualificacaoResponsavel
        this.capitalSocial = capitalSocial
        this.porteEmpresa = porteEmpresa
        this.opcaoSimples = opcaoSimples
        this.dataOpcaoSimples = dataOpcaoSimples
        this.dataExclusaoSimples = dataExclusaoSimples
        this.opcaoMei = opcaoMei
        this.situaçãoEspecial = situaçãoEspecial
        this.dataSituaçãoEspecial = dataSituaçãoEspecial
        this.filler = filler
        this.fimDeRegistro = fimDeRegistro
    }

    fun shouldParseDate(source : ByteArray) : Boolean {
        var answer = false
        for (byte in source) {
            if (byte != 32.toByte())
                answer = true
        }
        return answer
    }

}