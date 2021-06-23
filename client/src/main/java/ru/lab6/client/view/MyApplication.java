package ru.lab6.client.view;

import ru.lab6.client.controller.Controller;
import ru.lab6.common.humanbeing.Car;
import ru.lab6.common.humanbeing.Coordinates;
import ru.lab6.common.humanbeing.Mood;
import ru.lab6.common.humanbeing.WeaponType;

import java.util.ArrayDeque;
import java.util.Queue;


public class MyApplication implements Application {
    private final IO io;
    private final Controller controller;
    private final Queue<String> history;

    public MyApplication(IO io, Controller controller) {
        this.io = io;
        this.controller = controller;
        this.history = new ArrayDeque<>();
    }

    void changeHistory(String command) {
        if (history.size() == 6) {
            history.poll();
        }
        history.add(command);
    }

    private String readName() {
        while (true) {
            String line = io.readLine();
            String name;

            if (line.isEmpty()) {
                io.println("Это поле не может быть пустым. Введите name еще раз:");
                continue;
            }

            name = line;
            return name;
        }
    }

    private Boolean readRealHero() {
        while (true) {
            String line = io.readLine();
            if (line.isEmpty()) {
                io.println("Это поле не может быть пустым. Пожалуйста, введите realHero еще раз:");
                continue;
            }
            if (line.equalsIgnoreCase("true")) {
                return true;
            }
            if (line.equalsIgnoreCase("false")) {
                return false;
            }
            io.println("Это поле может принимать значения true или false. Пожалуйста, введите realHero еще раз:");
        }
    }


    private boolean readHasToothpick() {
        while (true) {
            String line = io.readLine();
            if (line.isEmpty()) {
                io.println("Это поле не может быть пустым. Пожалуйста, введите hasToothpick еще раз:");
                continue;
            }
            if (line.equalsIgnoreCase("true")) {
                return true;
            }
            if (line.equalsIgnoreCase("false")) {
                return false;
            }
            io.println("Это поле может принимать значения true или false. Пожалуйста, введите hasToothpick еще раз:");
        }
    }

    private Integer readX() {
        while (true) {
            String line = io.readLine();
            int x;

            if (line.isEmpty()) {
                io.println("Это поле не может быть пустым. Пожалуйста, введите X еще раз:");
                continue;
            }

            try {
                x = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                io.println("Это поле должно быть целым числом. Пожалуйста, введите x еще раз:");
                continue;
            }

            if (x < -475) {
                io.println("Это поле не может быть < -475. Пожалуйста, введите x еще раз:");
                continue;
            }
            return x;
        }
    }

    private double readY() {
        while (true) {
            String line = io.readLine();
            double y;

            if (line.isEmpty()) {
                io.println("Это поле не может быть пустым. Пожалуйста, введите Y еще раз:");
                continue;
            }

            try {
                y = Double.parseDouble(line);
            } catch (NumberFormatException e) {
                io.println("Это поле должно быть дробным числом. Пожалуйста, введите Y еще раз:");
                continue;
            }

            if (y < -533) {
                io.println("Это поле не может быть < -533. Пожалуйста, введите Y еще раз:");
                continue;
            }
            return y;
        }
    }

    private Float readImpactSpeed() {
        while (true) {
            String line = io.readLine();
            float impactSpeed;
            try {
                impactSpeed = Float.parseFloat(line);
            } catch (NumberFormatException e) {
                io.println("Это поле может принимать дробные или целые числа. Введите impactSpeed еще раз.");
                continue;
            }

            return impactSpeed;
        }
    }

    private Long readMinutesOfWaiting() {
        while (true) {
            String line = io.readLine();
            long minutesOfWaiting;
            if (line.isEmpty()) {
                return null;
            }
            try {
                minutesOfWaiting = Long.parseLong(line);
            } catch (NumberFormatException e) {
                io.println("Это поле может принимать только целые числа. Введите minutesOfWaiting еще раз:");
                continue;
            }

            return minutesOfWaiting;
        }
    }

    private WeaponType readWeaponType() {
        while (true) {
            String line = io.readLine().toUpperCase();
            WeaponType weaponType;

            if (line.isEmpty()) {
                return null;
            }

            try {
                weaponType = WeaponType.valueOf(line);
            } catch (IllegalArgumentException e) {
                io.println("Это поле должно принимать одно из значений: AXE, SHOTGUN, KNIFE, MACHINE_GUN. Пожалуйста, введите weaponType еще раз:");
                continue;
            }

            return weaponType;
        }
    }

    private Mood readMood() {
        while (true) {
            String line = io.readLine().toUpperCase();
            Mood mood;
            if (line.isEmpty()) {
                return null;
            }

            try {
                mood = Mood.valueOf(line);
            } catch (IllegalArgumentException e) {
                io.println("Это поле должно принимать одно из значений: SADNESS, LONGING, CALM, FRENZY. Пожалуйста, введите mood еще раз:");
                continue;
            }

            return mood;
        }

    }

    private String readNameCar() {
        String line = io.readLine();
        String nameCar;

        if (line.isEmpty()) {
            return null;
        }

        nameCar = line;
        return nameCar;
    }

    @Override
    public void start() {
        while (true) {
            io.println("");
            io.println("Введите команду:");
            String line = io.readLine();
            if (line.equals("add_if_max")) {
                doAddIfMax();
            } else if (line.equals("print_ascending")) {
                String printAscending = controller.printAscending();
                io.println(printAscending);
                changeHistory("print_ascending");
            } else if (line.contains("login")) {
                doLogin(line);
            } else if (line.contains("register")) {
                doRegister(line);
            } else if (line.contains("count_by_mood")) {
                doCountByMood(line);
            } else if (line.contains("filter_greater_than_mood")) {
                doFilterGreaterThanMood(line);
            } else if (line.equals("add")) {
                doAdd();
            } else if (line.equals("clear")) {
                String clear = controller.clear();
                io.println(clear);
                changeHistory("clear");
            } else if (line.equals("info")){
                String info = controller.info();
                io.println(info);
                changeHistory("info");
            } else if (line.equals("show")) {
                String show = controller.show();
                io.println(show);
                changeHistory("show");
            } else if (line.equals("help")) {
                String help = controller.help();
                io.println(help);
                changeHistory("help");
            } else if (line.contains("update")) {
                doUpdate(line);
            } else if (line.contains("remove_by_id")) {
                doRemoveById(line);
            } else if (line.contains("remove_lower")) {
                doRemoveLower();
            } else if (line.equals("history")) {
                doHistory();
            } else if (line.equals("save")) {
                String save = controller.save();
                io.println(save);
                changeHistory("save");
            } else if (line.equals("exit")) {
                io.println("Программа завершена");
                break;
            } else if (line.contains("execute_script")){
                if (line.length()<16){
                    io.println("Вы забыли ввести fileName. Введите execute_script еще раз:");
                    continue;
                }
                String fileName = line.substring(15);
                String executeScript = controller.executeScript(fileName);
                io.println(executeScript);
                changeHistory("execute_script");

            } else {
                io.println("Такой команды не существует");
            }
        }
    }

    private void doLogin(String line) {
        String login;
        String password;

        String[] strings = line.split(" ");

        if (strings.length<3) {
            io.println("Вы забыли ввести login или password");
            return;
        }

        login = strings[1];
        password = strings[2];

        String response = controller.login(login, password);
        io.println(response);
        changeHistory("login");

    }

    private void doRegister(String line) {
        String login;
        String password;

        String[] strings = line.split(" ");

        if (strings.length<3) {
            io.println("Вы забыли ввести login или password");
            return;
        }

        login = strings[1];
        password = strings[2];

        String response = controller.register(login, password);
        io.println(response);
        changeHistory("register");

    }


    private void doHistory() {
        if (history.isEmpty()){
            io.println("История комманд пуста");
            return;
        }

        io.println("");
        for (String command : history) {
            io.println(command);
        }
    }

    private void doCountByMood(String line) {
        Mood mood;
        if (line.length()<14){
            io.println("Вы забыли ввести Mood. Введите count_by_mood еще раз:");
            return;
        }

        String StringMood = line.substring(14).toUpperCase();

        try {
            mood = Mood.valueOf(StringMood);
        } catch(IllegalArgumentException e) {
            io.println("Это поле должно принимать одно из значений: SADNESS, LONGING, CALM, FRENZY. Пожалуйста, введите mood еще раз:");
            return;
        }

        String countByMood = controller.countByMood(mood);
        io.println(countByMood);
        changeHistory("count_by_mood");

    }

    private void doAdd() {
        io.println("Введите name:");
        String name = readName();

        io.println("Введите realHero:");
        Boolean realHero = readRealHero();

        io.println("Введите hasToothPick:");
        boolean hasToothpick = readHasToothpick();

        io.println("Введите impactSpeed:");
        float impactSpeed = readImpactSpeed();

        io.println("Введите minutesOfWaiting:");
        Long minutesOfWaiting = readMinutesOfWaiting();

        io.println("Введите coordinate X:");
        Integer x = readX();

        io.println("Введите coordinate Y:");
        double y = readY();

        io.println("Введите weaponType:");
        WeaponType weaponType = readWeaponType();

        io.println("Введите mood:");
        Mood mood = readMood();

        io.println("Введите nameCar:");
        String nameCar = readNameCar();

        Coordinates coordinates = new Coordinates(x,y);
        Car car = new Car(nameCar);

        String add = controller.add(name, coordinates, realHero, hasToothpick, impactSpeed, minutesOfWaiting, weaponType, mood, car);
        io.println(add);
        changeHistory("add");

    }

    private void doUpdate(String line) {
        int id;
        if (line.length()<8){
            io.println("Вы забыли ввести Id. Введите update еще раз:");
            return;
        }
        String StringId = line.substring(7);

        try {
            id = Integer.parseInt(StringId);

        } catch(NumberFormatException e){
            io.println("Id должно быть целым числом. Введите update еще раз:");
            return;
        }

        io.println("Введите name:");
        String name = readName();

        io.println("Введите realHero:");
        Boolean realHero = readRealHero();

        io.println("Введите hasToothPick:");
        boolean hasToothpick = readHasToothpick();

        io.println("Введите impactSpeed:");
        float impactSpeed = readImpactSpeed();

        io.println("Введите minutesOfWaiting:");
        Long minutesOfWaiting = readMinutesOfWaiting();

        io.println("Введите coordinate X:");
        Integer x = readX();

        io.println("Введите coordinate Y:");
        double y = readY();

        io.println("Введите weaponType:");
        WeaponType weaponType = readWeaponType();

        io.println("Введите mood:");
        Mood mood = readMood();

        io.println("Введите nameCar:");
        String nameCar = readNameCar();

        String update = controller.update(id, name, x, y, realHero, hasToothpick, impactSpeed, minutesOfWaiting, weaponType, mood, nameCar);
        io.println(update);
        changeHistory("update");

    }

    private void doRemoveById(String line) {
        int id;
        if (line.length()<14){
            io.println("Вы забыли ввести Id. Введите remove_by_id еще раз:");
            return;
        }

        String stringId = line.substring(13);

        try {
            id = Integer.parseInt(stringId);
        } catch(NumberFormatException e){
            io.println("Id должно быть целым числом. Введите remove_by_id еще раз:");
            return;
        }

        String removeById = controller.removeById(id);
        io.println(removeById);
        changeHistory("remove_by_id");

    }

    private void doAddIfMax() {
        io.println("Введите name:");
        String name = readName();

        io.println("Введите realHero:");
        Boolean realHero = readRealHero();

        io.println("Введите hasToothPick:");
        boolean hasToothpick = readHasToothpick();

        io.println("Введите impactSpeed:");
        float impactSpeed = readImpactSpeed();

        io.println("Введите minutesOfWaiting:");
        Long minutesOfWaiting = readMinutesOfWaiting();

        io.println("Введите coordinate X:");
        Integer x = readX();

        io.println("Введите coordinate Y:");
        double y = readY();

        io.println("Введите weaponType:");
        WeaponType weaponType = readWeaponType();

        io.println("Введите mood:");
        Mood mood = readMood();

        io.println("Введите nameCar:");
        String nameCar = readNameCar();

        Coordinates coordinates = new Coordinates(x,y);
        Car car = new Car(nameCar);

        String addIfMax = controller.addIfMax(name, coordinates, realHero, hasToothpick, impactSpeed, minutesOfWaiting, weaponType, mood, car);
        io.println(addIfMax);
        changeHistory("add_if_max");

    }

    private void doRemoveLower() {
        io.println("Введите name:");
        String name = readName();

        io.println("Введите realHero:");
        Boolean realHero = readRealHero();

        io.println("Введите hasToothPick:");
        boolean hasToothpick = readHasToothpick();

        io.println("Введите impactSpeed:");
        float impactSpeed = readImpactSpeed();

        io.println("Введите minutesOfWaiting:");
        Long minutesOfWaiting = readMinutesOfWaiting();

        io.println("Введите coordinate X:");
        Integer x = readX();

        io.println("Введите coordinate Y:");
        double y = readY();

        io.println("Введите weaponType:");
        WeaponType weaponType = readWeaponType();

        io.println("Введите mood:");
        Mood mood = readMood();

        io.println("Введите nameCar:");
        String nameCar = readNameCar();

        Coordinates coordinates = new Coordinates(x,y);
        Car car = new Car(nameCar);

        String removeLower = controller.removeLower(name, coordinates, realHero, hasToothpick, impactSpeed, minutesOfWaiting, weaponType, mood, car);
        io.println(removeLower);
        changeHistory("remove_lower");

    }

    private void doFilterGreaterThanMood(String line) {
        Mood mood;
        if (line.length()<25){
            io.println("Вы забыли ввести Mood. Введите filter_greater_than_mood еще раз:");
            return;
        }

        String StringMood = line.substring(25).toUpperCase();

        try {
            mood = Mood.valueOf(StringMood);
        } catch(IllegalArgumentException e) {
            io.println("Это поле должно принимать одно из значений: SADNESS, LONGING, CALM, FRENZY. Пожалуйста, введите mood еще раз:");
            return;
        }

        String filterGreaterThanMood = controller.filterGreaterThanMood(mood);
        io.println(filterGreaterThanMood);
        changeHistory("filter_greater_than_mood");
    }
}
