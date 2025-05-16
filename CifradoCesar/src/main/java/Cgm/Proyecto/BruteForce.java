package Cgm.Proyecto;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BruteForce {

    private Cipher cipher;
    private final String ALPHABET;

    public BruteForce(Cipher cipher, String ALPHABET){
        this.cipher = cipher;
        this.ALPHABET = ALPHABET;
    }

    public String decryptByBruteForce(String encryptedText, String dictionaryPath) {
        try {
            Set<String> dictionary = loadDictionary(dictionaryPath);

            int bestScore = -1;
            String bestDecryption = "";
            int bestKey = -1;

            for (int key = 1; key < ALPHABET.length(); key++) {
                String attempt = cipher.decrypt(encryptedText, key);
                int score = scoreText(attempt, dictionary);

                if (score > bestScore) {
                    bestScore = score;
                    bestDecryption = attempt;
                    bestKey = key;
                }
            }

            System.out.println("Mejor clave encontrada: " + bestKey);
            FileManager.writeFile(bestDecryption, "mejor_descifrado.txt");
            return bestDecryption;

        } catch (IOException e) {
            System.out.println("Error al cargar el diccionario o escribir el archivo: " + e.getMessage());
            return "";
        }
    }

    private Set<String> loadDictionary(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        Set<String> dictionary = new HashSet<>();
        for (String line : lines) {
            dictionary.add(line.trim().toLowerCase());
        }
        return dictionary;
    }

    private int scoreText(String text, Set<String> dictionary) {
        String[] words = text.toLowerCase().split("[^a-zñáéíóúü]+");
        int score = 0;
        for (String word : words) {
            if (dictionary.contains(word)) {
                score++;
            }
        }
        return score;
    }

}


