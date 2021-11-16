package ucf.assignments.giolitocop3330assignment4;

import javafx.application.Platform;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class TodoItemTest {

    TodoItem item;


    void init(){

        Platform.startup(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    @Test
    void TodoItem() {

        item = new TodoItem("description", "2001-10-23");
        boolean check = true;

        if (!item.getDueDate().equals("2001-10-23"))
            check = false;
        if (!item.getDescription().equals("description"))
            check = false;

        assertTrue(check);
    }

    @Test
    void editDate() {

        item = new TodoItem("description", "2001-10-23");
        item.editDueDate("2021-11-15");
        assertEquals("2001-10-23", item.getDueDate());
    }

    @Test
    void editDescription() throws IOException {

        item = new TodoItem("description", "2001-10-23");
        item.editDescription("test");
        assertEquals("test", item.getDescription());
    }

    @Test
    void changeCompletedMark() {

        init();
        item = new TodoItem("description", "2001-10-23");
        item.changeCompletedMark(true);

        assertTrue(item.getTodoCompletedMarker());

    }



}
