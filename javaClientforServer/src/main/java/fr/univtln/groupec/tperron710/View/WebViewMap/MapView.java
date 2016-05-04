package fr.univtln.groupec.tperron710.View.WebViewMap;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Created by tperron710 on 04/05/16.
 */
public class MapView extends Application {
    @Override
    public void start(Stage primaryStage) {
        final WebView webView = new WebView();
        AnchorPane.setTopAnchor(webView, 0d);
        AnchorPane.setLeftAnchor(webView, 0d);
        AnchorPane.setBottomAnchor(webView, 0d);
        AnchorPane.setRightAnchor(webView, 0d);
        // Barre sur le haut de l'écran : affichage.
        final ToggleGroup displayGroup = new ToggleGroup();
        final ToggleButton roadToggle = new ToggleButton("Route");
        roadToggle.setDisable(true);
        roadToggle.setToggleGroup(displayGroup);
        roadToggle.setSelected(true);
        final ToggleButton satelliteToggle = new ToggleButton("Satellite");
        satelliteToggle.setDisable(true);
        satelliteToggle.setToggleGroup(displayGroup);
        final ToggleButton hybrideToggle = new ToggleButton("Hybride");
        hybrideToggle.setDisable(true);
        hybrideToggle.setToggleGroup(displayGroup);
        final ToggleButton terrainToggle = new ToggleButton("Terrain");
        terrainToggle.setDisable(true);
        terrainToggle.setToggleGroup(displayGroup);
        displayGroup.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observableValue, Toggle oldValue, Toggle newValue) -> {
            String script = null;
            if (newValue == roadToggle) {
                script = "document.setMapTypeRoad()";
            } else if (newValue == satelliteToggle) {
                script = "document.setMapTypeSatellite()";
            } else if (newValue == hybrideToggle) {
                script = "document.setMapTypeHybrid()";
            } else if (newValue == terrainToggle) {
                script = "document.setMapTypeTerrain()";
            }
            if (script != null) {
                webView.getEngine().executeScript(script);
            }
        });
        final HBox topControls = new HBox(roadToggle, satelliteToggle, hybrideToggle, terrainToggle);
        AnchorPane.setTopAnchor(topControls, 6d);
        AnchorPane.setLeftAnchor(topControls, 50d);
        // Barre sur la gauche de l'écran : zoom.
        final Button zoomInButton = new Button("+");
        zoomInButton.setDisable(true);
        zoomInButton.setStyle("-fx-background-radius: 20, 19, 18, 17;");
        zoomInButton.setPrefSize(28, 28);
        zoomInButton.setOnAction(actionEvent -> webView.getEngine().executeScript("document.zoomIn();"));
        final Button zoomOutButton = new Button("-");
        zoomOutButton.setDisable(true);
        zoomOutButton.setStyle("-fx-background-radius: 20, 19, 18, 17;");
        zoomOutButton.setPrefSize(28, 28);
        zoomOutButton.setOnAction(actionEvent -> webView.getEngine().executeScript("document.zoomOut();"));
        final VBox leftControls = new VBox(zoomInButton, zoomOutButton);
        leftControls.setSpacing(6);
        AnchorPane.setTopAnchor(leftControls, 50d);
        AnchorPane.setLeftAnchor(leftControls, 6d);
        // Barre en bas de l'écran : recherche.
        final TextField searchField = new TextField();
        searchField.setDisable(true);
        searchField.setEditable(false);
        HBox.setHgrow(searchField, Priority.ALWAYS);
        final Button searchButton = new Button("Chercher");
        searchButton.setDisable(true);
        searchButton.setOnAction(actionEvent -> {
            final String toSearch = searchField.getText();
            if (toSearch != null && !toSearch.trim().isEmpty()) {
                webView.getEngine().executeScript(String.format("document.goToLocation('%s');", toSearch.trim()));
            }
        });
        final HBox bottomControls = new HBox(searchField, searchButton);
        bottomControls.setSpacing(6);
        AnchorPane.setLeftAnchor(bottomControls, 75d);
        AnchorPane.setBottomAnchor(bottomControls, 16d);
        AnchorPane.setRightAnchor(bottomControls, 6d);
        // Assemblage.
        final AnchorPane root = new AnchorPane();
        root.getChildren().setAll(webView, leftControls, topControls, bottomControls);
        final Scene scene = new Scene(root, 350, 300);
        primaryStage.setTitle("Test de Google Map");
        primaryStage.setScene(scene);
        primaryStage.show();
        // Chargement de la page.
        webView.getEngine().getLoadWorker().stateProperty().addListener((ObservableValue<? extends Worker.State> observableValue, Worker.State oldValue, Worker.State newValue) -> {
            final boolean disabled = newValue != Worker.State.SUCCEEDED;
            zoomInButton.setDisable(disabled);
            zoomOutButton.setDisable(disabled);
            roadToggle.setDisable(disabled);
            satelliteToggle.setDisable(disabled);
            hybrideToggle.setDisable(disabled);
            terrainToggle.setDisable(disabled);
            searchField.setEditable(!disabled);
            searchField.setDisable(disabled);
            searchButton.setDisable(disabled);
        });
        try {
            webView.getEngine().load(getClass().getResource("Connector.html").toExternalForm());
        }
        catch (Exception e){
            System.out.println("no");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
