
import java.util.HashSet;
import javax.swing.JTextArea;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Anthony
 */
public class Philosopher implements Runnable {

    public boolean eating;
    public Thread pThread;
    public int philID;
    private Fork leftF, rightF;
    private int pDelay = 1;
    private boolean dining = false;
    public int numMeals = 0;
    private JTextArea jta;
    public static String newline = System.getProperty("line.separator");

    public Philosopher(int ID, Fork left, Fork right, JTextArea jtextarea) {

        philID = ID;
        leftF = left;
        rightF = right;

        pThread = new Thread(this);
        jta = jtextarea;
    }

    public void start(int delay) {
        pDelay = delay;
        dining = true;
        pThread.start();
    }

    public void stop(boolean force) {
        if (force) {
            pThread.interrupt();
        } else {
            dining = false;
        }
    }

    @Override
    public void run() {
        try {
            while (dining) {

                Thread.sleep((int) (1000 * Math.random() * pDelay));

                if (leftF.holder != this) {

                    jta.append("Philosopher " + Integer.toString(philID) + " has requested fork " + Integer.toString(leftF.forkID) + newline);
                    leftF.RequestFork(this);
                }

                Thread.sleep((int) (1000 * Math.random() * pDelay));
                
                if (rightF.holder != this) {
                    jta.append("Philosopher " + Integer.toString(philID) + " has requested fork " + Integer.toString(rightF.forkID) + newline);
                    rightF.RequestFork(this);
                }

                eating = true;
                jta.append("Philosopher " + Integer.toString(philID) + " is eating" + newline);
                Thread.sleep((int) (1000 * Math.random() * (pDelay + 1)));
                numMeals += 1;
                eating = false;

                leftF.CleanFork();
                Thread.sleep((int) (1000 * Math.random() * pDelay));
                rightF.CleanFork();
                Thread.sleep((int) (1000 * Math.random() * pDelay));
                //  jta.append("Philosopher " + Integer.toString(philID) + " has cleaned his forks"  + newline);
                jta.append("Philosopher " + Integer.toString(philID) + " is thinking" + newline);

                jta.setCaretPosition(jta.getDocument().getLength());
                Thread.sleep((int) (1000 * Math.random() * (pDelay + 1)));
            }
        } catch (java.lang.InterruptedException e) {
        }
    }
}
