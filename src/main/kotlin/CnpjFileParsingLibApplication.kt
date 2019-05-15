import org.janusgraph.core.JanusGraph
import org.janusgraph.core.JanusGraphFactory


fun main() {
//    val dataFile: Path = Paths.get("/home/matheusvargas/Documentos/TATIC/CNPJ/DADOS/F.K032001K.D81106D")
//    val outputFolder: Path = Paths.get("/home/matheusvargas/Documentos/TATIC/CNPJ/DADOS/output/")
//
//    if(File(outputFolder.toString()).exists())
//        File(outputFolder.toString()).delete()

//    CnpjFileParser().getParser(dataFile, outputFolder).run()

    System.setProperty("java.security.auth.login.config", "conf/jaas.conf" )
    System.setProperty("zookeeper.sasl.client", "false")


    val graph : JanusGraph = JanusGraphFactory.open("conf/janusgraph-tatic.properties")
//    val management : JanusGraphManagement = graph.openManagement()
//
//    val countryName = management.makePropertyKey("Nome país").dataType(String::class.java).make()
//    val countryNameIndexBuilder = management.buildIndex("Nome país", Vertex::class.java).addKey(countryName)
//    countryNameIndexBuilder.unique()
//    val countryNameIndex = countryNameIndexBuilder.buildCompositeIndex()
//    management.setConsistency(countryNameIndex, ConsistencyModifier.LOCK)
//
//    val countryCode = management.makePropertyKey("Código país").dataType(String::class.java).make()
//    val countryCodeIndexBuilder = management.buildIndex("Código país", Vertex::class.java).addKey(countryCode)
//    countryCodeIndexBuilder.unique()
//    val countryCodeIndex = countryCodeIndexBuilder.buildCompositeIndex()
//    management.setConsistency(countryCodeIndex, ConsistencyModifier.LOCK)
//
//    val state = management.makePropertyKey("Unidade Federativa").dataType(String::class.java).make()
//    val stateIndexBuilder = management.buildIndex("Unidade Federativa", Vertex::class.java).addKey(state)
//    stateIndexBuilder.unique()
//    val stateIndex = stateIndexBuilder.buildCompositeIndex()
//    management.setConsistency(stateIndex, ConsistencyModifier.LOCK)
}
