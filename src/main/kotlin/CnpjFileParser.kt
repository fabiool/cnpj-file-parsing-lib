import model.Header
import model.InfoCnpj
import model.RecordFactory
import java.io.FileInputStream
import java.nio.file.Path
import java.text.ParseException
import java.util.logging.Level
import java.util.logging.Logger

class CnpjFileParser constructor() : Runnable {

    val LOGGER : Logger = Logger.getLogger(javaClass.toString())

    val RECORD_SIZE_BYTES : Int = 1200

    val CHUNK_RECORDS_COUNT : Int = 1000

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
            val fis : FileInputStream = FileInputStream(dataFile.toFile())
            val buff : ByteArray = ByteArray(1200)

            var readCount : Int = fis.read(buff)

            while (1200 == readCount) {
                val infoCnpj : InfoCnpj = RecordFactory().getRecord(buff)

                if(infoCnpj is Header) {
                    LOGGER.log(Level.INFO, "Got a Header record")
                }
                readCount = fis.read(buff)
            }

        } catch (ex : ParseException) {
            LOGGER.log(Level.SEVERE, String.format("Processing file %s aborted due to exception %s!", dataFile.toAbsolutePath().toString(), ex.message), ex)
            throw RuntimeException(ex)
        }
    }
}