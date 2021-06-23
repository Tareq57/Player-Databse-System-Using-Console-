package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Action {


    public static void Start() {
        ArrayList<String> arrayList = DatabaseRead();
        ArrayList<Club> clubs = Add_in_Club(arrayList);
        while (true) {
            System.out.println("Main Menu:\n" +
                    "(1) Search Players\n" +
                    "(2) Search Clubs \n" +
                    "(3) Add Player\n" +
                    "(4) Exit System\n\n" + "Enter Your Option:");
            int option = take_int_input();
            if (option == 1) {
                Player_Searching_Option(clubs);
            } else if (option == 2) {
                Club_Searching_Option(clubs);

            } else if (option == 3) {
                Add_New_Player(clubs);
            } else if (option == 4) {
                Exit(clubs);
                return;

            } else {
                System.out.println("Please Select The Right Option!!");
            }
        }
    }

    public static ArrayList<Club> Add_in_Club(ArrayList<String> arrayList) {
        ArrayList<Club> clubs = new ArrayList<>();
        for (String str : arrayList) {

            String[] tokens = str.split(",", 9);
            Player player = new Player(tokens[0], tokens[1], Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), tokens[4], tokens[5], Integer.parseInt(tokens[6]), Double.parseDouble(tokens[7]),tokens[8]);
            boolean ok = true;
            for (Club club : clubs) {
                if (club.getName().equalsIgnoreCase(player.getClub())) {
                    club.addPlayer(player);
                    ok = false;
                    break;
                }

            }
            if (ok) {
                Club club = new Club();
                club.addPlayer(player);
                club.setName(player.getClub());
                clubs.add(club);
            }
        }
        return clubs;

    }

    public static void Player_Searching_Option(ArrayList<Club> clubs) {
        System.out.println("Player Searching Options:\n" +
                "(1) By Player Name\n" +
                "(2) By Club and Country\n" +
                "(3) By Position\n" +
                "(4) By Salary Range\n" +
                "(5) Country-wise player count\n" +
                "(6) By Category\n"+
                "(7) Back to Main Menu\n");
        System.out.println("Enter Your Option:");
        int option = take_int_input();
        if (option == 1) {
            Search_With_PlayerName(clubs);
            Player_Searching_Option(clubs);

        } else if (option == 2) {
            Search_With_Club_Country(clubs);
            Player_Searching_Option(clubs);
        } else if (option == 3) {
            Search_With_Position(clubs);
            Player_Searching_Option(clubs);
        } else if (option == 4) {
            Search_With_Salary(clubs);
            Player_Searching_Option(clubs);
        } else if (option == 5) {
            Search_With_Country(clubs);
            Player_Searching_Option(clubs);
        } else if (option == 6) {
            Search_With_Category(clubs);
            Player_Searching_Option(clubs);
        }
        else if (option==7){
            return;
        }
        else {
            System.out.println("Please Select The Right Option!!");
        }


    }

    private static void Search_With_Category(ArrayList<Club> clubs) {
        Scanner input=new Scanner(System.in);
        System.out.println("Enter The Category: ");
        String category=input.nextLine();
        ArrayList<Player> arrayList=new ArrayList<>();
        for(Club club:clubs)
        {
            for(Player player:club.getPlayers())
            {
                if(player.getCategory().equalsIgnoreCase(category))
                {
                    arrayList.add(player);
                }
            }
        }
        if(arrayList.isEmpty())
        {
            System.out.println("There are no such player in database or You input Wrong!!");
        }
        else
        {
            Collections.sort(arrayList,new SalarySort());
            for(Player player:arrayList)
            {
                print(player);
            }
        }
    }

    public static void Club_Searching_Option(ArrayList<Club> clubs) {
        System.out.println("Club Searching Options:\n" +
                "(1) Player(s) with the maximum salary of a club\n" +
                "(2) Player(s) with the maximum age of a club\n" +
                "(3) Player(s) with the maximum height of a club\n" +
                "(4) Total yearly salary of a club\n" +
                "(5) Back to Main Menu\n");
        System.out.println("Enter Your Option:");
        int option = take_int_input();
        if (option == 1) {
            Players_With_Maximum_Salary(clubs);
            Club_Searching_Option(clubs);
        } else if (option == 2) {
            Players_With_Maximum_Age(clubs);
            Club_Searching_Option(clubs);
        } else if (option == 3) {
            Players_With_Maximum_Height(clubs);
            Club_Searching_Option(clubs);
        } else if (option == 4) {
            Yearly_Salary(clubs);
            Club_Searching_Option(clubs);
        } else if (option == 5) {
            return;

        } else {
            System.out.println("Please Select The Right Option!!");
        }


    }

    public static void Add_New_Player(ArrayList<Club> clubs) {
        int capable = 7;
        boolean ok = true;
        Player player = Get_Players();
        for (Club club : clubs) {
            for (Player player1 : club.getPlayers()) {
                if (player1.getName().equalsIgnoreCase(player.getName())) {
                    System.out.println("Sorry!!There Exits Same Player's Name");
                    return;
                }
            }
        }
        //checking position
        if (!(player.getPosition().equalsIgnoreCase("Goalkeeper") || player.getPosition().equalsIgnoreCase("Defender") || player.getPosition().equalsIgnoreCase("Midfielder") || player.getPosition().equalsIgnoreCase("Forward"))) {
            System.out.println("Sorry!!This position Doesn't exists");
            return;
        }

        // checking number and capability
        for (Club club : clubs) {
            if (club.getName().equalsIgnoreCase(player.getClub())) {

                if (club.getPlayers().size() >= capable) {
                    System.out.println("Sorry!!The capability of this Club is full");
                    return;
                } else {
                    for (Player player1 : club.getPlayers()) {
                        if (player1.getNumber() == player.getNumber()) {
                            System.out.println("Sorry!!There Exists Same Player's Number in this Club");
                            return;
                        }

                    }
                    club.addPlayer(player);
                    ok = false;
                    break;
                }
            }

        }

        if (ok) {
            Club club = new Club();
            club.addPlayer(player);
            club.setName(player.getClub());
            clubs.add(club);
        }


    }

    public static void Exit(ArrayList<Club> clubs) {
        try (FileWriter fw = new FileWriter("players.txt")) {
            BufferedWriter bw = new BufferedWriter(fw);
            for (Club club : clubs) {
                for (Player player : club.getPlayers()) {
                    fw.write(player.getName() + "," + player.getCountry() + "," + player.getAge() + "," + player.getHeight() + "," + player.getClub() + "," + player.getPosition() + "," + player.getNumber() + "," + player.getWeeklySalary() +","+player.getCategory()+ "\r\n");
                }
            }
            fw.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static Player Get_Players() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter The Player's Name:");
        String Name = input.nextLine();
        System.out.println("Enter The Player's Country Name:");
        String Country = input.nextLine();
        System.out.println("Enter The Player's Club Name:");
        String Club = input.nextLine();
        System.out.println("Enter The Player's Position:");
        String Position = input.nextLine();
        System.out.println("Enter The Player's Age:");
        int Age = take_int_input();
        System.out.println("Enter The Player's Number:");
        int Number = take_int_input();
        System.out.println("Enter The Player's WeeklySalary:");
        double WeeklySalary = take_Double_input();
        System.out.println("Enter The Player's Height:");
        double Height = take_Double_input();
        System.out.println("Enter The Player's Category:");
        String Category = input.nextLine();
        Player player = new Player(Name, Country, Age, Height, Club, Position, Number, WeeklySalary,Category);
        return player;
    }

    public static int take_int_input() {
        Scanner input = new Scanner(System.in);
        int p = 0;
        while (true) {
            try {
                p = input.nextInt();
                if (p < 0) {
                    input.nextLine();
                    System.out.println("Please Enter a Valid Input:");
                    continue;
                }
                break;
            } catch (Exception e) {
                input.nextLine();
                System.out.println("Please Enter a Valid Input:");

            }
        }
        return p;
    }

    public static double take_Double_input() {
        Scanner input = new Scanner(System.in);
        double p = 0;
        while (true) {
            try {
                p = input.nextDouble();
                break;
            } catch (Exception e) {
                input.nextLine();
                System.out.println("Please Enter a Valid Input:");

            }
        }
        return p;
    }


    public static void Yearly_Salary(ArrayList<Club> clubs) {
        System.out.println("Enter The Club Name:");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        for (Club club : clubs) {
            if (club.getName().equalsIgnoreCase(str)) {
                double total_salary = club.getYearlySalary();
                System.out.print("Total Salary in a Year: ");
                System.out.printf("%12.2f\n", total_salary);
                return;
            }
        }
        System.out.println("No such club with this name");
    }

    public static void Players_With_Maximum_Height(ArrayList<Club> clubs) {
        System.out.println("Enter The Club Name:");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        for (Club club : clubs) {
            if (club.getName().equalsIgnoreCase(str)) {

                ArrayList<Player> players = club.getPlayers();
                double max_height = 0;
                for (Player player : players) {
                    if (max_height < player.getHeight()) {
                        max_height = player.getHeight();
                    }
                }
                for (Player player : players) {
                    if (player.getHeight() == max_height) {
                        print(player);
                    }
                }
                return;
            }
        }
        System.out.println("No such club with this name");
    }

    public static void Players_With_Maximum_Age(ArrayList<Club> clubs) {
        System.out.println("Enter The Club Name:");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        for (Club club : clubs) {
            if (club.getName().equalsIgnoreCase(str)) {

                ArrayList<Player> players = club.getPlayers();
                int age = 0;
                for (Player player : players) {
                    if (age < player.getAge()) {
                        age = player.getAge();
                    }
                }
                for (Player player : players) {
                    if (player.getAge() == age) {
                        print(player);
                    }
                }
                return;
            }
        }
        System.out.println("No such club with this name");
    }

    public static void Players_With_Maximum_Salary(ArrayList<Club> clubs) {
        System.out.println("Enter The Club Name:");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        for (Club club : clubs) {
            if (club.getName().equalsIgnoreCase(str)) {

                ArrayList<Player> players = club.getPlayers();
                double max_salary = 0;
                for (Player player : players) {
                    if (max_salary < player.getWeeklySalary()) {
                        max_salary = player.getWeeklySalary();
                    }
                }
                for (Player player : players) {
                    if (player.getWeeklySalary() == max_salary) {
                        print(player);

                    }
                }
                return;
            }
        }
        System.out.println("No such club with this name");
    }


    public static void print(Player player) {
        System.out.print("Name: " + player.getName() + ", " +
                " Country: " + player.getCountry() + ", " +
                " Club: " + player.getClub() + ", " +
                " Age: " + player.getAge() + ", " +
                " Position: " + player.getPosition() + ", " +
                " Number: " + player.getNumber() + ", " +
                " Weekly Salary: ");
        System.out.printf("%10.2f, ", player.getWeeklySalary());
        System.out.println(" Height: " + player.getHeight()+", "+
                "Category: "+player.getCategory());
    }


    public static void Search_With_Country(ArrayList<Club> clubs) {
        ArrayList<Country> countries = new ArrayList<>();
        for (Club club : clubs) {

            for (Player player : club.getPlayers()) {
                boolean ok = true;
                for (Country country : countries) {
                    if (country.getName().equalsIgnoreCase(player.getCountry())) {
                        country.addPlayer(player);
                        ok = false;
                        break;
                    }
                }
                if (ok) {
                    Country country = new Country();
                    country.addPlayer(player);
                    country.setName(player.getCountry());
                    countries.add(country);
                }
            }
        }
        for (Country country : countries) {
            System.out.println("Country Name: " + country.getName());
            System.out.println("Number of Players: " + country.getPlayers().size());
            for (Player player : country.getPlayers()) {
                print(player);
            }
        }

    }

    public static void Search_With_Salary(ArrayList<Club> clubs) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter The Player's WeeklySalary Range(Least):");
        double num1 = take_Double_input();
        System.out.println("Enter The Player's WeeklySalary Range(Most):");
        double num2 = take_Double_input();
        int cnt = 0;
        for (Club club : clubs) {
            for (Player player : club.getPlayers()) {
                if (player.getWeeklySalary() >= num1 && player.getWeeklySalary() <= num2) {
                    print(player);
                    cnt++;

                }
            }

        }
        if (cnt == 0)
            System.out.println("No such player found in this Range");

    }

    public static void Search_With_Position(ArrayList<Club> clubs) {
        System.out.println("Enter The Player's Position:");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        int cnt = 0;
        for (Club club : clubs) {
            for (Player player : club.getPlayers()) {
                if (player.getPosition().equalsIgnoreCase(str)) {
                    print(player);
                    cnt++;

                }
            }

        }
        if (cnt == 0)
            System.out.println("No such player found in this Position");
    }

    public static void Search_With_Club_Country(ArrayList<Club> clubs) {
        System.out.println("Enter The Country:");
        Scanner input = new Scanner(System.in);
        String country = input.nextLine();
        System.out.println("Enter The Club: ");
        String str = input.nextLine();
        String any = "ANY";
        ArrayList<Club> copy_club = new ArrayList<>();
        for (Club club : clubs) {
            if (str.equalsIgnoreCase(any)) {
                copy_club.add(club);
            } else {
                if (str.equalsIgnoreCase(club.getName())) {
                    copy_club.add(club);
                }
            }
        }
        if (copy_club.size() == 0) {
            System.out.println("No such player with this country and club");
        } else {
            int cnt = 0;
            for (Club club : copy_club) {
                for (Player player : club.getPlayers()) {
                    if (player.getCountry().equalsIgnoreCase(country)) {
                        print(player);
                        cnt++;
                    }
                }
            }
            if (cnt == 0) {
                System.out.println("No such player with this country and club");
            }
        }
    }

    public static void Search_With_PlayerName(ArrayList<Club> clubs) {
        System.out.println("Enter The Player's Name:");
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        for (Club club : clubs) {
            for (Player player : club.getPlayers()) {
                if (player.getName().equalsIgnoreCase(str)) {
                    System.out.println("Player Found!!");
                    print(player);
                    return;

                }
            }

        }
        System.out.println("No such player with this name");


    }

    public static ArrayList<String> DatabaseRead() {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("players.txt"))) {
            while (true) {
                String s = br.readLine();
                if (s == null) break;
                list.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
