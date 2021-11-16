/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Fabrizio Giolito
 */

package ucf.assignments.giolitocop3330assignment4;

import javafx.application.Platform;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class TodoListTest {

    TodoList list;

    void init() {

        Platform.startup(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Test
    void editDate() throws IOException, ParseException {

        list = new TodoList();
        list.add("description", "2001-10-23");
        list.editDueDate(list.getList().get(0), "2021-11-15");
        assertEquals("2021-11-15", list.getList().get(0).getDueDate());

    }

    @Test
    void editDesc() throws IOException, ParseException {

        list = new TodoList();
        list.add("description", "2001-10-23");
        list.editDescription(list.getList().get(0), "new");
        assertEquals("new", list.getList().get(0).getDescription());
    }

    @Test
    void changeCompletedMark() throws IOException, ParseException {

        list = new TodoList();
        list.add("description", "2001-10-23");
        list.getList().get(0).changeCompletedMark(true);

        assertTrue(list.getList().get(0).getTodoCompletedMarker());
    }

    @Test
    void delete() throws IOException, ParseException {

        list = new TodoList();
        list.add("description", "2001-10-23");
        list.add("description", "2001-10-23");
        list.delete(list.getList().get(1));
    }

    @Test
    void editFileName() {

        list = new TodoList();
        File file = new File("./test");
        list.editfName(file);
        assertEquals(file, list.getfName());

    }




}
