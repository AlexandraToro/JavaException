package homework03;

import java.nio.file.Path;

public class Presenter {
    private View view;
    private Model model;

    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void execution() {
        String str = view.getValue("Введите следующие данные в произвольном порядке,\n" +
                " разделенные пробелом:Фамилия Имя Отчество Дату рождения(формат дд.мм.гггг) Номер телефона(11 цифр) пол(f или m)\n");
        System.out.println(str);
        int flag = model.dataValidation(str);
        if (flag < 0) {
            return;
        }
        String[] newstr = model.parsingString(str);
//        String path = view.getValue("Введите путь к директории для сохранения данных: ");
//        Path newPath = Path.of(path);
        Path newPath = Path.of("C:\\Users\\Otomi\\Desktop\\Learning\\Programming\\hw\\JavaException\\homework\\src\\main\\java\\homework03\\Files");
        model.savingData(newstr, newPath);
    }
}
