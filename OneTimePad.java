
import java.io.File;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.*;


public class OneTimePad {

    //Initializing the Scanner
    static final Scanner s = new Scanner(System.in);

    //Initializing FileReadWrite Class
    static final FileReadWrite frw = new FileReadWrite();

    //Initializing EncoderDecoder
    static final EncoderDecoder encdec = new EncoderDecoder();

    //Initialing variables
    static String  dataPath;
    public static void main(String[] args) {

        File f = new File("./src/data/key.txt");
        if(f.exists() && !f.isDirectory()) {
         dataPath =    "./src/data/";
        }
        else
        {
            dataPath="./data/";
        }

        //Reading and Printing the default Values
        DataReader();
        int option;
        do {
            //Choosing the function
            System.out.println("Following are the functionalities in the code, choose anyone:\n1.Encoding\n2.Decoding\n3.KeyGen\n4.Frequency Distribution\nPlease choose the value in between 1,2,3, or 4.");
            int funcSel = promptChoice(1, 4);
            //Executing the function
            subMain(funcSel);

            System.out.println("Do you want to re-run the program?");
            System.out.println("Please choose from the following.\n1. Yes\n2. No");
            option =  promptChoice(1, 2);
        } while (option == 1);

        System.out.println("You chose to terminate the program. Thank you.");
    }

    //Encoder/Decoder function

    public static void DataReader() {
        FileReadWrite frw = new FileReadWrite();
        //Reading default Values
        String keyText = frw.ReadFile(dataPath+"key.txt");
        String plainText = frw.ReadFile(dataPath+"plaintext.txt");
        String cipherText = frw.ReadFile(dataPath+"ciphertext.txt");
        String resultText = frw.ReadFile(dataPath+"result.txt");
        String newKeyText = frw.ReadFile(dataPath+"newkey.txt");

        //Confirming plaintext length to be 4
        if (plainText.length() != 4) {
            System.out.println("Allowed words length for plaintext is 4. And the enterted text is " + plainText + ", which is of length: " + plainText.length());
            System.exit(0);
        }
        //Printing initial Values
        System.out.println("Current Data in file key.text: " + keyText);
        System.out.println("Current Data in file plaintext.txt: " + plainText);
        System.out.println("Current Data in file ciphertext.txt:" + cipherText);
        System.out.println("Current Data in file newkey.txt:" + newKeyText);
        System.out.println("Current Data in file resultText: " + resultText + "\n");

    }

    private static int promptChoice(int min, int max) {
        for (; ; ) {
            if (!s.hasNextInt()) {
                System.out.println("You must enter a number");
            } else {
                int option = s.nextInt();
                if (option >= min && option <= max)
                    return option;
            }
            s.nextLine();
        }
    }

    public static void subMain(int option) {
        long duration, endTime, startTime;
        //Calling encryption function
        if (option == 1) {
            System.out.println("Selected Function is Encryption.");
            String  plainText = frw.ReadFile(dataPath+"plaintext.txt");
            String keyText = frw.ReadFile(dataPath+"key.txt");
            System.out.println("Plain text in the file plaintext.txt: " + plainText);
            startTime = System.nanoTime();
            String cipherText = encdec.EncodeDecodeData(keyText, plainText);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            double seconds = (double) duration / 1000000000.0;
            System.out.println("The time it took to run Encryption function  is: " + seconds + "Seconds");
            frw.WriteFile(dataPath+"ciphertext.txt", cipherText);
            System.out.println("Encrypted Text is: " + cipherText);

        }
        //Calling decryption function
        else if (option == 2) {
            System.out.println("Selected Function is Decryption.");
            String  cipherText = frw.ReadFile(dataPath+"ciphertext.txt");
            String keyText = frw.ReadFile(dataPath+"key.txt");
            System.out.println("Cipher text in the file ciphertext.txt: " + cipherText);
            startTime = System.nanoTime();
            String plainText = encdec.EncodeDecodeData(keyText, cipherText);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            double seconds = (double) duration / 1000000000.0;
            System.out.println("The time it took to run Decryption function  is: " + seconds + "Seconds");
            frw.WriteFile(dataPath+"result.txt", plainText);
            System.out.println("Decrypted Text is: " + plainText);
        }
        //Calling Keygen function
        else if (option == 3) {

            System.out.println("Enter the security parameter value, such that (1 <= λ <= 128).");
            int lambda =  promptChoice(1, 128);
            System.out.println("Selected Function is KeyGeneration and security parameter λ value is: " + lambda);

            startTime = System.nanoTime();
            BigInteger newSKey = new BigInteger(lambda, new SecureRandom());
            String binarySK = StringConvert.ToBinary(newSKey.toString());
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            double seconds = (double) duration / 1000000000.0;
            System.out.println("The time it took to run keyGen function  is: " + seconds + "Seconds");
            System.out.println("The generated key is: "+binarySK);
            frw.WriteFile(dataPath+"newkey.txt", binarySK);

            //Checking Key Distribution
            HashMap<BigInteger, Integer> uniqueKey = new HashMap<BigInteger, Integer>();
            for (int i = 0; i < 10000; i++) {
                newSKey = new BigInteger(lambda, new SecureRandom());
                if (uniqueKey.containsKey(newSKey)) {
                    uniqueKey.put(newSKey, (uniqueKey.get(newSKey) + 1));
                } else {
                    uniqueKey.put(newSKey, 1);
                }
            }

            //to avoid printing too many lines
            if(lambda<=5) {
                System.out.println("Following is the frequency distribution");
            }
            Integer[] frequencies = new Integer[uniqueKey.keySet().size()];
            int i = 0;
            long total = 0;
            for (BigInteger key : uniqueKey.keySet()) {
                int fVal = uniqueKey.get(key);
                frequencies[i] = fVal;
                total += fVal;
                i++;
                //to avoid printing too many lines
                if(lambda<=5) {
                    System.out.println("Key: " + key.toString() + " Freqency: " + fVal);
                }
            }


            System.out.println("Min Frequency Value: " + Collections.min(Arrays.asList(frequencies)));
            System.out.println("Max Frequency Value: " + Collections.max(Arrays.asList(frequencies)));
            System.out.println("Mean Frequency Value: " + (total / uniqueKey.keySet().size()));
        }
        else if (option == 4) {
            System.out.println("Selected Function is Frequency Distribution PlotGraph for lambda=3 and iterations = 10000. And to check the running time of 128 bit Encryption");
            String  plainText = "abcdefghijklmnop";
            BigInteger newSKey = new BigInteger(128, new Random());
            String keyText = newSKey.toString(2);
            startTime = System.nanoTime();
            String cipherText = encdec.EncodeDecodeData(keyText, plainText);
            endTime = System.nanoTime();
            duration = (endTime - startTime);
            double seconds = (double) duration / 1000000000.0;

            System.out.println("Encrypted Text is: " + cipherText);
            System.out.println("The time it took to run Encryption function for 128 bit text is: " + seconds + " Seconds");
            PlotGraph.main(null);

        }
        System.out.println("\nFiles after the run:");
        DataReader();
    }

}

