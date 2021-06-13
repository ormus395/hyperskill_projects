// package Cinema;

/*

   Create a program that allows users to buy multiple tickets
   calulates ticket price
   then returns the ticket price and prints the cinema floor state
   bases of menu input
*/

import java.util.Scanner;

public class Cinema {
   
   static int numTickets = 0;
   static int numRows = 0;
   static int numSeats = 0;
   static int currentIncome = 0;

   static Scanner kb = new Scanner(System.in);

   public static void main(String[] args) {
            
      println("Enter the number of rows:");
      numRows = getInt();

      println("Enter the number of seats in each row:");
      numSeats = getInt();

      int totalSeats = numSeats * numRows;
      char[][] cinemaFloor = new char[numRows][numSeats];

      while(true) {
         int menuOption = 0;
         displayMenu();
         menuOption = getInt();

         if(menuOption == 0) {
            break;
         }

         switch(menuOption) {
            case 1:
               printRoom(cinemaFloor);
               break;
            case 2:
               // buy a ticket
               int[] ticket = buyTicket(cinemaFloor);
               cinemaFloor[ticket[0] - 1][ticket[1] - 1] = 'B';
               break;
            case 3:
               printStats();
               break;
            default:
               println("Invalid menu option");
         }

      }
   }

   // print the cinema floor
   public static void printRoom(char[][] cinemaFloor) {
      int rows = cinemaFloor.length;
      int numSeats = cinemaFloor[0].length;

      println("Cinema:");
      for(int i = 1; i <= numSeats; i++) {
         if(i == 1) {
            System.out.print(" ");
         }
         System.out.print(" " + i);
      }

      println("");
      
      for(int row = 0; row < rows; row++) {
         System.out.print((row + 1) + " ");
         for(int col = 0; col < cinemaFloor[row].length; col++) {
            if(cinemaFloor[row][col] == '\u0000') {
               System.out.print("S ");
            } else {
               System.out.print(cinemaFloor[row][col] + " ");
            }
         }
         println("");
      }
   }

   public static void println(String arg) {
      System.out.println(arg);
   }

   public static int getInt() {
      while(true) {
         try {
            int enteredValue = kb.nextInt();
            if(enteredValue < 0) {
               println("Please enter a positive number");
            }

            return enteredValue;
         } catch(Exception error) {
            println("Please enter an integer");
         }
      }
   }

   public static void displayMenu() {
      String[] menu = {"1: Show the seats", "2. Buy a ticket","3. Statistics", "0. Exit"};

      for(int i = 0; i < menu.length; i++) {
         println(menu[i]);
      }
   }

   public static int getRow() {
      println("Enter a row number:");
      int ticketRow = getInt();
      boolean validRow = true;

      if(ticketRow > numRows) {
         validRow = !validRow;
      }

      while(!validRow) {
         println("Wrong input");
         ticketRow = getInt();

         if(ticketRow > numRows) {
            validRow = false;
         } else {
            validRow = true;
         }
      }

      return ticketRow;
   }

   public static int getSeat() {
      boolean validSeat = true;
      println("Enter a seat number in that row:");
      int ticketSeat = getInt();

      if(ticketSeat > numSeats) {
         validSeat = false;
      }

      while(!validSeat) {
         println("Wrong input");
         ticketSeat = getInt();

         if(ticketSeat <= numSeats) {
            validSeat = true;
         }
      }

      return ticketSeat;
   }

   public static int[] buyTicket(char[][] cinemaFloor) {
      boolean validTicket = true;

      int ticketRow = getRow();

      int ticketSeat = getSeat();

      // check if seat is takin
      if(cinemaFloor[ticketRow- 1][ticketSeat - 1] == 'B') {
         validTicket = false;
      }

      while(!validTicket) {
         println("That ticket has already been purchased");
         ticketRow = getRow();
         ticketSeat = getSeat();

         if(cinemaFloor[ticketRow - 1][ticketSeat - 1] != 'B') {
            validTicket = true;
         }
      }

      int ticketPrice = 0;

      if(numSeats * numRows <= 60) {
         ticketPrice = 10;
      } else {
         if(ticketRow > (numRows / 2)) {
            ticketPrice = 8;
         } else {
            ticketPrice = 10;
         }
      }

      println("Ticket price: $" + ticketPrice);

      // place the ticket and reprint
      int[] ticket = {ticketRow, ticketSeat};
      numTickets += 1;
      currentIncome += ticketPrice;
      return ticket;
   }

   public static int calcTotalIncome() {
      int income = 0;
      if (numRows * numSeats <= 60) {
         income = (numRows * numSeats) * 10;
      } else {
         int firstHalf = numRows / 2;
         int secondHalf = numRows - firstHalf;

         int firstHalfPrice = (firstHalf * numSeats) * 10;
         int secondHalfPrice = (secondHalf * numSeats) * 8;

         income = firstHalfPrice + secondHalfPrice;
      }

      return income;
   }

   public static void printStats() {
      int allPossibleTickets = numRows * numSeats;
      double percentPurchasedTickets = ((double)numTickets / allPossibleTickets) * 100d;
      int totalIncome = calcTotalIncome();

      
      println("Number of purchased tickets: " + numTickets);
      println(String.format("Percentage: %.2f%%", percentPurchasedTickets));
      println("Current income: $" + currentIncome);
      println("Total income: $" + totalIncome);
   }
}
