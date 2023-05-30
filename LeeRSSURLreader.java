/*
News Ticker Program RSS feed based

Author: Jonathan Lee
Professor: Gita Faroughi
Class: Sierra College CSCI13
Date: March 11 2022

UPDATED: May 11 2023

Help to Learn Solution to my problem
*/

import java.net.*;
import java.io.*;
import java.util.*;

public class LeeRSSURLreader 
{
   public static void main(String[] args) throws Exception 
   {
      String run = ("y");
      ArrayList newsarray = new ArrayList<String>();
      ArrayList keywordpresentarray = new ArrayList<String>();
      Scanner console = new Scanner(System.in);
      while (run.equals("y"))
      {
         System.out.print("Current News Feeds are \n1. Fox World News\n2. CNN World News\n3. UN.Org Top News\n4. BBC London\n5. ABC News Money Headlines\n6. OECD News releases and media alerts\n7. US Federal Reserve Press Release RSS feed\nEnter Number: ");
         String userchoice = console.nextLine();
      
         System.out.print("Enter Search Keywords one word at a time press enter after each keyword or Q/q to exit: ");
         String keywords = console.nextLine();
         while (!keywords.equalsIgnoreCase("q"))
         { 
            newsarray.add(keywords);
            System.out.print("Enter Search Keywords one word at a time press enter after each keyword or Q/q to exit: ");
            keywords = console.nextLine();
         }
      
      
         URL news = new URL("https://moxie.foxnews.com/feedburner/world.xml/");
         if(userchoice.equals("1"))
         {
            news = new URL("https://moxie.foxnews.com/feedburner/world.xml/");
         }
         else if (userchoice.equals("2"))
         {
            news = new URL("http://rss.cnn.com/rss/cnn_world.rss/");
         }
         else if (userchoice.equals("3"))
         {
            news = new URL("https://news.un.org/feed/subscribe/en/news/all/rss.xml/");
         }
         else if (userchoice.equals("4"))
         {
            news = new URL("http://feeds.bbci.co.uk/news/england/london/rss.xml");
         }
         else if (userchoice.equals("5"))
         {
            news = new URL("https://abcnews.go.com/abcnews/moneyheadlines");
         }
         else if (userchoice.equals("6"))
         {
            news = new URL("https://www.oecd.org/newsroom/index.xml");
         }
         else if (userchoice.equals("7"))
         {
            news = new URL("https://www.federalreserve.gov/feeds/press_all.xml");
         }
         BufferedReader in = new BufferedReader(new InputStreamReader(news.openStream()));// change me to what news feed you need.
         String inputLine;
         String q="";
         System.out.println("\n\n******************************************* WELCOME TO THE SIERRA COLLEGE RSS NEWS TICKER *******************************************");
         while((inputLine = in.readLine()) != null)
         {
            q+= inputLine;
         }
         in.close();
         System.out.println();    
         int index = q.indexOf("<description>");
         int close = q.indexOf("</description>");
         while(index >0 || index != -1)
         {
            index = q.indexOf("<description>");
            if (index == -1)
               break;
            close = q.indexOf("</description>");
            String check_for_word = q.substring(index+13,close);
            String currentkeyword="";
            Iterator<String> iterator = newsarray.iterator();; // list array iterator for actions
            if(check_for_word.contains("<![CDATA["))
            {
               check_for_word = check_for_word.substring(9, check_for_word.length()-3);
            }
            while(iterator.hasNext())
            {
               currentkeyword = iterator.next(); 
               if(check_for_word.toLowerCase().contains(currentkeyword.toLowerCase())) // if keyword is lower or upper case inside current iterator
               {
                  keywordpresentarray.add("!!KEYWORD"+" \""+currentkeyword.toUpperCase()+"\" IN HEADLINE: "+check_for_word);
               }
            }
            System.out.println(check_for_word);
            q = q.substring(close+15);
            System.out.println();
         }
         String headliners = "";
         Iterator<String> iteractors = keywordpresentarray.iterator();
         while(iteractors.hasNext())
         {
            System.out.println(iteractors.next());
         }
         System.out.println("Run Program Again y/n?");
         run = console.nextLine();
      }
   }
}
