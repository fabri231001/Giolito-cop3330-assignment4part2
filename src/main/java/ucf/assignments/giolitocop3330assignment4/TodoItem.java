/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Fabrizio Giolito
 */

package ucf.assignments.giolitocop3330assignment4;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TodoItem {


    private String description;
    private String dueDate;

    private Boolean completedMark;
    private String status;

    public TodoItem() {

        dueDate = null;
        description = null;
        completedMark = false;
        status = "Unfinished";
    }

    public TodoItem(String description, String dueDate) {
        // Set name, due date, and description using this.

        this.dueDate = dueDate;
        this.description = description;

        // Make completedMark false
        completedMark = false;
        status = "Unfinished";
    }




    public void editDescription(String newDescription) throws IOException{
        // Change description using this. edit: New requirement, the description can't be greater than 256 characters
        // First check length, then use this.
        if(newDescription.length() > 256)
            throw new IOException();

        this.description = newDescription;
    }

    public void editDueDate(String newDueDate) {
        // Change due date using this.
        // Edit: if in the wrong format throw exception
        // If no date is given leave empty ""
        if(!dueDate.isEmpty()){
            DateFormat ymd = new SimpleDateFormat("YYYY-MM-DD");
            ymd.setLenient(false);
            try {
                ymd.parse(newDueDate);
            } catch (ParseException e){}
        }
        this.dueDate = newDueDate;
    }

    public void changeCompletedMark(Boolean mark){
        // If false, change to true
        // If true, change to false (not needed but if user didn't finish or wants to change it
        if(mark){
            completedMark = true;
            status = "Finished";
        }

        if(!mark){
            completedMark = false;
            status = "Unfinished";
        }

    }



    public String getDescription(){
        // Return description
        return description;
    }

    public String getDueDate(){
        // Return due date
        return dueDate;
    }

    public Boolean getTodoCompletedMarker(){
        // Return completedMark
        return completedMark;
    }

    public String getStatus(){

        return status;
    }

}
