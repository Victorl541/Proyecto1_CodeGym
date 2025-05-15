package Cgm.Proyecto;

public class MainApp {

    public static void main(String[] args) {

        final String ALPHABET = "abcdefghijklmnopqrstuvwxyz 1234567890?,.:-'!";

        Cipher cipher = new Cipher(ALPHABET);
        Menu menu = new Menu(cipher);





        menu.showmenu();



    }
}