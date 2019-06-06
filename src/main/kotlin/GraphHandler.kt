import org.apache.commons.configuration.Configuration
import org.apache.commons.configuration.PropertiesConfiguration
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory
import org.janusgraph.core.JanusGraph
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

    fun CreateVertexLabels(mgmt : JanusGraphManagement) {
        mgmt.makeVertexLabel("Sede em")
    }
}

