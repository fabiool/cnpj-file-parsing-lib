import com.sun.xml.internal.ws.spi.db.BindingContextFactory
import enums.RegistryType
import model.DadosCadastrais
import model.Socio
import org.apache.commons.collections4.map.MultiValueMap
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths
import java.util.logging.Level

class CsvWriter {

    private val DADOS_CADASTRAIS_OUTPUT_FILE_NAME : String = "dados_cadastrais.csv"
    private val SOCIO_OUTPUT_FILE : String = "socio.csv"

    fun writeDataToCsv(path : Path, dataParsed: DadosCadastrais) {
        try {

            var outputFolder : Path = handleOutputFolder(path, dataParsed.uf)

            var declaredFields : String = ""
            for (field in dataParsed::class.java.declaredFields)
                declaredFields += field.name + ","

            var outputFile = File(handleOutputFile(outputFolder, declaredFields, RegistryType.DADOS_CADASTRAIS).toString())

            outputFile.appendText("${dataParsed.tipoDoRegistro.toString()},")
            outputFile.appendText("${dataParsed.indicadorFullDiario.toString()},")
            outputFile.appendText("${dataParsed.tipoAtualização.toString()},")
            outputFile.appendText("${dataParsed.cnpj},")
            outputFile.appendText("${dataParsed.indetificadorMatrizFilial.toString()},")
            outputFile.appendText("${dataParsed.razaoSocial},")
            outputFile.appendText("${dataParsed.nomeFantasia},")
            outputFile.appendText("${dataParsed.situacaoCadastral},")
            outputFile.appendText("${dataParsed.dataSituacaoCadastral.toString()},")
            outputFile.appendText("${dataParsed.motivoSituacaoCadastral},")
            outputFile.appendText("${dataParsed.nomeCidadeExterior},")
            outputFile.appendText("${dataParsed.codigoPais},")
            outputFile.appendText("${dataParsed.nomePais},")
            outputFile.appendText("${dataParsed.codigoNaturezaJuridica},")
            outputFile.appendText("${dataParsed.dataInicioAtividade.toString()},")
            outputFile.appendText("${dataParsed.cnaeFiscal},")
            outputFile.appendText("${dataParsed.descricaoTipoLogradouro},")
            outputFile.appendText("${dataParsed.logradouro},")
            outputFile.appendText("${dataParsed.numero},")
            outputFile.appendText("${dataParsed.complemento},")
            outputFile.appendText("${dataParsed.bairro},")
            outputFile.appendText("${dataParsed.cep},")
            outputFile.appendText("${dataParsed.uf},")
            outputFile.appendText("${dataParsed.codigoMunicipio},")
            outputFile.appendText("${dataParsed.municipio},")
            outputFile.appendText("${dataParsed.dddTelefone1},")
            outputFile.appendText("${dataParsed.ddd1},")
            outputFile.appendText("${dataParsed.telefone1},")
            outputFile.appendText("${dataParsed.dddTelefone2},")
            outputFile.appendText("${dataParsed.ddd2},")
            outputFile.appendText("${dataParsed.telefone2},")
            outputFile.appendText("${dataParsed.dddFax},")
            outputFile.appendText("${dataParsed.numeroDddFax},")
            outputFile.appendText("${dataParsed.numeroFax},")
            outputFile.appendText("${dataParsed.correioEletronico},")
            outputFile.appendText("${dataParsed.qualificacaoResponsavel},")
            outputFile.appendText("${dataParsed.capitalSocial},")
            outputFile.appendText("${dataParsed.porteEmpresa},")
            outputFile.appendText("${dataParsed.opcaoSimples},")
            outputFile.appendText("${dataParsed.dataOpcaoSimples.toString()},")
            outputFile.appendText("${dataParsed.dataExclusaoSimples.toString()},")
            outputFile.appendText("${dataParsed.opcaoMei.toString()},")
            outputFile.appendText("${dataParsed.situaçãoEspecial},")
            outputFile.appendText("${dataParsed.dataSituaçãoEspecial},")
            outputFile.appendText("${dataParsed.filler},")
            outputFile.appendText("${dataParsed.fimDeRegistro}")


        } catch (e: Exception){
            BindingContextFactory.LOGGER.log(Level.SEVERE, String.format("Could not create file - %s ", e.toString()))
        }

    }
    fun writeDataToCsv(path : Path, dataParsed: Socio, multiMap : MultiValueMap<String, String>) {
        try {

            var outputFolder : Path? = null
            for (key in multiMap.keys) {
                if(multiMap.containsValue(key, dataParsed.cnpj)){
                    outputFolder = handleOutputFolder(path, key)
                    break
                }
            }

            var declaredFields : String = ""
            for (field in dataParsed::class.java.declaredFields)
                declaredFields += field.name + ","

            var outputFile = File(handleOutputFile(outputFolder!!, declaredFields, RegistryType.SOCIO).toString())

            outputFile.appendText("${dataParsed.tipoDoRegistro},")
            outputFile.appendText("${dataParsed.indicadorFullDiario},")
            outputFile.appendText("${dataParsed.tipoAtualização},")
            outputFile.appendText("${dataParsed.cnpj},")
            outputFile.appendText("${dataParsed.identificadorDeSocio},")
            outputFile.appendText("${dataParsed.nomeSocio},")
            outputFile.appendText("${dataParsed.cnpjCpf},")
            outputFile.appendText("${dataParsed.qualificacaoSocio},")
            outputFile.appendText("${dataParsed.percentualCapitalSocial},")
            outputFile.appendText("${dataParsed.dataEntradaSociedade},")
            outputFile.appendText("${dataParsed.codigoPais},")
            outputFile.appendText("${dataParsed.nomePaisSocio},")
            outputFile.appendText("${dataParsed.cpfRepresentanteLegal},")
            outputFile.appendText("${dataParsed.nomeRepresentante},")
            outputFile.appendText("${dataParsed.qualificacaoRepresentante},")
            outputFile.appendText("${dataParsed.filler},")
            outputFile.appendText("${dataParsed.fimDeRegistro}")

        } catch (e: Exception){
            BindingContextFactory.LOGGER.log(Level.SEVERE, String.format("Could not create file - %s ", e.toString()))
        }

    }

    private fun handleOutputFolder(path : Path, filter : String) : Path{
        val file = File("$path/$filter")
        if(!file.exists()) {
            file.mkdirs()
        }
        return Paths.get("$path/$filter")
    }

    private fun handleOutputFile(outputFolder : Path, declaredFields : String, registryType : RegistryType) : Path {

        var fileName : String? = null

        when (registryType) {
            RegistryType.DADOS_CADASTRAIS -> fileName = DADOS_CADASTRAIS_OUTPUT_FILE_NAME
            RegistryType.SOCIO -> fileName = SOCIO_OUTPUT_FILE
            else -> {
                throw IllegalArgumentException("Unknown type of registry")
            }

        }

        val file = File("$outputFolder/$fileName")
        if(file.createNewFile()) {
            file.writeText(declaredFields)
            file.appendText('\n'.toString())
        } else {
            file.appendText('\n'.toString())
        }
        return (Paths.get("$outputFolder/$fileName"))
    }
}