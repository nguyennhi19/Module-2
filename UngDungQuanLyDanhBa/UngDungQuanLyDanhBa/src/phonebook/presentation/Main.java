package phonebook.presentation;

import phonebook.model.Pet;
import phonebook.service.PetService;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetService phoneBookService = new PetService();
        try {
            phoneBookService.loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int choose;
        do {
            creatMenu();
            choose = scanner.nextInt();
            switch (choose){
                case 1:
                    printPhoneBook();
                    break;
                case 2:
                    try {
                        addPhoneBook();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        editPhoneBook();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
                        delete();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    try {
                        findPhoneBook();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    try {
                        readToFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    try {
                        writeToFile();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 8:
                    exit();
                    break;
            }
        }while (choose != 8);
    }

    public static void creatMenu(){
        System.out.println("----- CH????NG TR??NH QU???N L?? DANH B??? -----");
        System.out.println("Ch???n ch???c n??ng theo s??? (????? ti???p t???c)");
        System.out.println("1. Xem danh s??ch");
        System.out.println("2. Th??m m???i");
        System.out.println("3. C???p nh???t");
        System.out.println("4. X??a");
        System.out.println("5. T??m ki???m");
        System.out.println("6. ?????c t??? file");
        System.out.println("7. Ghi v??o file");
        System.out.println("8. Tho??t");
        System.out.println("------------------------------------------");
    }

    public static void printPhoneBook(){
        PetService pbs = new PetService();
        pbs.printPhoneBook();
        System.out.println("Danh s??ch ??ang c?? " + pbs.size());
    }

    public static void addPhoneBook() throws Exception{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nh???p th??ng tin:");

        String phoneNumber;
        do {
            System.out.println("Nh???p s??? ??i???n tho???i(y??u c???u s??? ??i???n tho???i h???p l???): ");
            phoneNumber = scanner.nextLine();
        }while (checkInputPhoneNumber(phoneNumber) == false);

        System.out.println("Nh??m c???a danh b???: ");
        String groupName = scanner.nextLine();

        System.out.println("Nh???p t??n:");
        String name = scanner.nextLine();

        System.out.println("Nh???p gi???i t??nh:");
        String gender = scanner.nextLine();

        System.out.println("Nh???p ?????a ch???:");
        String address = scanner.nextLine();

        String dateOfBirth;
        do {
            System.out.println("Nh???p ng??y th??ng n??m sinh h???p l???(MM/DD/YYYY ho???c MM-DD-YYYY): ");
            dateOfBirth = scanner.nextLine();
        }while (checkInputDateOfBirth(dateOfBirth) == false);

        String email;
        do {
            System.out.println("Nh???p email h???p l???:");
            email = scanner.nextLine();
        }while (checkInputEmail(email) == false);

        Pet phoneBook = new Pet(phoneNumber,groupName,name,gender,address,dateOfBirth,email);
        PetService phoneBookService = new PetService();
        Pet phoneBook1 = phoneBookService.find(phoneNumber);
        if (phoneBook.equals(phoneBook1)){
            System.out.println("S??? ??i???n tho???i ???? t???n t???i:");
            System.out.println(phoneBook1.toString());
        }else {
            phoneBookService.add(phoneBook);
            System.out.println("B???n ???? th??m " + phoneBook.getName() + " v??o danh b??? th??nh c??ng!");
        }
    }

    public static void findPhoneBook(){
        Scanner scanner = new Scanner(System.in);
        String phoneNumber;
        do {
            System.out.println("Nh???p s??? ??i???n tho???i c???n t??m ki???m(y??u c???u s??? ??i???n tho???i h???p l???):");
            phoneNumber = scanner.nextLine();
        }while (checkInputPhoneNumber(phoneNumber) == false);

        PetService phoneBookService = new PetService();
        Pet phoneBook = phoneBookService.find(phoneNumber);
        if (phoneBook == null){
            System.out.println("Kh??ng t???n t???i");
        }else {
            System.out.println(phoneBookService.find(phoneNumber).toStringInfo());
        }
    }

    public static void delete() throws Exception{
        PetService phoneBookService = new PetService();
        Scanner scanner = new Scanner(System.in);
        String phoneNumber;
        do {
            System.out.println("Nh???p s??? ??i???n tho???i c???n x??a(y??u c???u s??? ??i???n tho???i h???p l???):");
            phoneNumber = scanner.nextLine();
        }while (checkInputPhoneNumber(phoneNumber) == false);

        Pet phoneBook = phoneBookService.find(phoneNumber);
        if (phoneBook == null){
            System.out.println("Kh??ng t??m ???????c danh b??? v???i s??? ??i???n tho???i tr??n");
        }else {
            System.out.println(phoneBookService.find(phoneNumber).toString());
            phoneBookService.remove(phoneNumber);
            System.out.println("???? x??a th??nh c??ng!");
        }
    }

    public static void editPhoneBook() throws Exception{
        Scanner scanner = new Scanner(System.in);
        PetService phoneBookService = new PetService();


        String phoneNumber;
        do {
            System.out.println("Nh???p s??? ??i???n tho???i(y??u c???u s??? ??i???n tho???i h???p l???):");
            phoneNumber = scanner.nextLine();
        }while (checkInputPhoneNumber(phoneNumber) == false);

        Pet phoneBook = phoneBookService.find(phoneNumber);
        if (phoneBook == null){
            System.out.println("Kh??ng t??m ???????c danh b??? v???i s??? ??i???n tho???i tr??n");
        }else {
            System.out.println(phoneBookService.find(phoneNumber));
            System.out.println("Nh??p th??ng tin m???i:");

            System.out.println("Nh??m c???a danh b???");
            String groupName = scanner.nextLine();
            phoneBook.setGroupName(groupName);

            System.out.println("Nh???p t??n:");
            String name = scanner.nextLine();
            phoneBook.setName(name);

            System.out.println("Gi???i t??nh:");
            String gender = scanner.nextLine();
            phoneBook.setGender(gender);

            System.out.println("Nh???p ?????a ch???:");
            String address = scanner.nextLine();
            phoneBook.setAddress(address);

            String dateOfBirth;
            do {
                System.out.println("Nh???p ng??y th??ng n??m sinh h???p l???(MM/DD/YYYY ho???c MM-DD-YYYY):");
                dateOfBirth = scanner.nextLine();
            }while (checkInputDateOfBirth(dateOfBirth) == false);
            phoneBook.setDateOfBirth(dateOfBirth);

            String email;
            do {
                System.out.println("Nh???p email h???p l???:");
                email = scanner.nextLine();
            }while (checkInputEmail(email) == false);
            phoneBook.setEmail(email);

            System.out.println("C???p nh???t th??nh c??ng!");
            System.out.println(phoneBookService.find(phoneNumber).toString());
            phoneBookService.updateFile();
        }
    }

    public static void exit(){
        System.out.println("???? tho??t");
        System.exit(0);
    }

    public static boolean  checkInputEmail(String email){
        String regex = "^[a-zA-Z]+[a-zA-Z0-9]*@{1}+[\\w+mail]|[outlook]+.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

    public static boolean checkInputDateOfBirth(String dateOfBirth){
        String regex = "\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateOfBirth);
        return matcher.find();
    }

    public static boolean checkInputPhoneNumber(String phone){
        String regex = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.find();
    }

    public static void readToFile() throws Exception{
        PetService phoneBookService = new PetService();
        phoneBookService.printPhoneBook();
    }

    public static void writeToFile() throws Exception{
        System.out.println("??ang ti???n h??nh ghi v??o file...");
        PetService phoneBookService = new PetService();
        phoneBookService.updateFile();
        System.out.println("Ghi v??o file th??nh c??ng");
    }
}
