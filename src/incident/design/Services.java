package incident.design;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import cyberPhysical_Incident.Activity;
import cyberPhysical_Incident.ActivityPattern;
import cyberPhysical_Incident.Actor;
import cyberPhysical_Incident.Asset;
import cyberPhysical_Incident.BigraphExpression;
import cyberPhysical_Incident.Condition;
import cyberPhysical_Incident.Connectivity;
import cyberPhysical_Incident.Entity;
import cyberPhysical_Incident.IncidentDiagram;
import cyberPhysical_Incident.IncidentEntity;
import cyberPhysical_Incident.Resource;
import cyberPhysical_Incident.Scene;
import cyberPhysical_Incident.impl.IncidentEntityImpl;

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
	 * Hides all entities that have the given type or their parent (or parent
	 * parents) have that type
	 * 
	 * @param self
	 * @param list
	 * @param type
	 * @return
	 */
	public Collection<IncidentEntity> hideEntitiesWithType(EObject self, Collection<IncidentEntity> list, String type) {

		List<IncidentEntity> tmp = new LinkedList<IncidentEntity>();
		List<IncidentEntity> entitiesIgnored = new LinkedList<IncidentEntity>();

		loop_entities: for (IncidentEntity entity : list) {
			String entTypeName = entity.getType() != null ? entity.getType().getName() : null;

			// if the entity type is the same as the given type then move to
			// next (ignore)
			if ((entTypeName != null && entTypeName.equalsIgnoreCase(type))) {
				entitiesIgnored.add(entity);
				continue;
			}

			IncidentEntity parent = (IncidentEntity) entity.getParentEntity();
			int length = 1000;

			while (parent != null && length > 0) {

				// if the parent already has been ignored then move on to next
				// parent
				if (entitiesIgnored.contains(parent)) {
					continue loop_entities;
				}

				String entParentTypeName = (parent.getType() != null) ? parent.getType().getName() : null;

				// if the parent has the same type as the given type then move
				// on to next parent
				if (entParentTypeName != null && entParentTypeName.equalsIgnoreCase(type)) {
					entitiesIgnored.add(parent);
					continue loop_entities;
				}

				parent = (IncidentEntity) parent.getParentEntity();

				length--;

			}

			tmp.add(entity);

		}

		return tmp;
	}

	public Collection<IncidentEntity> hideAssetsWithType(EObject self, Collection<IncidentEntity> list, String type) {

		List<IncidentEntity> tmp = new LinkedList<IncidentEntity>();
		List<IncidentEntity> entitiesIgnored = new LinkedList<IncidentEntity>();

		loop_entities: for (IncidentEntity entity : list) {

			// ignore if entity is not asset
			if (!(entity instanceof Asset)) {
				continue;
			}

			String entTypeName = entity.getType() != null ? entity.getType().getName() : null;

			// if the entity type is the same as the given type then move to
			// next (ignore)
			if ((entTypeName != null && entTypeName.equalsIgnoreCase(type))) {
				entitiesIgnored.add(entity);
				continue;
			}

			IncidentEntity parent = (IncidentEntity) entity.getParentEntity();
			int length = 1000;

			while (parent != null && length > 0) {

				// if the parent already has been ignored then move on to next
				// parent
				if (entitiesIgnored.contains(parent)) {
					continue loop_entities;
				}

				String entParentTypeName = (parent.getType() != null) ? parent.getType().getName() : null;

				// if the parent has the same type as the given type then move
				// on to next parent
				if (entParentTypeName != null && entParentTypeName.equalsIgnoreCase(type)) {
					entitiesIgnored.add(parent);
					continue loop_entities;
				}

				parent = (IncidentEntity) parent.getParentEntity();

				length--;

			}

			tmp.add(entity);
			System.out.println(entity.getName());

		}

		return tmp;
	}

	public Collection<IncidentEntity> hideResourcesWithType(EObject self, Collection<IncidentEntity> list,
			String type) {

		List<IncidentEntity> tmp = new LinkedList<IncidentEntity>();
		List<IncidentEntity> entitiesIgnored = new LinkedList<IncidentEntity>();

		loop_entities: for (IncidentEntity entity : list) {

			// ignore if entity is not asset
			if (!(entity instanceof Resource)) {
				continue;
			}

			String entTypeName = entity.getType() != null ? entity.getType().getName() : null;

			// if the entity type is the same as the given type then move to
			// next (ignore)
			if ((entTypeName != null && entTypeName.equalsIgnoreCase(type))) {
				entitiesIgnored.add(entity);
				continue;
			}

			IncidentEntity parent = (IncidentEntity) entity.getParentEntity();
			int length = 1000;

			while (parent != null && length > 0) {

				// if the parent already has been ignored then move on to next
				// parent
				if (entitiesIgnored.contains(parent)) {
					continue loop_entities;
				}

				String entParentTypeName = (parent.getType() != null) ? parent.getType().getName() : null;

				// if the parent has the same type as the given type then move
				// on to next parent
				if (entParentTypeName != null && entParentTypeName.equalsIgnoreCase(type)) {
					entitiesIgnored.add(parent);
					continue loop_entities;
				}

				parent = (IncidentEntity) parent.getParentEntity();

				length--;

			}

			tmp.add(entity);

		}

		return tmp;
	}

	public Collection<IncidentEntity> hideIncidentEntitiesWithType(EObject self, Collection<IncidentEntity> list,
			String type) {

		List<IncidentEntity> tmp = new LinkedList<IncidentEntity>();
		List<IncidentEntity> entitiesIgnored = new LinkedList<IncidentEntity>();

		loop_entities: for (IncidentEntity entity : list) {

			// ignore if entity is not asset
			if (!(entity.getClass().getName().equalsIgnoreCase(IncidentEntityImpl.class.getName()))) {
				continue;
			}

			String entTypeName = entity.getType() != null ? entity.getType().getName() : null;

			// if the entity type is the same as the given type then move to
			// next (ignore)
			if ((entTypeName != null && entTypeName.equalsIgnoreCase(type))) {
				entitiesIgnored.add(entity);
				continue;
			}

			IncidentEntity parent = (IncidentEntity) entity.getParentEntity();
			int length = 1000;

			while (parent != null && length > 0) {

				// if the parent already has been ignored then move on to next
				// parent
				if (entitiesIgnored.contains(parent)) {
					continue loop_entities;
				}

				String entParentTypeName = (parent.getType() != null) ? parent.getType().getName() : null;

				// if the parent has the same type as the given type then move
				// on to next parent
				if (entParentTypeName != null && entParentTypeName.equalsIgnoreCase(type)) {
					entitiesIgnored.add(parent);
					continue loop_entities;
				}

				parent = (IncidentEntity) parent.getParentEntity();

				length--;

			}

			tmp.add(entity);

		}

		return tmp;
	}

	public IncidentEntity updateParentNewEdge(EObject self, IncidentEntity src, IncidentEntity des) {

		// src is the source entity from which edge starts (parent)
		// des is the destination of the edge i.e. the other end (child)

		// update contained entities in src
		if (!src.getContainedEntities().contains(des)) {

			// check that the des is not the parent of the src
			IncidentEntity srcParent = (IncidentEntity) src.getParentEntity();
			if (srcParent == null || !srcParent.equals(des)) {
				src.getContainedEntities().add(des);

				// add src as parent entity for des
				IncidentEntity desParent = (IncidentEntity) des.getParentEntity();

				if (desParent == null) {
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

		while (!toVisit.isEmpty() && length > 0) {

			IncidentEntity ent = toVisit.remove(0);
			List<IncidentEntity> tmp = (List) ent.getContainedEntities();

			if (tmp.isEmpty()) {
				continue;
			}

			allContained.addAll(tmp);
			toVisit.addAll(tmp);

			length--;
		}

		if (length == 0) {
			System.out.println("Length reached 0");
		}

		return allContained;
	}

	/**
	 * Returns all entities in this condition
	 * 
	 * @param self
	 * @param entities
	 * @return
	 */
	public List<Entity> getAllConditionEntity(EObject self, List<Entity> entities) {

		List<Entity> allContained = new LinkedList<Entity>();
		List<Entity> toVisit = new LinkedList<Entity>();

		allContained.addAll(entities);
		toVisit.addAll(entities);

		int length = 100000;

		while (!toVisit.isEmpty() && length > 0) {

			Entity ent = toVisit.remove(0);
			List<Entity> tmp = (List) ent.getEntity();

			if (tmp.isEmpty()) {
				length--;
				continue;
			}

			allContained.addAll(tmp);
			toVisit.addAll(tmp);

			length--;
		}

		if (length == 0) {
			System.out.println("Length reached 0");
		}

		return allContained;

	}

	/**
	 * Returns all connections in this condition
	 * 
	 * @param self
	 * @param entities
	 * @return
	 */
	public List<Connectivity> getAllConditionConnectivity(EObject self, List<Entity> entities) {

		List<Connectivity> allConnectivity = new LinkedList<Connectivity>();
		List<Entity> toVisit = new LinkedList<Entity>();

		// allContained.addAll(entities);
		toVisit.addAll(entities);

		int length = 100000;

		while (!toVisit.isEmpty() && length > 0) {

			Entity ent = toVisit.remove(0);

			allConnectivity.addAll(ent.getConnectivity());

			List<Entity> tmp = (List) ent.getEntity();

			if (tmp.isEmpty()) {
				length--;
				continue;
			}

			toVisit.addAll(tmp);

			length--;
		}

		if (length == 0) {
			System.out.println("Length reached 0");
		}

		return allConnectivity;
	}

	/**
	 * checks if the given entity in the condition is a Source or not in the
	 * incident diagram
	 * 
	 * @param self
	 * @return
	 */
	public boolean isResource(EObject self) {

		return isResource(self, null);
	}

	public boolean isResource(EObject self, String type) {

		if (!(self instanceof Entity)) {
			return false;
		}

		Entity entity = (Entity) self;

		String name = entity.getName();

		int length = 1000;

		while (!(self instanceof IncidentDiagram) && length > 0) {
			self = self.eContainer();
			length--;
		}

		if (self instanceof IncidentDiagram) {

			IncidentDiagram incident = (IncidentDiagram) self;

			// for each source
			for (Resource r : incident.getResource()) {

				// if type is set then compare against it as well
				if (type != null) {
					if (r.getName().equalsIgnoreCase(name) && r.getType() != null
							&& r.getType().getName().equalsIgnoreCase(type)) {
						return true;
					}
				} else { // else just compare names
					if (r.getName().equalsIgnoreCase(name)) {
						return true;
					}
				}
			}
		} else {
			System.out.println("incident NOT found ");
		}

		if (length == 0) {
			System.out.println("Length reached 0");
		}

		return false;
	}

	/**
	 * checks if the given entity in the condition is an IncidentEntity or not
	 * in the incident diagram
	 * 
	 * @param self
	 * @return
	 */
	public boolean isIncidentEntity(EObject self) {

		return isIncidentEntity(self, null);
	}

	public boolean isIncidentEntity(EObject self, String type) {

		if (!(self instanceof Entity)) {
			return false;
		}

		Entity entity = (Entity) self;

		String name = entity.getName();

		int length = 1000;

		while (!(self instanceof IncidentDiagram) && length > 0) {
			self = self.eContainer();
			length--;
		}

		if (self instanceof IncidentDiagram) {

			IncidentDiagram incident = (IncidentDiagram) self;

			// for each source
			for (IncidentEntity r : incident.getIncidentEntity()) {

				// if type is set then compare against it as well
				if (type != null) {
					if (r.getName().equalsIgnoreCase(name) && r.getType() != null
							&& r.getType().getName().equalsIgnoreCase(type)) {
						return true;
					}
				} else { // else just compare names
					if (r.getName().equalsIgnoreCase(name)) {
						return true;
					}
				}
			}
		} else {
			System.out.println("incident NOT found ");
		}

		if (length == 0) {
			System.out.println("Length reached 0");
		}

		return false;
	}

	/**
	 * checks if the given entity in the condition is a Asset or not in the
	 * incident diagram
	 * 
	 * @param self
	 * @return
	 */
	public boolean isAsset(EObject self) {

		return isAsset(self, null);
	}

	public boolean isAsset(EObject self, String type) {

		if (!(self instanceof Entity)) {
			return false;
		}

		Entity entity = (Entity) self;

		String name = entity.getName();

		int length = 1000;

		while (!(self instanceof IncidentDiagram) && length > 0) {
			self = self.eContainer();
			length--;
		}

		if (self instanceof IncidentDiagram) {

			IncidentDiagram incident = (IncidentDiagram) self;

			// for each source
			for (Asset r : incident.getAsset()) {

				// if type is set then compare against it as well
				if (type != null) {
					if (r.getName().equalsIgnoreCase(name) && r.getType() != null
							&& r.getType().getName().equalsIgnoreCase(type)) {
						return true;
					}
				} else { // else just compare names
					if (r.getName().equalsIgnoreCase(name)) {
						return true;
					}
				}
			}
		} else {
			System.out.println("incident NOT found ");
		}

		if (length == 0) {
			System.out.println("Length reached 0");
		}

		return false;
	}

	/**
	 * checks if the given entity in the condition is a Actor or not in the
	 * incident diagram
	 * 
	 * @param self
	 * @return
	 */
	public boolean isActor(EObject self) {

		return isActor(self, null);
	}

	public boolean isActor(EObject self, String role) {

		if (!(self instanceof Entity)) {
			return false;
		}

		Entity entity = (Entity) self;

		String name = entity.getName();

		int length = 1000;

		while (!(self instanceof IncidentDiagram) && length > 0) {
			self = self.eContainer();
			length--;
		}

		if (self instanceof IncidentDiagram) {

			IncidentDiagram incident = (IncidentDiagram) self;

			// for each source
			for (Actor r : incident.getActor()) {

				// if type is set then compare against it as well
				if (role != null) {
					if (r.getName().equalsIgnoreCase(name) && r.getRole().toString().equalsIgnoreCase(role)) {
						return true;
					}
				} else { // else just compare names
					if (r.getName().equalsIgnoreCase(name)) {
						return true;
					}
				}
			}
		} else {
			System.out.println("incident NOT found ");
		}

		if (length == 0) {
			System.out.println("Length reached 0");
		}

		return false;
	}

	public void updateConditionEntityNames(EObject self, String oldName, String newName) {

		int length = 1000;

		if (self instanceof IncidentEntity) {
			IncidentEntity en = (IncidentEntity) self;
			en.setName(newName);
		}

		while (!(self instanceof IncidentDiagram) && length > 0) {
			self = self.eContainer();
			length--;
		}

		if (self instanceof IncidentDiagram) {

			IncidentDiagram incident = (IncidentDiagram) self;

			// for each scene
			for (Scene scene : incident.getScene()) {

				// for each activity
				for (Activity act : scene.getActivity()) {

					// check precondition
					BigraphExpression preExp = (BigraphExpression) act.getPrecondition().getExpression();
					List<Entity> entities = preExp != null ? preExp.getEntity() : null;

					if (preExp != null && entities != null) {
						for (Entity ent : entities) {

							// if same name entity found replace and move to
							// next entity (no need to check sub-entities as
							// entity can't contain itself
							if (ent.getName().equalsIgnoreCase(oldName)) {
								ent.setName(newName);
								continue;
							}

							List<Entity> subentities = preExp.getContainedEntities(ent.getName());

							for (Entity subEnt : subentities) {
								if (subEnt.getName().equalsIgnoreCase(oldName)) {
									subEnt.setName(newName);
									// continue;
								}
							}

						}

					}

					// check postcondition
					BigraphExpression postExp = (BigraphExpression) act.getPostcondition().getExpression();
					List<Entity> postEntities = preExp != null ? postExp.getEntity() : null;

					if (postExp != null && postEntities != null) {
						for (Entity ent : postEntities) {

							// if same name entity found replace and move to
							// next entity (no need to check sub-entities as
							// entity can't contain itself
							if (ent.getName().equalsIgnoreCase(oldName)) {
								ent.setName(newName);
								continue;
							}

							List<Entity> subentities = postExp.getContainedEntities(ent.getName());

							for (Entity subEnt : subentities) {
								if (subEnt.getName().equalsIgnoreCase(oldName)) {
									subEnt.setName(newName);
									// continue;
								}
							}

						}

					}
				}
			}
		} else {
			System.out.println("incident NOT found ");
		}

		if (length == 0) {
			System.out.println("Length reached 0");
		}

	}

	public List<Condition> getConditions(EObject self) {

		List<Condition> conditions = new LinkedList<Condition>();

		if (!(self instanceof Scene)) {
			return null;
		}

		Scene scene = (Scene) self;

		List<Activity> activities = scene.getActivity();

		for (Activity act : activities) {
			conditions.add(act.getPrecondition());
			conditions.add(act.getPostcondition());
		}

		return conditions;
	}

	public List<Condition> getActivityConditions(EObject self) {

		List<Condition> conditions = new LinkedList<Condition>();

		if (!(self instanceof Activity)) {
			return null;
		}

		Activity act = (Activity) self;

		conditions.add(act.getPrecondition());
		conditions.add(act.getPostcondition());

		return conditions;
	}

	/**
	 * Returns all entities that have the given connectivity
	 * @param self
	 * @return
	 */
	public List<Entity> getConnectivityEntities(EObject self) {
		
		List<Entity> entities = new LinkedList<Entity>();
		
		//self should be a Connectivity obj
		if(!(self instanceof Connectivity)) {
			System.out.println("self is not connectivity");
			return entities;
		}
		
		Connectivity con = (Connectivity)self;
		String conName = ((Connectivity)self).getName();
		
		//if name is empty then return nothing
		if(conName == null || conName.isEmpty()) {
			System.out.println("name is empty");
			return entities;
		}
		
		//get all entities that has the conn name from the condition (it's container)
		EObject container = self.eContainer(); //this gets the entity container
		container = container.eContainer();//this gets the condition
		
		
		//if the container is not BigraphEpxression obj then return
		if(!(container instanceof BigraphExpression)) {
			System.out.println("container is not expression");
			return entities;
		}
		
		BigraphExpression exp = (BigraphExpression) container;
		
		List<Entity> allEntities = getAllConditionEntity(self, exp.getEntity());
		
		//for all entities check connectivity list if it contains the required connectivity (i.e. self/con)
		for(Entity entity : allEntities) {
			
			for(Connectivity subCon : entity.getConnectivity()) {
				if(conName.equalsIgnoreCase(subCon.getName())) {
					entities.add(entity);
				}
			}
			
		}
		
		System.out.println("size: " + entities.size());
		
		return entities;
	}
	
	
	/**********************************************************************************************************
	 * **********************************************************************************************************
	 * **********************************************************************************************************
	 * **********************************************************************************************************
	 * Functionalities below are used by activity pattern
	 * 
	 * 
	 */

	/**
	 * checks if the given entity in the condition is an IncidentEntity or not
	 * in the incident diagram
	 * 
	 * @param self
	 * @return
	 */
	public boolean isIncidentEntityActivityPattern(EObject self) {

		return isIncidentEntityActivityPattern(self, null);
	}

	public boolean isIncidentEntityActivityPattern(EObject self, String type) {

		if (!(self instanceof Entity)) {
			return false;
		}

		Entity entity = (Entity) self;

		String name = entity.getName();

		int length = 1000;

		while (!(self instanceof ActivityPattern) && length > 0) {
			self = self.eContainer();
			length--;
		}

		if (self instanceof ActivityPattern) {

			ActivityPattern ptr = (ActivityPattern) self;

			// for each source
			for (IncidentEntity r : ptr.getIncidententity()) {

				if (!(r.getClass().getName().equalsIgnoreCase(IncidentEntityImpl.class.getName()))) {
					continue;
				}

				// if type is set then compare against it as well
				if (type != null) {
					if (r.getName().equalsIgnoreCase(name) && r.getType() != null
							&& r.getType().getName().equalsIgnoreCase(type)) {
						return true;
					}
				} else { // else just compare names
					if (r.getName().equalsIgnoreCase(name)) {
						return true;
					}
				}
			}
		} else {
			System.out.println("incident NOT found ");
		}

		if (length == 0) {
			System.out.println("Length reached 0");
		}

		return false;
	}

	/**
	 * checks if the given entity in the condition is a Asset or not in the
	 * incident diagram
	 * 
	 * @param self
	 * @return
	 */
	public boolean isAssetActivityPattern(EObject self) {

		return isAssetActivityPattern(self, null);
	}

	public boolean isAssetActivityPattern(EObject self, String type) {

		if (!(self instanceof Entity)) {
			return false;
		}

		Entity entity = (Entity) self;

		String name = entity.getName();

		int length = 1000;

		while (!(self instanceof ActivityPattern) && length > 0) {
			self = self.eContainer();
			length--;
		}

		if (self instanceof ActivityPattern) {

			ActivityPattern ptr = (ActivityPattern) self;

			// for each source
			for (IncidentEntity inc : ptr.getIncidententity()) {

				if (!(inc instanceof Asset)) {
					continue;
				}

				Asset r = (Asset) inc;

				// if type is set then compare against it as well
				if (type != null) {
					if (r.getName().equalsIgnoreCase(name) && r.getType() != null
							&& r.getType().getName().equalsIgnoreCase(type)) {
						return true;
					}
				} else { // else just compare names
					if (r.getName().equalsIgnoreCase(name)) {
						return true;
					}
				}
			}
		} else {
			System.out.println("incident NOT found ");
		}

		if (length == 0) {
			System.out.println("Length reached 0");
		}

		return false;
	}

	public boolean isActorActivityPattern(EObject self) {

		return isActorActivityPattern(self, null);
	}

	public boolean isActorActivityPattern(EObject self, String role) {

		if (!(self instanceof Entity)) {
			return false;
		}

		Entity entity = (Entity) self;

		String name = entity.getName();

		int length = 1000;

		while (!(self instanceof ActivityPattern) && length > 0) {
			self = self.eContainer();
			length--;
		}

		if (self instanceof ActivityPattern) {

			ActivityPattern activityPattern = (ActivityPattern) self;

			// for each source
			for (IncidentEntity inc : activityPattern.getIncidententity()) {

				if (!(inc instanceof Actor)) {
					continue;
				}

				Actor r = (Actor) inc;

				// if type is set then compare against it as well
				if (role != null) {
					if (r.getName().equalsIgnoreCase(name) && r.getRole().toString().equalsIgnoreCase(role)) {
						return true;
					}
				} else { // else just compare names
					if (r.getName().equalsIgnoreCase(name)) {
						return true;
					}
				}
			}
		} else {
			System.out.println("incident NOT found ");
		}

		if (length == 0) {
			System.out.println("Length reached 0");
		}

		return false;
	}

	/**
	 * checks if the given entity in the condition is a Source or not in the
	 * incident diagram
	 * 
	 * @param self
	 * @return
	 */
	public boolean isResourceActivityPattern(EObject self) {

		return isResourceActivityPattern(self, null);
	}

	public boolean isResourceActivityPattern(EObject self, String type) {

		if (!(self instanceof Entity)) {
			return false;
		}

		Entity entity = (Entity) self;

		String name = entity.getName();

		int length = 1000;

		while (!(self instanceof ActivityPattern) && length > 0) {
			self = self.eContainer();
			length--;
		}

		if (self instanceof ActivityPattern) {

			ActivityPattern ptr = (ActivityPattern) self;

			// for each source
			for (IncidentEntity r : ptr.getIncidententity()) {

				if (!(r instanceof Resource)) {
					continue;
				}

				// if type is set then compare against it as well
				if (type != null) {
					if (r.getName().equalsIgnoreCase(name) && r.getType() != null
							&& r.getType().getName().equalsIgnoreCase(type)) {
						return true;
					}
				} else { // else just compare names
					if (r.getName().equalsIgnoreCase(name)) {
						return true;
					}
				}
			}
		} else {
			System.out.println("incident NOT found ");
		}

		if (length == 0) {
			System.out.println("Length reached 0");
		}

		return false;
	}

	public List<Condition> getConditionsActPtrn(EObject self) {

		List<Condition> conditions = new LinkedList<Condition>();

		if (!(self instanceof ActivityPattern)) {
			return null;
		}

		ActivityPattern ptr = (ActivityPattern) self;

		List<Activity> activities = ptr.getAbstractActivity();

		for (Activity act : activities) {
			conditions.add(act.getPrecondition());
			conditions.add(act.getPostcondition());
		}

		return conditions;
	}

}
