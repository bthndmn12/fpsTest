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
  void spawn() {
    //text(id, xF+size, yF+size);
    fill(255);
    rect(xF, yF, size, size);
  }
  boolean boom(int x, int y, int sizeX) {
    die = false;
    if (((x <= xF+sizeX)&&(x>=xF-sizeX))&&((y<=yF+sizeX)&&(y>=yF-sizeX))) {
      die = true;
    }
    return die;
  }
}
