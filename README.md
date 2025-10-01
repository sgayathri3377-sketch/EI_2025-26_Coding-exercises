
DESIGN PATTERN

Design patterns represent established, reusable solutions to recurring problems that arise during system design and development. They are not finished pieces of code but rather conceptual templates that guide developers in structuring software in a way that is scalable, maintainable, and efficient.

Using design patterns leads to:
Consistency across codebases


Reusability of solutions


Improved communication among developers (since patterns provide a common vocabulary)


Maintainability and flexibility in software systems


Design patterns are generally divided into three categories: Creational, Structural, and Behavioral patterns.
Design patterns are a cornerstone of modern software development.
Creational patterns manage how objects are created.


Structural patterns define how objects and classes are organized.


Behavioral patterns regulate how objects interact and communicate.

Behavioral patterns
Definition:
 Behavioral patterns are concerned with communication and responsibility among objects. They provide solutions for defining clear interaction rules, making the system more flexible, reusable, and easier to extend.
Purpose:
Manage interactions between objects.


Define clear responsibilities.


Support extensibility of communication flows.

USE CASES


Observer Pattern – Space Weather Monitoring


Scenario: A space station monitors solar flares. Modules like Navigation, Communication, and Shields automatically react.


Benefit: Any new module can subscribe to weather updates without changing existing code.


Strategy Pattern – Spacecraft Propulsion


Scenario: A spacecraft can switch propulsion methods dynamically (Chemical, Ion, Warp).


Benefit: Mission Control can change behavior at runtime without modifying spacecraft code.




Creational Design Patterns
Definition:
 Creational patterns focus on the process of object creation. They provide solutions to control how objects are instantiated while ensuring the system is decoupled from the details of creation. This makes software more adaptable to future changes.
Purpose:
Encapsulate object creation logic.


Promote flexibility in choosing which objects to create.


Reduce direct dependencies between classes.


USE CASES 
Singleton Pattern – Mission Control Center


Scenario: Only one mission control exists to coordinate all space missions.


Benefit: Guarantees a single point of control.


Factory Pattern – Spacecraft Factory


Scenario: Mission Control can create different spacecraft types (Shuttle, Probe, Satellite) without knowing exact class.


Benefit: Centralized, flexible creation. New spacecraft can be added easily.



Structural Design Patterns
Definition:
 Structural patterns deal with the composition of classes and objects. They focus on how different parts of a system can be combined to form larger structures while keeping the system efficient and easy to modify.
Purpose:
Define clear relationships between components.


Simplify complex structures.


Enhance flexibility in object composition.
USE CASES


Adapter Pattern – Alien Communication


Scenario: Earth systems need to communicate with an alien transmitter with a different interface.


Benefit: Converts incompatible interfaces so systems can communicate seamlessly.


Composite Pattern – Space Station Modules


Scenario: A space station has multiple modules (solar panels, labs, shield modules) that can be treated uniformly.


Benefit: Treat individual modules and composite structures in the same way, simplifying operations.



BEHAVIOURAL

Use Case 1: Observer Pattern – Space Weather Monitoring
Real-Life Scenario:
A space station monitors solar flares.


Modules like Navigation, Communication, and Shield need to respond automatically when a flare occurs.


The system must allow new modules to subscribe/unsubscribe dynamically.


Why Observer Pattern?
It creates a one-to-many relationship between the weather station (subject) and modules (observers).


Changes in the weather station automatically notify all modules.


Classes and Responsibilities:
Class
Responsibility
SpaceWeatherStation
Subject; keeps a list of subscribed modules and notifies them when a flare occurs
Module
Observer interface; declares update() method
NavigationModule
Reacts to flares by adjusting navigation
CommunicationModule
Shields communication systems when flare occurs
ShieldModule




Activates protective shields





Simulation Flow:
User enters flare intensity.


All modules are notified automatically.


Modules react based on the intensity.


Use Case 2: Strategy Pattern – Spacecraft Propulsion
Real-Life Scenario:
A spacecraft can switch between Chemical Engine, Ion Engine, or Warp Drive.


Mission control may change propulsion strategy dynamically based on mission requirements.


Why Strategy Pattern?
Allows changing spacecraft behavior at runtime without modifying its class.


Each propulsion system is encapsulated as a separate class.


Classes and Responsibilities:
Class
Responsibility
Propulsion
Strategy interface; defines move() method
ChemicalEngine
Implements move() for chemical propulsion
IonEngine
Implements move() for ion propulsion
WarpDrive
Implements move() for warp drive
Spacecraft
Context class; uses a propulsion strategy and delegates travel


Simulation Flow:
User selects propulsion type.


Spacecraft switches propulsion dynamically.


Travel behavior changes instantly.




CREATIONAL

Use Case 1: Singleton Pattern – Mission Control Center
Real-Life Scenario:
There is only one Mission Control Center coordinating all space missions.


Any spacecraft or module that needs mission data must access the same instance.


Why Singleton Pattern?
Guarantees single instance of Mission Control.


Provides global access point for all modules.


Classes and Responsibilities:
Class
Responsibility
MissionControl
Singleton class; stores mission data and coordinates spacecraft

Simulation Flow:
Only one instance of Mission Control is created.


Any updates via mc1 are reflected when accessed via mc2.
Demonstrates controlled, single instance creation.


Use Case 2: Factory Method Pattern – Spacecraft Factory
Real-Life Scenario:
Mission Control can create different spacecraft types: Shuttle, Probe, or Satellite.


Mission control doesn’t need to know how each spacecraft is built, just requests a type.


Why Factory Pattern?
Encapsulates object creation logic.


Makes it easy to add new spacecraft types in the future without changing client code.


Classes and Responsibilities:
Class
Responsibility
Spacecraft
Abstract class/interface for all spacecraft
Shuttle, Probe, Satellite
Concrete spacecraft types
SpacecraftFactory
Factory class; creates spacecraft based on type

Simulation Flow:
User inputs spacecraft type.


Factory creates the corresponding spacecraft object.
Spacecraft launches according to its type.



STRUCTURAL

1 . Adapter Pattern – Space Satellite Data Download
Real-Life Scenario
A European ground station software expects satellite data using a method downloadData(String format).


A NASA satellite provides telemetry using fetchTelemetry(String protocol).


Their interfaces don’t match.


Why Adapter
In real missions, satellites from different agencies use different communication protocols.


The Adapter converts requests so the ground station can communicate with NASA’s satellite without modifying either system.


Simulation Flow
Ground station requests data (downloadData).


Adapter converts the request into NASA’s protocol.


NASA satellite responds correctly.



2️⃣ Composite Pattern – International Space Station Modules
Real-Life Scenario:
ISS has multiple modules: Labs, Habitation, Solar Panels.


Some modules contain submodules (lab equipment, solar arrays).


Operators want to check status or control modules uniformly, whether single module or composite.


Why Composite:
Treat single modules and module groups the same.


Operations like "check status" or "shutdown" work recursively on all components.


Simulation Flow:
Each leaf module reports status.


Composite modules recursively report status of all child modules.


Treating individual modules and composites uniformly simplifies management.





# 🚀 Rocket Launch Simulator  

A simulation system that models rocket launches with multiple design patterns (Command, Facade, Observer, State, Strategy, Singleton). The system allows a user to set the orbit, perform checks, launch the rocket, and fast-forward the simulation.  

---

## 📌 Features
- **Command Pattern** → Processes user commands cleanly (avoids nested `if-else`).  
- **Facade Pattern** → User interacts with simple commands without knowing internal workings.  
- **Observer Pattern** → Sends updates to UserClient and Logger.  
- **State Pattern** → Manages rocket stages (Pre-launch, Stage 1, Stage 2).  
- **Strategy Pattern** → Handles different failure checks at each stage.  
- **Singleton Pattern** → Logger ensures a single instance for logging events.  

---

## 🖥️ User Client
The entry point for the user.  
- Accepts commands and interacts with the `CommandParser`.  
- Executes parsed commands through the `RocketLaunchSimulator`.  

### User Commands
- `set_orbit_leo` → Set orbit to LEO and build rocket.  
- `set_orbit_meo` → Set orbit to MEO and build rocket.  
- `set_orbit_geo` → Set orbit to GEO and build rocket.  
- `start_checks` → Run pre-launch system checks.  
- `launch` → Launch the rocket and begin Stage 1.  
- `fast_forward_x` → Simulate `x` seconds ahead.  

---

## ⚙️ Command Processing Flow
1. **UserClient** receives input.  
2. **CommandParser** interprets input, validates it, and creates a `Command` object.  
3. **UserClient** executes the command.  
4. **RocketLaunchSimulator** runs the necessary method.  

✅ **Syntactic Errors** (typos like `lanch`, `startchecks`) → Handled by **CommandParser**.  
✅ **Semantic Errors** (launch before checks, checks before setting orbit) → Handled by **RocketLaunchSimulator**.  

---

## 🛰️ Orbit Levels
### Approach 1
- `MISSION_PROFILES` contains all 3 orbit configurations.  
- User selects `set_orbit_leo/meo/geo`.  

### Approach 2 (Preferred)  
- `MISSION_PROFILES` contains only LEO details.  
- Expansion for MEO and GEO possible later.  

---

## 🏗️ Rocket Building
### Rocket Model
- Initial values:  
  - Fuel = 100%  
  - Altitude = 0 km  
  - Speed = 0 km/hr  
  - Stage = 0  
  - Angle = 0  
  - Horizontal Speed = 0 km/hr  

- Parameters set by **RocketBuilder**:  
  - Mass  
  - Fuel Amount  
  - Max Altitude  
  - Max Orbital Speed  
  - Max Horizontal Speed  

### Rocket Builder
- Receives input from **Mission Director**.  
- Builds the rocket with the given parameters.  

### Mission Director
- Loads orbit profiles from `MISSION_PROFILES`.  
- Builds rocket using **Rocket Builder**.  

---

## 🚀 Rocket Launch Simulator
- Handles system state and rocket execution.  
- **Start Checks**:  
  - 0.5% chance of system malfunction.  
  - If successful, mission starts.  
- **Launch**:  
  - Stage 1 → Stage 2 transition handled.  
- **Fast Forward**:  
  - Advances simulation by `x` seconds.  

---

## 🔄 State Management
- **Pre-launch**  
- **Stage 1**  
- **Stage 2**  

**Simulator States**  
- `Active`  
- `Inactive`  

**Success Conditions**  
- Reached `max_altitude` and `orbital_speed`.  

**Failure Conditions**  
- System errors or malfunctions.  

---

## ⚠️ Failure Strategy (Strategy Pattern)
- **Pre-Launch**: 0.5% chance of malfunction.  
- **Stage 1**: 1% chance of engine flameout per second.  
- **Stage 2**: 2% chance of fuel leak per second (increases burn rate).  

---

## 📝 Logger (Singleton)
- Stores all user commands + mission events with timestamps.  
- Format:  

- Logs:  
- User commands  
- Mission status changes (success, failure, stage separation, etc.)  
- Exception handling for file operations included.  

---

## 📡 Output Management
### Approach 1  
Use Observer Pattern for **both UserClient and Logger**.  

### Approach 2 (Ease of Coding)  
Send output directly to console.  

### Approach 3 (Preferred for Scalability)  
Use Observer Pattern for **UserClient** only. Logger integration can be added later.  

---

## ⚡ Exception Handling
- **CommandParser**: Handles invalid syntax.  
- **RocketLaunchSimulator**: Handles invalid command order (semantic errors).  
- **Logger**: Handles file read/write errors.  
- **Commands**: Handles invalid values in `fast_forward_x` and checks states  

---

## 📂 Design Patterns Summary
- **Command Pattern** → User command handling.  
- **Facade Pattern** → Simplified interface for the user.  
- **Observer Pattern** → Output updates to clients.  
- **State Pattern** → Rocket stages + simulator status.  
- **Singleton Pattern** → Logger.  

---

## 📊 System Architecture

```mermaid
flowchart TD
  A[UserClient] -->|Sends input| B[CommandParser]
  B -->|Creates Command| C[RocketLaunchSimulator]
  C -->|Build Rocket| D[MissionDirector]
  D -->|Configures| E[RocketBuilder]
  E -->|Builds| F[Rocket]

  C -->|Updates| G[Logger]
  C -->|Notifies| H[UserClient Output]


