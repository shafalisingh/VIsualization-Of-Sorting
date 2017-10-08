
import javax.swing.*;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.io.*;
import java.util.*;

class RealDataSetMerge extends JFrame implements Runnable{
    private int tot = 100;
    private int max = 500;
    private int[] my_array;
    private Thread th;
    private DrawPanel mypanel;

    int count = 0;

    public RealDataSetMerge(){
        //This is to initialise everything
        setSize(tot*5 + 100, max + 300);//first we set the window size
        setLayout(new GridLayout(1, 1));//we set the layout

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // we set  the default operation so that the program terminates
        setTitle("MERGE SORT");
        addpanel();

    }
    public void addpanel()
    {
        mypanel = new DrawPanel();//This draws the panel window which we display
        getContentPane().add(mypanel); // adds the display panel to the display content window
        callthread();

    }


    public java.util.List<Integer> readfromcsv() {

        String csvFile = "/Users/tarun/Downloads/Prices.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = " ";
        java.util.List<Integer> myArrayList = new ArrayList<Integer>();
        int my_array1[] = new int[1000];
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {


                String[] money = line.split(cvsSplitBy);
                String mny[] = money[0].split(" ");

                for (String str : mny) {

                    myArrayList.add(Integer.parseInt(str));


                }


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return myArrayList;

    }
    public void callthread()
    {
        java.util.List<Integer> elem=readfromcsv();
        my_array = new int[elem.size()];
        for (int i=0; i < my_array.length; i++)
        {
            my_array[i] = elem.get(i).intValue()/500;

        }
        th = new Thread(this);
        th.start(); //starts the thread

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
                th.sleep(3);
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
                th.sleep(10);
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
            super.paintComponent(g); // Paints the content pane with the graphics object

            g.setColor(Color.DARK_GRAY); // sets the background of the graphics object.




            for(int i = 0; i < tot; i++){

                //this loop fills the object with bars corresponding to the values of the array numbers
                g.fillRoundRect(i*5 +60,getHeight()- my_array[i], 8, my_array[i], i, 0); //fills it
                g.setColor(Color.blue); // sets the color of the bar
            }
        }
    
    }
    
    public static void main(String[] args){
        //we run the thread inside main.
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new RealDataSetMerge().setVisible(true);
            }
        });
    }
}
     
