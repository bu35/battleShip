
/**
 * Write a description of class BattleShipClient here.
 * The Client handles the running of the game. First checks inputs to start the game. Does not account for whitespace!!!!
 * Begins the setup for each player
 * After each player is setup, game is run until one player wins
 * To exit anytime player enters exit, accounts for capitilzation
 * Player class really isn't utilized well. All this code could be separated into certain classes.
 * In the future, will implement above.
 * @Bryan Ung
 * @11/22/2018
 */
import java.util.Scanner;
public class BattleShipClient
{
    public static void main()
    {
        Scanner in = new Scanner(System.in);
        Person player1= new Person(); //Again, player class not really used here
        Person player2= new Person(); //Recreation of a Battleship game where I was new to Java. Kept class as a formality
        BattleShip game = new BattleShip(player1, player2);
        boolean victory = false;
        int turn= 1;
        
        
        
        System.out.println("--------Welcome To BattleShip Game--------");
        System.out.println("     To exit at anytime, type \"exit\"\n             To Start Type \"Y\"");
        String begin = in.nextLine();
        if(!(begin.equalsIgnoreCase("y")) || begin.equalsIgnoreCase("exit")){
            System.out.println("Program will terminate");
            System.exit(0);
        }
        System.out.println('\f');
        if(begin.equalsIgnoreCase("y")){
            game.startGame(player1, 1);
            System.out.println("Player1 Board");
            BattleShip.printBoard(game.getBoard(1));
            System.out.println("Are You Satistfied with your board (Y/N)");
            String satisfied = in.nextLine();
            if (satisfied.equalsIgnoreCase("exit")){
                    System.exit(0);
            }
            while(!satisfied.equalsIgnoreCase("Y")){
                System.out.println('\f');
                game.reset(1);
                System.out.println("Player1 Board");
                BattleShip.printBoard(game.getBoard(1));
                System.out.println("Are You Satistfied with your board (Y/N)");
                if (satisfied.equalsIgnoreCase("exit")){
                    System.exit(0);
                }
                satisfied = in.nextLine();
            }
        }
        else{
            while(!(begin.equalsIgnoreCase("y"))){
                System.out.println("Type Y to initiate setup for player 2");
                begin = in.nextLine();
                if(begin.equalsIgnoreCase("exit")){
                    System.exit(0);
                }
            }
        }
        System.out.println('\f');
        System.out.println("Type Y to initiate setup for player 2");
        String begin2 = in.nextLine();
        if(begin2.equalsIgnoreCase("exit")){
            System.exit(0);
        }
        if(begin2.equalsIgnoreCase("y")){
            game.startGame(player2, 2);
            System.out.println("Player2 Board");
            BattleShip.printBoard(game.getBoard(2));
            System.out.println("Are You Satistfied with your board (Y/N)");
            String satisfied = in.nextLine();
            if (satisfied.equalsIgnoreCase("exit")){
                    System.exit(0);
                }
            while(!satisfied.equalsIgnoreCase("Y")){
                System.out.println('\f');
                game.reset(2);
                System.out.println("Player2 Board");
                BattleShip.printBoard(game.getBoard(2));
                System.out.println("Are You Satistfied with your board (Y/N)");
                if (satisfied.equalsIgnoreCase("exit")){
                    System.exit(0);
                }
                satisfied = in.nextLine();
            }
        }
        else{
            while(!(begin2.equalsIgnoreCase("y"))){
                System.out.println("Type Y to initiate setup for player 2");
                begin2 = in.nextLine();
                if(begin2.equalsIgnoreCase("exit")){
                    System.exit(0);
                }
            }
        }
        System.out.println('\f');
        System.out.println("----Game Will Begin----\nPlayer 1 will begin\nType \"Y\" to begin");
        while(!victory){
            if(turn == 1){
                System.out.println("To show board, enter \"Y\"");
                String show = in.nextLine();
                if(show.equalsIgnoreCase("y")){
                    System.out.println("Player1 Ship Board");
                    game.printBoard(game.getBoard(1));
                    System.out.println("Player1 Torepdo Board");
                    game.printBoard(game.getBoard(3));
                    System.out.println("Enter Coords to Attack!");
                    String xtemp = in.nextLine();
                    if(xtemp.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                         System.exit(0);
                    }
                    String ytemp = in.nextLine();
                    if(ytemp.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                         System.exit(0);
                    }
                    int x = Integer.parseInt(xtemp);
                    int y = Integer.parseInt(ytemp);
                    //(int player, int x, int y, String[][] boardAttacked, String[][] playerAttackBoard)
                    System.out.println('\f');
                    game.attack(1, x , y, game.getBoard(2), game.getBoard(3));
                    System.out.println("Player1 Ship Board");
                    game.printBoard(game.getBoard(1));
                    System.out.println("Player1 Torepdo Board");
                    game.printBoard(game.getBoard(3));
                    System.out.println("Enter \"Y\" to end Turn");
                    String endTurn=in.nextLine();
                    if(endTurn.equalsIgnoreCase("exit")){
                        System.exit(0);
                    }
                    if(endTurn.equalsIgnoreCase("Y")){
                        System.out.println('\f');
                        turn = 2;
                    }
                    else{
                        while(!(endTurn.equalsIgnoreCase("Y"))){
                            System.out.println("Enter \"Y\" to end Turn");
                            endTurn=in.nextLine();
                        }
                    }
                    System.out.println('\f');
                }
                if(game.gameOver(1)){
                    victory = true;
                }
            }
            else if(turn == 2){
                System.out.println("To show board, enter \"Y\"");
                String show = in.nextLine();
                if(show.equalsIgnoreCase("y")){
                    System.out.println("Player2 Ship Board");
                    game.printBoard(game.getBoard(2));
                    System.out.println("Player2 Torepdo Board");
                    game.printBoard(game.getBoard(4));
                    System.out.println("Enter Coords to Attack!");
                    String xtemp = in.nextLine();
                    if(xtemp.equalsIgnoreCase("exit")){ //Checks first Value if exit is called
                         System.exit(0);
                    }
                    String ytemp = in.nextLine();
                    if(ytemp.equalsIgnoreCase("exit")){ //Checks second Value if exit is called
                         System.exit(0);
                    }
                    int x = Integer.parseInt(xtemp);
                    int y = Integer.parseInt(ytemp);
                    System.out.println('\f');
                    game.attack(2, x , y, game.getBoard(1), game.getBoard(4));
                    System.out.println("Player2 Ship Board");
                    game.printBoard(game.getBoard(2));
                    System.out.println("Player2 Torepdo Board");
                    game.printBoard(game.getBoard(4));
                    System.out.println("Enter \"Y\" to end Turn");
                    String endTurn=in.nextLine();
                    if(endTurn.equalsIgnoreCase("exit")){
                        System.exit(0);
                    }
                    if(endTurn.equalsIgnoreCase("Y")){
                        System.out.println('\f');
                        turn = 1;
                    }
                    else{
                        while(!(endTurn.equalsIgnoreCase("Y"))){
                            System.out.println("Enter \"Y\" to end Turn");
                            endTurn=in.nextLine();
                        }
                    }
                }
                if(game.gameOver(2)){
                    victory = true;
                }
                System.out.println('\f');
            }
        }
        if(game.returnScore(1) == 10){
            System.out.println('\f');
            System.out.println("Player 1 is Victorious, thank you for playing!");
        }
        else if(game.returnScore(2) == 10){
            System.out.println('\f');
            System.out.println("Player 2 is Victorious, thank you for playing!");
        }
    }
}
