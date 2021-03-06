import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by stevenburris on 9/14/16.
 */
public class Game {
    static Scanner scanner = new Scanner(System.in);
    static Player player = new Player();
    static final String FILE_NAME = "game.json";

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome,traveller");


        boolean keepRunning = true;

        while(keepRunning) {

            player.chooseName();
            player.chooseWeapon();
            player.chooseLocation();
            player.findItem("shield");
            player.findItem("potion");

            Enemy ogre = new Enemy("Ogre",10,10);
            player.battle(ogre);

            System.out.println("You Win!");
            System.out.println("Would you like to play again? Type y or n");

            String answer = customLine();
            if(answer.equalsIgnoreCase("n")){
                keepRunning = false;
            }
        }

        System.out.println("Game Over!");

    }

    static String customLine(){
        String line = scanner.nextLine();
        while(line.startsWith("/")){
            switch (line) {
                case "/exit":
                    System.exit(0);
                    break;
                case "/inv":
                    for (String item : player.items){
                        System.out.println(item);
                    }
                    if (player.items.isEmpty()){
                        System.out.println("You have no items yet!");
                    }
                    break;
                case "/save":
                    save();
                    break;
                case "/load":
                    load();
                    break;
                default:
                    System.out.println("Invalid command!");
                    break;

            }
            line = scanner.nextLine();
        }
        return line;
    }

    static void save() {
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.deep(true).serialize(player);
        File f = new File(FILE_NAME);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(json);
            fw.close();
        } catch (Exception e) {
            System.out.println("Couldn't save to file!");
        }
    }
    static void load() {
        File f = new File(FILE_NAME);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] contents = new char[fileSize];
            fr.read(contents, 0, fileSize);
            JsonParser parser = new JsonParser();
            player = parser.parse(contents, Player.class);
        }
        catch (Exception e) {
            System.out.println("Couldn't load file!");
        }
    }
}
