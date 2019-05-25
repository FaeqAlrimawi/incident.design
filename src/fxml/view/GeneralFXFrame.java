package fxml.view;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import javafx.application.Platform;

public class GeneralFXFrame extends JFrame {

 public enum JFXPanel {
    ADD_ACTIONS_PANEL("SystemActionView.fxml"),
    DRAW_BRS_PANEL("brsDrawView.fxml"),
    MAIN_SCREEN_PANEL("MainScreen.fxml"),
    LTS_VIEW_PANEL("LTSView.fxml"),
    SYSTEM_MODEL_SELECTOR("../fxml/SystemModelSelector.fxml");
	 
   private String fxmlFileName;
   
    private JFXPanel(String value) {
   
      fxmlFileName = value;
    }
  }
//  public static final String ADD_ACTIONS_PANEL = "SystemActionView.fxml";
//  public static final String DRAW_BRS_PANEL    = "brsDrawView.fxml";
//  public static final String MAIN_SCREEN_PANEL = "MainScreen.fxml";
//  public static final String LTS_VIEW_PANEL = "LTSView.fxml";
  
  private static int         lastX             = -1;
  private static int         lastY             = -1;
  private static int         offset            = 15;
  private int                x;
  private int                y;
  private static final int   WIDTH             = 800;
  private static final int   HEIGHT            = 600;
  private JFXPanel             fxPanelName;
  private int                panelWidth;
  private int                panelHeight;

  private GeneralJFXPanel jfxPanel;
private boolean isClosed = false;

  // public static void main(String [] args) {
  // EventQueue.invokeLater(new Runnable() {
  // public void run() {
  // try {
  //
  // frame = new SystemActionFXFrame();
  //
  // frame.setVisible(true);
  // } catch (Exception e) {
  //// e.printStackTrace();
  // System.out.println("oops!");
  // }
  // }
  // });
  // }

  public GeneralFXFrame(String title, JFXPanel panel) {

    setTitle(title);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    setXY();

    setPanel(panel);

    setWidthHeight(panel);

    jfxPanel = new GeneralJFXPanel();
    this.add(jfxPanel);

    setBounds(x, y, panelWidth, panelHeight);

    Platform.runLater(new Runnable() {

      public void run() {
        // TODO Auto-generated method stub
        jfxPanel.init(fxPanelName.fxmlFileName);
      }
    });

    setWindowListener();
  }

  public GeneralFXFrame(String title, int width, int height, JFXPanel panel) {

    setXY();

    setTitle(title);

    setWidthHeight(panel);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    setBounds(x, y, panelWidth, panelHeight);

    setPanel(panel);

    // add fx scene
    jfxPanel = new GeneralJFXPanel();
    this.add(jfxPanel);

    Platform.runLater(new Runnable() {

      public void run() {
        // TODO Auto-generated method stub
        jfxPanel.init(fxPanelName.fxmlFileName);
      }
    });

  }

  public GeneralFXFrame(String title, int x, int y, int width, int height, JFXPanel panel) {

    setXY(x, y);

    setTitle(title);

    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    setBounds(x, y, width, height);

    setPanel(panel);

    // add fx scene
    jfxPanel = new GeneralJFXPanel();
    this.add(jfxPanel);

    Platform.runLater(new Runnable() {

      public void run() {
        // TODO Auto-generated method stub
        jfxPanel.init(fxPanelName.fxmlFileName);
      }
    });

  }

  protected void setXY() {

    if (lastX == -1) {
      x = 100;
      y = 100;
      lastX = x; // default
      lastY = y;
    } else {
      x = lastX + offset;
      y = lastY + offset;
      lastX = x;
      lastY = y;
    }
  }

  protected void setXY(int x, int y) {

    lastX = x;
    lastY = y;
  }

  protected void setPanel(JFXPanel panel) {
    fxPanelName = panel;

  }

  public void setLocation(int x, int y) {

    super.setLocation(x, y);

    lastX = x;
    lastY = y;
  }

  protected void setWidthHeight(JFXPanel panel) {

    switch (panel) {
      case MAIN_SCREEN_PANEL:
        panelWidth = 600;
        panelHeight = 460;
        break;

      case ADD_ACTIONS_PANEL:
        panelWidth = 790;
        panelHeight = 420;
        break;

      case LTS_VIEW_PANEL:
        panelWidth = 600;
        panelHeight = 500;
        break;
        
      default:
        panelWidth = WIDTH;
        panelHeight = HEIGHT;
        break;
    }
  }

  // protected Pane getPane(String fxmlName) {
  //
  // FXMLLoader loader = new FXMLLoader();
  //
  // loader.setLocation(SystemActionView.class.getResource(fxmlName));
  //
  // Pane layout = null;;
  //
  // try {
  //
  // layout = loader.load();
  //
  // } catch (IOException ex) {
  // // TODO Auto-generated catch block
  // ex.printStackTrace();
  // }
  //
  // return layout;
  // }

  protected String getPanelTitle(JFXPanel panel) {

    switch (panel) {
      case ADD_ACTIONS_PANEL:
        return "System Actions";

      case DRAW_BRS_PANEL:
        return "Condition";

      case MAIN_SCREEN_PANEL:
        return "Main";
        
      case LTS_VIEW_PANEL:
        return "Labelled Transition System Generator";
        
      default:
        return "";

    }
  }
  

  protected void setWindowListener() {
this.addWindowListener(new WindowListener() {
      
      @Override
      public void windowClosed(WindowEvent e) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void windowActivated(WindowEvent e) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void windowClosing(WindowEvent e) {
        // TODO Auto-generated method stub
        jfxPanel = null;
        isClosed = true;
        lastX-=offset;
        lastY-=offset;
        
      }

      @Override
      public void windowDeactivated(WindowEvent e) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void windowDeiconified(WindowEvent e) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void windowIconified(WindowEvent e) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void windowOpened(WindowEvent e) {
        // TODO Auto-generated method stub
        
      }
      
    });
       
  }
  
  public boolean isClosed() {
    return isClosed;
  }
}
