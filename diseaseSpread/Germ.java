
package scifair;

import java.util.Timer;
import static scifair.GermCanvas.boxesChecked;

public class Germ {
    //the following code declares and instantiates some variables to be used later on
    int x = 0; //x coordinate
    int y = 0; //y coordinate
    static double percentag=0; //chance to go home
    static double percentage=0; //chance to infect
    static double percentag2=0; //chance to go home
    static double percentage2=0; //chance to infect
    static double percentag3=0; //chance to go home
    static double percentage3=0; //chance to infect
    static double percentag4=0; //chance to go home
    static double percentage4=0; //chance to infect
    static double percentag5=0; //chance to go home
    static double percentage5=0; //chance to infect
    int goneBefore=0;
    int dead=0;
    int inPanel2=0;
    int quadrentNum=0;
    int neverGetImmuneAgain=0;
    static int currentPopX=0;
    static int currentPopY=0;
    static int currentPop=0;
    static int currentPopX2=0;
    static int currentPopY2=0;
    static int currentPop2=0;
    static int germsInfected=0;
    static int germsInfected2=0;
    long timeInfected=0;
    int status = 0; //0 uninfected, 1 infected, 2 immune
    float randomNumber = 0; //50% of germs move down and right, 50% move up and left, this number determines which one this germ does.
    int width = 3; //how big all germs are
    static int uninfectedPopulation = 0; // a variable shared by EVERY germ. This number is added to every time a new germ is created
    static int infectedPopulation = 0;
    static int infectedPopulation2=0;
    static int immunePopulation = 0;
    static int[] xList = new int[1100];
    static int[] yList = new int[1100];
    static int[] xList2 = new int[1100];
    static int[] yList2 = new int[1100];
    int color = 0;
    int w = 665, h = 708; //just creating w(width) and h(height) to be used later.
    Germ[][] germs;   
    Germ[][] germs2;
    Timer time;
    boolean[][]uninfectedGerms;
    //double percentag3;
    
    public Germ(int x, int y, int status,Germ[][] germs,Germ[][] germs2,boolean[][]uninfectedGerms,int inPanel2,long timeInfected,int goneBefore,int neverGetImmuneAgain) { //constructor here 
        this.x = x; //when the germ is created in GermCanvas, it gets assigned an x and y value, these
        this.y = y; //lines of code give the assigned value to the germ.
        this.status = status; //when its created it gets a status too.
        this.neverGetImmuneAgain= neverGetImmuneAgain;
        this.germs=germs;
        this.germs2=germs2;
        this.inPanel2=inPanel2;
        this.uninfectedGerms=uninfectedGerms;
        this.timeInfected=timeInfected;
        this.goneBefore=goneBefore;
        randomNumber = (float) Math.random(); // decides if this germ will have a tendency to move down and right or left and up. =
        quadrentNum = (int)(Math.random()*24+1);
        if(status==0){
            uninfectedPopulation++; //adds one to this variable which is shared by EVERY germ.
        }else
            if(status==1){
                infectedPopulation++;
            }else
                if(status==2){
                    immunePopulation++;
                }
    }
//    public void atEdges(){ //if the germ is at an edge, changes the x/y coordinate to that of the opposite edge.
//        if(atRightEdge()){
//            x=0;
//        }else
//            if(atLeftEdge()){
//                x=665;
//            }else
//                if(atTopEdge()){
//                    y=708;
//                }else
//                    if(atBottomEdge()){
//                        y=0;
//                    }
//    }
//    public boolean atRightEdge(){
//        if(x==665){ //the very right of the array
//            return true;
//        }else
//            return false;
//    }
//    public boolean atLeftEdge(){
//        if(x==0){ //the very left of the array
//            return true;
//        }else
//            return false;
//    }
//    public boolean atTopEdge(){
//        if(y==0){ //the very top of the array
//            return true;
//        }else
//            return false;
//    }
//    public boolean atBottomEdge(){
//        if(y==708){ //the very bottom of the array
//            return true;
//        }else
//            return false;
//    }
    
//    public void goHome(int percentag){
//        if(status==1){
//            
//        }
//    }
//    public void kill(){
//        for(int x = 0 ; x < w ; x++){    //when using a loop like this, plug in x and    PARSES THE
//            for(int y = 0 ; y < h; y++){ // y for the x and y in the array boxes[][]       ARRAY
//               if(germs[x][y]!=null&&germs[x][y].dead==1){
//                   System.out.println("..."+germs[x][y].dead);
//                   germs[x][y]=null;
//               }
//            }
//        }
//    }
    
    public void becomeImmune(){
        if(timeInfected==0){
            timeInfected=System.currentTimeMillis();
        }
        if(System.currentTimeMillis()-timeInfected>=60000&&neverGetImmuneAgain==0){
            status=2;
            infectedPopulation--;
            immunePopulation++;
            //System.out.println("..."+immunePopulation);
            goneBefore=0;
            neverGetImmuneAgain=1;
            this.neverGetImmuneAgain= neverGetImmuneAgain;
        }
    } 
    
    public void moveSensibly(){
        if(status==0){ //if uninfected
            //atEdges(); //moves from edge to edge
            moveRandomly(); //moves randomly
            changeColor(); //changes color depending on status
        }else
            if(status==1){ //if infected
                //atEdges(); //moves from edge to edge
                moveRandomly(); //moves randomly
                changeColor(); //changes color depending on status
                checkNeighbors(percentage);
                becomeImmune();
            }else
                if(status==2){ //if immune
                    //atEdges(); //moves from edge to edge
                    moveRandomly(); //moves randomly
                    changeColor(); //changes color depending on status
                }
    }
    public void moveSensibly2(){
        if(status==0){ //if uninfected
            //atEdges(); //moves from edge to edge
            decide(); //moves randomly
            changeColor(); //changes color depending on status
        }else
            if(status==1){ //if infected
                //atEdges(); //moves from edge to edge
                decide(); //moves randomly
                changeColor(); //changes color depending on status
                checkNeighbors2(percentage);
                becomeImmune();
            }else
                if(status==2){ //if immune
                    //atEdges(); //moves from edge to edge
                    decide(); //moves randomly
                    changeColor(); //changes color depending on status
                }
    }
    public void infectOthers(double percentage){
        if(status==1){
            while(currentPopX>0&&currentPopY>0){    
                //System.out.println("xList..."+xList[currentPopX]+" yList..."+yList[currentPopY]);
                //System.out.println("currentPopX..."+currentPopX+"CurrentPopY..."+currentPopY);
                if(xList[currentPopX]!=0&&yList[currentPopY]!=0){
                    if(Math.random()*100+1<=percentage){
                        germs[xList[currentPopX]][yList[currentPopY]].status=1;
                        uninfectedPopulation--;
                        infectedPopulation++;
                        germsInfected++;
                    }
                }
                currentPopX--;
                currentPopY--;
                //System.out.println("Decent so far.............."+infectedPopulation);
            }
        }
    }
    public void infectOthersV2(double percentage2){
        if(status==1){
            while(currentPopX>0&&currentPopY>0){    
                //System.out.println("xList..."+xList[currentPopX]+" yList..."+yList[currentPopY]);
                //System.out.println("currentPopX..."+currentPopX+"CurrentPopY..."+currentPopY);
                if(xList[currentPopX]!=0&&yList[currentPopY]!=0){
                    if(Math.random()*100+1<=percentage2){
                        germs[xList[currentPopX]][yList[currentPopY]].status=1;
                        uninfectedPopulation--;
                        infectedPopulation++;
                        germsInfected++;
                    }
                }
                currentPopX--;
                currentPopY--;
                //System.out.println("Decent so far.............."+infectedPopulation);
            }
        }
    }
    public void infectOthersV3(double percentage3){
        if(status==1){
            while(currentPopX>0&&currentPopY>0){    
                //System.out.println("xList..."+xList[currentPopX]+" yList..."+yList[currentPopY]);
                //System.out.println("currentPopX..."+currentPopX+"CurrentPopY..."+currentPopY);
                if(xList[currentPopX]!=0&&yList[currentPopY]!=0){
                    if(Math.random()*100+1<=percentage3){
                        germs[xList[currentPopX]][yList[currentPopY]].status=1;
                        uninfectedPopulation--;
                        infectedPopulation++;
                        germsInfected++;
                    }
                }
                currentPopX--;
                currentPopY--;
                //System.out.println("Decent so far.............."+infectedPopulation);
            }
        }
    }
    public void infectOthersV4(double percentage4){
        if(status==1){
            while(currentPopX>0&&currentPopY>0){    
                //System.out.println("xList..."+xList[currentPopX]+" yList..."+yList[currentPopY]);
                //System.out.println("currentPopX..."+currentPopX+"CurrentPopY..."+currentPopY);
                if(xList[currentPopX]!=0&&yList[currentPopY]!=0){
                    if(Math.random()*100+1<=percentage4){
                        germs[xList[currentPopX]][yList[currentPopY]].status=1;
                        uninfectedPopulation--;
                        infectedPopulation++;
                        germsInfected++;
                    }
                }
                currentPopX--;
                currentPopY--;
                //System.out.println("Decent so far.............."+infectedPopulation);
            }
        }
    }
    public void infectOthersV5(double percentage5){
        if(status==1){
            while(currentPopX>0&&currentPopY>0){    
                //System.out.println("xList..."+xList[currentPopX]+" yList..."+yList[currentPopY]);
                //System.out.println("currentPopX..."+currentPopX+"CurrentPopY..."+currentPopY);
                if(xList[currentPopX]!=0&&yList[currentPopY]!=0){
                    if(Math.random()*100+1<=percentage5){
                        germs[xList[currentPopX]][yList[currentPopY]].status=1;
                        uninfectedPopulation--;
                        infectedPopulation++;
                        germsInfected++;
                    }
                }
                currentPopX--;
                currentPopY--;
                //System.out.println("Decent so far.............."+infectedPopulation);
            }
        }
    }
    
    public void checkNeighbors(double percentage){
    if(x>=2&&x<=664&&y>=2&&y<=707){  
    if(status==1){
        if(x > 0){
            if(germs[x-1][y]!=null&&germs[x-1][y].status==0){
                currentPopX++;
                currentPopY++;
                xList[currentPopX]=x-1; 
                yList[currentPopY]=y;
            } 
            if(y>0) if(germs[x-1][y-1]!=null&&germs[x-1][y-1].status==0){
                currentPopX++;
                currentPopY++;
                xList[currentPopX]=x-1;
                yList[currentPopY]=y-1;
            }
            if(y<h-1) if(germs[x-1][y+1]!=null&&germs[x-1][y+1].status==0){
                currentPopX++;
                currentPopY++;
                xList[currentPopX]=x-1; 
                yList[currentPopY]=y+1;
            }
        }
        if(x < w-1){
            if(germs[x+1][y]!=null&&germs[x+1][y].status==0){
                currentPopX++;
                currentPopY++;
                xList[currentPopX]=x+1; 
                yList[currentPopY]=y;
            }
            if(y>0) if(germs[x+1][y-1]!=null&&germs[x+1][y-1].status==0){
                currentPopX++;
                currentPopY++;
                xList[currentPopX]=x+1; 
                yList[currentPopY]=y-1;
            }
            if(y<h-1) if(germs[x+1][y+1]!=null&&germs[x+1][y+1].status==0){
                currentPopX++;
                currentPopY++;
                xList[currentPopX]=x+1; 
                yList[currentPopY]=y+1;
            }
        }
        if(y>0) if(germs[x][y-1]!=null&&germs[x][y-1].status==0){
            currentPopX++;
            currentPopY++;
            xList[currentPopX]=x; 
            yList[currentPopY]=y-1;
        }
        if(y<h-1) if(germs[x][y+1]!=null&&germs[x][y+1].status==0){
            currentPopX++;
            currentPopY++;
            xList[currentPopX]=x; 
            yList[currentPopY]=y+1;
        }
        //System.out.println("currentPopY..."+currentPopX+" currentPopY..."+currentPopY);
        //System.out.println("xList..."+xList[currentPopX]+" yList..."+yList[currentPopY]);
        if(GermCanvas.boxesChecked==1){
               infectOthers(percentage);
           }else
               if(GermCanvas.boxesChecked==2){
                   infectOthers(percentage);
                   infectOthersV2(percentage2);
               }else
                   if(GermCanvas.boxesChecked==3){
                       infectOthers(percentage);
                       infectOthersV2(percentage2);
                       infectOthersV3(percentage3);
                   }else
                       if(GermCanvas.boxesChecked==4){
                           infectOthers(percentage);
                           infectOthersV2(percentage2);
                           infectOthersV3(percentage3);
                           infectOthersV4(percentage4);
                       }else
                            if(boxesChecked==5){
                                infectOthers(percentage);
                                infectOthersV2(percentage2);
                                infectOthersV3(percentage3);
                                infectOthersV4(percentage4);
                                infectOthersV5(percentage5);
                            }
      }
    }
    }
    
    public void infectOthers2(double percentage){
        if(status==1){
            while(currentPopX2>0&&currentPopY2>0){    
                //System.out.println("xList2..."+xList2[currentPopX2]+" yList2..."+yList[currentPopY2]);
                //System.out.println("currentPopX2..."+currentPopX2+"CurrentPopY2..."+currentPopY2);
                if(xList2[currentPopX2]!=0&&yList2[currentPopY2]!=0){
                    if(Math.random()*100+1<=percentage){
                        germs2[xList2[currentPopX2]][yList2[currentPopY2]].status=1;
                        uninfectedPopulation--;
                        infectedPopulation++;
                        //System.out.println("Decent so far.............."+infectedPopulation);
                        germsInfected++;
                        //System.out.println("Decent so far.............."+infectedPopulation2);
                    }
                }
                currentPopX2--;
                currentPopY2--;
                //System.out.println("Decent so far.............."+infectedPopulation);
            }
        }
    }
    public void infectOthers2V2(double percentage2){
        if(status==1){
            while(currentPopX2>0&&currentPopY2>0){    
                //System.out.println("xList2..."+xList2[currentPopX2]+" yList2..."+yList[currentPopY2]);
                //System.out.println("currentPopX2..."+currentPopX2+"CurrentPopY2..."+currentPopY2);
                if(xList2[currentPopX2]!=0&&yList2[currentPopY2]!=0){
                    if(Math.random()*100+1<=percentage2){
                        germs2[xList2[currentPopX2]][yList2[currentPopY2]].status=1;
                        uninfectedPopulation--;
                        infectedPopulation++;
                        //System.out.println("Decent so far.............."+infectedPopulation);
                        germsInfected++;
                        //System.out.println("Decent so far.............."+infectedPopulation2);
                    }
                }
                currentPopX2--;
                currentPopY2--;
                //System.out.println("Decent so far.............."+infectedPopulation);
            }
        }
    }
    public void infectOthers2V3(double percentage3){
        if(status==1){
            while(currentPopX2>0&&currentPopY2>0){    
                //System.out.println("xList2..."+xList2[currentPopX2]+" yList2..."+yList[currentPopY2]);
                //System.out.println("currentPopX2..."+currentPopX2+"CurrentPopY2..."+currentPopY2);
                if(xList2[currentPopX2]!=0&&yList2[currentPopY2]!=0){
                    if(Math.random()*100+1<=percentage3){
                        germs2[xList2[currentPopX2]][yList2[currentPopY2]].status=1;
                        uninfectedPopulation--;
                        infectedPopulation++;
                        //System.out.println("Decent so far.............."+infectedPopulation);
                        germsInfected++;
                        //System.out.println("Decent so far.............."+infectedPopulation2);
                    }
                }
                currentPopX2--;
                currentPopY2--;
                //System.out.println("Decent so far.............."+infectedPopulation);
            }
        }
    }
    public void infectOthers2V4(double percentage4){
        if(status==1){
            while(currentPopX2>0&&currentPopY2>0){    
                //System.out.println("xList2..."+xList2[currentPopX2]+" yList2..."+yList[currentPopY2]);
                //System.out.println("currentPopX2..."+currentPopX2+"CurrentPopY2..."+currentPopY2);
                if(xList2[currentPopX2]!=0&&yList2[currentPopY2]!=0){
                    if(Math.random()*100+1<=percentage4){
                        germs2[xList2[currentPopX2]][yList2[currentPopY2]].status=1;
                        uninfectedPopulation--;
                        infectedPopulation++;
                        //System.out.println("Decent so far.............."+infectedPopulation);
                        germsInfected++;
                        //System.out.println("Decent so far.............."+infectedPopulation2);
                    }
                }
                currentPopX2--;
                currentPopY2--;
                //System.out.println("Decent so far.............."+infectedPopulation);
            }
        }
    }
    public void infectOthers2V5(double percentage5){
        if(status==1){
            while(currentPopX2>0&&currentPopY2>0){    
                //System.out.println("xList2..."+xList2[currentPopX2]+" yList2..."+yList[currentPopY2]);
                //System.out.println("currentPopX2..."+currentPopX2+"CurrentPopY2..."+currentPopY2);
                if(xList2[currentPopX2]!=0&&yList2[currentPopY2]!=0){
                    if(Math.random()*100+1<=percentage5){
                        germs2[xList2[currentPopX2]][yList2[currentPopY2]].status=1;
                        uninfectedPopulation--;
                        infectedPopulation++;
                        //System.out.println("Decent so far.............."+infectedPopulation);
                        germsInfected++;
                        //System.out.println("Decent so far.............."+infectedPopulation2);
                    }
                }
                currentPopX2--;
                currentPopY2--;
                //System.out.println("Decent so far.............."+infectedPopulation);
            }
        }
    }
    
    public void checkNeighbors2(double percentage){
    if(x>=2&&x<=664&&y>=2&&y<=707){  
    if(status==1){
        if(x > 0){
            if(germs2[x-1][y]!=null&&germs2[x-1][y].status==0){
                currentPopX2++;
                currentPopY2++;
                xList2[currentPopX2]=x-1; 
                yList2[currentPopY2]=y;
            } 
            if(y>0) if(germs2[x-1][y-1]!=null&&germs2[x-1][y-1].status==0){
                currentPopX2++;
                currentPopY2++;
                xList2[currentPopX2]=x-1;
                yList2[currentPopY2]=y-1;
            }
            if(y<h-1) if(germs2[x-1][y+1]!=null&&germs2[x-1][y+1].status==0){
                currentPopX2++;
                currentPopY2++;
                xList2[currentPopX2]=x-1; 
                yList2[currentPopY2]=y+1;
            }
        }
        if(x < w-1){
            if(germs2[x+1][y]!=null&&germs2[x+1][y].status==0){
                currentPopX2++;
                currentPopY2++;
                xList2[currentPopX2]=x+1; 
                yList2[currentPopY2]=y;
            }
            if(y>0) if(germs2[x+1][y-1]!=null&&germs2[x+1][y-1].status==0){
                currentPopX2++;
                currentPopY2++;
                xList2[currentPopX2]=x+1; 
                yList2[currentPopY2]=y-1;
            }
            if(y<h-1) if(germs2[x+1][y+1]!=null&&germs2[x+1][y+1].status==0){
                currentPopX2++;
                currentPopY2++;
                xList2[currentPopX2]=x+1; 
                yList2[currentPopY2]=y+1;
            }
        }
        if(y>0) if(germs2[x][y-1]!=null&&germs2[x][y-1].status==0){
            currentPopX2++;
            currentPopY2++;
            xList2[currentPopX2]=x; 
            yList2[currentPopY2]=y-1;
        }
        if(y<h-1) if(germs2[x][y+1]!=null&&germs2[x][y+1].status==0){
            currentPopX2++;
            currentPopY2++;
            xList2[currentPopX2]=x; 
            yList2[currentPopY2]=y+1;
        }
        //System.out.println("currentPopY..."+currentPopX+" currentPopY..."+currentPopY);
        //System.out.println("xList..."+xList[currentPopX]+" yList..."+yList[currentPopY]);
         if(GermCanvas.boxesChecked==1){
               infectOthers2(percentage);
           }else
               if(GermCanvas.boxesChecked==2){
                   infectOthers2(percentage);
                   infectOthers2V2(percentage2);
               }else
                   if(GermCanvas.boxesChecked==3){
                       infectOthers2(percentage);
                       infectOthers2V2(percentage);
                       infectOthers2V3(percentage3);
                   }else
                       if(GermCanvas.boxesChecked==4){
                           infectOthers2(percentage);
                           infectOthers2V2(percentage);
                           infectOthers2V3(percentage);
                           infectOthers2V4(percentage4);
                       }else
                            if(boxesChecked==5){
                                infectOthers2(percentage);
                                infectOthers2V2(percentage2);
                                infectOthers2V3(percentage3);
                                infectOthers2V4(percentage4);
                                infectOthers2V5(percentage5);
                            }
      }
    }
    }
    
    public void changeColor(){
        if(status==0){ //if uninfected
            color=0; 
        }else
            if(status==1){ //if infected
                color=1;
            }else
                if(status==2){ //if immune
                    color=2;
                } 
    }  
    
    public void moveRandomly() { //an action the germ preforms
        uninfectedGerms[x][y] = false;
        double theNumber = 0; // a double is a long number that can hold whole and decimal numbers. 2x as much space as a float.
        theNumber = Math.random(); //gives theNumber a value between 0 and 1. Could be 0.4353 for instance.
        if (randomNumber <= .5) { //makes 50% of germs have a high chance to move right and down.
            if (theNumber <= .35) {
                x++; //moves it right.
            } else if (theNumber <= .5 && theNumber >= .35) {
                x--; //moves it left.
            } else if (theNumber <= .65 && theNumber >= .5) {
                y--; //moves it up.
            } else if (theNumber >= .65) {
                y++; //moves it down.
            }
        } else if (randomNumber >= .5) { //makes 50% of germs have a high chance to move left and up.
            if (theNumber <= .35) {
                x--; //moves it left.
            } else if (theNumber <= .5 && theNumber >= .35) {
                x++; //moves it right.
            } else if (theNumber <= .65 && theNumber >= .5) {
                y++; //moves it down.
            } else if (theNumber >= .65) {
                y--; //moves it up.
            }
        }
        //this code uses mod(%), or modular division. It's like seats, where if there are three seats and it goes to five, then it comes back to 2. 5-3(mod)=2
        //makes sure the code cannot go outside of the mod(%). 12%7 is 5. 13%7 is 6. 21%2 is 1. 
        x = (x + w) % w; //keeps the nmber within bounds
        y = (y + h) % h;
        //System.out.println("x..."+x+"  y..."+y); //tells us that the program is creating germs.  

    }
       
       
    public void decide(){
        if(quadrentNum==1){
            if(x>=110){
                moveRandomlyX2();
            }else
                if(y>=177){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==2){
            if(x<=110||x>=220){
                moveRandomlyX2();
            }else
                if(y>=177){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==3){
            if(x<=220||x>=330){
                moveRandomlyX2();
            }else
                if(y>=177){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==4){
            if(x<=330||x>=440){
                moveRandomlyX2();
            }else
                if(y>=177){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==5){
            if(x<=440||x>=550){
                moveRandomlyX2();
            }else
                if(y>=177){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==6){
            if(x<=550){
                moveRandomlyX2();
            }else
                if(y>=177){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==7){
            if(x>=110){
                moveRandomlyX2();
            }else
                if(y<=177||y>=354){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==8){
            if(x<=110||x>=220){
                moveRandomlyX2();
            }else
                if(y<=177||y>=354){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==9){
            if(x<=220||x>=330){
                moveRandomlyX2();
            }else
                if(y<=177||y>=354){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==10){
            if(x<=330||x>=440){
                moveRandomlyX2();
            }else
                if(y<=177||y>=354){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==11){
            if(x<=440||x>=550){
                moveRandomlyX2();
            }else
                if(y<=177||y>=354){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==12){
            if(x<=550){
                moveRandomlyX2();
            }else
                if(y<=177||y>=354){
                    moveRandomlyY2();
                }else
                    moveRandomly();
            }else
            if(quadrentNum==13){
            if(x>=110){
                moveRandomlyX2();
            }else
                if(y<=354||y>=531){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==14){
            if(x<=110||x>=220){
                moveRandomlyX2();
            }else
                if(y<=354||y>=531){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==15){
            if(x<=220||x>=330){
                moveRandomlyX2();
            }else
                if(y<=354||y>=531){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==16){
            if(x<=330||x>=440){
                moveRandomlyX2();
            }else
                if(y<=354||y>=531){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==17){
            if(x<=440||x>=550){
                moveRandomlyX2();
            }else
                if(y<=354||y>=531){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==18){
            if(x<=550){
                moveRandomlyX2();
            }else
                if(y<=354||y>=531){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==19){
            if(x>=110){
                moveRandomlyX2();
            }else
                if(y<=531){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==20){
            if(x<=110||x>=220){
                moveRandomlyX2();
            }else
                if(y<=531){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==21){
            if(x<=220||x>=330){
                moveRandomlyX2();
            }else
                if(y<=531){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==22){
            if(x<=330||x>=440){
                moveRandomlyX2();
            }else
                if(y<=531){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==23){
            if(x<=440||x>=550){
                moveRandomlyX2();
            }else
                if(y<=531){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            if(quadrentNum==24){
            if(x<=550){
                moveRandomlyX2();
            }else
                if(y<=531){
                    moveRandomlyY2();
                }else
                    moveRandomly();
        }else
            moveRandomly();        
    }



    
    public void moveRandomlyX2() { //an action the germ preforms
        if(quadrentNum==1){
            x=55;
            y=88;
        }else
            if(quadrentNum==2){
            x=165;
            y=88;
        }else
            if(quadrentNum==3){
            x=285;
            y=88;
        }else
            if(quadrentNum==4){
            x=395;
            y=88;
        }else
            if(quadrentNum==5){
            x=505;
            y=88;
        }else
            if(quadrentNum==6){
            x=615;
            y=88;
        }else
            if(quadrentNum==7){
            x=55;
            y=265;
        }else
            if(quadrentNum==8){
            x=165;
            y=265;
        }else
            if(quadrentNum==9){
            x=285;
            y=265;
        }else
            if(quadrentNum==10){
            x=395;
            y=265;
        }else
            if(quadrentNum==11){
            x=505;
            y=265;
        }else
            if(quadrentNum==12){
            x=615;
            y=265;
        }else
            if(quadrentNum==13){
            x=55;
            y=442;
        }else
            if(quadrentNum==14){
            x=165;
            y=442;
        }else
            if(quadrentNum==15){
            x=285;
            y=442;
        }else
            if(quadrentNum==16){
            x=395;
            y=442;
        }else
            if(quadrentNum==17){
            x=505;
            y=442;
        }else
            if(quadrentNum==18){
            x=615;
            y=442;
        }else
            if(quadrentNum==19){
            x=55;
            y=619;
        }else
            if(quadrentNum==20){
            x=165;
            y=619;
        }else
            if(quadrentNum==21){
            x=285;
            y=619;
        }else
            if(quadrentNum==22){
            x=395;
            y=619;
        }else
            if(quadrentNum==23){
            x=505;
            y=619;
        }else
            if(quadrentNum==24){
            x=615;
            y=619;
        }
        //this code uses mod(%), or modular division. It's like seats, where if there are three seats and it goes to five, then it comes back to 2. 5-3(mod)=2
        //makes sure the code cannot go outside of the mod(%). 12%7 is 5. 13%7 is 6. 21%2 is 1. 
        x = (x + w) % w; //keeps the nmber within bounds
        y = (y + h) % h; 
    }
    public void moveRandomlyY2(){
        moveRandomlyX2();
    }
}
