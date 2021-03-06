/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdlm.gui;
import java.time.LocalDate;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import properties_manager.PropertiesManager;
import saf.AppTemplate;
import saf.ui.AppGUI;
import tdlm.PropertyType;
import tdlm.data.ToDoItem;
/**
 *
 * @author jappatel
 */
public class InputDialog extends Dialog<ToDoItem>{
    private static final String GRID_PANE = "gridpane";
    private static final String BUTTON_WIDTH = "buttonWidth";
    private static final String HBOX = "hbox";
    private static final String CSS_PATH = "file:/home/jappatel/Documents/CSE%20219%20homeworks/homework1/ToDoListMakerApp/build/classes/tdlm/css/InputDialog.css";
    
    
    
    private ToDoItem todo = new ToDoItem();
    
    private TextField CatagoryTextField;
    private TextField DescriptionTextField;
    private DatePicker startDateDatePicker;
    private DatePicker endDateDatePicker;
    private CheckBox completeCheckBox;
   
    private ToDoItem item;       
    
    public InputDialog(String Catagory, String Description, LocalDate startDate, LocalDate endDate, boolean complete, AppTemplate app){
	PropertiesManager prop = PropertiesManager.getPropertiesManager();
	
	Stage stage = new Stage();
	stage.initModality(Modality.APPLICATION_MODAL);
	stage.setResizable(false);
	stage.setHeight(300);
	stage.setWidth(400);
	stage.centerOnScreen();
	GridPane gridpane = new GridPane();
	gridpane.getStyleClass().add(GRID_PANE);
	
	
	CatagoryTextField = new TextField(Catagory);
	gridpane.add(new Label(prop.getProperty(PropertyType.CATEGORY_COLUMN_HEADING)), 0, 0);
	gridpane.add(CatagoryTextField, 1, 0);
	
	DescriptionTextField = new TextField(Description);
	gridpane.add(new Label(prop.getProperty(PropertyType.DESCRIPTION_COLUMN_HEADING)), 0, 1);
	gridpane.add(DescriptionTextField, 1, 1);
	
	startDateDatePicker = new DatePicker(startDate);
	gridpane.add(new Label(prop.getProperty(PropertyType.START_DATE_COLUMN_HEADING)), 0, 2);
	gridpane.add(startDateDatePicker, 1, 2);
	
	endDateDatePicker = new DatePicker(endDate);	
	gridpane.add(new Label(prop.getProperty(PropertyType.END_DATE_COLUMN_HEADING)), 0, 3);
	gridpane.add(endDateDatePicker, 1, 3);
	
	completeCheckBox = new CheckBox();
	completeCheckBox.setSelected(complete);
	gridpane.add(new Label(prop.getProperty(PropertyType.CATEGORY_COLUMN_HEADING)), 0, 4);
	gridpane.add(completeCheckBox, 1, 4);
	
	HBox hbox = new HBox();
	Button okButton = new Button(prop.getProperty(PropertyType.OK_BUTTON));
	Button cancleButton = new Button(prop.getProperty(PropertyType.CANCEL_BUTTON));
	okButton.getStyleClass().add(BUTTON_WIDTH);
	cancleButton.getStyleClass().add(BUTTON_WIDTH);
	hbox.getChildren().addAll(cancleButton, okButton);
	hbox.getStyleClass().add(HBOX);
	
	
//	gridpane.add(cancleButton, 0, 5); gridpane.setHalignment(cancleButton, HPos.RIGHT);
//	gridpane.add(okButton, 1, 5); gridpane.setHalignment(okButton, HPos.LEFT);
//	
	gridpane.add(hbox, 1, 5);
	
	okButton.setOnAction(e->{
	    item = new ToDoItem(CatagoryTextField.getText(), DescriptionTextField.getText(),
		startDateDatePicker.getValue(), endDateDatePicker.getValue(),
		completeCheckBox.isSelected());
	    app.getGUI().updateToolbarControls(false);
	    stage.close();
	});
	
	cancleButton.setOnAction(e->{
	    stage.close();
	});
	Scene scene = new Scene(gridpane);
	scene.getStylesheets().add(CSS_PATH);
	stage.setScene(scene);
	stage.showAndWait();	
	
    }
    
    public ToDoItem getToDo(){
	return item;
    }
}
