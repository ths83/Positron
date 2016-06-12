package fr.univtln.groupc;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import fr.univtln.groupc.entities.CPlayerEntity;
import fr.univtln.groupc.server.CServer;

import java.awt.FlowLayout;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;


public class CTopList {

    public void mainFrame() throws IOException {
    }

    public static void main(String[] args) throws IOException {
        System.out.println("go");

        Client c = Client.create();

        // Mapper configuration to get REST Liste Objects

        WebResource webResource = c.resource(CServer.BASE_URI);
        ObjectMapper lMapper = new ObjectMapper();
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        lMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        lMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
        lMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);

        // Recupérate all players to DataBase
        String lPlayersJson = webResource.path("/players").get(String.class);
        ArrayList<CPlayerEntity> lPlayers = lMapper.readValue(lPlayersJson, lMapper.getTypeFactory().constructCollectionType(List.class, CPlayerEntity.class));

        // Create the frame that will contains players
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("TOP 3 USERS");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ArrayList<String> names = new ArrayList<>();

        for (CPlayerEntity lPlayer : lPlayers){
            names.add(lPlayer.getNickName());
        }

        String[] selections =null;

        if (names.size()==3)
            selections = new String[]{lPlayers.get(0).getNickName(), lPlayers.get(1).getNickName(), lPlayers.get(2).getNickName()};

        else if (names.size()==2)
            selections = new String[]{lPlayers.get(0).getNickName(), lPlayers.get(1).getNickName()};

        else if (names.size()==1)
            selections = new String[]{lPlayers.get(0).getNickName()};

        if (selections != null) {
            JList<String> list = new JList<String>(selections);
            list.setSelectedIndex(1);
            frame.add(new JScrollPane(list));
        }

        frame.pack();

        frame.setVisible(true);
    }
}