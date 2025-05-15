package Cgm.Proyecto;

public class Cipher {

    private String ALPHABET;

    public Cipher(String ALPHABET) {
        this.ALPHABET = ALPHABET;
    }

    public String encrypt (String text, int key){
        if (key < 1) {
            return "La clave debe ser un número positivo.";
        }

        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);
            int index = ALPHABET.indexOf(currentChar);

            if (index != -1) {
                int shiftedIndex = (index + key) % ALPHABET.length();
                encryptedText.append(ALPHABET.charAt(shiftedIndex));
            } else {
                encryptedText.append(currentChar);
            }
        }

        return encryptedText.toString();
    }

    public String decrypt (String encryptedText, int key){

        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < encryptedText.length(); i++) {
            char currentChar = encryptedText.charAt(i);
            int index = ALPHABET.indexOf(currentChar);

            if (index == -1) {
                throw new IllegalArgumentException("Carácter no permitido: " + currentChar);
            }
            int shiftedIndex = (index - key) % ALPHABET.length();
            if (shiftedIndex < 0) {
                shiftedIndex += ALPHABET.length();
            }
            decryptedText.append(ALPHABET.charAt(shiftedIndex));
        }
        return decryptedText.toString();
    }

}




