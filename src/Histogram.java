/**
 * Created by Frank Hall on 5/15/2016.
 */
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Histogram extends JFrame {

    private DisplayArrayPanel displayArrayPanel1 = new DisplayArrayPanel();
    private int[] numbers1 = initializeNumbers();
    private DisplayArrayPanel displayArrayPanel2 = new DisplayArrayPanel();
    private int[] numbers2 = numbers1.clone();
    private DisplayArrayPanel displayArrayPanel3 = new DisplayArrayPanel();
    private int[] numbers3 = numbers1.clone();
    final int PAUSE_TIME = 250;

    public Histogram() {
        super("Sorting Histogram");
        setLayout(new GridLayout(1, 4));

        displayArrayPanel1.setNumbers(numbers1);
        JPanel panel1 = new JPanel(new BorderLayout());
        panel1.add(new JLabel("Selection Sort", JLabel.CENTER), BorderLayout.NORTH);
        panel1.add(displayArrayPanel1, BorderLayout.CENTER);
        add(panel1);

        displayArrayPanel2.setNumbers(numbers2);
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(new JLabel("Insertion Sort", JLabel.CENTER), BorderLayout.NORTH);
        panel2.add(displayArrayPanel2, BorderLayout.CENTER);
        add(panel2);

        displayArrayPanel3.setNumbers(numbers3);
        JPanel panel3 = new JPanel(new BorderLayout());
        panel3.add(new JLabel("Bubble Sort", JLabel.CENTER), BorderLayout.NORTH);
        panel3.add(displayArrayPanel3, BorderLayout.CENTER);
        add(panel3);

        new Thread (new Runnable(){
            @Override
            public void run(){
                selectionSort(numbers1);
            }
        }).start();

        new Thread (new Runnable(){
            @Override
            public void run(){
                insertionSort(numbers2);
            }
        }).start();

        new Thread (new Runnable(){
            @Override
            public void run(){
                bubbleSort(numbers3);
            }
        }).start();
    }

    // methods for sorting the arrays
    public void selectionSort(int[] list) {
        int i, j, first, temp;
        for (i = list.length - 1; i >= 0; i--) {
            first = 0;   //initialize to subscript of first element
            for (j = i; j >= 0; j--) //locate smallest element between positions 1 and i.
            {
                if (list[j] > list[first]) {
                    first = j;
                }

            }
            temp = list[first];   //swap smallest found with element in position i.
            list[first] = list[i];
            list[i] = temp;

            try{
                Thread.sleep(500);
            }catch(InterruptedException e) {}
            displayArrayPanel1.repaint();
        }
    }

    // methods for sorting the arrays
    public void insertionSort(int[] list) {
        int temp;		// this variable is the element of the unsorted array currently
        // being compared to elements of the sorted array
        int pos;		// this variable keeps track of where in the sorted array the
        // comparison takes place

        for (int i = 1; i < list.length; i++)		// loop through the unsorted array
        {
            // (the first element is considered sorted)
            temp = list[i];			// grab the first element of the unsorted array
            pos = i - 1;				// get the index of the last sorted element

            while ((pos >= 0) && (temp < list [pos]))	// while the current sorted element is greater than temp
            {
                list[pos + 1] = list[pos];		// keep shifting sorted elements back by 1
                pos--;					// decrement the pos index
                try{
                    Thread.sleep(250);
                }catch(InterruptedException e) {}
                displayArrayPanel2.repaint();
            }
            list[pos + 1] = temp;				// insert temp such that array[pos] <= temp < array[pos + 2]
            try{
                Thread.sleep(250);
            }catch(InterruptedException e) {}
            displayArrayPanel2.repaint();
        }
    }

    // methods for sorting the arrays
    public void bubbleSort(int[] list) {
        int temp;

        for(int i = 0; i < list.length; i++)
        {
            for(int j = 1; j < list.length; j++)
            {
                //if numbers[j-1] > numbers[j], swap the elements
                if(list[j-1] > list[j])
                {
                    temp = list[j-1];
                    list[j-1] = list[j];
                    list[j] = temp;
                }
                try{
                    Thread.sleep(250);
                }catch(InterruptedException e) {}
                displayArrayPanel3.repaint();
            }
            try{
                Thread.sleep(250);
            }catch(InterruptedException e) {}
            displayArrayPanel3.repaint();
        }


    }


    public int[] initializeNumbers() {
        int[] numbers = new int[50];
        ArrayList<Integer> list = new ArrayList<Integer>();
        int x;
        for (x = 1; x < 51; ++x) {
            list.add(x);
        }
        Collections.shuffle(list);
        Object[] objects = list.toArray();
        for (x = 0; x < 50; ++x) {
            numbers[x] = Integer.parseInt(objects[x].toString());
        }
        return numbers;
    }

    public static void main(String[] args) {
        Histogram frame = new Histogram();
        frame.setSize(600, 160);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);// center the frame
        frame.setVisible(true);
    }
}
class DisplayArrayPanel extends JPanel {

    int[] numbers;

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //find max integer
        int max = numbers[0];
        for (int i = 0; i < numbers.length; ++i) {
            if (max < numbers[i]) {
                max = numbers[i];
            }
        }
        int height = (int) (getSize().height + 0.98);
        int width = getSize().width;
        int unitWidth = (int) (width * 1.0 / numbers.length);
        for (int i = 0; i < numbers.length; ++i) {
            g.drawRect((int) (i * unitWidth), getSize().height - (int) ((numbers[i] * 1.0 / max) * height), (int) (unitWidth), getSize().height);
        }
    }
}
