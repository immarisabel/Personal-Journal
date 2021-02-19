package nl.marisabel.journal;

import java.sql.*;
import java.util.Scanner;
public class DelEntry {

    private static Scanner scanner = new Scanner(System.in);

    private  String newDate =Date.today();
    private  String newEntry;
    private int entryId;

    public void deleteEntry(int entryId) throws ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        Connection connection = null;
        this.entryId = entryId;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:journaxDB.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            PreparedStatement prep = connection.prepareStatement("DELETE FROM journal WHERE entry_id = ?");
            prep.setInt(1, entryId);
            prep.execute();

            System.out.println(">>>>>>>> ENTRY DELETED");

        } catch (
                SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

        }
    }
}