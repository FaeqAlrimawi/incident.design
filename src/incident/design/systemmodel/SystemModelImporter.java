package incident.design.systemmodel;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;

public class SystemModelImporter implements IExternalJavaAction {

	@Override
	public boolean canExecute(Collection<? extends EObject> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void execute(Collection<? extends EObject> arg0, Map<String, Object> arg1) {
		// TODO Auto-generated method stub
		
		importSystemModel("test");
	}
	
	protected void importSystemModel(String path) {
		System.out.println(path);
	}
	

}
