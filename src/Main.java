import net.sourceforge.sizeof.SizeOf;

import javax.swing.*;
import java.io.*;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by Ramon Brand on 20-Oct-16.
 */
public class Main {

    public static void main(String[] args){

///*
        //System.out.println(SizeOf.sizeOf(new Tile[100000000])/1000000 + " MB");

        UserInterface ui = new UserInterface();
        SwingUtilities.invokeLater(ui);

        Tile myTile = new Tile("my cool tile!");
        myTile.x = 55;
        myTile.incrementX();
        myTile.y = 7;

        try {

            File myFile = new File("tile.ser");
            FileOutputStream fileOutputStream = new FileOutputStream(myFile);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(myTile);
            objectOutputStream.close();

            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
//*/
/*

        Tile newTile = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("tile.ser");

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            newTile = (Tile) objectInputStream.readObject();
            objectInputStream.close();

            fileInputStream.close();
        }catch(Exception e) {
            e.printStackTrace();
        }

        System.out.println("X: " + newTile.x);
        System.out.println("Y: " + newTile.y);
        System.out.println("Name: " + newTile.getName());
        */

    }

}
