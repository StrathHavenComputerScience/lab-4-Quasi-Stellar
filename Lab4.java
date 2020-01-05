public class Lab4
{
  public static void turnRight()
  {
    Robot.turnLeft();
    Robot.turnLeft();
    Robot.turnLeft();
  }
  
  public static void turnAround()
  {
    Robot.turnLeft();
    Robot.turnLeft();
  }

  public static void backUp()
  {
    turnAround();
    Robot.move();
    turnAround();
  }
  
  public static void completeBars()
  {
    for (int col=1; col<=Grid.getWidth(); col++) {
        Robot.turnLeft();
        while (!Robot.onDark()) {
            Robot.makeDark();
            Robot.move();
        }
        turnAround();
        while (Robot.frontIsClear()) {Robot.move();}
        Robot.turnLeft();
        if (col < Grid.getWidth()) {Robot.move();}
    }
  }
  
  public static void testCompleteBars1()
  {
    Robot.load("bars1.txt");
    Robot.setDelay(0.025);
    completeBars();
  }
  
  public static void testCompleteBars2()
  {
    Robot.load("bars2.txt");
    Robot.setDelay(0.025);
    completeBars();
  }
  
  public static void combinePiles()
  {
    //insert instructions below
    int queue = 0;
    while (Robot.onDark()) {
        queue += 1;
        Robot.makeLight();
        if (Robot.frontIsClear()) {Robot.move();}
    }
    turnAround();
    while (Robot.frontIsClear()) {Robot.move();}
    Robot.turnLeft();
    Robot.move();
    Robot.turnLeft();
    while (queue > 0) {
        if (!Robot.onDark()) {
            Robot.makeDark();
            queue -= 1;
        }
        if (Robot.frontIsClear()) {Robot.move();}
    }
  }

  public static void testCombinePiles1()
  {
    Robot.load("piles1.txt");
    Robot.setDelay(0.025);
    combinePiles();
  }
  
  public static void testCombinePiles2()
  {
    Robot.load("piles2.txt");
    Robot.setDelay(0.025);
    combinePiles();
  }
  
  public static int connectivity() {
      for (int dir=0; dir<=2; dir++) {
        int step = 0;
        boolean dark = false;
        for (int i=1; i<=2; i++) {
          if (Robot.frontIsClear()) {
            step ++;
            Robot.move();
          }
        }
        if (step == 2 && Robot.onDark()) {dark = true;}
        for (int i=step; i>0; i--) {backUp();}
        if (dark) {
            for (int i=1; i<=(dir%2); i++) {turnRight();}
            return dir;
        }
        for (int i=1; i<=(dir%2)+1; i++) {Robot.turnLeft();}
      }
      return 4;
    }
  public static void connectDots()
  {
    //insert instructions below
    while (true) {
        int con = connectivity();
        if (con == 4) {break;}
        for (int i=1; i<(con%2)+1; i++) {Robot.turnLeft();}
        for (int step=1; step<=2; step++) {
            Robot.move();
            if (!Robot.onDark()) {Robot.makeDark();}
        }
    }
    
    
  }
  
  public static void testConnectDots1()
  {
    Robot.load("connect1.txt");
    Robot.setDelay(0.025);
    connectDots();
  }
  
  public static void testConnectDots2()
  {
    Robot.load("connect2.txt");
    Robot.setDelay(0.025);
    connectDots();
  }
}