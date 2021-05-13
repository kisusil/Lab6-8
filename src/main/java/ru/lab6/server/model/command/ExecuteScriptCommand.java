package ru.lab6.server.model.command;

import ru.lab6.common.parameters.ExecuteScriptParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.humanbeing.Car;
import ru.lab6.common.humanbeing.Coordinates;
import ru.lab6.common.humanbeing.Mood;
import ru.lab6.common.humanbeing.WeaponType;
import ru.lab6.server.controller.Controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.Stack;

public class ExecuteScriptCommand implements Command {
    private final Controller controller;
    private final Stack <String> stack;

    public ExecuteScriptCommand(Controller controller) {
        this.controller = controller;
        this.stack = new Stack<>();
    }

    @Override
    public String execute(Parameters parameters) {
        if (!(parameters instanceof ExecuteScriptParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        ExecuteScriptParameters executeScriptParameters = (ExecuteScriptParameters) parameters;

        if (!stack.contains(executeScriptParameters.fileName)) {
            stack.push(executeScriptParameters.fileName);
        }
        else {
            return "Обнаружено зацикливание";
        }

        try (Scanner scanner = new Scanner(new InputStreamReader(new FileInputStream(executeScriptParameters.fileName), StandardCharsets.UTF_8))) {
            String result = "";
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                if (line.equals("add_if_max")) {
                   result += doAddIfMax(scanner) + "\n";
                } else if (line.equals("print_ascending")) {
                    String printAscending = controller.printAscending();
                    result += printAscending + "\n";
                } else if (line.contains("count_by_mood")) {
                    result += doCountByMood(line) + "\n";
                } else if (line.contains("filter_greater_than_mood")) {
                    result += doFilterGreaterThanMood(line) + "\n";
                } else if (line.equals("add")) {
                    result += doAdd(scanner) + "\n";
                } else if (line.equals("clear")) {
                    String clear = controller.clear();
                    result += clear + "\n";
                } else if (line.equals("info")){
                    String info = controller.info();
                    result += info + "\n";
                } else if (line.equals("show")) {
                    String show = controller.show();
                    result += show + "\n";
                } else if (line.equals("help")) {
                    String help = controller.help();
                    result += help + "\n";
                } else if (line.contains("update")) {
                    result += doUpdate(line, scanner) + "\n";
                } else if (line.contains("remove_by_id")) {
                    result += doRemoveById(line) + "\n";
                } else if (line.contains("remove_lower")) {
                    result += doRemoveLower(scanner) + "\n";
                } else if (line.contains("execute_script")){
                    if (line.length()<16){
                        throw new ExecuteScriptException("Вы забыли ввести fileName.");
                    }
                    String fileName = line.substring(15);
                    String executeScript = controller.executeScript(fileName);
                    result += executeScript + "\n";
                } else {
                    throw new ExecuteScriptException("Такой команды не существует");
                }
            }
            stack.pop();
            return result;
        } catch (ExecuteScriptException e) {
            stack.pop();
            return "Скрипт содержит ошибки. Выполнение скрипта прервано:\n" + e.getMessage();
        } catch (FileNotFoundException e) {
            stack.pop();
            return "Файл с таким именем не существует:\n" + e.getMessage();
        } catch (Exception e) {
            stack.pop();
            return "Непредвиденная ошибка чтения из файла:\n" + e.getMessage();
        }
    }


    private String readName(Scanner scanner) throws ExecuteScriptException{
        String line = scanner.nextLine();
        String name;

        if (line.isEmpty()) {
            throw new ExecuteScriptException("Это поле не может быть пустым.");
        }

        name = line;
        return name;
    }

    private Boolean readRealHero (Scanner scanner) throws ExecuteScriptException {
        String line = scanner.nextLine();
        if (line.isEmpty()) {
            throw new ExecuteScriptException("Это поле не может быть пустым.");
        }
        if (line.equalsIgnoreCase("true")){
            return true;
        }
        if (line.equalsIgnoreCase("false")) {
            return false;
        }
        throw new ExecuteScriptException("Это поле может принимать значения true или false.");
    }


    private boolean readHasToothpick (Scanner scanner) throws ExecuteScriptException {
        String line = scanner.nextLine();
        if (line.isEmpty()) {
            throw new ExecuteScriptException("Это поле не может быть пустым.");
        }
        if (line.equalsIgnoreCase("true")){
            return true;
        }
        if (line.equalsIgnoreCase("false")) {
            return false;
        }
        throw new ExecuteScriptException("Это поле может принимать значения true или false.");

    }

    private Integer readX (Scanner scanner) throws ExecuteScriptException{
        String line = scanner.nextLine();
        int x;

        if (line.isEmpty()) {
            throw new ExecuteScriptException("Это поле не может быть пустым.");
        }

        try {
            x = Integer.parseInt(line);
        } catch(NumberFormatException e) {
            throw new ExecuteScriptException("Это поле должно быть целым числом.");
        }

        if (x < -475 ){
            throw new ExecuteScriptException("Это поле не может быть < -475.");
        }

        return x;
    }

    private double readY (Scanner scanner) throws ExecuteScriptException{
        String line = scanner.nextLine();
        double y;

        if (line.isEmpty()) {
            throw new ExecuteScriptException("Это поле не может быть пустым.");
        }

        try {
            y = Double.parseDouble(line);
        } catch (NumberFormatException e) {
            throw new ExecuteScriptException("Это поле должно быть дробным числом.");
        }

        if (y < -533) {
            throw new ExecuteScriptException("Это поле не может быть < -533.");
        }
        return y;
    }

    private Float readImpactSpeed (Scanner scanner) throws ExecuteScriptException{
        String line = scanner.nextLine();
        float impactSpeed;
        try {
            impactSpeed = Float.parseFloat(line);
        } catch (NumberFormatException e) {
            throw new ExecuteScriptException("Это поле может принимать дробные или целые числа.");
        }

        return impactSpeed;
    }

    private Long readMinutesOfWaiting (Scanner scanner) throws ExecuteScriptException{
        String line = scanner.nextLine();
        long minutesOfWaiting;

        if (line.isEmpty()) {
            return null;
        }

        try {
            minutesOfWaiting = Long.parseLong(line);
        }
        catch (NumberFormatException e) {
            throw new ExecuteScriptException("Это поле может принимать только целые числа.");
        }

        return minutesOfWaiting;
    }

    private WeaponType readWeaponType (Scanner scanner) throws ExecuteScriptException{
        String line = scanner.nextLine().toUpperCase();
        WeaponType weaponType;

        if (line.isEmpty()) {
            return null;
        }

        try {
            weaponType = WeaponType.valueOf(line);
        } catch(IllegalArgumentException e) {
            throw new ExecuteScriptException("Это поле должно принимать одно из значений: AXE, SHOTGUN, KNIFE, MACHINE_GUN.");
        }

        return weaponType;
    }

    private Mood readMood (Scanner scanner) throws ExecuteScriptException{
        String line = scanner.nextLine().toUpperCase();
        Mood mood;

        if (line.isEmpty()) {
            return null;
        }

        try {
            mood = Mood.valueOf(line);
        } catch(IllegalArgumentException e) {
            throw new ExecuteScriptException("Это поле должно принимать одно из значений: SADNESS, LONGING, CALM, FRENZY.");
        }

        return mood;

    }

    private String readNameCar (Scanner scanner){
        String line = scanner.nextLine();
        String nameCar;

        if (line.isEmpty()) {
            return null;
        }

        nameCar = line;
        return nameCar;
    }

    private String doAdd(Scanner scanner) throws ExecuteScriptException {
        String name = readName(scanner);

        Boolean realHero = readRealHero(scanner);

        boolean hasToothpick = readHasToothpick(scanner);

        float impactSpeed = readImpactSpeed(scanner);

        Long minutesOfWaiting = readMinutesOfWaiting(scanner);

        Integer x = readX(scanner);

        double y = readY(scanner);

        WeaponType weaponType = readWeaponType(scanner);

        Mood mood = readMood(scanner);

        String nameCar = readNameCar(scanner);

        Coordinates coordinates = new Coordinates(x,y);
        Car car = new Car(nameCar);

        return controller.add(name, coordinates, realHero, hasToothpick, impactSpeed, minutesOfWaiting, weaponType, mood, car);
    }

    private String doUpdate(String line, Scanner scanner) throws ExecuteScriptException {
        int id;
        if (line.length()<8){
            throw new ExecuteScriptException("Вы забыли ввести id");
        }

        String StringId = line.substring(7);

        try {
            id = Integer.parseInt(StringId);
        } catch(NumberFormatException e){
            throw new ExecuteScriptException("Вы ввели id, которое не является числом");
        }

        String name = readName(scanner);

        Boolean realHero = readRealHero(scanner);

        boolean hasToothpick = readHasToothpick(scanner);

        float impactSpeed = readImpactSpeed(scanner);

        Long minutesOfWaiting = readMinutesOfWaiting(scanner);

        Integer x = readX(scanner);

        double y = readY(scanner);

        WeaponType weaponType = readWeaponType(scanner);

        Mood mood = readMood(scanner);

        String nameCar = readNameCar(scanner);

        return controller.update(id, name, x, y, realHero, hasToothpick, impactSpeed, minutesOfWaiting, weaponType, mood, nameCar);
    }

    private String doRemoveById(String line) throws ExecuteScriptException {
        int id;
        if (line.length()<14){
            throw new ExecuteScriptException("Вы забыли ввести Id.");
        }

        String StringId = line.substring(13);

        try {
            id = Integer.parseInt(StringId);
        } catch(NumberFormatException e){
            throw new ExecuteScriptException("Id должно быть целым числом.");
        }

        return controller.removeById(id);
    }

    private String doAddIfMax(Scanner scanner) throws ExecuteScriptException {
        String name = readName(scanner);

        Boolean realHero = readRealHero(scanner);

        boolean hasToothpick = readHasToothpick(scanner);

        float impactSpeed = readImpactSpeed(scanner);

        Long minutesOfWaiting = readMinutesOfWaiting(scanner);

        Integer x = readX(scanner);

        double y = readY(scanner);

        WeaponType weaponType = readWeaponType(scanner);

        Mood mood = readMood(scanner);

        String nameCar = readNameCar(scanner);

        Coordinates coordinates = new Coordinates(x,y);
        Car car = new Car(nameCar);

        return controller.addIfMax(name, coordinates, realHero, hasToothpick, impactSpeed, minutesOfWaiting, weaponType, mood, car);
    }

    private String doRemoveLower(Scanner scanner) throws ExecuteScriptException {
        String name = readName(scanner);

        Boolean realHero = readRealHero(scanner);

        boolean hasToothpick = readHasToothpick(scanner);

        float impactSpeed = readImpactSpeed(scanner);

        Long minutesOfWaiting = readMinutesOfWaiting(scanner);

        Integer x = readX(scanner);

        double y = readY(scanner);

        WeaponType weaponType = readWeaponType(scanner);

        Mood mood = readMood(scanner);

        String nameCar = readNameCar(scanner);

        Coordinates coordinates = new Coordinates(x,y);
        Car car = new Car(nameCar);

        return controller.removeLower(name, coordinates, realHero, hasToothpick, impactSpeed, minutesOfWaiting, weaponType, mood, car);
    }

    private String  doFilterGreaterThanMood(String line) throws ExecuteScriptException {
        Mood mood;
        if (line.length()<25){
            throw new ExecuteScriptException("Вы забыли ввести Mood.");
        }

        String StringMood = line.substring(24).toUpperCase();

        try {
            mood = Mood.valueOf(StringMood);
        } catch(IllegalArgumentException e) {
            throw new ExecuteScriptException("Это поле должно принимать одно из значений: SADNESS, LONGING, CALM, FRENZY.");
        }

        return controller.filterGreaterThanMood(mood);
    }

    private String doCountByMood(String line) throws ExecuteScriptException{
        Mood mood;
        if (line.length()<14){
            throw new ExecuteScriptException("Вы забыли ввести Mood.");
        }

        String StringMood = line.substring(13).toUpperCase();

        try {
            mood = Mood.valueOf(StringMood);
        } catch(IllegalArgumentException e) {
            throw new ExecuteScriptException("Это поле должно принимать одно из значений: SADNESS, LONGING, CALM, FRENZY.");
        }

        return controller.countByMood(mood);
    }
}
