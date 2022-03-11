import java.io.*;
import java.util.*;

//https://stackoverflow.com/questions/505928/how-to-count-the-number-of-occurrences-of-an-element-in-a-list

public class MainApp {
    static String csvPath = "src/data_sekolah.csv";
    static int menuTerpilih;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        pilihMenu();
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
        System.out.print("Pilih menu : ");
        menuTerpilih = input.nextInt();
        switch (menuTerpilih){
            case 1 :
                persebaranData(renderFile(csvPath));
                break;
            case 2 :
                tulisTxt(cariMean(renderFile(csvPath)),cariMedian(renderFile(csvPath)), cariModus(renderFile(csvPath)) );
                break;

            case 3 :
                persebaranData(renderFile(csvPath));
                tulisTxt(cariMean(renderFile(csvPath)),cariMedian(renderFile(csvPath)), cariModus(renderFile(csvPath)) );
                break;

            case 0 :
                System.exit(0);
                break;
            default:
                System.out.println("Input salah mohon coba lagi");
                pilihMenu();
        }
    }


    public static List<Integer> renderFile(String target){
        String line = "";
        List<String> lsValues = new ArrayList<>();
        List<Integer> lsNumber = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(target));
            while ((line = br.readLine()) != null){
                lsValues.addAll(Arrays.asList(line.split(";")));
            }
            lsValues.removeIf(str -> str.contains("Kelas"));
            for (String str : lsValues){
                int num = Integer.parseInt(str);
                lsNumber.add(num);
            }
            br.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        Collections.sort(lsNumber);
        return lsNumber;
    }

    public static float cariMean(List<Integer> lsParam){
        float jumlah = 0;
        float mean = 0;
        for (int num : lsParam){
            jumlah += num;
        }
        mean = jumlah/lsParam.size();
        return mean;
    }

    public static float cariMedian(List<Integer> lsParam){
        float median = 0;
        if (lsParam.size() % 2 == 0){
            float num1 = lsParam.get(lsParam.size()/2);
            float num2 = lsParam.get(lsParam.size()/2 - 1);
            median = (num1 + num2)/2;
        }
        else {
            median = lsParam.get(lsParam.size()/2);
        }
        return median;
    }

    public static float cariModus(List<Integer> lsParam){
        float modus = 0;
        int counter = 1;
        for (int num : lsParam){
            int freq = Collections.frequency(lsParam, num);
            if (freq > counter){
                modus = num;
                counter = freq;
            }
        }
        return modus;
    }

    public static void tulisTxt(float mean, float median, float modus){
        String path = "src/data_sekolah.txt";
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write("Berikut Hasil Pengolahan Nilai:");
            writer.newLine();
            writer.write("Berikut hasil sebaran data nilai");
            writer.newLine();
            writer.write("Mean " + mean);
            writer.newLine();
            writer.write("Median " + median);
            writer.newLine();
            writer.write("Modus " + modus);
            writer.close();
            selesai(path);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void persebaranData (List <Integer> lsParam){
        String path = "src/data_sekolah_modus.txt";
        Map<Integer, Integer> hm = new HashMap<Integer, Integer>();
        for (Integer i : lsParam) {
            Integer j = hm.get(i);
            hm.put(i, (j == null) ? 1 : j + 1);
        }
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write("Berikut Hasil Pengolahan Nilai:");
            writer.newLine();
            writer.write("Nilai     |     Frekuensi");
            writer.newLine();
            for (Map.Entry<Integer, Integer> val : hm.entrySet()) {
                writer.write(val.getKey() + "         |       " + val.getValue());
                writer.newLine();
            }
            writer.close();
            selesai(path);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void selesai(String path){
        System.out.println("-------------------------------------");
        System.out.println("Aplikasi Pengolah nilai Siswa");
        System.out.println("-------------------------------------");
        System.out.println("File telah digenerate " + path);
        System.out.println("Silahkan cek");
        System.out.println("0. Exit");
        System.out.println("1. Kembali ke menu utama");
        System.out.println("-------------------------------------");
        System.out.print("Pilih menu : ");
        menuTerpilih = input.nextInt();
        switch (menuTerpilih){
            case 0 :
                System.exit(0);
                break;
            case 1 :
                pilihMenu();
                break;
            default:
                System.out.println("Inputan Salah");
                selesai(path);
        }
    }

}
