import java.util.Random;

public abstract class BattleLoc  extends Location {
    private Monster monster;
    private String award;
    private int maxMonster;

    public BattleLoc(Player player,String name,Monster monster,String award,int maxMonster){
        super(player,name);
        this.monster=monster;
        this.award=award;
        this.maxMonster=maxMonster;
    }
    @Override
    public boolean onLocation(){
        int monsNumber=randomMonsterNumber();
        System.out.println("Şuan buradasınız: "+ this.getName());
        System.out.println("Dikkatli ol burada " +monsNumber+" tane "+ this.getMonster().getName()+" yaşıyor!");
        System.out.println("<S>avaş veya <K>aç");
        String selectCase = input.nextLine().toUpperCase();
        if (selectCase.equals("S")&&combat(monsNumber)){
                System.out.println(this.getName()+" tüm düşmanları yendiniz!");
                return true;
        }
        if (this.getPlayer().getHealth()<=0){
            System.out.println("Öldünüz.");
            return false;
        }
        return true;
    }
    public boolean combat(int monsNumber){
        Loot loot = new Loot();//Loot sınıfı örneği oluşturur.
        for(int i=1; i<=monsNumber;i++){
            this.getMonster().setHealth(this.getMonster().getOrjinalHealth());
            playerStats();
            monsterStats(i);
            while (this.getPlayer().getHealth()>0&&this.getMonster().getHealth()>0){
                System.out.print("<V>ur veya <K>aç");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("V")){
                    System.out.println("Siz vurdunuz.");
                    monster.setHealth(this.monster.getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                    if (this.getMonster().getHealth()>0){
                        System.out.println();
                        System.out.println("Canavar size vurdu!");
                        int monsterDamage=this.getMonster().getDamage()-this.getPlayer().getInventory().getArmor().getBlock();
                        if (monsterDamage<0){
                            monsterDamage=0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth()-monsterDamage);
                        afterHit();
                    }

                }else {
                    return false;
                }

            }
            if (this.getMonster().getHealth()<this.getPlayer().getHealth()){
                System.out.println("Düşmanı yendiniz!");
                System.out.println(this.getMonster().getAward()+" para kazandınız.");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+this.getMonster().getAward());
                System.out.println("Güncel paranız: "+getPlayer().getMoney());
                //Ganimet ekleme
                String lootItem = Loot.getLoot();
                System.out.println("Ganimet: "+lootItem);
                //Envantere ganimet ekleme.
                if (!lootItem.equals("Hiç bir şey kazanamadınız.")){
                    if (lootItem.endsWith("PARA")){
                        int amount = Integer.parseInt((lootItem.split(" ")[0]));
                        this.getPlayer().setMoney(this.getPlayer().getMoney()+amount);
                        System.out.println(amount+ " para kazandınız. Güncel paranız: " + this.getPlayer().getMoney());
                    }
                    else {
                        this.getPlayer().getInventory().addItem(lootItem);
                        System.out.println("Envanterinize "+ lootItem+ " eklendi.");
                    }
                }
            }else {
                return false;
            }
        }

        return true;
    }
    public void afterHit(){
        System.out.println("Canınız:" + this.getPlayer().getHealth());
        System.out.println(this.getMonster().getName()+" Canı: "+ this.getMonster().getHealth());
        System.out.println();
    }
    public void playerStats(){
        System.out.println("Oyuncu Değerleri:");
        System.out.println("---------------");
        System.out.println("Sağlık: " + this.getPlayer().getHealth());
        System.out.println("Silah: "+ this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zırh:" + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama:"+this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Hasar:" + this.getPlayer().getTotalDamage());
        System.out.println("Para: " +this.getPlayer().getMoney());

    }

    public void monsterStats(int i){
        System.out.println(i + "."+ this.getMonster().getName()+ " değerleri");
        System.out.println("---------------");
        System.out.println("Sağlık: "+ this.getMonster().getHealth());
        System.out.println("Hasar: "+this.getMonster().getDamage());
        System.out.println("Ödül: "+this.getMonster().getAward());
    }
    public int randomMonsterNumber(){
        Random r= new Random();
        return r.nextInt(this.maxMonster)+1;
    }
    public Monster getMonster(){
        return monster;
    }
    public void setMonster(Monster monster){
        this.monster=monster;
    }
    public String getAward(){
        return award;
    }
    public void setAward(String award){
        this.award=award;
    }
    public int getMaxMonster(){
        return maxMonster;
    }
    public void setMaxMonster(int maxMonster){
        this.maxMonster=maxMonster;
    }
}
