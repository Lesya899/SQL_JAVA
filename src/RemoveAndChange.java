
/*Добавьте к вашему проекту функции delete_type(int id) и update_type(int id, String new_type) для удаления и изменения типа соответственно */

import java.sql.*;


public class RemoveAndChange {
    public static Connection connection;
    public static Statement  statement;


    //подключаемся к БД
    static void connectDataBase() throws ClassNotFoundException, SQLException {
        //проверяем наличие JDBC драйвера для работы с БД
        Class.forName("org.sqlite.JDBC");
        //устанавливаем соединение с СУБД. По переданному адресу, JDBC сама определит тип и местоположение нашей СУБД
        // и вернёт Connection, который мы можем использовать для связи с БД
        connection = DriverManager.getConnection("jdbc:sqlite:My_cats.db");
        statement = connection.createStatement();
        }

        //метод удаляет тип
        static void deleteType (int id) throws SQLException {
            statement.executeUpdate("DELETE FROM types WHERE id=" + id);
        }

      //изменение типа
    static void updateType(int id, String newType) throws SQLException{
        statement.execute("UPDATE types SET type = '" + newType + "' WHERE id = " + id);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
            RemoveAndChange.connectDataBase();
            RemoveAndChange.deleteType(2);
            RemoveAndChange.updateType(5, "Лесной пушистик");
            statement.close(); //немедленно освобождает базу данных Statement объекта и ресурсы JDBC вместо того, чтобы ждать, пока это произойдет, когда он будет автоматически закрыт
            connection.close(); // отключение от БД
        }
    }




