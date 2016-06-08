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
        WebResource webResource = c.resource(CServer.BASE_URI);
        ObjectMapper lMapper = new ObjectMapper();
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        lMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        lMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        lMapper.configure(DeserializationFeature.FAIL_ON_UNRESOLVED_OBJECT_IDS, false);
        lMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);

        System.out.println("lmapper configured");

        String lPlayersJson = webResource.path("/players").get(String.class);

        System.out.println("players gotten");

        ArrayList<CPlayerEntity> lPlayers = lMapper.readValue(lPlayersJson, lMapper.getTypeFactory().constructCollectionType(List.class, CPlayerEntity.class));

        for (int i = 0 ; i < lPlayers.size() ; i ++) {
            System.out.println("player n° " + i);
            System.out.println(lPlayers.get(i).getNickName());
            System.out.println(lPlayers.get(i).getXp());
        }

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("TOP USERS");
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] selections = { lPlayers.get(0).getNickName(), lPlayers.get(1).getNickName(), lPlayers.get(2).getNickName()};

        JList<String> list = new JList<String>(selections);

        list.setSelectedIndex(1);
        System.out.println(list.getSelectedValue());
        frame.add(new JScrollPane(list));
        frame.pack();

        frame.setVisible(true);
    }
}