package Cgm.Proyecto;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Validator{

        public boolean isValidKey(int key, String ALPHABET) {
            return key >= 0 && key < ALPHABET.length();
        }
        public boolean fileExists(String path) {
            return Files.exists(Paths.get(path));
        }
}



