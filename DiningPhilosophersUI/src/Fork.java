/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Anthony
 */
public class Fork {

    int forkID;
    Philosopher holder = null;

    public Fork(int ID) {
        forkID = ID;
    }

    public void RequestFork(Philosopher phil) throws InterruptedException {
        if (holder == null || (holder.philID > phil.philID && holder.eating == false)) {
            holder = phil;
        } else {
            while (holder != null) {
                Thread.sleep(0);

                holder = phil;
            }
        }
    }

    public void CleanFork() {
        holder = null;
    }
}
