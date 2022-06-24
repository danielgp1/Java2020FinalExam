package com.example.gui;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import model.Department;
import model.Manager;
import model.Staff;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnQuit;

    @FXML
    private ComboBox<Staff> cboStaffMember;

    @FXML
    private Label lblHiredAt;

    @FXML
    private Label lblIncrease;

    @FXML
    private Label lblName;

    @FXML
    private Label lblSalary;

    @FXML
    private Label lblWorksAt;

    @FXML
    private Slider sldIncrease;

    private Staff[] candidates;
    private Manager manager;
    private Department department;
    private Random rand;
    private ObservableList<Staff> list;

    @FXML
    void btnQuitOnActionHandler(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void cboStaffMemberOnActionHandler(ActionEvent event) {
        lblName.setText(cboStaffMember.getValue().getName());
        lblWorksAt.setText(cboStaffMember.getValue().getWorkAt());
        lblHiredAt.setText(cboStaffMember.getValue().getHiredAt().format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }

    @FXML
    void sldIncreaseOnMouseExited(MouseEvent event)   {
        Staff member = cboStaffMember.getValue();
        double salary =member.getSalary() * ((100 + sldIncrease.getValue())/100) ;
        lblIncrease.setText(String.format("%.2f %%", this.sldIncrease.getValue()));
        lblSalary.setText(String.format("%.2f лв.", salary));
    }

    @FXML
    void initialize() {
        assert btnQuit != null : "fx:id=\"btnQuit\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert cboStaffMember != null : "fx:id=\"cboStaffMember\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert lblHiredAt != null : "fx:id=\"lblHiredAt\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert lblIncrease != null : "fx:id=\"lblIncrease\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert lblName != null : "fx:id=\"lblName\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert lblSalary != null : "fx:id=\"lblSalary\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert lblWorksAt != null : "fx:id=\"lblWorksAt\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert sldIncrease != null : "fx:id=\"sldIncrease\" was not injected: check your FXML file 'hello-view.fxml'.";
        lblIncrease.setText("0.00 %");
        lblSalary.setText("0.00 лв.");
        candidates = new Staff[]{new Staff("Staff1",0,null,null),
                                 new Staff("Staff2",0,null,null),
                                 new Staff("Staff3",0,null,null),
                                 new Staff("Staff4",0,null,null),
                                 new Staff("Staff5",0,null,null),
                                 new Staff("Staff6",0,null,null)};
        manager = new Manager("Manager", 0, null);
        department = new Department(manager,"Department");
        manager.setAppointRule(department.appointmentHandler);
        rand = new Random();
        for (Staff candidate : candidates) {
            manager.onStaffAppointment(candidate, 1000 + rand.nextDouble(1001));
        }
        list = FXCollections.observableArrayList();
        list.addAll(department.getStaff());
        cboStaffMember.setConverter(new StringConverter<Staff>() {
            @Override
            public String toString(Staff usr) {
                return usr == null ? "" : usr.ID + ": " + usr.getName();
            }

            @Override
            public Staff fromString(String s) {
                return null;
            }
        });
        cboStaffMember.setItems(list);
    }
}
