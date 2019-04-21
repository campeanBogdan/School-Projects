package View.Presentation.Admin;

import javax.swing.*;
import java.util.Observable;

public class SendChanges extends Observable {

    public void sendInsertBookButton() {
        setChanged();
        notifyObservers("Insert Book");
    }

    public void sendDeleteBookButton() {
        setChanged();
        notifyObservers("Delete Book");
    }

    public void sendUpdateBookButton() {
        setChanged();
        notifyObservers("Update Book");
    }

    public void sendInsertUser() {
        setChanged();
        notifyObservers("Insert User");
    }

    public void sendDeleteUser() {
        setChanged();
        notifyObservers("Delete User");
    }

    public void loginUserButton() {
        setChanged();
        notifyObservers("Login User");
    }

    public void loginAdminButton() {
        setChanged();
        notifyObservers("Login Admin");
    }
}
