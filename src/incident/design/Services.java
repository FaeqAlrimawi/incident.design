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
	 * Hides all entities that have the given type or their parent (or parent parents) have that type
	 * @param self
	 * @param list
	 * @param type
	 * @return
	 */
	public Collection<IncidentEntity> hideEntitiesWithType(EObject self, Collection<IncidentEntity> list, String type) {

		List<IncidentEntity> tmp = new LinkedList<IncidentEntity>();
		List<IncidentEntity> entitiesIgnored = new LinkedList<IncidentEntity>();
		
		loop_entities : for(IncidentEntity entity : list) {
			String entTypeName = entity.getType()!=null?entity.getType().getName():null;
			
			//if the entity type is the same as the given type then move to next (ignore)
			if((entTypeName != null && entTypeName.equalsIgnoreCase(type))){
				entitiesIgnored.add(entity);
				continue;
			}
			
			IncidentEntity parent = (IncidentEntity)entity.getParentEntity();
			int length = 1000;
			
			while(parent != null && length >0) {
				
				//if the parent already has been ignored then move on to next parent
				if(entitiesIgnored.contains(parent)) {
					continue loop_entities;
				}
				
				String entParentTypeName = (parent.getType()!=null)?parent.getType().getName():null;	
				
				//if the parent has the same type as the given type then move on to next parent
				if(entParentTypeName!=null && entParentTypeName.equalsIgnoreCase(type)){
					entitiesIgnored.add(parent);					
					continue loop_entities;
				}
				
				parent = (IncidentEntity)parent.getParentEntity();
				
				length--;
				
			}
			

			
			tmp.add(entity);
				
		}
		
		return tmp;
	}
	
	public IncidentEntity updateParentNewEdge(EObject self, IncidentEntity src, IncidentEntity des) {
		
		//src is the source entity from which edge starts (parent)
		//des is the destination of the edge i.e. the other end (child)
		
		//update contained entities in src
		if(!src.getContainedEntities().contains(des)) {
			
			//check that the des is not the parent of the src
			IncidentEntity srcParent = (IncidentEntity)src.getParentEntity();			
			if(srcParent  == null || !srcParent.equals(des)) {
				src.getContainedEntities().add(des);
				
				//add src as parent entity for des
				IncidentEntity desParent = (IncidentEntity)des.getParentEntity();
				
				if(desParent == null) {
					des.setParentEntity(src);
				} else {
					desParent.getContainedEntities().remove(des);
					des.setParentEntity(srcParent);
				}
			}
		}
		
		return null;
	}
	
	public List<IncidentEntity> getAllcontainedEntities(EObject self, List<IncidentEntity> containedEntities) {
		
		List<IncidentEntity> allContained = new LinkedList<IncidentEntity>();
		List<IncidentEntity> toVisit = new LinkedList<IncidentEntity>();
		
		allContained.addAll(containedEntities);
		toVisit.addAll(containedEntities);
		
		int length = 100000;
		
		while(!toVisit.isEmpty() && length>0) {
			
			IncidentEntity ent = toVisit.remove(0);
			List<IncidentEntity> tmp = (List)ent.getContainedEntities();
			
			if(tmp.isEmpty()) {
				continue;
			}
			
			allContained.addAll(tmp);
			toVisit.addAll(tmp);
			
			length--;
		}
		
		if(length == 0) {
			System.out.println("Length reached 0");
		}
		
		return allContained;
	}

}
