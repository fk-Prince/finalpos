package Main;

import Exceptions.InvalidInputException;
import User.*;

import java.util.Scanner;

public class Login {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            try {
                System.out.println("-------------------------------");
                System.out.println("[1]Login\n[2]Signup\n[3]Exit");
                System.out.print("Choice: ");
                int choice = Integer.parseInt(sc.nextLine());
                System.out.println("-------------------------------");
                switch (choice) {
                    case 1 -> {
                        User user = getUserInput();
                        boolean isUserExist = UserRepository.isUserExist(user);
                        if (isUserExist) {
                            System.out.println("-----Logged in-----");
                            PointOfSale pos = new PointOfSale();
                            pos.run();
                        } else {
                            System.out.println("-----Incorrect Password-----");
                        }
                    }
                    case 2 -> {
                        User user = getUserInput();
                        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
                            throw new InvalidInputException("-----Username or Password is empty.-----");
                        }
                        boolean usernameExist = UserRepository.isUsernameExist(user.getUsername());
                        if (usernameExist) {
                            System.out.println("-----Try again different username-----");
                        } else {
                            User userCreated = new User(user.getUsername(), user.getPassword());
                            UserRepository.addUser(userCreated);
                            System.out.println("-----Successfully Created-----");
                        }
                    }
                    case 3 -> System.exit(0);
                    default -> throw new InvalidInputException("Invalid Choice");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Invalid Input");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static User getUserInput() {
        System.out.print("Enter Username: ");
        String name = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        return new User(name, password);
    }

}