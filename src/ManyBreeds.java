
/*Напишите функцию add_all_types(), которая добавит все породы из списка с породами кошек */

import java.sql.*;

public class ManyBreeds {
    public static final String DB_URL = "jdbc:sqlite:My_cats.db";
    public static final String DB_Driver = "org.sqlite.JDBC";
    public static Connection connection;
    public static Statement statement;
    static String[] types = new String[]{"Абиссинская кошка",
            "Австралийский мист",
            "Американская жесткошерстная",
            "Американская короткошерстная",
            "Американский бобтейл",
            "Американский кёрл",
            "Балинезийская кошка",
            "Бенгальская кошка",
            "Бирманская кошка",
            "Бомбейская кошка",
            "Бразильская короткошёрстная",
            "Британская длинношерстная",
            "Британская короткошерстная",
            "Бурманская кошка",
            "Бурмилла кошка",
            "Гавана",
            "Гималайская кошка",
            "Девон-рекс",
            "Донской сфинкс",
            "Европейская короткошерстная",
            "Египетская мау",
            "Канадский сфинкс",
            "Кимрик",
            "Корат",
            "Корниш-рекс",
            "Курильский бобтейл",
            "Лаперм",
            "Манчкин",
            "Мейн-ку́н",
            "Меконгский бобтейл",
            "Мэнкс кошка",
            "Наполеон",
            "Немецкий рекс",
            "Нибелунг",
            "Норвежская лесная кошка",
            "Ориентальная кошка",
            "Оцикет",
            "Персидская кошка",
            "Петерболд",
            "Пиксибоб",
            "Рагамаффин",
            "Русская голубая кошка",
            "Рэгдолл",
            "Саванна",
            "Селкирк-рекс",
            "Сиамская кошка",
            "Сибирская кошка",
            "Сингапурская кошка",
            "Скоттиш-фолд",
            "Сноу-шу",
            "Сомалийская кошка",
            "Тайская кошка",
            "Тойгер",
            "Тонкинская кошка",
            "Турецкая ангорская кошка",
            "Турецкий ван",
            "Украинский левкой",
            "Чаузи",
            "Шартрез",
            "Экзотическая короткошерстная",
            "Японский бобтейл"
    };

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
//метод добавляет все породы кошек из массива
    static void addAllTypes(String[] types) throws SQLException {
        for (String t : types) {
            statement.execute("INSERT INTO types (type) VALUES ('" + t + "')");
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //проверяем наличие JDBC драйвера для работы с БД
        Class.forName(DB_Driver);
        ManyBreeds.addAllTypes(ManyBreeds.types);
    }
}
