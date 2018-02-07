
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReadWrite {

    String dataPath;

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public static String ReadFile(String fileName)
    {
        String filePath = fileName;
        StringBuilder ReadData = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
                stream.forEach(s -> ReadData.append(s));
        }
        catch (IOException e)
        {
            System.out.println("File does not exist.");
        }
        if(ReadData.toString().length()==0)
        {
            return fileName + " is Empty or Does not exist.";
        }
        else {
            return ReadData.toString();
        }

    }

    public static Boolean WriteFile(String fileName, String DataToWrite)
    {
        String filePath = fileName;
        try
        {
            Path file = Paths.get(filePath);
            Files.write(file, DataToWrite.getBytes());
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

    }
}
