package fr.univtln.groupec.tperron710;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    public static void main(String[] args) {
        System.out.println( "Main method inside Thread : " +  Thread.currentThread().getName());
        launch(args);
    }

    @Override
    public void start(Stage args) throws Exception {
        System.out.println( "Start method inside Thread : " +  Thread.currentThread().getName());
    }
}
