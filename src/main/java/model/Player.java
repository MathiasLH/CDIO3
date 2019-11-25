package model;

import gui_fields.GUI_Chance;
import gui_fields.GUI_Jail;
import gui_fields.GUI_Player;
import gui_main.GUI;

public class Player {
    private String name;
    private GUI_Player gui_player;
    //private int balance;
    private int playerNumber;
    private static int playerIndex = 1;
    private int position;
    private GUI gui;
        private boolean hasJailCard = false;
    public Player( String name, int balance, GUI_Player gui_player, GUI gui){
        this.playerNumber = playerIndex++;
        this.name = name;
        gui_player.setBalance(balance);
        this.gui_player = gui_player;
        this.gui = gui;
    }

    public void movePlayer(int n){
        gui.getFields()[position].setCar(gui_player, false);
        setPosition(position + n);
        gui.getFields()[position].setCar(gui_player, true);
        if(gui.getFields()[position] instanceof GUI_Jail){
            goToJail();
        }else if(gui.getFields()[position] instanceof GUI_Chance){
            //chancecard stuff goes here
        }

    }

    //set users position to passed value.
    private void setPlayerPosition(int pos){
        gui.getFields()[position].setCar(gui_player, false);
        setPosition(pos);
        gui.getFields()[position].setCar(gui_player, true);
    }

    private void goToJail(){
        setPlayerPosition(6);
        if(hasJailCard){
            setHasJailCard(false);
        }else{
            changeBalance(-1);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GUI_Player getGui_player() {
        return gui_player;
    }

    public void setGui_player(GUI_Player gui_player) {
        this.gui_player = gui_player;
    }

    public int getBalance() {
        return gui_player.getBalance();
    }

    public void changeBalance(int diff) {
        gui_player.setBalance(getBalance() + diff);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean hasJailCard() {
        return hasJailCard;
    }

    public void setHasJailCard(boolean hasJailCard) {
        this.hasJailCard = hasJailCard;
    }

}
