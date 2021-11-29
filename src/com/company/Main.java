package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    private static File bookInfo = new File("BookInfo.txt");
    private static Scanner input = new Scanner(System.in);
    private static String[] bookInformation = new String[4];

    public static void main(String[] args) {

        CreateFile();
        while (true) {
            MainMenu();
            System.out.println("Do you want to return to the main menu!");
            String endDecision = input.next();
            if (endDecision.equalsIgnoreCase("no") || endDecision.equalsIgnoreCase("n")) {
                break;
            }
        }
    }

    public static void MainMenu() {
        try {
            System.out.println("MAIN MENU: What do you want to do? Type 1,2 or 3!");
            System.out.println(" - Add Book(s) to Library " + "\n" + " - View Library " + "\n" + " - Empty Library " + "\n" + " - Search for a Book " + "\n" + " - Remove a Specific Book ");
            int menuChoice = input.nextInt();

            if (menuChoice == 1) {
                AddBookInfo();
            } else if (menuChoice == 2) {
                System.out.println("These are the books in your library");
                CreateFile();
                ReadFile();
            } else if (menuChoice == 3) {
                DeleteFile();
            } else if (menuChoice == 4) {
                ViewBookInfo();
            } else if (menuChoice == 5) {
                DeleteBook();
            } else {
                System.out.println("Invalid menu choice!");
            }
        }
        catch (Exception e) {
            System.out.println("An error has occurred! "+e);
            input.next();
        }
    }

    public static void CreateFile() {
        try {
            if (bookInfo.createNewFile()) {
                System.out.println("File created: " + bookInfo.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void WriteToFile() {
        try {
            FileWriter myWriter = new FileWriter(bookInfo.getName(), true); //True means append to file contents, False means overwrite
            System.out.println("This is the contents of the file:");

            myWriter.write(Arrays.toString(bookInformation)+"\n"); // Overwrites everything in the file
            myWriter.close();
            //bookInformation = null;
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void  AddBookInfo() {
        System.out.println("How many books do you want to store the information of?");
        int numOfBooks = input.nextInt();
        for (int i = 0; i < numOfBooks; i++) {
            System.out.println("Please type in your books title, ISBN, Author and Genre!(Make sure to click enter after each category!)");
            String usersBookTitle = input.next();
            bookInformation[0] = "Title: " + usersBookTitle;
            int usersBookISBN = input.nextInt();
            bookInformation[1] = "ISBN: " + (Integer.toString(usersBookISBN));
            String usersBookAuthor = input.next();
            bookInformation[2] = "Author: " + usersBookAuthor;
            String usersBookGenre = input.next();
            bookInformation[3] = "Genre: " + usersBookGenre;
            WriteToFile();

        }
    }
        public static void ReadFile() {
            try {
                Scanner myReader = new Scanner(bookInfo);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);

                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        public static void ViewBookInfo() {
            System.out.println("Enter the ISBN of the book you want the information of?");
            String requestedBook = input.next();
            try {
                Scanner myReader = new Scanner(bookInfo);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if (data.contains(requestedBook)) {
                        System.out.println(data);
                    }

                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("Book not found!");
                e.printStackTrace();
            }
        }
        public static void DeleteBook() {
            System.out.println("Enter the ISBN of the book you want to delete!");
            String requestedDeletedBook = input.next();
            try {
                Scanner myReader = new Scanner(bookInfo);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    if(data.contains(requestedDeletedBook)) {
                        //delete file here
                    }
                }
            } catch (Exception e) {
                System.out.println("Book not found!");
                e.printStackTrace();
            }
        }
        public static void DeleteFile() {
            if (bookInfo.delete()) {
                System.out.println("Deleted the file: " + bookInfo.getName());
            } else {
                System.out.println("Library is already empty!");
            }
        }
    }

