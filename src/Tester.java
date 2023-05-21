import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Statikus osztály teszteléshez
 */
public class Tester {



    /**
     * Függvény az opciók kilistázására
     */
    public static void listOptions(){
        System.out.print("Please choose from the following options (with a number 1--X):\n" +
                "[1] Run all test\n"+
                "[2] Run a specified test (1-44)\n");
    }

    /**
     * Függvény az opciók bekéréséhez, és validálásához
     * @param l_limit fõ opció alsó limit
     * @param u_limit fõ opció felsõ limit
     * @param l_limit2 mellék opció alsó limit
     * @param u_limit2 mellék opció felsõ limit
     * @return
     */
    public static int[] getChoice(int l_limit, int u_limit, int l_limit2, int u_limit2) {
        Scanner scan = new Scanner(System.in);
        int[] user_input = {};
        while(true) {
            String user_cmd = scan.nextLine();
            String[] input = user_cmd.split(" ");
            if (input.length == 1) {
                try {
                    user_input = new int[]{Integer.parseInt(input[0])};
                    if (user_input[0] < l_limit || user_input[0] > u_limit) {
                    }
                    else{
                        break;
                    }
                }
                catch(Exception ex){
                }
            }
            if (input.length == 2) {
                try {
                    user_input = new int[]{Integer.parseInt(input[0]), Integer.parseInt(input[1])};
                    if (user_input[0] < l_limit || user_input[0] > u_limit || user_input[1] <l_limit2 || user_input[1]>u_limit2) {
                    }
                    else{
                        break;
                    }
                }
                catch(Exception ex){
                }
            }
        }
        return user_input;
    }

    /**
     * Függvény a tesztek bemeneti parancsainak betöltésére
     * @param file fájl neve
     * @return a parancsok
     */
    public static ArrayList<String[]> readCommands(String file){
        ArrayList<String[]> cmd_s = new ArrayList<String[]>();
        try {
            String currentdir = System.getProperty("user.dir");
            File in = new File(currentdir+"/test/input_txt/"+file+".txt");
            Scanner scan = new Scanner(in);
            while (scan.hasNextLine()) {
                String[] data = scan.nextLine().strip().split(" ");
                cmd_s.add(data);

                //System.out.println(data);
            }
            scan.close();
        }
        catch (Exception e) {
            return null;
        }
        return cmd_s;
    }


    /**
     * Függvény a tesztek elvárt kimenetének betöltésére
     * @param file a fájl neve
     * @return az elvárt kimenet
     */
    public static String readOutput(String file){
        String cmd_s = "";
        try {
            String currentdir = System.getProperty("user.dir");
            File in = new File(currentdir+"/test/output_txt/"+file+".txt");
            Scanner scan = new Scanner(in);
            while (scan.hasNextLine()) {
                String data = scan.nextLine() +"\n";
                cmd_s = cmd_s.concat(data);
                //System.out.println(data);
            }
            scan.close();
        }
        catch (Exception e) {
            return null;
        }
        return cmd_s;
    }

    /**
     * Függvény két String lista összehasonlításához
     * @param first egyik lista
     * @param second másik lista
     * @return összehasonlítás eredménye
     */
    public static boolean checkOutPut(String first, String second){
        try{
            return first.equals(second);
        }
        catch(Exception ex){
            return false;
        }
    }

    /**
     * Függvény az összes teszt egymás utáni lefuttatásához
     */
    static void runAllTests(){
        //AttackAnotherVirologist();
        //PickUpSack();
        // és így tovább az összes függvény meghívása...
        StepOnLaboratory();
        StepOnLaboratoryWithBearGene();
        StepOnShelter();
        StepOnWarehouse();
        StepOnWarehouseAsBear();
        LearOblivionGene();
        LearnParalyzeGene();
        LearnInvulnerabilityGene();
        LearnChoreaGene();
        CraftOblivionAgent();
        CraftParalyzeAgent();
        CraftInvulnerabilityAgent();
        CraftChoreaAgent();
        PickUpGloves();
        PickUpSack();
        PickUpCloak();
        PickUpAxe();
        PickUpNucleotid();
        PickUpAminoAcid();
        StealParalyzeAgent();
        StealInvulnerabilityAgent();
        StealOblivionAgent();
        StealChoreaAgent();
        StealGloves();
        StealSack();
        StealCloak();
        StealAxe();
        StealNucleotid();
        StealAminoAcid();
        SpreadOblivionAgent();
        SpreadOblivionAgentWithCloakActive();
        SpreadInvulnerabilityAgent();
        SpreadChoreaAgent();
        SpreadChoreaAgentWithCloakActive();
        SpreadParalyzeAgent();
        SpreadParalyzeAgentWithCloakActive();
        SpreadBearAgent();
        SpreadBearAgentWithCloakActive();
        SpreadBackOblivionAgent();
        SpreadBackInvulnerabilityAgent();
        SpreadBackChoreaAgent();
        SpreadBackParalyzeAgent();
        AttackAnotherVirologist();
    }


    /**
     * Függvény a StepOnLaboratory tesztesethez mintának kb.
     */
    static void StepOnLaboratory(){
        //neve száma, csak ezeket kell átírni elviekben másolgatás esetén
        String name = "StepOnLaboratory";
        String number = "1";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }
            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }

    static void StepOnLaboratoryWithBearGene(){
        String name = "StepOnLaboratoryWithBearGene";
        String number = "2";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }

    static void StepOnShelter(){
        String name = "StepOnShelter";
        String number = "3";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }
            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }

    static void StepOnWarehouse(){
        String name = "StepOnWarehouse";
        String number = "4";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }

    static void StepOnWarehouseAsBear(){
        String name = "StepOnWarehouseAsBear";
        String number = "5";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }

    static void LearOblivionGene(){
        String name = "LearnOblivionGene";
        String number = "6";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }

    static void LearnParalyzeGene(){
        String name = "LearnParalyzeGene";
        String number = "7";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }

    static void LearnInvulnerabilityGene(){
        String name = "LearnInvulnerabilityGene";
        String number = "8";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void LearnChoreaGene(){
        String name = "LearnChoreaGene";
        String number = "9";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }
            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }

    static void CraftOblivionAgent(){
        String name = "CraftOblivionAgent";
        String number = "10";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void CraftParalyzeAgent(){
        String name = "CraftParalyzeAgent";
        String number = "11";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void CraftInvulnerabilityAgent(){
        String name = "CraftInvulnerabilityAgent";
        String number = "12";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }

    static void CraftChoreaAgent(){
        String name = "CraftChoreaAgent";
        String number = "13";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }

    static void PickUpGloves(){
        String name = "PickUpGloves";
        String number = "14";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }


            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }

    static void PickUpSack(){
        String name = "PickUpSack";
        String number = "15";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void PickUpCloak(){
        String name = "PickUpCloak";
        String number = "16";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void PickUpAxe(){
        String name = "PickUpAxe";
        String number = "17";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }

    static void PickUpNucleotid(){
        String name = "PickUpNucleotid";
        String number = "18";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void PickUpAminoAcid(){
        String name = "PickUpAminoAcid";
        String number = "19";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void StealParalyzeAgent(){
        String name = "StealParalyzeAgent";
        String number = "20";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }
            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void StealInvulnerabilityAgent(){
        String name = "StealInvulnerabilityAgent";
        String number = "21";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void StealOblivionAgent(){
        String name = "StealOblivionAgent";
        String number = "22";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void StealChoreaAgent(){
        String name = "StealChoreaAgent";
        String number = "23";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }

    static void StealGloves(){
        String name = "StealGloves";
        String number = "24";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void StealSack(){
        String name = "StealSack";
        String number = "25";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void StealCloak(){
        String name = "StealCloak";
        String number = "26";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void StealAxe(){
        String name = "StealAxe";
        String number = "27";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void StealNucleotid(){
        String name = "StealNucleotid";
        String number = "28";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void StealAminoAcid(){
        String name = "StealAminoAcid";
        String number = "29";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void SpreadOblivionAgent(){
        String name = "SpreadOblivionAgent";
        String number = "30";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void SpreadOblivionAgentWithCloakActive(){
        String name = "SpreadOblivionAgentWithCloakActive";
        String number = "31";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void SpreadInvulnerabilityAgent(){
        String name = "SpreadInvulnerAbilityAgent";
        String number = "32";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void SpreadChoreaAgent(){
        String name = "SpreadChoreaAgent";
        String number = "33";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void SpreadChoreaAgentWithCloakActive(){
        String name = "SpreadChoreaAgentWithCloakActive";
        String number = "34";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void SpreadParalyzeAgent(){
        String name = "SpreadParalyzeAgent";
        String number = "35";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void SpreadParalyzeAgentWithCloakActive(){
        String name = "SpreadParalyzeAgentWithCloakActive";
        String number = "36";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void SpreadBearAgent(){
        String name = "SpreadBearAgent";
        String number = "37";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void SpreadBearAgentWithCloakActive(){
        String name = "SpreadBearAgentWithCloakActive";
        String number = "38";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void SpreadBackOblivionAgent(){
        String name = "SpreadBackOblivionAgent";
        String number = "39";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void SpreadBackInvulnerabilityAgent(){
        String name = "SpreadBackInvulnerabilityAgent";
        String number = "40";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }
            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void SpreadBackChoreaAgent(){
        String name = "SpreadBackChoreaAgent";
        String number = "41";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    static void SpreadBackParalyzeAgent(){
        String name = "SpreadBackParalyzeAgent";
        String number = "42";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }

    static void AttackAnotherVirologist(){
        String name = "AttackAnotherVirologist";
        String number = "43";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }
            //craftingTable
            //System.out.print(output);
            //System.out.print(readOutput(name));
            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }
    /**
     * Mórickapélda a tesztelésre, egy egyszerû parancsot hív meg de ugyanolyan módon mint a StepOnLab minta
     * hogy lássuk mûködés közben a tesztprogramot annak ellenére, hogy nincs még load meg save stb.
     * futtatása "2 0"
     */
    static void TesztTeszt(){
        //neve száma, csak ezeket kell átírni elviekben másolgatás esetén
        String name = "TesztTeszt";
        String number = "0";
        try {
            ArrayList<String[]> commands = readCommands(name);
            String output = "";
            for (int i = 0; i < commands.size(); i++) {
                Prototype.main(commands.get(i));
                output = output.concat(Prototype.results);
            }

            //JUnittal is lehetne, de most nem azzal van az ellenõrzés, maradjon egyelõre így
            if (checkOutPut(output, readOutput(name))) {
                System.out.println(number + ". " + name + " passed");
            } else {
                System.out.println(number + ". " + name + " failed");
            }
        }
        catch(Exception ex){
            System.out.println(number + ". " + name + " failed");
        }
    }


    public static void main(String args[]){
        //Prototípus felkészítése a tesztelésre
        Prototype.testMode = true;
        String[][] commands = {};

        while(true){
            listOptions();
            int[] input = getChoice(1,2,0,44);
            switch(input[0])     {
                case 1:
                    runAllTests();
                    break;
                case 2:
                    switch(input[1]){
                        case 0:
                            TesztTeszt();
                            break;
                        case 1:
                            StepOnLaboratory();
                            break;
                        case 2:
                            StepOnLaboratoryWithBearGene();
                            break;
                        case 3:
                            StepOnShelter();
                            break;
                        case 4:
                            StepOnWarehouse();
                            break;
                        case 5:
                            StepOnWarehouseAsBear();
                            break;
                        case 6:
                            LearOblivionGene();
                            break;
                        case 7:
                            LearnParalyzeGene();
                            break;
                        case 8:
                            LearnInvulnerabilityGene();
                            break;
                        case 9:
                            LearnChoreaGene();
                            break;
                        case 10:
                            CraftOblivionAgent();
                            break;
                        case 11:
                            CraftParalyzeAgent();
                            break;
                        case 12:
                            CraftInvulnerabilityAgent();
                            break;
                        case 13:
                            CraftChoreaAgent();
                            break;
                        case 14:
                            PickUpGloves();
                            break;
                        case 15:
                            PickUpSack();
                            break;
                        case 16:
                            PickUpCloak();
                            break;
                        case 17:
                            PickUpAxe();
                            break;
                        case 18:
                            PickUpNucleotid();
                            break;
                        case 19:
                            PickUpAminoAcid();
                            break;
                        case 20:
                            StealParalyzeAgent();
                            break;
                        case 21:
                            StealInvulnerabilityAgent();
                            break;
                        case 22:
                            StealOblivionAgent();
                            break;
                        case 23:
                            StealChoreaAgent();
                            break;
                        case 24:
                            StealGloves();
                            break;
                        case 25:
                            StealSack();
                            break;
                        case 26:
                            StealCloak();
                            break;
                        case 27:
                            StealAxe();
                            break;
                        case 28:
                            StealNucleotid();
                            break;
                        case 29:
                            StealAminoAcid();
                            break;
                        case 30:
                            SpreadOblivionAgent();
                            break;
                        case 31:
                            SpreadOblivionAgentWithCloakActive();
                            break;
                        case 32:
                            SpreadInvulnerabilityAgent();
                            break;
                        case 33:
                            SpreadChoreaAgent();
                            break;
                        case 34:
                            SpreadChoreaAgentWithCloakActive();
                            break;
                        case 35:
                            SpreadParalyzeAgent();
                            break;
                        case 36:
                            SpreadParalyzeAgentWithCloakActive();
                            break;
                        case 37:
                            SpreadBearAgent();
                            break;
                        case 38:
                            SpreadBearAgentWithCloakActive();
                            break;
                        case 39:
                            SpreadBackOblivionAgent();
                            break;
                        case 40:
                            SpreadBackInvulnerabilityAgent();
                            break;
                        case 41:
                            SpreadBackChoreaAgent();
                            break;
                        case 42:
                            SpreadBackParalyzeAgent();
                            break;
                        case 43:
                            AttackAnotherVirologist();
                            break;
                        //.... és így tovább
                    }
            }
        }
    }
}
