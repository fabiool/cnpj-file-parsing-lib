import org.apache.commons.configuration.Configuration
import org.apache.commons.configuration.PropertiesConfiguration
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory
import org.janusgraph.core.JanusGraph
import org.janusgraph.core.JanusGraphFactory
import org.janusgraph.core.schema.JanusGraphManagement
import java.nio.file.Path
import java.nio.file.Paths
import java.util.logging.Level
import java.util.logging.Logger


class GraphHandler {

    lateinit var graph : Graph
    lateinit var g : GraphTraversalSource

    val LOGGER : Logger = Logger.getLogger(javaClass.toString())

    val dataFile: Path = Paths.get("/media/matheusvargas/801a135b-b0c4-4e76-b21c-70103f96481a/TATIC/CNPJ/DADOS/inputs/test")

    fun startGraphProperties(){

        LOGGER.log(Level.INFO, "Starting JanusGraph properties")

        val conf : Configuration = PropertiesConfiguration("conf/janusgraph-inmemory-tatic.properties")
        graph = GraphFactory.open(conf)
        g = graph.traversal()

        var mgmt : JanusGraphManagement = getJanusGraph().openManagement()
        createProperties(mgmt)
        createEdgeLabels(mgmt)

        CnpjFileParser().getParser(g, dataFile).run()

        closeGraph()
    }


    fun getJanusGraph() : JanusGraph{
        return graph as JanusGraph
    }

    fun closeGraph() {

        LOGGER.log(Level.INFO, "Closing graph")
        graph.close()
        g.close()

        if (graph != null) {
            JanusGraphFactory.drop(getJanusGraph())
        }

    }

    fun createEdgeLabels(mgmt : JanusGraphManagement) {
        mgmt.makeEdgeLabel("Reside").make()
        mgmt.makeEdgeLabel("Possui").make()
        mgmt.makeEdgeLabel("Emite").make()
        mgmt.makeEdgeLabel("Atua").make()
    }

    fun createProperties(mgmt: JanusGraphManagement) {
        mgmt.makePropertyKey("Nome").dataType(String::class.java).make()
        mgmt.makePropertyKey("Sigla").dataType(String::class.java).make()
        mgmt.makePropertyKey("CPF").dataType(String::class.java).make()
        mgmt.makePropertyKey("CNPJ").dataType(String::class.java).make()
        mgmt.makePropertyKey("Data").dataType(String::class.java).make()
        mgmt.makePropertyKey("Data Emissão").dataType(String::class.java).make()
        mgmt.makePropertyKey("Número").dataType(String::class.java).make()
        mgmt.makePropertyKey("Valor").dataType(String::class.java).make()
        mgmt.makePropertyKey("% Participação").dataType(String::class.java).make()
    }
}

