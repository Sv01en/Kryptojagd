public class Vigenere {

    private final static char[] normalAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static String encode(String text, String key) {
        
        String encodedText = "";

        if (key.length() == 0) {
            return text.toUpperCase();
        }

        text = text.toUpperCase();
        key = key.toUpperCase();
 
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') {
                int result = (text.charAt(i) + getNormalAlphabetIndex(key.charAt(i % key.length())));
                if (result > 'Z') {
                    result = result - 26;
                }
                encodedText = encodedText + (char) result;
            } else {
                encodedText = encodedText + text.charAt(i);
            }
        }
 
        return encodedText;
 
    }

    public static String decode(String text, String key) {
 
        String decodedText = "";

        text = text.toUpperCase();
        key = key.toUpperCase();
 
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) >= 'A' && text.charAt(i) <= 'Z') {
                int result;
                if (getNormalAlphabetIndex(text.charAt(i)) - getNormalAlphabetIndex(key.charAt(i % key.length())) < 0) {
                    result = normalAlphabet[getNormalAlphabetIndex(text.charAt(i)) - getNormalAlphabetIndex(key.charAt(i % key.length())) + 26];
                } else {
                    result = normalAlphabet[getNormalAlphabetIndex(text.charAt(i)) - getNormalAlphabetIndex(key.charAt(i % key.length()))];
                }
                decodedText = decodedText + (char) result;
            } else {
                decodedText = decodedText + text.charAt(i);
            }
            
        }
 
        return decodedText;
 
    }

    private static int getNormalAlphabetIndex(char symbol) {
        if (symbol >= 'A' && symbol <= 'Z') {
            for (int i = 0; i < normalAlphabet.length; i++) {
                if (normalAlphabet[i] == symbol) {
                    return i;
                }
            }
        }
        return -1;
    }
}
