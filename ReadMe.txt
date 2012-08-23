This is the ReadMe file for the Computerized Precision Pendulum Software.
Here are the steps to get your Pendulum up and running.

Step 1:
Before you run the software plug the pendulum into the USB port an and install the included driver located in the FTDI USB Drivers folder

Step 2:
Change the comport.txt file to the number on your system there are directions at the top of file, the file must keep the same number of lines that
it has now, only change the number.

Step 3:
Find and run the pendulum.exe file for the system you are on.  For instance if you are on a 32 bit windows machine you want to run
pendulum.exe from the application.windows32 folder.

Step 4:
Once pendulum.exe starts up you should see a graph thats changing in response to pendulum motions.  Congratulations! Your Computerized

Precision Pendulum is up and running!

Command List - The pendulum.exe file recognizes these commands from the keyboard
c - clears the screen
z - zeroes the graph to extents
r - starts recording
s - stops recording and saves the data in the current folder under the name data.csv
right & left arrow keys zoom in and out of the graph

The data.csv file will hold a list of numbers that you can import into other software for analyisis.  You must rename this file to something else

before you restart the pendulum.exe program or it will overwrite and clear the file erasing any previous data that you may have saved.  Rename

the files to something like data_01.csv, data_02.csv, data_03.csv etc... as you take successive readings.  You have to close pendulum.exe rename

the file and then re-open pendulum.exe to record a new file.

pendulum.exe is written in the Processing coding language.  See processing.org for more information.  The actual pendulum processing code lives

in the main directory as pendulum.pde and can be opened with any text browser to see how it works.

Enjoy the Pendulum and let me know if you have any questions or comments,
Eric Daine

The fine print:
This program is licensed Creative Commons Attribution-ShareAlike 3.0 Unported (CC BY-SA 3.0)

Under the following conditions:
Attribution — You must attribute the work in the manner specified by the author or licensor (but not in any way that suggests that they endorse you or your use of the work).
Share Alike — If you alter, transform, or build upon this work, you may distribute the resulting work only under the same or similar license to this one.
With the understanding that:
Waiver — Any of the above conditions can be waived if you get permission from the copyright holder.
Public Domain — Where the work or any of its elements is in the public domain under applicable law, that status is in no way affected by the license.
Other Rights — In no way are any of the following rights affected by the license:
Your fair dealing or fair use rights, or other applicable copyright exceptions and limitations;
The author's moral rights;
Rights other persons may have either in the work itself or in how the work is used, such as publicity or privacy rights.