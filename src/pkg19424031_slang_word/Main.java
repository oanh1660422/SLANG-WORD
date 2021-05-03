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
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        if(LoadTuDien(false)) { 
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
                                    if(ThemTu(tratu)) {
                                        System.out.print("Ban co muon cap nhat vao file tu dien ?. Nhan YES de luu, hoac bat ki de thoat: ");
                                        if(sc.nextLine().equals("YES"))
                                        {
                                            LuuFile();
                                            System.out.println("Da cap nhat file.");
                                        }
                                    }
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
                                    if(ThemTu("")) {
                                        System.out.print("Ban co muon cap nhat vao file tu dien ?. Nhan YES de luu, hoac bat ki de thoat: ");
                                        if(sc.nextLine().equals("YES"))
                                        {
                                            LuuFile();
                                            System.out.println("Da cap nhat file.");
                                        }
                                    }
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
                    System.out.println("-------THANKS-------");
                }
                else if (chon.equals("4")) {
                    String tieptuc ;
                    boolean check = false;
                    do 
                    {
                        check = ThemTu("");
                        System.out.print("Ban co muon them tu nua khong ?: Nhan YES de tiep tuc, hoac bat ki de thoat: ");
                        tieptuc = sc.nextLine();
                    }
                    while (tieptuc.equals("YES"));
                    
                    if(check) {
                        System.out.print("Ban co muon cap nhat vao file tu dien ?. Nhan YES de luu, hoac bat ki de thoat: ");
                        tieptuc = sc.nextLine();
                        if(tieptuc.equals("YES"))
                        {
                            LuuFile();
                            System.out.println("Da cap nhat file.");
                        }
                    }
                    System.out.println("-------THANKS-------");
                }
                else if (chon.equals("5")) {
                    String tieptuc;
                    boolean check = false;
                    do 
                    {
                        check = SuaTu("");
                        System.out.print("Ban co muon sua tu nua khong ?: Nhan YES de tiep tuc, hoac bat ki de thoat: ");
                        tieptuc = sc.nextLine();
                    }
                    while (tieptuc.equals("YES"));
                    if(check) {
                        System.out.print("Ban co muon cap nhat vao file tu dien ?. Nhan YES de luu, hoac bat ki de thoat: ");
                        tieptuc = sc.nextLine();
                        if(tieptuc.equals("YES"))
                        {
                            LuuFile();
                            System.out.println("Da cap nhat file.");
                        }
                    }
                    System.out.println("-------THANKS-------");
                }
                else if (chon.equals("6")) {
                    String tieptuc;
                    int sl = dictionarySD.size();
                    
                    do 
                    {
                        XoaTu();
                        System.out.print("Ban co muon xoa tu nua khong ?: Nhan YES de tiep tuc, hoac bat ki de thoat: ");
                        tieptuc = sc.nextLine();
                    }
                    while (tieptuc.equals("YES"));
                    
                    if(sl != dictionarySD.size()) {
                        System.out.print("Ban co muon cap nhat vao file tu dien ?. Nhan YES de luu, hoac bat ki de thoat: ");
                        tieptuc = sc.nextLine();
                        if(tieptuc.equals("YES"))
                        {
                            LuuFile();
                            System.out.println("Da cap nhat file.");
                        }
                    }
                    System.out.println("-------THANKS-------");
                }
                else if(chon.equals("7")) {
                    System.out.print("Nhap YES de co the refresh lai tu dien goc, hoac bat ki de thoat: ");
                    if(sc.nextLine().equals("YES")) {
                        LoadTuDien(true);
                        LuuFile();
                        System.out.println("Da Refresh thanh cong.");
                        System.out.println("-------THANKS-------");
                    }
                }
                else if(chon.equals("8")) {
                    do {
                        Set<String> keySet = dictionarySD.keySet();
                        List<String> keyList = new ArrayList<>(keySet);

                        int randIdx = new Random().nextInt(keyList.size());

                        String randomKey = keyList.get(randIdx);
                        String randomValue = dictionarySD.get(randomKey);

                        System.out.println("Random: " + randomKey + " (Slang) = " + randomValue + " (Definition)" );
                        System.out.print("Nhap YES de tiep tuc, hoac bat ki de thoat: ");
                    }
                    while(sc.nextLine().equals("YES"));
                    System.out.println("-------THANKS-------");
                }
                else if (chon.equals("9")) {
                    int diem = 0;
                    Set<String> keySet = dictionarySD.keySet();
                    List<String> keyList = new ArrayList<>(keySet);
                    do {                        
                        if(TroChoiDoVui(keyList, true))
                        {
                            diem++;
                            System.out.println("Ban da tra loi dung lien tiep " + diem + " cau.");
                        }
                        else {
                            diem = 0;
                            System.out.println("Ban da tra loi sai.");
                        }
                        System.out.print("Nhap 1 de tiep tuc, hoac bat ki de thoat: ");
                    } while(sc.nextLine().equals("1"));
                    System.out.println("-------THANKS-------");
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
    
    private static boolean LoadTuDien(boolean refresh) {
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;

        try {
            if(refresh) {
                dictionarySD.clear();
                dictionarySD.clear();
            }
            fileInputStream = new FileInputStream(refresh ? "slangBackUp.txt" : "slang.txt");
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
           int dem = 0;
            for(int i = 0; i < tunghia.size(); i++) {   
                if(tunghia.get(i).charAt(1) == loai.charAt(0)) {
                    System.out.println(tunghia.get(i));  
                    dem++;
                }
            }
            if(dem == 0) {
                System.out.println("Khong co lich su tra cuu");
            }
        }
    }
    
    private  static boolean ThemTu(String tu) {
        if(tu.equals("")) {
            System.out.print("Moi nham tu (Slang): ");
            tu = sc.nextLine();
        }
        if(dictionarySD.get(tu) == null) {
            System.out.print("Moi nham nghia (Definition): ");
            String nghia = sc.nextLine();
            dictionarySD.put(tu, nghia);
            dictionaryDS.put(nghia, tu);
            System.out.println("Them thanh cong");
            return true;
        }
        else {
            System.out.print("Tu nay da co trong tu dien. Nhan CN de co the cap nhat hoac bat ki de thoat: ");
            String luachon = sc.nextLine();
            if(luachon.equals("CN")) {
                return SuaTu(tu);
            }
            return false;
        }
    }
    
    private  static  void XoaTu() {
        System.out.print("Moi nham tu (Slang) muon xoa: ");
        String tu = sc.nextLine();
        if(dictionarySD.get(tu) == null) {
            System.out.println("Tu nay khong co trong tu dien.");  
        }
        else {
            System.out.print("Nhan YES de xoa tu " + tu + " (Slang) = " + dictionarySD.get(tu) + " (Definition), hoac bat ki de thoat: ");
            if(sc.nextLine().equals("YES")) {
                dictionaryDS.remove(dictionarySD.get(tu));
                dictionarySD.remove(tu);
                System.out.println("Xoa thanh cong");
            }
        }
    }
    
     private  static boolean SuaTu(String tucapnhat) {
        if(tucapnhat.equals("")) {
            System.out.print("Moi nham tu (Slang) muon sua: ");
            tucapnhat = sc.nextLine();
        }
        if(dictionarySD.get(tucapnhat) == null) {
            System.out.println("Tu nay khong co trong tu dien.");  
            return false;
        }
        else {
            dictionaryDS.remove(dictionarySD.get(tucapnhat));
            System.out.print("Moi nham nghia (Definition): ");
            String nghia = sc.nextLine();
            
            dictionarySD.replace(tucapnhat, nghia);
            dictionaryDS.put(nghia, tucapnhat);
 
            System.out.println("Cap nhat thanh cong");
            return true;
        }
    }
    
    private static void LuuFile() {
        BufferedOutputStream bout = null;
        FileOutputStream fout = null;
        try {
            fout = new FileOutputStream("Slang.txt");
            bout = new BufferedOutputStream(fout);
            for (Map.Entry<String, String> item : dictionarySD.entrySet()) {
                String s=  item.getKey() + "`" + item.getValue() + "\n";
                byte b[] = s.getBytes();
                bout.write(b);
                            bout.flush();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                bout.close();
                fout.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }           
        }
    }
    
    private static boolean  TroChoiDoVui(List<String> keyList, boolean  Slang) {
        List<DoVui> list = new ArrayList<DoVui>();
        List check = new ArrayList();
        int[] dapan = new int[4];
        String cauhoi = "";

        for(int i = 0; i < 4; i++) {
            int randIdx = new Random().nextInt(keyList.size());
            if(i == 0) {
                cauhoi = keyList.get(randIdx);
                check.add(randIdx);
                DoVui dv = new DoVui();
                dv.setNghia(Slang ? dictionarySD.get(cauhoi): dictionaryDS.get(cauhoi) );
                dv.setDapAn(true);
                list.add(dv);
            }
            else {
                if(check.stream().filter(p -> p.equals(randIdx)).findAny().orElse(null) == null) {
                    check.add(randIdx);
                    String ranDValue = keyList.get(randIdx);
                    DoVui dv = new DoVui();
                    dv.setNghia(Slang ? dictionarySD.get(ranDValue): dictionaryDS.get(ranDValue));
                    dv.setDapAn(false);
                    list.add(dv);
                }
                else {
                    i--;
                }
            }
        }
        System.out.println("Dap an cua " + cauhoi + " la: ");
        check = new ArrayList();
        for(int i = 0; i < 4; i++) {
            int randIdx = new Random().nextInt(4);
            if(check.stream().filter(p -> p.equals(randIdx)).findAny().orElse(null) == null) {
                check.add(randIdx);
                dapan[i] = list.get(randIdx).getDapAn() ? 1 : 0;
                System.out.println(i+1 + ". " + list.get(randIdx).getNghia());
            }
            else {
                i--;
            }
        }
        System.out.print("Dap an cua ban la: ");
        if(dapan[Integer.parseInt(sc.nextLine())-1] == 1) {
            return true;
        }
        else {
            return false;
        }
    }
}
