package model;

import gui_main.GUI;

public class ChanceCards {
    GUI gui;
    public ChanceCards(GUI gui){
        this.gui = gui;
    }

    public void drawCard(Player player){
        int roll = (int) (Math.random() * 12 + 1);
        switch (roll){
            case 1:moveToStart(player);
            break;
            case 2: moveOneField(player);
            break;
            case 3: moveToOrange(player);
            break;
            case 4: pay2(player);
            break;
            case 5: moveToGreen(player);
            break;
            case 6: moveToLightBlue(player);
            break;
            case 7: giveJailCard(player);
            break;
            case 8: moveToBoardwalk(player);
            break;
            case 9: receive1(player);
            break;
            case 10: moveToPink(player);
            break;
            case 11: receive2(player);
            break;
            case 12: moveToSkatePark(player);
        }
    }

    private void moveToStart(Player player){
        player.setPosition(0);
        player.changeBalance(2);
        gui.displayChanceCard("Move to start and receive 2M!");
    }

    private void moveOneField(Player player){
        player.movePlayer(1);
        gui.displayChanceCard("Move one field forward.");
    }

    private void moveToOrange(Player player){
        player.setPosition(11);
        gui.displayChanceCard("Move to the swimming pool.");
    }

    private void pay2(Player player){
        player.changeBalance(-2);
        gui.displayChanceCard("You ate too much candy. Pay 2M.");
    }

    private void moveToGreen(Player player){
        player.setPosition(19);
        gui.displayChanceCard("Move to the bowling alley.");
    }

    private void moveToLightBlue(Player player){
        player.setPosition(4);
        gui.displayChanceCard("Move to the sweet shop!");
    }

    private void giveJailCard(Player player){
        player.setHasJailCard(true);
        gui.displayChanceCard("Receive Get-Out-Of-Jail-free card! Next time you go to jail, dont pay the cost.");
    }

    private void moveToBoardwalk(Player player){
        player.setPosition(23);
        gui.displayChanceCard("Move to the boardwalk.");
    }

    private void receive1(Player player){
        player.changeBalance(1);
        gui.displayChanceCard("It's your birthday! Receive 1M.");
    }

    private void moveToPink(Player player){
        player.setPosition(7);
        gui.displayChanceCard("Move to the museum.");
    }

    private void receive2(Player player){
        player.changeBalance(2);
        gui.displayChanceCard("You did all of your homework, good job! Receive 2M.");
    }

    private void moveToSkatePark(Player player){
        player.setPosition(10);
        gui.displayChanceCard("Move to the skate park.");
    }

}
