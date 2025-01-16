package src;

import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PolynomialSolver {

    public static long decodeValue(String base, String value) {
        int baseValue = Integer.parseInt(base);
        return Long.parseLong(value, baseValue);
    }

    public static JSONObject generateInputJSON(int n, int k, Scanner scanner) {
        JSONObject inputJSON = new JSONObject();
        JSONObject keys = new JSONObject();
        keys.put("n", n);
        keys.put("k", k);
        inputJSON.put("keys", keys);

        for (int i = 1; i <= n; i++) {
            System.out.println("Enter base and value for root " + i);
            System.out.print("Base (e.g., 2, 10, 16, 7, etc.): ");
            String base = scanner.next();
            System.out.print("Value (in base " + base + "): ");
            String value = scanner.next();

            JSONObject root = new JSONObject();
            root.put("base", base);
            root.put("value", value);

            inputJSON.put(String.valueOf(i), root);
        }

        return inputJSON;
    }

    public static void saveToFile(JSONObject inputJSON) {
        try (FileWriter file = new FileWriter("input.json")) {
            file.write(inputJSON.toString(4));
            System.out.println("Input JSON saved to 'input.json'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of roots (n): ");
        int n = scanner.nextInt();
        System.out.print("Enter the minimum number of roots required (k): ");
        int k = scanner.nextInt();

        JSONObject inputJSON = generateInputJSON(n, k, scanner);
        saveToFile(inputJSON);

        scanner.close();
    }
}
