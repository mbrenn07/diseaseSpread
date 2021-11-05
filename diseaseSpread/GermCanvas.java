/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scifair;

import java.awt.Color; //all of the imports
import java.awt.Graphics;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

public class GermCanvas extends javax.swing.JFrame {

    //static int boxesChecked;
    //static int boxesChecked;

    Graphics offScrGraph; //the graphics; offscreen the whole time, never seen
    Image offScrImg; //an img that eventually gets made into graphics
    Graphics offScrGraph2; //the graphics; offscreen the whole time, never seen
    Image offScrImg2; //an img that eventually gets made into graphics
    boolean box1Checked=false;
    boolean box2Checked=false;
    boolean box3Checked=false;
    boolean box4Checked=false;
    boolean box5Checked=false;
    static int boxesChecked=0;
    int dont1=0;
    int dont2=0;
    int dont3=0;
    int dont4=0;
    int dont5=0;
    int dont6=0;
    int totalInfected=0;
    long timeStarted=0;
    long timeBegun=0;
    int lines=0;
    static int totalPopulation=0;
    int uninfectedPopulation2=0;
    int POPULATION = 1000; //change this constant to your desired number of germs.
    int FINALPOPULATION = POPULATION-1; //code is wierd, this makes your number exact every time.\
    int POPULATION2 = POPULATION/5; //makes the number of germs at home(jPanel2) one fifth of the number of germs at school(jPanel1)
    int FINALPOPULATION2 = POPULATION2-1; //code is wierd, this makes your number exact every time.\
    int IMMUNEPOPULATION = 10; //change this constant to your desired number of germs.
    int FINALIMMUNEPOPULATION = IMMUNEPOPULATION-1; //code is wierd, this makes your number exact every time.
    int INFECTEDPOPULATION = 100; //desired number of infected germs
    int FINALINFECTEDPOPULATION = INFECTEDPOPULATION - 1; //code is odd, this is needed to make the number exact.
    int RESULTSPOP= POPULATION+POPULATION2+IMMUNEPOPULATION+INFECTEDPOPULATION;
    int w = 665, h = 708; //just creating w(width) and h(height) to be used later.
    long timeToGoHome=0;
    int thisValue=0;
    boolean play; //toggled on and off by a button
    Germ[][] germs; //like an egg carton; declares the array here.
    Germ[][] germs2;
    boolean[][]uninfectedGerms;
    
    public GermCanvas() {
        initComponents(); //initializes the components
        Timer time = new Timer(); //creates a timer
        //w = jPanel1.getWidth();h= jPanel1.getHeight()*2; //changes w to be the width of the panel, and h to be the height of the panel.
        germs=new Germ[w][h];//instantiates the germs, with w rows and h columns, still like an egg carton, empty.
        germs2=new Germ[w][h];
        uninfectedGerms=new boolean[w][h];
        while(Germ.uninfectedPopulation<=FINALPOPULATION){ //using != doesn't work b/c it will just keep going if it doesn't hit an exact number.
            for(int x = 0 ; x < w ; x++){    //when using a loop like this, plug in x and    PARSES THE
                for(int y = 0 ; y < h; y++){ // y for the x and y in the array boxes[][]       ARRAY
                    if(Math.random()<=.0005&&Germ.uninfectedPopulation<=FINALPOPULATION){ //a very small part of the time it makes a germ. Stops once it has made 1000 or whatever FINALPOPULATION is set to.
                        uninfectedGerms[x][y]=true;
                        germs[x][y]=new Germ(x,y,0,germs,germs2,uninfectedGerms,0,0,0,0); //goes to a specific spot and creates a germs there.
                        System.out.println("Creating uninfected germs..."+Germ.uninfectedPopulation); //tells us that the program is creating germs.
                        totalPopulation++;
                        //System.out.println("x..."+germs[x][y].x+"y..."+germs[x][y].y); //tells us that the program is creating germs.
                    }
                }    
            }
        }
        while(Germ.infectedPopulation<=FINALINFECTEDPOPULATION){ //using != doesn't work b/c it will just keep going if it doesn't hit an exact number.
            for(int x = 0 ; x < w ; x++){    //when using a loop like this, plug in x and    PARSES THE
                for(int y = 0 ; y < h; y++){ // y for the x and y in the array boxes[][]       ARRAY
                    if(Math.random()<=.0002&&Germ.infectedPopulation<=FINALINFECTEDPOPULATION){ //a very small part of the time it makes a germ. Stops once it has made 1000 or whatever FINALPOPULATION is set to.
                        germs[x][y]=new Germ(x,y,1,germs,germs2,uninfectedGerms,0,0,0,0); //goes to a specific spot and creates a germs there.
                        System.out.println("Creating infected germs..."+Germ.infectedPopulation); //tells us that the program is creating germs.
                        totalPopulation++;
                    }
                }    
            }
        }
        while(Germ.immunePopulation<=FINALIMMUNEPOPULATION){ //using != doesn't work b/c it will just keep going if it doesn't hit an exact number.
            for(int x = 0 ; x < w ; x++){    //when using a loop like this, plug in x and    PARSES THE
                for(int y = 0 ; y < h; y++){ // y for the x and y in the array boxes[][]       ARRAY
                    if(Math.random()<=.000005&&Germ.immunePopulation<=FINALIMMUNEPOPULATION){ //a very small part of the time it makes a germ. Stops once it has made 1000 or whatever FINALPOPULATION is set to.
                        germs[x][y]=new Germ(x,y,2,germs,germs2,uninfectedGerms,0,0,0,0); //goes to a specific spot and creates a germs there.
                        System.out.println("Creating immune germs..."+Germ.immunePopulation); //tells us that the program is creating germs.
                        totalPopulation++;
                        //System.out.println("x..."+germs[x][y].x+"y..."+germs[x][y].y); //tells us that the program is creating germs.
                    }
                }    
            }
        }
        while(uninfectedPopulation2<=FINALPOPULATION2){ //using != doesn't work b/c it will just keep going if it doesn't hit an exact number.
            for(int x = 0 ; x < w ; x++){    //when using a loop like this, plug in x and    PARSES THE
                for(int y = 0 ; y < h; y++){ // y for the x and y in the array boxes[][]       ARRAY
                    if(Math.random()<=.0005&&uninfectedPopulation2<=FINALPOPULATION2){ //a very small part of the time it makes a germ. Stops once it has made 1000 or whatever FINALPOPULATION is set to.
                        uninfectedPopulation2++;
                        uninfectedGerms[x][y]=true;
                        germs2[x][y]=new Germ(x,y,0,germs,germs2,uninfectedGerms,1,0,0,0); //goes to a specific spot and creates a germs there.
                        System.out.println("Creating uninfected germs at home..."+uninfectedPopulation2); //tells us that the program is creating germs.
                        totalPopulation++;
                        //System.out.println("x..."+germs[x][y].x+"y..."+germs[x][y].y); //tells us that the program is creating germs.
                    }
                }    
            }
        }
        if(timeBegun==0){
            timeBegun=System.currentTimeMillis();
        }
        offScrImg = createImage(jPanel1.getWidth(), jPanel1.getHeight()); //makes an image the size of the JPanel
        offScrGraph = offScrImg.getGraphics(); //makes the img into graphics
        offScrImg2 = createImage(jPanel2.getWidth(), jPanel2.getHeight()); //makes an image the size of the JPanel
        offScrGraph2 = offScrImg2.getGraphics(); //makes the img into graphics
//        TimerTask copy = new TimerTask(){ //a timer task, something the timer does
//            public void run(){ //an override
//                System.arraycopy(germs, 0, germs2, 0, germs.length );
//            }
//        };
        TimerTask goHome = new TimerTask(){ //a timer task, something the timer does   
        public void run(){ //an override
        for(int x = 0 ; x < w ; x++){    //when using a loop like this, plug in x and    PARSES THE
            for(int y = 0 ; y < h; y++){ // y for the x and y in the array boxes[][]       ARRAY
               if(germs[x][y]!=null&&germs[x][y].status==1){
                   if(Math.random()*100+1<=germs[x][y].percentag){
                        germs2[x][y]=new Germ(x,y,1,germs,germs2,uninfectedGerms,0,germs[x][y].timeInfected,1,germs[x][y].neverGetImmuneAgain);
                        germs[x][y]=null;
                        GermCanvas.totalPopulation--;
                    }
                }
            }
        }
        if(timeToGoHome==0){
            timeToGoHome=System.currentTimeMillis();
        }
        if(System.currentTimeMillis()-timeToGoHome>=10000){
            goBack();
            timeToGoHome=0;
        }
        }
    };
        TimerTask goHomeV2 = new TimerTask(){ //a timer task, something the timer does   
        public void run(){ //an override
        for(int x = 0 ; x < w ; x++){    //when using a loop like this, plug in x and    PARSES THE
            for(int y = 0 ; y < h; y++){ // y for the x and y in the array boxes[][]       ARRAY
               if(germs[x][y]!=null&&germs[x][y].status==1){
                   if(Math.random()*100+1<=germs[x][y].percentag2){
                        germs2[x][y]=new Germ(x,y,1,germs,germs2,uninfectedGerms,0,germs[x][y].timeInfected,1,germs[x][y].neverGetImmuneAgain);
                        germs[x][y]=null;
                        GermCanvas.totalPopulation--;
                    }
                }
            }
        }
        }
    };
        TimerTask goHomeV3 = new TimerTask(){ //a timer task, something the timer does   
        public void run(){ //an override
        for(int x = 0 ; x < w ; x++){    //when using a loop like this, plug in x and    PARSES THE
            for(int y = 0 ; y < h; y++){ // y for the x and y in the array boxes[][]       ARRAY
               if(germs[x][y]!=null&&germs[x][y].status==1){
                   if(Math.random()*100+1<=germs[x][y].percentag3){
                        germs2[x][y]=new Germ(x,y,1,germs,germs2,uninfectedGerms,0,germs[x][y].timeInfected,1,germs[x][y].neverGetImmuneAgain);
                        germs[x][y]=null;
                        GermCanvas.totalPopulation--;
                    }
                }
            }
        }
        }
    };
        TimerTask goHomeV4 = new TimerTask(){ //a timer task, something the timer does   
        public void run(){ //an override
        for(int x = 0 ; x < w ; x++){    //when using a loop like this, plug in x and    PARSES THE
            for(int y = 0 ; y < h; y++){ // y for the x and y in the array boxes[][]       ARRAY
               if(germs[x][y]!=null&&germs[x][y].status==1){
                   if(Math.random()*100+1<=germs[x][y].percentag4){
                        germs2[x][y]=new Germ(x,y,1,germs,germs2,uninfectedGerms,0,germs[x][y].timeInfected,1,germs[x][y].neverGetImmuneAgain);
                        germs[x][y]=null;
                        GermCanvas.totalPopulation--;
                    }
                }
            }
        }
        }
    };
        TimerTask task = new TimerTask(){ //a timer task, something the timer does
            public void run(){ //an override
                if(play){ //play is controlled by the button
                    if(timeStarted==0){
                        timeStarted=System.currentTimeMillis();
                    }
                    repain(); //paints
                    doStuff(); //does things like move and infect(later)
                    getResults();
                    getResults2();
                    getResults3();
                    getResults4();
                    getResults5();
                    getResults6();
                    //System.out.println(System.currentTimeMillis()-timeBegun);
                    if(System.currentTimeMillis()-timeStarted>=29999){
                        if(thisValue==0){
                            if(boxesChecked==1){
                                time.scheduleAtFixedRate(goHome, 0,30000);//does the task every 10 seconds.
                            }else
                                if(boxesChecked==2){
                                    time.scheduleAtFixedRate(goHome, 0,30000);//does the task every 10 seconds.
                                    time.scheduleAtFixedRate(goHomeV2, 0,30000);//does the task every 10 seconds.
                                }else
                                    if(boxesChecked==3){
                                        time.scheduleAtFixedRate(goHome, 0,30000);//does the task every 10 seconds.
                                        time.scheduleAtFixedRate(goHomeV2, 0,30000);//does the task every 10 seconds.
                                        time.scheduleAtFixedRate(goHomeV3, 0,30000);//does the task every 10 seconds.
                                    }else
                                        if(boxesChecked==4){
                                            time.scheduleAtFixedRate(goHome, 0,30000);//does the task every 10 seconds.
                                            time.scheduleAtFixedRate(goHomeV2, 0,30000);//does the task every 10 seconds.
                                            time.scheduleAtFixedRate(goHomeV3, 0,30000);//does the task every 10 seconds.
                                            time.scheduleAtFixedRate(goHomeV4, 0,30000);//does the task every 10 seconds.
                                    }
                            thisValue++;
                        }
                    //System.out.println("Here are your results:");
                    }
                }
            }
        };
        
        
        time.scheduleAtFixedRate(task, 0,100);//does the task every .1 second.
        repain(); //paints
    }
//    public void search(){
//        for(int x = 0 ; x < w ; x++){
//            for(int y = 0 ; y < h; y++){
//                if(germs[x][y]!=null){   
//                    uninfectedGerms[x][y]=true;
//                }else
//                    uninfectedGerms[x][y]=false;
//            }
//        }
//    }
    public void getResults(){
        if(System.currentTimeMillis()-timeStarted>=30000&&dont1==0){
            System.out.println("Here are your results:");
            System.out.println("There were "+RESULTSPOP+" people used in this simulation.");
            System.out.println("A total of "+Germ.germsInfected+" people were infected over the course of the simulation.");
            System.out.println("The simulation has a total of:");
            System.out.println(Germ.uninfectedPopulation+" uninfected people.");
            System.out.println((RESULTSPOP-Germ.uninfectedPopulation-Germ.immunePopulation)+" infected people.");
            System.out.println(Germ.immunePopulation+" immune people");
            //System.out.println("The germs had a "+Germ.percentage+"% chance to infect another germ");
            //System.out.println("They also had a "+Germ.percentag+"% chance to go home.");
            System.out.println("The simulation has lasted for 30 seconds.");
            System.out.println("   ");
            dont1 = 1;
        }
    }
    public void getResults2(){
        if(System.currentTimeMillis()-timeStarted>=60000&&dont2==0){
            System.out.println("Here are your results:");
            System.out.println("There were "+RESULTSPOP+" people used in this simulation.");
            System.out.println("A total of "+Germ.germsInfected+" people were infected over the course of the simulation.");
            System.out.println("The simulation has a total of:");
            System.out.println(Germ.uninfectedPopulation+" uninfected people.");
            System.out.println((RESULTSPOP-Germ.uninfectedPopulation-Germ.immunePopulation)+" infected people.");
            System.out.println(Germ.immunePopulation+" immune people");
            //System.out.println("The germs had a "+Germ.percentage+"% chance to infect another germ");
            //System.out.println("They also had a "+Germ.percentag+"% chance to go home.");
            System.out.println("The simulation has lasted for 1 minute.");
            System.out.println("   ");
            dont2 = 1;
        }
    }
   public void getResults3(){
        if(System.currentTimeMillis()-timeStarted>=90000&&dont3==0){
            System.out.println("Here are your results:");
            System.out.println("There were "+RESULTSPOP+" people used in this simulation.");
            System.out.println("A total of "+Germ.germsInfected+" people were infected over the course of the simulation.");
            System.out.println("The simulation has a total of:");
            System.out.println(Germ.uninfectedPopulation+" uninfected people.");
            System.out.println((RESULTSPOP-Germ.uninfectedPopulation-Germ.immunePopulation)+" infected people.");
            System.out.println(Germ.immunePopulation+" immune people");
            //System.out.println("The germs had a "+Germ.percentage+"% chance to infect another germ");
            //System.out.println("They also had a "+Germ.percentag+"% chance to go home.");
            System.out.println("The simulation has lasted for 1 minute and 30 seconds.");
            System.out.println("   ");
            dont3 = 1;
        }
    }
   public void getResults4(){
        if(System.currentTimeMillis()-timeStarted>=120000&&dont4==0){
            System.out.println("Here are your results:");
            System.out.println("There were "+RESULTSPOP+" people used in this simulation.");
            System.out.println("A total of "+Germ.germsInfected+" people were infected over the course of the simulation.");
            System.out.println("The simulation has a total of:");
            System.out.println(Germ.uninfectedPopulation+" uninfected people.");
            System.out.println((RESULTSPOP-Germ.uninfectedPopulation-Germ.immunePopulation)+" infected people.");
            System.out.println(Germ.immunePopulation+" immune people");
            //System.out.println("The germs had a "+Germ.percentage+"% chance to infect another germ");
            //System.out.println("They also had a "+Germ.percentag+"% chance to go home.");
            System.out.println("The simulation has lasted for 2 minutes.");
            System.out.println("   ");
            dont4 = 1;
        }
    }
   public void getResults5(){
        if(System.currentTimeMillis()-timeStarted>=150000&&dont5==0){
            System.out.println("Here are your results:");
            System.out.println("There were "+RESULTSPOP+" people used in this simulation.");
            System.out.println("A total of "+Germ.germsInfected+" people were infected over the course of the simulation.");
            System.out.println("The simulation has a total of:");
            System.out.println(Germ.uninfectedPopulation+" uninfected people.");
            System.out.println((RESULTSPOP-Germ.uninfectedPopulation-Germ.immunePopulation)+" infected people.");
            System.out.println(Germ.immunePopulation+" immune people");
            //System.out.println("The germs had a "+Germ.percentage+"% chance to infect another germ");
            //System.out.println("They also had a "+Germ.percentag+"% chance to go home.");
            System.out.println("The simulation has lasted for 2 minutes and 30 seconds.");
            System.out.println("   ");
            dont5 = 1;
        }
    }
   public void getResults6(){
        if(System.currentTimeMillis()-timeStarted>=180000&&dont6==0){
            play = !play;
            System.out.println("Here are your results:");
            System.out.println("There were "+RESULTSPOP+" people used in this simulation.");
            System.out.println("A total of "+Germ.germsInfected+" people were infected over the course of the simulation.");
            System.out.println("The simulation ended with a total of:");
            System.out.println(Germ.uninfectedPopulation+" uninfected people.");
            System.out.println((RESULTSPOP-Germ.uninfectedPopulation-Germ.immunePopulation)+" infected people.");
            System.out.println(Germ.immunePopulation+" immune people");
            //System.out.println("The germs had a "+Germ.percentage+"% chance to infect another germ");
            //System.out.println("They also had a "+Germ.percentag+"% chance to go home.");
            System.out.println("The simulation lasted for 3 minutes.");
            System.out.println("Thank you and goodbye.");
            dont6 = 1;
        }
    }
   
    
    
    
    public void goBack(){ //a timer task, something the timer does   
        for(int x = 0 ; x < w ; x++){    //when using a loop like this, plug in x and    PARSES THE
            for(int y = 0 ; y < h; y++){ // y for the x and y in the array boxes[][]       ARRAY
               if(germs2[x][y]!=null/*&&germs2[x][y].inPanel2==0&&germs2[x][y].goneBefore==0*/&&germs2[x][y].status==2){
                        germs[x][y]=new Germ(x,y,2,germs,germs2,uninfectedGerms,0,germs2[x][y].timeInfected,0,germs2[x][y].neverGetImmuneAgain);
                        germs2[x][y]=null;
                        Germ.immunePopulation--;
                }
            }
        }
        
    } 
    public void doStuff(){
         for(int x = 0 ; x < w ; x++){
            for(int y = 0 ; y < h; y++){
                if(germs[x][y]!=null){    
                    germs[x][y].moveSensibly(); //infects, moves, checksNeighbors, changes color
                    //search(); //WAYYY too resource intensive.
                }
                if(germs2[x][y]!=null){    
                    germs2[x][y].moveSensibly2(); //infects, moves, checksNeighbors, changes color
                    //search(); //WAYYY too resource intensive.
                }
            }
         }
    }
    
    private void repain2(){
        offScrGraph2.setColor(jPanel2.getBackground()); //gives the graphics the same color as the jPanel
        offScrGraph2.fillRect(0, 0, jPanel2.getWidth(), jPanel2.getHeight());//uses that color to fill in a rect, same size as the jPanel
        for(int x = 0 ; x < w ; x++){  //this loop parses the germ array. x for rows and
            for(int y = 0 ; y < h; y++){ //y for columns.
                if(germs2[x][y]!=null){   //doesn't draw if there is nothing there; prevents crashes and errors. 
                    if(germs2[x][y].color==0){
                        offScrGraph2.setColor(Color.GREEN); //sets color to green.
                        offScrGraph2.fillRect(germs2[x][y].x,germs2[x][y].y,germs2[x][y].width ,germs2[x][y].width ); //makes a green rect. to represent the germ, rect moves with the germ.  
                    }
                    if(germs2[x][y].color==1){
                        offScrGraph2.setColor(Color.RED); //sets color to red.
                        offScrGraph2.fillRect(germs2[x][y].x,germs2[x][y].y,germs2[x][y].width ,germs2[x][y].width ); //makes a red rect. to represent the germ, rect moves with the germ.  
                    }
                    if(germs2[x][y].color==2){
                        offScrGraph2.setColor(Color.CYAN); //sets color to red.
                        offScrGraph2.fillRect(germs2[x][y].x,germs2[x][y].y,germs2[x][y].width ,germs2[x][y].width ); //makes a red rect. to represent the germ, rect moves with the germ.  
                    }
                }    
            }
        }
        offScrGraph2.setColor(Color.GRAY);
        for(int i = 1; i < h;i=i+177+lines){
            if(lines<=12){
            lines=lines+3;
            }
            int y = i * jPanel2.getHeight()/h;
            offScrGraph2.drawLine(0, y, jPanel2.getWidth(), y);
        }
        for(int j = 1; j < w;j=j+110){
            int x = j * jPanel2.getWidth()/w;
            offScrGraph2.drawLine(x, 0, x, jPanel2.getHeight());
        }    
        jPanel2.getGraphics().drawImage(offScrImg2, 0, 0, jPanel2); //draws the graphics onto the Jpanel, where it can be seen.
    }
    private void repain(){
        offScrGraph.setColor(jPanel1.getBackground()); //gives the graphics the same color as the jPanel
        offScrGraph.fillRect(0, 0, jPanel1.getWidth(), jPanel1.getHeight());//uses that color to fill in a rect, same size as the jPanel
        for(int x = 0 ; x < w ; x++){  //this loop parses the germ array. x for rows and
            for(int y = 0 ; y < h; y++){ //y for columns.
                if(germs[x][y]!=null){   //doesn't draw if there is nothing there; prevents crashes and errors. 
                    if(germs[x][y].color==0){
                        offScrGraph.setColor(Color.GREEN); //sets color to green.
                        offScrGraph.fillRect(germs[x][y].x,germs[x][y].y,germs[x][y].width ,germs[x][y].width ); //makes a green rect. to represent the germ, rect moves with the germ.  
                    }
                    if(germs[x][y].color==1){
                        offScrGraph.setColor(Color.RED); //sets color to red.
                        offScrGraph.fillRect(germs[x][y].x,germs[x][y].y,germs[x][y].width ,germs[x][y].width ); //makes a red rect. to represent the germ, rect moves with the germ.  
                    }
                    if(germs[x][y].color==2){
                        offScrGraph.setColor(Color.CYAN); //sets color to red.
                        offScrGraph.fillRect(germs[x][y].x,germs[x][y].y,germs[x][y].width ,germs[x][y].width ); //makes a red rect. to represent the germ, rect moves with the germ.  
                    }
                }    
            }
        }
        jPanel1.getGraphics().drawImage(offScrImg, 0, 0, jPanel1); //draws the graphics onto the Jpanel, where it can be seen.
        repain2();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jSpinner1 = new javax.swing.JSpinner();
        jCheckBox5 = new javax.swing.JCheckBox();
        jSpinner2 = new javax.swing.JSpinner();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel1ComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 665, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 354, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jPanel2ComponentResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 667, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jCheckBox1.setText("Rash");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("Fever");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox3.setText("Sneezing");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jCheckBox4.setText("Vomiting/Diarrhea");
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setText("  Home");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setText("  School");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));
        jSpinner1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                jSpinner1InputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
        });

        jCheckBox5.setText("Custom Symptom");
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(0, 0, 100, 1));

        jTextField3.setEditable(false);
        jTextField3.setText(" % to infect >");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jTextField4.setEditable(false);
        jTextField4.setText(" % to go home >");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox2))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBox2)
                        .addComponent(jCheckBox1)
                        .addComponent(jCheckBox4)
                        .addComponent(jCheckBox3)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox5)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        play = !play; //toggles play from on to off and vice versa.
        if(play) jButton1.setText("Pause"); //sets text to pause if play is not active
        else jButton1.setText("Play"); //text is play if play is active
        repain(); //repains everything
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel1ComponentResized
        offScrImg = createImage(jPanel1.getWidth(), jPanel1.getHeight()); //changes the img to match the dimensions on the JPanel
        offScrGraph = offScrImg.getGraphics(); //givess the graphics the dimentions of the img.
        repain(); //repains everything
    }//GEN-LAST:event_jPanel1ComponentResized

    private void jPanel2ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanel2ComponentResized
        offScrImg2 = createImage(jPanel2.getWidth(), jPanel2.getHeight()); //changes the img to match the dimensions on the JPanel
        offScrGraph2 = offScrImg2.getGraphics(); //givess the graphics the dimentions of the img.
        repain(); //repains everything
    }//GEN-LAST:event_jPanel2ComponentResized

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        System.out.println("Sneezing Symptom Set");
        box3Checked=!box3Checked;
        if(box3Checked){
           boxesChecked++;
           if(boxesChecked==1){
               Germ.percentage=76.6;
               Germ.percentag=12;
           }else
               if(boxesChecked==2){
                    Germ.percentage2=76.6;
                    Germ.percentag2=12;
               }else
                   if(boxesChecked==3){
                        Germ.percentage3=76.6;
                        Germ.percentag3=12;
                   }else
                       if(boxesChecked==4){
                            Germ.percentage4=76.6;
                            Germ.percentag4=12;
                       }else
                            if(boxesChecked==5){
                                Germ.percentage5=76.6;
                                Germ.percentag5=12;
                            }
        }
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        System.out.println("Vomiting/Diarrhea Symptom Set");
        box4Checked=!box4Checked;
        if(box4Checked){
           boxesChecked++;
           if(boxesChecked==1){
                Germ.percentage=13.9;
                Germ.percentag=35.4;
           }else
               if(boxesChecked==2){
                    Germ.percentage2=13.9;
                    Germ.percentag2=35.4;
               }else
                   if(boxesChecked==3){
                        Germ.percentage3=13.9;
                        Germ.percentag3=35.4;
                   }else
                       if(boxesChecked==4){
                        Germ.percentage4=13.9;
                        Germ.percentag4=35.4;
                       }else
                            if(boxesChecked==5){
                                Germ.percentage5=13.9;
                                Germ.percentag5=35.4;
                            }
        }
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
       System.out.println("Rash Symptom Set");
       box1Checked=!box1Checked;
       if(box1Checked){
           boxesChecked++;
           if(boxesChecked==1){
                Germ.percentage=100;
                Germ.percentag=90;
           }else
               if(boxesChecked==2){
                    Germ.percentage2=100;
                    Germ.percentag2=90;
               }else
                   if(boxesChecked==3){
                        Germ.percentage3=100;
                        Germ.percentag3=90;
                   }else
                       if(boxesChecked==4){
                            Germ.percentage4=100;
                            Germ.percentag4=90;
                       }else
                            if(boxesChecked==5){
                                Germ.percentage5=100;
                                Germ.percentag5=90;
                            }
       }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
       System.out.println("Fever Symptom Set");
       box2Checked=!box2Checked;
       if(box2Checked){
           boxesChecked++;
           if(boxesChecked==1){
                Germ.percentage=0;
                Germ.percentag=49;
           }else
               if(boxesChecked==2){
                    Germ.percentage2=0;
                    Germ.percentag2=49;
               }else
                   if(boxesChecked==3){
                        Germ.percentage3=0;
                        Germ.percentag3=49;
                   }else
                       if(boxesChecked==4){
                            Germ.percentage4=0;
                            Germ.percentag4=49;
                       }else
                            if(boxesChecked==5){
                                Germ.percentage5=(int)jSpinner1.getValue();;
                                Germ.percentag5=(int)jSpinner1.getValue();;
                            }
       }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jSpinner1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_jSpinner1InputMethodTextChanged

    }//GEN-LAST:event_jSpinner1InputMethodTextChanged

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
    System.out.println("Custom Symptom Set");
       box5Checked=!box5Checked;
       if(box5Checked){
           boxesChecked++;
           if(boxesChecked==1){
                //System.out.println(jSpinner1.getValue().toString());
                //System.out.println(jSpinner2.getValue().toString());
                Germ.percentage=(int)jSpinner1.getValue();
                Germ.percentag=(int)jSpinner2.getValue();;
           }else
               if(boxesChecked==2){
                    //System.out.println(jSpinner1.getValue().toString());
                    //System.out.println(jSpinner2.getValue().toString());
                    Germ.percentage2=(int)jSpinner1.getValue();;
                    Germ.percentag2=(int)jSpinner2.getValue();;
               }else
                   if(boxesChecked==3){
                        Germ.percentage3=(int)jSpinner1.getValue();;
                        Germ.percentag3=(int)jSpinner2.getValue();;
                   }else
                       if(boxesChecked==4){
                            Germ.percentage4=(int)jSpinner1.getValue();;
                            Germ.percentag4=(int)jSpinner2.getValue();;
                       }else
                            if(boxesChecked==5){
                                Germ.percentage5=(int)jSpinner1.getValue();;
                                Germ.percentag5=(int)jSpinner2.getValue();;
                            }
       }
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GermCanvas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GermCanvas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GermCanvas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GermCanvas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GermCanvas().setVisible(true); //makes the whole thing visible
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
