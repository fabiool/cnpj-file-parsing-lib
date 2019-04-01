package model

import com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER
import java.text.ParseException
import java.util.*
import java.util.Arrays.copyOfRange
import java.util.logging.Level

class DadosCadastrais constructor(): InfoCnpj {
    private var tipoDoRegistro: Char? = null
    private var indicadorFullDiario: Char? = null
    private var tipoAtualização: Char? = null
    private lateinit var cnpj: String
    private var indetificadorMatrizFilial: Char? = null
    private lateinit var razaoSocial: String
    private lateinit var nomeFantasia: String
    private lateinit var situacaoCadastral: String
    private var dataSituacaoCadastral: Date? = null
    private lateinit var motivoSituacaoCadastral: String
    private lateinit var nomeCidadeExterior: String
    private lateinit var codigoPais: String
    private lateinit var nomePais: String
    private lateinit var codigoNaturezaJuridica: String
    private var dataInicioAtividade: Date? = null
    private lateinit var cnaeFiscal: String
    private lateinit var descricaoTipoLogradouro: String
    private lateinit var logradouro: String
    private lateinit var numero: String
    private lateinit var complemento: String
    private lateinit var bairro: String
    private lateinit var cep: String
    private lateinit var uf: String
    private lateinit var codigoMunicipio: String
    private lateinit var municipio: String
    private lateinit var dddTelefone1: String
    private lateinit var ddd1: String
    private lateinit var telefone1: String
    private lateinit var dddTelefone2: String
    private lateinit var ddd2: String
    private lateinit var telefone2: String
    private lateinit var dddFax: String
    private lateinit var numeroDddFax: String
    private lateinit var numeroFax: String
    private lateinit var correioEletronico: String
    private lateinit var qualificacaoResponsavel: String
    private lateinit var capitalSocial: String
    private lateinit var porteEmpresa: String
    private var opcaoSimples: Char? = null
    private var dataOpcaoSimples: Date? = null
    private var dataExclusaoSimples: Date? = null
    private var opcaoMei: Char? = null
    private lateinit var situaçãoEspecial: String
    private var dataSituaçãoEspecial: Date? = null
    private lateinit var filler: String
    private var fimDeRegistro: Char? = null

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