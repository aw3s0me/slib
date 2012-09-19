/*

Copyright or © or Copr. Ecole des Mines d'Alès (2012) 

This software is a computer program whose purpose is to 
process semantic graphs.

This software is governed by the CeCILL  license under French law and
abiding by the rules of distribution of free software.  You can  use, 
modify and/ or redistribute the software under the terms of the CeCILL
license as circulated by CEA, CNRS and INRIA at the following URL
"http://www.cecill.info". 

As a counterpart to the access to the source code and  rights to copy,
modify and redistribute granted by the license, users are provided only
with a limited warranty  and the software's author,  the holder of the
economic rights,  and the successive licensors  have only  limited
liability. 

In this respect, the user's attention is drawn to the risks associated
with loading,  using,  modifying and/or developing or reproducing the
software by the user in light of its specific status of free software,
that may mean  that it is complicated to manipulate,  and  that  also
therefore means  that it is reserved for developers  and  experienced
professionals having in-depth computer knowledge. Users are therefore
encouraged to load and test the software's suitability as regards their
requirements in conditions enabling the security of their systems and/or 
data to be ensured and,  more generally, to use and operate it in the 
same conditions as regards security. 

The fact that you are presently reading this means that you have had
knowledge of the CeCILL license and that you accept its terms.

 */


package slib.sglib.io.loader;

import java.util.Arrays;
import java.util.Collection;

import org.openrdf.model.URI;
import org.openrdf.rio.RDFFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import slib.sglib.algo.GraphActionExecutor;
import slib.sglib.io.conf.GDataConf;
import slib.sglib.io.conf.GraphConf;
import slib.sglib.io.loader.bio.gaf2.GraphLoader_GAF_2;
import slib.sglib.io.loader.bio.obo.GraphLoader_OBO_1_2;
import slib.sglib.io.loader.rdf.RDFLoader;
import slib.sglib.io.loader.sgl.GraphLoader_SGL;
import slib.sglib.io.util.GFormat;
import slib.sglib.model.graph.G;
import slib.sglib.model.graph.impl.memory.GraphMemory;
import slib.sglib.model.repo.impl.DataRepository;
import slib.utils.ex.SGL_Ex_Critic;
import slib.utils.ex.SGL_Exception;

public class GraphLoaderGeneric {


	public static GFormat[] supportedFormat = {GFormat.OBO};

	public static Logger logger = LoggerFactory.getLogger(GraphLoaderGeneric.class);

	public static G populate(GDataConf dataConf, G g) throws SGL_Exception{

		logger.debug("Populate "+g.getURI()+" based on "+dataConf.getLoc());
		
		IGraphLoader gLoader = getLoader(dataConf);

		gLoader.populate(dataConf, g);

		return g;
	}
	
	public static G createGraph(URI uri){
		
		logger.debug("Create graph "+uri);
		
		G g = new GraphMemory(uri);
		DataRepository.getSingleton().addGraph(g);
		return g;
	}

	public static G load(GraphConf graphConf) throws SGL_Exception{
		
		logger.debug("Loading Graph");
		
		G g = createGraph(graphConf.getUri());
		
		
		for(GDataConf dataConf : graphConf.getData()){
			populate(dataConf,g);
		}
		
		logger.debug(g.toString());
		
		GraphActionExecutor.applyActions(graphConf.getActions(), (G) null);

		DataRepository.getSingleton().addGraph(g);
		
		return g;
	}


	public static void load(Collection<GraphConf> graphConfs) throws SGL_Exception{

		for(GraphConf conf : graphConfs)
			load(conf);
	}

	private static IGraphLoader getLoader(GDataConf data) throws SGL_Ex_Critic {

		if(data.getFormat() == GFormat.OBO)
			return new GraphLoader_OBO_1_2();

		else if(data.getFormat()  == GFormat.SGL)
			return new GraphLoader_SGL();

		else if(data.getFormat()  == GFormat.GAF2)
			return new GraphLoader_GAF_2();
		
		else if(data.getFormat()  == GFormat.RDF_XML)
			return new RDFLoader(RDFFormat.RDFXML);
		
		else if(data.getFormat()  == GFormat.NTRIPLES)
			return new RDFLoader(RDFFormat.NTRIPLES);
		else
			throw new SGL_Ex_Critic("Unknown Graph format "+data.getFormat());
	}


	/**
	 * TODO modify to apply actions
	 * @param graphConf
	 * @param g
	 * @throws SGL_Ex_Critic
	 */
//	private static void performPOSTprocess(GraphConf graphConf, G g) throws SGL_Ex_Critic {
//

//
//		// DAG checking
//
//		if(graphConf.dag == false)
//			logger.info("skipping DAG checking");
//		else{
//			logger.info("Checking DAG restriction ");
//
//			ValidatorDAG dagVal = new ValidatorDAG();
//
//			if(! dagVal.containsRootedTaxonomicDag(g))
//				throw new SGL_Ex_Critic("Require rooted DAG");
//		}
//
//		logger.debug("Graph information: "+g.toString());
//	}


	public static boolean supportFormat(String format){
		return Arrays.asList(supportedFormat).contains(format);
	}

	public static GFormat[] getSupportedFormat(){
		return supportedFormat;
	}

}