package control;

import gui_fields.*;
import gui_main.GUI;
import model.ChanceCards;
import model.Dice;
import model.Player;

import java.awt.*;
import java.util.Arrays;

public class Game {
    GUI gui;
    ChanceCards cc;
    Player[] players;
    GUI_Field[] fields = new GUI_Field[24];
    Dice d1 = new Dice();
    Dice d2 = new Dice();
    boolean gameFinished = false;
    String[] fieldNames = {"START", "BURGER JOINT", "PIZZA HOUSE", "CHANCE", "CANDY SHOP", "ICE CREAM PARLOUR", "VISIT JAIL", "MUSEUM", "LIBRARY", "CHANCE", "SKATE PARK", "SWIMMING POOL", "FREE PARKING", "VIDEOGAME ARCADE", "CINEMA", "CHANCE",  "TOY SHOP", "PET SHOP", "GO TO JAIL",  "BOWLING ALLEY", "THE ZOO", "CHANCE", "WATER PARK", "BOARDWALK"};
    Color[] fieldColors = {null,
            new Color(129, 83, 37), new Color(129, 83, 37), null, //brown
            new Color(139, 193, 222), new Color(139, 193, 222), null, //light blue
            new Color(183, 64, 144), new Color(183, 64, 144), null, //magenta
            new Color(207, 149, 58), new Color(207, 149, 58), null, //orange
            new Color(191, 55, 51), new Color(191, 55, 51), null, //red
            new Color(223, 224, 61), new Color(223, 224, 61), null, //yellow
            new Color(53, 185, 47), new Color(53, 185, 47), null, //green
            new Color(47, 51, 145), new Color(47, 51, 145), null};//blue
    int[] fieldCosts = {0, 1, 1, 0, 1, 1, 0 , 2, 2, 0, 2, 2, 0, 3, 3, 0, 3, 3, 0, 4, 4, 0, 5, 5};
    boolean[] fieldBought = new boolean[24];
    String[] descriptions = {
            "Receive 2 points for passing start.",
            "Grab a burger with shake and fries. yum!",
            "You can never go wrong with a good pizza.",
            "Draw a chance card.",
            "Tasty treats and sugary sweets.",
            "If the weather is hot, get an ice cream to cool down!",
            "Just visiting the local jail. how fun.",
            "A museum of priceless art.",
            "Soon to be replaced by the internet.",
            "Draw a chance card.",
            "Ride your skateboard at the skate park! Tubular!", //https://www.urbandictionary.com/define.php?term=Tubular
            "Take a dive at the swimming pool!",
            "Free space, pay no rent.",
            "Play video games with your friends!",
            "Watch the latest films.",
            "Draw a chance card.",
            "Any child's paradise: the toy store.",
            "The cutest dogs, cats and other pets.",
            "Go back to jail. sad!",
            "Go bowling with your cousin! or dont, he's kind of annoying.",
            "From elephants to giraffes, to tarantulas, the zoo has a wide variety of animals.",
            "Draw a chance card.",
            "Go down the tallest water slide, if you dare!",
            "Take in the fresh sea-air at the boardwalk." };

    public Game(){
        createFields();
        gui = new GUI(fields);
        cc = new ChanceCards(gui);
        registerPlayers(gui);
    }

    private void registerPlayers(GUI gui){
        int amountOfPlayers = (gui.getUserInteger("Enter a number of contestants between 2 and 4."));
        while (amountOfPlayers > 4 || amountOfPlayers < 2) {
            amountOfPlayers = (gui.getUserInteger("Enter a number of contestants between 2 and 4."));
        }
        players = new Player[amountOfPlayers];

        for(int i = 0; i < players.length; i++){
            String name = gui.getUserString(("Enter the name of player " + (i + 1)));
            int balance = 20 - ((amountOfPlayers - 2) * 2);
            Color color;
            switch (i){
                case 0:
                    color = Color.RED;
                    break;
                case 1:
                    color = Color.BLUE;
                    break;
                case 2:
                    color = Color.YELLOW;
                    break;
                case 3:
                    color = Color.GREEN;
                    break;
                default:
                    color = Color.BLACK;
            }

            players[i] = new Player(name, balance, addPlayer(color, name, balance), gui, cc);
            players[i].setPosition(0);
        }
    }

    private GUI_Player addPlayer(Color color1, String name, int startPoints){
        GUI_Car car = new GUI_Car();
        car.setPrimaryColor(color1);
        GUI_Player player = new GUI_Player(name, startPoints, car);
        gui.addPlayer(player);
        gui.getFields()[0].setCar(player, true);

        return player;
    }

    private void createFields(){
        for(int i = 0; i < fields.length; i++){
            if(fieldNames[i].equals("START")){
                fields[i] = new GUI_Start();
            }else if(fieldNames[i].equals("CHANCE")){
                fields[i] = new GUI_Chance();
            }else if(fieldNames[i].equals("VISIT JAIL") || fieldNames[i].equals("GO TO JAIL")){
                fields[i] = new GUI_Jail();
            }else if(fieldNames[i].equals("FREE PARKING")){
                fields[i] = new GUI_Refuge();
            }else{
                GUI_Street street = new GUI_Street();
                street.setBackGroundColor(fieldColors[i]);
                if(fieldCosts[i] > 0){
                    street.setRentLabel("Cost" + ": ");
                    street.setRent(Integer.toString(fieldCosts[i]));
                }else{
                    street.setOwnableLabel("Owner: ");
                    street.setOwnerName("None");
                }
                fields[i] = street;
            }
            if(fieldCosts[i] > 0 && !fieldBought[i]){
                fields[i].setSubText("Cost: " + fieldCosts[i]);
            }else if(fieldCosts[i] > 0 && fieldBought[i]){
                fields[i].setSubText("Rent: " + fieldCosts[i]);
            }
            fields[i].setTitle(fieldNames[i]);
            fields[i].setTitle(fieldNames[i]);
            fields[i].setDescription(descriptions[i]);
        }

    }

    private void buyField(int fieldPosition, Player player){
        GUI_Street street = (GUI_Street) fields[fieldPosition];
        //set owner of field
        street.setOwnableLabel("Owner: ");
        street.setOwnerName(player.getName());
        //set rent of field
        street.setRentLabel("Rent: ");
        //set a border on the field, in the same color as the player that bought it.
        street.setBorder(player.getGui_player().getPrimaryColor());

        player.changeBalance(Integer.parseInt(street.getRent()) * -1);

        fieldBought[fieldPosition] = true;
    }

    private void takeTurn(Player player){
        if ((player.getName().charAt(player.getName().length() - 1) == 's')) {
            gui.getUserButtonPressed(("It is player " + player.getName() + "' turn."), "Roll dice");
        } else {
            gui.getUserButtonPressed(("It is player " + player.getName() + "'s turn."), "Roll dice");
        }

        int roll = d1.roll() + d2.roll();

        if(player.getPosition() + roll > fields.length-1){
            //player has passed start
            player.changeBalance(2);
            roll -= 24;
        }

        gui.setDice(d1.getCurrentRoll(), d2.getCurrentRoll());

       player.movePlayer(roll);

        //check if field can be purchased, has already been purchased, or is not purchasable.
        if(fieldCosts[player.getPosition()] > 0 && !fieldBought[player.getPosition()]){
            buyField(player.getPosition(), player);
        }else if(fieldCosts[player.getPosition()] > 0 && fieldBought[player.getPosition()]){
            //field is already bought, player has to pay rent.
            String ownerName = ((GUI_Street) fields[player.getPosition()]).getOwnerName();
            for (int i = 0; i < players.length; i++) {
                if (ownerName.equals(players[i].getName())) {
                    int rent = Integer.parseInt(((GUI_Street) fields[player.getPosition()]).getRent());
                    player.changeBalance(rent * -1);
                    players[i].changeBalance(rent);
                }
            }
        }
        repaint();
    }

    private void repaint(){
        for(int i = 0; i < fields.length; i++){
            if(fieldCosts[i] > 0 && !fieldBought[i]){
                fields[i].setSubText("Cost: " + fieldCosts[i]);
            }else if(fieldCosts[i] > 0 && fieldBought[i]){
                fields[i].setSubText("Rent: " + fieldCosts[i]);
            }
            gui.getFields()[i].removeAllCars();
        }
        for(int i = 0; i < players.length; i++){
            gui.getFields()[players[i].getPosition()].setCar(players[i].getGui_player(), true);
        }
    }

    private void checkGameOver() {
        for (int i = 0; i < players.length; i++) {
            if (players[i].getBalance() < 0) {
                gameFinished = true;
                findWinner();
            }
        }
    }

    private void findWinner(){
        int highestBalance = -1000;
        String winnerName = "";
        for (int i = 0; i < players.length; i++) {
            if (players[i].getBalance() > highestBalance) {
                highestBalance = players[i].getBalance();
                winnerName = players[i].getName();
            }
        }
        gui.showMessage(winnerName + " has won the game!");
    }

    //run game loop here.
    public void start(){
        while(!gameFinished){
            for(int i = 0; i < players.length; i++){
                takeTurn(players[i]);
            }
            checkGameOver();
        }
    }
}