import java.util.*;



public class Say {


    public static String ZERO = "zero";
    public static String TEN = "ten";
    public static String HUNDRED = "hundred";
    public static List<String> units = Arrays.asList(new String[]{"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"});
    public static List<String> teens = Arrays.asList(new String[]{"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"});
    public static List<String> tens = Arrays.asList(new String[]{"twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"});
    public static List<String> thousands = Arrays.asList(new String[]{"thousand", "million", "billion", "trillion", "quadrillion", "quintillion"});


    public static String value(long n) {

        String result = "";

        if (n == 0) {
            return "zero";
        }

        if (n > 0 && n < 10) {
            return units.get((int) (n - 1));
        }

        if (n == 10) {
            return "ten";
        }

        if (n > 10 && n < 20) {
            return teens.get((int) (n - 11));
        }

        if (n < 100 && n % 10 == 0 && n != 10) {
            return tens.get(((int) n / 10) - 2);
        }

        if (n > 20 && n < 100 && n % 10 != 0) {
            long unidades = n % 10;
            return (value(n - unidades) + "-" + value(unidades));
        }

        if (n == 100) {
            return "hundred";
        }

        int exponente = (int) Math.log10(n);
        if (exponente >= 3) {
            return thousands.get((exponente / 3) - 1);
        }

        return result;
    }

    public static String valorMiembro(long coeficiente, int exponente){

        String result = "";

        if (coeficiente==0) return "";

        if (exponente>=3) return valorMiembro(coeficiente,0) + " " + value((long) Math.pow(10, exponente));

        if (coeficiente < 100) return value(coeficiente%100);

        if (coeficiente%100==0) return value((int)coeficiente/100) + " hundred"  ;

        if (coeficiente%100!=0) return value((int)coeficiente/100) + " hundred and " + value(coeficiente%100) ;

        return result;
    }




    public static String say(long n) {

        if (n==0) return "Zero";

        String result = "";

        Map<Integer, Long> miembros = new HashMap<Integer, Long>();

        long resto = n;
        int  exponente=0;
        while (resto>0){
            long miembro = resto%1000;
            if (miembro>0) miembros.put(exponente, miembro);
            resto-=miembro;
            resto/=1000;
            exponente+=3;
        }

        List<String> partes = new ArrayList<String>();

        List<Integer> exponentes = new ArrayList();
        exponentes.addAll(miembros.keySet());
        Collections.sort(exponentes);
        Collections.reverse(exponentes);

        for (int key:exponentes){
            long miembro = miembros.get(key);
            String parte = valorMiembro(miembro,key);
            result = result.concat(" ").concat(parte);
        }


        result = result.trim();
        result = result.substring(0, 1).toUpperCase() + result.substring(1);

        return result;
    }

}