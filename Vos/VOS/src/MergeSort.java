

import javax.swing.*;
import java.awt.*;
import java.awt.color.ColorSpace;

class MergeSort extends JFrame implements Runnable{
  private int tot = 150;
    private int max = 450;
    private int[] my_array;
    private Thread th;
    private DrawPanel mypanel;
   
    int count = 0;

    public MergeSort(){
        
        setSize(tot*4 + 100, max + 400);
        setLayout(new GridLayout(1, 1));
         
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MERGE SORT");
        addpanel();
        
    }
    public void addpanel()
    {
        mypanel=new DrawPanel();
        getContentPane().add(mypanel); 
        callthread();
    
    }
    public void callthread()
    {
        my_array = generaterandArray(tot, max);
        th = new Thread(this); 
        th.start(); 
        
    }

    
    public static int[] generaterandArray(int N, int max){
        int[] my_array = new int[N];
        for(int i = 0; i < N; i++){
            my_array[i] = (int) (Math.random() * max);
        }
        return my_array;
    }
  private int[] temp;
    private int len;
 
    
    private void my_MergeSort(int low, int high) {
         
        if (low < high) {
            int mid = low + (high - low) / 2;
            
            my_MergeSort(low, mid);
            
            my_MergeSort(mid + 1, high);
            
            mergeintoone(low, mid, high);
        }
    }
 
    private void mergeintoone(int low, int mid, int high) {

        for (int i = low; i <= high; i++) {
            temp[i] = my_array[i];
count++;      
            try{
                th.sleep(50);
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
            mypanel.repaint();
       System.out.println("the array after pass:" );
       for(int k=0;k<my_array.length;k++)
       {
           System.out.print(my_array[k] + " ");
       }
       
       System.out.println();
            
       
       System.out.println();
        
        
        }
        int i = low;            
        int j = mid + 1;
        int m = low;
        while (i <= mid && j <= high) {
 
            if (temp[i] <= temp[j]) {

                my_array[m] = temp[i];
                i++;
            } else {
                my_array[m] = temp[j];
                j++;
            }
            m++;
        }
        while (i <= mid) {
 
            my_array[m] = temp[i];
            m++;
            i++;
        }
 
    }

    @Override
    @SuppressWarnings("empty-statement")
    public void run(){
        {
        
        count++;  
        this.my_array = my_array;
        this.len = my_array.length;
        this.temp = new int[len];
        my_MergeSort(0, len - 1);
          
            
            try{
                th.sleep(15);
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }
            mypanel.repaint();
         System.out.println("the array after pass:" );
       for(int k=0;k<my_array.length;k++)
       {
           System.out.print(my_array[k] + " ");
       }
       
       System.out.println();
            
        }
        
        }
    
    
    class DrawPanel extends JPanel{            
        public void paintComponent(Graphics g){
            super.paintComponent(g); 
            
            g.setColor(Color.DARK_GRAY); 
            
            
            
            
            for(int i = 0; i < my_array.length; i++){
            
                
                g.fillRoundRect(i*5 +60,getHeight()- my_array[i], 8, my_array[i], i, 0);
                g.setColor(Color.blue); 
            }
            }
    }
    
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new MergeSort().setVisible(true);
            }
        });
    }
}