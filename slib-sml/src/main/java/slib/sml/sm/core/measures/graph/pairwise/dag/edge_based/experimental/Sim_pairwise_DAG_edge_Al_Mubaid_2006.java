/* 
 *  Copyright or © or Copr. Ecole des Mines d'Alès (2012-2014) 
 *  
 *  This software is a computer program whose purpose is to provide 
 *  several functionalities for the processing of semantic data 
 *  sources such as ontologies or text corpora.
 *  
 *  This software is governed by the CeCILL  license under French law and
 *  abiding by the rules of distribution of free software.  You can  use, 
 *  modify and/ or redistribute the software under the terms of the CeCILL
 *  license as circulated by CEA, CNRS and INRIA at the following URL
 *  "http://www.cecill.info". 
 * 
 *  As a counterpart to the access to the source code and  rights to copy,
 *  modify and redistribute granted by the license, users are provided only
 *  with a limited warranty  and the software's author,  the holder of the
 *  economic rights,  and the successive licensors  have only  limited
 *  liability. 

 *  In this respect, the user's attention is drawn to the risks associated
 *  with loading,  using,  modifying and/or developing or reproducing the
 *  software by the user in light of its specific status of free software,
 *  that may mean  that it is complicated to manipulate,  and  that  also
 *  therefore means  that it is reserved for developers  and  experienced
 *  professionals having in-depth computer knowledge. Users are therefore
 *  encouraged to load and test the software's suitability as regards their
 *  requirements in conditions enabling the security of their systems and/or 
 *  data to be ensured and,  more generally, to use and operate it in the 
 *  same conditions as regards security. 
 * 
 *  The fact that you are presently reading this means that you have had
 *  knowledge of the CeCILL license and that you accept its terms.
 */
package slib.sml.sm.core.measures.graph.pairwise.dag.edge_based.experimental;

import org.openrdf.model.URI;
import slib.sml.sm.core.engine.SM_Engine;
import slib.sml.sm.core.measures.graph.pairwise.dag.edge_based.Sim_DAG_edge_abstract;
import slib.sml.sm.core.utils.SMconf;

/**
 * ﻿Al-Mubaid H, Nguyen HA: A cluster-based approach for semantic similarity in
 * the biomedical domain. Conference proceedings : Annual International
 * Conference of the IEEE Engineering in Medicine and Biology Society. IEEE
 * Engineering in Medicine and Biology Society. Conference 2006, 1:2713-7.
 *
 * @author Sébastien Harispe <sebastien.harispe@gmail.com>
 *
 *
 *
 */
public abstract class Sim_pairwise_DAG_edge_Al_Mubaid_2006 extends Sim_DAG_edge_abstract {

    @Override
    public double compare(URI a, URI b, SM_Engine c, SMconf conf) {
        throw new UnsupportedOperationException();

    }
}
