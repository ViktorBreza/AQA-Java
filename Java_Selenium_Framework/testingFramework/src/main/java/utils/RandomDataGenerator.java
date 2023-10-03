package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomDataGenerator {
    public String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

    public int generateRandomNumber(int min, long max) {
        Random random = new Random();
        return random.nextInt((int) (max - min + 1)) + min;
    }

    public boolean generateRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public static Map<String, String> getFakeManager() {
        Map<String, String> fakeManager = new HashMap<>();
        RandomDataGenerator dataGenerator = new RandomDataGenerator();

        String firstName = dataGenerator.generateRandomString(5);
        String lastName = dataGenerator.generateRandomString(5);
        String email = dataGenerator.generateRandomString(5).toLowerCase() + "@"
                + dataGenerator.generateRandomString(4).toLowerCase() + ".com";
        String login = dataGenerator.generateRandomString(1).toUpperCase()
                + dataGenerator.generateRandomString(3);
        String phoneNumber = String.valueOf(dataGenerator.generateRandomNumber(1000000000, 9999999999L));
        String skype = dataGenerator.generateRandomString(4) + dataGenerator.generateRandomNumber(100, 999);

        fakeManager.put("firstName", firstName);
        fakeManager.put("lastName", lastName);
        fakeManager.put("email", email);
        fakeManager.put("login", login);
        fakeManager.put("phoneNumber", phoneNumber);
        fakeManager.put("skype", skype);

        return fakeManager;
    }


}
