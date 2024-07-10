/*
  GROUP TY-31 CSB:
  Roll nos and Name of Group Members:
  53- Vyankatesh Kulkarni
  55- Kunal Chaudhari
  56- Ayush Kurapati
  63- Shrihari Mangle
*/

/*
  Input File Contents- 
  $AMJ00010003001
GD10GD20PD10PD20H
$DTA
VIT
VIIT
$END001
$AMJ000100030001
GD20GD30GD40GD50LR20CR30BT09PD50HPD40
H000
$DTA
VIT
VIT
IS SAME
NOT SAME
$END002
$AMJ00200080003
GD20PD20GD30PD30LR30SR20PD20H
$DTA
CAT CAN EAT RAT
RAT CAN NOT EAT CAT
$END0003
$AMJ00100040001
GD20LR22SR25PD20H
$DTA
I LIKE THIS HAT OF
$END0004
$AMJ000700600005
GD50LR50SR67LR51SR66LR52SR65LR53SR64LR54
SR63LR55SR62LR56SR61LR57SR60PD50PD60H000
$DTA
   N   U   T   R   A   F   I   N
$END0005
$AMJ00200070002
GD20LR26CR20BT06GD30PD30PD20H
$DTA
RAM  IS OLDER THAN  SHRIRAM
NOT IN EXISTANCE
$END0006
$AMJ020200160005
GD20PD20LR20SR30SR31PD30SR40SR41SR42PD40
SR50SR51PD50SR60PD60H
$DTA
*
$END0007
*/

/*
    Output File Contents

VIT                                     
VIIT                                    


IS SAME                                 


CAT CAN EAT RAT                         
RAT CAN NOT EAT CAT                     
RAT CAN EAT RAT                         


I LIKE THIS HAT OF  HIS                 


   N   U   T   R   A   F   I   N        
   N   I   F   A   R   T   U   N        


NOT IN EXISTANCE                        
RAM  IS OLDER THAN  SHRIRAM             


*                                       
*   *                                   
*   *   *                               
*   *                                   
*                                       

*/

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.IOException;

class CPU {
    char IR[] = new char[4];
    char R[] = new char[4];
    int IC = 0;
    int SI = 0;
    int tempcounter = 0; //read purpose
    int pcounter = 1;
    boolean C = false;
    char M[][] = new char[100][4];
    char buf[] = new char[40];

    public void init() {
        char IR1[] = new char[4];
        char R1[] = new char[4];
        char M1[][] = new char[100][4];
        char buf1[] = new char[40];

        for (int i = 0; i < 4; i++) {
            IR1[i] = '\0';
            R1[i] = '\0';
        }
        for (int i = 0; i < 40; i++) {
            buf1[i] = '\0';
        }
        this.IC = 0;
        this.SI = 0;
        this.tempcounter = 0;
        this.C = false;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 4; j++) {
                M1[i][j] = '\0';
            }
        }
        this.M = M1;
        this.buf = buf1;
        this.IR = IR1;

    }

    public void LOAD() {
        int m = 0;

        try {
            File file = new File("D:\\OS\\Course Project\\Input.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                int len = data.length();
//                System.out.println(data);
                char arr[] = data.toCharArray();

                if (data.length() > 4 && data.substring(0, 4).equals("$AMJ")) {
                    String data1 = reader.nextLine();
                    char arr1[] = data1.toCharArray();

                    for (int i = 0; i < arr1.length; i++) {
                        this.buf[i] = arr1[i];
                    }
                    int l = 0;
                    int k = m + 10;
                    for (int i = m; i < k; i++) {
                        int j = 0;
                        while (l < 40) {
                            if (this.buf[l] == 'H') {
                                this.M[i][j] = this.buf[l];
                                l++;
                                this.M[i][j + 1] = '0';
                                this.M[i][j + 2] = '0';
                                this.M[i][j + 3] = '0';
                                break;
                            }
                            this.M[i][j] = this.buf[l];
                            l++;
                            j++;
                            if (j % 4 == 0) {
                                break;
                            }
                        }
                    }
                    m = m + 10;

                } else if (data.length() == 4 && data.substring(0, 4).equals("$DTA")) {
                    MOS_START();
                } else if (data.length() > 4 && data.substring(0, 4).equals("$END")) {
                    System.out.println("--------------------------------Job " + this.pcounter + " Done!!!------------------------------------");
                    init();
                    m = 0;
                    this.pcounter++;
                } else {
                    char arr1[] = data.toCharArray();
                    for (int i = 0; i < 40; i++) {
                        this.buf[i] = ' ';
                    }
                    for (int i = 0; i < arr1.length; i++) {
                        this.buf[i] = arr1[i];
                    }
                    int l = 0;
                    int k = m + 10;
                    for (int i = m; i < k; i++) {
                        int j = 0;
                        while (l < 40) {
                            this.M[i][j] = this.buf[l];
                            l++;
                            j++;
                            if (j % 4 == 0) {
                                break;
                            }
                        }
                    }
                    m = m + 10;
                }

            }

            reader.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void MOS_START() {
        IC = 0;
        EXECUTEUSERPROGRAM(IC);
    }

    public void MOS(int SI) {
        String ir0 = "";
        String ir3 = "";
//        ir0 = IR[0] + "" + IR[1];
//        ir3 = IR[2] + "" + IR[3];
        switch (SI) {
            case 1:
                ir0 = this.IR[0] + "" + this.IR[1];
                ir3 = this.IR[2] + "" + this.IR[3];
                READ(ir0, ir3);
                //EXECUTEUSERPROGRAM(++IC);
                break;
            case 2:
                ir0 = this.IR[0] + "" + this.IR[1];
                ir3 = this.IR[2] + "" + this.IR[3];
                WRITE(ir0, ir3);
                break;
            case 3:
                Terminate();
                break;

            default:
                break;
        }
    }

    public void Terminate() {
        try {
            FileWriter f = new FileWriter("D:\\OS\\Course Project\\Output.txt", true);

            f.write("\n\n");

            f.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void READ(String ir0, String ir3) {
        this.IR[3] = '0';

        try {
            File file = new File("D:\\OS\\Course Project\\Input.txt");
            Scanner reader = new Scanner(file);
            int y = 0;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String check = "$DTA";
                if (check.equals(data)) {
                    y++;
                }

                if (y == this.pcounter) {
                    break;
                }
            }
            String data = "";
            for (int i = 0; i < tempcounter; i++) {
                data = reader.nextLine();
            }

            char darr[] = data.toCharArray();

            int d_ir3 = Integer.parseInt(ir3);

            int k = 0, f = 0;

            for (int i = d_ir3; i < d_ir3 + 10; i++) {
                for (int j = 0; j < 4; j++) {

                    this.M[i][j] = darr[k];

                    if (k == darr.length - 1) {
                        f = 1;
                        break;
                    }
                    k++;
                }
                if (f == 1)
                    break;
            }

            for (int i = 0; i < 100; i++) {
                System.out.print("M[" + i + "] - ");
                for (int j = 0; j < 4; j++) {
                    System.out.print(this.M[i][j] + " ");
                }
                System.out.println();
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void WRITE(String ir0, String ir3) {
        this.IR[3] = '0';
        int d1_ir3 = Integer.parseInt(ir3);
        try {

            String str = "";
            for (int i = d1_ir3; i < d1_ir3 + 10; i++) {
                for (int j = 0; j < 4; j++) {
                    str += this.M[i][j];
                }
            }

            FileWriter f = new FileWriter("D:\\OS\\Course Project\\Output.txt", true);

            f.write(str + "\n");

            f.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public void EXECUTEUSERPROGRAM(int IC) {
        //System.out.println(IC);
        String ir0 = "";
        String ir3 = "";

        for (int j = 0; j < 4; j++) {
            this.IR[j] = this.M[IC][j]; // h 0 0 0
        }

        ir0 = this.IR[0] + "" + this.IR[1]; //h0
        ir3 = this.IR[2] + "" + this.IR[3]; //00


        switch (ir0) {
            case "LR":
                int l = Integer.parseInt(ir3);
                for (int j = 0; j < 4; j++) {
                    this.R[j] = this.M[l][j];
                }
                EXECUTEUSERPROGRAM(++IC);
                break;
            case "SR":
                int x = Integer.parseInt(ir3);
                //System.out.println(x);
                for (int j = 0; j < 4; j++) {
                    this.M[x][j] = this.R[j];
                }
                /*for (int i = 0; i < 100; i++) {
                    System.out.print("M[" + i + "] - ");
                    for (int j = 0; j < 4; j++) {
                        System.out.print(this.M[i][j] + " ");
                    }
                }*/
                EXECUTEUSERPROGRAM(++IC);
                break;
            case "CR":
                int y = Integer.parseInt(ir3);
                char p1[] = new char[4];
                for (int k = 0; k < 4; k++) {

                    p1[k] = M[y][k];
                }
                if (Arrays.equals(R, p1)) {
                    this.C = true;
                } else {
                    this.C = false;
                }
                EXECUTEUSERPROGRAM(++IC);
                break;
            case "BT":
                int s1 = Integer.parseInt(ir3);
                if (this.C == true) {
                    IC = s1;
                    EXECUTEUSERPROGRAM(IC);
                } else {
                    EXECUTEUSERPROGRAM(++IC);
                }
                break;
            case "GD":
                this.SI = 1;
                this.tempcounter++;
                MOS(this.SI);
                this.SI = 0;
                EXECUTEUSERPROGRAM(++IC);
                break;
            case "PD":
                this.SI = 2;
                MOS(this.SI);
                EXECUTEUSERPROGRAM(++IC);
                break;
            case "H0":
                this.SI = 3;
                MOS(this.SI);
                //Terminate();
                break;

        }

    }

}

public class Phase1 {

    public static void main(String[] args) throws IOException {
        CPU cpu = new CPU();
        FileWriter f = new FileWriter("D:\\OS\\Course Project\\Output.txt");
        cpu.LOAD();

    }
}
