import java.io.*;
import java.util.*;

public class Main {
    static String modusPath = "/home/kmalif/IdeaProjects/Challenge2/src/data_sekolah.txt";
    static String meanMedianPath = "/home/kmalif/IdeaProjects/Challenge2/src/data_sekolah_mean.txt";
    static String csvPath = "/home/kmalif/IdeaProjects/Challenge2/src/data_sekolah.csv";
    static int menuTerpilih;
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
//        removeList(renderFile(csvPath));
//        pilihMenu();

    }

    public static void pilihMenu(){
        System.out.println("-------------------------------------");
        System.out.println("Aplikasi Pengolah nilai Siswa");
        System.out.println("-------------------------------------");
        System.out.println("Menu : ");
        System.out.println("1. Generate txt untuk menampilkan modus");
        System.out.println("2. Generate txt untuk menampilkan nilai rata-rata");
        System.out.println("3. Generate kedua file");
        System.out.println("0. Keluar");
        System.out.println("-------------------------------------");
        menuTerpilih = input.nextInt();
        switch (menuTerpilih){
            case 1 :
                writeText(renderFile(csvPath), modusPath);
                break;
            case 2 :
                System.out.println("Mean, median");
                break;

            case 3 :
                System.out.println("kedua file");
                break;

            case 0 :
                System.exit(0);
                break;
            default:
                System.out.println("Input salah mohon coba lagi");
                pilihMenu();
        }
    }

    public static List<String> renderFile(String target){
        String line = "";
        List<String> lsValues = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(target));
            while ((line = br.readLine()) != null){
                lsValues.addAll(Arrays.asList(line.split(";")));
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return lsValues;
    }

    public static void writeText(List<String> words, String path){
        StringJoiner sj = new StringJoiner(";");
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for (String word : words){
                sj.add(word);
            }
            writer.write(sj.toString());
            writer.newLine();
            writer.close();
            System.out.println("File terbentuk " + path);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static List<Integer> removeList (List<String> lsParam){
        lsParam.removeIf(str -> str.contains("Kelas"));
        List<Integer> lsINteger = new ArrayList<>();
        for (String str : lsParam){
            int num = Integer.parseInt(str);
            lsINteger.add(num);
        }
        Collections.sort(lsINteger);
        return lsINteger;
    }



}
