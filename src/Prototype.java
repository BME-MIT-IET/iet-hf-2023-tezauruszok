import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
/**
 * A prototipus mukodeset megvalosito osztalyt
 */
public class Prototype {
    /**
     * Jatek vezerlese
     */
    public static Game g;
    /**
     *Lista a konzolos parancsokhoz
     */
    private static ArrayList<String[]> commands = new ArrayList<String[]>();
    /**
     * Megnyitott palya neve
     */
    public static String openedMap;
    /**
     *Grafika vezerlese
     */
    private static Graphics graphics;
    /**
     * Foglalt ikonok tárolása
     */
    private static ArrayList<Integer> reservedIcons = new ArrayList<>();

    //Csak a tesztprogramhoz
    //***********************
    public static boolean testMode = false;
    public static String results ="";
    //***********************

    /**
     * Main
     */

    public static void main(String[] args) throws IOException {

        graphics = new Graphics();
        graphics.initMainMenu();
        graphics.initGameMenu();
        graphics.showMainMenu();
    }



    /**
     * A jatekban az elerheto opciokat kilistazo fuggveny
     */
    static void listOptions(){
        /**
         * NINCS KESZ, csak definicio LEHET NEM IS FOG KELLENI!!!
         */
    }

    /**
     * Eldonti egy szamrol, hogy a kivant intervallumban van e, es megfelelo e
     * @param min
     * @param max
     * @return a dontes eredmenye
     */
    static boolean checkNumber(int number, int min, int max) {
        return number<=max && number>=min;
    }

    /**
     * Eldonti egy fajlnevrol, hogy létezik e
     * @param filename
     * @return a dontes eredmenye
     */
    static boolean checkFileName(String filename){
        Path path = Paths.get(filename);
        return Files.exists(path);
    }

    /**
     * A jatekban a startGame <szam> parancs feldolgozasakor hivott fuggveny
     * @param playersNum
     * @return sikerult e a parancs, vagy nem
     */
    static boolean startGame(int playersNum){
        /**
         * HA checkNumber(playersNum) HAMIS:
         *       VISSZATERES hamis értékkel
         * EGYÉBKÉNT:
         *      PRÓBÁLD:
         *          ÚJ JÁTÉK LÉTREHOZÁSA a parameterben kapott jatekosmennyiseggel
         *          VISSZATÉRÉS igaz értékkel
         *      EGYÉBKÉNT:
         *          VISSZATERES hamis értékkel
         */

        if(!checkNumber(playersNum, 1, 6)){
            return false;
        }
        else{
            try{
                Random r = new Random();
                int randomszam = r.nextInt(3) + 1;
                //int randomszam = 1;
                String name = "palya" + randomszam +".xml";
                FileHandler.loadFile(System.getProperty("user.dir")+"/test/"+name);
                g = new Game(FileHandler.remaining_virologists, FileHandler.virologists, new City(FileHandler.fields));
                //g = new Game();
                g.new_Game(playersNum);
                for(Field f: g.getCity().getFields())
                graphics.addPolygon(f.p);
                return true;
            }
            catch (Exception e){
                return false;
            }
        }
    }

    /**
     * A játékban az exit parancs feldolgozasokor hivott fuggveny
     */
    static void exit(){
        g.game_over();
        System.exit(0);
    }

    /**
     * A jatekban a load parancs feldolgozasakor hivott fuggveny
     * @param name a betolteni kivant fajl neve
     * @return sikerult e
     */
    static boolean load(String name){
        /**
         * HA checkFileName(name) HAMIS:
         *      VISSZATERES hamis értékkel
         * EGYÉBKÉNT:
         * PRÓBÁLD:
         *      FileHandler meghívasa name parameterrel
         *          openedMap = name
         *          ÚJ JÁTÉK LETREHOZÁSA
         *          CIKLUS a betoltott virologusokon:
         *              virologus jatekhoz hozzaadasa
         *          CIKLUS a betoltott Fieldeken:
         *              Field jatekhoz hozzaadasa
         *          CIKLUS a betoltott Objektumokon:
         *              Objektum jatekhoz, es mezokhoz hozzadasa
         *          CIKLUS a betoltott Parancsokon:
         *              adott parancs vegrehajtasa
         * HA nem sikerül:
         *          visszateres hamis ertekkel
         *
         */
        try{
            if(checkFileName(System.getProperty("user.dir")+"/test/"+name)){
                FileHandler.loadFile(System.getProperty("user.dir")+"/test/"+name);
                g = new Game(FileHandler.remaining_virologists, FileHandler.virologists, new City(FileHandler.fields));
                for(Field f: g.getCity().getFields())
                    graphics.addPolygon(f.p);
                return true;
            }
            else{
                return false;
            }

        }
        catch(Exception e){
            return false;
        }
    }

    /**
     * A játékban a save parancs feldolgozásakor hívott függvény
     * @param name a kimeneti fajl neve
     * @param cmd írja e ki a parancssorra
     * @return  sikerult e
     */
    static boolean save(String name, String openedMap, boolean cmd){
        /**
         * PRÓBÁLD:
         *      Filehandler.saveFile(name, openedMap, cmd) meghívása
         */
        FileHandler.saveFile(System.getProperty("user.dir")+"/test/"+name, openedMap, cmd, g);
        return true;
    }

    /**
     * A játékban a listNeighbours parancs feldolgozásakor hívott függvény
     */
    static void listNeighbours(){
        try{
            int id = 1;
            if(testMode){
                for(Field f: g.getActualPlayer().getField().getNeighbours()){
                    results = results.concat("Neighbour: " + f.toString() + " id: " + id++ +"\n");
                    }
                return;
            }
            else{
                for(Field f: g.getActualPlayer().getField().getNeighbours())
                    System.out.println("Neighbour: " + f.toString() + " id: " + id++);
            }
        }
        catch(Exception ex){
        }
    }

    /**
     * A játékban a step parancs feldolgozasakor hívott függvény
     * @param neighbourNum
     */
    static boolean step(int neighbourNum){
        /**
         * PROBALD:
         *      Aktualis jatekos leptetese a neighbourNUm mezore
         *      VISSZATÉRÉS igaz értékkel
         * EGYÉBKÉNT
         *      VISSZATÉRÉS hamis értékkel
         */


        try{
            if(!checkNumber(neighbourNum, 0, g.getActualPlayer().getField().neighbours.size())){
                return false;
            }
            g.getActualPlayer().move(neighbourNum);
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }

    /**
     * A játékban a learnGene parancs feldolgozásakor hívott függvény
     * @param geneName a játékos által megadott gén név
     * @return sikerult e
     */
    static boolean learnGene(String geneName){
        /**
         * PROBALD:
         *      HA geneName = Chorea_gene:
         *          Hozd létre a Chorea gént
         *          aktuális virológus learn_Gene függvényének meghívása
         *      HA geneName = Oblivion_gene:
         *          Hozd létre a Feledés gént
         *          aktuális virológus learn_Gene függvényének meghívása
         *      HA geneName = Invulnerability_gene:
         *          Hozd létre a érinthetlenség gént
         *          aktuális virológus learn_Gene függvényének meghívása
         *      HA geneName = Paralyze_gene:
         *          Hozd létre a benulas gént
         *          aktuális virológus learn_Gene függvényének meghívása
         *      HA egyik gén név se:
         *          Visszatérés hamis értékkel
         *      EGYÉBKÉNT:
         *          VISSZATÉRÉS igaz értékkel
         *  EGYÉBKÉNT:
         *      VISSZATÉRÉS hamis értékkel
         */

        try{
            if(geneName.equals("Chorea_gene")){
                Chorea temp = new Chorea();
                g.getActualPlayer().learn_Gene(temp);
                return true;
            }
            else if(geneName.equals("Oblivion_gene")){
                Oblivion_gene temp = new Oblivion_gene();
                g.getActualPlayer().learn_Gene(temp);
                return true;
            }
            else if(geneName.equals("Invulnerability_gene")){
                Invulnerability_gene temp = new Invulnerability_gene();
                g.getActualPlayer().learn_Gene(temp);
                return true;
            }
            else if(geneName.equals("Paralyze_gene")){
                Paralyze_gene temp = new Paralyze_gene();
                g.getActualPlayer().learn_Gene(temp);
                return true;
            }
            else{
                return false;
            }

        }
        catch(Exception ex){
            return false;
        }

    }

    /**
     * A játékban a craftAgent parancs feldolgozásakor hívott függvény
     * @param agent az elkészíteni kívánt ágens neve
     * @return sikerült e
     */
    public static boolean craftAgent(String agent){
        /**
         * PROBALD:
         *      HA agent = Chorea_agent:
         *          aktuális virológus craftAgent(újÁgensId) függvényének meghívása
         *      HA agent = Oblivion_agent:
         *          aktuális virológus craftAgent(újÁgensId) függvényének meghívása
         *      HA agent = Invulnerability_agent:
         *          aktuális virológus craftAgent(újÁgensId) függvényének meghívása
         *      HA agent = Paralyze_agent:
         *          aktuális virológus craftAgent(újÁgensId) függvényének meghívása
         *      HA egyik ágens név se a fentiek közül:
         *          Visszatérés hamis értékkel
         *      EGYÉBKÉNT:
         *          VISSZATÉRÉS igaz értékkel
         *  EGYÉBKÉNT:
         *      VISSZATÉRÉS hamis értékkel
         */

        try{
            if(agent.equals("Chorea_agent")){
                g.getActualPlayer().craft_Agent(new Chorea_agent().getId());
                return true;
            }
            else if(agent.equals("Oblivion_agent")){
                g.getActualPlayer().craft_Agent(new Oblivion_agent().getId());
                return true;
            }
            else if(agent.equals("Invulnerability_agent")){
                g.getActualPlayer().craft_Agent(new Invulnerability_agent().getId());
                return true;
            }
            else if(agent.equals("Paralyze_agent")){
                g.getActualPlayer().craft_Agent(new Paralyze_agent().getId());
                return true;
            }
            else{
                return false;
            }
        }

        catch(Exception ex){
            return false;
        }
    }

    /**
     * A játékban a listThingsOnField parancs feldolgozásakor hívott függvény NA EZ NEMJÓ
     */
    static void listThingsOnField(boolean listVirologists, boolean listItems){
        try{
            String out = g.getActualPlayer().getField().listThings(0,listVirologists, listItems);
            if(testMode){
                results = out;
                return;
            }
            else{
                System.out.print(out);
            }
        }
        catch(Exception ex){
        }

    }

    /**
     * A játékban a pickUp parancs feldolgozásakor hívott függvény
     */
    static boolean pickUp(String thing){
        /**
         * PRÓBÁLD:
         *      HA thing = Axe:
         *          eq = jelenlegi virologus mezojenek getEq(1) hívása
         *          jelenlegi virológus pickupEq(eq) hívása
         *      HA thing = Cloak:
         *          eq = jelenlegi virologus mezojenek getEq(2) hívása
         *          jelenlegi virológus pickupEq(eq) hívása
         *      HA thing = Gloves:
         *          eq = jelenlegi virologus mezojenek getEq(3) hívása
         *          jelenlegi virológus pickupEq(eq) hívása
         *      HA thing = Sack:
         *          eq = jelenlegi virologus mezojenek getEq(4) hívása
         *          jelenlegi virológus pickupEq(eq) hívása
         *      HA thing = Nucleotid:
         *          re = jelenlegi virologus mezojenek getRe(2) hívása
         *          jelenlegi virológus pickupRe(re) hívása
         *      HA thing = Amino_acid:
         *          re = jelenlegi virologus mezojenek getRe(1) hívása
         *          jelenlegi virológus pickupRe(re) hívása
         *      HA egyik sem a fentiek közül:
         *          VISSZATÉRÉS hamis értékkel
         *      EGYÉBKÉNT:
         *          VISSZATÉRÉS igaz értékkel
         * EGYÉBKÉNT:
         *      VISSZATÉRÉS hamis értékkel
         *
         */
        try{
            if(thing.equals("Axe")){
                Equipment eq = ((Shelter)(g.getActualPlayer().getField())).getEq(1);
                g.getActualPlayer().pickupEq(eq);
                return true;
            }
            else if(thing.equals("Cloak")){
                Equipment eq = ((Shelter)(g.getActualPlayer().getField())).getEq(2);
                g.getActualPlayer().pickupEq(eq);
                return true;
            }
            else if(thing.equals("Gloves")){
                Equipment eq = ((Shelter)(g.getActualPlayer().getField())).getEq(3);
                g.getActualPlayer().pickupEq(eq);
                return true;
            }
            else if(thing.equals("Sack")){
                Equipment eq = ((Shelter)(g.getActualPlayer().getField())).getEq(4);
                g.getActualPlayer().pickupEq(eq);
                return true;
            }
            else if(thing.equals("Nucleotid")){
                Resource res = ((Warehouse)(g.getActualPlayer().getField())).getRes(2);
                g.getActualPlayer().pickupRe(res);
                return true;
            }
            else if(thing.equals("Amino_acid")){
                Resource res = ((Warehouse)(g.getActualPlayer().getField())).getRes(1);
                g.getActualPlayer().pickupRe(res);
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception ex){
            return false;
        }
    }

    /**
     * A játékban az attack parancs feldolgozásakor hívott függvény
     * @param target a megtámandó Virológus azonosítója
     * @return sikerült e
     */
    static boolean attack(String target) {
        /**
         * PRÓBÁLD:
         *      CIKLUS a jelenlegi virológus mezõjének virológusain:
         *          HA adott virológus neve = target:
         *              jelenlegi virológus attack(adott virológus) hívása
         *              sikeres = igaz
         *
         *      HA sikeres:
         *          VISSZATÉRÉS igaz értékkel
         *      EGYÉBKÉNT:
         *          VISSZATÉRÉS hamis értékkel
         * EGYÉBKÉNT:
         *      VISSZATÉRÉS hamis értékkel
         *
         */
        try{
            for (int i = 0; i <g.getActualPlayer().getField().getVirologists().size(); i++) {
                if(g.getActualPlayer().getField().getVirologists().get(i).getName().equals(target)){
                    g.getActualPlayer().attack(g.getActualPlayer().getField().getVirologists().get(i));
                    return true;
                }
            }
            return false;
        }
        catch(Exception ex){
            return false;
        }
    }

    /**
     * A játékban a spread parancs feldolgozásakor hívott függvény
     * @param target a megtámandó Virológus azonosítója
     * @param agent  a felkenni szándékozott Ágens neve
     * @return sikerült e
     */
    static boolean spread(String target, String agent) {
        /**
         * PRÓBÁLD:
         *      HA agent = Chorea_agent:
         *          temp agens = ÚJ Chorea_agent
         *      HA agent = Oblivion_agent:
         *          temp agens = ÚJ Oblivion_agent
         *      HA agent = Invulnerability_agent:
         *          temp agens = ÚJ Invulnerability_agent
         *      HA agent = Paralyze_agent:
         *          temp agens = ÚJ Paralyze_agent
         *      HA egyik ágens név se a fentiek közül:
         *          Visszatérés hamis értékkel
         *
         *      CIKLUS a jelenlegi virológus mezõjének virológusain:
         *          HA adott virológus neve = target:
         *              jelenlegi virológus spreading(temp agens, adott virológus) hívása
         *              sikeres = igaz
         *      HA sikeres:
         *          VISSZATÉRÉS igaz értékkel
         *      EGYÉBKÉNT:
         *          VISSZATÉRÉS hamis értékkel
         * EGYÉBKÉNT:
         *      VISSZATÉRÉS hamis értékkel
         *
         */

        try {
            int temp = 0;
            boolean isAccepted = false;
            if(agent.equals("Chorea_agent")){
                temp = 4;
                isAccepted = true;
            }
            else if(agent.equals("Oblivion_agent")){
                temp= 2;
                isAccepted = true;
            }
            else if(agent.equals(("Invulnerability_agent"))){
                temp = 3;
                isAccepted = true;
            }
            else if(agent.equals(("Paralyze_agent"))) {
                temp = 1;
                isAccepted = true;
            }
            if(!isAccepted){
                return false;
            }
            for (int i = 0; i <g.getActualPlayer().getField().getVirologists().size(); i++) {
                if(g.getActualPlayer().getField().getVirologists().get(i).getName().equals(target)){
                    Agent tmp = g.getActualPlayer().getAgents().remove(temp);
                    if(!tmp.getIs_used()) {
                        //g.getActualPlayer().spreading(temp, g.getActualPlayer().getField().getVirologists().get(i));
                        g.getActualPlayer().spreading(tmp, g.getActualPlayer().getField().getVirologists().get(i));
                        g.getActualPlayer().getAgents().add(tmp);
                        return true;
                    }
                    g.getActualPlayer().getAgents().add(tmp);
                }
            }
            return false;
        } catch (Exception ex) {
            return false;
        }
    }
    /**
     * A játékban a spreadingBack parancs feldolgozásakor hívott függvény
     * @param target a visszamegtámandó Virológus azonosítója
     * @param agent  a visszakenni szándékozott Ágens neve
     * @return sikerült e
     */
        static boolean spreadingBack(String target, String agent){
            /**
             * PRÓBÁLD:
             *      HA agent = Chorea_agent:
             *          temp agens = ÚJ Chorea_agent
             *      HA agent = Oblivion_agent:
             *          temp agens = ÚJ Oblivion_agent
             *      HA agent = Invulnerability_agent:
             *          temp agens = ÚJ Invulnerability_agent
             *      HA agent = Paralyze_agent:
             *          temp agens = ÚJ Paralyze_agent
             *      HA egyik ágens név se a fentiek közül:
             *          Visszatérés hamis értékkel
             *
             *      CIKLUS a jelenlegi virológus mezõjének virológusain:
             *          HA adott virológus neve = target:
             *              jelenlegi virológus spreadingBack(temp agens, adott virológus) hívása
             *              sikeres = igaz
             *      HA sikeres:
             *          VISSZATÉRÉS igaz értékkel
             *      EGYÉBKÉNT:
             *          VISSZATÉRÉS hamis értékkel
             * EGYÉBKÉNT:
             *      VISSZATÉRÉS hamis értékkel
             *
             */
            try {
                int temp = 0;
                boolean isAccepted = false;
                if(agent.equals("Chorea_agent")){
                    temp = 4;
                    isAccepted = true;
                }
                else if(agent.equals("Oblivion_agent")){
                    temp= 2;
                    isAccepted = true;
                }
                else if(agent.equals(("Invulnerability_agent"))){
                    temp = 3;
                    isAccepted = true;
                }
                else if(agent.equals(("Paralyze_agent"))) {
                    temp = 1;
                    isAccepted = true;
                }
                if(!isAccepted){
                    return false;
                }
                for (int i = 0; i <g.getActualPlayer().getField().getVirologists().size(); i++) {
                    if(g.getActualPlayer().getField().getVirologists().get(i).getName().equals(target)){
                        Agent tmp = g.getActualPlayer().getAgents().remove(temp);
                        if(!tmp.getIs_used()) {
                            //g.getActualPlayer().spreading(temp, g.getActualPlayer().getField().getVirologists().get(i));
                            g.getActualPlayer().spreadingBack(tmp, g.getActualPlayer().getField().getVirologists().get(i));
                            g.getActualPlayer().getAgents().add(tmp);
                            return true;
                        }
                        g.getActualPlayer().getAgents().add(tmp);
                    }
                }
                return false;
            } catch (Exception ex) {
                return false;
            }
        }

    /**
     * A játékban a steal parancs feldolgozásakor hívott függvény
     * @param thing ellopni kívánt dolog neve
     * @param target kifosztandó virológus
     * @return sikeres e
     */
        static boolean steal(String thing, String target){

            /**
             * PRÓBÁLD:
             *      HA thing = Chorea_agent:
             *          temp thing = ÚJ Chorea_agent
             *          kategoria = Agent
             *      HA thing = Oblivion_agent:
             *          temp thing = ÚJ Oblivion_agent
             *          kategoria = Agent
             *      HA thing = Invulnerability_agent:
             *          temp thing = ÚJ Invulnerability_agent
             *          kategoria = Agent
             *      HA thing = Paralyze_agent:
             *          temp thing = ÚJ Paralyze_agent
             *          kategoria = Agent
             *      HA thing = Axe:
             *          temp thing = ÚJ Axe
             *          kategoria = Equipment
             *      HA thing = Cloak:
             *          temp thing = ÚJ Cloak
             *          kategoria = Equipment
             *      HA thing = Gloves:
             *          temp thing = ÚJ Gloves
             *          kategoria = Equipment
             *      HA thing = Sack:
             *          temp thing = ÚJ Sack
             *          kategoria = Equipment
             *      HA thing = Amino_acid:
             *          temp thing = ÚJ Amino_acid
             *          kategoria = Resource
             *      HA thing = Nucleotid:
             *          temp thing = ÚJ Nucleotid
             *          kategoria = Resource
             *      HA egyik dolog  név se a fentiek közül:
             *          Visszatérés hamis értékkel
             *
             *      CIKLUS a jelenlegi virológus mezõjének virológusain:
             *          HA adott virológus neve = target:
             *              HA kategoria = Agent:
             *                  Adott virologus dropAg(jelenlegi virologus, thing)
             *                  sikeres = igaz
             *              HA kategoria = Equipment:
             *                  Adott virologus dropEq(jelenlegi virologus, thing)
             *                  sikeres = igaz
             *              HA kategoria = Resource:
             *                  Adott virologus dropRe(jelenlegi virologus, thing)
             *                  sikeres = igaz
             *      HA sikeres:
             *          VISSZATÉRÉS igaz értékkel
             *      EGYÉBKÉNT:
             *          VISSZATÉRÉS hamis értékkel
             * EGYÉBKÉNT:
             *      VISSZATÉRÉS hamis értékkel
             *
             */

            try{
                //Object temp = null;
                int temp = 0;
                boolean isAccepted = false;
                String category = null;

                if(thing.equals("Chorea_agent")){
                    //temp = new Chorea_agent();
                    temp = 4;
                    isAccepted = true;
                    category  = "Agent";
                }
                else if(thing.equals("Oblivion_agent")){
                    //temp = new Oblivion_agent();
                    temp = 2;
                    isAccepted = true;
                    category  = "Agent";
                }
                else if(thing.equals("Invulnerability_agent")){
                    //temp = new Invulnerability_agent();
                    temp = 3;
                    isAccepted = true;
                    category  = "Agent";
                }
                else if(thing.equals("Paralyzed_agent")){
                    //temp = new Paralyze_agent();
                    temp = 1;
                    isAccepted = true;
                    category  = "Agent";
                }
                else if(thing.equals("Axe")){
                    temp = 1;
                    isAccepted = true;
                    category  = "Equipment";
                }
                else if(thing.equals("Cloak")){
                    temp = 2;
                    isAccepted = true;
                    category  = "Equipment";
                }
                else if(thing.equals("Gloves")){
                    temp = 3;
                    isAccepted = true;
                    category  = "Equipment";
                }
                else if(thing.equals("Sack")){
                    temp = 4;
                    isAccepted = true;
                    category  = "Equipment";
                }
                else if(thing.equals("Amino_acid")){
                    temp = 1;
                    isAccepted = true;
                    category  = "Resource";
                }
                else if(thing.equals("Nucleotid")){
                    temp = 2;
                    isAccepted = true;
                    category  = "Resource";
                }
                if(!isAccepted){
                    return false;
                }
                for (int i = 0; i <g.getActualPlayer().getField().getVirologists().size(); i++) {
                    if(g.getActualPlayer().getField().getVirologists().get(i).getName().equals(target)){
                        if(category.equals("Agent")){
                            //g.getActualPlayer().getField().getVirologists().get(i).dropAg((Agent)temp, g.getActualPlayer());
                            g.getActualPlayer().getField().getVirologists().get(i).dropAg(temp, g.getActualPlayer());
                            return true;
                        }
                        else if(category.equals("Equipment")){
                            g.getActualPlayer().getField().getVirologists().get(i).dropEq(temp, g.getActualPlayer());
                            return true;
                        }
                        else{
                            g.getActualPlayer().getField().getVirologists().get(i).dropRe(temp, g.getActualPlayer());
                            return true;
                        }
                    }
                }
                return false;
            }
            catch(Exception ex){
                return false;
            }
        }

    /**
     * A játékban a stat parancs feldolgozásakor hívott függvény
      */
    static void stat(){
        try{
            String out = g.getActualPlayer().listThings();
            if(testMode) {
                results = out;
                return;
            }
            else{
                System.out.printf(out);
            }
        }
        catch(Exception ex){
        }
    }

    /**
     * A játékban a nextPlayer parancs feldolgozásakor hívott függvény
     */
    static void nextPlayer(){
        /**
         * Visszaállítja az aktuális virológus munkaegységének a számát 4-re
         * Ha van még játékos, akkor meghívja rajta a next_Player függvényt, ha nincs, akkor új kört kezd
         */
        g.getActualPlayer().setWork_Unit(4);
        if(g.getRemainingPlayers() > 0){
            g.next_Player(g.getVirologists().get(g.getRemainingPlayers()-1));
            //Kiírjuk, mivel lett mekkenve
            if(g.getActualPlayer().getisBear()){
                if(testMode){
                    results = "You have been spreaded with Bear Agent\n";
                }
                else{
                    System.out.println("You have been spreaded with Bear Agent");
                }
            }
            else if(g.getActualPlayer().getisOblivion()){
                if(testMode){
                    results = "You have been spreaded with Oblivion_agent\n";
                }
                else{
                    System.out.println("You have been spreaded with Oblivion_agent");
                }
            }
            else if(g.getActualPlayer().getIsParalyzed()){
                if(testMode){
                    results = "You have been spreaded with Paralyze_agent\n";
                }
                else{
                    System.out.println("You have been spreaded with Paralyze_agent");
                }
            }
            else if(g.getActualPlayer().getIsInvulnerable()){
                if(testMode){
                    results = "You have been spreaded with Invulnerability_agent\n";
                }
                else{
                    System.out.println("You have been spreaded with Invulnerability_agent");
                }
            }
            else if(g.getActualPlayer().getIsChorea()){
                if(testMode){
                    results = "You have been spreaded with Chorea_agent\n";
                }
                else{
                    System.out.println("You have been spreaded with Chorea_agent");
                }
            }
            else if(g.getActualPlayer().getisDead()){
                if(testMode){
                    results = "You have been killed\n";
                }
                else{
                    System.out.println("You have been killed");
                }
            }
            for(Agent a : g.getActualPlayer().getAgents().getAgents()){
                if(a.getIsSpreadedBack()){
                    if(testMode){
                        results = "Your " + a.toString() + " has been spreaded back to you";
                    }
                    else{
                        System.out.println("Your " + a.toString() + " has been spreaded back to you");
                    }
                }
            }
        }
        else{
            g.next_Round();
            for(Agent a : g.getActualPlayer().getAgents().getAgents()){
                if(a.getIsSpreadedBack()){
                    if(testMode){
                        results = "Your " + a.toString() + " has been spreaded back to you\n";
                    }
                    else{
                        System.out.println("Your " + a.toString() + " has been spreaded back to you");
                    }
                }
            }
        }
    }

    /**
     * A játékban a már beírt érvényes parancsok naplózását szolgáló függvény
     * @param cmd
     */
    static void addCommand(String[] cmd){
        //
    }

    /**
     * Függvény random ikon generálásához
     * @return
     */
    public static String generateIcon(){
        Random r = new Random();
        int randomszam = r.nextInt(6) + 1;
        while(reservedIcons.contains(randomszam)){
            randomszam = r.nextInt(6) + 1;
        }
        String result = "v" + randomszam + ".png";
        reservedIcons.add(randomszam);
        return result;
    }

}
