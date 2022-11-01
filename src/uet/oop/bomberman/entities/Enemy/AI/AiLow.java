package uet.oop.bomberman.entities.Enemy.AI;

public class AiLow extends AI {


    @Override
    public int calDirection() {
        return random.nextInt(4);
    }
}
