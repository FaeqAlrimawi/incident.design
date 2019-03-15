package incident.design;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import cyberPhysical_Incident.IncidentEntity;

/**
 * The services class used by VSM.
 */
public class Services {

	/**
	 * See
	 * http://help.eclipse.org/neon/index.jsp?topic=%2Forg.eclipse.sirius.doc%2Fdoc%2Findex.html&cp=24
	 * for documentation on how to write service methods.
	 */
	public EObject myService(EObject self, String arg) {
		// TODO Auto-generated code
		return self;
	}

	public IncidentEntity castToIncidentEntity(EObject self) {

		if (self instanceof IncidentEntity) {
			IncidentEntity ent = (IncidentEntity) self;
			System.out.println(ent.getName());
			return ent;
			
		}
			
		else
			return null;
	}
	
	/**
	 * Hides all entities that have the given type or their parent have that type
	 * @param self
	 * @param list
	 * @param type
	 * @return
	 */
	public Collection<IncidentEntity> hideEntitiesWithType(EObject self, Collection<IncidentEntity> list, String type) {

		System.out.println("type: "+type);
		List<IncidentEntity> tmp = new LinkedList<IncidentEntity>();
		
		for(IncidentEntity entity : list) {
			String entTypeName = entity.getType()!=null?entity.getType().getName():null;
			String entParentTypeName = (entity.getParentEntity()!=null && ((IncidentEntity)entity.getParentEntity()).getType()!=null)?((IncidentEntity)entity.getParentEntity()).getType().getName():null;
			
			System.out.println(entTypeName+ " "+ entParentTypeName);
			if((entTypeName != null && entTypeName.equalsIgnoreCase(type)) || (entParentTypeName!=null && entParentTypeName.equalsIgnoreCase(type))){
				continue;
			}
			
			tmp.add(entity);
				
		}
		
		return tmp;
	}

}
