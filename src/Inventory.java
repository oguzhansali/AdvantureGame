public class Inventory {
    private Weapon weapon;
    private Armor armor;
    public Inventory(){
        this.weapon=new Weapon("yumruk",-1,0,0);
        this.armor=new Armor(-1,"Paçavra",0,0);
    }

    public Weapon getWeapon() {
        return weapon;
    }
    public void setWeapon(Weapon weapon){
        this.weapon=weapon;
    }
    public Armor getArmor(){
        return armor;
    }
    public void setArmor(Armor armor){
        this.armor=armor;
    }

    public void addItem(String lootItem) {
        if (lootItem.endsWith("Para")){}
    }

    public void addArmor(String lootItem) {
    }

    public void addWeapon(String lootItem) {
    }
}
