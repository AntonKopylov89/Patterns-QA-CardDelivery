package ru.netology.service.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class DataGenerator {

    private static Faker faker;

    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        String date = LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    public static String generateCity(String locale) {
        faker = new Faker(new Locale(locale));
        var city = new String[]{"Горно-Алтайск", "Гатчина", "Ярославль", "Якутск", "Южно-Сахалинск", "Элиста", "Чита", "Черкесск", "Челябинск", "Чебоксары", "Ханты-Мансийск", "Хабаровск", "Уфа", "Ульяновск", "Улан-Удэ", "Тюмень", "Тула", "Томск", "Тверь", "Тамбов", "Сыктывкар", "Ставрополь", "Смоленск", "Симферополь", "Севастополь", "Саратов", "Саранск", "Санкт-Петербург", "Самара", "Салехард", "Рязань", "Ростов-на-Дону", "Псков", "Петропавловск-Камчатский", "Петрозаводск", "Пермь", "Пенза", "Оренбург", "Орёл", "Омск", "Новосибирск", "Нижний Новгород", "Нарьян-Мар", "Нальчик", "Мурманск", "Москва", "Махачкала", "Майкоп", "Магас", "Магадан", "Липецк", "Кызыл", "Курск", "Курган", "Красноярск", "Краснодар", "Красногорск", "Кострома", "Киров", "Кемерово", "Калуга", "Калининград", "Казань", "Йошкар-Ола", "Иркутск", "Ижевск", "Иваново", "Екатеринбург", "Грозный", "Воронеж", "Вологда", "Волгоград", "Владимир", "Владикавказ", "Владивосток", "Великий Новгород", "Брянск", "Благовещенск", "Биробиджан", "Белгород", "Барнаул", "Астрахань", "Архангельск", "Анадырь", "Абакан"
        };
        return city[new Random().nextInt(city.length)];
    }

    public static String generateName(String locale) {
        faker = new Faker(new Locale(locale));
        String name = faker.name().lastName() + " " + faker.name().firstName();
        return name;
    }

    public static String generatePhone(String locale) {
        faker = new Faker(new Locale(locale));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(locale),
            // generateName(locale), generatePhone(locale)
            faker = new Faker(new Locale(locale));
            var user = new UserInfo(
                    generateCity(locale),
                    generateName(locale),
                    generatePhone(locale)
            );
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
