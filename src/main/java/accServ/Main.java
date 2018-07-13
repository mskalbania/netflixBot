package accServ;

import java.util.HashMap;
import java.util.Map;

import static accServ.AccountServiceConstants.EMAIL;
import static accServ.AccountServiceConstants.PASSWORD;

public class Main {


    public static void main(String[] args) {
        int rand = (int) (Math.random() * 1000000);
        final Map<String, String> inputPrams = new HashMap<>();
        inputPrams.put(EMAIL, "qwerty" + rand + "@gmail.com");
        inputPrams.put(PASSWORD, "qwerty1234");
        final Map<String, String> accountParams = CreateAccountService.createAccount(inputPrams);

    }
}