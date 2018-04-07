# OneTimePad
Implementation of OneTimePad

To run the project from Linux terminal use following command

```
javac OneTimePad.java  
java OneTimePad  
//In linux (Well, in my system I am getting a warning which can be ignored.)

This will print the current data in the files and will give you options to choose the function as follows.
___________________________________________________________________________________________
Current Data in file key.text: 01010101101010101111000000001111
Current Data in file plaintext.txt: cats
Current Data in file ciphertext.txt:6Ë|
Current Data in file newkey.txt:00110100
Current Data in file resultText: cats

Following are the functionalities in the code, choose anyone:
1.Encoding
2.Decoding
3.KeyGen
4.Frequency Distribution
Please choose the value in between 1,2,3, or 4.
____________________________________________________________________________________________
Here you will need to input the number corresponding to the function.
once the function is executed it will print the updated status of all the files.
once that part is done, the program will ask you if you want to re-run the program, you can select 1 to rerun it or 2 to terminate the program.
____________________________________________________________________________________________
Do you want to re-run the program?
Please choose from the following.
1. Yes
2. No
____________________________________________________________________________________________
if you select 1 it will reprint the function menu and if you press 2 the program will terminate.

*I have added additional code in OneTimePad.java file to test 2 locations for the 'Data' folder. So mostly there will not be any issue regarding the data folder path.
