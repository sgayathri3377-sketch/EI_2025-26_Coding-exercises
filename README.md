UserClient
This will be the code that the user interacts with.
User will be able to choose the orbit -> LEO, MEO, and GEO
Based on the chosen orbit, the RocketLaunchSimulator passes the command to the Mission Director to build the rocket.
User Commands:
The user can type “set_orbit_leo” to set the orbit and start building rocket
The user can type “start_checks” to run pre-launch checks
The user can type “launch” to launch the rocket and begin stage 1
The user can type “fast_forward_x” to view the result after x seconds
Exception Handling for handling input
The commands can be processed using Command Pattern(Avoids multiple if-else at the RocketLaunchSimulator)
This also follows the facade pattern as the user just types a command, but has no idea about the inner workings of the code.
Detailed Working of Command Pattern
The UserClient receives the input from the user. This input is sent to a utility class called “CommandParser”
The CommandParser is responsible for having the if-else statement and for separating the input if needed( needed to take the x seconds from fast_forward_x command). The necessary command object is created and sent to the UserClient class.
Then the userClient class executed the command, which calls the necessary method in rocketlaunchsimulator.
Exception Handling in User Input
Any syntactic errors such as “startchecks”, “lanch”, etc. shall be handled by the CommandParser.
Any semantic errors such as “launch” before start_checks, “start_checks” before setting the orbit level, shall be handled by the necessary method in RocketLaunchSimulator by checking the state(context).
All the error outputs must be received by the user client to be displayed in a neat way to the user.
Doubts on orbit level
Approach 1
The MissionProfiles will contain all 3 orbit information. The user can use “set_orbit_leo/meo/geo” to choose one of the three orbits. This information is used by the RocketLaunchSimulator to give command to the mission director. And commands are generally processed through commander approach.
Approach 2 (Preferred)
The mission profiles file shall have only the details for leo. So the command “set_orbit_leo/meo/geo” is not possible. The simulator always calls the mission director to build a rocket based on leo. This provides with the opportunity for expansion at a later time.
Building a Rocket
Rocket Model
All basic parameters such as
Fuel Percentage=100%
Altitude=0 km
Speed=0km/hr
stage = 0
Angle=0
Horizontal speed=0km/hr
Contains the physics equations to calculate fuel consumption, fuel consumption when fuel leaks, speed and altitude.
Has mass, fuel amount, max_altitude, max_orbital_speed, max_horizontal_speed which is set by the RocketBuilder.
Rocket Builder
Receives the input from MissionDirector regarding the values for mass, fuel amount, max_altitude, max_orbital_speed, max_horizontal_speed.
Sets these values in the rocket object and builds the rocket.
Mission Director
Loads Different Initial States for rockets from MISSION_PROFILES file.
Receives the orbit level from RocketLaunchSimulator.
Uses that profile information and Rocket Builder to build the rocket.
Rocket Launch Simulator
Statically initialises Logger object.
Initialises status to “Active” after receiving start_checks command.
Calls the MisisonDirector to build the rocket
Get the rocket object.
Start_Checks
In the Prelaunch state. Has a 0.5% chance for system malfunction. If not, the mission starts.
Launch and Fast Forward
Enters Stage 1(and later stage 2)
Has the method for advanceSimulation. Has a hardcoded value of 1 to display the status each second. We can use fast_forward to change the value of 1 for that time.
Exception handling for number of seconds in fast_forward
The status output is sent using Observer Pattern to the user and Logger.
Logger
Has an instance of the Logger.
Saves all the user commands with timestamps
Stores important status changes(success, failure, stage 1 separation, entering stage 2, launch, etc.) with time stamps. These states will be received from advance_simulation(x) method. Specific steps are needed inside.
Each command functionality such as “launch”, “start_checks”, must send data to the logger before executing the command.
State Management (State Pattern)
The different stages of rocket are 
Pre-launch
Stage 1
Stage 2 (Stage 1 and 2 is also stored in rocket model)
The status of RocketLaunchSimulator are “Active” and “Inactive” (boolean)
If max_altitude and orbital_speed is reached -> success and inactive
If some failure -> mission failed and inactive
Strategy for Failure Checks (Strategy Pattern)
Based on the stages of the rocket, different failure_checks are checked every second.
Fuel Percentage is checked at all stages.
Pre-Launch
System Malfunction(0.5% chance) 
Stage 1
Engine Flameout (1% chance every second of the run)
Stage 2
Fuel Leak ( 2% chance every second of the run )
Speed of fuel consumption increases (the burnrate is increased)
Output to UserClient and Logger
Approach 1
We can use the observer pattern to send outputs to both userClient and Logger.
Approach 2 (Preferred for ease in coding)
We can directly send the output to consoleOutputter. Frequently logging every second may not be needed by the Logger.
Approach 3 (Preferred for scalability)
Use the observer pattern only for UserClient. Right now, there is no need to include the logger. But, if an expansion is needed, it is easy to do so.
Logger
Loads the file.
Has exception handling for file handling
Uses Singleton Pattern
Format of all details : [TIMESTAMP] [EVENT TYPE] [DETAILS]
Exception Handling in Different Areas


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


