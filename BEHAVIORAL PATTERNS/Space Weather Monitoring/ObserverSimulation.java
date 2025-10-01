import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList; // <-- FIX: Necessary import for CopyOnWriteArrayList

// Subject class (Observable)
class SpaceWeatherStation {
    // FIX: Must import from java.util.concurrent or specify the full path
    private final CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<>(); 
    private String solarFlare;

    public void subscribe(Module module) { modules.addIfAbsent(module); }
    public void unsubscribe(Module module) { modules.remove(module); }

    public void setSolarFlare(String flare) {
        this.solarFlare = flare;
        System.out.println("\n[Mission Control] Solar Flare Detected: " + flare);
        notifyModules();
    }

    private void notifyModules() {
        for (Module m : modules) {
            try {
                m.update(solarFlare);
            } catch (Exception e) {
                System.err.println("[Warning] Module " + m.getClass().getSimpleName() +
                                   " failed to handle update: " + e.getMessage());
                // optionally log stacktrace
            }
        }
    }
}

// Observer interface
interface Module { 
    void update(String flare); 
}

// Concrete Observers
class NavigationModule implements Module {
    @Override
    public void update(String flare) {
        System.out.println("[Navigation] Adjusting trajectory for " + flare);
    }
}

class CommunicationModule implements Module {
    @Override
    public void update(String flare) {
        System.out.println("[Communication] Shielding comms for " + flare);
    }
}

class ShieldModule implements Module {
    @Override
    public void update(String flare) {
        System.out.println("[Shield] Activating shields for " + flare);
    }
}

// Simulation - public class name matches the file name (ObserverSimulation)
public class ObserverSimulation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SpaceWeatherStation station = new SpaceWeatherStation();

        // Subscribe modules
        station.subscribe(new NavigationModule());
        station.subscribe(new CommunicationModule());
        station.subscribe(new ShieldModule());

        System.out.println("=== Space Weather Monitoring (Observer Pattern) ===");
        System.out.println("Type 'exit' to stop.");

        while (true) {
            System.out.print("Enter solar flare intensity (Low/Medium/High): ");
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("exit")) break;
            
            // Check if input is valid before setting the flare
            if (input.equalsIgnoreCase("low") || input.equalsIgnoreCase("medium") || input.equalsIgnoreCase("high")) {
                station.setSolarFlare(input);
            } else {
                System.out.println("[System] Invalid input. Please enter Low, Medium, High, or exit.");
            }
        }

        System.out.println("Simulation ended.");
        sc.close();
    }
}
