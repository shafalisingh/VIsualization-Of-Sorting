
import javax.swing.*;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.io.*;
import java.util.*;

class DataSetSel extends JFrame implements Runnable{
    private int tot = 100;
    private int max = 500;
    private int[] my_array;
    private Thread th;
    
    private DrawPanel mypanel;

    int count = 0;

    public DataSetSel(){
        //This is to initialise everything
        setSize(tot*5 + 100, max + 300);//first we set the window size
        setLayout(new GridLayout(1, 1));//we set the layout

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // we set  the default operation so that the program terminates
        setTitle("INSERTION SORT");
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
            my_array[i] = elem.get(i).intValue()/80;

        }
        th = new Thread(this);
        th.start(); //starts the thread

    }




    @Override
    @SuppressWarnings("empty-statement")
    public void run(){
    	   for (int i = 0; i < my_array.length - 1; i++)
           {
               count++; 
               int pos = i;
               for (int j = i + 1; j < my_array.length; j++)
               
                   if (my_array[j] < my_array[pos])
                       pos = j; 
         
               int less = my_array[pos]; 
               my_array[pos] = my_array[i];
               my_array[i] = less;
           
               
          try{
                   th.sleep(30);
               }
          catch(InterruptedException ie){
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
                new DataSetSel().setVisible(true);
            }
        });
    }
}
     