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
     * Foglalt ikonok t�rol�sa
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
     * Eldonti egy fajlnevrol, hogy l�tezik e
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
         *       VISSZATERES hamis �rt�kkel
         * EGY�BK�NT:
         *      PR�B�LD:
         *          �J J�T�K L�TREHOZ�SA a parameterben kapott jatekosmennyiseggel
         *          VISSZAT�R�S igaz �rt�kkel
         *      EGY�BK�NT:
         *          VISSZATERES hamis �rt�kkel
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
     * A j�t�kban az exit parancs feldolgozasokor hivott fuggveny
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
         *      VISSZATERES hamis �rt�kkel
         * EGY�BK�NT:
         * PR�B�LD:
         *      FileHandler megh�vasa name parameterrel
         *          openedMap = name
         *          �J J�T�K LETREHOZ�SA
         *          CIKLUS a betoltott virologusokon:
         *              virologus jatekhoz hozzaadasa
         *          CIKLUS a betoltott Fieldeken:
         *              Field jatekhoz hozzaadasa
         *          CIKLUS a betoltott Objektumokon:
         *              Objektum jatekhoz, es mezokhoz hozzadasa
         *          CIKLUS a betoltott Parancsokon:
         *              adott parancs vegrehajtasa
         * HA nem siker�l:
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
     * A j�t�kban a save parancs feldolgoz�sakor h�vott f�ggv�ny
     * @param name a kimeneti fajl neve
     * @param cmd �rja e ki a parancssorra
     * @return  sikerult e
     */
    static boolean save(String name, String openedMap, boolean cmd){
        /**
         * PR�B�LD:
         *      Filehandler.saveFile(name, openedMap, cmd) megh�v�sa
         */
        FileHandler.saveFile(System.getProperty("user.dir")+"/test/"+name, openedMap, cmd, g);
        return true;
    }

    /**
     * A j�t�kban a listNeighbours parancs feldolgoz�sakor h�vott f�ggv�ny
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
     * A j�t�kban a step parancs feldolgozasakor h�vott f�ggv�ny
     * @param neighbourNum
     */
    static boolean step(int neighbourNum){
        /**
         * PROBALD:
         *      Aktualis jatekos leptetese a neighbourNUm mezore
         *      VISSZAT�R�S igaz �rt�kkel
         * EGY�BK�NT
         *      VISSZAT�R�S hamis �rt�kkel
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
     * A j�t�kban a learnGene parancs feldolgoz�sakor h�vott f�ggv�ny
     * @param geneName a j�t�kos �ltal megadott g�n n�v
     * @return sikerult e
     */
    static boolean learnGene(String geneName){
        /**
         * PROBALD:
         *      HA geneName = Chorea_gene:
         *          Hozd l�tre a Chorea g�nt
         *          aktu�lis virol�gus learn_Gene f�ggv�ny�nek megh�v�sa
         *      HA geneName = Oblivion_gene:
         *          Hozd l�tre a Feled�s g�nt
         *          aktu�lis virol�gus learn_Gene f�ggv�ny�nek megh�v�sa
         *      HA geneName = Invulnerability_gene:
         *          Hozd l�tre a �rinthetlens�g g�nt
         *          aktu�lis virol�gus learn_Gene f�ggv�ny�nek megh�v�sa
         *      HA geneName = Paralyze_gene:
         *          Hozd l�tre a benulas g�nt
         *          aktu�lis virol�gus learn_Gene f�ggv�ny�nek megh�v�sa
         *      HA egyik g�n n�v se:
         *          Visszat�r�s hamis �rt�kkel
         *      EGY�BK�NT:
         *          VISSZAT�R�S igaz �rt�kkel
         *  EGY�BK�NT:
         *      VISSZAT�R�S hamis �rt�kkel
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
     * A j�t�kban a craftAgent parancs feldolgoz�sakor h�vott f�ggv�ny
     * @param agent az elk�sz�teni k�v�nt �gens neve
     * @return siker�lt e
     */
    public static boolean craftAgent(String agent){
        /**
         * PROBALD:
         *      HA agent = Chorea_agent:
         *          aktu�lis virol�gus craftAgent(�j�gensId) f�ggv�ny�nek megh�v�sa
         *      HA agent = Oblivion_agent:
         *          aktu�lis virol�gus craftAgent(�j�gensId) f�ggv�ny�nek megh�v�sa
         *      HA agent = Invulnerability_agent:
         *          aktu�lis virol�gus craftAgent(�j�gensId) f�ggv�ny�nek megh�v�sa
         *      HA agent = Paralyze_agent:
         *          aktu�lis virol�gus craftAgent(�j�gensId) f�ggv�ny�nek megh�v�sa
         *      HA egyik �gens n�v se a fentiek k�z�l:
         *          Visszat�r�s hamis �rt�kkel
         *      EGY�BK�NT:
         *          VISSZAT�R�S igaz �rt�kkel
         *  EGY�BK�NT:
         *      VISSZAT�R�S hamis �rt�kkel
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
     * A j�t�kban a listThingsOnField parancs feldolgoz�sakor h�vott f�ggv�ny NA EZ NEMJ�
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
     * A j�t�kban a pickUp parancs feldolgoz�sakor h�vott f�ggv�ny
     */
    static boolean pickUp(String thing){
        /**
         * PR�B�LD:
         *      HA thing = Axe:
         *          eq = jelenlegi virologus mezojenek getEq(1) h�v�sa
         *          jelenlegi virol�gus pickupEq(eq) h�v�sa
         *      HA thing = Cloak:
         *          eq = jelenlegi virologus mezojenek getEq(2) h�v�sa
         *          jelenlegi virol�gus pickupEq(eq) h�v�sa
         *      HA thing = Gloves:
         *          eq = jelenlegi virologus mezojenek getEq(3) h�v�sa
         *          jelenlegi virol�gus pickupEq(eq) h�v�sa
         *      HA thing = Sack:
         *          eq = jelenlegi virologus mezojenek getEq(4) h�v�sa
         *          jelenlegi virol�gus pickupEq(eq) h�v�sa
         *      HA thing = Nucleotid:
         *          re = jelenlegi virologus mezojenek getRe(2) h�v�sa
         *          jelenlegi virol�gus pickupRe(re) h�v�sa
         *      HA thing = Amino_acid:
         *          re = jelenlegi virologus mezojenek getRe(1) h�v�sa
         *          jelenlegi virol�gus pickupRe(re) h�v�sa
         *      HA egyik sem a fentiek k�z�l:
         *          VISSZAT�R�S hamis �rt�kkel
         *      EGY�BK�NT:
         *          VISSZAT�R�S igaz �rt�kkel
         * EGY�BK�NT:
         *      VISSZAT�R�S hamis �rt�kkel
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
     * A j�t�kban az attack parancs feldolgoz�sakor h�vott f�ggv�ny
     * @param target a megt�mand� Virol�gus azonos�t�ja
     * @return siker�lt e
     */
    static boolean attack(String target) {
        /**
         * PR�B�LD:
         *      CIKLUS a jelenlegi virol�gus mez�j�nek virol�gusain:
         *          HA adott virol�gus neve = target:
         *              jelenlegi virol�gus attack(adott virol�gus) h�v�sa
         *              sikeres = igaz
         *
         *      HA sikeres:
         *          VISSZAT�R�S igaz �rt�kkel
         *      EGY�BK�NT:
         *          VISSZAT�R�S hamis �rt�kkel
         * EGY�BK�NT:
         *      VISSZAT�R�S hamis �rt�kkel
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
     * A j�t�kban a spread parancs feldolgoz�sakor h�vott f�ggv�ny
     * @param target a megt�mand� Virol�gus azonos�t�ja
     * @param agent  a felkenni sz�nd�kozott �gens neve
     * @return siker�lt e
     */
    static boolean spread(String target, String agent) {
        /**
         * PR�B�LD:
         *      HA agent = Chorea_agent:
         *          temp agens = �J Chorea_agent
         *      HA agent = Oblivion_agent:
         *          temp agens = �J Oblivion_agent
         *      HA agent = Invulnerability_agent:
         *          temp agens = �J Invulnerability_agent
         *      HA agent = Paralyze_agent:
         *          temp agens = �J Paralyze_agent
         *      HA egyik �gens n�v se a fentiek k�z�l:
         *          Visszat�r�s hamis �rt�kkel
         *
         *      CIKLUS a jelenlegi virol�gus mez�j�nek virol�gusain:
         *          HA adott virol�gus neve = target:
         *              jelenlegi virol�gus spreading(temp agens, adott virol�gus) h�v�sa
         *              sikeres = igaz
         *      HA sikeres:
         *          VISSZAT�R�S igaz �rt�kkel
         *      EGY�BK�NT:
         *          VISSZAT�R�S hamis �rt�kkel
         * EGY�BK�NT:
         *      VISSZAT�R�S hamis �rt�kkel
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
     * A j�t�kban a spreadingBack parancs feldolgoz�sakor h�vott f�ggv�ny
     * @param target a visszamegt�mand� Virol�gus azonos�t�ja
     * @param agent  a visszakenni sz�nd�kozott �gens neve
     * @return siker�lt e
     */
        static boolean spreadingBack(String target, String agent){
            /**
             * PR�B�LD:
             *      HA agent = Chorea_agent:
             *          temp agens = �J Chorea_agent
             *      HA agent = Oblivion_agent:
             *          temp agens = �J Oblivion_agent
             *      HA agent = Invulnerability_agent:
             *          temp agens = �J Invulnerability_agent
             *      HA agent = Paralyze_agent:
             *          temp agens = �J Paralyze_agent
             *      HA egyik �gens n�v se a fentiek k�z�l:
             *          Visszat�r�s hamis �rt�kkel
             *
             *      CIKLUS a jelenlegi virol�gus mez�j�nek virol�gusain:
             *          HA adott virol�gus neve = target:
             *              jelenlegi virol�gus spreadingBack(temp agens, adott virol�gus) h�v�sa
             *              sikeres = igaz
             *      HA sikeres:
             *          VISSZAT�R�S igaz �rt�kkel
             *      EGY�BK�NT:
             *          VISSZAT�R�S hamis �rt�kkel
             * EGY�BK�NT:
             *      VISSZAT�R�S hamis �rt�kkel
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
     * A j�t�kban a steal parancs feldolgoz�sakor h�vott f�ggv�ny
     * @param thing ellopni k�v�nt dolog neve
     * @param target kifosztand� virol�gus
     * @return sikeres e
     */
        static boolean steal(String thing, String target){

            /**
             * PR�B�LD:
             *      HA thing = Chorea_agent:
             *          temp thing = �J Chorea_agent
             *          kategoria = Agent
             *      HA thing = Oblivion_agent:
             *          temp thing = �J Oblivion_agent
             *          kategoria = Agent
             *      HA thing = Invulnerability_agent:
             *          temp thing = �J Invulnerability_agent
             *          kategoria = Agent
             *      HA thing = Paralyze_agent:
             *          temp thing = �J Paralyze_agent
             *          kategoria = Agent
             *      HA thing = Axe:
             *          temp thing = �J Axe
             *          kategoria = Equipment
             *      HA thing = Cloak:
             *          temp thing = �J Cloak
             *          kategoria = Equipment
             *      HA thing = Gloves:
             *          temp thing = �J Gloves
             *          kategoria = Equipment
             *      HA thing = Sack:
             *          temp thing = �J Sack
             *          kategoria = Equipment
             *      HA thing = Amino_acid:
             *          temp thing = �J Amino_acid
             *          kategoria = Resource
             *      HA thing = Nucleotid:
             *          temp thing = �J Nucleotid
             *          kategoria = Resource
             *      HA egyik dolog  n�v se a fentiek k�z�l:
             *          Visszat�r�s hamis �rt�kkel
             *
             *      CIKLUS a jelenlegi virol�gus mez�j�nek virol�gusain:
             *          HA adott virol�gus neve = target:
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
             *          VISSZAT�R�S igaz �rt�kkel
             *      EGY�BK�NT:
             *          VISSZAT�R�S hamis �rt�kkel
             * EGY�BK�NT:
             *      VISSZAT�R�S hamis �rt�kkel
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
     * A j�t�kban a stat parancs feldolgoz�sakor h�vott f�ggv�ny
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
     * A j�t�kban a nextPlayer parancs feldolgoz�sakor h�vott f�ggv�ny
     */
    static void nextPlayer(){
        /**
         * Vissza�ll�tja az aktu�lis virol�gus munkaegys�g�nek a sz�m�t 4-re
         * Ha van m�g j�t�kos, akkor megh�vja rajta a next_Player f�ggv�nyt, ha nincs, akkor �j k�rt kezd
         */
        g.getActualPlayer().setWork_Unit(4);
        if(g.getRemainingPlayers() > 0){
            g.next_Player(g.getVirologists().get(g.getRemainingPlayers()-1));
            //Ki�rjuk, mivel lett mekkenve
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
     * A j�t�kban a m�r be�rt �rv�nyes parancsok napl�z�s�t szolg�l� f�ggv�ny
     * @param cmd
     */
    static void addCommand(String[] cmd){
        //
    }

    /**
     * F�ggv�ny random ikon gener�l�s�hoz
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
