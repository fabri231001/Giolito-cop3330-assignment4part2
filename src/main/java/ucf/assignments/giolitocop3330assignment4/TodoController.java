/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Fabrizio Giolito
 */

package ucf.assignments.giolitocop3330assignment4;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;

public class TodoController implements Initializable{

    private TodoList list;
    private int swOperator;


    @FXML
    private Button addB;

    @FXML
    private Button deleteB;

    @FXML
    private Button finishedMark;

    @FXML
    private TextField date;

    @FXML
    private TextField description;


    @FXML
    private TableView<TodoItem> table;

    @FXML
    private TableColumn<TodoItem, String> todoDate;

    @FXML
    private TableColumn<TodoItem, String> todoDescription;




    @FXML
    private MenuItem menuCompletedItems;

    @FXML
    private MenuItem menuIncompletedItems;

    @FXML
    private MenuItem clearList;

    @FXML
    private MenuItem viewAll;

    @FXML
    public void handleAddButton() {

        try
        {
            list.add(date.getCharacters().toString(), description.getCharacters().toString());
        } catch (IOException e) {
            popupError("Please enter a valid description of 1 to 256 characters");
        } catch (ParseException e) {
            popupError("Please enter a valid date in the format YYYY-MM-DD");
        } catch (OutOfMemoryError e) {
            popupError("List cannot contain more than 200 records");
        }

        description.clear();
        date.clear();
        refresh();
    }

    @FXML
    public void handleDeleteButton() {
        // Deletes the top item from the list
        list.delete(table.getSelectionModel().getSelectedItem());
        refresh();
    }

    @FXML
    public void handleClearList() {
        // Clear the list (new requirement) changed from deleteList
        list.clearList();
        refresh();
    }


    @FXML
    public void handleFinishedMark() {
        // Changes the completeMark of the item
        list.changeCompletedMark(table.getSelectionModel().getSelectedItem(), !table.getSelectionModel().getSelectedItem().getTodoCompletedMarker());
        refresh();
    }

    @FXML
    public void handleViewAll() {
        // Set swOperator to 0 then refresh
        swOperator = 0;
        refresh();
    }

    @FXML
    public void handleCompletedMarks(){

        swOperator = 1;
        refresh();
    }

    @FXML
    public void handleIncompletedMarks(){

        swOperator = 2;
        refresh();
    }


    public void refresh(){

        switch(swOperator){
            case 0: table.setItems(list.getList());
                    break;
            case 1: table.setItems(list.getCompletedItems());
                    break;
            case 2: table.setItems(list.getIncompletedItems());
        }

    }





    @FXML
    public void handleExportList() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showSaveDialog(table.getScene().getWindow());
        list.editfName(file);
        list.printFile();

    }

    @FXML
    public void handleSearch() {

        table.setItems(list.search(swOperator, description.getCharacters().toString(), date.getCharacters().toString()));

        description.clear();
        date.clear();

    }

    public void setUpDate() {

        todoDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        todoDate.setCellFactory(TextFieldTableCell.forTableColumn());
        todoDate.setOnEditCommit(event -> {
            TodoItem newItem = event.getRowValue();
            try {
                list.editDueDate(newItem, event.getNewValue());
            }
            catch (Exception e) {
                popupError("Please enter a valid date in YYYY-MM-DD format");
                table.refresh();
            }
        });
    }

    public void setUpDesc() {

        todoDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        todoDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        todoDescription.setOnEditCommit(event -> {
            TodoItem newItem = event.getRowValue();
            try {
                list.editDescription(newItem, event.getNewValue());
            }
            catch (Exception e) {
                popupError("Please enter a valid description of 1 to 256 characters");
            }
            table.refresh();
        });
    }




    private void popupError(String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }



    // I might need to change this later or add more methods to allow it to work
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        list = new TodoList();
        table.setEditable(true);

        setUpDesc();
        setUpDate();

        swOperator = 0;
        refresh();
    }
}