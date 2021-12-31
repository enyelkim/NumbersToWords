import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import static java.lang.Math.pow;
import static java.util.Arrays.asList;



public class Words {


    @SuppressWarnings("ThrowablePrintedToSystemOut")
    public static void main(String[] args) {

        String numberInWords;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter a number in words:");
            numberInWords = br.readLine();
            System.out.println("The result " + numberInWords + " is: " + words(numberInWords));
        } catch (IOException ioe) {
            System.out.println(ioe);
        }


    }

    public static String ZERO = "zero";
    public static String TEN = "ten";
    public static String HUNDRED = "hundred";
    public static List<String> units = asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    public static List<String> teens = asList("eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen");
    public static List<String> tens = asList("twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety");
    public static List<String> thousands = asList("thousand", "million", "billion", "trillion", "quadrillion", "quintillion");

    public static long value(String s) {

        long result = 0;

        if (s == null) {
            return 0;
        }
        // Carácter en la posición 1 de unidades
        if (units.contains(s)) {
            return units.indexOf(s) + 1;
        }
        if (TEN.equals(s)) {
            return 10;
        }
        // Carácter  +11 es porque el valor eleven  está en el Index 0.
        // Se le suma 1 por el índice y 10 porque en la primera decena llevo 10
        if (teens.contains(s)) {
            return teens.indexOf(s) + 11;
        }
        // Carácter en la posición después del valor del índice dado con un dígito largo.

        if (tens.contains(s)) {
            return (tens.indexOf(s) + 2) * 10L;
        }

        //Se suma el primero que es múltiplo de 10 + el número de unidades.
        if (s.contains("-")) {
            long val = 0;
            String[] ss = s.split("-");
            for (String part : ss) {
                val += value(part);
            }
            return val;
        }
        //Función simple de que si el texto tiene que ver con el string s que sería hundred, que imprima 100.
        if (HUNDRED.equals(s)) {
            return 100;
        }
        //Lo mismo con el mil, usando la sentencia del pow para colocar el exponente elevado a 3 junto el contador
        //del string a 1.
        if (thousands.contains(s)) {
            return (long) pow(10, 3 * (thousands.indexOf(s) + 1));
        }
        return result;
    }

    public static long valorMiembro(long coeficiente, int exponente) {
        return (long) (coeficiente * pow(10, exponente));
    }

    public static List<String> tokens(String number) {

        List<String> result = new ArrayList<String>();

        if (number == null) {
            return result;
        }

        String s = number.toLowerCase();

        for (String token : Arrays.asList(s.split(" "))) {
            if (token == null) {
                continue;
            }
            if ("".equals(token)) {
                continue;
            }
            result.add(token);
        }

        return result;
    }

    public static List<String> giraTokens(String number) {

        List<String> result = tokens(number);
        Collections.reverse(result);
        return result;
    }

    public static long words(String s) {

        List<String> tokens = giraTokens(s);

        long result = 0;

        if (tokens.isEmpty()) {
            return result;
        }
        if (ZERO.equals(tokens.get(0))) {
            return result;
        }

        result = calcula(tokens);

        return result;
    }

    private static long calcula(List<String> tokens) {

        long numero = 0;
        long coeficiente = 0;
        int exponente = 0;

        boolean isHundred = false;

        for (String token : tokens) {

            if ("and".contains(token)) {
                continue;
            }
            if (thousands.contains(token)) {
                numero += valorMiembro(coeficiente, exponente);
                exponente = (int) Math.log10(value(token));
                coeficiente = 0;
                continue;
            }
            if (HUNDRED.equals(token)) {
                isHundred = true;
                continue;
            }
            if (isHundred) {
                isHundred = false;
                coeficiente += (value(token) * value(HUNDRED));
                continue;
            }
            coeficiente += value(token);

        }

        numero += valorMiembro(coeficiente, exponente);

        return numero;
    }

}
