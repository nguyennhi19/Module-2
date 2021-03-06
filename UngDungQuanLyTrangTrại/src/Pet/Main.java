package Pet;

import Pet.model.Pet;
import Pet.service.PetService;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PetService petService = new PetService();
        try {
            petService.loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int choose;
        do {
            creatMenu();
            choose = scanner.nextInt();
            switch (choose){
                case 1:
                    print();
                    break;
                case 2:
                    try {
                        addPet();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        edit();
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
                        find();
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
        System.out.println("----- Qu???n l?? trang tr???i Pet -----");
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

    public static void printPet(){
        PetService ps = new PetService();
        ps.print();
        System.out.println("Danh s??ch ??ang c?? " + ps.size());
    }

    public static void addPet() throws Exception{
        Scanner sc = new Scanner(System.in);
        System.out.println("Nh???p th??ng tin:");

        System.out.println("Nh???p t??n th?? c??ng: ");
        String name = sc.nextLine();

        System.out.println("Nh???p tu???i th?? c??ng: ");
        int age = sc.nextInt();
        String a = sc.nextLine();

        System.out.println("Nh???p gi???i t??nh th?? c??ng: ");
        String gender = sc.nextLine();

        String dateOfBirth;
        do {
            System.out.println("Nh???p ng??y th??ng n??m sinh c???a th?? c??ng h???p l???(MM/DD/YYYY ho???c MM-DD-YYYY): ");
            dateOfBirth = sc.nextLine();
        }while (checkInputDateOfBirth(dateOfBirth) == false);

        System.out.println("Nh???p m??u l??ng c???a th?? c??ng: ");
        String color = sc.nextLine();

        System.out.println("Gi???ng M??o c???a th?? c??ng: ");
        String species = sc.nextLine();

        System.out.println("G??a c???a th?? c??ng:");
        int price = sc.nextInt();

        System.out.println("Nh???p s??? l?????ng: ");
        int  quantity = sc.nextInt();
        String abc = sc.nextLine();

        System.out.println("Nh???p tr???ng th??i: ");
        String  status = sc.nextLine();

        Pet pet = new Pet(name, age, gender,dateOfBirth, color, species, price, quantity, status);
        PetService petService = new PetService();
        Pet pet1 = petService.find(name);
        if (pet.equals(pet1)){
            System.out.println("t??n Pet ???? t???n t???i:");
            System.out.println(pet1.toString());
        }else {
            petService.add(pet);
            System.out.println("B???n ???? th??m " + pet.getName() + " v??o danh s??ch th??nh c??ng!");
        }
    }

    public static void find(){
        Scanner scanner = new Scanner(System.in);
        String name;
        System.out.println("Nh???p t??n Pet c???n t??m ki???m(y??u c???u s??? ??i???n tho???i h???p l???):");
        name = scanner.nextLine();

        PetService petService = new PetService();
        Pet pet = petService.find(name);
        if (pet == null){
            System.out.println("Kh??ng t???n t???i");
        }else {
            System.out.println(petService.find(name).toStringInfo());
        }
    }

    public static void delete() throws Exception{
        PetService petService = new PetService();
        Scanner scanner = new Scanner(System.in);
        String name;
        System.out.println("Nh???p t??n Pet c???n x??a(y??u c???u s??? ??i???n tho???i h???p l???):");
        name = scanner.nextLine();

        Pet pet = petService.find(name);
        if (pet == null){
            System.out.println("Kh??ng t??m ???????c th???y t??n Pet");
        }else {
            System.out.println(petService.find(name).toString());
            petService.remove(name);
            System.out.println("???? x??a th??nh c??ng!");
        }
    }

    public static void edit() throws Exception{
        Scanner sc = new Scanner(System.in);
        PetService petService = new PetService();

        String name;
        System.out.println("Nh???p t??n Pet(y??u c???u s??? ??i???n tho???i h???p l???):");
        name = sc.nextLine();

        Pet pet = petService.find(name);

        if (name == null){
            System.out.println("Kh??ng t??m ???????c t??n Pet");
        }else {
            System.out.println("Nh???p th??ng tin m???i:");

            System.out.println("Nh???p tu???i th?? c??ng: ");
            int age = sc.nextInt();
            pet.setAge(age);
            String a = sc.nextLine();

            System.out.println("Nh???p gi???i t??nh th?? c??ng: ");
            String gender = sc.nextLine();
            pet.setGender(gender);

            String dateOfBirth;
            do {
                System.out.println("Nh???p ng??y th??ng n??m sinh c???a th?? c??ng h???p l???(MM/DD/YYYY ho???c MM-DD-YYYY): ");
                dateOfBirth = sc.nextLine();
            }while (checkInputDateOfBirth(dateOfBirth) == false);
            pet.setDateOfBirth(dateOfBirth);

            System.out.println("Nh???p m??u l??ng c???a th?? c??ng: ");
            String color = sc.nextLine();
            pet.setColor(color);

            System.out.println("Gi???ng M??o c???a th?? c??ng: ");
            String species = sc.nextLine();
            pet.setSpecies(species);

            System.out.println("G??a c???a th?? c??ng:");
            int price = sc.nextInt();
            pet.setPrice(price);

            System.out.println("Nh???p s??? l?????ng: ");
            int  quantity = sc.nextInt();
            pet.setQuantity(quantity);
            String abc = sc.nextLine();

            System.out.println("Nh???p tr???ng th??i: ");
            String  status = sc.nextLine();
            pet.setStatus(status);

            System.out.println("C???p nh???t th??nh c??ng!");
            System.out.println(petService.find(name).toString());
            petService.updateFile();
        }
    }

    public static void exit(){
        System.out.println("???? tho??t");
        System.exit(0);
    }

    public static boolean checkInputDateOfBirth(String dateOfBirth){
        String regex = "\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(dateOfBirth);
        return matcher.find();
    }

    public static void readToFile() throws Exception{
        PetService petService = new PetService();
        petService.print();
    }

    public static void writeToFile() throws Exception{
        System.out.println("??ang ti???n h??nh ghi v??o file...");
        PetService petService = new PetService();
        petService.updateFile();
        System.out.println("Ghi v??o file th??nh c??ng");
    }
}
