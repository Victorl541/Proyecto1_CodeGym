package Cgm.Proyecto;

public class MainApp {

    public static void main(String[] args) {

        final String ALPHABET = "abcdefghijklmnopqrstuvwxyz 1234567890?,.:-'!";

        Cipher cipher = new Cipher(ALPHABET);
        FileManager fileManager = new FileManager();
        Validator validator = new Validator();





        Menu menu = new Menu(cipher, fileManager, validator, ALPHABET);
        menu.showmenu();

    }
}