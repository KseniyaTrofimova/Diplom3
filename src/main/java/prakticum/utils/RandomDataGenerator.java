package prakticum.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomDataGenerator {
    public static String generateRandomEmail() {
        return "test" + System.currentTimeMillis() + "@example.com";
    }

    public static String generateRandomName() {
        return "User" + System.currentTimeMillis();
    }

    public static String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = ThreadLocalRandom.current().nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }
}
