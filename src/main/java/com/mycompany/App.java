package com.mycompany;
/* 


I certify, that this computer program submitted by me is all of my own work, Wyatt Fredrickson.

Wyatt Fredrickson

06-09-2024

CSC 322 


Sources:

Java Programming Book: Introduction to Java Programming and Data Structures, Comprehensive Version, 12th Edition
https://docs.oracle.com/en/java/javase/22/
https://docs.oracle.com/javase/8/docs/platform/serialization/spec/serial-arch.html
https://docs.oracle.com/javase/8/javafx/get-started-tutorial/jfx-overview.htm
https://docs.oracle.com/javase/8/javafx/layout-tutorial/builtin_layouts.htm
https://www.tutorialspoint.com/javafx/javafx_listview.htm
https://www.javatpoint.com/javafx-ui-controls

*/
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The main application clas for the 'AddressBook' app using javafx.base.
 * 
 */
public class App extends Application {
    // The ListView to display contacts 
    private ListView<Contacts> contactListView;

    // All form fields attributes/properties
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField streetField;
    private TextField cityField;

    // The ComboBox to display all 50 states
    private ComboBox<String> stateComboBox;
    private TextField phoneField;
    private TextField emailField;
    private TextArea notesArea;

    // Declare/initialize a private immutable variable for file storage for all contacts
    private static final String DATA_FILE = "contacts.ser";

    /**
     * The beginning main entry point for the JavaFX application
     * @param primaryStage the primary stage for the application, where the application scene can be set.
     */
    @Override
    public void start(Stage primaryStage) {
        // Setting the title of the primary stage (The window)
        primaryStage.setTitle("AddressBook"); // Describes the 'window' of the application

        // Create a main layout instances of BorderPane
        BorderPane root = new BorderPane();

        // Create the ListView for displaying the contacts
        contactListView = new ListView<>();
        contactListView.setPrefHeight(200); // Adjust the height for the ListView as need.
        contactListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> populateForm(newValue));
        loadContacts(); // Invoke the method and load in existing contacts when application starts. 

        // Creating a label for the title of the contacts list
        Label contactListTitle = new Label("Contacts");
        contactListTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-padding: 10px;");

        // Creating a VBox to add the padding/spacing/height around the ListView that displays the 'contacts'
        VBox contactListContainer = new VBox(contactListTitle, contactListView);
        contactListContainer.setPadding(new Insets(20)); // Adjust the padding as needed.
        contactListContainer.setSpacing(10); // Adjust the spacing as needed.
        contactListContainer.setPrefHeight(100);

        // Creating the contact form using GridPane
        GridPane contactForm = new  GridPane();
        contactForm.setPadding(new Insets(10)); // Set padding for contact form as needed.
        contactForm.setHgap(15); // Set the horizontal gap between columns, adjust as needed for contact form.
        contactForm.setVgap(10); // Set the vertical gap between rows, adjust as needed for contact form.
        // Inline CSS styles
        contactForm.setStyle(STYLESHEET_CASPIAN);





        // ****Add the form fields/labels****
        // First name label
        Label firstNameLabel = new Label("First:");
        firstNameField = new TextField();
        // Inline CSS styles
        firstNameLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-text-fill: #333;");
        firstNameField.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 5px; -fx-border-color: #ccc; -fx-border-radius: 5px;");
        contactForm.add(firstNameLabel, 0, 0);
        contactForm.add(firstNameField, 1, 0);
        // Last name label
        Label lastNameLabel = new Label("Last:");
        lastNameField = new TextField();
        // Inline CSS styles
        lastNameLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-text-fill: #333;");
        lastNameField.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 5px; -fx-border-color: #ccc; -fx-border-radius: 5px;");
        contactForm.add(lastNameLabel, 2, 0);
        contactForm.add(lastNameField, 3, 0);
        // Street name label
        Label streetLabel = new Label("Street:");
        streetField = new TextField();
        // Inline CSS styles
        streetLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-text-fill: #333;");
        streetField.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 5px; -fx-border-color: #ccc; -fx-border-radius: 5px;");
        contactForm.add(streetLabel, 0, 1);
        contactForm.add(streetField, 1, 1, 3, 1);
        // City name label
        Label cityLabel = new Label("City:");
        cityField = new TextField();
        // Inline CSS styles
        cityLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-text-fill: #333;");
        cityField.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 5px; -fx-border-color: #ccc; -fx-border-radius: 5px;");
        contactForm.add(cityLabel, 0, 2);
        contactForm.add(cityField, 1, 2);
        // State name label
        Label stateLabel = new Label("State:");
        stateComboBox = new ComboBox<>();
        // Inline CSS styles
        stateLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-text-fill: #333;");
        stateComboBox.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 5px; -fx-border-color: #ccc; -fx-border-radius: 5px;");
        stateComboBox.getItems().addAll
        ("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", 
                     "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", 
                     "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", 
                     "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", 
                     "VT", "VA", "WA", "WV", "WI", "WY"); 
        stateComboBox.setValue(""); // Setting the default value when the application will start up
        contactForm.add(stateLabel, 2, 2);
        contactForm.add(stateComboBox, 3, 2);
        // Phone number label
        Label phoneLabel = new Label("Phone:");
        phoneField = new TextField();
        // Inline CSS styles 
        phoneLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-text-fill: #333;");
        phoneField.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 5px; -fx-border-color: #ccc; -fx-border-radius: 5px;");
        contactForm.add(phoneLabel, 0, 3);
        contactForm.add(phoneField, 1, 3);
        // Email name label
        Label emailLabel = new Label("Email:");
        emailField = new TextField();
        // Inline CSS styles
        emailLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-text-fill: #333;");
        emailField.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 5px; -fx-border-color: #ccc; -fx-border-radius: 5px;");
        contactForm.add(emailLabel, 0, 4);
        contactForm.add(emailField, 1, 4);
        // Notes name label
        Label notesLabel = new Label("Notes:");
        TextField notesField = new TextField();
        notesArea = new TextArea();
        // Inline CSS styles
        notesLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-text-fill: #333;");
        notesArea.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 5px; -fx-border-color: #ccc; -fx-border-radius: 5px;");
        notesArea.setPrefRowCount(5); // Adjust and increase row count as needed. 
        contactForm.add(notesLabel, 0, 5);
        contactForm.add(notesArea, 1, 5, 3, 1);





        // Adding in all the buttons and there actions as well
        Button newButton = new Button("New"); // New contact
        Button updateButton = new Button("Update"); // Update contact
        Button deleteButton = new Button("Delete"); // Delete contact
        // Inline CSS styles of each button
        newButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10px;");
        updateButton.setStyle("-fx-background-color: #FFC107; -fx-text-fill: white; -fx-padding: 10px;");
        deleteButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white; -fx-padding: 10px;");
        contactForm.add(newButton, 1, 6);
        contactForm.add(updateButton, 2, 6);
        contactForm.add(deleteButton, 3, 6);

        // Adjustment of the row index for the buttons, adjust as needed
        GridPane.setRowIndex(newButton, 7);
        GridPane.setRowIndex(updateButton, 7);
        GridPane.setRowIndex(deleteButton, 7);
        // Span the entire notesArea across all remaining columns
        GridPane.setColumnSpan(notesArea, GridPane.REMAINING); 

        // Setup the button actions with event listeners. 
        newButton.setOnAction(event -> createNewContact());
        updateButton.setOnAction(event -> updateContact());
        deleteButton.setOnAction(event -> deleteContact());
    
        // The icon and header that is placed above the application form
        HBox header = new HBox();
        header.setSpacing(10); // Adjust the spacing between icon and label as needed
        ImageView icon = new ImageView(new Image(getClass().getResource("/image/addressbooklogo.jpg").toExternalForm()));
        icon.setFitHeight(50); // Setting the icon height, adjust as needed
        icon.setFitWidth(50); // Setting the icon width, adjust as needed
        
        Label titleLabel = new Label("Addressbook");
        // Inline CSS styles for the header that is placed above the application form
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;"); // Set style, adjust as needed
        header.getChildren().addAll(icon, titleLabel); // Adding the icon and label to the header
        header.getStyleClass().add("header");

        // Create a new form instance using VBox
        VBox formContainer = new VBox(header, contactForm);
        formContainer.setPadding(new Insets(20)); // Set the padding around the form, adjust as needed
        formContainer.setSpacing(20); // Adjust spacing as needed.
        // Add the ListView and form to the BorderPane
        root.setLeft(contactListContainer); // Add contact list to the left view
        root.setCenter(formContainer); // Add form to the center of view

        // Create new instance scene with the BorderPane as the roote node
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene); // Setting the scene to the primary stage

        // Add notes
        Application.setUserAgentStylesheet(STYLESHEET_CASPIAN);
        // Add notes
        primaryStage.setOnCloseRequest(event -> saveContacts()); // Point to method 'saveContacts'
        primaryStage.show(); // Now show the primary stage
    }

    /**
    * Method for loading contacts from a file location.
    */
    private void loadContacts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            contactListView.getItems().clear(); // Clears the existing items from the list
            Contacts[] contactsArray = (Contacts[]) ois.readObject(); // Reading contacts from file
            contactListView.getItems().addAll(contactsArray); // Adding the contacts to the ListView
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No existing contacts can be found or there is an error reading the file.");
        }
    }

    /**
    * Method for saving contacts to a file location.
    */
    private void saveContacts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            List<Contacts> contactsList = new ArrayList<>(contactListView.getItems()); 
            oos.writeObject(contactsList.toArray(new Contacts[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * Method for populating the form fields with the selected contact's details.
    */
    private void populateForm(Contacts contact) {
        if (contact != null) {
            firstNameField.setText(contact.getFirstName());
            lastNameField.setText(contact.getLastName());
            streetField.setText(contact.getStreet());
            cityField.setText(contact.getCity());
            stateComboBox.setValue(contact.getState());
            phoneField.setText(contact.getPhone());
            emailField.setText(contact.getEmail());
            notesArea.setText(contact.getNotes()); 
        }
    }

    /**
    * Method for creating a new contact with the default state values.
    */
    private void createNewContact() {
        Contacts newContact = new Contacts("New", "New", "", "", "", "", "", "");
        contactListView.getItems().add(newContact); // add nex contact to the ListView
        contactListView.getSelectionModel().selectLast(); // Get the new contact
        populateForm(newContact); // Now populare form with the new contact's details
    }

    /**
    * Method used for updating the contact with the values from the form.
    */
    private void updateContact() {
        int selectedIndex = contactListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Contacts selectedContact = contactListView.getItems().get(selectedIndex);
            selectedContact.setFirstName(firstNameField.getText());
            selectedContact.setLastName(lastNameField.getText());
            selectedContact.setStreet(streetField.getText());
            selectedContact.setCity(cityField.getText());
            selectedContact.setState(stateComboBox.getValue());
            selectedContact.setPhone(phoneField.getText());
            selectedContact.setEmail(emailField.getText());
            selectedContact.setNotes(notesArea.getText());
            contactListView.refresh(); // Refresh the ListView so that it shows updated contact information as needed
        }
    }

    /**
    * Method used for deleting the selected contact from the list. 
    */
    private void deleteContact() {
        int selectedIndex = contactListView.getSelectionModel().getSelectedIndex(); // Retrieve the contact via selectedIndex
        if (selectedIndex >= 0) {
            contactListView.getItems().remove(selectedIndex); // Removing the selected contact
            clearForm(); // Clear all fields
        }
    }

    /**
    * Method used for clearing the form fields.
    */
    private void clearForm() {
        firstNameField.setText(""); // Clearing the first name field.
        lastNameField.setText(""); // Clearing the last name field.
        streetField.setText(""); // Clearing the street field.
        cityField.setText(""); // Clearing the city field.
        stateComboBox.setValue(""); // Reseting the state combo box
        phoneField.setText(""); // Clearing the phone field.
        emailField.setText(""); // Clearing the email field.
        notesArea.setText(""); // Cleating the notes field.

    }

    /**
    * The main method entry point of the application.
    */
    public static void main(String[] args) {
        launch(args);
    }
}