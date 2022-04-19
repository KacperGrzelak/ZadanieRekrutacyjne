import java.security.SecureRandom;
import java.util.Random;

public class RestUtils {

    static String[] NAMES = {"Kacper", "Robert", "Agnieszka", "Maciej"};
    static String[] LASTNAMES = {"Kowalski", "Nowak", "Wójcik", "Wiśniewski"};
    static String[] STREETS = {"Orla", "Morska", "Hallera", "Rejtana"};
    static String[] POSTCODES = {"82-300", "75-235", "80-000", "00-001", "30-002", "60-001", "70-001"};
    static String[] PLACES = {"Elbląg", "Koszalin", "Gdańsk", "Warszawa", "Kraków", "Poznań", "Szczecin"};
    static String[] PROVINCES = {"13", "15", "10", "6", "5", "14", "15"};
    static char[] ALL_CHARS = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    static Random rand = new SecureRandom();

    public static String getName() {
        String name = NAMES[rand.nextInt(NAMES.length)];
        return new String(name);
    }

    public static String getStreet() {
        String street = STREETS[rand.nextInt(STREETS.length)];
        return new String(street);
    }

    public static String getLastname() {
        String lastname = LASTNAMES[rand.nextInt(LASTNAMES.length)];
        return new String(lastname);
    }

    public static String getPostcode() {
        String postcode = POSTCODES[rand.nextInt(POSTCODES.length)];
        return new String(postcode);
    }

    public static String getPlace(int x) {
        String place = PLACES[x];
        return new String(place);
    }

    public static int getProvince(int x){
        String p = PROVINCES[x];
        int province = Integer.parseInt(p);
        return province;
    }

    public static String getLocalNumber(){
        int number = rand.nextInt(50);
        String localNumber = String.valueOf(number);
        return new String(localNumber);
    }

    public static String getPhoneNumber(){
        int n1 = rand.nextInt(100) + 500;
        int n2 = rand.nextInt(100) + 100;
        int n3 = rand.nextInt(100) + 100;
        String phoneNumber = ("" + n1 + "" + n2 + "" + n3);
        return new String(phoneNumber);
    }

    public static String getEmail(int length){
        assert length >= 4;
        assert length <= 16;

        char[] email = new char[length];

        for (int i = 0; i < length; i++) {
            email[i] = ALL_CHARS[rand.nextInt(ALL_CHARS.length)];
        }

        String fullEmail = String.valueOf(email) + "@wp.pl";

        return new String(fullEmail);
    }
}