import model.*
import org.apache.commons.collections4.MultiMap
import org.apache.commons.collections4.map.MultiValueMap
import java.io.FileInputStream
import java.nio.file.Path
import java.text.ParseException
import java.util.logging.Level
import java.util.logging.Logger

class CnpjFileParser constructor() : Runnable {

    val LOGGER : Logger = Logger.getLogger(javaClass.toString())

    val RECORD_SIZE_BYTES : Int = 1200

    val CHUNK_RECORDS_COUNT : Int = 1000

    val BUFF_SIZE = 1202

    lateinit var dataFile : Path

    lateinit var outputFolder : Path


    constructor(dataFile : Path, outputFolder : Path) : this() {
        this.dataFile = dataFile
        this.outputFolder = outputFolder
    }

    fun getParser(dataFile : Path, outputFolder : Path) : CnpjFileParser {

        if(!dataFile.toFile().exists() || !dataFile.toFile().canRead()) {
            throw  IllegalArgumentException(String.format("Can´t read from %s", dataFile.toAbsolutePath().toString()))
        }
        outputFolder.toFile().mkdirs()
        return CnpjFileParser(dataFile, outputFolder)
    }

    private fun getChunckSizeBytes() : Int { return  RECORD_SIZE_BYTES * CHUNK_RECORDS_COUNT }


    override fun run() {
        try {

            val multiMap : MultiValueMap<String, String> = MultiValueMap()

            val fis : FileInputStream = FileInputStream(dataFile.toFile())
            var buff : ByteArray = ByteArray(BUFF_SIZE)

            var readCount : Int = fis.read(buff)

            var count : Int = 0

            while (BUFF_SIZE == readCount) {
                LOGGER.log(Level.INFO, String.format("Iteration %s ", count++.toString()))

                val dataParsed  = RecordFactory().getRecord(buff)

                if(dataParsed is Header) {
                    LOGGER.log(Level.INFO, "Got a Header record")

                } else if (dataParsed is DadosCadastrais) {
                    LOGGER.log(Level.INFO, "Got a DadosCadastrais record")
                    CsvWriter().writeDataToCsv(outputFolder, dataParsed)
                    multiMap.put(dataParsed.uf, dataParsed.cnpj)

                } else if (dataParsed is Socio) {
                    LOGGER.log(Level.INFO, "Got a Socio record")
                    CsvWriter().writeDataToCsv(outputFolder, dataParsed, multiMap)

                } else if (dataParsed is CnaeSecundaria) {
                    LOGGER.log(Level.INFO, "Got a CnaeSecundaria record")
                    CsvWriter().writeDataToCsv(outputFolder, dataParsed, multiMap)

                } else if (dataParsed is Trailler) {
                    LOGGER.log(Level.INFO, "Got a Trailler record")
                    CsvWriter().writeDataToCsv(outputFolder, dataParsed)
                }

                readCount = fis.read(buff)
            }

        } catch (ex : ParseException) {
            LOGGER.log(Level.SEVERE, String.format("Processing file %s aborted due to exception %s!", dataFile.toAbsolutePath().toString(), ex.message), ex)
            throw RuntimeException(ex)
        }
    }
}