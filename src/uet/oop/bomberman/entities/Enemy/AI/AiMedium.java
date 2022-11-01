package uet.oop.bomberman.entities.Enemy.AI;

import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Character;

public class AiMedium extends AI {

    Bomber _bomber;
    Character _enemy;
    private final int threshold = 5;

    public AiMedium(Bomber bomber, Character enemy) {
        _bomber = bomber;
        _enemy = enemy;
    }
    @Override
    public int calDirection() {
        // thuat toan tim duong di
        // neu khoang cach giua Bomber va enemy nho hon hoac bang
        //threshold thi enemy se di ve huong bomber
        // neu khoang cach lon hon thi nhu alLow
        int Xdistance = _bomber.getX() - _enemy.getX();
        int Ydistance = _bomber.getY() - _enemy.getY();
        boolean chasing = (Math.abs(Xdistance) <= threshold) && (Math.abs(Ydistance) <= threshold);
        if (chasing) {
            int moveH = random.nextInt(2);
            if (moveH == 1) {
                if (Xdistance < 0)
                    return 0; //Trai
                else return 1; //Phai
            } else {
                if (Ydistance< 0)
                    return 2; //Len
                else return 3; //Xuong
            }
        }
        return random.nextInt(4);
    }
}
