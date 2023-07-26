public class Учитель {
private int id;
private String имя;
private int возраст;
private String предмет;
public Учитель(int id, String имя, int возраст, String предмет) {
    this.id = id;
    this.имя = имя;
    this.возраст = возраст;
    this.предмет = предмет;
}

public int getId() {
    return id;
}

public String getИмя() {
    return имя;
}

public void setИмя(String имя) {
    this.имя = имя;
}

public int getВозраст() {
    return возраст;
}

public void setВозраст(int возраст) {
    this.возраст = возраст;
}

public String getПредмет() {
    return предмет;
}

public void setПредмет(String предмет) {
    this.предмет = предмет;
}

import java.util.ArrayList;
import java.util.List;

public class УчительСервис {
private List<Учитель> учителя = new ArrayList<>();
private int id = 0;
public Учитель создатьУчителя(String имя, int возраст, String предмет) {
    Учитель учитель = new Учитель(id++, имя, возраст, предмет);
    учителя.add(учитель);
    return учитель;
}

public Учитель найтиУчителя(int id) {
    for (Учитель учитель : учителя) {
        if (учитель.getId() == id) {
            return учитель;
        }
    }
    return null;
}

public List<Учитель> получитьВсехУчителей() {
    return учителя;
}

public void редактироватьУчителя(Учитель учитель, String имя, int возраст, String предмет) {
    учитель.setИмя(имя);
    учитель.setВозраст(возраст);
    учитель.setПредмет(предмет);
}

import java.util.List;
import java.util.Scanner;

public class УчительВью {
private УчительСервис учительСервис;
private Scanner scanner;
public УчительВью(УчительСервис учительСервис, Scanner scanner) {
    this.учительСервис = учительСервис;
    this.scanner = scanner;
}

public void показатьСписокУчителей() {
    List<Учитель> учителя = учительСервис.получитьВсехУчителей();
    for (Учитель учитель : учителя) {
        System.out.println("ID: " + учитель.getId() + ", Имя: " + учитель.getИмя() + ", Возраст: " + учитель.getВозраст() + ", Предмет: " + учитель.getПредмет());
    }
}

public void создатьУчителя() {
    System.out.println("Введите имя учителя:");
    String имя = scanner.nextLine();
    System.out.println("Введите возраст учителя:");
    int возраст = Integer.parseInt(scanner.nextLine());
    System.out.println("Введите предмет учителя:");
    String предмет = scanner.nextLine();
    Учитель учитель = учительСервис.создатьУчителя(имя, возраст, предмет);
    System.out.println("Учитель создан. ID: " + учитель.getId());
}

public void редактироватьУчителя() {
    System.out.println("Введите ID учителя, которого вы хотите отредактировать:");
    int id = Integer.parseInt(scanner.nextLine());
    Учитель учитель = учительСервис.найтиУчителя(id);
    if (учитель == null) {
        System.out.println("Учитель с ID " + id + " не найден.");
        return;
    }
    System.out.println("Введите новое имя учителя:");
    String имя = scanner.nextLine();
    System.out.println("Введите новый возраст учителя:");
    int возраст = Integer.parseInt(scanner.nextLine());
    System.out.println("Введите новый предмет учителя:");
    String предмет = scanner.nextLine();
    учительСервис.редактироватьУчителя(учитель, имя, возраст, предмет);
    System.out.println("Учитель с ID " + id + " отредактирован.");
}

import java.util.Scanner;

public class УчительКонтроллер {
private УчительСервис учительСервис;
private УчительВью учительВью;
private Scanner scanner;
public УчительКонтроллер(УчительСервис учительСервис, УчительВью учительВью, Scanner scanner) {
    this.учительСервис = учительСервис;
    this.учительВью = учительВью;
    this.scanner = scanner;
}

public void начать() {
    while (true) {
        System.out.println("Выберите действие:");
        System.out.println("1. Показать список учителей");
        System.out.println("2. Создать учителя");
        System.out.println("3. Редактировать учителя");
        System.out.println("4. Выйти");
        int выбор = Integer.parseInt(scanner.nextLine());
        switch (выбор) {
            case 1:
                учительВью.показатьСписокУчителей();
                break;
            case 2:
                учительВью.создатьУчителя();
                break;
            case 3:
                учительВью.редактироватьУчителя();
                break;
            case 4:
                System.out.println("До свидания!");
                return;
            default:
                System.out.println("Выберите действие из списка.");
        }
    }
}
