

import javax.swing.*;
import java.awt.*;

class InsertionSort extends JFrame implements Runnable{
    private int tot = 150;
    private int max = 450;
    private int[] my_array;
    private Thread th;
    private DrawPanel mypanel;
   
    int count = 0;

    public InsertionSort(){
        
        setSize(tot*4 + 100, max + 400);
        setLayout(new GridLayout(1, 1));
         
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("INSERTION SORT");
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

    @Override
    @SuppressWarnings("empty-statement")   public void run(){
      for (int p = 1; p < tot; p++) {
       count++; 
          int val = my_array[p]; 
            int i = p-1;
            while ( (i > -1) && ( my_array [i] > val ) ) {
                my_array [i+1] = my_array [i]; 
                i--;
            }
            my_array[i+1] = val; 
        
      
try{
                th.sleep(150);
            }catch(InterruptedException ie){
                ie.printStackTrace();
            }   mypanel.repaint();
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
                g.setColor(Color.blue);             }
            }
    }
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new InsertionSort().setVisible(true);
            }
        });
    }
}