
import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);
    public void start(){
        System.out.println("Macera oyunna hoşgeldiniz.");
        System.out.print("Lütfen bir isim giriniz: ");
        String playerName= input.nextLine();
        Player player = new Player(playerName);//İsmi girdik
        System.out.println("Sayın " + player.getName()+ " Hoşgeldiniz.");
        System.out.println("Lütfen bir karakter seçiniz. ");
        player.selectChar();
        Location location = null;
        while (true){
            player.prinltInfo();
            System.out.println();
            System.out.println("------------- Bölgeler ----------");
            System.out.println();
            System.out.println("1-Güvenli Bölge --> Hasar alınmaz, yaratık yok.");
            System.out.println("2-Eşya Dükkanı --> Eşya satın alımı.");
            System.out.println("3-Mağara--> Ödül<Yemek>.Dikkatli ol vampir çıkabilir!");
            System.out.println("4-Orman --> Ödül<Odun>.Dikkatli ol zombi çıkabilir!");
            System.out.println("5-Nehir --> Ödül<Su>.Karşına ayı çıkabilir!");
            System.out.println("6-Maden --> farm yapabileceğiniz kısım.");
            System.out.println("0-Çıkış Yap --> Oyunu sonlandır.");
            System.out.println("Lütfen gitmek istediğiniz bölgeyi seçiniz.");
            int selectLoc= input.nextInt();
            switch (selectLoc){
                case 0:
                    location=null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location=new ToolStore(player);
                    break;
                case 3:
                    location=new Cave(player);
                    break;
                case 4:
                    location=new Forest(player);
                    break;
                case 5:
                    location=new River(player);
                    break;
                case 6:
                    location=new Mine(player);
                    break;
                default:
                    System.out.println("Geçerli bir bölge seçiniz.");
                    //location=new SafeHouse(player);
                    continue;
            }

            if (location==null){
                System.out.println("Oyun bitti yine bekleriz.");
                break;
            }

            if (!location.onLocation()) {
                System.out.println("Game Over!");
                break;
            }
        }

    }



}
