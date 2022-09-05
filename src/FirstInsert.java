
/*Добавьте к вашему проекту функцию insert_type(String type), которая добавляет в базу данных тип (породу) кошки.
С помощью неё добавьте 3 породы: "Абиссинская кошка", "Австралийский мист", "Американская жесткошерстная" */

import java.sql.*;

public class FirstInsert {
    public static final String DB_URL = "jdbc:sqlite:My_cats.db";
    public static final String DB_Driver = "org.sqlite.JDBC";
    public static Connection connection;
    public static Statement statement;

    static {
        try {
            //устанавливаем соединение с СУБД. По переданному адресу, JDBC сама определит тип и местоположение нашей СУБД
            // и вернёт Connection, который мы можем использовать для связи с БД
            connection = DriverManager.getConnection(DB_URL);
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }
    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }
    //метод добавляет в таблицу тип кошки
    static void insertType(String type) {
        try {
            statement.execute("INSERT INTO 'types' ('type') VALUES ('" + type + "')");
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException{
        //проверяем наличие JDBC драйвера для работы с БД
        Class.forName(DB_Driver);
        //добавляем в таблицу тип
        FirstInsert.insertType("Абиссинская кошка");
        FirstInsert.insertType("Австралийский мист");
        FirstInsert.insertType("Американская жесткошерстная");
    }
}

