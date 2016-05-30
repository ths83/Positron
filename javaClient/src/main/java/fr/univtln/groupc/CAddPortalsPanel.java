package fr.univtln.groupc;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CPortalEntity;
import fr.univtln.groupc.server.CServer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class CAddPortalsPanel {

    Client c = Client.create();
    WebResource mWebResource = c.resource(CServer.BASE_URI);
    String mJson;
    ObjectMapper mMapper = new ObjectMapper();

    private JPanel panel;
    private JFrame frame;
    private JList todoItemsList;
    private boolean introduceBugs;
    private List<String> todoItems = new ArrayList<String>();
    private Pattern pattern1 = Pattern.compile("\\b,.*\\b");
    private Pattern pattern2 = Pattern.compile("\\b.*,");
    String match1 = null, match2 = null;

    public void postPortal(String pPortalLong, String pPortalLat) throws IOException {
        ObjectMapper lMapper = new ObjectMapper();
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        lMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        lMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
        lMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
        Random rand = null;
        int randomNum = 0;
        if (rand != null) {
            randomNum = rand.nextInt((100000000) + 1);
        }
        else randomNum = 4544646;

        CPortalEntity lPortal = new CPortalEntity.CPortalBuilder(randomNum).longitude(Double.parseDouble(pPortalLong)).latitude(Double.parseDouble(pPortalLat)).build();
        System.out.println(Double.parseDouble(pPortalLong) + " " + Double.parseDouble(pPortalLat));
        mJson = mMapper.writeValueAsString(lPortal);
        mWebResource.path("/portals").type("application/json").accept("application/json").post(ClientResponse.class, mJson);

    }
    public CAddPortalsPanel(boolean introduceBugs) {
        this.introduceBugs = introduceBugs;
        createFrame();
        createMainPanel();
        addComponentsToMainPanel();
        addMainPanelToFrame();
    }

    public void display() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    private void createFrame() {
        frame = new JFrame("Portals displays List");
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addMainPanelToFrame() {
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panel, BorderLayout.CENTER);
    }

    private void createMainPanel() {
        panel = new JPanel();
        panel.setName("Main Panel");
        panel.setLayout(new BorderLayout());
    }

    private void addComponentsToMainPanel() {
        createUsernameInput();
        createUsersList();
    }

    private void createUsernameInput() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.add(new JLabel("Add your portal coords (example : 15.0012, 1454.0015) :  "));
        final JTextField textField = usernameField();
        topPanel.add(textField);
        topPanel.add(submitButton(textField));
        panel.add(topPanel, BorderLayout.NORTH);
    }

    private JButton submitButton(final JTextField textField) {
        @SuppressWarnings("serial")
        final JButton submitButton = new JButton("Add Portal") {{
            addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    todoItems.add(textField.getText());

                    // regex to separate long and lat
                    // Long regex
                    Matcher matcher1 = pattern1.matcher(textField.getText());
                    while (matcher1.find()) {
                        match1 = matcher1.group().replace(", ","");
                        System.out.println("Long : "+ match1) ;
                    }

                    // Lat regex
                    Matcher matcher2 = pattern2.matcher(textField.getText());
                    while (matcher2.find()) {
                        match2 = matcher2.group().replace(",","");
                        System.out.println("Lat : "+ match2) ;
                    }

                    try {
                        postPortal(match1, match2);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    updateTodoItems();
                }
            });
        }};
        submitButton.setName("add");
        return submitButton;
    }

    private void updateTodoItems() {
        todoItemsList.setListData(todoItems.toArray());
    }

    private JTextField usernameField() {
        final JTextField textField = new JTextField();
        textField.setName("description");
        return textField;
    }

    private void createUsersList() {
        JPanel usersPanel = usersPanel();
        usersPanel.add(usersList(), BorderLayout.CENTER);
        usersPanel.add(deleteButton(), BorderLayout.SOUTH);
        panel.add(usersPanel, BorderLayout.CENTER);
    }

    private JList usersList() {
        todoItemsList = new JList();
        todoItemsList.setName("todolist");
        updateTodoItems();
        return todoItemsList;
    }

    private JPanel usersPanel() {
        JPanel listPane = new JPanel();
        listPane.setLayout(new BorderLayout());
        listPane.add(new JLabel("Portals added:"), BorderLayout.NORTH);
        return listPane;
    }

    private JPanel deleteButton() {
        JPanel deleteButtonPane = new JPanel();
        deleteButtonPane.setLayout(new BorderLayout());
        @SuppressWarnings("serial")
        final JButton deleteButton = new JButton("Delete") {{
            addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    removeTodoItem();
                }
            });
        }};
        deleteButton.setName("delete");
        deleteButtonPane.add(deleteButton, BorderLayout.EAST);
        return deleteButtonPane;
    }

    private void removeTodoItem() {
        Object possibleSelection = todoItemsList.getSelectedValue();
        if (introduceBugs || possibleSelection != null) {
            todoItems.remove(possibleSelection.toString());
            updateTodoItems();
        }
    }

    public static void main(String[] args) {
        new CAddPortalsPanel(false).display();
    }
}