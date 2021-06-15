package com.company;

import java.io.*;
import java.util.*;

public class Action {


    public static void Start() {
        ArrayList<String> arrayList=DatabaseRead();
        ArrayList<Club> clubs=Add_in_Club(arrayList);
        while (true)
        {
            System.out.println("Main Menu:\n" +
                    "(1) Search Players\n" +
                    "(2) Search Clubs \n" +
                    "(3) Add Player\n" +
                    "(4) Exit System\n\n"+"Enter Your Option:");
            Scanner input = new Scanner(System.in);
            int option = input.nextInt();
            if (option == 1) {
                Player_Searching_Option(clubs);
            }
            else if (option == 2) {
                Club_Searching_Option(clubs);

            }
            else if (option == 3) {
                Add_New_Player(clubs);
            }
            else if (option == 4) {
                Exit(clubs);
                return ;

            }
            else
            {
                System.out.println("Please Select The Right Option!!");
            }
        }
    }

    public  static ArrayList<Club> Add_in_Club(ArrayList<String> arrayList) {
        ArrayList<Club> clubs=new ArrayList<>();
        for(String str:arrayList)
        {

            String[] tokens=str.split(",",8);
            Player player=new Player(tokens[0],tokens[1],Integer.parseInt(tokens[2]),Double.parseDouble(tokens[3]),tokens[4],tokens[5],Integer.parseInt(tokens[6]),Double.parseDouble(tokens[7]));
            boolean ok=true;
            for(Club club:clubs)
            {
                if(club.getName().equalsIgnoreCase(player.getClub()))
                {
                    club.addPlayer(player);
                    ok=false;
                    break;
                }

            }
            if(ok)
            {
                Club club=new Club();
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
                "(6) Back to Main Menu\n");
        System.out.println("Enter Your Option:");
        Scanner input=new Scanner(System.in);
        int option=input.nextInt();
        if(option==1)
        {
            Search_With_PlayerName(clubs);
            Player_Searching_Option(clubs);

        }
        else if(option==2)
        {
            Search_With_Club_Country(clubs);
            Player_Searching_Option(clubs);
        }
        else if(option==3)
        {
            Search_With_Position(clubs);
            Player_Searching_Option(clubs);
        }
        else if(option==4)
        {
            Search_With_Salary(clubs);
            Player_Searching_Option(clubs);
        }
        else if(option==5)
        {
            Search_With_Country(clubs);
            Player_Searching_Option(clubs);
        }
        else if(option==6)
        {
            return;
        }
        else
        {
            System.out.println("Please Select The Right Option!!");
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
        Scanner input=new Scanner(System.in);
        int option=input.nextInt();
        if(option==1)
        {
            Players_With_Maximum_Salary(clubs);
            Club_Searching_Option(clubs);
        }
        else if(option==2)
        {
            Players_With_Maximum_Age(clubs);
            Club_Searching_Option(clubs);
        }
        else  if(option==3)
        {
            Players_With_Maximum_Height(clubs);
            Club_Searching_Option(clubs);
        }
        else if(option==4)
        {
            Yearly_Salary(clubs);
            Club_Searching_Option(clubs);
        }
        else if(option==5)
        {
            return ;

        }
        else
        {
            System.out.println("Please Select The Right Option!!");
        }


    }
    public static void Add_New_Player(ArrayList<Club> clubs) {
        int capable=7;
        Scanner input=new Scanner(System.in);
        System.out.println("Enter The Player's Name:");
        String Name=input.nextLine();
        System.out.println("Enter The Player's Country Name:");
        String Country=input.nextLine();
        System.out.println("Enter The Player's Club Name:");
        String Club=input.nextLine();
        System.out.println("Enter The Player's Position:");
        String Position=input.nextLine();
        System.out.println("Enter The Player's Age:");
        int Age=input.nextInt();
        System.out.println("Enter The Player's Number:");
        int Number=input.nextInt();
        System.out.println("Enter The Player's WeeklySalary:");
        double WeeklySalary=input.nextDouble();
        System.out.println("Enter The Player's Height:");
        double Height=input.nextDouble();
        Player player=new Player(Name,Country,Age,Height,Club,Position,Number,WeeklySalary);
        for(Club club:clubs)
        {
            for (Player player1: club.getPlayers())
            {
                if(player1.getName().equalsIgnoreCase(player.getName()))
                {
                    System.out.println("Sorry!!There Exits Same Player's Name");
                    return  ;
                }
            }
        }
        boolean ok=true;
        for(Club club:clubs)
        {
            if(club.getName().equalsIgnoreCase(player.getClub()))
            {

                if(club.getPlayers().size()==capable)
                {
                    System.out.println("Sorry!!The capability of this Club is full");
                    return ;
                }
                else
                {
                    club.addPlayer(player);
                    ok=false;
                    break;
                }
            }

        }
        if(ok)
        {
            Club club=new Club();
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
                    fw.write(player.getName() + "," + player.getCountry() + "," + player.getAge() + "," + player.getHeight() + "," + player.getClub() + "," + player.getPosition() + "," + player.getNumber() + "," + player.getWeeklySalary() + "\r\n");
                }
            }
            fw.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void Yearly_Salary(ArrayList<Club> clubs) {
        System.out.println("Enter The Club Name:");
        Scanner input=new Scanner(System.in);
        String str=input.nextLine();
        for(Club club:clubs)
        {
            if(club.getName().equalsIgnoreCase(str))
            {
               double total_salary= club.getYearlySalary();
                System.out.println("Total Salary in a Year: "+total_salary);
                return;
            }
        }
        System.out.println("No such club with this name");
    }
    private static void Players_With_Maximum_Height(ArrayList<Club> clubs) {
        System.out.println("Enter The Club Name:");
        Scanner input=new Scanner(System.in);
        String str=input.nextLine();
        for(Club club:clubs)
        {
            if(club.getName().equalsIgnoreCase(str))
            {

                ArrayList<Player> players=club.getPlayers();
                double max_height=0;
                for(Player player:players)
                {
                    if(max_height<player.getHeight())
                    {
                        max_height=player.getHeight();
                    }
                }
                for(Player player:players)
                {
                    if(player.getHeight()==max_height)
                    {
                        System.out.println("Name: "+player.getName()+", "+
                                " Country: "+ player.getCountry()+", "+
                                " Club: "+player.getClub()+", "+
                                " Age: "+player.getAge()+", "+
                                " Position: "+player.getPosition()+", "+
                                " Number: "+player.getNumber()+", "+
                                " Weekly Salary: "+player.getWeeklySalary()+", "+
                                " Height: "+player.getHeight());
                    }
                }
                return;
            }
        }
        System.out.println("No such club with this name");
    }
    private static void Players_With_Maximum_Age(ArrayList<Club> clubs) {
        System.out.println("Enter The Club Name:");
        Scanner input=new Scanner(System.in);
        String str=input.nextLine();
        for(Club club:clubs)
        {
            if(club.getName().equalsIgnoreCase(str))
            {

                ArrayList<Player> players=club.getPlayers();
                int age=0;
                for(Player player:players)
                {
                    if(age<player.getAge())
                    {
                        age=player.getAge();
                    }
                }
                for(Player player:players)
                {
                    if(player.getAge()==age)
                    {
                        System.out.println("Name: "+player.getName()+", "+
                                " Country: "+ player.getCountry()+", "+
                                " Club: "+player.getClub()+", "+
                                " Age: "+player.getAge()+", "+
                                " Position: "+player.getPosition()+", "+
                                " Number: "+player.getNumber()+", "+
                                " Weekly Salary: "+player.getWeeklySalary()+", "+
                                " Height: "+player.getHeight());
                    }
                }
                return;
            }
        }
        System.out.println("No such club with this name");
    }
    private static void Players_With_Maximum_Salary(ArrayList<Club> clubs) {
        System.out.println("Enter The Club Name:");
        Scanner input=new Scanner(System.in);
        String str=input.nextLine();
        for(Club club:clubs)
        {
            if(club.getName().equalsIgnoreCase(str))
            {

                ArrayList<Player> players=club.getPlayers();
                double max_salary=0;
                for(Player player:players)
                {
                    if(max_salary<player.getWeeklySalary())
                    {
                        max_salary=player.getWeeklySalary();
                    }
                }
                for(Player player:players)
                {
                    if(player.getWeeklySalary()==max_salary)
                    {
                        System.out.println("Name: "+player.getName()+", "+
                                " Country: "+ player.getCountry()+", "+
                                " Club: "+player.getClub()+", "+
                                " Age: "+player.getAge()+", "+
                                " Position: "+player.getPosition()+", "+
                                " Number: "+player.getNumber()+", "+
                                " Weekly Salary: "+player.getWeeklySalary()+", "+
                                " Height: "+player.getHeight());
                    }
                }
                return;
            }
        }
        System.out.println("No such club with this name");
    }

    public static void Search_With_Country(ArrayList<Club> clubs) {
        ArrayList<Country> countries=new ArrayList<>() ;
        for(Club club:clubs)
        {

            for (Player player:club.getPlayers())
            {
                boolean ok=true;
                for(Country country:countries)
                {
                    if(country.getName().equalsIgnoreCase(player.getCountry()))
                    {
                        country.addPlayer(player);
                        ok=false;
                        break;
                    }
                }
                if(ok)
                {
                    Country country=new Country();
                    country.addPlayer(player);
                    country.setName(player.getCountry());
                    countries.add(country);
                }
            }
        }
        for(Country country:countries)
        {
            System.out.println("Country Name: "+country.getName());
            for(Player player:country.getPlayers())
            {
                System.out.println("Name: "+player.getName()+", "+
                        " Country: "+ player.getCountry()+", "+
                        " Club: "+player.getClub()+", "+
                        " Age: "+player.getAge()+", "+
                        " Position: "+player.getPosition()+", "+
                        " Number: "+player.getNumber()+", "+
                        " Weekly Salary: "+player.getWeeklySalary()+", "+
                        " Height: "+player.getHeight());
            }
        }

    }
    public static void Search_With_Salary(ArrayList<Club> clubs) {
        System.out.println("Enter The Player's WeeklySalary Range:");
        Scanner input=new Scanner(System.in);
        double num1=input.nextDouble();
        double num2=input.nextDouble();
        int cnt=0;
        for(Club club:clubs)
        {
            for (Player player:club.getPlayers())
            {
                if(player.getWeeklySalary()>=num1&&player.getWeeklySalary()<=num2)
                {
                    System.out.println("Name: "+player.getName()+", "+
                            " Country: "+ player.getCountry()+", "+
                            " Club: "+player.getClub()+", "+
                            " Age: "+player.getAge()+", "+
                            " Position: "+player.getPosition()+", "+
                            " Number: "+player.getNumber()+", "+
                            " Weekly Salary: "+player.getWeeklySalary()+", "+
                            " Height: "+player.getHeight());
                    cnt++;

                }
            }

        }
        if(cnt==0)
            System.out.println("No such player found in this Range");

    }
    public static void Search_With_Position(ArrayList<Club> clubs) {
        System.out.println("Enter The Player's Position:");
        Scanner input=new Scanner(System.in);
        String str= input.nextLine();
        int cnt=0;
        for(Club club:clubs)
        {
            for (Player player:club.getPlayers())
            {
                if(player.getPosition().equalsIgnoreCase(str))
                {
                    System.out.println("Name: "+player.getName()+", "+
                            " Country: "+ player.getCountry()+", "+
                            " Club: "+player.getClub()+", "+
                            " Age: "+player.getAge()+", "+
                            " Position: "+player.getPosition()+", "+
                            " Number: "+player.getNumber()+", "+
                            " Weekly Salary: "+player.getWeeklySalary()+", "+
                            " Height: "+player.getHeight());
                    cnt++;

                }
            }

        }
        if(cnt==0)
        System.out.println("No such player found in this Position");
    }
    public static void Search_With_Club_Country(ArrayList<Club> clubs) {
        System.out.println("Enter The Country:");
        Scanner input=new Scanner(System.in);
        String country=input.nextLine();
        System.out.println("Enter The Club: ");
        String str=input.nextLine();
        String any="ANY";
        ArrayList<Club> copy_club=new ArrayList<>();
        for(Club club : clubs)
        {
            if(str.equalsIgnoreCase(any))
            {
                copy_club.add(club);
            }
            else
            {
                if(str.equalsIgnoreCase(club.getName()))
                {
                    copy_club.add(club);
                }
            }
        }
        if(copy_club.size()==0)
        {
            System.out.println("No such player with this country and club");
        }
        else
        {
            int cnt=0;
            for(Club club:copy_club)
            {
                for(Player player:club.getPlayers())
                {
                    if(player.getCountry().equalsIgnoreCase(country))
                    {
                        System.out.println("Name: "+player.getName()+","+
                                "Country: "+ player.getCountry()+","+
                                "Club: "+player.getClub()+","+
                                "Age: "+player.getAge()+","+
                                "Position: "+player.getPosition()+","+
                                "Number: "+player.getNumber()+","+
                                "Weekly Salary: "+player.getWeeklySalary()+","+
                                "Height: "+player.getHeight());
                        cnt++;
                    }
                }
            }
            if(cnt==0)
            {
                System.out.println("No such player with this country and club");
            }
        }
    }
    public static void Search_With_PlayerName(ArrayList<Club> clubs) {
        System.out.println("Enter The Player's Name:");
        Scanner input=new Scanner(System.in);
        String str= input.nextLine();
        for(Club club:clubs)
        {
            for (Player player:club.getPlayers())
            {
                if(player.getName().equalsIgnoreCase(str))
                {
                    System.out.println("Player Found!!");
                    System.out.println("Player's Name: "+player.getName()+"\n"+
                            "Player's Country Name: "+ player.getCountry()+"\n"+
                            "Player's Club Name: "+player.getClub()+"\n"+
                            "Player's Age: "+player.getAge()+"\n"+
                            "Player's Position: "+player.getPosition()+"\n"+
                            "Player's Number: "+player.getNumber()+"\n"+
                            "Player's Weekly Salary: "+player.getWeeklySalary()+"\n"+
                            "Player's Height: "+player.getHeight());
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
