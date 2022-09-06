
/*Добавьте к вашему проекту функции:
-delete_cat(int id) - удаление котика по id;
-delete_cat(String where) - удаление котика по условию, аналогичному на слайде 7;
-update_cat(int id, Strnig set, String where) - изменение котика заданным образом по заданному критерию. */

import java.sql.*;

public class RemovAndChangCat {
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

    //удаление котика по id
    static void deleteCat(int id) throws SQLException {
        statement.executeUpdate("DELETE FROM cats WHERE id=" + id);
    }

    //метод удаляет  согласно  указанного условия
    public static void delСat (String where) throws SQLException {
        statement.execute("DELETE FROM cats WHERE " + where);
    }

    static void updateCat(int id, String set) throws SQLException{
        statement.execute("UPDATE cats SET " + set + "WHERE id = " + id);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        RemovAndChangCat.connectDataBase();
        RemovAndChangCat.deleteCat(63);
        RemovAndChangCat.delСat("id > 65 AND id < 70");
        RemovAndChangCat.updateCat(47,"name = 'Лика'");
        statement.close(); //немедленно освобождает базу данных Statement объекта и ресурсы JDBC вместо того, чтобы ждать, пока это произойдет, когда он будет автоматически закрыт
        connection.close(); // отключение от БД
    }
}
