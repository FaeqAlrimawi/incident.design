package incident.design;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.eclipse.emf.ecore.EObject;

import cyberPhysical_Incident.Activity;
import cyberPhysical_Incident.ActivityPattern;
import cyberPhysical_Incident.Actor;
import cyberPhysical_Incident.Asset;
import cyberPhysical_Incident.BigraphExpression;
import cyberPhysical_Incident.Condition;
import cyberPhysical_Incident.Connection;
import cyberPhysical_Incident.Connectivity;
import cyberPhysical_Incident.CrimeScript;
import cyberPhysical_Incident.CyberPhysicalIncidentFactory;
import cyberPhysical_Incident.Entity;
import cyberPhysical_Incident.IncidentDiagram;
import cyberPhysical_Incident.IncidentEntity;
import cyberPhysical_Incident.Postcondition;
import cyberPhysical_Incident.Precondition;
import cyberPhysical_Incident.Property;
import cyberPhysical_Incident.Resource;
import cyberPhysical_Incident.Scene;
import cyberPhysical_Incident.ScriptCategory;
import cyberPhysical_Incident.Type;
import cyberPhysical_Incident.Vulnerability;
import cyberPhysical_Incident.impl.IncidentEntityImpl;
import environment.Action;
import environment.CyberPhysicalSystemPackage;
import environment.EnvironmentDiagram;
import incident.util.BigraphERTokens;
import incident.util.Tokenizer;

/**
 * The services class used by VSM.
 */
public class Services {

	SystemInstanceHandler sysHandler = new SystemInstanceHandler();

	List<Type> systemAssetTypes;
	List<Type> systemConnectionTypes;

	Tokenizer brsTokenizer;

	/**
	 * See
	 * http://help.eclipse.org/neon/index.jsp?topic=%2Forg.eclipse.sirius.doc%2Fdoc%2Findex.html&cp=24
	 * for documentation on how to write service methods.
	 */

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
				} else { // if the target (child) parent is not null, then
							// remove the target from its parent
					desParent.getContainedEntities().remove(des);
					des.setParentEntity(src);
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
		List<String> conNames = new LinkedList<String>();

		List<Entity> toVisit = new LinkedList<Entity>();

		// allContained.addAll(entities);
		toVisit.addAll(entities);

		int length = 100000;

		while (!toVisit.isEmpty() && length > 0) {

			Entity ent = toVisit.remove(0);

			// allConnectivity.addAll(ent.getConnectivity());

			for (Connectivity con : ent.getConnectivity()) {

				// only include unique connectivity
				if (conNames.contains(con.getName())) {
					continue;
				}

				allConnectivity.add(con);
				conNames.add(con.getName());
			}

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

	/**
	 * Returns a random name (partialName+"random number")
	 * 
	 * @param self
	 * @param partialName
	 * @return
	 */
	public String getRandomName(EObject self, String partialName) {

		Random rand = new Random();

		int maxNumber = 10000;
		String newName = partialName + rand.nextInt(maxNumber);

		boolean isUnique = false;

		// navigate to the condition expression
		int length = 1000;

		EObject container = self;

		while (!(container instanceof BigraphExpression) && length > 0) {
			container = container.eContainer();
			length--;
		}

		if ((container instanceof BigraphExpression)) {

			int tries = 100;

			BigraphExpression exp = (BigraphExpression) container;

			List<Connectivity> connectivities = getAllConditionConnectivity(self, exp.getEntity());

			loop: while (!isUnique && tries > 0) {

				// check connectivity names to see if new name is unique or not
				for (Connectivity con : connectivities) {
					if (newName.equalsIgnoreCase(con.getName())) {
						tries--;
						continue loop;
					}
				}

				// if this is reached then the new name is unique
				isUnique = true;
			}

			// if name is unique
			if (isUnique) {
				return newName;
			} else {
				// try one more time and then return random name
				newName = partialName + rand.nextInt();
			}
		} else {
			// create a new random name
			newName = partialName + rand.nextInt();
		}

		return newName;

	}

	/**
	 * Copies the given self (as IncidentEntity) into the given contianer (as IncidentDiagram)
	 * @param self
	 * @param container
	 */
	public void copyIncidentEntity(EObject self, EObject container) {

		// self is incident entity
		// container is incident diagram

		CyberPhysicalIncidentFactory instance = CyberPhysicalIncidentFactory.eINSTANCE;

		try {

			// self should be an incident entity
			if (!(self instanceof IncidentEntity)) {
				return;
			}

			// container should be an incident entity
			if (!(container instanceof IncidentDiagram)) {
				return;
			}

			IncidentEntity original = (IncidentEntity) self;
			IncidentDiagram diagram = (IncidentDiagram) container;

			// create an incident entity
			IncidentEntity copiedEntity = (IncidentEntity) instance.create(original.eClass());

			// copy information from original
			Type originalType = original.getType();

			// set type
			copiedEntity.setType(originalType);

			// set connections knowledge
			copiedEntity.setConnectionsKnowledge(original.getConnectionsKnowledge());

			// set contained asset knowledge
			copiedEntity.setContainedAssetsKnowledge(original.getContainedAssetsKnowledge());

			// set mobility
			copiedEntity.setMobility(original.getMobility());

			// set properties
			List<Property> props = new LinkedList<Property>();

			for (Property prop : original.getProperties()) {
				Property tmp = instance.createProperty();
				tmp.setName(prop.getName());
				tmp.setValue(prop.getValue());
				props.add(tmp);
			}
			copiedEntity.getProperties().addAll(props);

			
			// add new copied element to diagram
			if (copiedEntity instanceof Asset) { // as Asset
				
				// set attributes
				List<Vulnerability> vuls = new LinkedList<Vulnerability>();

				for (Vulnerability vul : ((Asset)original).getVulnerability()) {
					Vulnerability tmp = instance.createVulnerability();
					tmp.setName(vul.getName());
					tmp.setURL(vul.getURL());
					tmp.setDescription(vul.getDescription());
					tmp.setSeverity(vul.getSeverity());
					vuls.add(tmp);
				}
				((Asset)copiedEntity).getVulnerability().addAll(vuls);

				//set status
				((Asset)copiedEntity).setStatus(((Asset)original).getStatus());
				
				diagram.getAsset().add((Asset) copiedEntity);
				
			} else if (copiedEntity instanceof Actor) { // as Actor
				
				//set role
				((Actor)copiedEntity).setRole(((Actor)original).getRole());
				
				diagram.getActor().add((Actor) copiedEntity);
				
			} else if (copiedEntity instanceof Resource) { // as Resource
			
				diagram.getResource().add((Resource) copiedEntity);
				
			} else if (copiedEntity instanceof IncidentEntity) { // as incident
																	// entity
				diagram.getIncidentEntity().add(copiedEntity);
			}

		} catch (IllegalArgumentException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 * 
	 * @param self
	 * @return
	 */
	public List<Entity> getConnectivityEntities(EObject self) {

		List<Entity> entities = new LinkedList<Entity>();

		// self should be a Connectivity obj
		if (!(self instanceof Connectivity)) {
			System.out.println("self is not connectivity");
			return entities;
		}

		Connectivity con = (Connectivity) self;
		String conName = ((Connectivity) self).getName();

		// if name is empty then return nothing
		if (conName == null || conName.isEmpty()) {
			System.out.println("name is empty");
			return entities;
		}

		// get all entities that has the conn name from the condition (it's
		// container)
		EObject container = self.eContainer(); // this gets the entity container
		container = container.eContainer();// this gets the condition

		int length = 1000;
		// if the container is not BigraphEpxression obj then return
		while (!(container instanceof BigraphExpression) && length > 0) {
			// System.out.println("container is not expression");

			container = container.eContainer();
			length--;
		}

		if (length == 0) {
			System.out.println("container is not bigraph expression");
			return entities;
		}

		BigraphExpression exp = (BigraphExpression) container;

		List<Entity> allEntities = getAllConditionEntity(self, exp.getEntity());

		// for all entities check connectivity list if it contains the required
		// connectivity (i.e. self/con)
		for (Entity entity : allEntities) {

			for (Connectivity subCon : entity.getConnectivity()) {
				if (conName.equalsIgnoreCase(subCon.getName())) {
					entities.add(entity);
				}
			}

		}

		return entities;
	}

	/**
	 * Removes all connectivities that has the same name as the given one
	 * 
	 * @param self
	 *            Connectivity
	 * @return
	 */
	public void removeConnectivity(EObject self) {

		List<Connectivity> connectivitiesToRemove = new LinkedList<Connectivity>();

		// self should be a Connectivity obj
		if (!(self instanceof Connectivity)) {
			System.err.println("self is not connectivity");
			return;
		}

		Connectivity con = (Connectivity) self;
		String conName = ((Connectivity) self).getName();

		// if name is empty then return nothing
		if (conName == null || conName.isEmpty()) {
			System.err.println("name is empty");
			return;
		}

		// get all entities that has the conn name from the condition (it's
		// container)
		EObject container = self.eContainer(); // this gets the entity container
		container = container.eContainer();// this gets the condition

		int length = 1000;
		// if the container is not BigraphEpxression obj then return
		while (!(container instanceof BigraphExpression) && length > 0) {
			// System.out.println("container is not expression");

			container = container.eContainer();
			length--;
		}

		if (!(container instanceof BigraphExpression)) {
			System.err.println("container is not bigraph expression");
			return;
		}

		if (length == 0) {
			System.err.println("bigraph expression not found");
			return;
		}

		BigraphExpression exp = (BigraphExpression) container;

		List<Entity> allEntities = getAllConditionEntity(self, exp.getEntity());

		// for all entities check connectivity list if it contains the required
		// connectivity (i.e. self/con)
		for (Entity entity : allEntities) {

			List<Connectivity> entityCon = entity.getConnectivity();
			connectivitiesToRemove.clear();

			for (Connectivity subCon : entityCon) {
				if (conName.equalsIgnoreCase(subCon.getName())) {
					connectivitiesToRemove.add(subCon);
				}
			}

			entityCon.removeAll(connectivitiesToRemove);
		}

	}

	public void removeEntityContainmentRelation(EObject parent, EObject child) {
		
		//both should be Entity objects
		
		if(!(parent instanceof Entity)) {
			return;
		}
		
		if(!(child instanceof Entity)) {
			return;
		}
		
		Entity pEntity = (Entity) parent;
		Entity cEntity = (Entity)child;
		
		System.out.println(pEntity.getName() + " "+cEntity.getName());
		//get condition 
		EObject container = pEntity.eContainer();	
		int length  = 1000;
	
		while(!(container instanceof BigraphExpression) && length>0) {
			
			container = container.eContainer();
			length--;
		}
		
		if(container instanceof BigraphExpression) {
			
			//get bigraph expression
			BigraphExpression exp = (BigraphExpression) container;
			
			//remove child from old parent
			pEntity.getEntity().remove(cEntity);
			
			//add child to the express as a new root
			exp.getEntity().add(cEntity);
		}
		
		
		
		
	}
	/**
	 * Sets the name of every connectivity in the condition that has the oldName
	 * 
	 * @param self
	 * @param oldName
	 * @param newName
	 */
	public void setConnectivityName(EObject self, String oldName, String newName) {

		if (!(self instanceof Connectivity)) {
			System.out.println("self is not connectivity");
			return;
		}

		// Connectivity con = (Connectivity)self;
		//
		// //set the name of the given connectivity (i.e. self)
		// con.setName(newName);

		// change all connectivity entities that has the same old name

		// get expression
		EObject obj = self.eContainer();

		int length = 1000;

		while (!(obj instanceof BigraphExpression) && length > 0) {
			// System.out.println("contianer is not bigraph expression");

			obj = obj.eContainer();
			length--;
		}

		if (length == 0) {
			System.out.println("container is not bigraph expression");
			return;
		}

		BigraphExpression exp = (BigraphExpression) obj;

		List<Entity> allEntities = getAllConditionEntity(self, exp.getEntity());

		for (Entity ent : allEntities) {

			// update name for each connectivity if it matches to old name
			for (Connectivity con : ent.getConnectivity()) {
				if (con.getName().equalsIgnoreCase(oldName)) {
					con.setName(newName);
				}
			}
		}

	}

	/**
	 * Returns all available incident entities objects in the incident diagram
	 * 
	 * @param self
	 *            could be any element in the diagram
	 * @return
	 */
	public List<IncidentEntity> getAllIncidentEntities(EObject self) {

		List<IncidentEntity> entities = new LinkedList<IncidentEntity>();

		// find root element (incident diagram)

		EObject container = self;
		int length = 1000;

		while (!(container instanceof IncidentDiagram) && length > 0) {

			container = container.eContainer();
			length--;
		}

		if (!(container instanceof IncidentDiagram)) {
			System.out.println("Incident Digram object is not found");
			return entities;
		}

		IncidentDiagram incident = (IncidentDiagram) container;

		// add all assets
		entities.addAll(incident.getAsset());
		// add all actors
		entities.addAll(incident.getActor());
		// add all resources
		entities.addAll(incident.getResource());
		// add all other incident entities
		entities.addAll(incident.getIncidentEntity());

		return entities;
	}

	/**
	 * Returns all connection objects defined in the incident diagram
	 * @param self
	 * @return
	 */
	public List<Connection> getAllIncidentConnections(EObject self) {
	
		
		//get root element (Incident diagram)
		IncidentDiagram incident = getIncidentDiagram(self);
		
		if(incident == null) {
			return null;
		}
		
		return incident.getConnection();
	}
	
	/**
	 * returns root element IncidentDiagram
	 * 
	 * @return
	 */
	protected IncidentDiagram getIncidentDiagram(EObject self) {

		int length = 10000;

		EObject container = self;

		while (!(container instanceof IncidentDiagram) && length > 0) {

			container = container.eContainer();
			length--;
		}

		if (!(container instanceof IncidentDiagram)) {
			System.err.println("Root element (IncidentDiagram) object is not found");
			return null;
		}

		return (IncidentDiagram) container;

	}

	/**
	 * Determines whether the incident is instance or not (could be a pattern)
	 * 
	 * @param self
	 * @return
	 */
	public boolean isIncidentInstance(EObject self) {

		IncidentDiagram inc = getIncidentDiagram(self);

		// if root element is not found return false
		if (inc == null) {
			return false;
		}

		CrimeScript script = inc.getCrimeScript();

		// if there's no crime script defined then return false;
		if (script == null) {
			return false;
		}

		if (script.getCategory().equals(ScriptCategory.INSTANCE)) {
			return true;
		}

		return false;

	}

	
	public List<Type> getSystemTypes(EObject self) {

		// if the selected entity is an incident entity, then return all system
		// classes that are subclasses of Asset
		if ((self instanceof IncidentEntity) || (self instanceof Type && self.eContainer() instanceof IncidentEntity)) {
			return getSystemAssetTypes(self);
		}

		// if the selected entity in a connection, then return all classes from
		// the system meta-model that are subclasses of Connection
		if ((self instanceof Connection) || (self instanceof Type && self.eContainer() instanceof Connection)) {
			return getSystemConnectionTypes(self);
		}

		return null;
	}

	/**
	 * Returns all asset types from the system meta-model
	 * 
	 * @param self
	 * @return
	 */
	public List<Type> getSystemAssetTypes(EObject self) {

		// an implementation could use the system handler instance to do this

		if (systemAssetTypes != null && !systemAssetTypes.isEmpty()) {
			return systemAssetTypes;
		}

		systemAssetTypes = new LinkedList<Type>();

		CyberPhysicalIncidentFactory instance = CyberPhysicalIncidentFactory.eINSTANCE;

		// read the system meta-model and identify all classes and convert them
		// into types
		Method[] packageMethods = CyberPhysicalSystemPackage.class.getDeclaredMethods();

		// Map<String, List<String>> classMap = new HashMap<String,
		// List<String>>();

		String className = null;

		for (Method mthd : packageMethods) {

			className = mthd.getName();
			Class cls = mthd.getReturnType();

			// only consider EClass as the classes
			if (!cls.getSimpleName().equals("EClass")) {
				continue;
			}

			// remove [get] at the beginning
			// if it contains __ then it is not a class its an attribute
			if (className.startsWith("get")) {
				className = className.replace("get", "");

				// create a class from the name
				String fullClassName = "environment.impl." + className + "Impl";

				Class potentialClass;

				try {
					potentialClass = Class.forName(fullClassName);

					// class is not of type asset
					if (!(environment.Asset.class.isAssignableFrom(potentialClass))) {
						continue;
					}
					// create e type based on class name
					Type tmp = instance.createType();
					tmp.setName(className);
					systemAssetTypes.add(tmp);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					// if it is not a class then skip
				}
			}

		}

		return systemAssetTypes;
	}

	/**
	 * Returns all connection types from the system meta-model
	 * 
	 * @param self
	 * @return
	 */
	public List<Type> getSystemConnectionTypes(EObject self) {

		// an implementation could use the system handler instance to do this

		if (systemConnectionTypes != null && !systemConnectionTypes.isEmpty()) {
			return systemConnectionTypes;
		}

		systemConnectionTypes = new LinkedList<Type>();

		CyberPhysicalIncidentFactory instance = CyberPhysicalIncidentFactory.eINSTANCE;

		// read the system meta-model and identify all classes and convert them
		// into types
		Method[] packageMethods = CyberPhysicalSystemPackage.class.getDeclaredMethods();

		// Map<String, List<String>> classMap = new HashMap<String,
		// List<String>>();

		String className = null;

		for (Method mthd : packageMethods) {

			className = mthd.getName();
			Class cls = mthd.getReturnType();

			// only consider EClass as the classes
			if (!cls.getSimpleName().equals("EClass")) {
				continue;
			}

			// remove [get] at the beginning
			// if it contains __ then it is not a class its an attribute
			if (className.startsWith("get")) {
				className = className.replace("get", "");

				// create a class from the name
				String fullClassName = "environment.impl." + className + "Impl";

				Class potentialClass;

				try {
					potentialClass = Class.forName(fullClassName);

					// class is not of type Connection then skip
					if (!(environment.Connection.class.isAssignableFrom(potentialClass))) {
						continue;
					}

					// create e type based on class name
					Type tmp = instance.createType();
					tmp.setName(className);
					systemConnectionTypes.add(tmp);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					// if it is not a class then skip
				}
			}

		}

		return systemConnectionTypes;
	}

	/******************************
	 * SYSTEM
	 * FUNCTIONS*************************************************************
	 * *********************************************************************************************************
	 * *********************************************************************************************************
	 * *********************************************************************************************************
	 * 
	 * @return
	 */

	public EnvironmentDiagram getSystemInstance(EObject self) {

		return sysHandler.getInstance();

	}

	public EnvironmentDiagram getSystemInstance(EObject self, String fileName) {

		return sysHandler.getInstance(fileName);

	}

	/**
	 * Return the list of action names from the system instance model
	 * 
	 * @param self
	 * @return
	 */
	public List<Action> getSystemActions(EObject self) {

		// dummy
		List<String> dummy = new LinkedList<String>();
		dummy.add("enterRoom");
		dummy.add("connectDevice");

		// return dummy;
		return sysHandler.getDummyActions();
	}

	/**
	 * Updates the condition of the given activity (self) according to the given
	 * BRS condition
	 * 
	 * @param self
	 * @param cond
	 * @param BRScondition
	 */
	public void updateConditions(EObject self, EObject action) {

		// if self is not activity then do nothing
		if (!(self instanceof Activity)) {
			System.err.println("updateCondition: self is Not activity");
			return;
		}

		Activity activity = (Activity) self;

		if (!(action instanceof Action)) {
			System.err.println("updateCondition: action is Not Action");
			return;
		}

		Action act = (Action) action;

		// update precondition
		Precondition activityPre = activity.getPrecondition();

		String brsPreCond = (act.getPreconditions() != null && !act.getPreconditions().isEmpty())
				? act.getPreconditions().get(0) : null;

		BigraphExpression newPreBRS = parseActionBRSCondition(brsPreCond);

		activityPre.setExpression(newPreBRS);

		// update postcondition
		Postcondition activityPost = activity.getPostcondition();

		String brsPostCond = (act.getPostconditions() != null && !act.getPostconditions().isEmpty())
				? act.getPostconditions().get(0) : null;

		// BigraphExpression newPostBRS = parseActionBRSCondition(brsPostCond);
		//
		// activityPost.setExpression(newPostBRS);

	}

	/**
	 * Parses the given condition in BRS format to identify entities and
	 * connectivity then creates a new condition based on that
	 * 
	 * @param BRScondition
	 * @return Condition
	 */
	public BigraphExpression parseActionBRSCondition(String BRScondition) {

		// if(brsTokenizer == null) {
		createBRSTokenizer();
		// }

		CyberPhysicalIncidentFactory instance = CyberPhysicalIncidentFactory.eINSTANCE;

		int rootNum = 0;

		LinkedList<Entity> rootEntities = new LinkedList<Entity>();
		LinkedList<Entity> allEntities = new LinkedList<Entity>();
		LinkedList<Entity> containers = new LinkedList<Entity>();
		LinkedList<String> closedConnectivities = new LinkedList<String>();

		// boolean isBracketContainment = false;
		boolean isContainment = false;
		boolean isFirstEntity = true;
		boolean isBigraphJuxta = false;
		boolean isEntityJuxta = false;
		boolean hasSite = false;
		boolean isConnectivity = false;
		boolean isClosedConnectivity = false;

		// ===tokenize
		brsTokenizer.tokenize(BRScondition);
		for (Tokenizer.Token tok : brsTokenizer.getTokens()) {
			switch (tok.token) {

			case BigraphERTokens.CONTAINMENT: // .

				// add to the container the last entity in all entities
				containers.addFirst(allEntities.getLast());
				isContainment = true;

				break;

			case BigraphERTokens.OPEN_BRACKET: // (

				if (!containers.isEmpty()) {
					// isBracketContainment = true;
					isContainment = false;
				}

				break;

			case BigraphERTokens.CLOSED_BRACKET: // )

				// remove a container from the list of containers
				if (!containers.isEmpty()) {

					// check if it has site
					if (!hasSite) {
						containers.getFirst().setSite(null);
						containers.getFirst().setHasSite(false);
					} else { // reset
						hasSite = false;
					}

					containers.pop();
				}

				if (containers.isEmpty()) {
					// isBracketContainment = false;
				}

				break;

			case BigraphERTokens.ENTITY_JUXTAPOSITION: // |
				// next element should be contained in the same entity as the
				// previous
				isEntityJuxta = true;

				break;
			case BigraphERTokens.BIGRAPH_JUXTAPOSITION: // ||

				// next element should be a root element
				isBigraphJuxta = true;

				break;

			case BigraphERTokens.SITE:// id

				// by default a site is created with each entity

				// if the token is site then if it is containment add site to
				// last add to all entities
				// done by default

				// else if container is not empty then add to the head of the
				// container list
				// done by default

				// just look for cases where site needs to be removed

				// maybe you should cover when site is in bigraph juxtaposition
				if (isBigraphJuxta) {
					// do something
				}

				hasSite = true;

				break;

			case BigraphERTokens.OPEN_BRACKET_CONNECTIVITY:// {

				isConnectivity = true;
				// name or words are recognised as connectivity for last added
				// entity in all entities until closing the bracket
				break;

			case BigraphERTokens.CLOSED_BRACKET_CONNECTIVITY: // }

				// connectivity names ended
				isConnectivity = false;
				break;

			case BigraphERTokens.CLOSED_CONNECTIVITY: // e.g., /con

				// next token should be a name that relates to a connectivity
				// but it is closed
				isClosedConnectivity = true;

				break;
			case BigraphERTokens.COMMA: // ,
				// defines different names
				// nothing to be done
				break;

			case BigraphERTokens.WORD: // entity or connectivity

				// if closed connectivity token appeared, then the word is a
				// connectivity name that
				if (isClosedConnectivity) {
					closedConnectivities.add(tok.sequence);
					isClosedConnectivity = false;
				}
				// if it is connectivity
				else if (isConnectivity) {
					// create connectivity for last added entity in all entities
					Entity lastAdded = allEntities.getLast();

					Connectivity tmpCon = instance.createConnectivity();
					tmpCon.setName(tok.sequence);
					lastAdded.getConnectivity().add(tmpCon);

					// close connectivity
					if (!closedConnectivities.isEmpty()) {
						if (tok.sequence.equalsIgnoreCase(closedConnectivities.getLast())) {
							tmpCon.setIsClosed(true);
							isClosedConnectivity = false;
							closedConnectivities.removeLast(); // remove last
						}
					}

					// if it is entity
				} else {
					// create an entity
					Entity tmp = instance.createEntity();
					tmp.setName("<" + tok.sequence + ">");
					allEntities.add(tmp);

					// check if containers are not empty, if so, then get the
					// head (first element as the current container)
					if (!containers.isEmpty()) {

						Entity currentContainer = containers.getFirst();

						currentContainer.getEntity().add(tmp);

						// System.out.println("entity " + tok.sequence + " is
						// contained in " + currentContainer.getName());

						// if containment is not within brackets ()
						if (isContainment) {
							// System.out.println("removing container: " +
							// currentContainer.getName());
							// it has no site then! so remove it
							currentContainer.setSite(null);
							currentContainer.setHasSite(false);
							containers.removeFirst();
							isContainment = false;
						}
					} else if (isBigraphJuxta) { // if entity after ||
						rootEntities.add(tmp);
						isBigraphJuxta = false;

					} else if (isEntityJuxta) { // if entity after |
						// then the last added entity should be remove from the
						// root and a new entity created that combines both
						Entity lastRoot = rootEntities.removeLast();
						Entity newRoot = instance.createEntity();

						newRoot.setName("<Root-" + rootNum + ">");
						newRoot.getEntity().add(lastRoot);
						newRoot.getEntity().add(tmp);

						// for now root is not added to all entities
						rootEntities.add(newRoot);
						isEntityJuxta = false;

						rootNum++;
					}

					else { // if entity is not contained anywhere

						// if entity is the first one
						if (isFirstEntity) {
							rootEntities.add(tmp);
							isFirstEntity = false;
						}

					}
				}
				break;
			default:
				// nothing
				// System.out.println("ignoring " + tok.sequence);

			}
		}

		// ===create bigraph expression
		BigraphExpression newBRS = instance.createBigraphExpression();

		newBRS.getEntity().addAll(rootEntities);

		return newBRS;
	}

	protected void createBRSTokenizer() {

		brsTokenizer = new Tokenizer();

		brsTokenizer.add(BigraphERTokens.TOKEN_CONTAINMENT, BigraphERTokens.CONTAINMENT);
		brsTokenizer.add(BigraphERTokens.TOKEN_COMPOSITION, BigraphERTokens.COMPOSITION);
		brsTokenizer.add(BigraphERTokens.TOKEN_BIGRAPH_JUXTAPOSITION, BigraphERTokens.BIGRAPH_JUXTAPOSITION);
		brsTokenizer.add(BigraphERTokens.TOKEN_ENTITY_JUXTAPOSITION, BigraphERTokens.ENTITY_JUXTAPOSITION);
		brsTokenizer.add(BigraphERTokens.TOKEN_SITE, BigraphERTokens.SITE);
		brsTokenizer.add(BigraphERTokens.TOKEN_OPEN_BRACKET, BigraphERTokens.OPEN_BRACKET);
		brsTokenizer.add(BigraphERTokens.TOKEN_CLOSED_BRACKET, BigraphERTokens.CLOSED_BRACKET);
		brsTokenizer.add(BigraphERTokens.TOKEN_OPEN_BRACKET_CONNECTIVITY, BigraphERTokens.OPEN_BRACKET_CONNECTIVITY);
		brsTokenizer.add(BigraphERTokens.TOKEN_CLOSED_BRACKET_CONNECTIVITY,
				BigraphERTokens.CLOSED_BRACKET_CONNECTIVITY);
		brsTokenizer.add(BigraphERTokens.TOKEN_CLOSED_CONNECTIVITY, BigraphERTokens.CLOSED_CONNECTIVITY);
		brsTokenizer.add(BigraphERTokens.TOKEN_COMMA, BigraphERTokens.COMMA);
		brsTokenizer.add(BigraphERTokens.TOKEN_SMALL_SPACE, BigraphERTokens.SMALL_SPACE);
		brsTokenizer.add(BigraphERTokens.TOKEN_WORD, BigraphERTokens.WORD);

	}

	/**
	 * updates the contained entities of the given element by adding the target
	 * while removing the source. Also updates the parent of both source and
	 * target to reflect the change
	 * 
	 * @param self
	 * @param element
	 * @param source
	 * @param target
	 */
	public void updateContainedEntities(EObject self, IncidentEntity element, IncidentEntity source,
			IncidentEntity target) {

		List<IncidentEntity> elementContianedEntities = (List) element.getContainedEntities();

		// the target is not already included in the containedEntities of the
		// element then add it

		if (!elementContianedEntities.contains(target)) {

			// add traget to element contained entities
			elementContianedEntities.add(target);

			// update target parent entity (set to element)
			// remove target from old parent
			IncidentEntity targetParent = (IncidentEntity) target.getParentEntity();

			if (targetParent != null) {
				targetParent.getContainedEntities().remove(target);
			}

			target.setParentEntity(element);

			// remove source from element contained elements
			elementContianedEntities.remove(source);

			// update source parent (set to null)
			source.setParentEntity(null);

		}
	}

	/**
	 * updates the parent entity of the given element by setting the target as a
	 * parent while removing the source. Also updates the contained entities of
	 * both source and target to reflect the change
	 * 
	 * @param self
	 * @param element
	 * @param source
	 * @param target
	 */
	public void updateParentEntity(EObject self, IncidentEntity element, IncidentEntity source, IncidentEntity target) {

		System.out.println(
				"element: " + element.getName() + " source: " + source.getName() + " target: " + target.getName());
		List<IncidentEntity> targetContianedEntities = (List) target.getContainedEntities();
		List<IncidentEntity> sourceContianedEntities = (List) source.getContainedEntities();

		// the target is not already including in the containedEntities the
		// element then add the element
		if (!targetContianedEntities.contains(target)) {

			// add traget to element contained entities
			targetContianedEntities.add(element);

			// remove element from old parent (source)
			sourceContianedEntities.remove(element);

			// update element parent entity (set to target)
			element.setParentEntity(target);

		}
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

	public boolean isConditionEntity(EObject self) {

		if (self instanceof Entity) {
			return true;
		}

		return false;
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
