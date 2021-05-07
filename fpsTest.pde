
ArrayList<Character> chs;
ArrayList<Food> food;

int countChs = 5;
int countFood = 200;

void setup() {
  //size(400, 400);
  fullScreen();
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

void draw() {
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
void addCharacter() {
  if (mousePressed == true) {
    chs.add(new Character(countChs, mouseX, mouseY, 10, (int)floor(random(2)), height, width, 1, 1, color((int)floor(random(255)), (int)floor(random(255)), (int)floor(random(255))), 0));
    countChs++; 
    //food.add(new Food(countFood,mouseX,mouseY, 3));
    //countFood++;
  }
}

void mouseDraggedCharacter() {
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
