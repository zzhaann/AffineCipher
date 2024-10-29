public class AffineCipher {
    public static int modInverse(int a, int m) { //(a*x)%m=1
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }

    // шифрования текста
    public static String encrypt(String text, int a, int b, int m) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                int x = Character.toUpperCase(ch) - 'A'; //position
                char encryptedChar = (char) ((a * x + b) % m + 'A');
                result.append(encryptedChar);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    // расшифрования текста
    public static String decrypt(String text, int a, int b, int m) {
        StringBuilder result = new StringBuilder();
        int aInverse = modInverse(a, m);

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                int y = Character.toUpperCase(ch) - 'A';
                char decryptedChar = (char) (aInverse * (y - b + m) % m + 'A');
                result.append(decryptedChar);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String plaintext = "SECRET MESSAGE";
        int a = 5;
        int b = 7;
        int m = 26;

        String encrypted = encrypt(plaintext, a, b, m);
        System.out.println("Зашифрованный текст: " + encrypted);

        String decrypted = decrypt(encrypted, a, b, m);
        System.out.println("Расшифрованный текст: " + decrypted);
    }
}
