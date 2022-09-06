/* Добавьте функции:
-get_cat(int id) -  должна возвращать котика по переданному id;
-get_cat_where(String where) - должна печатать на экран всех котиков, которые подходят под запрос where
 (в качестве переменно where передаются только условия, например: "id < 15" или "name LIKE '%а'");
-get_all_cats() - печатает всех котиков. */

import java.sql.*;

public class SelectCat {
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

    //метод возвращает  котика по переданному id
    static String getCat(int id)  throws SQLException {
        //ResultSet  - таблица данных, представляющая набор результатов базы данных, которая создается путем выполнения запроса SQL
        ResultSet rs = statement.executeQuery("SELECT name FROM cats WHERE id = " + id); //executeQuery выполняет данный оператор SQL
        return rs.getString("name"); // извлекает значение указанного столбца в текущей строке этого ResultSet объекта как String
    }

    //метод печатает котиков согласно  указанного условия
    public static void getCatWhere (String where) throws SQLException {
        ResultSet r = statement.executeQuery("SELECT * FROM cats WHERE " + where);
        while(r.next())
            System.out.println(r.getInt("id") + ": " + r.getString("name"));
    }
    //метод печатает всех котиков
    public static void getAllCats () throws SQLException {
        ResultSet s = statement.executeQuery("SELECT * FROM cats");
        while(s.next()) {
            int id = s.getInt("id");
            String name = s.getString("name");
            int type_id = s.getInt("type_id");
            int age = s.getInt("age");
            double weight = s.getDouble("weight");
            System.out.println("id: " + id + " name: " + name + " type_id: " + type_id + " age: " + age + " weight: " + weight);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        SelectCat.connectDataBase();
        System.out.println("id 80: " + SelectCat.getCat(80));
        SelectCat.getCatWhere("id > 150 AND id < 160");
        SelectCat.getAllCats();
        statement.close(); //немедленно освобождает базу данных Statement объекта и ресурсы JDBC вместо того, чтобы ждать, пока это произойдет, когда он будет автоматически закрыт
        connection.close(); // отключение от БД
    }
}
