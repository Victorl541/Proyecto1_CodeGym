package Cgm.Proyecto;

import java.io.IOException;
import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final String ALPHABET;
    private final Cipher cipher;
    private final FileManager fileManager;
    private final Validator validator;
    private final BruteForce bruteForce;

    public Menu(Cipher cipher, FileManager fileManager, Validator validator, String ALPHABET, BruteForce bruteForce){
        this.ALPHABET = ALPHABET;
        this.cipher = cipher;
        this.fileManager = fileManager;
        this.validator = validator;
        this.bruteForce = bruteForce;
    }


    public void showmenu(){
        boolean exit = true;

        while (exit) {
            System.out.println("\n********************* MENÚ *********************");
            System.out.println("*****  1.Cifrar texto rapido por consola    ****");
            System.out.println("*****  2.Decifrar texto rapido por consola  ****");
            System.out.println("*****  3.Cifrar texto en archivo            ****");
            System.out.println("*****  4.Decifrar texto en archivo          ****");
            System.out.println("*****  5.Decifrar por Fuerza bruta (hack)   ****");
            System.out.println("*****  0.Salir                              ****");
            System.out.println("************************************************");
            System.out.print("Elige una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    encryptOptionFast();
                    break;
                case 2:
                    decryptOptionFast();
                    break;
                case 3:
                    encryptFile();
                    break;
                case 4:
                    decryptFile();
                    break;
                case 5:
                    decryptByBruteForce();
                    break;
                case 7:
                    exit = false;
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Elija un un numero correspondiente a las opciones que hay.");
            }
        }
    }

    private void encryptOptionFast() {
        System.out.print("Ingresa el texto a cifrar: ");
        String text = scanner.nextLine();
        System.out.print("Ingresa la clave (entero): ");
        int key = scanner.nextInt();
        scanner.nextLine();
        String encrypted = cipher.encrypt(text, key);
        System.out.println("Texto cifrado: " + encrypted);
    }

    private void decryptOptionFast(){
        System.out.print("Ingresa el texto a decifrar: ");
        String text = scanner.nextLine();
        System.out.print("Ingresa la clave (entero): ");
        int key = scanner.nextInt();
        scanner.nextLine();
        String decrypted = cipher.decrypt(text, key);
        System.out.println("Texto decifrado: " + decrypted);
    }

    private void encryptFile(){
        try {
            System.out.print("\n==== Cifrado de Archivo ====");
            String inputPath = promptPath("\nIngrese la ruta del archivo a cifrar:");
            String outputPath = promptPath("Ingrrese la ruta de salida: ");
            int key = promptKey();

            String content = FileManager.readFile(inputPath);
            String encripted = cipher.encrypt(content, key);

            FileManager.writeFile(encripted, outputPath);
            System.out.print("\n==== Archivo cifrado! ====");

        } catch (IOException e) {
            System.out.println("\nError al procesar el archivo: " + e.getMessage());
        }
    }

    private String promptPath(String message){
        String path;
        do{
            System.out.println(message);
            path = scanner.nextLine().trim();
            if(!validator.fileExists(path)){
                System.out.println("Archivo no encontrado");
            }
        } while (!validator.fileExists(path));
            return path;
    }

    private int promptKey(){
        int key;
        do{
            System.out.println("Ingrese la clave (entero)");
            try{
                key = Integer.parseInt(scanner.nextLine());
                if(!validator.isValidKey(key, ALPHABET)){
                    System.out.println("Clave invalida. Debe estar entre 0 y " + (ALPHABET.length() - 1));
                    key = -1;
                }
            } catch (Exception e) {
                System.out.println("Por favor ingrese una clave valida");
                key = -1;
            }
        } while (key == -1);
        return key;
    }

    private void decryptFile(){
        try {
            System.out.print("\n==== Descifrado de Archivo ====");
            String inputPath = promptPath("\nIngrese la ruta del archivo a descifrar");
            String outputPath = promptOutputPath("Ingrese la ruta del archivo de salida para el texto descifrado");
            int key = promptKey();

            String content = FileManager.readFile(inputPath);
            String decrypted = cipher.decrypt(content, key);

            FileManager.writeFile(decrypted, outputPath);
            System.out.println("\n==== Archivo descifrado correctamente! ====");

        } catch (IOException e) {
            System.out.println("\nError al procesar el archivo: " + e.getMessage());
        }
    }

    private String promptOutputPath(String message){
        System.out.println(message);
        return scanner.nextLine().trim();
    }

    private void decryptByBruteForce(){
        try {
            System.out.println("\n==== Descifrado por Fuerza Bruta ====");
            String inputPath = promptPath("\nIngrese la ruta del archivo cifrado:");
            String dictPath = promptPath("Ingrese la ruta del archivo diccionario (una palabra por línea):");
            String outputPath = promptOutputPath("Ingrese la ruta del archivo de salida para el texto descifrado:");

            String encryptedText = FileManager.readFile(inputPath);
            String bestGuess = bruteForce.decryptByBruteForce(encryptedText, dictPath);

            FileManager.writeFile(bestGuess, outputPath);
            System.out.println("\n==== Descifrado completado con éxito ====");

        } catch (IOException e) {
            System.out.println("\nError durante el descifrado por fuerza bruta: " + e.getMessage());
        }
    }


}


