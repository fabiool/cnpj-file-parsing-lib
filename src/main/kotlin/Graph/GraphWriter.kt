package Graph

import model.DadosCadastrais
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Vertex

class GraphWriter {

    fun insertDataToGraph(g: GraphTraversalSource, dataParsed: DadosCadastrais) {
        val tmpVertex : Vertex = g.addV("empresa")
                .property("nome", dataParsed.nomeFantasia)
                .property("cnpj", dataParsed.cnpj).next()

        val ufVertex = g.V().has("uf","sigla", dataParsed.uf)
        if (ufVertex.hasNext()) ufVertex.next() else g.addV("uf").property("sigla",dataParsed.uf).next()

        g.V(tmpVertex).V(ufVertex).addE("reside").from(tmpVertex.label()).next()
    }

}