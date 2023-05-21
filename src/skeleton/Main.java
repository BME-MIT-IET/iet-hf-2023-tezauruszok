package skeleton;

import java.util.Scanner;

public class Main {
    private static Integer tabcount = 1;

    public static void printMethod(String s, boolean nov) {
        String tabStr = "";
        for (Integer i = 0; i < tabcount; i++) {
            tabStr += "\t";
        }
        //System.out.println(tabStr + s);
        if(nov)
            tabcount++;
        else
            tabcount--;
    }

    public static void printMethod(String s) {
        String tabStr = "";
        for (Integer i = 0; i < tabcount; i++) {
            tabStr += "\t";
        }
        //System.out.println(tabStr + s);
    }

    public static void main(String[] args) {
        String answer = null;

        Skeleton s = new Skeleton();

        do {
            System.out.println("Válassz egy tesztesetet:");
            System.out.println("1. player.Virologist steps on field");
            System.out.println("2. player.Virologist steps on storage");
            System.out.println("3. player.Virologist steps on laboratory");
            System.out.println("4. player.Virologist robs virologist");
            System.out.println("5. player.Virologist picks up gloves");
            System.out.println("6. player.Virologist picks up jacket");
            System.out.println("7. player.Virologist picks up sack");
            System.out.println("8. player.Virologist creates vaccine agent");
            System.out.println("9. player.Virologist creates freeze agent");
            System.out.println("10. player.Virologist creates forget agent");
            System.out.println("11. player.Virologist creates crazy agent");
            System.out.println("12. player.Virologist uses crazy agent");
            System.out.println("13. player.Virologist uses vaccine agent");
            System.out.println("14. player.Virologist uses forget agent");
            System.out.println("15. player.Virologist uses freeze agent");
            System.out.println("16. effect.Frozen virologist tries to move");
            System.out.println("17. Crazy virologist tries to move");
            System.out.println("Q - Kilépés");

            answer = new Scanner(System.in).nextLine();

            switch (answer) {
                case "1":
                    s.VirologistStepsOnField();
                    break;
                case "2":
                    s.ActionOnStorage();
                    break;
                case "3":
                    s.ActionOnLaboratory();
                    break;
                case "4":
                    s.VirologistRobsVirologist();
                    break;
                case "5":
                    s.VirologistPicksUpGloves();
                    break;
                case "6":
                    s.VirologistPicksUpJacket();
                    break;
                case "7":
                    s.VirologistPicksUpSack();
                    break;
                case "8":
                    s.VirologistCreatesVaccineAgent();
                    break;
                case "9":
                    s.VirologistCreatesFreezeAgent();
                    break;
                case "10":
                    s.VirologistCreatesForgetAgent();
                    break;
                case "11":
                    s.VirologistCreatesCrazyAgent();
                    break;
                case "12":
                    s.VirologistUsesCrazyAgent();
                    break;
                case "13":
                    s.VirologistUsesVaccineAgent();
                    break;
                case "14":
                    s.VirologistUsesForgetAgent();
                    break;
                case "15":
                    s.VirologistUsesFreezeAgent();
                    break;
                case "16":
                    s.FrozenVirologistTriesToMove();
                    break;
                case "17":
                    s.CrazyVirologistTriesToMove();
                    break;
                case "Q":
                    System.exit(0);
                default:
                    System.out.println("Nincs ilyen választási lehetõség.");
                    break;
            }
        }while(answer != "Q");

        return;
    }
}
