
public class ToolStore extends NormalLocation {
    public ToolStore(Player player) {
        super(player, "Mağaza.");
    }

    @Override
    public boolean onLocation() {
        System.out.println();
        System.out.println("%% Mağazaya hoş geldiniz %%");
        System.out.println();

        boolean showMenu = true;
        while (showMenu) {
            System.out.println("1-Silahlar \t 2-Zırhlar \t 3-Çıkış Yap ");
            System.out.print("Yapmak isteğiniz işlemi seçiniz.");
            System.out.println();
            int selectCase = input.nextInt();
            while (selectCase < 1 || selectCase > 3) {
                System.out.print("Geçersiz değer, tekrar giriniz: ");
                selectCase = input.nextInt();
            }
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Görüşmek üzere:)");
                    showMenu = false;
                    break;
            }

        }
        return true;

    }

    public void printWeapon() {
        System.out.println("--- Silahlar ---");
        System.out.println();
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + " " + w.getName() + "<Para : " + w.getValue() + " Hasar: " + w.getDamage());
        }
        System.out.println("0-Çıkış yap.");

    }

    public void buyWeapon() {
        System.out.println("Bir silah seçiniz:");
        int selectWeaponID = input.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length) {
            System.out.println("Lütfen Geçerli bir silah seçiniz!");
            selectWeaponID = input.nextInt();
        }
        if (selectWeaponID != 0) {
            Weapon selectedWeapon = Weapon.getWeaponObjById(selectWeaponID);

            if (selectedWeapon != null) {
                //Satın alım işlemi.
                if (selectedWeapon.getValue() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmamaktadır.");
                } else {
                    System.out.println(selectedWeapon.getName() + " silahını satın alıdnız.");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getValue();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız: " + this.getPlayer().getMoney());
                    System.out.println("Önceki silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
                    //System.out.println("Yeni silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    //System.out.println("Yeni silahınız: " + this.getPlayer().getInventory().getWeapon().getName());

                }
            }
        }

    }

    public void printArmor() {
        System.out.println("--- Zırhlar ---");
        System.out.println();
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() +a.getName() + "<Para : " + a.getValue() + " Zırh: " + a.getBlock());
        }
        System.out.println("0-Çıkış Yap.");
    }

    public void buyArmor() {
        System.out.println("Bir Zırh seçiniz:");
        int selectArmorID = input.nextInt();
        while (selectArmorID < 0 || selectArmorID > Weapon.weapons().length) {
            System.out.println("Lütfen Geçerli bir silah seçiniz!");
            selectArmorID = input.nextInt();
        }
        if (selectArmorID!=0){
            Armor selectedArmor = Armor.getArmorObjById(selectArmorID);

            if (selectedArmor != null) {
                //Satın alım işlemi.
                if (selectedArmor.getValue() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmamaktadır.");
                } else {
                    System.out.println(selectedArmor.getName() + " Zırh satın alıdnız.");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getValue();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız: " + this.getPlayer().getMoney());
                    System.out.println("Önceki Zırhınız: " + this.getPlayer().getInventory().getArmor().getName());
                    //System.out.println("Yeni silahınız: " + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    //System.out.println("Yeni silahınız: " + this.getPlayer().getInventory().getWeapon().getName());

                }
            }
        }
    }
}
