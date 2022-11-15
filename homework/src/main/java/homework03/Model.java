package homework03;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Тут содержатся методы для проверки количества введенных данных(dataValidation), выделения параметров(parsingString),
 * сохранения данных (savingData), классы икслючений.
 * В качестве необходимого формата номера телефона выбрано 11-значное число.
 */
public class Model {
    public int dataValidation(String str) {
        String[] strArr = str.split(" ");
        if (strArr.length < 6) {
            System.out.println("Введено недостаточное количество данных");
            return -2;
        } else if (strArr.length > 6) {
            System.out.println("Введено избыточное количество данных");
            return -1;
        } else {
            System.out.println("Спасибо, проводится проверка данных... ");
            return 1;
        }
    }

    public String[] parsingString(String string) {
        String[] outStr = new String[6];
        StringBuilder str = new StringBuilder(string);
        str.append(" ");
        str.insert(0, " ");
        Pattern p1 = Pattern.compile("\\W\\d{11}\\W");
        Matcher m1 = p1.matcher(str);
        try {
            if (m1.find()) {
                outStr[4] = "<" + m1.group().replace(" ", "") + ">";
            } else {
                throw new IncorrectPhoneException();
            }
        } catch (IncorrectPhoneException ex) {
            System.out.println(ex.getMessage());
        }
        Pattern p2 = Pattern.compile("\\W[f|m]{1}\\W");
        Matcher m2 = p2.matcher(str);
        try {
            if (m2.find()) {
                outStr[5] = "<" + m2.group().replace(" ", "") + ">";
            } else {
                throw new IncorrectGenderException();
            }
        } catch (IncorrectGenderException ex) {
            System.out.println(ex.getMessage());
        }
        Pattern p3 = Pattern.compile("\\W\\d{2}[.]\\d{2}[.]\\d{4}\\W");
        Matcher m3 = p3.matcher(str);
        try {
            if (m3.find()) {
                outStr[3] = "<" + m3.group().replace(" ", "") + ">";
            } else {
                throw new IncorrectBirthdayException();
            }
        } catch (IncorrectBirthdayException ex) {
            System.out.println(ex.getMessage());
        }
        Pattern p4 = Pattern.compile("[a-zA-Z]{2,}[-]*[']*[a-zA-Z]*");
        Matcher m4 = p4.matcher(str);
        int i = 0;
        try {
            while (m4.find()) {
                outStr[i] = "<" + m4.group().replace(" ", "") + ">";
                i++;
            }
            if (i <= 2 || i >= 4) {
                throw new IncorrectNameException();
            }
        } catch (IncorrectNameException ex) {
            System.out.println(ex.getMessage());
        }
        return outStr;
    }

    public void savingData(String[] strings, Path path) {
        Path newPath = Path.of(path + "\\" + strings[0].replace("<", "").replace(">", "") + ".txt");
        try {
            for (String s : strings) {
                if (!s.isEmpty()) {
                    continue;
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("Перезапустите программу");
            return;
        }
        try (FileWriter writer = new FileWriter(newPath.toString(), true)) {
            if (!Files.exists(newPath)) {
                Files.createFile(newPath);
            }
            writer.append(Arrays.toString(strings));
            writer.append("\n");
            System.out.println("Данные успешно сохранены.");
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public static class IncorrectGenderException extends RuntimeException {
        public IncorrectGenderException() {
            super("Введено некорректное значение пола");
        }
    }

    public static class IncorrectPhoneException extends RuntimeException {
        public IncorrectPhoneException() {
            super("Введен некорректный номер телефона");
        }
    }

    public static class IncorrectBirthdayException extends RuntimeException {
        public IncorrectBirthdayException() {
            super("Некорректный формат даты рождения, необходим ввод в формате 'дд.мм.гггг'");
        }
    }

    public static class IncorrectNameException extends RuntimeException {
        public IncorrectNameException() {
            super("Необходимо указать Фамилию Имя Отчество");
        }
    }

}
