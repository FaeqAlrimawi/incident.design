package incident.design;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import environment.Action;
import environment.Asset;
import environment.CyberPhysicalSystemPackage;
import environment.EnvironmentDiagram;
import environment.impl.EnvironmentDiagramImpl;

public class SystemInstanceHandler {

	private static final String EXTENSION = "cps";
	private static final EPackage MODEL_PACKAGE_EINSTANCE = CyberPhysicalSystemPackage.eINSTANCE;
	private static final String MODEL_PACKAGE_ENS_URI = CyberPhysicalSystemPackage.eNS_URI;

	private String testFile = "D:/Bigrapher data/lero/lero.cps";

	private EnvironmentDiagram systemInstance;

	/**
	 * Load a system model from the given file name
	 * 
	 * @param fileName
	 *            the XMI file of the system model
	 * @return an EnvironmentDigram object containing the model information
	 */
	public EnvironmentDiagram loadSystemFromFile(String fileName) {

		EnvironmentDiagram environmentDiagram = null;

		// generate EPackages from schemas
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());

		ResourceSet rs = new ResourceSetImpl();
		// enable extended metadata
		final ExtendedMetaData extendedMetaData = new BasicExtendedMetaData(rs.getPackageRegistry());
		rs.getLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);

		EPackage.Registry.INSTANCE.put(MODEL_PACKAGE_ENS_URI, MODEL_PACKAGE_EINSTANCE);

		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(EXTENSION, new XMIResourceFactoryImpl() {

			public Resource createResource(URI uri) {

				XMIResource xmiResource = new XMIResourceImpl(uri);

				return xmiResource;
			}
		});

		try {

			Resource r = rs.getResource(URI.createFileURI(fileName), true);

			if (r.getContents().size() == 0) {
				System.err.println("Could not find a resource for the given file [" + fileName + "]");
				return null;
			}

			EObject eObject = r.getContents().get(0);

			if (eObject instanceof EPackage) {
				EPackage p = (EPackage) eObject;
				rs.getPackageRegistry().put(p.getNsURI(), p);
			}

			if (!(eObject instanceof EnvironmentDiagram)) {
				System.err.println("Not a system instance model. File [" + fileName + "]");
				return null;
			}

			environmentDiagram = (EnvironmentDiagramImpl) eObject;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return environmentDiagram;

	}

	public EnvironmentDiagram getInstance(String fileName) {

		if (systemInstance == null) {
			loadSystemFromFile(fileName);
		}

		return systemInstance;
	}

	public EnvironmentDiagram getInstance() {

		return systemInstance;
	}

	public List<Action> getSystemActions() {

		if(systemInstance == null) {
			return null;
		}
		
		List<Action> actions = systemInstance.getAction();

		return actions;
	}

	//dummy function should be replaced by the call to getSystemActionNames
	public List<Action> getDummyActions() {

		// create a couple of dummy actions
		systemInstance = loadSystemFromFile(testFile);

		if (systemInstance != null) {
			return systemInstance.getAction();
		}
		return null;

	}

	public List<Asset> getSystemAssets() {
	
		if(systemInstance == null) {
			return null;
		}
		
		List<Asset> assets = systemInstance.getAsset();

		return assets;
	}

	//dummy function should be replaced by the call to getSystemActionNames
	public List<Asset> getDummyAssets() {

		// create a couple of dummy actions
		systemInstance = loadSystemFromFile(testFile);

		if (systemInstance != null) {
			return systemInstance.getAsset();
		}
		return null;

	}

	//
}
