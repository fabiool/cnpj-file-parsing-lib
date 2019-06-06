import org.apache.commons.configuration.Configuration
import org.apache.commons.configuration.PropertiesConfiguration
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Graph
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory
import java.util.logging.Level
import java.util.logging.Logger

class GraphHandler {

    lateinit var graph : Graph
    lateinit var g : GraphTraversalSource


    fun StartGraphProperties(){

        val LOGGER : Logger = Logger.getLogger(javaClass.toString())
        LOGGER.log(Level.INFO, "Starting JanusGraph properties")

        val conf : Configuration = PropertiesConfiguration("conf/janusgraph-inmemory-tatic.properties")
        graph = GraphFactory.open(conf)
        g = graph.traversal()

        CloseGraph()
    }


    fun writeDataToGraph() {
    }

    fun CloseGraph() {
        graph.close()
        g.close()
    }
}

