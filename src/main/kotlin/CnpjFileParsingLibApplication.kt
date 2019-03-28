import java.nio.file.Path
import java.nio.file.Paths

fun main() {
    val dataFile : Path? = Paths.get("/home/matheusvargas/Documentos/TATIC/CNPJ/DADOS/F.K032001K.D81106D")
    val outputFolder : Path? = Paths.get("/home/matheusvargas/Documentos/TATIC/CNPJ/")

    CnpjFileParser.getParser(dataFile!!, outputFolder!!).run()

}