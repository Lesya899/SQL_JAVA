
/* С помощью кода добавьте таблицу cats. Обратите внимание, что столбец type_id должен быть связан со столбцом id из таблицы
 types как внешний ключ. И это тоже надо сделать кодом. При повторном запуске вашей программы таблица не должна пересоздаваться */

import java.sql.*;


public class SecondTable {
    public static Connection connection;
    public static Statement  statement;


    //подключаемся к БД
    static void connectDataBase() throws ClassNotFoundException, SQLException {
        //проверяем наличие JDBC драйвера для работы с БД
        Class.forName("org.sqlite.JDBC");
        //устанавливаем соединение с СУБД. По переданному адресу, JDBC сама определит тип и местоположение нашей СУБД
        // и вернёт Connection, который мы можем использовать для связи с БД
        connection = DriverManager.getConnection("jdbc:sqlite:My_cats.db");
        statement = connection.createStatement(); //создаем объект Statement для отправки операторов SQL в базу данных
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        SecondTable.connectDataBase();
        //создаем таблицу
        //FOREIGN KEY позволяют установить связи между таблицами. Внешний ключ устанавливается для столбцов из зависимой, подчиненной таблицы, и указывает на один из столбцов из главной таблицы
        statement.executeUpdate("CREATE TABLE 'cats' ('id' INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, 'name' VARCHAR(20) NOT NULL, 'type_id' INTEGER NOT NULL," +
                "'age' INTEGER NOT NULL, 'weight' DOUBLE, FOREIGN KEY ('type_id')  REFERENCES 'types' ('id'))");

        statement.close(); //немедленно освобождает базу данных Statement объекта и ресурсы JDBC вместо того, чтобы ждать, пока это произойдет, когда он будет автоматически закрыт
        connection.close(); // отключение от БД
    }
}
