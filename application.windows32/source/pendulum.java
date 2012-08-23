import processing.core.*; 
import processing.xml.*; 

import processing.serial.*; 
import controlP5.*; 

import controlP5.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class pendulum extends PApplet {

/*
  Language Processing:
  
  This Code is for Graphing the Computerized Precision Pendulum made by symCDC
  
  Thanks to Tom Igoe for his,
  Serial Graphing Sketch
  Which I borrowed heavily from as a starting place and branching out from there...
  
  Last Updated May 06 2012
*/

 


/*


ControlP5 controlP5;             //Define the variable controlP5 as a ControlP5 type.
DropdownList ports;              //Define the variable ports as a Dropdownlist.
Serial port;                     //Define the variable port as a Serial object.
int Ss;                          //The dropdown list will return a float value, which we will connvert into an int. we will use this int for that).
String[] comList ;               //A string to hold the ports in.
boolean serialSet;               //A value to test if we have setup the Serial port.
boolean Comselected = false;     //A value to test if you have chosen a port in the list.
*/

Serial myPort;                    // The serial port

 
int maxNumberOfSensors = 6;       // Arduino has 6 analog inputs, so I chose 6
boolean fontInitialized = false;  // whether the font's been initialized
float[] previousValue = new float[maxNumberOfSensors];  // array of previous values
int xpos = 0;                     // x position of the graph
PFont myFont;                     // font for writing text to the window
 
 
//Set up Variables
int minvalue = 16000000; //Start the minvalue very high
int maxvalue = 0;  //Start the max value very low
int offset = 1000; //For zooming up down
int division = 1;  //For zooming right left

//Variables for saving the data
boolean DataFlag = false; //Start with RECORDING OFF
PrintWriter output; //Turn on PrintWriter


public void setup () {
  // set up the window to whatever size you want:
  size(1200, 500);

  //Set the Frame Title  
  frame.setTitle("Computerized Precision Pendulum");
  
  //Setup the Serial Port
  String lines[] = loadStrings("comport.txt");
  int portnumber = Integer.parseInt(lines[3]);
  myPort = new Serial(this, Serial.list()[portnumber], 57600);
  //Now Clear the port and initialize the AD7745
  myPort.clear();
  myPort.write('r');  //Resets the Chip
  delay(1000);
  myPort.write('m');  //Puts the Chip in continuous capture mode
  delay(10);
  myPort.clear();
  
  /*
  //Build Box for Serial Port Selection
  controlP5 = new ControlP5(this);
  ports = controlP5.addDropdownList("list-1",10,25,100,84);
  customize(ports);
  */
  
  
  // create a font with the fourth font available to the system:
  //println(PFont.list());
  myFont = createFont(PFont.list()[6], 20);
  textFont(myFont);
  fontInitialized = true;
  
  // set inital background:
  background(255,255,240);  //ivory
  smooth();  //turn on antialiasing
  
  output = createWriter("data.csv");  //Set the File Name 
 
}

/*
//The dropdown list returns the data in a way, that i dont fully understand, again monkey see monkey do. However once inside the two loops, the value (a float) can be achive via the used line ;).
void controlEvent(ControlEvent theEvent) {
  if (theEvent.isGroup()) 
  {
    //Store the value of which box was selected, we will use this to acces a string (char array).
    float S = theEvent.group().value();
    //Since the list returns a float, we need to convert it to an int. For that we us the int() function.
    Ss = int(S);
    //With this code, its a one time setup, so we state that the selection of port has been done. You could modify the code to stop the serial connection and establish a new one.
    Comselected = true;
  }
}

//here we setup the dropdown list.
void customize(DropdownList ddl) {
  //Set the background color of the list (you wont see this though).
  ddl.setBackgroundColor(color(200));
  //Set the height of each item when the list is opened.
  ddl.setItemHeight(20);
  //Set the height of the bar itself.
  ddl.setBarHeight(15);
  //Set the lable of the bar when nothing is selected.
  ddl.captionLabel().set("Select COM port");
  //Set the top margin of the lable.
  ddl.captionLabel().style().marginTop = 3;
  //Set the left margin of the lable.
  ddl.captionLabel().style().marginLeft = 3;
  //Set the top margin of the value selected.
  ddl.valueLabel().style().marginTop = 3;
  //Store the Serial ports in the string comList (char array).
  comList = port.list();
  //We need to know how many ports there are, to know how many items to add to the list, so we will convert it to a String object (part of a class).
  String comlist = join(comList, ",");
  //We also need how many characters there is in a single port name, we\u00b4ll store the chars here for counting later.
  String COMlist = comList[0];
  //Here we count the length of each port name.
  int size2 = COMlist.length();
  //Now we can count how many ports there are, well that is count how many chars there are, so we will divide by the amount of chars per port name.
  int size1 = comlist.length() / size2;
  //Now well add the ports to the list, we use a for loop for that. How many items is determined by the value of size1.
  for(int i=0; i< size1; i++)
  {
    //This is the line doing the actual adding of items, we use the current loop we are in to determin what place in the char array to access and what item number to add it as.
    ddl.addItem(comList[i],i);
  }
  //Set the color of the background of the items and the bar.
  ddl.setColorBackground(color(60));
  //Set the color of the item your mouse is hovering over.
  ddl.setColorActive(color(255,128));
}

void startSerial(String[] theport)
{
  println("Setting up the serial port");
  //When this function is called, we setup the Serial connection with the accuried values. The int Ss acesses the determins where to accsess the char array. 
  myPort = new Serial(this, theport[Ss], 57600);
  //Since this is a one time setup, we state that we now have set up the connection.
  serialSet = true;
  
  
  //Now Clear the port and initialize the AD7745
  myPort.clear();
  myPort.write('r');  //Resets the Chip
  delay(1000);
  myPort.write('m');  //Puts the Chip in continuous capture mode
  delay(10);
  myPort.clear();
}

*/ 	
 
public void draw () {
  
  //So when we have chosen a Serial port but we havent yet setup the Serial connection. Do this loop
  //while(Comselected == true && serialSet == false)
  //{
    //Call the startSerial function, sending it the char array (string[]) comList
    //startSerial(comList);
  //}
  
}


//On Exit Close the Main Window
public void exit(){
   output.close();
}

//Event Manager for Key Presses
public void keyPressed() {
  //print (key);
  int xPos=10;
  int yPos=10;
  
  
  //Clear the screen
  if (key == 'c'){
    background(255,255,240);  //ivory
  }
  
  //Record the data
  if (key == 'r'){
    if (DataFlag == true){
      print ("Push s to stop recording..." + "\n");
      fill(0,0,0);  //black
      if (fontInitialized) {
        text("Push s to stop recording...",xPos, yPos);
        }
      }
  else if (DataFlag == false){
    print ("Recording Data" + "\n");
    fill(0,0,0);  //black
    if (fontInitialized) {
      text("Recording Data...",xPos,yPos);
    }
    DataFlag = true;
    }
  }
  
  //Save the data Stop Recording  
  if (key == 's'){
    if (DataFlag == true){
      print ("Turning OFF Recording ..." + "\n");
      
      //Time Stamps
      //String s = year() + "-" + month() + "-" + day() + "-" + hour() + "-" + minute() + "-" + second();
      //output.println(s);
      //Calendar now = Calendar.getInstance();
      //output.println(String.format("%1$ty%1$tm%1$td_%1$tH%1$tM%1$tS%1$tL", now));
    
      //Shut 'er down
      output.flush(); // Writes the remaining data to the file
      output.close(); // Finishes the file
      DataFlag = false;
      fill(0,0,0);  //black
        if (fontInitialized) {
         text("Turning OFF Recording ...",xPos,yPos);
          }
    }
    
    else if (DataFlag == false){
    print ("Push r to start a recording" + "\n");
    fill(0,0,0);  //black
      if (fontInitialized) {
          text("Push r to start a recording",xPos, yPos);
        }
      }
  }
  
  //Zero the graph
  if (key == 'z'){
    fill(0,0,0);  //black
    if (fontInitialized) {
          text("Zeroing Graph...",xPos, yPos);
        }
   print ("Zeroing Graph..." + "\n");
   minvalue = 65000000;
   maxvalue = 0;
   offset = 1000;
}
  
  if (key == CODED) {
    if (keyCode == UP) {
      offset = offset - 100;
    } else if (keyCode == DOWN) {
      offset = offset + 100;
    } else if (keyCode == RIGHT) {
      division = division + 1;
    } else if (keyCode == LEFT) {
      division = division - 1;
    }
    }
 
}


public void serialEvent (Serial myPort) {
  // get the ASCII string:
  String inString = myPort.readStringUntil('\n');
 
  // if it's not empty:
  if (inString != null) {
    // trim off any whitespace:
    inString = trim(inString);
    
    //Setup the min and max value
    //If minvalue catches a 0 fix it b making it huge again
    if (minvalue==0){
      minvalue = 16000000;
    }
    if (PApplet.parseInt(inString) >= maxvalue) {
      maxvalue = PApplet.parseInt(inString);
    }
    if (PApplet.parseInt(inString) <= minvalue) {
       minvalue=PApplet.parseInt(inString);
    }
   
   
   //If recording is on then write it to the file...
   if (DataFlag == true){
      output.println(inString);  //Write it to the file
     }
   
   
 
    
    // convert to an array of ints:
    int incomingValues[] = PApplet.parseInt(split(inString, ","));
    for (int i = 0; i < incomingValues.length; i++) {
 
        // map the incoming values to an appropriate
        float ypos = map(incomingValues[i], maxvalue + offset, minvalue - offset,  0, 500);
        
        stroke(25,25,112);
        line(xpos, previousValue[i], xpos+1, ypos);
        // save the current value to be the next time's previous value:
        previousValue[i] = ypos;
      }
    
    // if you've drawn to the edge of the window, start at the beginning again:
    if (xpos >= width) {
      xpos = 0;
      background(255,255,240);  //ivory
    } 
    else {
      xpos = xpos + division;
      
    }
  }
}


  static public void main(String args[]) {
    PApplet.main(new String[] { "--bgcolor=#F0F0F0", "pendulum" });
  }
}
