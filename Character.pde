
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
  color c;

  Character(int id, int x, int y, int size, int dgr, int heightS, int widthS, int vx, int vy, color c, int point) {
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
  void born() {
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
  void move() {

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
  void eat() {
    for (int i=0; i < food.size(); i++) {
      if (food.get(i).boom(x, y, size) == true) {
        food.remove(i);
        size++;
        point++;
      }
    }
  }

 


  void setX(int x) {
    this.x = x;
  }
  void setY(int y) {
    this.y = y;
  }
}
