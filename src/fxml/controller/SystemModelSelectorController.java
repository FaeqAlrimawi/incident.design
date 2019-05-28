package fxml.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import environment.EnvironmentDiagram;
import incident.design.SystemInstanceHandler;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

public class SystemModelSelectorController {
	
	@FXML
	private TextField textFieldSelectedStatesFolder;

	@FXML
	private TextField textFieldSystemFile;

	@FXML
	private TextField textFieldIncidentInstance;

	@FXML
	private ImageView imgSystemFileCheck;

	@FXML
	private ImageView imgSelectSystemFile;

	@FXML
	private ImageView imgOpenFolder;

	@FXML
	private ImageView imgRefresh;

	@FXML
	private ImageView imgRefreshEmpty;
	
	@FXML
	private ImageView imgIncidentInstanceFileCheck;
	
	@FXML
	private Label lblSystemFileCheck;

	@FXML
	private Button btnUpdateSystemModel;

	@FXML
	private Button btnEditActions;
	
	@FXML
	private Button btnImport;

	@FXML
	private ImageView imgStatesCheck;

	@FXML
	private Pane mainPane;

	private static final String IMAGES_FOLDER = "resources/images/";
	private static final String IMAGE_CORRECT = IMAGES_FOLDER + "correct.png";
	private static final String IMAGE_WRONG = IMAGES_FOLDER + "wrong.png";
	private static final int INTERVAL = 3000;
	private static final int FILE_MENU = 0;

//	private BigrapherStatesChecker checker;

//	private File selectedStatesDirectory;
	private File selectedSystemFile;
//	private File selectedIncidentInstanceFile;
	private EnvironmentDiagram systemModel;

//	private SweetHome3D sh3d;

	
	@FXML
	void modelSystem(ActionEvent event) {

//		if (sh3d == null) {
//			sh3d = new SweetHome3D();
//			sh3d.init(new String[] {});
//
//		} else {
//			// bring focus to it
//
//		}

	}
	
	@FXML
	void selectSystemFile(MouseEvent event) {

		FileChooser fileChooser = new FileChooser();

		if (selectedSystemFile != null) {
			fileChooser.setInitialFileName(selectedSystemFile.getName());
		}

		// set extension to be of system model (.cps)
		// fileChooser.setSelectedExtensionFilter(new ExtensionFilter("System
		// model files (*.cps)",".cps"));
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Cyber Physical System files (*.cps)",
				"*.cps");

		fileChooser.getExtensionFilters().add(extFilter);

		selectedSystemFile = fileChooser.showOpenDialog(null);

		if (selectedSystemFile != null) {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					textFieldSystemFile.setText(selectedSystemFile.getAbsolutePath());
				}
			});

			if (isSystemFileValid()) {

				updateImage(IMAGE_CORRECT, imgSystemFileCheck);
				updateText("System model is valid", lblSystemFileCheck);
				btnUpdateSystemModel.setDisable(false);
				btnEditActions.setDisable(false);
				btnImport.setDisable(false);
//				imgOpenBigrapher.setVisible(true);
//				imgOpenBigrapherEmpty.setVisible(false);

//				if (selectedIncidentInstanceFile != null) {
//					btnGenerateIncidentPattern.setDisable(false);
//				}

			} else {
				updateImage(IMAGE_WRONG, imgSystemFileCheck);
				updateText("System model is not valid", lblSystemFileCheck);
				btnUpdateSystemModel.setDisable(true);
//				imgOpenBigrapher.setVisible(false);
//				imgOpenBigrapherEmpty.setVisible(true);
				imgRefresh.setVisible(false);
				imgRefreshEmpty.setVisible(true);
				btnImport.setDisable(true);

//				btnGenerateIncidentPattern.setDisable(true);

			}

			// remove the check image and text after a few secs
			new Timer().schedule(new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					updateImage(null, imgSystemFileCheck);
					updateText("", lblSystemFileCheck);
				}
			}, INTERVAL);
		}

		textFieldSystemFile.requestFocus();

	}
	
	@FXML
	void openSystemFile(MouseEvent event) {
		// open bigrapher model file
//		if (SystemHandler.isSystemModelGenerated()) {
//			String path = SystemHandler.getFilePath();
//
//			if (path != null) {
//				try {
//					Desktop.getDesktop().open(new File(path));
//				} catch (IOException ex) {
//					// TODO Auto-generated catch block
//					ex.printStackTrace();
//				}
//			}
//
//		}
		if(selectedSystemFile != null) {
			try {
				Desktop.getDesktop().open(selectedSystemFile);
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}
	
	@FXML
	void updateSystemModel(ActionEvent event) {

		// to be done
		// create a model in SH3D based on the system model
	}

	@FXML
	void importSystemModel(ActionEvent event) {
		
		if(SystemInstanceHandler.importSystemModelFromFile(selectedSystemFile.getAbsolutePath())) {
			//close window
//			Scene scene = btnImport.getScene();
//			Window window = scene.getWindow();
//			PopupWindow wind = (PopupWindow) window;
			updateImage(IMAGE_CORRECT, imgSystemFileCheck);
			updateText("System model is imported successfully. You can close the window now.", lblSystemFileCheck);
			
			
//			btnImport.getp
		} 
	}
	
	@FXML
	void openActionPanel(ActionEvent event) {

		try {

			Pane layout;
			FXMLLoader loader = new FXMLLoader();

			URL url = SystemModelSelectorController.class.getResource("../fxml/SystemActionView.fxml");

			if (url != null) {
				System.out.println(url.getPath());
			} else {
				System.out.println("url is null");
			}

			loader.setLocation(url);

			// Platform.setImplicitExit(false);

			layout = loader.load();

			Scene scene = new Scene(layout);

			Stage newStage = new Stage();
			newStage.setScene(scene);
			newStage.show();

			newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					// TODO Auto-generated method stub
					imgRefresh.setVisible(true);
					imgRefreshEmpty.setVisible(false);
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	protected boolean isSystemFileValid() {

		// try creating an object from the file
//		SystemHandler.setFilePath(selectedSystemFile.getAbsolutePath());

		boolean isValid = SystemInstanceHandler.isSystemModelFileValid(selectedSystemFile.getAbsolutePath());

		return isValid;

	}
	
	protected void updateImage(String imgPath, ImageView imgView) {

		if (imgView == null) {
			return;
		}

		if (imgPath == null) {
			imgView.setVisible(false);

		} else {

			imgView.setVisible(true);

			URL urlImage = getClass().getClassLoader().getResource(imgPath);

			if (urlImage != null) {
				Image img;
				try {
					img = new Image(urlImage.openStream());
					imgView.setImage(img);
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

			} else {
				System.out.println(imgPath + " Not found!");
			}

		}

	}

	protected void updateText(String msg, final Label label) {

		if (label == null) {
			return;

		} else {

			label.setVisible(true);
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					label.setText(msg);
				}
			});
		}

	}



}
