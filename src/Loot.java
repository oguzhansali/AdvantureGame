import java.util.Random;

public class Loot {
    private  static final int[] loot_probabilities={15,20,30,50,15,20,30,50};
    private static final String[] weapons ={"Silah","Tüfek","Kılıç","Tabanca"};
    private static final String[] armors ={"Ağır Zırh","Orta Zırh","Hafif Zırh"};
    private static final int no_loot_probability=45;
    private  static final int[] money_probabilities={20,30,50};

    private static Random random;

    public Loot(){
        this.random=new Random();
    }//Random nesnesinin başlatılması.
    public static String getLoot(){
        int chance =random.nextInt(100)+1;
        //Loot alamaz.
        if (chance<=no_loot_probability){
            return "Hiç bir şey kazanamadınız.";
        }
        chance-=no_loot_probability;

        //Silahlar.
        for (int i=0;i< weapons.length;i++){
            if (chance<=loot_probabilities[i]){
                return weapons[i];
            }
            chance-= loot_probabilities[i];
        }
        //Zırhlar.
        for (int i=0;i< armors.length;i++){
            if (chance<=loot_probabilities[i+ weapons.length]){
                return  armors[i];
            }
            chance -= loot_probabilities[i+ weapons.length];
        }
        //Para.
        chance = random.nextInt(100)+1;
        if (chance<=25){
            if (chance<= money_probabilities[0]){
                return "10 PARA";
            }
            chance -= money_probabilities[0];
            if (chance<=money_probabilities[1]){
                return "5 PARA";
            }
            chance -= money_probabilities[1];
            if (chance<= money_probabilities[2]){
                return "1 PARA";
            }
        }
        return "Hiç bir şey kazanamadınız.";
    }

    //Ganimetin envantere eklendikten sonra ganimet özelliklerinin playere eklenmesi.
    public  void applyLootPlayer(String lootItem,Player player){
        if (lootItem.equals("PARA")){//Kazanılan paranın playere eklenmesi.
            int amount = Integer.parseInt((lootItem.split("")[0]));
            player.setMoney(player.getMoney()+amount);
            System.out.println(amount +" para kazandınız. Güncel paranız: "+ player.getMoney());
        }else if (lootItem.endsWith("Zırh")){
            player.getInventory().addArmor(lootItem);
            System.out.println("Envanterinize "+ lootItem+" eklendi.");
        }else {
            player.getInventory().addWeapon(lootItem);
            System.out.println("Envanterinize "+ lootItem+" eklendi.");

        }
    }

}
