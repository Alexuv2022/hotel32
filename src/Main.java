/*
 * Программа для автоматизации работы гостинницы
 * - Поиск свободных комнат /getFreeRooms
 * - Поиск свободных комнат с условиями /getFreeRooms 1000-2000 2 bar conditioner
 * - Бронирование комнаты /reserve 32
 * - Выселение из комнаты /checkout 32
 * - Справка по работе с программой /help
 * -
 */


import java.util.Scanner;
import java.util.SortedMap;

public class Main {
    public static void main(String[] args) {
        Room[] rooms = {
                new Room(11, 2, 2000, true, false),
                new Room(12, 2, 5000, true, true),
                new Room(13, 1, 1500, true, false),
                new Room(21, 1, 900, false, false),
                new Room(22, 3, 3000, true, false),
                new Room(23, 4, 8000, true, true),
                new Room(31, 4, 3000, false, false),
                new Room(32, 2, 1500, false, false),
                new Room(33, 3, 6000, true, true),
        };
        Scanner scanner = new Scanner(System.in);
        String command ="";
        while (!command.equals("/exit")) {
            command = scanner.nextLine();
            if (command.equals("/getFreeRooms")) {
                for (int i = 0; i < rooms.length; i++) {
                    if (!rooms[i].isReserved())
                        System.out.print(rooms[i].getNum() + ", ");
                }
                System.out.println();
            } else if (command.contains("/getFreeRooms")) {
                // /getFreeRooms 1000-2000 2 bar conditioner
                String[] splitted = command.split(" ");
                boolean bar = false;
                boolean conditioner = false;
                int min = 0;
                int max = (int) Double.POSITIVE_INFINITY;
                int place = 0;
                for (String param: splitted) {
                    if (param.equals("bar")) bar = true;
                    else if (param.equals("conditioner")) conditioner = true;
                    else if (param.contains("-")) {
                        min = Integer.parseInt(param.split("-")[0]);
                        max = Integer.parseInt(param.split("-")[1]);
                    } else if (param.matches("\\d")) {
                        place = Integer.parseInt(param);
                    }
                }
                boolean roomsFitConditions = false;
                for (Room room: rooms) {
                    int mark = 0;
                    if (room.isReserved()) continue;
                    if (bar && room.isBar()) mark++;
                    if (conditioner && room.isConditioner()) mark++;
                    if (room.getPrice() >= min && room.getPrice() <= max && max != (int) Double.POSITIVE_INFINITY) mark++;
                    if (room.getPlace() == place) mark++;
                    if (splitted.length - 1 == mark) {
                        roomsFitConditions = true;
                        System.out.print(room.getNum() + ", ");
                    }
                }
                if (roomsFitConditions == true) {
                    System.out.println();
                } else {
                    System.out.println("Нет подходящих комнат");
                }
            } else if (command.contains("/reserve")) {
                int num = Integer.parseInt(command.split(" ")[1]);
                for (Room room: rooms) {
                    if (room.getNum() == num) {
                        room.setReserved(true);
                    }
                }
            } else if (command.contains("/checkout")) {
                int num = Integer.parseInt(command.split(" ")[1]);
                for (Room room: rooms) {
                    if (room.getNum() == num) {
                        room.setReserved(false);
                    }
                }
            } else if (command.equals("/help")) {
                System.out.println("Для работы с программой используйте следующие команды:");
                System.out.println("           - Поиск свободных комнат: /getFreeRooms");
                System.out.println("           - Поиск свободных комнат с условиями (часть условий можно опустить): /getFreeRooms 1000-2000 2 bar conditioner");
                System.out.println("           - Бронирование комнаты: /reserve 32");
                System.out.println("           - Выселение из комнаты: /checkout 32");

            }
        }
    }
}
