import org.apache.commons.configuration.Configuration
import org.apache.commons.configuration.PropertiesConfiguration
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Edge
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.Vertex
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory
import org.janusgraph.core.JanusGraph
import org.janusgraph.core.Multiplicity
import org.janusgraph.core.schema.JanusGraphManagement
import java.util.logging.Level
import java.util.logging.Logger


class GraphHandler {

    lateinit var graph : Graph
    lateinit var g : GraphTraversalSource
    val LOGGER : Logger = Logger.getLogger(javaClass.toString())

    fun StartGraphProperties(){

        LOGGER.log(Level.INFO, "Starting JanusGraph properties")

        val conf : Configuration = PropertiesConfiguration("conf/janusgraph-inmemory-tatic.properties")
        graph = GraphFactory.open(conf)
        g = graph.traversal()

        var mgmt : JanusGraphManagement = GetJanusGraph().openManagement()
        CreateProperties(mgmt)
        CreateEdgeLabels(mgmt)
//        CreateCompositeIndexes(mgmt)

        CloseGraph()
    }


    fun GetJanusGraph() : JanusGraph{
        return graph as JanusGraph
    }

    fun CloseGraph() {

        LOGGER.log(Level.INFO, "Closing graph")
        graph.close()
        g.close()
    }

    fun CreateEdgeLabels(mgmt : JanusGraphManagement) {
        mgmt.makeEdgeLabel("Reside").make()
        mgmt.makeEdgeLabel("Possui").make()
        mgmt.makeEdgeLabel("Emite").make()
        mgmt.makeEdgeLabel("Atua").make()
    }

//    fun CreateCompositeIndexes(mgmt: JanusGraphManagement) {
//        mgmt.buildIndex("Data", Edge::class.java)
//                .addKey(mgmt.getPropertyKey("Possui")).buildCompositeIndex()
//
//        mgmt.buildIndex("%", Edge::class.java)
//                .addKey(mgmt.getPropertyKey("Possui"))
//                .addKey(mgmt.getPropertyKey("Emite"))
//                .addKey(mgmt.getPropertyKey("Atua")).buildCompositeIndex()
//    }


    fun CreateProperties(mgmt: JanusGraphManagement) {
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

