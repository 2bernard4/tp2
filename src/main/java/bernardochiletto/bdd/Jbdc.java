package bernardochiletto.bdd;

import bernardochiletto.models.Player;
import bernardochiletto.models.Word;

import java.sql.*;
import java.util.Collections;

public class Jbdc {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/tp2";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";


    public Word bringWord(){
        Connection conn = null;
        Statement stmt = null;
        Word word = null;
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT word, length FROM words ORDER BY RAND() LIMIT 1;";
            stmt.executeQuery(sql);
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                word = new Word(rs.getString("word"), rs.getInt("length"));
            }

            //STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try

        return word;
    }

    public void insertWinnerBdd(Player player, Word word){
        Connection conn = null;
        Statement stmt = null;
        java.util.Date dt = new java.util.Date();

        java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("yyyy-MM-dd");

        String currentTime = sdf.format(dt);
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query

            stmt = conn.prepareStatement("INSERT INTO winners(nameWinner, guessedWord, dateWinner) "
                    + "VALUES(?,?,?)");

            // Insert first record
            ((PreparedStatement) stmt).setString(1, player.getPlayerName());
            ((PreparedStatement) stmt).setString(2, word.getWord());
            ((PreparedStatement) stmt).setString(3, currentTime);
            ((PreparedStatement) stmt).executeUpdate();


        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){
            }// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
    }
}
