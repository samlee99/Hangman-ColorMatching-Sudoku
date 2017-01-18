/***************************************************************
* file: Splash.java
* author: Sam Lee, Andrew Nipp, Joshua Ludwig, Steven Mai, Je'Don Carter
* class: CS 245 – Programming Graphical User Interfaces
*
* assignment: Project v1.0
* date last modified: 1/18/2017
*
* purpose: This file is for the splash screen before the game loads.
*
****************************************************************/ 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs245.project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.SplashScreen;
import java.awt.geom.Rectangle2D;
/**
 *
 * @author Stebo
 */
public class Splash {    
    static SplashScreen splash;
    static Graphics2D splashTitle,splashName;               
    static Rectangle2D.Double splashTitleSpace, splashNameSpace;
    static Font titleFont, nameFont;

    //This method creates the text on the splash screen
        public static void splashInit() {
            int height, width;
            splash = SplashScreen.getSplashScreen();
            Dimension dim = splash.getSize();
            height = dim.height;
            width = dim.width;
            splashTitleSpace = new Rectangle2D.Double(15., height*.1, width * .45, 32.);
            splashTitle = splash.createGraphics();
            titleFont = new Font("Arial", Font.PLAIN, 40);
            splashTitle.setFont(titleFont);

            splashNameSpace = new Rectangle2D.Double(190., height*0.88, width * .45, 32.);
            splashName = splash.createGraphics();
            nameFont = new Font("Arial", Font.PLAIN, 30);
            splashName.setFont(nameFont); 
            
            splashTitleText("Rebel Scum: Hangman Edition");
            splashNameText("By: Team TBD");
            
            


        }
        //This method displays the game name on the splash screen
        public static void splashTitleText(String str){
            if (splash != null && splash.isVisible()) {
                splashTitle.setPaint(Color.YELLOW);
                splashTitle.drawString(str, (int)(splashTitleSpace.getX() + 10),(int)(splashTitleSpace.getY() + 15));
                splash.update();

            }
        }
        //This method displays the team name on the splash screen
        public static void splashNameText(String str){
            if (splash != null && splash.isVisible()) {
                splashName.setPaint(Color.YELLOW);
                splashName.drawString(str, (int)(splashNameSpace.getX() + 10),(int)(splashNameSpace.getY() + 15));
                splash.update();
            }
        }


}
