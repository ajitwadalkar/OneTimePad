
public class EncoderDecoder {
    public static String EncodeDecodeData(String str1, String str2) {
        StringConvert sc = new StringConvert();
        String BinaryText = sc.ToBinary(str2);
        String xorData = XorData(str1, BinaryText);
        String stringTxt = sc.ToString(xorData);
        return stringTxt;
    }

    //Xor Function
    public static String XorData(String str1, String str2) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < str1.length(); i++)
            output.append((str1.charAt(i) ^ str2.charAt(i)));
        return output.toString();
    }

}
