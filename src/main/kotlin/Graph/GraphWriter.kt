package Graph

import model.CnaeSecundaria
import model.DadosCadastrais
import model.Socio
import model.Trailler
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource
import org.apache.tinkerpop.gremlin.structure.Vertex
import java.util.logging.Level
import java.util.logging.Logger

class GraphWriter {

    val PESSOA_JURIDICA : Int = 1
    val PESSOA_FISICA : Int = 2
    val ESTRANGEIRO : Int = 3

    val LOGGER : Logger = Logger.getLogger(javaClass.toString())

    fun insertDataToGraph(g: GraphTraversalSource, dataParsed: DadosCadastrais) {
        val companyVertex = g.addV("empresa")
                .property("nome", dataParsed.nomeFantasia)
                .property("cnpj", dataParsed.cnpj).next()

        val tmpVertex = g.V().has("uf","sigla", dataParsed.uf)
        lateinit var ufVertex : Vertex

        when(tmpVertex.hasNext()) {
            true -> ufVertex = tmpVertex.next()
            false -> ufVertex = g.addV("uf").property("sigla",dataParsed.uf).next()
        }

//        g.V(companyVertex).next().addEdge("reside", g.V(ufVertex).next())
        g.V(companyVertex).`as`("a").V(ufVertex)
                .addE("reside")
                .from("a")
                .next()

    }

    fun insertDataToGraph(g: GraphTraversalSource, dataParsed: Socio) {

        /**
         * Tipos de pessoa segundo a documentação
            1 – PESSOA JURÍDICA
            2 – PESSOA FISICA
            3 – ESTRANGEIRO
        **/

        lateinit var vertexLabel : String

        when(dataParsed.identificadorDeSocio!!.toInt()) {
            PESSOA_JURIDICA -> vertexLabel = "empresa"
            PESSOA_FISICA -> vertexLabel = "pessoa"
            ESTRANGEIRO -> vertexLabel = "estrangeiro"
            else -> {
                LOGGER.log(Level.SEVERE, "Unindentified partner ID")
                return
            }
        }

        val partnerVertex = g.V().addV(vertexLabel)
                .property("nome", dataParsed.nomeSocio)
                .property("cpfCnpj", dataParsed.cnpjCpf)

        val companyVertex = g.V().has("empresa","cnpj", dataParsed.cnpj)
        if(!companyVertex.hasNext()) {
            LOGGER.log(Level.SEVERE, "Can not find partner company CNPJ")
            return
        }

        g.V(partnerVertex).`as`("a").V(companyVertex.next())
                .addE("possui")
                .property("data", dataParsed.dataEntradaSociedade)
                .property("%", dataParsed.percentualCapitalSocial)
                .from("a")
                .next()


    }
    fun insertDataToGraph(g: GraphTraversalSource, dataParsed: CnaeSecundaria) {}
    fun insertDataToGraph(g: GraphTraversalSource, dataParsed: Trailler) {}

}