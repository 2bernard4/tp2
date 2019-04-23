package bernardochiletto;


import bernardochiletto.models.Player;

import static java.lang.Thread.sleep;

public class App
{
    public static void main(String[] args) throws InterruptedException {
        Game game = new Game();
        Player p1 = new Player("Bernardo", game);
        Player p2 = new Player("German", game);
        p1.start();
        p2.start();


        while (p1.isAlive() || p2.isAlive()){
        }

        if (p1.getLives() == 0 && p2.getLives() == 0){
            System.out.println("\nYou both died the word was: " + game.getWord().getWord());
        }
    }
}
