# Project 3 : GWack Slack Simulator

To test download all files and run GWack.jar

## Describe the OOP design of your GWack Slack Simulator

Please provide a short description of your programming progress

ReaderT extends thread and handles the getting and setting of the member list and messages. 
Sender calls ReaderT and starts the the thread. In Sender there is a function to display a message.
GWackClientGUI handles the GUI design and checks if all the required information is correct.

## Workings
The IP Address and port numbers were programmed based on GW server, However these can be easily changed just by changing some conditional statemnets in the code. 
host: ssh-cs2113.adamaviv.com port: 8886
host: ssh-cs2113.adamaviv.com port: 8887
host: ssh-cs2113.adamaviv.com port: 8888

By connecting to a host and port, you will be able to chat with all people also connected to the same host and port. 

## What additional features did you attempt and how can we test them

- I added a status label, you can choose the status from the dropdown menu and have it displayed next to your name on the member list and when sending messages. To test choose a status, then disconnect and reconnect to the server. 
- I added a text color, text font, and theme. Text color lets you choose what color you want the messages to be, text font lets you choose what font you want the messages to be, and theme allows a dark and light mode. To test choose any of the options and the changes will take affect the next time you send a message. 
- to "pretty up" the GUI I changes the font of all the text and added a GWU logo, the light mode is also supposed to resemble the GWU colors. 
- implemented choices 4, 6, 7, and 9 from the possible feature upgrade list. 

