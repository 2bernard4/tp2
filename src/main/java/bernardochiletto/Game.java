package bernardochiletto;

import bernardochiletto.bdd.Jbdc;
import bernardochiletto.models.Player;
import bernardochiletto.models.Word;
import org.apache.commons.lang3.StringUtils;

public class Game {
    private Jbdc jbdc = new Jbdc();
    private Word word;
    private boolean disponible = false;

    public Game() {
        this.word = jbdc.bringWord();
    }

    public synchronized void play(Player player){ //todo hacerlo sync
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        disponible = true;


        System.out.println("\nThe word has " + word.getLenght() + " letters left.");

        boolean aux = true;

        while (aux && player.getWon()){
            System.out.println("\n---------------------------------------------");
            System.out.println("\n" + player.getPlayerName() + " guess a letter");
            //aca el jugador deberia mandar un char random

            aux = guessWord(player, player.randomLetter());

            if (this.word.getLenght() == 0){
                System.out.println("Congratulations " + player.getPlayerName() + ", you guessed the word it was " + word.getWord());
                //aca lo cargaria a la bdd al ganador
                jbdc.insertWinnerBdd(player, word);
                player.setWon(false);
            }
        }
        if (player.getLives() == 0){
            System.out.println("\n" + player.getPlayerName() + " YOU DIED" + " the word was " + word.getWord());
        }
        disponible = false;
        notifyAll();
    }

    public boolean guessWord(Player player, char letter){ //preguntar aca si es mejor que el while lo haga la funcion principal o hacerlo aca?
        System.out.println(player.getPlayerName() + " said the letter: " + letter);
        if (0 <= word.getWord().indexOf(letter)){
            word.setLenght(word.getLenght()-StringUtils.countMatches(word.getWord(), letter)); //aca le resto la cantidad de veces que esa letra este

            System.out.println("\nYou have guessed a letter, " + word.getLenght() + " letters remaining");
            player.deleteLetter(letter);
            return true;
        }else {
            player.setLives(player.getLives() - 1);
            System.out.println("\nThe letter doesn't match, you have " + player.getLives() + " lives left.");
            player.deleteLetter(letter);
        }
        return false;
    }

    public Word getWord() {
        return word;
    }
}




