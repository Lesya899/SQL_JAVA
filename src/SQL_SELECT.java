/*Добавьте функции:
- get_type(int id) -  должна возвращать тип котика по переданному id
- get_type_where(String where) - должна печатать на экран все породы котиков, которые подходят под запрос where
 (в качестве переменно where передаются только условия, например: "id < 15" или "type LIKE '%а'")
-get_all_types() - печатает все породы котиков.
 */

import java.sql.*;

public class SQL_SELECT {
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

     //метод возвращает тип котика по переданному id
    static String getType(int id)  throws SQLException {
        //ResultSet  - таблица данных, представляющая набор результатов базы данных, которая создается путем выполнения оператора
        ResultSet rs = statement.executeQuery("SELECT type FROM types WHERE id = " + id); //executeQuery выполняет данный оператор SQL
        return rs.getString("type"); // извлекает значение указанного столбца в текущей строке этого ResultSet объекта как String
    }
    //метод печатает породы кошек согласно  указанного условия
    public static void getTypeWhere (String where) throws SQLException {
        ResultSet r = statement.executeQuery("SELECT type FROM types WHERE " + where);
        while(r.next())
            System.out.println(r.getString("type"));
    }
    //метод печатает все породы котиков
    public static void getAllTypes () throws SQLException {
        ResultSet s = statement.executeQuery("SELECT type FROM types");
        while(s.next()) {
            System.out.println(s.getString("type"));
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        SQL_SELECT.connectDataBase();
        System.out.println("ID 34: " + SQL_SELECT.getType(34));
        SQL_SELECT.getTypeWhere("id > 30");
        SQL_SELECT.getAllTypes();
        statement.close(); //немедленно освобождает базу данных Statement объекта и ресурсы JDBC вместо того, чтобы ждать, пока это произойдет, когда он будет автоматически закрыт
        connection.close(); // отключение от БД
    }
}
