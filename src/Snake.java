import java.util.Random;

public class Snake extends Monster {

    public Snake(int id) {
        super(4, "Yılan", getRandomDamage(), 12,0);//Yilan ödülü yok,loot verileck.
    }
    private  static  int getRandomDamage(){
        Random random = new Random();
        return random.nextInt(4)+3;//3 ile 6 arasında hasar veirir.
    }


}
