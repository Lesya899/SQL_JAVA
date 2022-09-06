
/* Добавьте к вашему проекту функцию insert_cat(String name, String type, int age, Double weight), которая добавляет в базу данных кошку с переданными параметрами.
Обратите внимание, что в качестве параметра type приходит сам тип кошки, а не его id. Соответственно, если такой тип уже есть в БД, то вам необходимо достать его id и добавить его в соответствующее поле.
Если же такого типа ещё нет в нашей БД, то добавить этот тип в таблицу types, получить его id и только потом добавить нашего котика с новым id.
Добавьте 3 каких-нибудь котиков в вашу БД. Хотя бы один из них должен иметь тип, которого ещё нет в вашей БД. */


import java.sql.*;

public class InsertSecondTable {
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

    //метод добавляет в таблицу тип кошки
    static void insertType(String type) throws SQLException {
        statement.execute("INSERT INTO types ('type') VALUES ('" + type + "')");
    }
    static void insertCat(String name, String type, int age, Double weight) throws SQLException {
        int id;
        if (!getType(type).isBeforeFirst()) { //если типа нет
            insertType(type); //то добавляем тип
        }
        id = getType(type).getInt("id");
        statement.execute("INSERT INTO cats ('name', 'type_id', 'age', 'weight') VALUES ('" + name + "', " + id + ", " + age + ", " + weight + ")");
    }
    //ResultSet предназначен для работы с таблицей данных, полученной в результате обработки запроса
    static ResultSet getType(String type) throws SQLException {
        return statement.executeQuery("SELECT * FROM types WHERE type = '" + type + "'");
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        InsertSecondTable.connectDataBase();
        InsertSecondTable.insertCat("Милка","Лесной пушистик", 1, 2.5);
        InsertSecondTable.insertCat("Тутик","Пестрый пушок", 2, 3.5);
        statement.close(); //немедленно освобождает базу данных Statement объекта и ресурсы JDBC вместо того, чтобы ждать, пока это произойдет, когда он будет автоматически закрыт
        connection.close(); // отключение от БД
    }
}
