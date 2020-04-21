/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SHADOW
 */
public class classification {

    
    public static void main(String[] args) {
        int[][] tab=new int[][]{{4,3,1},{3,3,6},{1,1,2}};
        int[][] tab1=new int[][]{{-6,-3},{-3,-2}};
        //get_class2(tab1);
        get_class3(tab);
    }
    private static void get_class2(int[][] tab) {
        int[] a=new int[]{0,0,0};
        a[0]=tab[0][0];
        a[1]=tab[1][1];
        a[2]=(tab[0][0]*tab[1][1])-(tab[1][0]*tab[0][1]);
        System.out.println(a[0]+" "+a[1]+" "+a[2]);
        if (a[0]<=0 && a[1]<=0 && a[2]>=0) {
            
            if (a[0]<0 &&  a[2]>0) System.out.println("Class difini negatif");
            else System.out.println("Class semi difini negatif");
            
        }else if (a[0]>=0 && a[1]>=0 && a[2]>=0) {
            
            if (a[0]>0 && a[2]>0) System.out.println("Class difini positif");
            else System.out.println("Class semi difini positif");
            
        }else System.out.println("Class indifini");
    }

    private static void get_class3(int[][] tab) {
       int[] a=new int[]{0,0,0,0,0,0,0};
       a[0]=tab[0][0];
       a[1]=tab[1][1];
       a[2]=tab[2][2];
       
       
       a[3]=(tab[1][1]*tab[2][2])-(tab[2][1]*tab[1][2]);
       a[4]=(tab[0][0]*tab[2][2])-(tab[0][2]*tab[2][0]);
       a[5]=(tab[0][0]*tab[1][1])-(tab[1][0]*tab[0][1]);
       
       
       a[6]=tab[0][0]*((tab[1][1]*tab[2][2])-(tab[2][1]*tab[1][2]))+(-tab[0][1]*((tab[1][0]*tab[2][2])-(tab[1][2]*tab[2][0])))+(tab[0][2]*((tab[1][0]*tab[2][1])-(tab[2][0]*tab[1][1])));

       
       
       int neg=0,pos=0,nul=0;
       
       for(int i=0;i<a.length;i++){
           System.out.print(" det"+i+":"+a[i]+" ");
           if(a[i]==0) nul++;
           if(a[i]<0) neg++;
           if(a[i]>0) pos++;
       }
       System.out.println("negatif="+neg+" positif "+pos+" null:"+nul);
       
       if (pos==a.length) System.out.println("Class difini positif");
       else if(neg==a.length) System.out.println("Class difini negatif");
       else if((neg+nul)==a.length) System.out.println("Class semi difini negatif");
       else if((pos+nul)==a.length) System.out.println("Class semi difini positif");
       else System.out.println("Class indifini");
    }
}
