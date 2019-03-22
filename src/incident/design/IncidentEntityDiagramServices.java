package incident.design;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import cyberPhysical_Incident.Asset;
import cyberPhysical_Incident.Vulnerability;

/**
 * The services class used by VSM.
 */
public class IncidentEntityDiagramServices {


	
	public List<Vulnerability> getAssetVulnerabilities(EObject self) {
				
		//only get vulenrabilities if self is an asset
		
		if(!(self instanceof Asset)) {
			return null;
		}
		
		Asset asset = (Asset)self;
		
		return asset.getVulnerability();
		
	}

}
