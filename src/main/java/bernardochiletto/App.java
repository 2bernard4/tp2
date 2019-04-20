package bernardochiletto;


import bernardochiletto.models.Player;

public class App
{
    public static void main(String[] args) {
        Game game = new Game();
        Player p1 = new Player("Bernardo", game);
        Player p2 = new Player("German", game);
        p1.start();
        p2.start();
    }
}
