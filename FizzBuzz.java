import java.util.Scanner;

public class FizzBuzz {
    public static void main(String args[]) {
        Scanner numInput = new Scanner(System.in);
        String input = numInput.nextLine();
        String[] numArray = input.split(" ");
        int N = Integer.parseInt(numArray[2]);
        int fizzCheck = Integer.parseInt(numArray[0]);
        int buzzCheck = Integer.parseInt(numArray[1]);

        for (int i = 1; i <= N; i ++) {
            String message = "";
            if (i % fizzCheck == 0) {
                message += "Fizz";
            }
            if (i % buzzCheck == 0) {
                message += "Buzz";
            }

            if (message == "") {
                message = String.valueOf(i);
            }

            System.out.println(message != ""? message : String.valueOf(i));
        }

    }

}