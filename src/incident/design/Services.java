package incident.design;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
import cyberPhysical_Incident.Goal;
import cyberPhysical_Incident.IncidentDiagram;
import cyberPhysical_Incident.IncidentEntity;
import cyberPhysical_Incident.Location;
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
import fxml.view.GeneralFXFrame;
import fxml.view.GeneralFXFrame.JFXPanel;
import incident.util.BRSParser;

/**
 * The services class used by VSM.
 */
public class Services {

	SystemInstanceHandler sysHandler = new SystemInstanceHandler();

	// a map of the types in which the
	Map<Type, List<Type>> systemAssetTypes;
	Map<Type, List<Type>> systemConnectionTypes;

	BRSParser brsParser;


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

			// // ignore if entity is not asset
			// if
			// (!(entity.getClass().getName().equalsIgnoreCase(IncidentEntityImpl.class.getName())))
			// {
			// continue;
			// }

			String entTypeName = entity.getType() != null ? entity.getType().getName() : null;

			// if the entity type is the same as the given type then move to
			// next (ignore)
			if ((entTypeName != null && entTypeName.equalsIgnoreCase(type))) {
				entitiesIgnored.add(entity);
				continue;
			}

			IncidentEntity parent = (IncidentEntity) entity.getParentEntity();
			int length = 10000;

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

	/**
	 * Hides all the incident entities if they or their parents have one of the
	 * given types.
	 * 
	 * @param self
	 * @param list
	 * @param types
	 * @return
	 */
	public Collection<IncidentEntity> hideIncidentEntitiesWithType(EObject self, Collection<IncidentEntity> list,
			Collection<String> types) {

		List<IncidentEntity> tmp = new LinkedList<IncidentEntity>();
		List<IncidentEntity> entitiesIgnored = new LinkedList<IncidentEntity>();

		loop_entities: for (IncidentEntity entity : list) {
			//
			// // ignore if entity is not asset
			// if
			// (!(entity.getClass().getName().equalsIgnoreCase(IncidentEntityImpl.class.getName())))
			// {
			// continue;
			// }

			String entTypeName = entity.getType() != null ? entity.getType().getName() : null;

			if (entTypeName != null) {
				entTypeName = entTypeName.toLowerCase();
			}

			// if the entity type is the same as the given type then move to
			// next (ignore)
			if ((entTypeName != null && types.contains(entTypeName))) {
				entitiesIgnored.add(entity);
				continue;
			}

			IncidentEntity parent = (IncidentEntity) entity.getParentEntity();
			int length = 10000;

			while (parent != null && length > 0) {

				// if the parent already has been ignored then move on to next
				// parent
				if (entitiesIgnored.contains(parent)) {
					continue loop_entities;
				}

				String entParentTypeName = (parent.getType() != null) ? parent.getType().getName() : null;

				if (entParentTypeName != null) {
					entParentTypeName = entParentTypeName.toLowerCase();
				}

				// if the parent has the same type as the given type then move
				// on to next parent
				if (entParentTypeName != null && types.contains(entParentTypeName)) {
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

	public boolean isOfType(EObject self, String type) {

		if (!(self instanceof IncidentEntity)) {
			System.err.println("not an incident entity");
			return false;
		}

		IncidentEntity entity = (IncidentEntity) self;

		String entTypeName = entity.getType() != null ? entity.getType().getName() : null;

		// if the entity type is the same as the given type then move to
		// next (ignore)
		if ((entTypeName != null && entTypeName.equalsIgnoreCase(type))) {
			return true;
		}

		return false;

	}

	public void updateParentNewEdge(EObject self, IncidentEntity src, IncidentEntity des) {

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

	}

	/**
	 * Returns all contained entities in the given self (as incident entity)
	 * 
	 * @param self
	 * @param containedEntities
	 * @return
	 */
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

	public boolean isAssetRoom(EObject self) {

		// if it is not asset then return false
		if (!(self instanceof Asset)) {
			return false;
		}

		// check type to be room
		Asset ast = (Asset) self;

		String type = ast.getType().getName();

		if (type != null && type.equalsIgnoreCase("room")) {
			return true;
		}

		return false;
	}

	/**
	 * Returns a random name for connectivity (partialName+"random number")
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
	 * Returns a random name for an incident entity (partialName+"random
	 * number")
	 * 
	 * @param self
	 * @param partialName
	 * @return
	 */
	public String getRandomEntityName(EObject self, String partialName) {

		Random rand = new Random();

		int maxNumber = 100;
		String newName = null;

		boolean isUnique = false;

		int tries = 100;

		int cnt = 0;
		int round = 10;

		List<IncidentEntity> allEntities = getAllIncidentAssets(self);

		maxNumber = allEntities.size() * 10; // initially 10 times the number of
												// entities
		loop: while (!isUnique && tries > 0) {

			newName = partialName + rand.nextInt(maxNumber);

			// check names to see if new name is unique or not
			for (IncidentEntity ent : allEntities) {
				if (newName.equalsIgnoreCase(ent.getName())) {
					tries--;

					cnt++; // used to count number of tries before increasing
							// the maxNumber

					if (cnt == round) {
						cnt = 0;
						maxNumber *= 2; // double the number
					}

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

		return newName;

	}

	/**
	 * Copies the given self (as IncidentEntity) into the given contianer (as
	 * IncidentDiagram)
	 * 
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

				for (Vulnerability vul : ((Asset) original).getVulnerability()) {
					Vulnerability tmp = instance.createVulnerability();
					tmp.setName(vul.getName());
					tmp.setURL(vul.getURL());
					tmp.setDescription(vul.getDescription());
					tmp.setSeverity(vul.getSeverity());
					vuls.add(tmp);
				}
				((Asset) copiedEntity).getVulnerability().addAll(vuls);

				// set status
				((Asset) copiedEntity).setStatus(((Asset) original).getStatus());

				diagram.getAsset().add((Asset) copiedEntity);

			} else if (copiedEntity instanceof Actor) { // as Actor

				// set role
				((Actor) copiedEntity).setRole(((Actor) original).getRole());

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

		if (self instanceof IncidentEntity) {
			IncidentEntity en = (IncidentEntity) self;
			en.setName(newName);
		}

		IncidentDiagram incident = getIncidentDiagram(self);

		if (incident != null) {

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
					List<Entity> postEntities = postExp != null ? postExp.getEntity() : null;

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

		// both should be Entity objects

		if (!(parent instanceof Entity)) {
			return;
		}

		if (!(child instanceof Entity)) {
			return;
		}

		Entity pEntity = (Entity) parent;
		Entity cEntity = (Entity) child;

		System.out.println(pEntity.getName() + " " + cEntity.getName());
		// get condition
		EObject container = pEntity.eContainer();
		int length = 1000;

		while (!(container instanceof BigraphExpression) && length > 0) {

			container = container.eContainer();
			length--;
		}

		if (container instanceof BigraphExpression) {

			// get bigraph expression
			BigraphExpression exp = (BigraphExpression) container;

			// remove child from old parent
			pEntity.getEntity().remove(cEntity);

			// add child to the express as a new root
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
		int length = 100000;

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
	 * Returns all available incident entities objects in the given room (self)
	 * 
	 * @param self
	 *            could be any element in the diagram
	 * @return
	 */
	public List<Location> getContainedIncidentEntities(EObject self) {

		// List<IncidentEntity> entities = new LinkedList<IncidentEntity>();

		// find root element (incident diagram)

		if (!(self instanceof Asset)) {
			return null;
		}

		Asset ast = (Asset) self;

		return ast.getContainedEntities();

	}

	/**
	 * Returns all available incident asset objects in the incident diagram
	 * 
	 * @param self
	 *            could be any element in the diagram
	 * @return
	 */
	public List<IncidentEntity> getAllIncidentAssets(EObject self) {

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
		// entities.addAll(incident.getActor());
		// // add all resources
		// entities.addAll(incident.getResource());
		// // add all other incident entities
		// entities.addAll(incident.getIncidentEntity());

		return entities;
	}

	/**
	 * Returns all available incident resources objects in the incident diagram
	 * 
	 * @param self
	 *            could be any element in the diagram
	 * @return
	 */
	public List<IncidentEntity> getAllIncidentResources(EObject self) {

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
		// entities.addAll(incident.getAsset());
		// add all actors
		// entities.addAll(incident.getActor());
		// // add all resources
		entities.addAll(incident.getResource());
		// // add all other incident entities
		// entities.addAll(incident.getIncidentEntity());

		return entities;
	}

	/**
	 * Sets the target asset of the given activity (self) to the newTarget
	 * 
	 * @param self
	 * @param newTarget
	 */
	public void setTargetAsset(EObject self, EObject newTarget) {

		if (!(self instanceof Activity)) {
			System.err.println("Self should be an activity");
			return;
		}

		if (!(newTarget instanceof Asset) && newTarget != null) {
			System.err.println("Self should be an Asset");
			return;
		}

		Activity act = (Activity) self;

		Asset ast = (Asset) newTarget;

		// clear the targeted assets
		act.getTargetedAssets().clear();

		// add the new target asset
		act.getTargetedAssets().add(ast);
	}

	/**
	 * Sets the resource of the given activity (self) to the newResource
	 * 
	 * @param self
	 * @param newTarget
	 */
	public void setUsedResource(EObject self, EObject newResource) {

		if (!(self instanceof Activity)) {
			System.err.println("Self should be an activity");
			return;
		}

		if (!(newResource instanceof Resource) && newResource != null) {
			System.err.println("Self should be a Resource");
			return;
		}

		Activity act = (Activity) self;

		Resource res = (Resource) newResource;

		// clear the resources
		act.getResources().clear();

		// add the new resource
		act.getResources().add(res);
	}

	/**
	 * Returns all connection objects defined in the incident diagram
	 * 
	 * @param self
	 * @return
	 */
	public List<Connection> getAllIncidentConnections(EObject self) {

		// get root element (Incident diagram)
		IncidentDiagram incident = getIncidentDiagram(self);

		if (incident == null) {
			return null;
		}

		return incident.getConnection();
	}

	/**
	 * returns root element IncidentDiagram
	 * 
	 * @return
	 */
	public IncidentDiagram getIncidentDiagram(EObject self) {

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
	 * create a Goal then add it to self (crime script)
	 * 
	 * @param self
	 */
	public void addGoal(EObject self) {

		// self should be crime script
		if (!(self instanceof CrimeScript)) {
			System.err.println("Self is not a CrimeScript");
			return;
		}

		CrimeScript script = (CrimeScript) self;

		CyberPhysicalIncidentFactory instance = CyberPhysicalIncidentFactory.eINSTANCE;

		// create a goal
		Goal newGoal = instance.createGoal();

		// add goal to incident
		IncidentDiagram incident = getIncidentDiagram(self);

		if (incident != null) {

			// add to incident
			incident.getGoal().add(newGoal);

			// add to crime script
			script.getGoals().add(newGoal);

		}

	}

	/**
	 * add an existing Goal to self (Activity)
	 * 
	 * @param self
	 */
	public void addGoalToActivity(EObject self, EObject newGoal) {

		// self should be activity
		if (!(self instanceof Activity)) {
			System.err.println("Self is not an Activity");
			return;
		}

		// self should be Goal
		if (!(newGoal instanceof Goal)) {
			System.err.println("Self is not a Goal");
			return;
		}
		Activity act = (Activity) self;
		Goal goal = (Goal) newGoal;

		// add goal to activity
		act.getGoals().add(goal);

	}

	/**
	 * add an existing Goal to self (Activity)
	 * 
	 * @param self
	 */
	public void removeGoalFromActivity(EObject self, EObject goal) {

		// self should be activity
		if (!(self instanceof Activity)) {
			System.err.println("Self is not an Activity");
			return;
		}

		// self should be Goal
		if (!(goal instanceof Goal)) {
			System.err.println("Self is not a Goal");
			return;
		}
		Activity act = (Activity) self;
		Goal gl = (Goal) goal;

		// add goal to activity
		act.getGoals().remove(gl);

	}

	/**
	 * return goals of the incident
	 * 
	 * @param self
	 */
	public List<Goal> getGoals(EObject self) {

		// get incident
		IncidentDiagram incident = getIncidentDiagram(self);

		if (incident != null) {

			return incident.getGoal();
		}

		return null;

	}

	/**
	 * checks if the given scene (self) activities are in sequence
	 * 
	 * @param self
	 * @return
	 */
	public boolean areActivitiesInSequence(EObject self) {

		// self is a scene
		if (!(self instanceof Scene)) {
			return false;
		}

		Scene scene = (Scene) self;
		List<Activity> visitedActivities = new LinkedList<Activity>();
		List<Activity> sceneActivities = scene.getActivity();

		// check that all activities are in sequence
		Activity iniActivity = scene.getInitialActivity();
		Activity finalActivity = scene.getFinalActivity();

		// if scene has no activities then return true. if there's only one
		// activity then return true
		if (sceneActivities.size() == 0 || sceneActivities.size() == 1) {
			return true;
		}

		// if initial activity is null then return false
		if (iniActivity == null) {
			return false;
		}

		visitedActivities.add(iniActivity);

		// if there's no next activity then there's no sequence
		Activity next = !iniActivity.getNextActivities().isEmpty() ? iniActivity.getNextActivities().get(0) : null;

		int length = 100000;

		while (next != null && length > 0) {

			if (visitedActivities.contains(next)) {
				return false; // there's a loop!
			}

			visitedActivities.add(next);

			next = !next.getNextActivities().isEmpty() ? next.getNextActivities().get(0) : null;
			length--;
		}

		// if the visited activities does not equal the scenes activities number
		// then return false
		if (visitedActivities.size() != sceneActivities.size()) {
			return false;
		}

		return true;
	}

	/**
	 * checks if the given scene (self) scenes is in sequence including its
	 * activities
	 * 
	 * @param self
	 *            as scene
	 * @return
	 */
	public boolean isSceneInSequence(EObject self) {

		// self is a scene
		if (!(self instanceof Scene)) {
			return false;
		}

		Scene scene = (Scene) self;
		Scene nxtScene = !scene.getNextScenes().isEmpty() ? scene.getNextScenes().get(0) : null;

		// if next scene is not null then check that the final activity in the
		// given scene has as next activity the first activity in the next scene
		if (nxtScene != null) {
			Activity lastAct = scene.getFinalActivity();
			Activity firstAct = nxtScene.getInitialActivity();

			// if the next activity from the next scene is null return true as
			// there's a problem in the next activity
			// or if the last activity in the given scene is null then return
			// true as well
			if (firstAct == null || lastAct == null) {
				return true;
			}

			Activity nxt = !lastAct.getNextActivities().isEmpty() ? lastAct.getNextActivities().get(0) : null;

			// they are not connected
			if (nxt == null) {
				return false;
			}

			// they are not equal
			if (!nxt.equals(firstAct)) {
				return false;
			}
		} else { // if next scene is null then there's a possibility that the
					// given scene is the last scene
			// check that the given scene is the last scene or not

			// get incident diagram
			IncidentDiagram incident = getIncidentDiagram(self);

			if (incident != null) {
				// if last scene then return true
				return isLastScene(scene, incident.getScene().size(), incident.getInitialScene());
			}

		}

		return true;
	}

	protected boolean isLastScene(Scene scene, int numberOfScenes, Scene initialScene) {

		// this is determined by looking at the previous scenes if the number of
		// the previous scenes equals to the number of scenes -1

		// if there's one scene only
		if (numberOfScenes == 1 && scene.equals(initialScene)) {
			return true;
		}

		Scene next = !initialScene.getNextScenes().isEmpty() ? initialScene.getNextScenes().get(0) : null;
		int length = 1;

		// while next scene is not null or not equal to the given scene
		while (next != null && !next.equals(scene)) {

			next = !next.getNextScenes().isEmpty() ? next.getNextScenes().get(0) : null;

			length++;
		}

		// if the length is the same as the number of scenes then the given
		// scene is the last scene
		if (length == numberOfScenes - 1) {
			return true;
		}

		return false;

	}

	public void deleteActivity(EObject self) {

		// deletes the given activity from the set of activities in the scene

		if (!(self instanceof Activity)) {
			return;
		}

		Activity activity = (Activity) self;
		Scene scene = (Scene) self.eContainer();

		scene.getActivity().remove(activity);
	}

	/**
	 * Deletes the child relation between the self (parent) and the target
	 * (child)
	 * 
	 * @param self
	 * @param target
	 */
	public void deleteChildRelation(EObject self, EObject target) {

		// self is parent as incident entity and target is the child
		if (!(self instanceof IncidentEntity)) {
			return;
		}

		if (!(target instanceof IncidentEntity)) {
			System.err.println("Target is not an incident entity");
			return;
		}

		IncidentEntity parent = (IncidentEntity) self;
		IncidentEntity child = (IncidentEntity) target;
		//
		// remove child from contained entities of the parent
		parent.getContainedEntities().remove(child);

		// set parent of child as null
		child.setParentEntity(null);
	}

	/**
	 * Deletes the child relation between the self (parent) and the target
	 * (child) in the condition
	 * 
	 * @param self
	 * @param target
	 */
	public void deleteEntityChildRelation(EObject self, EObject target) {

		// self is parent as incident entity and target is the child
		if (!(self instanceof Entity)) {
			System.err.println("self is not an entity");
			return;
		}

		if (!(target instanceof Entity)) {
			System.err.println("Target is not an entity");
			return;
		}

		Entity parent = (Entity) self;
		Entity child = (Entity) target;
		//
		// remove child from contained entities of the parent
		parent.getEntity().remove(child);

		// add child as a root to the condition

		EObject container = self.eContainer();

		int length = 10000;

		while (!(container instanceof BigraphExpression) && length > 0) {
			container = container.eContainer();
			length--;
		}

		if (container instanceof BigraphExpression) {
			BigraphExpression exp = (BigraphExpression) container;

			exp.getEntity().add(child);
		}

	}

	/**
	 * Deletes the child relation between the self (parent) and the target
	 * (child) in the condition
	 * 
	 * @param self
	 * @param target
	 */
	public void deleteEntity(EObject self) {

		// self is parent as incident entity and target is the child
		if (!(self instanceof Entity)) {
			System.err.println("self is not an entity");
			return;
		}

		Entity entity = (Entity) self;
		List<Entity> children = entity.getEntity();

		boolean isRoot = false; // used to indicate if the entity is root

		// add child as a root to the condition

		EObject container = self.eContainer();

		int length = 10000;

		while (!(container instanceof BigraphExpression) && length > 0) {
			container = container.eContainer();
			length--;
		}

		if (container instanceof BigraphExpression) {
			BigraphExpression exp = (BigraphExpression) container;

			// add all children of the entity as root entities
			if (children != null && !children.isEmpty()) {
				exp.getEntity().addAll(children);
			}

			// remove entity

			// find the parent

			// first look if the entity is a root entity
			for (Entity ent : exp.getEntity()) {
				if (ent.equals(entity)) {
					isRoot = true;
					break;
				}
			}

			if (isRoot) {
				exp.getEntity().remove(entity);
			} else { // if entity is not root then find it in the other entities

				List<Entity> allEntities = getAllConditionEntity(self, exp.getEntity());
				Entity parent = null;

				// check contained entities of each entity
				for (Entity ent : allEntities) {

					// if entity is found then set isChild to true
					if (ent.getEntity().contains(entity)) {
						parent = ent;
						break;
					}
				}

				// if parent found then remove entity from it
				if (parent != null) {
					parent.getEntity().remove(entity);
				}

			}

			String parentName = exp.getContainer(entity.getName());

			System.out.println(parentName);

			Entity parent = exp.getEntity(parentName);

			if (parent != null) {
				parent.getEntity().remove(entity);
			} else { // the entity might be root i.e. should be deleted from the
						// expression

				for (Entity ent : exp.getEntity()) {
					if (ent.equals(entity)) {
						isRoot = true;
						break;
					}
				}

				if (isRoot) {
					exp.getEntity().remove(entity);
				}

			}

		}

	}

	/**
	 * Deletes the next activity relation of the self
	 * 
	 * @param self
	 * @param target
	 */
	public void deleteNextActivityRelation(EObject self) {

		// self is parent as incident entity and target is the child
		if (!(self instanceof Activity)) {
			System.err.println("self is not an Activity");
			return;
		}

		Activity next = (Activity) self; // the next activity
		Activity act = (Activity) next.getPreviousActivities().get(0);
		//
		System.out.println(act.getName() + " " + next.getName());
		// clear next activities for act
		act.getNextActivities().clear();

		// clear previous activities for next
		next.getPreviousActivities().clear();

	}

	/**
	 * checks if the given incident diagram (self) scenes are in sequence
	 * 
	 * @param self
	 * @return
	 */
	public boolean areScenesInSequence(EObject self) {

		// self is a incident diagram
		if (!(self instanceof IncidentDiagram)) {
			return false;
		}

		IncidentDiagram incident = (IncidentDiagram) self;
		List<Scene> visitedScenes = new LinkedList<Scene>();
		List<Scene> incidentScenes = incident.getScene();

		// check that all scenes are in sequence
		Scene iniScene = incident.getInitialScene();
		// Scene finalScene = incident.get

		// if scene has no scenes then return true.
		if (incidentScenes.size() == 0) {
			return true;
		}

		// if there's one scene only then check that scene activities for
		// sequence
		if (incidentScenes.size() == 1) {
			return areActivitiesInSequence(incidentScenes.get(0));
		}

		// check that the scenes are in sequence
		// if initial activity is null then return false
		if (iniScene == null) {
			return false;
		}

		// visitedScenes.add(iniScene);

		Scene next = iniScene;// !iniScene.getNextScenes().isEmpty()?iniScene.getNextScenes().get(0):null;
		Scene previous = null;

		int length = 100000;

		while (next != null && length > 0) {

			if (visitedScenes.contains(next)) {
				return false; // there's a loop!
			}

			// add to visited
			visitedScenes.add(next);

			// check that scene activities are in sequence
			if (!areActivitiesInSequence(next)) {

				return false;
			}

			// check that the last activity in the scene is connected to the
			// first activity of the next scene
			previous = next;
			next = !next.getNextScenes().isEmpty() ? next.getNextScenes().get(0) : null;

			Activity lastAct = previous.getFinalActivity();
			Activity firstAct = next != null ? next.getInitialActivity() : null;

			// check activities connected as long as the sizes are not the same
			if (visitedScenes.size() != incidentScenes.size()) {

				// if any of the two activities is null then return false
				if (lastAct == null || firstAct == null) {
					return false;
				}

				// if the next of the last activity is not the first activity of
				// the next scene then return false
				Activity nxt = !lastAct.getNextActivities().isEmpty() ? lastAct.getNextActivities().get(0) : null;

				// they are not connected
				if (nxt == null) {
					return false;
				}

				if (!nxt.equals(firstAct)) {
					return false;
				}
			}

			length--;
		}

		// if the visited activities does not equal the scenes activities number
		// then return false
		if (visitedScenes.size() != incidentScenes.size()) {
			return false;
		}

		return true;
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

	public Collection<Type> getSystemTypes(EObject self) {

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
	public Collection<Type> getSystemAssetTypes(EObject self) {

		// an implementation could use the system handler instance to do this

		if (systemAssetTypes != null && !systemAssetTypes.isEmpty()) {
			return systemAssetTypes.keySet();
		}

		systemAssetTypes = new HashMap<Type, List<Type>>();

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
				int numOfLevels = 100; // determines how many superclasses to
										// add

				try {

					potentialClass = Class.forName(fullClassName);
					// class is not of type asset
					if (!(environment.Asset.class.isAssignableFrom(potentialClass))) {
						continue;
					}

					// get superclasses
					List<Type> classHierarchy = new LinkedList<Type>();
					int cnt = 0;

					do { // loop over superclasses

						Type tmp = instance.createType();
						tmp.setName(potentialClass.getSimpleName().replace("Impl", ""));
						classHierarchy.add(tmp);
						potentialClass = potentialClass.getSuperclass();
						cnt++;
					} while (potentialClass != null && !potentialClass.getSimpleName().equals("Container")
							&& cnt < numOfLevels);

					// create new type and add its subclasses
					Type type = instance.createType();
					type.setName(className);

					// add new entry to the map
					systemAssetTypes.put(type, classHierarchy);

					// create e type based on class name

					// systemAssetTypes.add(classntmp);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					// if it is not a class then skip
				}
			}

		}

		return systemAssetTypes.keySet();
	}

	/**
	 * Returns all connection types from the system meta-model
	 * 
	 * @param self
	 * @return
	 */
	public Collection<Type> getSystemConnectionTypes(EObject self) {

		// an implementation could use the system handler instance to do this

		if (systemConnectionTypes != null && !systemConnectionTypes.isEmpty()) {
			return systemConnectionTypes.keySet();
		}

		systemConnectionTypes = new HashMap<Type, List<Type>>();

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
				int numOfLevels = 100;

				try {
					potentialClass = Class.forName(fullClassName);

					// class is not of type Connection then skip
					if (!(environment.Connection.class.isAssignableFrom(potentialClass))) {
						continue;
					}

					// get superclasses
					List<Type> classHierarchy = new LinkedList<Type>();
					int cnt = 0;

					do { // loop over superclasses

						Type tmp = instance.createType();
						tmp.setName(potentialClass.getSimpleName().replace("Impl", ""));
						classHierarchy.add(tmp);
						potentialClass = potentialClass.getSuperclass();
						cnt++;
					} while (potentialClass != null && !potentialClass.getSimpleName().equals("Container")
							&& cnt < numOfLevels);

					// create new type and add its subclasses
					Type type = instance.createType();
					type.setName(className);

					// add new entry to the map
					systemConnectionTypes.put(type, classHierarchy);

				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					// if it is not a class then skip
				}
			}

		}

		return systemConnectionTypes.keySet();
	}

	/******************************
	 * SYSTEM
	 * FUNCTIONS*************************************************************
	 * *********************************************************************************************************
	 * *********************************************************************************************************
	 * *********************************************************************************************************
	 * 
	 */

	/**
	 * Returns system instance;
	 * 
	 * @param self
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

	public boolean isSystemModelSet(EObject self) {
	
		if(sysHandler.getInstance() == null) {
			GeneralFXFrame selector = new GeneralFXFrame("Select System", JFXPanel.SYSTEM_MODEL_SELECTOR);
			selector.setVisible(true);
			return false;
		}
		
		return true;
	}
	
	public List<environment.Asset> getSystemComponents(EObject self) {

		// dummy list for the moment
//		System.out.println("Whaaaat");
		return sysHandler.getDummyAssets();
//		return null;
	}
	

	/**
	 * returns the class name of the given Asset (from environment) object
	 */
	public String getSystemComponentClassName(EObject self) {

		if (!(self instanceof environment.Asset)) {
			return null;
		}

		environment.Asset ast = (environment.Asset) self;
		String name = ast.getClass().getSimpleName().replace("Impl", "");

		return name;
	}

	/******************************
	 *************************************************************
	 * *********************************************************************************************************
	 * *********************************************************************************************************
	 * *********************************************************************************************************
	 * 
	 */


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

		CyberPhysicalIncidentFactory instance = CyberPhysicalIncidentFactory.eINSTANCE;

		Action act = (Action) action;

		// === update precondition
		Precondition activityPre = activity.getPrecondition();

		String brsPreCond = (act.getPreconditions() != null && !act.getPreconditions().isEmpty())
				? act.getPreconditions().get(0) : null;

		BigraphExpression newPreBRS = parseActionBRSCondition(brsPreCond);

		// if precondition is null then create a new condition
		if (activityPre == null) {
			activityPre = instance.createPrecondition();
		}

		activityPre.setExpression(newPreBRS);
		activity.setPrecondition(activityPre);

		// === update postcondition
		Postcondition activityPost = activity.getPostcondition();

		String brsPostCond = (act.getPostconditions() != null && !act.getPostconditions().isEmpty())
				? act.getPostconditions().get(0) : null;

		BigraphExpression newPostBRS = parseActionBRSCondition(brsPostCond);

		// if postcondition is null then create a new condition
		if (activityPost == null) {
			activityPost = instance.createPostcondition();
		}

		activityPost.setExpression(newPostBRS);
		activity.setPostcondition(activityPost);

	}

	/**
	 * Parses the given condition in BRS format to identify entities and
	 * connectivity then creates a new condition based on that
	 * 
	 * @param BRScondition
	 * @return Condition
	 */
	public BigraphExpression parseActionBRSCondition(String BRScondition) {

		if (brsParser == null) {
			brsParser = new BRSParser();
		}

		return brsParser.parseActionBRSCondition(BRScondition);

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

	/**********************
	 * VALIDATIONS **************************************************
	 * ***********************************************************************************
	 * ***********************************************************************************
	 * ***********************************************************************************
	 * ***********************************************************************************
	 * ***********************************************************************************
	 * ***********************************************************************************
	 */

	/**
	 * Checks that the self (Incident entity) parent is not an Actor (as Class
	 * and as Type)
	 * 
	 * @param self
	 * @param parent
	 * @return
	 */
	public boolean checkParentIsNotSuperSupOfActor(EObject self) {

		if (!(self instanceof IncidentEntity)) {
			// System.err.println("Self should be an actor");
			return true;
		}

		IncidentEntity entity = (IncidentEntity) self;
		IncidentEntity actor = (IncidentEntity) entity.getParentEntity();

		// if(!(actor instanceof Actor)) {
		//// System.err.println("Self should be an actor");
		// return true;
		// }

		// check that the parent is not an actor
		// if((entity instanceof Actor)) {
		// return false;
		// }

		// if the parent is null then return true
		if (actor == null) {
			return true;
		}

		Type entityType = entity.getType();
		Type actorType = actor.getType();
		// String type = null;

		// this checks that the type of the entity is not the same or superclass
		// of the parent and the other way around

		if (actorType == null || entityType == null) {
			return true;
		}

		// create class for each type
		String entityTypeName = entityType.getName();
		String actorTypeName = actorType.getName();

		String entityClassName = "environment.impl." + entityTypeName + "Impl";
		String actorClassName = "environment.impl." + actorTypeName + "Impl";

		try {

			Class entityClass = Class.forName(entityClassName);
			Class actorClass = Class.forName(actorClassName);
			//
			//
			// //check that the entity class is a subclass/superclass of the
			// parent. If so then return false
			if (entityClass.isAssignableFrom(actorClass) || actorClass.isAssignableFrom(entityClass)) {
				return false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		return true;

	}

	/**
	 * Checks that the type of the entity and the type of its parent do not
	 * produce conflict by having a digital containing a physical
	 * 
	 * @return
	 */
	public boolean isDigitalContainsPhysical(EObject self) {

		if (!(self instanceof IncidentEntity)) {
			// System.err.println("Self should be an actor");
			return true;
		}

		IncidentEntity entity = (IncidentEntity) self;
		IncidentEntity parent = (IncidentEntity) entity.getParentEntity();

		// if parent is null then its valid
		if (parent == null) {
			return true;
		}

		Type entityType = entity.getType();
		Type parentType = parent.getType();
		// String type = null;

		// this checks that the type of the entity is not the same or superclass
		// of the parent and the other way around

		if (parentType == null || entityType == null) {
			return true;
		}

		// create class for each type
		String entityTypeName = entityType.getName();
		String actorTypeName = parentType.getName();

		String entityClassName = "environment.impl." + entityTypeName + "Impl";
		String actorClassName = "environment.impl." + actorTypeName + "Impl";

		try {

			Class entityClass = Class.forName(entityClassName);
			Class parentClass = Class.forName(actorClassName);
			//
			//
			// //check that if the entity class is physical then the parent
			// should be also physical
			if (environment.PhysicalAsset.class.isAssignableFrom(entityClass)) {

				// check the parent to be physical
				if (!environment.PhysicalAsset.class.isAssignableFrom(parentClass)) {
					return false;
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}

		return true;
	}
	
	
	
	
}
