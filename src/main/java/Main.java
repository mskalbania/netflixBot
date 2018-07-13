import java.util.HashMap;
import java.util.Map;

public class Main {


    public static void main(String[] args) {
        int rand = (int) (Math.random() * 1000000);
        final Map<String, String> inputPrams = new HashMap<>();
        inputPrams.put(CreateAccountService.EMAIL, "qwerty" + rand + "@gmail.com");
        inputPrams.put(CreateAccountService.PASSWORD, "qwerty1234");
        final Map<String, String> accountParams = CreateAccountService.createAccount(inputPrams);

    }
}