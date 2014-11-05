import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class flappy extends PApplet {

PImage [] numbers = new PImage [10];
PImage [] images = new PImage[8];
PImage [] numbersScore = new PImage [10];
PImage tube;
PImage grid;
PImage day;
PImage night;
PImage play;
PImage leader;
PImage score;
PImage gameOver;
PImage flappyBird;

PImage first;
int currentFrame = 1;
int currentNumber = 0;
int currentNumber10 = 0;
int currentNumber100 = 0;
int currentNumberTotal = 0;
int currNumX = 300;
int currNum10X = 252;
int currNum100X = 204;
float yPosBird = 450;
float xPosTube = 800;
float x1PosTube = 1200;
int speed = -10;
int randomTubeHeight = 1;
int randomTubeHeight1 = 1;
float yPosTube = 550;
float y1PosTube = 770;
float xPosGrid = 0;
float gravity = 0;
float wiggleAngle = 0.0f;
float wiggleSpeed = 0.3f;
float count = 0;
int yPosFirst = 240;
int mousePressedCount = -1;
boolean inTube = false;
boolean inTube1 = false;
boolean gameOverFall = false;
boolean gameOverFallDown = false;
float transFirst = 255;
float transFirst1 = 255;
float randomDayOrNight;
boolean gravityTest = false;
float gravityPush =0;
float gravitySum;
float rotation;
int gameMode = 0;
boolean transMode = false;
int playLeader = 960;
float tintGameOver = 0;



public void setup(){
  size(640,960);
  
  tube = loadImage("tube.png");
  
  grid = loadImage("grid.gif");
  
  day = loadImage("day.gif");
  
  night = loadImage("night.gif");
  
  first = loadImage("first.png");
  
  play = loadImage("play.gif");
  
  score = loadImage ("score.gif");
  
  leader = loadImage ("leader.gif");
  
  gameOver = loadImage ("gameOver.gif");
  
  flappyBird = loadImage ("flappyBird.gif");
  
  float tubeHeight1 = random(0,-404);
  
  for (int i=1; i < 8; i++) {
   String imageName = "frame" +i+".gif";
   images[i] = loadImage(imageName);
  }
  for (int i=0; i <10; i++) {
   String imageName = i+".gif";
   numbers[i] = loadImage(imageName);
  }
  
   for (int i=0; i <10; i++) {
   String imageName = i+"score.gif";
   numbersScore[i] = loadImage(imageName);
  }
  

 

  frameRate(24);
}


public void draw(){
  
 //////////////
 //BACKGROUND//
 //////////////
// if (gameMode == true){
 //randomDayOrNight = random(1,2);
// if (randomDayOrNight >= 1){
  image(day,0,0);
// }
//if ( randomDayOrNight <= 1){
// image(night,0,0);
//} 
//}
 
  
  
  
 if ( gameMode == 0){                                  /////////////////////////GAME MODE 0////////////////////////////////
 tint(255, transFirst1);
   image(flappyBird,127,248);
   tint(255,255);
 tint(255, transFirst1);
   tint(255,255);
   image(play,58,671);
 tint(255, transFirst1);
   image(leader,352,671);
   tint(255,255);
 tint(255, transFirst1);
   image(images[currentFrame],285,391,78,55);
   tint(255,255);
  currentFrame++;
  if (currentFrame >= 8){
    currentFrame = 1 ;  
   }
   if (mousePressed == true && mouseY >= 671 && mouseY <= 799 && mouseX >= 58 && mouseX <= 288){
     transMode = true;
  }
  if (transMode == true){
    transFirst1 = transFirst1-50 ;
  }
  mousePressed = false;
  if (transFirst1 <= 30){
    gameMode = 1;
    transFirst1 = 0;
  }
  
 }
 
 
 
 if(gameMode == 1){                                    /////////////////////////GAME MODE 1////////////////////////////////
  float wiggle = 0;                          
  
    
    wiggle = 2 + sin(wiggleAngle)*10;
    wiggleAngle += wiggleSpeed ;
  
    if (mousePressed == true){  
  gameMode = 2;
  }
  yPosBird = 450;
  pushMatrix();
  imageMode(CENTER);
  translate(25,40);
  translate(165, yPosBird + wiggle);
  image(images[currentFrame],0,0,78,55);
  currentFrame++;
  if (currentFrame >= 8){
    currentFrame = 1 ;
  }
popMatrix();

imageMode(CORNERS);
   
  if (transFirst <= 30){
    transFirst = 0;
  }
  
  tint(255, transFirst);
  image(first,116,240);
  tint(255,255);
  
}
 
 
 if (gameMode == 2){                                   /////////////////////////GAME MODE 2////////////////////////////////
  gravitySum = (-gravityPush + gravity);
    
if (mousePressedCount == 1) {
  gravityPush = 20;
  gravity = 0;
}
else {
  gravityPush--;
  gravity = gravity+1.05f;
}


if (gravitySum >=25){
  gravitySum = 25;
}
 if(gameMode == 2 && gameOverFall == false){
   yPosBird = yPosBird + gravitySum;
 }
  
  if (mousePressed == true){                  
    mousePressedCount = mousePressedCount + 1;
    
  }
  else { mousePressedCount = 0;
}


}
 if(gameMode==2 && gameOverFall == false){
   rotation = map(gravitySum,-30,25,-70,30);
 }

 
 
   if (xPosTube <-100){                  
    xPosTube = 700;
  }
  
  if (x1PosTube <-100){                 
    x1PosTube = 700;
  }
  
  if (xPosTube == 700){                     
    randomTubeHeight = 1;
  } 
  else {
    randomTubeHeight = 0;
  }
  
  if (randomTubeHeight == 1) {
    yPosTube = random(-820,-420);
  }
    
      if (x1PosTube == 700){               
    randomTubeHeight1 = 1;
  } 
  else {
    randomTubeHeight1 = 0;
  }
  
  if (randomTubeHeight1 == 1) {
    y1PosTube = random(-820,-420);
  }
  
  fill(222,216,149);                         
  rect(0,848,640,112);                       
  image(tube,xPosTube,yPosTube);
  image(tube,x1PosTube,y1PosTube);

if(gameMode ==2){
pushMatrix();
  imageMode(CENTER);
  translate(25,40);
  translate(165, yPosBird );
  rotate(radians(rotation));
  image(images[currentFrame],0,0,78,55);
  currentFrame++;
  if (currentFrame >= 8){
    currentFrame = 1 ;
  }
popMatrix();

imageMode(CORNERS);


  if ( xPosTube < 243 && xPosTube > 51){      
    inTube = true;
  }
  else { 
    inTube = false;
  }
     if ( x1PosTube < 243 && x1PosTube > 51){
    inTube1 = true;
  }
  else { 
    inTube1 = false;
  }
  
  if  ((( yPosBird  < yPosTube + 910 && inTube == true)  || (yPosBird + 55 > yPosTube + 910 + 215 && inTube == true)) || (( yPosBird < y1PosTube + 910 && inTube1 == true)  || (yPosBird +55  > y1PosTube + 910 + 215 && inTube1 == true))){
    gameOverFall = true;  
    }

    
    if(gameOverFall == true){
      speed= 0;
      yPosBird = yPosBird + 30;
      rotation = rotation + 10;
      if(rotation>=90){
        rotation = 90;
        
      }
    }
    if (yPosBird - 27 > 780){
    gameOverFallDown = true;
    }
    
      
  if (gameOverFallDown == true){
       gameMode = 3;
        playLeader = 960;
    xPosTube = 800;
    x1PosTube = 1200;
    gravityPush = 0; 
    yPosFirst = 240;
    transFirst = 255;
    gameOverFall = false;
    gameOverFallDown = false;
    speed = -10;
    tintGameOver = 0;
  }
  

  

   
  
  if (gameMode == 2){  
  xPosTube = xPosTube + speed;
  x1PosTube = x1PosTube + speed;
  transFirst = transFirst-50 ;
    }  
  
  
  if(currentNumberTotal == 0){
    currentNumber =0;
    currentNumber10 =0;
    currentNumber100 =0;
  }
  
  println(currentNumber100,currentNumber10,currentNumber);
  println(currNum100X,currNum10X,currNumX);
  
  if(currentNumber10 == 0 && currentNumber100 == 0){
   currNumX = 292;
 }
 if(currentNumber != 1 && currentNumber10 == 1 && currentNumber100 == 0 ){             ////01[]
   currNum10X = 273;
   currNumX = 311;
 } 
 if(currentNumber == 1  && currentNumber10 != 1 && currentNumber100 == 0){             ////0[]1
   currNumX= 310;
   currNum10X = 274;
 }
 if ( currentNumber == 1 && currentNumber10 ==1 && currentNumber100 == 0){             ////011
   currNumX= 305;
   currNum10X = 278;
 }
 
if ( currentNumber != 1 && currentNumber10 != 1 && currentNumber100 == 0){             ////0[][]
   currNumX= 316;
   currNum10X = 268;
}
if (currentNumber !=1 && currentNumber10 != 1 &&  currentNumber100 == 1){             ////1[][]
   
   currNumX = 335; 
   currNum10X = 287;
   currNum100X = 249;
}
if (currentNumber == 1 && currentNumber10 == 1 && currentNumber100 == 1){             ////111
  
   currNumX = 319; 
   currNum10X = 292;
   currNum100X = 265;
}

if (currentNumber == 1 && currentNumber10 != 1 && currentNumber100 == 1){             ////1[]1
  
   currNumX = 329; 
   currNum10X = 293;
   currNum100X = 254;
}
if (currentNumber != 1 && currentNumber10 == 1 && currentNumber100 == 1){             ////11[]
  
   currNumX = 325; 
   currNum10X = 286;
   currNum100X = 259;
}

if (currentNumber == 1 && currentNumber10 != 1 && currentNumber100 != 1){             ////[][]1
  
   currNumX = 334; 
   currNum10X = 298;
   currNum100X = 250;
}

if (currentNumber != 1 && currentNumber10 == 1 && currentNumber100 != 1){             ////[]1[]
  
   currNumX = 328; 
   currNum10X = 289;
   currNum100X = 253;
}

if (currentNumber == 1 && currentNumber10 == 1 && currentNumber100 != 1){             ////[]11
  
   currNumX = 323; 
   currNum10X = 296;
   currNum100X = 260;
}


  if(xPosTube == 130){
   currentNumberTotal++;
  }
  if(x1PosTube == 130){
   currentNumberTotal++;
  }
 
  if(xPosTube == 130){
   currentNumber++;
  }
  if(x1PosTube == 130){
   currentNumber++;
  }
  
  if (currentNumber >= 10){
    currentNumber = 0 ;
    currentNumber10++;
  }
 
  if (currentNumber10 >= 10){
    currentNumber10 = 0 ;
    currentNumber100++ ;
  }
  
  if (currentNumber100 >= 10){
    currentNumber100 = 0 ;
  }
 
  
 println(currNumX);
 
  if( currentNumberTotal >=100){
  image(numbers[currentNumber100],currNum100X,100);
  }
  
  if( currentNumberTotal >=10){
    image(numbers[currentNumber10],currNum10X,100);
  }
  
  image(numbers [currentNumber],currNumX,100);
 
 
 

 
}




 
  if (gameMode == 3){                                    ////////////////////////GAME MODE 3///////////////////////////////////
  
 playLeader = playLeader-30;
 

 
  if(playLeader <= 700){
    playLeader = 671;
  }
  
  tintGameOver = tintGameOver + 30;
  
  if (tintGameOver >= 245){
    tintGameOver = 255;
  }
  
   tint(255,tintGameOver);
   image(score,70,345);
   
   tint(255,tintGameOver);
   image(gameOver,107,205);
  
  
   
   image(numbersScore [currentNumber],492,420);
   
   if( currentNumberTotal >=10){
   image(numbersScore [currentNumber10],455,420);
   }
   
   if( currentNumberTotal >=100){
   image(numbersScore [currentNumber100],418,420);
   }
   tint (255,255);
    image(play,58,playLeader);
   image(leader,352,playLeader);
   
   if (mousePressed == true && mouseY >= 671 && mouseY <= 799 && mouseX >= 58 && mouseX <= 288){
     gameMode = 1;
     currentNumberTotal = 0;
   }
  mousePressed = false;
  
  

  
   
  }
 
  
   
   xPosGrid = xPosGrid + speed; 
   
   if (xPosGrid < -64){
    xPosGrid = 0;
  }  
  
  
  
  
  ///GRID///
  
  noStroke();
  fill(88,129,36);
  rect(0,840,640,4);
  image(grid,xPosGrid,808);
  
  
  noStroke();
  fill(227,252,139);
  rect(0,804,640,4);
  
  
  noStroke();                                
    fill(222,216,149);
    rect(0,848,640,112);
  
  noStroke();
  fill(215,168,76);
  rect(0,844,640,4);
  
  noStroke();
  fill(0);
  rect(0,800,640,4);
  
  
 
 
}

    


  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--full-screen", "--bgcolor=#666666", "--stop-color=#cccccc", "flappy" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
