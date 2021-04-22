/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg19424031_slang_word;

import java.io.*;
import java.util.*;

/**
 *
 * @author nguye
 */
public class Main {
 

    private static Scanner sc = new Scanner(System.in);
    private static Map < String, String > dictionarySD = new HashMap < String, String > ();
    private static Map < String, String > dictionaryDS = new HashMap < String, String > ();    
    
    public static void main(String[] args) {
        
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
                    System.out.print("Ban muon tra tu: ");
                    String tratu = sc.nextLine();
                    if(dictionarySD.get(tratu)!= null) {
                        System.out.println("Nghia tu " + tratu + " (Slang) sang tu Definition la: " + dictionarySD.get(tratu) );
                    }
                    else {
                        System.out.println("Tu nay chua co trong tu dien. Nhap 'THEM' de them vao hoac bam bat ki de tiep tuc.");
                        if(sc.nextLine().equals("THEM")) {
                            System.out.println("Them thanh cong");
                        }
                    }
                    System.out.println("-------THANKS-------");
                }
                else if (chon.equals("2")) {
                    System.out.print("Ban muon tra tu: ");
                    String tratu = sc.nextLine();
                    if(dictionaryDS.get(tratu)!= null) {
                        System.out.println("Nghia tu " + tratu + " (Definition) sang tu Slang la: " + dictionaryDS.get(tratu) );
                    }
                    else {
                        System.out.println("Tu nay chua co trong tu dien. Nhap 'THEM' de them vao hoac bam bat ki de tiep tuc.");
                        if(sc.nextLine().equals("THEM")) {
                            System.out.println("Them thanh cong");
                        }
                    }
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
    
    public static boolean LoadTuDien() {
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
}
