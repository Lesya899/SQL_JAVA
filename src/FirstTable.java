
/* Напишите код, который создаст базу данных My_cats.db, в которую добавьте таблицу types.
   Таблица types должна содержать всего два столбца: id и type.
   При повторном запуске вашей программы таблица не должна пересоздаваться. */


import java.sql.*;


public class FirstTable {
    public static final String DB_URL = "jdbc:sqlite:My_cats.db";
    public static final String DB_Driver = "org.sqlite.JDBC";
    public static Connection connection;
    public static Statement statement;


    static {
        try {
            //устанавливаем соединение с СУБД. По переданному адресу, JDBC сама определит тип и местоположение нашей СУБД
            // и вернёт Connection, который мы можем использовать для связи с БД
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException{
        Class.forName(DB_Driver); //проверяем наличие JDBC драйвера для работы с БД

        statement.executeUpdate("CREATE TABLE 'types' (" +
                        "'id' INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE," +
                        "'type' VARCHAR(100) NOT NULL)");

        statement.close();
        connection.close(); // отключение от БД

    }
}

