package ru.geekbrains.lesson3;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

class InvalidFormatException extends Exception {
    public InvalidFormatException(String message) {
        super(message);
    }
}

class InputFormatException extends Exception {
    public InputFormatException(String message) {
        super(message);
    }
}

public class Task0 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в формате: Фамилия Имя Отчество датарождения номертелефона пол");
        String input = scanner.nextLine().trim();
        String[] data = input.split("\\s+");
        if (data.length != 6) {
            System.err.println("Ошибка: неверное количество данных");
            System.exit(1);
        }
        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse(data[3], java.time.format.DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } catch (DateTimeParseException e) {
            System.err.println("Ошибка: неверный формат даты рождения");
            System.exit(1);
            return;
        }
        long phoneNumber;
        try {
            phoneNumber = Long.parseLong(data[4]);
        } catch (NumberFormatException e) {
            System.err.println("Ошибка: неверный формат номера телефона");
            System.exit(1);
            return;
        }
        char gender;
        if (data[5].length() == 1) {
            gender = data[5].charAt(0);
            if (gender != 'f' && gender != 'm') {
                System.err.println("Ошибка: неверный формат пола");
                System.exit(1);
                return;
            }
        } else {
            System.err.println("Ошибка: неверный формат пола");
            System.exit(1);
            return;
        }
        String fileName = lastName + ".txt";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(lastName + firstName + middleName + birthDate.toString() + " " + phoneNumber + gender + "\n");
            System.out.println("Данные успешно записаны в файл " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка: не удалось записать данные в файл " + fileName);
            e.printStackTrace();
        }
    }
}

