
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.mustaphin.project.CallableClassExample;
import net.mustaphin.project.RunnableClassExample;

public class Test2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	ExecutorService es = Executors.newFixedThreadPool(1);
	StringBuilder builder = new StringBuilder();
	Future<Integer> f = es.submit(new CallableClassExample(builder));
	es.execute(new RunnableClassExample(builder));
	try {
	    System.out.println("Finish " + f.get());
	} catch (InterruptedException ex) {
	    Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
	} catch (ExecutionException ex) {
	    Logger.getLogger(Test2.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

}
