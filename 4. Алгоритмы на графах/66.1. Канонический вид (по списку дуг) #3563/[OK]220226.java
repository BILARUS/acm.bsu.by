import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main{

    static int n;

    public static void main(String[] args) throws Exception{
            List<String> lines = Files.readAllLines(Paths.get("input.txt"));
            String line1 = lines.get(0);
            n = Integer.parseInt(line1);

            int[] arr = new int[n];


            for (int i = 1; i <= n - 1; i++){
                String[] line = lines.get(i).split(" ");
                int v1 = Integer.parseInt(line[0]);
                int v2 = Integer.parseInt(line[1]);
                arr[v2 - 1] = v1;
            }

            Files.createFile(Paths.get("output.txt"));
            PrintWriter pw = new PrintWriter("output.txt");
            

            for (int i = 0; i < n; i++){
                pw.print(arr[i]);
                if (i != n - 1) pw.print(" ");
            }

            pw.flush();


        }

}
