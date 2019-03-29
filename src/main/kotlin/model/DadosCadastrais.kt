package model

import java.text.ParseException
import java.util.*
import java.util.Arrays.copyOfRange

class DadosCadastrais constructor(): InfoCnpj {
    private var tipoDoRegistro: Char? = null
    private var indicadorFullDiario: Char? = null
    private var tipoAtualização: Char? = null
    private lateinit var cnpj: String
    private var indetificadorMatrizFilial: Char? = null
    private lateinit var razaoSocial: String
    private lateinit var nomeFantasia: String
    private lateinit var situacaoCadastral: String
    private lateinit var dataSituacaoCadastral: Date
    private lateinit var motivoSituacaoCadastral: String
    private lateinit var nomeCidadeExterior: String
    private lateinit var codigoPais: String
    private lateinit var nomePais: String
    private lateinit var codigoNaturezaJuridica: String
    private lateinit var dataInicioAtividade: Date
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
    private lateinit var dataOpcaoSimples: Date
    private lateinit var dataExclusaoSimples: Date
    private var opcaoMei: Char? = null
    private lateinit var situaçãoEspecial: String
    private lateinit var dataSituaçãoEspecial: Date
    private lateinit var filler: String
    private var fimDeRegistro: Char? = null

    @Throws(ParseException::class)
    fun parse(source : ByteArray) : DadosCadastrais {
        return DadosCadastrais(
                String(copyOfRange(source, 0, 1))[0],
                String(copyOfRange(source, 1, 2))[0],
                String(copyOfRange(source, 2, 3))[0],
                String(copyOfRange(source, 3, 17)),
                String(copyOfRange(source, 17, 18))[0],
                String(copyOfRange(source, 18, 168)),
                String(copyOfRange(source, 168, 223)),
                String(copyOfRange(source, 223, 225)),
                parseDate(copyOfRange(source, 225, 233)),
                String(copyOfRange(source, 233, 235)),
                String(copyOfRange(source, 235, 290)),
                String(copyOfRange(source, 290, 293)),
                String(copyOfRange(source, 293, 363)),
                String(copyOfRange(source, 363, 367)),
                parseDate(copyOfRange(source, 367, 375)),
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
                String(copyOfRange(source, 750, 754)),
                String(copyOfRange(source, 754, 762)),
                String(copyOfRange(source, 762, 774)),
                String(copyOfRange(source, 774, 778)),
                String(copyOfRange(source, 778, 786)),
                String(copyOfRange(source, 786, 798)),
                String(copyOfRange(source, 798, 802)),
                String(copyOfRange(source, 802, 810)),
                String(copyOfRange(source, 810, 925)),
                String(copyOfRange(source, 925, 927)),
                String(copyOfRange(source, 927, 941)),
                String(copyOfRange(source, 941, 943)),
                String(copyOfRange(source, 943, 944))[0],
                parseDate(copyOfRange(source, 944, 952)),
                parseDate(copyOfRange(source, 952, 960)),
                String(copyOfRange(source, 960, 961))[0],
                String(copyOfRange(source, 961, 984)),
                parseDate(copyOfRange(source, 984, 992)),
                String(copyOfRange(source, 992, 1199)),
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
                                dataSituacaoCadastral: Date,
                                motivoSituacaoCadastral: String,
                                nomeCidadeExterior: String, codigoPais:
                                String, nomePais: String,
                                codigoNaturezaJuridica: String,
                                dataInicioAtividade: Date,
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
                                dataOpcaoSimples: Date,
                                dataExclusaoSimples: Date,
                                opcaoMei: Char,
                                situaçãoEspecial: String,
                                dataSituaçãoEspecial: Date,
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

}