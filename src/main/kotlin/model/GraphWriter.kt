package model

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.janusgraph.core.JanusGraphFactory
import org.janusgraph.core.JanusGraph



class GraphWriter {
    fun writeDataToGraph() {
        var graph : JanusGraph = JanusGraphFactory.open("conf/janusgraph-cql.properties")
        var g : GraphTraversalSource = graph.traversal()
    }
}

