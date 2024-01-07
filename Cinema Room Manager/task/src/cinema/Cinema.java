package cinema;
import java.util.Scanner;

public class Cinema {

    public static int pricecalculator(int rownumber, int frontRow, int takenSeats){
        int ticketprice;
        if(rownumber <= frontRow){
            ticketprice = 10;
        } else {
            ticketprice = 8;
        }
        return ticketprice;
    }
    public static void showStatistic(int ticketstotal, double percentage, int currentincome, int totalprice){
        System.out.printf("Number of purchased tickets: %d%n", ticketstotal);
        System.out.printf("Percentage: %.2f", percentage);
        System.out.println("%");
        System.out.printf("Current income: $%d%n", currentincome);
        System.out.printf("Total income: $%d%n", totalprice);
    }
    public static int printmenu(){
        Scanner scan = new Scanner(System.in);

        StringBuilder ask = new StringBuilder("""
               
        1. Show the seats
        2. Buy a ticket
        3. Statistics
        0. Exit""");
        System.out.println(ask);
        int choose = scan.nextInt();
        return choose;
    }

    public static int[] buyTicket(char[][] matrix, int frontRow, int takenSeats, int row, int seatsinrow){

        Scanner scan = new Scanner(System.in);
        int[] testarray = new int[2];

        System.out.println("Enter a row number:");
        int rownumber = scan.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatnumberinrow = scan.nextInt();

        // seat doesnt exist?
        while(rownumber > row || seatnumberinrow > seatsinrow){
            System.out.println("Wrong input!");
            System.out.println("Enter a row number:");
            rownumber = scan.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatnumberinrow = scan.nextInt();
        }

        //ticket already purchased?
        while(matrix[rownumber][seatnumberinrow] == 'B'){
            System.out.println("That ticket has already been purchased!");
            System.out.println("Enter a row number:");
            rownumber = scan.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatnumberinrow = scan.nextInt();
        }


        matrix[rownumber][seatnumberinrow] = 'B';

        int myprice = pricecalculator(rownumber, frontRow, takenSeats);

        System.out.printf("%nTicket price: $%d%n", myprice);
        testarray[0] = myprice;
        testarray[1] = 1;

        return testarray;
    }
    public static void justPrint(char[][] matrix){
        System.out.println("Cinema:");
        for(int i = 0; matrix.length > i; i++){
            for(int j = 0; matrix[i].length > j; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("");
    }
    public static void printroom(char[][] matrix){

        // set all values to S
        for(int i = 0; matrix.length > i; i++){
            for(int j = 0; matrix[i].length > j; j++){
                matrix[i][j] = 'S';
            }
        }

        // overwrite first row and first column
        for(int i = 0; matrix.length > i; i++){
            matrix[i][0] = Character.forDigit(i, 10);
            for(int j = 0; matrix[i].length > j; j++){
            matrix[0][j] = Character.forDigit(j, 10);
            }
        }

        matrix[0][0] = (char) ' ';
    }

    // -------------- MAIN -----------
    // -------------- MAIN -----------
    // -------------- MAIN -----------
    // -------------- MAIN -----------

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("\nEnter the number of rows:");
        int rows = scan.nextInt();
        int backRow;
        int frontRow = rows/2;

        if(rows%2 == 0){
            backRow = rows/2;
        } else {
            backRow = rows/2 + 1;
        }


        System.out.println("Enter the number of seats in each row:");
        int seatsinrow = scan.nextInt();

        //calculations
        int takenSeats = rows * seatsinrow;
        int pricefront = 10;
        int priceback = 8;
        int frontRowPrice = frontRow * pricefront * seatsinrow;
        int backRowPrice = backRow * priceback * seatsinrow;
        char[][] matrix = new char[rows+1][seatsinrow+1];
        int ticketstotal = 0;
        int currentincome = 0;
        double percentage = 0.00;
        int totalPrice;


        totalPrice = frontRowPrice + backRowPrice;

        printroom(matrix);

        int choose = printmenu();

        while(choose != 0){
        switch(choose){
            case 1: justPrint(matrix);
                    choose = printmenu();
                    break;
            case 2: int[] mytest = buyTicket(matrix, frontRow, takenSeats, rows, seatsinrow);
                    ticketstotal += mytest[1];
                    currentincome += mytest[0];
                    choose = printmenu();
                    break;
            case 3:
                    percentage = ((double) ticketstotal / takenSeats) * 100;
                    showStatistic(ticketstotal, percentage, currentincome, totalPrice);
                    choose = printmenu();
                    break;
            case 0: System.exit(0); break;
            default:
                break;
        }
        }
    }
}