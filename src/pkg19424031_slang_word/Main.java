/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg19424031_slang_word;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *
 * @author nguye
 */
public class Main {
 

    private static Scanner sc = new Scanner(System.in);
    private static Map < String, String > dictionarySD = new HashMap < String, String > ();
    private static Map < String, String > dictionaryDS = new HashMap < String, String > (); 
    private static Map < String, ArrayList < String >> thongKe = new HashMap < String, ArrayList < String >> ();
    
    public static void main(String[] args) throws IOException {
        LoadThongKe();
        
        if(LoadTuDien()) { 
            while(true) {
                System.out.println("1. Tim kiem theo slang word.");
                System.out.println("2. Tim kiem theo definition.");
                System.out.println("3. Lich su tim kiem.");
                System.out.println("4. Them tu moi vao slang word.");
                System.out.println("5. Sua tu trong slang word.");
                System.out.println("6. Xoa tu trong slang word.");
                System.out.println("7. Reset danh sách slang word goc");
                System.out.println("8. Random 1 slang word");
                System.out.println("9. Do vui, Ramdom slang word");
                System.out.println("10. Do vui, Ramdom definition");
                System.out.print("Ban chon chuc nang (Nham 0 de thoat): ");  

                String chon = sc.nextLine();
                if (chon.equals("0")) {
                    System.out.println("Cam on da su dung!");
                    break;
                }
                else if (chon.equals("1")) {
                    String tratu;
                    do {
                        System.out.print("Ban muon tra tu (Bam 0 de tro lai): ");
                        tratu = sc.nextLine();
                        if(!tratu.equals("0")) {
                            String nghia = dictionarySD.get(tratu);
                            if(nghia != null) {
                                System.out.println("Nghia tu " + tratu + " (Slang) sang tu Definition la: " + nghia );
                                WriteHistory(tratu, nghia, "S");
                            }
                            else {
                                System.out.println("Tu nay chua co trong tu dien. Nhap 'THEM' de them vao hoac bam bat ki de tiep tuc.");
                                if(sc.nextLine().equals("THEM")) {
                                    System.out.println("Them thanh cong");
                                }
                            }
                        }
                    }
                    while (!tratu.equals("0")) ;            
                    System.out.println("-------THANKS-------");
                }
                else if (chon.equals("2")) {
                    String tratu;
                    do {
                        System.out.print("Ban muon tra tu (Bam 0 de tro lai): ");
                        tratu = sc.nextLine();
                        if(!tratu.equals("0")) {
                            String nghia = dictionaryDS.get(tratu);
                            if(nghia!= null) {
                                System.out.println("Nghia tu " + tratu + " (Definition) sang tu Slang la: " + nghia );
                                WriteHistory(tratu, nghia, "D");
                            }
                            else {
                                System.out.println("Tu nay chua co trong tu dien. Nhap 'THEM' de them vao hoac bam bat ki de tiep tuc.");
                                if(sc.nextLine().equals("THEM")) {
                                    System.out.println("Them thanh cong");
                                }
                            }
                        }
                    }
                    while (!tratu.equals("0")) ;            
                    System.out.println("-------THANKS-------");
                }
                else if(chon.equals("3")) {
                    String date;
                    do 
                    {
                        System.out.print("Lich su ngay muon xem (Format theo dinh dang dd/MM/yyyy) - Bam 0 de tro ve: ");
                        date = sc.nextLine();
                        if(!date.equals("0")) {
                            ArrayList<String> history = thongKe.get(date);
                            if(history == null) {
                                System.out.println("Khong co lich su tra cu hoac ban da nhap sai format");
                            }
                            else {
                                System.out.print("S: Slang - D: Definition - (Bat Ki de xem tat ca): ");
                                String loai = sc.nextLine();
                                XemThongKe(date, loai);
                            }
                        }
                    }
                    while (!date.equals("0"));
                }
                else {
                    System.out.println("Ban da chon sai roi. Moi chon lai.");
                }
            }
        }
        else {
            System.out.println("Load file tu dien khong thanh cong!");
        }
    }
    
    private static boolean LoadTuDien() {
        System.out.print("Vui long nhap duong dan file tu dien: ");
        String url = sc.nextLine();

        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;

        try {
            fileInputStream = new FileInputStream(url);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = bufferedReader.readLine();
            while (line != null) {
                dictionarySD.put(line.split("`")[0],line.split("`")[1]);
                dictionaryDS.put(line.split("`")[1],line.split("`")[0]);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileInputStream.close();
            return true;
        } catch (IOException ex) {
            System.out.print("Lõi");
            return false;
        } 
    }
    
    private static void LoadThongKe() throws IOException {
        File x = new File("History.txt");
        Scanner sc = new Scanner(x);

        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            String date = sc.nextLine();
            int n1 = Integer.parseInt(sc.nextLine());
            ArrayList < String > ds = new ArrayList < String > ();
            for (int j = 0; j < n1; j++) {
                ds.add(sc.nextLine());
            }
            thongKe.put(date, ds);
        }
        sc.close();
    }
    
    private static  void WriteHistory(String tu, String nghia, String kieu){
        try {
            LocalDate d1 = java.time.LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = d1.format(formatter);
            String tunghia = "[" + (kieu.equals("S") ? "Slang" : "Definition") + "] " + tu + " : " + nghia;
            
            if (thongKe.containsKey(date)) {
                if(thongKe.get(date).stream().filter(p -> p.equals(tunghia)).findAny().orElse(null) == null){
                    ArrayList < String > ds = thongKe.get(date);
                    ds.add(tunghia);
                    thongKe.replace(date, ds);
                }
            } else {
                ArrayList < String > ds = new ArrayList < String > ();
                ds.add(tunghia);
                thongKe.put(date, ds);
            }
            Formatter f = new Formatter("History.txt");
            f.format("%d\n", thongKe.size());
            Set entries = thongKe.entrySet();
            Iterator iter = entries.iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                String key = (String) entry.getKey();
                ArrayList < String > value = (ArrayList < String > ) entry.getValue();
                f.format("%s\n", key);
                f.format("%d\n", value.size());
                for (int i = 0; i < value.size(); i++) {
                    f.format("%s\n", value.get(i));
                }
            }
            f.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    private static void XemThongKe(String date, String loai) {
        ArrayList<String> tunghia = thongKe.get(date);
        if(loai.charAt(0) != 'S' && loai.charAt(0) != 'D') {
            for(int i = 0; i < tunghia.size(); i++) {
                System.out.println(tunghia.get(i));            
            }
        }
        else {
            for(int i = 0; i < tunghia.size(); i++) {   
                if(tunghia.get(i).charAt(1) == loai.charAt(0)) {
                    System.out.println(tunghia.get(i));            
                }
            }
        }
    }
}
