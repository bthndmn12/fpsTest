package processing.test.fpstest;

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

public class fpsTest extends PApplet {


ArrayList<Character> chs;
ArrayList<Food> food;

int countChs = 5;
int countFood = 200;

public void setup() {
  //size(400, 400);
  
   orientation(PORTRAIT); 
  colorMode(RGB, 255);

  chs = new ArrayList();
  food = new ArrayList();

  for (int i=0; i < 5; i++) {
    chs.add(new Character(i, (int)(random(width)), (int)floor(random(height)), 10, (int)floor(random(2)), height, width, 1, 1, color((int)floor(random(255)), (int)floor(random(255)), (int)floor(random(255))), 0));
  }
  for (int i=0; i < 200; i++) {
    food.add(new Food(countChs, (int)floor(random(width)), (int)floor(random(height)), 3));
  }
}

public void draw() {
  background(0);
  text(millis()/1000, 5, 10);
  text((int)frameRate, 5, 30);
  text("Characters",width-100,20);
  text(countChs,width-20,20);

  for (int i = 0; i<chs.size(); i++) {
    Character c = chs.get(i);
    c.born();
    c.move();
    c.eat();
    if (chs.size()== 1000) {
      chs.remove(i);
    }
    
  }

  for (Food f : food) {
    f.spawn();
  }
  //mouseDraggedCharacter();
  addCharacter();
}
public void addCharacter() {
  if (mousePressed == true) {
    chs.add(new Character(countChs, mouseX, mouseY, 10, (int)floor(random(2)), height, width, 1, 1, color((int)floor(random(255)), (int)floor(random(255)), (int)floor(random(255))), 0));
    countChs++; 
    //food.add(new Food(countFood,mouseX,mouseY, 3));
    //countFood++;
  }
}

public void mouseDraggedCharacter() {
  if (mousePressed == true) {
    for (int i=0; i < chs.size(); i++) {
      if (((chs.get(i).x <= mouseX+chs.get(i).size)&&(chs.get(i).x>=mouseX-chs.get(i).size))&&((chs.get(i).y<=mouseY+chs.get(i).size)&&(chs.get(i).y>=mouseY-chs.get(i).size)) ) {
        //chs.get(i).x = mouseX;
        //chs.get(i).y = mouseY;
        chs.get(i).setX(mouseX);
        chs.get(i).setY(mouseY);
      }
      //((chs.get(i).x <= mouseX+chs.get(i).size)&&(x>=mouseX-chs.get(i).size))&&((chs.get(i).y<=mouseY+chs.get(i).size)&&(chs.get(i).y>=mouseY-chs.get(i).size))
    }
  }
}

class Character {
  int id;
  int x, y;
  int size;
  int dgr;  
  int point;
  int heightS;
  int widthS;
  int vx, vy;
  boolean dieCh = false;
  boolean bornCh = false;
  int c;

  Character(int id, int x, int y, int size, int dgr, int heightS, int widthS, int vx, int vy, int c, int point) {
    this.id = id;
    this.x =x;
    this.y = y;
    this.vx = vx;
    this.vy = vy;
    this.size = size;
    this.dgr = dgr;
    this.point = point;
    this.heightS = heightS;
    this.widthS = widthS;
    this.c = c;
  }
  public void born() {
    if (dgr == 0) {
      fill(c);
      //text(id+" "+point, x+size, y+size);
      circle(x, y, size);
    } else if (dgr == 1) {
      fill(c);
      //text(id+" "+point, x+size, y+size);
      rect(x, y, size, size);
    }
  }
  public void move() {

    if (dgr == 0) {
      //x+=2*vx;
      //y+=2*vy;
      //if(x == 400 || x == 0){
      //   vx = -vx;
      //}
      //if(y == 400 || y == 0){
      //   vy =-vy;
      //}
      int r = floor(randomGaussian() * 8);
      switch(r) {
      case 0:
        x += 1;
        break;
      case 1:
        x -= 1;
        break;
      case 2:
        y += 1;
        break;
      case 3:
        y -= 1;
        break;
      case 4:
        x += 2;
        break;
      case 5:
        x -= 2;
        break;
      case 6:
        y += 2;
        break;
      case 7:
        y -= 2;
        break;
      }

      if (x >= widthS) {
        x-=1;
      }
      if (x <= 0) {
        x+=1;
      }
      if (y >= heightS) {
        y-=1;
      }
      if (y <= 0) {
        y+=1;
      }
    }
    if (dgr == 1) {
      int r = floor(random(4));
      switch(r) {
      case 0:
        x += 1;
        break;
      case 1:
        x -= 1;
        break;
      case 2:
        y += 1;
        break;
      case 3:
        y -= 1;
        break;
      }
      if (x >= widthS) {
        x-=1;
      }
      if (x <= 0) {
        x+=1;
      }
      if (y >= heightS) {
        y-=1;
      }
      if (y <= 0) {
        y+=1;
      }
    }
  }
  public void eat() {
    for (int i=0; i < food.size(); i++) {
      if (food.get(i).boom(x, y, size) == true) {
        food.remove(i);
        size++;
        point++;
      }
    }
  }

 


  public void setX(int x) {
    this.x = x;
  }
  public void setY(int y) {
    this.y = y;
  }
}
class Food {

  Character ch;

  int id;
  int xF, yF;
  int size;
  boolean die;
  boolean getF = false;

  Food(int id, int xF, int yF, int size) {
    this.id = id;
    this.xF = xF;
    this.yF = yF;
    this.size = size;
  }
  public void spawn() {
    //text(id, xF+size, yF+size);
    fill(255);
    rect(xF, yF, size, size);
  }
  public boolean boom(int x, int y, int sizeX) {
    die = false;
    if (((x <= xF+sizeX)&&(x>=xF-sizeX))&&((y<=yF+sizeX)&&(y>=yF-sizeX))) {
      die = true;
    }
    return die;
  }
}
  public void settings() {  fullScreen(); }
}
