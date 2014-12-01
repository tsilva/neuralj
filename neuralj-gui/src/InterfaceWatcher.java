import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import neuralj.networks.feedforward.learning.FeedForwardNetworkLearningAlgorithm;
import neuralj.watchers.IWatcher;

/*
 * InterfaceWatcher.java
 *
 * Created on 29 de Julho de 2006, 16:55
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class InterfaceWatcher implements IWatcher{
    
    public static FeedForwardNetworkLearningAlgorithm algorithm = null;
    public static NewJFrame frame;
    public static String training_path;
    public static String validation_path;
    public static PrintWriter training_log = null;
    public static PrintWriter validation_log = null;
    
    /** Creates a new instance of InterfaceWatcher */
    public InterfaceWatcher(FeedForwardNetworkLearningAlgorithm alg, NewJFrame gui, String training, String validation) {
        algorithm = alg;
        frame = gui;
        this.training_path = training;
        this.validation_path = validation;
    }
    
    public void start() {
        frame.setCurrentEpoch(0);
        frame.setCurrentMSE(0);
        frame.repaint();
        if(!this.training_path.equals(""))
            
            try {
                training_log = new PrintWriter(new FileOutputStream(this.training_path));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        
        if(!this.validation_path.equals(""))
            
            try {
                validation_log = new PrintWriter(new FileOutputStream(this.validation_path));
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        try {
            SwingUtilities.invokeAndWait(new Runnable(){
                public void run(){
                    frame.setInterfaceStateTraining();
                }
            }
            );
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
    
    public void monitor(){
        try {
            SwingUtilities.invokeAndWait(new Runnable(){
                public void run(){
                    frame.setCurrentEpoch(algorithm.current_epoch);
                    frame.setCurrentMSE(algorithm.minimum_validation_error);
                }
            }
            );
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
        if(training_log != null)
            training_log.println(algorithm.current_epoch + "," + algorithm.current_training_error);
        if(validation_log != null)
            validation_log.println(algorithm.current_epoch + "," + algorithm.current_validation_error);
        frame.repaint();
    }
    
    public void stop() {
        if(training_log != null) {
            training_log.close();
            training_log = null;
        }
        if(validation_log != null) {
            validation_log.close();
            validation_log = null;
        }
        try {
            SwingUtilities.invokeAndWait(new Runnable(){
                public void run(){
                    frame.setInterfaceStateAwaitingInput();
                }
            }
            );
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
}
