//Привет)!

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class DeliveryApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static ArrayList<FragileParcel> fragileParcels = new ArrayList<>();

    public static ArrayList<StandardParcel> standardParcelList = new ArrayList<>();
    public static ArrayList<FragileParcel> fragileParcelList = new ArrayList<>();
    public static ArrayList<PerishableParcel> perishableParcelList = new ArrayList<>();

    public static ParcelBox<StandardParcel> standardParcelBox = new ParcelBox<>(standardParcelList,1000);
    public static ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(fragileParcelList,1000);
    public static ParcelBox<PerishableParcel> perishableParcelBox = new ParcelBox<>(perishableParcelList,1000);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 0:
                    running = false;
                    break;
                case 4:
                    getReportStatus(fragileParcels);
                    break;
                case 5:
                    printParcelBox();
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Отследить посылку");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");

    }

    // реализуйте методы ниже

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        System.out.println("Тип посылки");
        System.out.println("1 — Стандартная");
        System.out.println("2 — Хрупкая");
        System.out.println("3 — Скоропортящаяся");

        int choice = Integer.parseInt(scanner.nextLine());

        System.out.println("Описание посылки");
        String description = scanner.nextLine();

        System.out.println("Вес");
        int weight = Integer.parseInt(scanner.nextLine());

        System.out.println("Адрес доставки");
        String deliveryAddress = scanner.nextLine();

        System.out.println("День месяца");
        int sendDay = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                StandardParcel standardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(standardParcel);
                standardParcelBox.addParcel(standardParcel);
                break;
            case 2:
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(fragileParcel);
                fragileParcels.add(fragileParcel);
                fragileParcelBox.addParcel(fragileParcel);
                break;
            case 3:
                System.out.println("Срок годности в днях");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                PerishableParcel perishableParcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                allParcels.add(perishableParcel);
                perishableParcelBox.addParcel(perishableParcel);
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        for(Parcel parcel : allParcels){
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        int sumCost = 0;
        for(Parcel parcel : allParcels){
            sumCost += parcel.calculateDeliveryCost();
        }
        System.out.println(sumCost);
    }


    private static void getReportStatus(ArrayList<? extends Trackable> listParcels){
        for(Trackable parcel : listParcels){
            System.out.println("Введите населенный пункт");
            String newLocation = scanner.nextLine();

            parcel.reportStatus(newLocation);
        }
    }

    private static void printParcelBox() {
        System.out.println("Выберете коробку");
        System.out.println("1 — Стандартная");
        System.out.println("2 — Хрупкая");
        System.out.println("3 — Скоропортящаяся");


        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                standardParcelBox.getAllParcels();
                break;
            case 2:
                fragileParcelBox.getAllParcels();
                break;
            case 3:
                perishableParcelBox.getAllParcels();
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }

}