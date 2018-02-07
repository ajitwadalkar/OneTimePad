
public class StringConvert {

    public static String ToBinary(String str)
    {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str.length();i++)
        {
            char A = str.charAt(i);
            int ascii = (int) A;
            String bitData = Integer.toBinaryString(ascii);
            bitData= ("00000000" + bitData).substring(bitData.length());
            sb.append(bitData);
        }
        return sb.toString();
    }

    public static String ToString(String BinaryData)
    {
        StringBuilder textData = new StringBuilder();
        for (int i = 0; i < BinaryData.length(); i += 8) {
            String bData = BinaryData.substring(i, Math.min(i+8, BinaryData.length()));
            int ascii = Integer.parseInt(bData, 2);
            char asciiChar = (char)ascii;
            textData.append(asciiChar);
        }
        return textData.toString();
    }
}
