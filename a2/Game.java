package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.CheckBox;

public class Game extends Form{
	private GameWorld gw;
	

	
	//Declare and Initialize Commands for use
	private AccelerateCommand accelerateCommand = new AccelerateCommand();
	private BrakeCommand brakeCommand = new BrakeCommand();
	private SteerLeftCommand steerLeftCommand = new SteerLeftCommand();
	private SteerRightCommand steerRightCommand = new SteerRightCommand();
	private PickUpFuelCanCommand pickUpFuelCanCommand = new PickUpFuelCanCommand();
	private TickCommand tickCommand = new TickCommand();
	private ExitCommand exitCommand = new ExitCommand();
	private SwitchStrategyCommand switchStrategyCommand = new SwitchStrategyCommand();
	private CheckBox soundCommand = new CheckBox("Sound");
	private GiveAboutInfoCommand giveAboutInfoCommand = new GiveAboutInfoCommand();
	private GiveHelpInfoCommand giveHelpInfoCommand = new GiveHelpInfoCommand();
	private MapCommand mapCommand = new MapCommand();
	private CollideWithNPCCommand collidewithNPCCommand = new CollideWithNPCCommand();
	private CollideWithPylonCommand collidewithPylonCommand = new CollideWithPylonCommand();
	private CollideWithBirdCommand collidewithBirdCommand = new CollideWithBirdCommand();
	
	private MapView mapView;
	/**
	 * Instantiates the GameWorld and its Objects
	 * It also begins the action listener by invoking play().
	 */
	public Game(){
		
		//Initializes the Game World Object
		gw = new GameWorld();
		gw.init();
		
		//GUI SetUp
		this.setLayout(new BorderLayout());
		soundCommand.getAllStyles().setBgTransparency(255);
		soundCommand.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		soundCommand.getAllStyles().setFgColor(ColorUtil.WHITE);
		
		//Toolbar Setup
		Toolbar toolBar = new Toolbar();
		this.setToolbar(toolBar);
		toolBar.setTitle("Race Car Game");
		toolBar.addCommandToSideMenu(accelerateCommand);
		toolBar.addCommandToSideMenu(exitCommand);
		toolBar.addCommandToSideMenu(giveAboutInfoCommand);
		toolBar.addCommandToSideMenu(giveHelpInfoCommand);
		toolBar.addCommandToRightBar(giveHelpInfoCommand);
		toolBar.addComponentToSideMenu(soundCommand);
		
		//Setup West Container
		Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		this.setUpWestContainer(westContainer);
		westContainer.getAllStyles().setPadding(Component.TOP, 400);
		westContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		//Setup South Container
		Container southContainer = new Container(new FlowLayout(Component.CENTER));
		this.setUpSouthContainer(southContainer);
		southContainer.getAllStyles().setPadding(Component.LEFT, 200);
		southContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		//Setup Map View
		mapView = new MapView(gw);
		mapView.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		this.addKeyListener('m', mapCommand);
		
		//Setup Score View
		ScoreView scoreView = new ScoreView();
		scoreView.setLayout(new FlowLayout(Component.CENTER));
		scoreView.getAllStyles().setFgColor(ColorUtil.BLUE);
		//scoreView.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.GREEN));
		
		//Adds the containers to the Game Form
		this.add(BorderLayout.WEST, westContainer);
		this.add(BorderLayout.SOUTH, southContainer);
		this.add(BorderLayout.NORTH, scoreView);
		this.add(BorderLayout.CENTER, mapView);
		
		//Adds the Observers to Game World.
		gw.addObserver(scoreView);
		gw.addObserver(mapView);
		
		this.show();
	}
	

	
	/**
	 * This method skips a line to create cleaner reading experience for command line.
	 */
	private void skipALine() {
		System.out.println();
	}
	
	private void setUpWestContainer(Container container) {

		
		//Creates Buttons
		MyButton switchStrategyButton = new MyButton("Switch Strategy");
		MyButton accelerateButton = new MyButton("Accelerate");
		MyButton brakeButton = new MyButton("Brake");
		MyButton rightButton = new MyButton("Right");
		MyButton leftButton = new MyButton("Left");
		MyButton exitButton = new MyButton("Exit");
		
		//Sets commands to Buttons
		switchStrategyButton.setCommand(switchStrategyCommand);
		accelerateButton.setCommand(accelerateCommand);
		brakeButton.setCommand(brakeCommand);
		rightButton.setCommand(steerRightCommand);
		leftButton.setCommand(steerLeftCommand);
		exitButton.setCommand(exitCommand);
		
	
		//Sets Key Listeners
		this.addKeyListener('s', switchStrategyCommand);
		this.addKeyListener('a', accelerateCommand);
		this.addKeyListener('b', brakeCommand);
		this.addKeyListener('l', steerLeftCommand);
		this.addKeyListener('r', steerRightCommand);
		this.addKeyListener('x', exitCommand);
		
		//Adds buttons to the West Container
		container.add(switchStrategyButton);
		container.add(accelerateButton);
		container.add(brakeButton);
		container.add(rightButton);
		container.add(leftButton);
		container.add(exitButton);
	}
	
	private void setUpSouthContainer(Container container) {

		
		//Creates Buttons
		MyButton collidewithNPC = new MyButton("Collide With NPC");
		MyButton collidewithPylon = new MyButton("Collide With Pylon");
		MyButton collidewithBird = new MyButton("Collide With Bird");
		MyButton pickUpFuel = new MyButton("Pick Up Fuel Can");
		MyButton tick = new MyButton("Tick");
		
		//Sets commands to Buttons
		collidewithNPC.setCommand(collidewithNPCCommand);
		collidewithPylon.setCommand(collidewithPylonCommand);
		collidewithBird.setCommand(collidewithBirdCommand);
		pickUpFuel.setCommand(pickUpFuelCanCommand);
		tick.setCommand(tickCommand);

		//Sets Key Listeners
		this.addKeyListener('w', collidewithNPCCommand);
		this.addKeyListener('1', collidewithPylonCommand);
		this.addKeyListener('2', collidewithPylonCommand);
		this.addKeyListener('3', collidewithPylonCommand);
		this.addKeyListener('4', collidewithPylonCommand);
		this.addKeyListener('5', collidewithPylonCommand);
		this.addKeyListener('6', collidewithPylonCommand);
		this.addKeyListener('7', collidewithPylonCommand);
		this.addKeyListener('8', collidewithPylonCommand);
		this.addKeyListener('9', collidewithPylonCommand);
		this.addKeyListener('g', collidewithBirdCommand);
		//Sets up key bindings
		this.addKeyListener('f', pickUpFuelCanCommand);
		this.addKeyListener('t', tickCommand);

		
		//Adds buttons to the South Container
		container.add(collidewithNPC);
		container.add(collidewithPylon);
		container.add(collidewithBird);
		container.add(pickUpFuel);
		container.add(tick);

		
	}
	
	public class AccelerateCommand extends Command {
		public AccelerateCommand() {
			super("Accelerate");
		}

		@Override
		public void actionPerformed(ActionEvent ev) {
			skipALine();
			System.out.println("Accelerating!");
			gw.accelerateCar();
		}
	}

	public class BrakeCommand extends Command {
		public BrakeCommand() {
			super("Brake");
		}

		@Override
		public void actionPerformed(ActionEvent ev) {
			skipALine();
			System.out.println("Braking!");
			gw.brakeCar();
		}
	}

	public class SteerLeftCommand extends Command {
		public SteerLeftCommand() {
			super("SteerLeft");
		}

		@Override
		public void actionPerformed(ActionEvent ev) {
			skipALine();
			System.out.println("Steering Left!!!");
			gw.steerWheelLeft();
		}
	}

	public class SteerRightCommand extends Command {
		public SteerRightCommand() {
			super("SteerRight");
		}

		@Override
		public void actionPerformed(ActionEvent ev) {
			skipALine();
			System.out.println("Steering Right!!!");
			gw.steerWheelRight();
		}
	}

	public class CollideWithNPCCommand extends Command {
		public CollideWithNPCCommand() {
			super("CollideWithNPC");
		}

		@Override
		public void actionPerformed(ActionEvent ev) {
			skipALine();
			System.out.println("Collided with NPC!!!");
			gw.tookDamageByCar();
		}
	}

	public class CollideWithPylonCommand extends Command {
		public CollideWithPylonCommand() {
			super("CollideWithPylon");
		}
		
		@Override
		public void actionPerformed(ActionEvent ev) {
			//Creates array of Commands for Dialog
			Command ok = new Command("Ok");
			Command cancel = new Command("Cancel");
			Command[] cmds = new Command[]{ok, cancel};
			
			//Text field for pylon input
			TextField textField = new TextField();
			Dialog.show("Select Pylon Number", textField, cmds);
			//Grabs input
			String pylonNumber = textField.getText().toString();
			
			//checks input to see if the length is 1
			if (pylonNumber.length() == 1) {
				this.checkIntegerOneThroughNine(pylonNumber.charAt(0));
			} else {
				Dialog.show("ERROR", "Not a Valid Input", "Ok", "Cancel");
			}
			skipALine();
		}
		
	
		private void checkIntegerOneThroughNine(char command) {
			//Converts the char into a int.
			//The ASCII for char 0 is equal to 48.
			//So if the input is '0', its int value will be 48. So 48 - 48 is equal to 0.
			int characterNumber = command - 48;
			//If condition checks to see if it is between 0 and 10.
			if (0 < characterNumber && characterNumber < 10) {
				gw.overPassedPylon(characterNumber);
				skipALine();
			} else {
				Dialog.show("ERROR", "Not a Valid Input", "Ok", "Cancel");
				skipALine();
			}
		}
	}

	public class PickUpFuelCanCommand extends Command {
		public PickUpFuelCanCommand() {
			super("PickUpFuelCan");
		}

		@Override
		public void actionPerformed(ActionEvent ev) {
			skipALine();
			System.out.println("Collided with Fuel Can!!!");
			gw.gotFuelCan();
		}
	}

	public class CollideWithBirdCommand extends Command {
		public CollideWithBirdCommand() {
			super("CollideWithBird");
		}

		@Override
		public void actionPerformed(ActionEvent ev) {
			skipALine();
			System.out.println("Collided with Bird!!!");
			gw.tookDamageByBird();
		}
	}

	public class TickCommand extends Command {
		public TickCommand() {
			super("Tick");
		}

		@Override
		public void actionPerformed(ActionEvent ev) {
			skipALine();
			gw.gameTick(1);
		}
	}

	public class ExitCommand extends Command {
		public ExitCommand() {
			super("Exit");
		}

		@Override
		public void actionPerformed(ActionEvent ev) {
			boolean answer = Dialog.show("Exit", "Are you sure you want to Exit?", "Yes", "No");
			if (answer) {
				System.exit(0);
			} 
		}
	}

	public class SwitchStrategyCommand extends Command {
		public SwitchStrategyCommand() {
			super("SwitchStrategy");
		}

		@Override
		public void actionPerformed(ActionEvent ev) {
			skipALine();
			// display source of request in console
			gw.changeNPCStrategies();
			System.out.println("NPC Strategy has been changed and new Startegy Retrieved within GameWorld " + ev.getCommand()
			+ " " + ev.getSource().getClass());
			
		}
	}

	public class SoundCommand extends Command {
		public SoundCommand() {
			super("Sound");
		}

		@Override
		public void actionPerformed(ActionEvent ev) {
			skipALine();
		}
	}

	public class GiveAboutInfoCommand extends Command {
		public GiveAboutInfoCommand() {
			super("About");
		}

		@Override
		public void actionPerformed(ActionEvent ev) {
			System.out.println("About Info!");
			Dialog.show("About",
					"Developer: Hasib Habibi\n"
					+ "Course: CSC 133 Summer 2017\n"
					+ "Object-Oriented Programming",
					"Done", "Cancel");
			skipALine();
		}
	}
	
	//Prints out Help dialog box.
	public class GiveHelpInfoCommand extends Command {
		public GiveHelpInfoCommand() {
			super("Help");
		}

		@Override
		public void actionPerformed(ActionEvent ev) {
			System.out.println("Help Info!");
			Dialog.show("Help", 
					"Hiii this is the help box and these are your options.\n"
						+ "'a' is to Accelerate.\n"
						+ "'b' is to Brake.\n"
						+ "'l' is to turn left.\n"
						+ "'r' is to turn right.\n"
						+ "'f' is to pick up fuel cans.\n"
						+ "'t' is to invoke game tick.\n"
						+ "'m' is to print the map on console.\n"
						+ "'x' is to exit the game.\n"
						+ "Or you can feel free to press the buttons!", 
					"OK", "Cancel");
			skipALine();
		}
	}
	//Outputs the current state of the map by passing GameWorld's gameObject collection to mapView to output it
	public class MapCommand extends Command {
		public MapCommand() {
			super("Map");
		}
		
		@Override
		public void actionPerformed(ActionEvent ev) {
			mapView.outputMap(gw.getGameObjectCollection());
			skipALine();
		}
	}
	
}
	