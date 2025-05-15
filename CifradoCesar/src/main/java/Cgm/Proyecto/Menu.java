package Cgm.Proyecto;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final Cipher cipher;

    public Menu(Cipher cipher){
        this.cipher = cipher;
    }

    public void showmenu(){
        boolean exit = true;

        while (exit) {
            System.out.println("\n***************** MENÚ *****************");
            System.out.println("*****1.Cifrar texto rapido por consola***");
            System.out.println("*****2.Decifrar texto rapido por consola***");
            System.out.println("*****3.Cifrar texto en archivo***");
            System.out.println("*****4.Decifrar texto en archivo***");
            System.out.println("*****7.Salir*****");
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

}


