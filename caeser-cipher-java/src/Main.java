import java.io.*;
import java.util.Scanner;

public class Main {

    public static String rus_upper_alphabet = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    public static String rus_lower_alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static void main(String[] args) throws IOException {

        while (true){
            Scanner in = new Scanner(System.in);
            System.out.println("Укажите операцию (1/2):\n1. Шифрование\n2. Дешифрование");
            System.out.print(">> ");

            int operation = in.nextInt();

            if (!(operation > 0 & operation < 3)){
                System.out.println("Ошибка: выбрана неверная операция.");
                continue;
            }

            Scanner in2 = new Scanner(System.in);
            System.out.println("Укажите сдвиг: ");
            System.out.print(">> ");

            int change = in2.nextInt();

            if (operation == 1){
                if (change >= 0){
                    processFile(change);

                    System.out.println("Шифрование завершено.");
                }
                else{
                    System.out.println("Ошибка: сдвиг принимает только числа от нуля.");
                    continue;
                }
            }
            else if (operation == 2){
                if (change >= 0){
                    processFile(-change);

                    System.out.println("Расшифровка завершена.");
                }
                else{
                    System.out.println("Ошибка: сдвиг принимает только числа от нуля.");
                }
            }
        }
    }

    public static String caesar_encrypt(String word, int change){
        StringBuilder result = new StringBuilder();

        char[] temp = new char[]{};
        for (char ch : word.toCharArray()){
            if (rus_upper_alphabet.contains(Character.toString(ch))){
                result.append(rus_upper_alphabet.toCharArray()[((rus_upper_alphabet.indexOf("" + ch) + (change % 33 + 33)) % 33)]);
            }
            else if (rus_lower_alphabet.contains(Character.toString(ch))){
                result.append(rus_lower_alphabet.toCharArray()[((rus_lower_alphabet.indexOf("" + ch) + (change % 33 + 33)) % 33)]);
            }
            else if ((int)ch >= 65 & (int)ch <= 91){
                result.append((char) ('A' + (ch - 'A' + (change % 26 + 26)) % 26));
            }
            else if ((int)ch >= 97 & (int)ch <= 123){
                result.append((char) ('a' + (ch - 'a' + (change % 26 + 26)) % 26));
            }
            else{
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static void processFile(int change) throws IOException {
        File inputFile = new File("src/resources/input_file.txt");
        File outputFile = new File("src/resources/output_file.txt");

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        String line;
        while ((line = reader.readLine()) != null){
            writer.write(caesar_encrypt(line, change));
        }

        reader.close();
        writer.close();
    }
}