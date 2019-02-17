# Model-View-Presenter-Project
An interactive maze game which has both client side (the game itself) and server side that handles many clients simultaneously. Different algorithms are being used: For generating new mazes – Simple &amp; Growing Tree algorithm and for maze solving- Depth First Search &amp; Best First Search.

## _DOWNLOAD the full and  game with all the explanations inside_ ##
https://drive.google.com/open?id=1fkWM9IpRPoSoDgJsDUGDORi9xotRntKn

## _Click the image below to see LIVE demonstration & full explanation_ ##
[![Maze Project](https://i.imgur.com/WzVm6qz.jpg)](https://www.youtube.com/watch?v=U2QG0PXBxic "Maze Project")

## The Lion King Maze – User Instructions ##
1. Run the server
![server-run](https://i.imgur.com/2Vijb7r.jpg)

2. Run a client
![client-run](https://i.imgur.com/rqrmRcY.jpg)

3. The server will indicate that a user is connected and the server log will update the
number of clients connected.
![user connection](https://i.imgur.com/mQdsBtr.jpg)

4. On the client side, press the "Generate maze" button.

5. A "Generate Maze" window will open. The client will choose a name for the maze,
the dimensions of the maze and the algorithm by which the maze will be generated.
<img src="https://i.imgur.com/JgLjfFz.jpg" width="340">

6. By pressing the "Generate" button, a query will be sent to server to check if the
name is available. If the name already exists, an error massage will appear
prompting the user to choose a different name.
<img src="https://i.imgur.com/P5H7VVb.jpg" width="340">

7. If the name is available than a maze will be generated and displayed on the client
GUI, Showing Pumbaa at the starting point of the maze.
![maze](https://i.imgur.com/mkw4ooC.jpg)

8. Use the keyboard arrow keys to move around the plane and the "page up"/"page
down" keys to move between floors.

9. In case the user is having trouble solving the maze, he can ask the server for a hint
by pressing the "Hint" button.

10. The server will solve the maze and hint the user by randomly spreading red bugs
along the solution path.
![hint](https://i.imgur.com/aSKpb1p.jpg)

11. Every maze that the server solves will be saved in the server's cache memory.
![maze in cache](https://i.imgur.com/j6TAKxc.jpg)

12. The user can use the "Solve maze" button in order to view the full solution of the
maze.

13. The game ends when Pumbaa finds Timon that's waiting for him with a plate of
bugs. A massage of "Game Over" will appear.
![game over](https://i.imgur.com/vUuLqz6.jpg)

14. The server can handle several clients. The connected clients will appear in the left
side of the server GUI window.
![multiple clients](https://i.imgur.com/TKTQbOW.jpg)

15. Pressing the "Disconnect" button will terminate the connection between the server
and the specific client. The client won't be able to request a hint or solution or
generate a new maze.
![disconnect client](https://i.imgur.com/54nZx8i.jpg)

16. In order to close the server, press the "Terminate the Server gracefully". The server
will no longer accept new clients and after all client dialogs will terminate, it will
close.
![terminate server](https://i.imgur.com/UTGVRL7.jpg)

17. On the client GUI window, in the "File" menu the user can choose to "Load" a maze
from local file or "Save" the current maze.
![load/save](https://i.imgur.com/ezqb3xo.jpg)

18. Choosing "Properties" will display the system properties as defined in the
"properties.xml" file found in the utilities directory.
<img src="https://i.imgur.com/HH787vc.jpg" width="250">

19. Switching to cli mode is done by typing "cli" instead of "gui" in the "properties.xml"
file found in the utilities directory.


### _Note:_ You can also choose to use a CLI interface instead of the GUI, following is the full instructions set:###

## INSTRUCTIONS SET
•	generate_maze
<name> <z> <y> <x> <"simple"/"growingLast"/"growingRandom">

•	display <name>

•	solve <name> <"BFS"/"DFS">

•	display_solution <name>

•	save_maze <name> <file name>

•	load_maze <file name> <name>

•	display_cross_section <line number> <axis> <name>

•	dir <path>

•	exit

**_Developed by Edan Leibovitz_**

**_edan.leibo@gmail.com_**

**:smile: Feel free to use it at will with attribution of source. :bowtie:**

**:astonished: If you find any typos, errors, inconsistencies etc. please do not hesitate to contact me for any issue. :mailbox:**

