/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Fabrizio Giolito
 */

package ucf.assignments.giolitocop3330assignment4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.io.File;

public class TodoList {

    private ObservableList<TodoItem> list;
    private File fName;

    public TodoList(){
        // Make To-do list with the items from the given list
        // Set fName to null

        list = FXCollections.observableArrayList();
        fName = null;
    }

    public void addItem(TodoItem item){
        // Add item to the list through .add

        list.add(item);
    }

    public void add(String description, String dueDate) throws IOException, ParseException, IllegalAccessError {
        // Call TodoItem constructor with description and dueDate
        // Give next available ID from the list (numerical order)
        // Add item to list with .add

        //edit: doesn't seem to work without throwing the exceptions even though constructor checks... need to read up on it

        TodoItem item = new TodoItem(dueDate, description);
        addItem(item);
    }

    public void delete(TodoItem item){
        // Delete from the list using .remove
        list.remove(item);
    }

    public void editDescription(TodoItem item, String description) throws IOException{
        // Using get with the itemID then use the editDescription
        item.editDescription(description);
    }

    public void editDueDate(TodoItem item, String date) {
        // Make sure the format is correct, throw exception otherwise
        // Use get with the itemID and use editDueDate
        item.editDueDate(date);
    }

    public void changeCompletedMark(TodoItem item, Boolean mark){
        // Use get with the item and call changeCompletedMark
        item.changeCompletedMark(mark);
    }

    public ObservableList<TodoItem> getList(){
        //return list
        return list;
    }

    public File getfName() {
        //return fName
        return fName;
    }



    public ObservableList<TodoItem> getIncompletedItems(){
        // Create a new list and add any items with the completedMarker on false
        ObservableList<TodoItem> incomplete = FXCollections.observableArrayList();

        for(int i = 0; i < list.size(); i++){
            if(!list.get(i).getTodoCompletedMarker())
                incomplete.add(list.get(i));
        }
        // Return list
        return incomplete;
    }

    public ObservableList<TodoItem> getCompletedItems(){
        // Create a new list and add any items with the completedMarker on true
        ObservableList<TodoItem> complete = FXCollections.observableArrayList();

        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getTodoCompletedMarker())
                complete.add(list.get(i));
        }
        // Return list
        return complete;
    }


    public void editfName(File name){
        // Change fName to name with this.
        this.fName = name;
    }

    public void clearList(){
        // Clears the file (new requirement)
        list.clear();
    }

    public void printFile(){
        // Create a new file and print the list
        try (FileWriter myWriter = new FileWriter(fName)) {
            for (TodoItem item : list) {

                myWriter.write(item.getDescription() + ",");
                if (item.getDueDate().isEmpty()) {
                    myWriter.write(("\"\"" + ","));
                } else {
                    myWriter.write(item.getDueDate() + ",");
                }
                if (item.getTodoCompletedMarker())
                    myWriter.write("Finished");
                else
                    myWriter.write("Unfinished");
                myWriter.write("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public ObservableList<TodoItem> search (int check, String description, String date) {
        ObservableList<TodoItem> temp = null;
        if (check == 0)
            temp = FXCollections.observableArrayList(getList());
        else if (check == 1)
            temp =FXCollections.observableArrayList(getCompletedItems());
        else
            temp = FXCollections.observableArrayList(getIncompletedItems());


        if(!description.isBlank()) {
            temp.removeIf(searchItem -> !searchItem.getDescription().toLowerCase(Locale.ROOT).contains(description.toLowerCase(Locale.ROOT)));
        }

        if (!date.isBlank()) {
            temp.removeIf(searchItem -> !searchItem.getDueDate().contains(date));
        }
        return  temp;
    }
}


