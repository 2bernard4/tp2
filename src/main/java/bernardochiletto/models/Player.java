package bernardochiletto.models;

import bernardochiletto.controlador.Game;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Random;

public class Player extends Thread{
    public final static int MAX_LIFES = 10;
    private static boolean WON = true;

    private Game game;
    private String playerName;
    private int lives;
    private Random r;
    private static char[] abc = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public Player(String name, Game game) {
        this.playerName = name;
        this.lives = MAX_LIFES;
        this.game = game;
        this.r = new Random();
    }

    public char randomLetter(){
        return abc[r.nextInt(abc.length)];
    }

    public void deleteLetter(char letter){ //uso esto o verifico que haya usado antes la palabra
        abc = ArrayUtils.removeElement(abc, letter);
    }

    @Override
    public void run() {
        while (this.lives > 0 && WON){
            game.play(this);
        }
        if (this.lives == 0 || !WON ){
            this.interrupt();
        }
    }

    public boolean getWon() {
        return WON;
    }

    public void setWon(boolean aux) {
        this.WON = aux;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }
}
