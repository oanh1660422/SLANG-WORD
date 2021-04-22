/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg19424031_slang_word;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author nguye
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    
    
    public static void main(String[] args) {
        
        
        String url = "D:\\slang.txt";

        // Đọc dữ liệu từ File với BufferedReader
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        ArrayList a = new ArrayList();

        try {
            fileInputStream = new FileInputStream(url);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = bufferedReader.readLine();
            while (line != null) {
                a.add(line);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileInputStream.close();
        } catch (IOException ex) {
            System.out.print("Lõi");
        } 
        
        Scanner sc = new Scanner(System.in);
        
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

        int chon = sc.nextInt();
        switch (chon) {
            case 0:
                System.out.println("Cam on thay da su dung!");
                break;
            case 1:
                System.out.println(a.get(152));
                break;
            case 2:
                System.out.println("2");
                break;
            case 3:
                System.out.println("2");
                break;
            case 4:
                System.out.println("2");
                break;
            case 5:
                System.out.println("2");
                break;
            case 6:
                System.out.println("2");
                break;
            case 7:
                System.out.println("2");
                break;
            case 8:
                System.out.println("2");
                break;
            case 9:
                System.out.println("2");
                break;
            case 10:
                System.out.println("2");
                break;
            default:
                System.out.println("Chon sai, xin chon lai");
                break;
        }
         
    }
   
}
