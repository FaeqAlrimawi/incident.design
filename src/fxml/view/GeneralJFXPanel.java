package fxml.view;
import java.io.IOException;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GeneralJFXPanel extends JFXPanel {

  private Pane   layout;
//  private String fxmlFileName;

  public void init(String fxmlfileName) {

    FXMLLoader loader = new FXMLLoader();

    System.out.println(fxmlfileName);
    loader.setLocation(GeneralJFXPanel.class.getResource(fxmlfileName));

    Platform.setImplicitExit(false);
    try {

      layout = loader.load();

      Scene scene = new Scene(layout);

      this.setScene(scene);

    } catch (IOException ex) {
      // TODO Auto-generated catch block
       ex.printStackTrace();
    }
  }
  
  public void init(Pane layout) {

      Scene scene = new Scene(layout);

      this.setScene(scene);
  }

//  public GeneralJFXPanel() {
//
//  }
//
//  public GeneralJFXPanel(String fxmlFile) {
//
//    this.fxmlFileName = fxmlFile;
//    
//
//  }
}
