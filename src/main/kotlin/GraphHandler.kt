import org.apache.commons.configuration.Configuration
import org.apache.commons.configuration.PropertiesConfiguration
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Graph
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
        mgmt.makeVertexLabel("Matriz").make()
        mgmt.makeVertexLabel("Filial").make()
        mgmt.makeVertexLabel("Sócio").make()
    }

    fun CreateEdgeLabels(mgmt: JanusGraphManagement) {
        mgmt.makeEdgeLabel("AC").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("AL").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("AM").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("AP").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("BA").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("BR").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("CE").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("DF").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("ES").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("EX").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("GO").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("MA").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("MG").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("MS").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("MT").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("PA").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("PB").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("PE").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("PI").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("PR").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("RJ").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("RN").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("RO").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("RR").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("RS").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("SC").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("SE").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("SP").multiplicity(Multiplicity.MANY2ONE).make()
        mgmt.makeEdgeLabel("TO").multiplicity(Multiplicity.MANY2ONE).make()

    }
}

