
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import net.mustaphin.project.bus.Bus;
import net.mustaphin.project.station.BusStop;
import net.mustaphin.project.station.DuispatcherStation;

public class Test2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
	BusStop novinky = new BusStop(3, "Novinky");
	BusStop zakharova = new BusStop(2, "Zakharova");
	BusStop peramoha = new BusStop(1, "Ploshcha Peramogi");
	BusStop circus = new BusStop(2, "Circus");
	BusStop kolas = new BusStop(4, "Jakub Kolas");
	List<BusStop> line18 = Arrays.asList(novinky, peramoha, zakharova);
	List<BusStop> line100 = Arrays.asList(circus, peramoha, kolas);
	List<Bus> bus = new ArrayList<>();
	bus.add(new Bus("18", line18));
	bus.add(new Bus("100", line100));
	DuispatcherStation duispatcherStation = new DuispatcherStation();
	duispatcherStation.startShift(bus);
    }
    
}
