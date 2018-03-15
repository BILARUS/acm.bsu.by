import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BFS {
    
    private static int n; 
    private int counter = 0;
    private List<Integer>[] graph;
    private boolean[] used;
    static FileWriter writeFile;
    private static int []result;
    private Queue<Integer> queue;

    private void bfs(int v) { 
        if (used[v]) { //���� ������� �������� ����������, �� �� ���������� �� ��� ����� ���������
            return;
        }
        queue.add(v); //�������� ����� �� ������� v
        used[v] = true; //�������� ������� ��� ����������
        while (!queue.isEmpty()) {
            v = queue.poll(); //��������� ������� �� �������
            counter++;
            result[v]=counter;
            //��������� ����� �� ���� ������, ������� � �������� v
            for (int i = 0; i < graph[v].size(); ++i) { 
                int w = graph[v].get(i);
                //���� ������� ��� ���� ��������, �� ���������� ��
                if (used[w]) { 
                    continue;
                }
                queue.add(w); //��������� ������� � ������� ������
                used[w] = true; //�������� ������� ��� ����������
            }
        }
    }
    
    //��������� ���������� ������� ������ � �������
    private void readData() throws IOException {
        		List<String> matrix_of_graph= new ArrayList<String>();
        		String str_test = null;
        		try {
        			Scanner sc = new Scanner(new File("input.txt"));
        			n = sc.nextInt();
        			while (sc.hasNextLine()) {
        				if(!((str_test=sc.nextLine()).isEmpty()))
        					matrix_of_graph.add(str_test);
        			}
        			sc.close();
        		} catch (FileNotFoundException e) {};
        
        		int mas[][]= new int[n][n];
        		String temp_mas[]= new String[n];
        		for(int i = 0; i<n; i++){
        			temp_mas = matrix_of_graph.get(i).split(" ");
        			for(int j = 0; j<n; j++){
        				mas[i][j] = Integer.parseInt(temp_mas[j]);
        			}
        		}
        

        graph = new ArrayList[n]; 
        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<Integer>();
        }
        
        for(int i = 0; i<n;i++){
        	for(int j = 0; j< n; j++){
        		if(mas[i][j]!=0){
        			graph[i].add(j);
        		}
        	}
        }
        
        result = new int[n];
        used = new boolean[n];
        Arrays.fill(used, false);
        writeFile = new FileWriter(new File("output.txt"));
        queue = new LinkedList<Integer>();
    }
    
    private void run() throws IOException {
    	readData();
        for (int v = 0; v < n; ++v) {
            bfs( v);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BFS solution = new BFS();
        solution.run();
        for (int i = 0; i<n-1; i++){
        	writeFile.write("" + result[i]+" ");
        }
        writeFile.write(""+result[n-1]);
        writeFile.close();
    }
}