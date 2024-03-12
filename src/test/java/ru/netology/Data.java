package ru.netology;


import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class Data {
    private Data() {

    }

    public static String GenerateDate(int addDays) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String GeneraCity(String locale) {
        var cities = new String[]{"Москва", "Киров", "Рязань", "Иваново", "Южно-Сахалинск", "Курган"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String GenerateName(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String GeneratePhone(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private Registration() {

        }


        public static UserInfo generateUser(String locale) {
            return new UserInfo(GeneraCity(locale), GenerateName(locale), GeneratePhone(locale));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;


    }
}