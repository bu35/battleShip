
/**
 * Write a description of class BattleShip here.
 * Before starting the assignment, read through the entire next slide. This will that several 
 * class periods so make plan.  You MUST plan your code in some type of Word document (pseudocode).
 * I encourage cooperation, a good plan makes writing code easier.

Plan all of your for loops, while loops, if-and-or statements, array declarations, etc…..

Create four 10-by-10 game boards – a “Ship Board” for each player (to place ships on), and a 
“Torpedo Board” for each player (to fire torpedoes on). Place a dash (-) in each “cell” of each board.
Start by asking each player to place his/her 3 ships:
First, display his/her Ship Board.
The Cruiser and the Destroyer are both 4 cells long; the Battleship is 5.
You must ask the player where to start placing a ship (for example, Row 3, Column 7),
and then asking which direction the ship should go from there (up, down, left, or right). 
(this will require some serious error checking!)
Assign an uppercase C (Cruiser), D (Destroyer), or B (Battleship) to the cells where a
ship is located. (you can be creative here)
Now…let the game begin. Display Player 1’s Ship Board and Torpedo Board. Let Player 1 
enter the coordinates of the location he would like to fire a torpedo at. Announce 
whether he hits or misses. If he hits, place an X in the location of the hit 
(on Player 1’s Torpedo Board, AND on Player 2’s Ship Board). If he misses, place an uppercase O.
Error checking: do not allow a player to fire at a location he has already fired at; 
there are only 10 rows & columns; some directions are impossible.
If a player sinks a ship, announce it. (“You sunk Player 2’s Destroyer!”).
After Player 1 fires, it is Player 2’s turn.
The players take turns until one player’s ships are all sunk.

 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class BattleShip
{
    private String[][] Board1,  Torpedo1, Board2, Torpedo2;
    private Person player1,player2;
    private String ship;
    private int count, cordx, cordy, direction, player1score, player2score;
    private boolean countdirection, firstplace;
    Scanner in= new Scanner(System.in);
    public BattleShip(Person x, Person y)
    {
        ship="empty";
        cordx=0;
        cordy=0;
        count = 0;
        direction = 5; // 0 = north or south, 1 = east or west
        firstplace = true; //first placing a ship
        Board1= new String[11][11]; //Player 1 battleship board// length 11 for index on board/* */ 
        Board2= new String[11][11]; //Player 2 battleship board
        Torpedo1= new String[11][11]; //Player 1 attack board
        Torpedo2= new String[11][11]; //Player 2 attack board
        Person player1= x;
        Person player2= y;
        player1score = 0; //If one reaches 10, game is over, !subject to change with addition of ships!
        player2score = 0;
    }

    public void startGame(Person x, int p)
    {
        if(p==1) //when sent value 1, runs through startup for player1.
        {
            for(int r=0;r<Board1.length;r++) 
            {
                for(int c=0; c<Board1[0].length; c++) //length of first row
                {
                    Board1[r][c] = "-";
                    Board1[0][0]=" ";
                    Board1[0][1]="1";
                    Board1[0][2]="2";
                    Board1[0][3]="3";
                    Board1[0][4]="4";
                    Board1[0][5]="5";
                    Board1[0][6]="6";
                    Board1[0][7]="7";
                    Board1[0][8]="8";
                    Board1[0][9]="9";
                    Board1[0][10]="0";
                    Board1[1][0]="1";
                    Board1[2][0]="2";
                    Board1[3][0]="3";
                    Board1[4][0]="4";
                    Board1[5][0]="5";
                    Board1[6][0]="6";
                    Board1[7][0]="7";
                    Board1[8][0]="8";
                    Board1[9][0]="9";
                    Board1[10][0]="0";

                    Torpedo1[r][c] = "-";
                    Torpedo1[0][0]=" ";
                    Torpedo1[0][1]="1";
                    Torpedo1[0][2]="2";
                    Torpedo1[0][3]="3";
                    Torpedo1[0][4]="4";
                    Torpedo1[0][5]="5";
                    Torpedo1[0][6]="6";
                    Torpedo1[0][7]="7";
                    Torpedo1[0][8]="8";
                    Torpedo1[0][9]="9";
                    Torpedo1[0][10]= "0";

                    Torpedo1[1][0]="1";
                    Torpedo1[2][0]="2";
                    Torpedo1[3][0]="3";
                    Torpedo1[4][0]="4";
                    Torpedo1[5][0]="5";
                    Torpedo1[6][0]="6";
                    Torpedo1[7][0]="7";
                    Torpedo1[8][0]="8";
                    Torpedo1[9][0]="9";
                    Torpedo1[10][0]= "0";

                }
            }

            while(count!=5) //BATTLESHIP 
            {
                if (count==0)
                {
                    try{
                        System.out.println("Player1 Ship Board (type exit to quit)");
                        printBoard(Board1);
                        System.out.println("Place your BattleShip(L=5)");
                        System.out.println("Enter Rows, Columns for your BattleShip(L=5)");
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        firstplace = true; //first time placing ship if count ==0
                        if(onBoardandValid(cordx, cordy, Board1)){
                            placeShip(Board1,"battleship");
                            firstplace = false; //After placed first piece, variable set to false, used in placeShip()
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Invalid");
                        }
                    }
                    catch (Exception e){
                       System.out.println('\f');
                       System.out.println("Error occured with inputs");
                       in.nextLine();
                    }
                }
                else if(count>0)
                {
                    try{
                        System.out.println("Player1 Ship Board (type exit to quit)");
                        printBoard(Board1);
                        System.out.println("Enter Rows, Columns for your BattleShip(L=5)");
                        printDirection(direction);
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        if(onBoardandValid(cordx,cordy, Board1))
                        {
                            placeShip(Board1, "battleship");
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                        }
                    }
                    catch (Exception e){
                        System.out.println('\f');
                        System.out.println("Error occured with inputs");
                        in.nextLine();
                    }
                }
            }
            count = 0;
            while (count!= 3)
            {
                if (count==0)
                {
                    try{
                        System.out.println("Player1 Ship Board (type exit to quit)");
                        printBoard(Board1);
                        System.out.println("Place your Cruiser(L=3)");
                        System.out.println("Enter Rows, Columns for your Cruiser(L=3)");
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        firstplace = true; //first time placing ship if count ==0
                        if(onBoardandValid(cordx, cordy, Board1)){
                            placeShip(Board1,"cruiser");
                            firstplace = false; //After placed first piece, variable set to false, used in placeShip()
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Invalid");
                        }
                    }
                    catch (Exception e){
                       System.out.println('\f');
                       System.out.println("Error occured with inputs");
                       in.nextLine();
                    }
                }
                else if(count>0)
                {
                    try{
                        System.out.println("Player1 Ship Board (type exit to quit)");
                        printBoard(Board1);
                        System.out.println("Enter Rows, Columns for your Cruiser(L=3)");
                        printDirection(direction);
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        if(onBoardandValid(cordx,cordy, Board1))
                        {
                            placeShip(Board1, "cruiser");
                         
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                            
                        }
                    }
                    catch (Exception e){
                        System.out.println('\f');
                        System.out.println("Error occured with inputs");
                        in.nextLine();
                    }
                }
            }
            count = 0;
            while (count!= 2)
            {
                if (count==0)
                {
                    try{
                        System.out.println("Player1 Ship Board (type exit to quit)");
                        printBoard(Board1);
                        System.out.println("Place your Destroyer(L=2)");
                        System.out.println("Enter Rows, Columns for your Destroyer(L=2)");
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        firstplace = true; //first time placing ship if count ==0
                        if(onBoardandValid(cordx, cordy, Board1)){
                            placeShip(Board1,"destroyer");
                            firstplace = false; //After placed first piece, variable set to false, used in placeShip()
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Invalid");
                        }
                    }
                    catch (Exception e){
                       System.out.println('\f');
                       System.out.println("Error occured with inputs");
                       in.nextLine();
                    }
                }
                else if(count>0)
                {
                    try{
                        System.out.println("Player1 Ship Board (type exit to quit)");
                        printBoard(Board1);
                        System.out.println("Enter Rows, Columns for your Destroyer(L=2)");
                        printDirection(direction);
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        if(onBoardandValid(cordx,cordy, Board1))
                        {
                            placeShip(Board1, "destroyer");
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                        }
                    }
                    catch (Exception e){
                        System.out.println('\f');
                        System.out.println("Error occured with inputs");
                        in.nextLine();
                    }
                }
            }
        }
        else if(p==2)
        {
            count =0;
            ship="empty";
            cordx=0;
            cordy=0;
            count = 0;
            direction = 5;
            for(int r=0;r<Board2.length;r++)
            {
                for(int c=0; c<Board2[0].length; c++)
                {
                    Board2[r][c] = "-";
                    Board2[0][0]=" ";
                    Board2[0][1]="1";
                    Board2[0][2]="2";
                    Board2[0][3]="3";
                    Board2[0][4]="4";
                    Board2[0][5]="5";
                    Board2[0][6]="6";
                    Board2[0][7]="7";
                    Board2[0][8]="8";
                    Board2[0][9]="9";
                    Board2[0][10]="0";
                    Board2[0][0]=" ";
                    Board2[1][0]="1";
                    Board2[2][0]="2";
                    Board2[3][0]="3";
                    Board2[4][0]="4";
                    Board2[5][0]="5";
                    Board2[6][0]="6";
                    Board2[7][0]="7";
                    Board2[8][0]="8";
                    Board2[9][0]="9";
                    Board2[10][0]="0";
                    Torpedo2[r][c] = "-";
                    Torpedo2[0][0]="1";
                    Torpedo2[0][1]="2";
                    Torpedo2[0][2]="3";
                    Torpedo2[0][3]="4";
                    Torpedo2[0][4]="5";
                    Torpedo2[0][5]="6";
                    Torpedo2[0][6]="7";
                    Torpedo2[0][7]="8";
                    Torpedo2[0][8]="9";
                    Torpedo2[0][9]="10";
                    Torpedo2[0][0]=" ";
                    Torpedo2[1][0]="1";
                    Torpedo2[2][0]="2";
                    Torpedo2[3][0]="3";
                    Torpedo2[4][0]="4";
                    Torpedo2[5][0]="5";
                    Torpedo2[6][0]="6";
                    Torpedo2[7][0]="7";
                    Torpedo2[8][0]="8";
                    Torpedo2[9][0]="9";
                    Torpedo2[10][0]="0";
                }
            }
            
            while(count!=5) //BATTLESHIP 
            {
                if (count==0)
                {
                    try{
                        System.out.println("Player2 Ship Board (type exit to quit)");
                        printBoard(Board2);
                        System.out.println("Place your BattleShip(L=5)");
                        System.out.println("Enter Rows, Columns for your BattleShip(L=5)");
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        firstplace = true; //first time placing ship if count ==0
                        if(onBoardandValid(cordx, cordy, Board2)){
                            placeShip(Board2,"battleship");
                            firstplace = false; //After placed first piece, variable set to false, used in placeShip()
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Invalid");
                        }
                    }
                    catch (Exception e){
                       System.out.println('\f');
                       System.out.println("Error occured with inputs");
                       in.nextLine();
                    }
                }
                else if(count>0)
                {
                    try{
                        System.out.println("Player2 Ship Board (type exit to quit)");
                        printBoard(Board2);
                        System.out.println("Enter Rows, Columns for your BattleShip(L=5)");
                        printDirection(direction);
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        if(onBoardandValid(cordx,cordy, Board2))
                        {
                            placeShip(Board2, "battleship");
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                        }
                    }
                    catch (Exception e){
                        System.out.println('\f');
                        System.out.println("Error occured with inputs");
                        in.nextLine();
                    }
                }
            }
            count = 0;
            while (count!= 3)
            {
                if (count==0)
                {
                    try{
                        System.out.println("Player2 Ship Board (type exit to quit)");
                        printBoard(Board2);
                        System.out.println("Place your Cruiser(L=3)");
                        System.out.println("Enter Rows, Columns for your Cruiser(L=3)");
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        firstplace = true; //first time placing ship if count ==0
                        if(onBoardandValid(cordx, cordy, Board2)){
                            placeShip(Board2,"cruiser");
                            firstplace = false; //After placed first piece, variable set to false, used in placeShip()
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Invalid");
                        }
                    }
                    catch (Exception e){
                       System.out.println('\f');
                       System.out.println("Error occured with inputs");
                       in.nextLine();
                    }
                }
                else if(count>0)
                {
                    try{
                        System.out.println("Player2 Ship Board (type exit to quit)");
                        printBoard(Board2);
                        System.out.println("Enter Rows, Columns for your Cruiser(L=3)");
                        printDirection(direction);
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        if(onBoardandValid(cordx,cordy, Board2))
                        {
                            placeShip(Board2, "cruiser");
                         
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                            
                        }
                    }
                    catch (Exception e){
                        System.out.println('\f');
                        System.out.println("Error occured with inputs");
                        in.nextLine();
                    }
                }
            }
            count = 0;
            while (count!= 2)
            {
                if (count==0)
                {
                    try{
                        System.out.println("Player2 Ship Board (type exit to quit)");
                        printBoard(Board2);
                        System.out.println("Place your Destroyer(L=2)");
                        System.out.println("Enter Rows, Columns for your Destroyer(L=2)");
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        firstplace = true; //first time placing ship if count ==0
                        if(onBoardandValid(cordx, cordy, Board2)){
                            placeShip(Board2,"destroyer");
                            firstplace = false; //After placed first piece, variable set to false, used in placeShip()
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Invalid");
                        }
                    }
                    catch (Exception e){
                       System.out.println('\f');
                       System.out.println("Error occured with inputs");
                       in.nextLine();
                    }
                }
                else if(count>0)
                {
                    try{
                        System.out.println("Player2 Ship Board (type exit to quit)");
                        printBoard(Board2);
                        System.out.println("Enter Rows, Columns for your Destroyer(L=2)");
                        printDirection(direction);
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        if(onBoardandValid(cordx,cordy, Board2))
                        {
                            placeShip(Board2, "destroyer");
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                        }
                    }
                    catch (Exception e){
                        System.out.println('\f');
                        System.out.println("Error occured with inputs");
                        in.nextLine();
                    }
                }
            }
        }
    }

    public void placeShip(String [][] s, String ship)
    {
        if(ship == "battleship")
        {
            if (firstplace) // first time placing ship
            {
                System.out.println("Which Direction Will the Ship Be Facing?\nOnly Valid directions(North,East,South,West)");
                String coordinate=in.nextLine();
                direction = directionOfShip(coordinate); //Direction of ship 0 = north/south 1 = east/west
                System.out.println("direction  = " + direction);
                /*
                 * NOTE, Ships battleship && Cruiser do not need the method enoughSpace if it's a game of 3 ships
                 * Only included just to have it work
                 * In the future for further implementation, this method will be needed after the 2nd ship has been placed.
                 * Also!!!!
                 * Only needed in the first time placing a ship -- checks direction and see if it works
                 * If it does not work then nothing happens, and it asks you to reinput your ship xcord and ycord
                 */
                if (enoughSpace(s, ship, direction, cordx, cordy)){ // If a ship can be placed along these lines
                    //Cordx and cordy will always be modified in the constructor
                    if(coordinate.equalsIgnoreCase("exit")){
                        System.exit(0);
                    }
                    if((direction == 1 || direction == 0))
                    {
                        s[cordx][cordy]="B";
                        count ++;
                        System.out.println('\f');
                    }
                    else
                    {
                        System.out.println('\f');
                        System.out.println("Invalid Direction");
                    }
                }
                else{
                    System.out.println('\f');
                    System.out.println("Can not place ship in this direction.");
                }
            }
            if (!firstplace) //Not first place.
            {
                System.out.println('\f'); // \f form feed - clears the terminal.
                if(direction == 0)//North or South //direction.equalsIgnoreCase ("north"))
                {
                    if(s[cordx][cordy]=="-" && (cordx==10 || cordy==10)){ // If it's on the endge of board and empty, allow to place. Handles errors
                        // in regard to out of bounds.
                        if(cordx == 10 && cordy == 10){
                            s[cordx][cordy]="B";
                            count ++;
                            System.out.println('\f');
                        }
                        else if(cordx==10 && (s[cordx-1][cordy] == "B")){
                            s[cordx][cordy]="B";
                            count ++;
                            System.out.println('\f');
                        }
                        else if(cordy==10 && (s[cordx-1][cordy] == "B" || s[cordx+1][cordy] == "B")){
                            s[cordx][cordy]="B";
                            count ++;
                            System.out.println('\f');
                        }
                        else
                        {
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                        }
                    }
                    else if(s[cordx][cordy]=="-" && (s[cordx+1][cordy]=="B" || s[cordx-1][cordy]=="B"))
                    {
                        s[cordx][cordy]="B";
                        count ++;
                        System.out.println('\f');
                    }
                    else
                    {
                        System.out.println('\f');
                        System.out.println("Impossible to place your ship here.");
                    }
                }
                else if (direction == 1)
                {
                    if((s[cordx][cordy]=="-") && (cordx==10 || cordy==10)){ // If it's on the endge of board and empty, allow to place. Handles errors
                       if(cordx == 10 && cordy == 10){
                            s[cordx][cordy]="B";
                            count ++;
                            System.out.println('\f');
                        }
                       else if(cordx==10 && (s[cordx][cordy+1] == "B" || s[cordx][cordy-1] == "B")){
                            s[cordx][cordy]="B";
                            count ++;
                            System.out.println('\f');
                       }
                       else if(cordy==10 && (s[cordx][cordy-1] == "B")){
                            s[cordx][cordy]="B";
                            count ++;
                            System.out.println('\f');
                        }
                       else
                        {
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                        }
                    }
                    else if(s[cordx][cordy]=="-" && (s[cordx][cordy+1]=="B" || s[cordx][cordy-1]=="B"))
                    {
                        s[cordx][cordy]="B";
                        count ++;
                        System.out.println('\f');
                    }
                    else
                    {
                        System.out.println('\f');
                        System.out.println("Impossible to place your ship here.");
                    }
                }
            }
        }
        /////////////////////////////////////////////////////////////////////////////////////////////
        else if(ship == "cruiser")//CRUISER
        {
            if (firstplace) // first time placing ship
            {
                System.out.println("Which Direction Will the Ship Be Facing?\nOnly Valid directions(North,East,South,West)");
                String coordinate=in.nextLine();
                direction = directionOfShip(coordinate); //Direction of ship 0 = north/south 1 = east/west
                if (enoughSpace(s, ship, direction, cordx, cordy)){
                    if(coordinate.equalsIgnoreCase("exit")){
                        System.exit(0);
                    }
                    if((direction == 1 || direction == 0))
                    {
                        s[cordx][cordy]="C";
                        count ++;
                        System.out.println('\f');
                    }
                    else
                    {
                        System.out.println('\f');
                        System.out.println("Invalid Direction");
                    }
                }
                else{
                    System.out.println('\f');
                    System.out.println("Can not place ship in this direction.");
                }
            }
            if (!firstplace) //Not first place.
            {
                System.out.println('\f'); // \f form feed - clears the terminal.
                if(direction == 0)//North or South //direction.equalsIgnoreCase ("north"))
                {
                    if(s[cordx][cordy]=="-" && (cordx==10 || cordy==10)){
                        if(cordx == 10 && cordy == 10){
                            s[cordx][cordy]="C";
                            count ++;
                           // System.out.println('\f');
                        }
                        else if(cordx==10 && (s[cordx-1][cordy] == "C")){
                            s[cordx][cordy]="C";
                            count ++;
                           // System.out.println('\f');
                        }
                        else if(cordy==10 && (s[cordx+1][cordy] == "C" || s[cordx-1][cordy] == "C")){
                            s[cordx][cordy]="C";
                            count ++;
                            System.out.println('\f');
                        }
                        else
                        {
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                        }
                    }
                    
                    else if(s[cordx][cordy]=="-" && (s[cordx+1][cordy]=="C" || s[cordx-1][cordy]=="C"))
                    {
                        s[cordx][cordy]="C";
                        count ++;
                        System.out.println('\f');
                    }
                    else
                    {
                        System.out.println('\f');
                        System.out.println("Impossible to place your ship here.");
                    }
                }
                else if (direction == 1) //E/W
                {
                    if(s[cordx][cordy]=="-" && (cordx==10 || cordy==10)){
                       if(cordx == 10 && cordy == 10){
                           s[cordx][cordy]="C";
                           count ++;
                           System.out.println('\f');
                        }
                       else if(cordx==10 && (s[cordx][cordy+1] == "C" || s[cordx][cordy-1] == "C")){
                           s[cordx][cordy]="C";
                           count ++;
                           System.out.println('\f');
                       }
                       else if(cordy==10 && (s[cordx][cordy-1] == "C")){
                           s[cordx][cordy]="C";
                           count ++;
                           System.out.println('\f');
                       }
                       else
                        {
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                        }
                    }
                    else if(s[cordx][cordy]=="-" && (s[cordx][cordy+1]=="C" || s[cordx][cordy-1]=="C"))
                    {
                        s[cordx][cordy]="C";
                        count ++;
                        System.out.println('\f');
                    }
                    else
                    {
                        System.out.println('\f');
                        System.out.println("Impossible to place your ship here.");
                    }
                }
            }
        }
    
        /////////////////////////////////////////////////////////////////////////////////////////////
        else if(ship == "destroyer")//CRUISER
        {
            if (firstplace) // first time placing ship
            {
                System.out.println("Which Direction Will the Ship Be Facing?\nOnly Valid directions(North,East,South,West)");
                String coordinate=in.nextLine();
                direction = directionOfShip(coordinate); //Direction of ship 0 = north/south 1 = east/west
                if (enoughSpace(s, ship, direction, cordx, cordy)){ // If a ship can be placed along these lines
                    //Cordx and cordy will always be modified in the constructor
                    if(coordinate.equalsIgnoreCase("exit")){
                        System.exit(0);
                    }
                    if((direction == 1 || direction == 0))
                    {
                        s[cordx][cordy]="D";
                        count ++;
                        System.out.println('\f');
                    }
                    else
                    {
                        System.out.println('\f');
                        System.out.println("Invalid Direction");
                    }
                }
                else{
                    System.out.println('\f');
                    System.out.println("Can not place ship in this direction.");
                }
            }
            if (!firstplace) //Not first place.
            {
                System.out.println('\f'); // \f form feed - clears the terminal.
                if(direction == 0)//North or South //direction.equalsIgnoreCase ("north"))
                {
                    if((s[cordx][cordy]=="-") && (cordx==10 || cordy==10)){
                        if(cordx == 10 && cordy == 10){
                            s[cordx][cordy]="D";
                            count ++;
                            System.out.println('\f');
                        }
                        else if(cordx==10 && (s[cordx-1][cordy] == "D")){
                            s[cordx][cordy]="D";
                            count ++;
                            System.out.println('\f');
                        }
                        else if(cordy==10 && (s[cordx+1][cordy] == "D" || s[cordx-1][cordy] == "D")){
                            s[cordx][cordy]="D";
                            count ++;
                            System.out.println('\f');
                        }
                        else
                        {
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                        }
                    }
                    else if((s[cordx][cordy]=="-") && (s[cordx+1][cordy]=="D" || s[cordx-1][cordy]=="D"))
                    {
                        s[cordx][cordy]="D";
                        count ++;
                        System.out.println('\f');
                    }
                    else
                    {
                        System.out.println('\f');
                        System.out.println("Impossible to place your ship here.");
                    }
                }
                else if (direction == 1)
                {
                    if((s[cordx][cordy]=="-") && (cordx==10 || cordy==10)){
                        if(cordx == 10 && cordy == 10){
                            s[cordx][cordy]="D";
                            count ++;
                            System.out.println('\f');
                        }
                       else if(cordx==10 && (s[cordx][cordy+1] == "D" || s[cordx][cordy-1] == "D")){
                            s[cordx][cordy]="D";
                            count ++;
                            System.out.println('\f');
                       }
                       else if(cordy==10 && (s[cordx][cordy-1] == "D")){
                            s[cordx][cordy]="D";
                            count ++;
                            System.out.println('\f');
                       }
                           else
                        {
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                        }
                    }
                    else if((s[cordx][cordy]=="-") && (s[cordx][cordy+1]=="D" || s[cordx][cordy-1]=="D"))
                    {
                        s[cordx][cordy]="D";
                        count ++;
                        System.out.println('\f');
                    }
                    else
                    {
                        System.out.println('\f');
                        System.out.println("Impossible to place your ship here.");
                    }
                }
            }
        }
    }
    
    public boolean onBoardandValid(int x, int y, String[][] s){ //Checks if it is on the board and a valid position
        if(x>0 && y>0 && x<11 && y<11 && s[x][y] == "-"){
            return true;
        }
        else{
            return false;
        }
    }
    
    public int directionOfShip(String direction){//Checks the direction the ship is, returns a number
        if(direction.equalsIgnoreCase("north") || direction.equalsIgnoreCase("south")){
            return 0;
        }
        else if (direction.equalsIgnoreCase("east") || direction.equalsIgnoreCase("west")){
            return 1;
        }
        else{
            return 3; 
        }
    }
    
    public boolean enoughSpace(String[][] s, String ship, int direction, int xpos, int ypos){//Checks if a ship can be placed a certain way
        //Only needed after first 2 ships placed. Cant place a ship between 2 and have it intersect.
        //Works as of having 3 ships.
        //Code looks good to me, can definitely be condensed.
        int count = 0; //count used as a substitution for ship length
        int possiblePos= 1;
        int trackerOne = 1; //directional tracker counter on battleship board
        int trackerTwo = 1; //One for each direction
        int cancelLoop = 0; //If a value of 2 is reached, then the ship can not be placed in current direction
        //The above value is added if the next spot in a unique direction is not empty(-)
        boolean northCancel = false; //Boolean variables to allow addition to cancelLoop only once
        boolean southCancel = false;
        boolean eastCancel = false;
        boolean westCancel = false;
        if (ship.equals("battleship")){
            count=5;
        }
        else if(ship.equals("cruiser")){
            count=4;
        }
        else if(ship.equals("destroyer")){ //Stays as else if in case wants to add more ships in the future.
            count=2;
        }
        if(direction == 0) //Placing ship North/South
        {
            while(possiblePos != count){
                if((xpos == 10)&& !southCancel){
                    southCancel = true;
                    cancelLoop+=1;
                }
                if(xpos + trackerOne > 10 && !southCancel){
                    possiblePos+=1;
                    southCancel=true;
                    cancelLoop+=1;
                }
                if(xpos + trackerOne < 11){
                    if(s[xpos+trackerOne][ypos] == "-" && !southCancel){ //First statement checks South
                        possiblePos+=1;
                        trackerOne+=1; //Next Array position
                    }
                    else if((s[xpos+trackerOne][ypos] != "-") && !southCancel){ //If space is not empty in the South Direction, adds one to canceloop
                        cancelLoop +=1;
                        southCancel = true;
                    }
                }
                if (s[xpos-trackerTwo][ypos] == "-" && !northCancel){ //Checks North
                    possiblePos+=1;
                    trackerTwo+=1;
                }
                else if((s[xpos-trackerTwo][ypos] != "-") && !northCancel){ //If space is not empty in the North Direction, adds one to canceloop
                    cancelLoop +=1;
                    northCancel = true;
                    }
                if (cancelLoop == 2){
                    break;
                }
            }
        }
        if(direction == 1) //Placing ship East/West
        {
            while(possiblePos < count){
                if((ypos == 10) && !eastCancel){
                    eastCancel = true;
                    cancelLoop+=1;
                }
                if(ypos + trackerOne > 10 && !eastCancel){
                    possiblePos+=1;
                    eastCancel=true;
                    cancelLoop+=1;
                }
                if(ypos + trackerOne <11){ // out of bounds handeling
                    if(s[xpos][ypos+trackerOne] == "-" && !eastCancel){ //First statement checks East
                        possiblePos+=1;
                        trackerOne+=1; //Next Array position
                    }
                    else if((s[xpos][ypos+trackerOne] != "-") && !eastCancel){ //If space is not empty in the South Direction, adds one to canceloop
                        cancelLoop +=1;
                        eastCancel = true;
                    }
                }
                if (s[xpos][ypos-trackerTwo] == "-" && !westCancel){ //Checks North
                    possiblePos+=1;
                    trackerTwo+=1;
                }
                else if((s[xpos][ypos-trackerTwo] != "-") && !westCancel){ //If space is not empty in the North Direction, adds one to canceloop
                    cancelLoop +=1;
                    westCancel = true;
                }
                if (cancelLoop == 2){
                    break;
                }
            }
        }
        if(possiblePos >= count){
            return true;
        }
        else{ // When cancelLoop = 2 
            return false;
        }
    }
    
    public void printDirection(int x){
        if(x == 0){
            System.out.println("Current Direction = North/South");
        }
        else if(x == 1){
            System.out.println("Current Direction = East/West");
        }
    }
    //private String[][] Board1,  Torpedo1, Board2, Torpedo2;
    public String[][] getBoard(int x){
        if(x==1){
            return Board1;
        }
        else if(x==2){
            return Board2;
        }
        else if(x==3){
            return Torpedo1;
        }
        else{
            return Torpedo2;
        }
    }
    
    public void attack(int player, int x, int y, String[][] boardAttacked, String[][] playerAttackBoard){
        Scanner in = new Scanner(System.in);
        boolean roundCompleted = false;
        
        if (player == 1){
            if(((x>0 && x < 11) || (y>0 && y<11)) && playerAttackBoard[x][y] == "-"){
                roundCompleted = true;
            }
            while (!roundCompleted){ //if invalid position
                System.out.println("Please Enter Valid Coordinates");
                String xtemp = in.nextLine();
                if(xtemp.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                    System.exit(0);
                }
                String ytemp = in.nextLine();
                if(ytemp.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                         System.exit(0);
                }
                x = Integer.parseInt(xtemp);
                y = Integer.parseInt(ytemp);
                if(((x<0 && x > 11) || (y<0 && y>11)) && playerAttackBoard[x][y] == "-"){
                    roundCompleted = true;
                }
            }
            if(boardAttacked[x][y]!="-"){ //If hit
                playerAttackBoard[x][y] = "X";
                boardAttacked[x][y] = "X";
                player1score+=1;
                System.out.println("Hit!");
            }
            else{
                playerAttackBoard[x][y] = "O";
                boardAttacked[x][y] = "O";
                System.out.println("Miss!");
            }
        }
        else if (player == 2){
            if(((x>0 && x < 11) || (y>0 && y<11)) && playerAttackBoard[x][y] == "-"){
                roundCompleted = true;
            }
            while (!roundCompleted){ //if invalid position
                System.out.println("Please Enter Valid Coordinates");
                String xtemp = in.nextLine();
                if(xtemp.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                    System.exit(0);
                }
                String ytemp = in.nextLine();
                if(ytemp.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                         System.exit(0);
                }
                x = Integer.parseInt(xtemp);
                y = Integer.parseInt(ytemp);
                if(((x<0 && x > 11) || (y<0 && y>11)) && playerAttackBoard[x][y] == "-"){
                    roundCompleted = true;
                }
            }
            if(boardAttacked[x][y]!="-"){ //If hit
                playerAttackBoard[x][y] = "X";
                boardAttacked[x][y] = "X";
                player2score+=1;
                System.out.println("Hit!");
            }
            else{
                playerAttackBoard[x][y] = "O";
                boardAttacked[x][y] = "O";
                System.out.println("Miss!");
            }
        }
    }
    
    public boolean gameOver(int player){
        if(player == 1){
            if(player1score == 10){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(player2score==10){
                return true;
            }
            else{
                return false;
            }
        }
    }
    
    public int returnScore(int i){
        if(i == 1){
            return player1score;
        }
        else{
            return player2score;
        }
    }
  
    public static void printBoard(String[][] x)
    {
        for(int p=0; p<x.length; p++)
        {
            for(int z=0; z<x[0].length; z++)
            {
                System.out.print(x[p][z] + " ");
            }
            System.out.println();
        }
    }
    
    
    
    
    
    
    /////////////////////////////////////RESET METHOD BELOW!
    public void reset(int p)
    {
        ship="empty";
        cordx=0;
        cordy=0;
        count = 0;
        direction = 5; // 0 = north or south, 1 = east or west
        firstplace = true; //first placing a ship
        if(p==1) //when sent value 1, runs through startup for player1.
        {
            for(int r=0;r<Board1.length;r++) 
            {
                for(int c=0; c<Board1[0].length; c++) //length of first row
                {
                    Board1[r][c] = "-";
                    Board1[0][0]=" ";
                    Board1[0][1]="1";
                    Board1[0][2]="2";
                    Board1[0][3]="3";
                    Board1[0][4]="4";
                    Board1[0][5]="5";
                    Board1[0][6]="6";
                    Board1[0][7]="7";
                    Board1[0][8]="8";
                    Board1[0][9]="9";
                    Board1[0][10]="0";
                    Board1[1][0]="1";
                    Board1[2][0]="2";
                    Board1[3][0]="3";
                    Board1[4][0]="4";
                    Board1[5][0]="5";
                    Board1[6][0]="6";
                    Board1[7][0]="7";
                    Board1[8][0]="8";
                    Board1[9][0]="9";
                    Board1[10][0]="0";

                    Torpedo1[r][c] = "-";
                    Torpedo1[0][0]=" ";
                    Torpedo1[0][1]="1";
                    Torpedo1[0][2]="2";
                    Torpedo1[0][3]="3";
                    Torpedo1[0][4]="4";
                    Torpedo1[0][5]="5";
                    Torpedo1[0][6]="6";
                    Torpedo1[0][7]="7";
                    Torpedo1[0][8]="8";
                    Torpedo1[0][9]="9";
                    Torpedo1[0][10]= "0";

                    Torpedo1[1][0]="1";
                    Torpedo1[2][0]="2";
                    Torpedo1[3][0]="3";
                    Torpedo1[4][0]="4";
                    Torpedo1[5][0]="5";
                    Torpedo1[6][0]="6";
                    Torpedo1[7][0]="7";
                    Torpedo1[8][0]="8";
                    Torpedo1[9][0]="9";
                    Torpedo1[10][0]= "0";

                }
            }

            while(count!=5) //BATTLESHIP 
            {
                if (count==0)
                {
                    try{
                        System.out.println("Player1 Ship Board (type exit to quit)");
                        printBoard(Board1);
                        System.out.println("Place your BattleShip(L=5)");
                        System.out.println("Enter Rows, Columns for your BattleShip(L=5)");
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        firstplace = true; //first time placing ship if count ==0
                        if(onBoardandValid(cordx, cordy, Board1)){
                            placeShip(Board1,"battleship");
                            firstplace = false; //After placed first piece, variable set to false, used in placeShip()
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Invalid");
                        }
                    }
                    catch (Exception e){
                       System.out.println('\f');
                       System.out.println("Error occured with inputs");
                       in.nextLine();
                    }
                }
                else if(count>0)
                {
                    try{
                        System.out.println("Player1 Ship Board (type exit to quit)");
                        printBoard(Board1);
                        System.out.println("Enter Rows, Columns for your BattleShip(L=5)");
                        printDirection(direction);
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        if(onBoardandValid(cordx,cordy, Board1))
                        {
                            placeShip(Board1, "battleship");
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                        }
                    }
                    catch (Exception e){
                        System.out.println('\f');
                        System.out.println("Error occured with inputs");
                        in.nextLine();
                    }
                }
            }
            count = 0;
            while (count!= 3)
            {
                if (count==0)
                {
                    try{
                        System.out.println("Player1 Ship Board (type exit to quit)");
                        printBoard(Board1);
                        System.out.println("Place your Cruiser(L=3)");
                        System.out.println("Enter Rows, Columns for your Cruiser(L=3)");
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        firstplace = true; //first time placing ship if count ==0
                        if(onBoardandValid(cordx, cordy, Board1)){
                            placeShip(Board1,"cruiser");
                            firstplace = false; //After placed first piece, variable set to false, used in placeShip()
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Invalid");
                        }
                    }
                    catch (Exception e){
                       System.out.println('\f');
                       System.out.println("Error occured with inputs");
                       in.nextLine();
                    }
                }
                else if(count>0)
                {
                    try{
                        System.out.println("Player1 Ship Board (type exit to quit)");
                        printBoard(Board1);
                        System.out.println("Enter Rows, Columns for your Cruiser(L=3)");
                        printDirection(direction);
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        if(onBoardandValid(cordx,cordy, Board1))
                        {
                            placeShip(Board1, "cruiser");
                         
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                            
                        }
                    }
                    catch (Exception e){
                        System.out.println('\f');
                        System.out.println("Error occured with inputs");
                        in.nextLine();
                    }
                }
            }
            count = 0;
            while (count!= 2)
            {
                if (count==0)
                {
                    try{
                        System.out.println("Player1 Ship Board (type exit to quit)");
                        printBoard(Board1);
                        System.out.println("Place your Destroyer(L=2)");
                        System.out.println("Enter Rows, Columns for your Destroyer(L=2)");
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        firstplace = true; //first time placing ship if count ==0
                        if(onBoardandValid(cordx, cordy, Board1)){
                            placeShip(Board1,"destroyer");
                            firstplace = false; //After placed first piece, variable set to false, used in placeShip()
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Invalid");
                        }
                    }
                    catch (Exception e){
                       System.out.println('\f');
                       System.out.println("Error occured with inputs");
                       in.nextLine();
                    }
                }
                else if(count>0)
                {
                    try{
                        System.out.println("Player1 Ship Board (type exit to quit)");
                        printBoard(Board1);
                        System.out.println("Enter Rows, Columns for your Destroyer(L=2)");
                        printDirection(direction);
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        if(onBoardandValid(cordx,cordy, Board1))
                        {
                            placeShip(Board1, "destroyer");
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                        }
                    }
                    catch (Exception e){
                        System.out.println('\f');
                        System.out.println("Error occured with inputs");
                        in.nextLine();
                    }
                }
            }
        }
        else if(p==2)
        {
            count =0;
            ship="empty";
            cordx=0;
            cordy=0;
            count = 0;
            direction = 5;
            for(int r=0;r<Board2.length;r++)
            {
                for(int c=0; c<Board2[0].length; c++)
                {
                    Board2[r][c] = "-";
                    Board2[0][0]=" ";
                    Board2[0][1]="1";
                    Board2[0][2]="2";
                    Board2[0][3]="3";
                    Board2[0][4]="4";
                    Board2[0][5]="5";
                    Board2[0][6]="6";
                    Board2[0][7]="7";
                    Board2[0][8]="8";
                    Board2[0][9]="9";
                    Board2[0][10]="0";
                    Board2[0][0]=" ";
                    Board2[1][0]="1";
                    Board2[2][0]="2";
                    Board2[3][0]="3";
                    Board2[4][0]="4";
                    Board2[5][0]="5";
                    Board2[6][0]="6";
                    Board2[7][0]="7";
                    Board2[8][0]="8";
                    Board2[9][0]="9";
                    Board2[10][0]="0";
                    
                    Torpedo2[r][c] = "-";
                    Torpedo2[0][0]=" ";
                    Torpedo2[0][1]="1";
                    Torpedo2[0][2]="2";
                    Torpedo2[0][3]="3";
                    Torpedo2[0][4]="4";
                    Torpedo2[0][5]="5";
                    Torpedo2[0][6]="6";
                    Torpedo2[0][7]="7";
                    Torpedo2[0][8]="8";
                    Torpedo2[0][9]="9";
                    Torpedo2[0][10]="0";
                    Torpedo2[1][0]="1";
                    Torpedo2[2][0]="2";
                    Torpedo2[3][0]="3";
                    Torpedo2[4][0]="4";
                    Torpedo2[5][0]="5";
                    Torpedo2[6][0]="6";
                    Torpedo2[7][0]="7";
                    Torpedo2[8][0]="8";
                    Torpedo2[9][0]="9";
                    Torpedo2[10][0]="0";
                }
            }
            
            while(count!=5) //BATTLESHIP 
            {
                if (count==0)
                {
                    try{
                        System.out.println("Player2 Ship Board (type exit to quit)");
                        printBoard(Board2);
                        System.out.println("Place your BattleShip(L=5)");
                        System.out.println("Enter Rows, Columns for your BattleShip(L=5)");
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        firstplace = true; //first time placing ship if count ==0
                        if(onBoardandValid(cordx, cordy, Board2)){
                            placeShip(Board2,"battleship");
                            firstplace = false; //After placed first piece, variable set to false, used in placeShip()
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Invalid");
                        }
                    }
                    catch (Exception e){
                       System.out.println('\f');
                       System.out.println("Error occured with inputs");
                       in.nextLine();
                    }
                }
                else if(count>0)
                {
                    try{
                        System.out.println("Player2 Ship Board (type exit to quit)");
                        printBoard(Board2);
                        System.out.println("Enter Rows, Columns for your BattleShip(L=5)");
                        printDirection(direction);
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        if(onBoardandValid(cordx,cordy, Board2))
                        {
                            placeShip(Board2, "battleship");
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                        }
                    }
                    catch (Exception e){
                        System.out.println('\f');
                        System.out.println("Error occured with inputs");
                        in.nextLine();
                    }
                }
            }
            count = 0;
            while (count!= 3)
            {
                if (count==0)
                {
                    try{
                        System.out.println("Player2 Ship Board (type exit to quit)");
                        printBoard(Board2);
                        System.out.println("Place your Cruiser(L=3)");
                        System.out.println("Enter Rows, Columns for your Cruiser(L=3)");
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        firstplace = true; //first time placing ship if count ==0
                        if(onBoardandValid(cordx, cordy, Board2)){
                            placeShip(Board2,"cruiser");
                            firstplace = false; //After placed first piece, variable set to false, used in placeShip()
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Invalid");
                        }
                    }
                    catch (Exception e){
                       System.out.println('\f');
                       System.out.println("Error occured with inputs");
                       in.nextLine();
                    }
                }
                else if(count>0)
                {
                    try{
                        System.out.println("Player2 Ship Board (type exit to quit)");
                        printBoard(Board2);
                        System.out.println("Enter Rows, Columns for your Cruiser(L=3)");
                        printDirection(direction);
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        if(onBoardandValid(cordx,cordy, Board2))
                        {
                            placeShip(Board2, "cruiser");
                         
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                            
                        }
                    }
                    catch (Exception e){
                        System.out.println('\f');
                        System.out.println("Error occured with inputs");
                        in.nextLine();
                    }
                }
            }
            count = 0;
            while (count!= 2)
            {
                if (count==0)
                {
                    try{
                        System.out.println("Player2 Ship Board (type exit to quit)");
                        printBoard(Board2);
                        System.out.println("Place your Destroyer(L=2)");
                        System.out.println("Enter Rows, Columns for your Destroyer(L=2)");
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        firstplace = true; //first time placing ship if count ==0
                        if(onBoardandValid(cordx, cordy, Board2)){
                            placeShip(Board2,"destroyer");
                            firstplace = false; //After placed first piece, variable set to false, used in placeShip()
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Invalid");
                        }
                    }
                    catch (Exception e){
                       System.out.println('\f');
                       System.out.println("Error occured with inputs");
                       in.nextLine();
                    }
                }
                else if(count>0)
                {
                    try{
                        System.out.println("Player2 Ship Board (type exit to quit)");
                        printBoard(Board2);
                        System.out.println("Enter Rows, Columns for your Destroyer(L=2)");
                        printDirection(direction);
                        String inputX= in.nextLine(); //Taken as a string to check if want to exit.
                        if(inputX.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                            System.exit(0);
                        }
                        String inputY= in.nextLine();
                        if(inputY.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                            System.exit(0);
                        }
                        cordx = Integer.parseInt(inputX);
                        cordy = Integer.parseInt(inputY);
                        if(onBoardandValid(cordx,cordy, Board2))
                        {
                            placeShip(Board2, "destroyer");
                        }
                        else{
                            System.out.println('\f');
                            System.out.println("Impossible to place your ship here.");
                        }
                    }
                    catch (Exception e){
                        System.out.println('\f');
                        System.out.println("Error occured with inputs");
                        in.nextLine();
                    }
                }
            }
        }
    }
}