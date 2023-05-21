import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * A jatek menthetosegeert, illetve visszatolthetosegeert felelos osztaly
 */
public class FileHandler {
    /**
     * A palyan levo virologusok listaja
     */
    static ArrayList<Virologist> virologists = new ArrayList<>();
    /**
     * A palyan levo mezok listaja
     */
    static ArrayList<Field> fields = new ArrayList<>();
    /**
     * A jatek aktualis koreben hatralevo jatekosok szama
     */
    static int remaining_virologists;

    /**
     * Betolti a parameterben megadott fajlnevu fajlban levo jatekelemeket
     * @param filename
     * @return sikerult e a betoltes
     */
    public static boolean loadFile(String filename){
        /** PRÓBÁLD:
         *      XML fajl megnyitasa filename parameterrel
         *      CIKLUS az XML main node-jain:
         *          HA Virologus a node:
         *              new = Új virologus a node alapján
         *              Adjuk hozzá new-t a virológusokhoz
         *          HA Field a node:
         *              new = ÚJ Field a node alapján
         *              Adjuk hozzá a new-t a Fieldekhez
         *          HA Objektum a node:
         *              new = ÚJ objektum a node alapján
         *              Adjuk hozzá a new-t az Objektumokhoz
         *          HA Parancs a node:
         *              Adjuk hozzá a parancsot a parancsokhoz, a node alapján
         *      CIKLUS VEGE
         *      VISSZATERES igaz értékkel
         *  EGYÉBKÉNT:
         *      VISSZATÉRÉS hamis értékkel
         *
         *
         *
         */
        virologists = new ArrayList<>();
        fields = new ArrayList<>();
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler(){

                //a megfelelõ mûködéshez különbözõ objektumok beolvasása esetén
                private boolean isCity;
                private boolean isPlayers;
                private boolean isAgent;
                private boolean isEquipment;
                private boolean isGene;
                private boolean isResource;


                //fieldek beolvasásához
                private int fSide;
                private ArrayList<Integer> fNeighbourId = new ArrayList<>();
                private ArrayList<String> fVirologistName = new ArrayList<>();
                private ArrayList<Point> points = new ArrayList<>();


                //shelter/virologist equipment beolvasásához
                private ArrayList<Equipment> Equipment = new ArrayList<>();
                //laboratory/virologist genes beolvasásához
                private ArrayList<Gene> Genes = new ArrayList<>();
                //warehouse/virologist backpack beolvasásához
                private ArrayList<Resource> Resources = new ArrayList<>();
                //virologist agents beolvasásához
                private ArrayList<Agent> Agents = new ArrayList<>();


                //virologistok beolvasásához
                private String vname;
                private boolean visParalyzed;
                private boolean visChorea;
                private boolean visInvulnerable;
                private boolean visOblivion;
                private boolean visSackActive;
                private boolean visCloakActive;
                private boolean visGlovesActive;
                private boolean visDead;
                private boolean visAxeActive;
                private boolean visBear;
                private int vwork_Unit;
                private int f_id;
                private Backpack vbackpack;
                private Agents vagents;
                private Genes vgenes;
                private Equipment_s vequipment;


                private int bpSize;
                private int bpMaxSize;
                private int gAcquired;


                //Agent beolvasásához
                private int amino_cost;
                private int nucleo_cost;
                private int effect_time;
                private String effected_virologist_name;
                private boolean is_used;
                private int a_id;

                //Equipment beolvasásához
                private int durability;
                private int e_id;
                //Sack esetén
                private int prevsize;


                //Gene beolvasásához
                private int g_id;


                //Resource beolvasásához
                private int r_id;

                String chars;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    switch (qName){
                        case "city": isCity = true; break;
                        case "players": isPlayers = true; break;
                        case "equipment": isEquipment = true; break;
                        case "agent": isAgent = true; break;
                        case "gene": isGene = true; break;
                        case "resource": isResource = true; break;
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    switch (qName){
                        case "city": isCity = false; break;
                        case "players": isPlayers = false; break;
                        case "equipment":
                            if(e_id == 1)
                                Equipment.add(new Axe());
                            else if(e_id == 2)
                                Equipment.add(new Cloak());
                            else if(e_id == 3)
                                Equipment.add(new Gloves());
                            else if(e_id == 4)
                                Equipment.add(new Sack(prevsize));
                            break;
                        case "agent":
                            if(a_id == 1)
                                Agents.add(new Paralyze_agent(amino_cost, nucleo_cost, effect_time, effected_virologist_name, is_used));
                            else if(a_id == 2)
                                Agents.add(new Oblivion_agent());
                            else if(a_id == 3)
                                Agents.add(new Invulnerability_agent(amino_cost, nucleo_cost, effect_time, effected_virologist_name, is_used));
                            else if(a_id == 4)
                                Agents.add(new Chorea_agent(amino_cost, nucleo_cost, effect_time, effected_virologist_name, is_used));
                            else if(a_id == 5)
                                Agents.add(new Bear_agent(amino_cost, nucleo_cost, effect_time, effected_virologist_name, is_used));
                            break;
                        case "gene":
                            if(g_id == 1)
                                Genes.add(new Paralyze_gene());
                            else if(g_id == 2)
                                Genes.add(new Oblivion_gene());
                            else if(g_id == 3)
                                Genes.add(new Invulnerability_gene());
                            else if(g_id == 4)
                                Genes.add(new Chorea());
                            else if(g_id == 5)
                                Genes.add(new Bear_gene());
                            break;
                        case "resource":
                            if(r_id == 1)
                                Resources.add(new Amino_acid());
                            else if(r_id == 2)
                                Resources.add(new Nucleotid());
                            break;
                        case "remaining_players": remaining_virologists = Integer.parseInt(chars); break;
                        case "field":
                            if(isCity){


                                if(isGene){
                                    fields.add(new Laboratory(fSide, Genes, fNeighbourId, points));
                                }
                                else if(isEquipment){
                                    fields.add(new Shelter(fSide, Equipment, fNeighbourId, points));
                                }
                                else if(isResource){
                                    fields.add(new Warehouse(fSide, Resources, fNeighbourId, points));
                                }
                                else{
                                    fields.add(new Field(fSide, fNeighbourId, points));
                                }
                                isGene = false;
                                isEquipment = false;
                                isResource = false;
                                fNeighbourId.clear();
                                fVirologistName.clear();
                                Genes.clear();
                                Resources.clear();
                                Equipment.clear();
                                points.clear();
                            }
                            break;
                        case "side": fSide = Integer.parseInt(chars); break;
                        case "virologist_name":
                            if(isCity){
                                fVirologistName.add(chars);
                            }
                            else
                                effected_virologist_name = chars;
                            break;
                        case "virologist":
                            if(isPlayers){
                                Field f = null;
                                for(int i = 0; i < fields.size(); ++i)
                                    fields.get(i).setId(i);
                                for(int i = 0; i < fields.size(); ++i)
                                    if(fields.get(i).getId() == f_id)
                                        f = fields.get(i);
                                
                                virologists.add(new Virologist(
                                        vname, visParalyzed, visChorea, visInvulnerable, visOblivion,
                                        visSackActive, visCloakActive, visGlovesActive, visDead, visAxeActive, visBear,
                                        vwork_Unit, f, vbackpack, vagents, vgenes, vequipment));
                                Resources.clear();
                                Equipment.clear();
                                Genes.clear();
                                Agents.clear();
                            }
                            break;
                        case "durability": durability = Integer.parseInt(chars); break;
                        case "id":
                            if(isResource)
                                r_id = Integer.parseInt(chars);
                            else if(isAgent)
                                a_id = Integer.parseInt(chars);
                            else if(isEquipment)
                                e_id = Integer.parseInt(chars);
                            else if(isGene)
                                g_id = Integer.parseInt(chars);
                            else {
                                f_id = Integer.parseInt(chars);
                                fNeighbourId.add(f_id);
                            }
                            break;
                        case "previoussize": prevsize = Integer.parseInt(chars); break;

                        case "name": vname = chars; break;
                        case "isParalyzed": visParalyzed = Boolean.parseBoolean(chars); break;
                        case "isChorea": visChorea = Boolean.parseBoolean(chars); break;
                        case "isInvulnerable": visInvulnerable = Boolean.parseBoolean(chars); break;
                        case "isOblivion": visOblivion = Boolean.parseBoolean(chars); break;

                        case "isSackActive": visSackActive = Boolean.parseBoolean(chars); break;
                        case "isCloakActive": visCloakActive = Boolean.parseBoolean(chars); break;
                        case "isGlovesActive": visGlovesActive = Boolean.parseBoolean(chars); break;
                        case "isAxeActive": visAxeActive = Boolean.parseBoolean(chars); break;

                        case "isDead": visDead = Boolean.parseBoolean(chars); break;
                        case "isBear": visBear = Boolean.parseBoolean(chars); break;

                        case "workUnit": vwork_Unit = Integer.parseInt(chars); break;

                        case "size": bpSize = Integer.parseInt(chars); break;
                        case "maxsize": bpMaxSize = Integer.parseInt(chars); break;

                        case "backpack":
                            vbackpack = new Backpack(bpSize, bpMaxSize, Resources);
                            isResource = false;
                            break;
                        case "equipment_s":
                            vequipment = new Equipment_s(Equipment);
                            isEquipment = false;
                            break;
                        case "agents":
                            vagents = new Agents(Agents);
                            isAgent = false;
                            break;
                        case "genes":
                            vgenes = new Genes(gAcquired, Genes);
                            isGene = false;
                            break;



                        case "amino_cost": amino_cost = Integer.parseInt(chars); break;
                        case "nucleo_cost": nucleo_cost = Integer.parseInt(chars); break;

                        case "acquired": gAcquired = Integer.parseInt(chars); break;
                        case "effect_time": effect_time = Integer.parseInt(chars); break;
                        case "is_used": is_used = Boolean.parseBoolean(chars); break;

                        case "points": String s = chars;
                            String[] ps = s.split(",");
                            for(int i = 0; i < ps.length; i += 2){
                                Point p = new Point(Integer.parseInt(ps[i]), Integer.parseInt(ps[i + 1]));
                                points.add(p);
                            }
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    chars = new String(ch, start, length);
                }
            };

            parser.parse(filename, handler);

            for(int i = 0; i < fields.size(); ++i)
                fields.get(i).setId(i);

            //beallitja a mezok szomszedait az azonositok alapjan
            for(Field f: fields)
                for(Integer i: f.neighbourId)
                    for(Field f2: fields)
                        if(f2.getId() == i)
                            f.addNeighbour2(f2);

            //beallitja az agenseken az altaluk befolyasolt virologust a virologus neve alapjan
            for(Virologist v: virologists)
                for(Agent a: v.getAgents().getAgents())
                    for(Virologist t: virologists)
                        if(a.getEffected_virologist() != null && a.getEffected_virologist_name().equals(t.getName()))
                            a.setEffected_virologist(t);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Elmenti a jatekelemeket egy parameterben megadott fajlnevu fajlba
     * @param filename
     * @return sikerult e a mentes
     */
    public static boolean saveFile(String filename, String openedMap, boolean isOnConsole, Game g){
        /**
         * PRÓBÁLD:
         *      XML fájl megnyitása openedmap paraméterrel
         *      XML fájl megnyitása filename paraméterrel
         *      openedmap tartalmának másolása filename-be
         *      CIKLUS cmd, a commands-okon:
         *          Új node létrehozása
         *          cmd beleírása
         *      VISSZATÉRÉS igaz értékkel
         * EGYÉBKÉNT:
         *      VISSZATÉRÉS hamis értékkel
         */
        try{
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            Element root = doc.createElement("game");
            doc.appendChild(root);

            Element city = doc.createElement("city");
            root.appendChild(city);

            for(Field f: g.getCity().getFields()) {
                Element field = doc.createElement("field");
                city.appendChild(field);

                Element side = doc.createElement("side");
                side.appendChild(doc.createTextNode(String.valueOf(f.getSide())));
                field.appendChild(side);

                Element neigh = doc.createElement("neighbours");
                field.appendChild(neigh);
                for(Field f2: f.getNeighbours()){
                    Element fi = doc.createElement("neighbour");
                    neigh.appendChild(fi);

                    Element neighbour = doc.createElement("id");
                    neighbour.appendChild(doc.createTextNode(String.valueOf(f2.getId())));
                    fi.appendChild(neighbour);
                }

                Element points = doc.createElement("points");
                points.appendChild(doc.createTextNode(f.polygonposes()));
                field.appendChild(points);

                Element viro = doc.createElement("virologists");
                field.appendChild(viro);

                for(Virologist v: f.getVirologists()){
                    Element virologist = doc.createElement("virologist");
                    viro.appendChild(virologist);

                    Element vname = doc.createElement("virologist_name");
                    vname.appendChild(doc.createTextNode(v.getName()));
                    virologist.appendChild(vname);
                }

                try{
                    Laboratory l = (Laboratory)f;

                    Element genes = doc.createElement("ges");
                    field.appendChild(genes);

                    for(Gene gene: l.getGenes()){
                        Element ge = doc.createElement("gene");
                        genes.appendChild(ge);

                        Element id = doc.createElement("id");
                        id.appendChild(doc.createTextNode(String.valueOf(gene.getId())));
                        ge.appendChild(id);
                    }
                }catch(Exception ex){}


                try{
                    Shelter s = (Shelter)f;

                    Element eq = doc.createElement("eq");
                    field.appendChild(eq);

                    for(Equipment e: s.getEq()){
                        Element equip = doc.createElement("equipment");
                        eq.appendChild(equip);

                        Element durability = doc.createElement("durability");
                        durability.appendChild(doc.createTextNode(String.valueOf(e.getDurability())));
                        equip.appendChild(durability);

                        Element id = doc.createElement("id");
                        id.appendChild(doc.createTextNode(String.valueOf(e.getId())));
                        equip.appendChild(id);

                        if(e.getId() == 4){
                            Element prevsize = doc.createElement("previoussize");
                            prevsize.appendChild(doc.createTextNode(String.valueOf(((Sack)e).getPrevioussize())));
                            equip.appendChild(prevsize);
                        }
                    }
                }catch(Exception ex){}


                try {
                    Warehouse w = (Warehouse) f;

                    Element res = doc.createElement("res");
                    field.appendChild(res);

                    for (Resource r : w.getRes()) {
                        Element resource = doc.createElement("resource");
                        res.appendChild(resource);

                        Element id = doc.createElement("id");
                        id.appendChild(doc.createTextNode(String.valueOf(r.getId())));
                        resource.appendChild(id);
                    }
                }catch (Exception ex){};
            }

            Element rem_player = doc.createElement("remaining_players");
            rem_player.appendChild(doc.createTextNode(String.valueOf(g.getRemainingPlayers())));
            root.appendChild(rem_player);

            Element players = doc.createElement("players");
            root.appendChild(players);

            for(Virologist v: g.getVirologists()){
                Element virologist = doc.createElement("virologist");
                players.appendChild(virologist);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(v.getName()));
                virologist.appendChild(name);

                Element isP = doc.createElement("isParalyzed");
                isP.appendChild(doc.createTextNode(String.valueOf(v.getIsParalyzed())));
                virologist.appendChild(isP);

                Element isC = doc.createElement("isChorea");
                isC.appendChild(doc.createTextNode(String.valueOf(v.getIsChorea())));
                virologist.appendChild(isC);

                Element isI = doc.createElement("isInvulnerable");
                isI.appendChild(doc.createTextNode(String.valueOf(v.getIsInvulnerable())));
                virologist.appendChild(isI);

                Element isO = doc.createElement("isOblivion");
                isO.appendChild(doc.createTextNode(String.valueOf(v.getisOblivion())));
                virologist.appendChild(isO);

                Element isSA = doc.createElement("isSackActive");
                isSA.appendChild(doc.createTextNode(String.valueOf(v.getIsSackActive())));
                virologist.appendChild(isSA);

                Element isCA = doc.createElement("isCloakActive");
                isCA.appendChild(doc.createTextNode(String.valueOf(v.getIsCloakActive())));
                virologist.appendChild(isCA);

                Element isGA = doc.createElement("isGlovesActive");
                isGA.appendChild(doc.createTextNode(String.valueOf(v.getIsGlovesActive())));
                virologist.appendChild(isGA);

                Element isD = doc.createElement("isDead");
                isD.appendChild(doc.createTextNode(String.valueOf(v.getisDead())));
                virologist.appendChild(isD);

                Element isAA = doc.createElement("isAxeActive");
                isAA.appendChild(doc.createTextNode(String.valueOf(v.getisAxeActive())));
                virologist.appendChild(isAA);

                Element isB = doc.createElement("isBear");
                isB.appendChild(doc.createTextNode(String.valueOf(v.getisBear())));
                virologist.appendChild(isB);

                Element wu = doc.createElement("workUnit");
                wu.appendChild(doc.createTextNode(String.valueOf(v.getWork_Unit())));
                virologist.appendChild(wu);

                Element fi = doc.createElement("field");
                virologist.appendChild(fi);

                Element field = doc.createElement("id");
                field.appendChild(doc.createTextNode(String.valueOf(v.getField().getId())));
                fi.appendChild(field);

                Element bp = doc.createElement("backpack");
                virologist.appendChild(bp);

                Element size = doc.createElement("size");
                size.appendChild(doc.createTextNode(String.valueOf(v.getBackpack().getSize())));
                bp.appendChild(size);

                Element maxsize = doc.createElement("maxsize");
                maxsize.appendChild(doc.createTextNode(String.valueOf(v.getBackpack().getMaxSize())));
                bp.appendChild(maxsize);

                for(Resource r: v.getBackpack().getResources()){
                    Element res = doc.createElement("resource");
                    bp.appendChild(res);

                    Element id = doc.createElement("id");
                    id.appendChild(doc.createTextNode(String.valueOf(r.getId())));
                    res.appendChild(id);
                }

                Element ags = doc.createElement("agents");
                virologist.appendChild(ags);

                for(Agent a: v.getAgents().getAgents()){
                    Element ag = doc.createElement("agent");
                    ags.appendChild(ag);

                    Element id = doc.createElement("id");
                    id.appendChild(doc.createTextNode(String.valueOf(a.getId())));
                    ag.appendChild(id);

                    Element amino = doc.createElement("amino_cost");
                    amino.appendChild(doc.createTextNode(String.valueOf(a.getAmino_cost())));
                    ag.appendChild(amino);

                    Element nucleo = doc.createElement("nucleo_cost");
                    nucleo.appendChild(doc.createTextNode(String.valueOf(a.getNucleo_cost())));
                    ag.appendChild(nucleo);

                    Element time = doc.createElement("effect_time");
                    time.appendChild(doc.createTextNode(String.valueOf(a.getEffect_time())));
                    ag.appendChild(time);

                    Element evirologist = doc.createElement("effected_virologist");
                    ag.appendChild(evirologist);

                    Element vname = doc.createElement("virologist_name");
                        if(a.getEffected_virologist() != null){
                        vname.appendChild(doc.createTextNode(a.getEffected_virologist().getName()));
                        evirologist.appendChild(vname);
                    }

                    Element isU = doc.createElement("is_used");
                    isU.appendChild(doc.createTextNode(String.valueOf(a.getIs_used())));
                    ag.appendChild(isU);
                }

                Element genes = doc.createElement("genes");
                virologist.appendChild(genes);

                Element acq = doc.createElement("acquired");
                acq.appendChild(doc.createTextNode(String.valueOf(v.getGenes().getAcquired())));
                genes.appendChild(acq);

                for(Gene gene: v.getGenes().getGenes()){
                    Element ge = doc.createElement("gene");
                    genes.appendChild(ge);

                    Element id = doc.createElement("id");
                    id.appendChild(doc.createTextNode(String.valueOf(gene.getId())));
                    ge.appendChild(id);
                }

                Element eq = doc.createElement("equipment_s");
                virologist.appendChild(eq);

                for(Equipment e: v.getEquipment().getEquipment()){
                    Element equip = doc.createElement("equipment");
                    eq.appendChild(equip);

                    Element durability = doc.createElement("durability");
                    durability.appendChild(doc.createTextNode(String.valueOf(e.getDurability())));
                    equip.appendChild(durability);

                    Element id = doc.createElement("id");
                    id.appendChild(doc.createTextNode(String.valueOf(e.getId())));
                    equip.appendChild(id);

                    if(e.getId() == 4){
                        Element prevsize = doc.createElement("previoussize");
                        prevsize.appendChild(doc.createTextNode(String.valueOf(((Sack)e).getPrevioussize())));
                        equip.appendChild(prevsize);
                    }
                }
            }

            Transformer tr = null;
            try {
                tr = TransformerFactory.newInstance().newTransformer();

                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

                tr.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(filename)));
            }catch (TransformerException | IOException te){
                te.printStackTrace();
            }

        }catch (ParserConfigurationException e){
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + e);
            return false;
        }

        return true;
    }

    /**
     * Visszaadja a jelenleg az osztalyban levo virologusokat
     * @return
     */
    public static ArrayList<Virologist> getVirologists(){
        return null;
    }

    /**
     * Visszaadja a jelenleg az osztalyban levo mezoket
     * @return
     */
    public static ArrayList<Field> getFields(){
        return null;
    }

    /**
     * Visszaadja a jelenleg az osztalyban levo objektumokat
     * @return
     */
    public static ArrayList<Object> getObjects(){
        return null;
    }

    /**
     * Visszaadja a jelenleg az osztalyban levo parancsokat
     * @return
     */
    public static ArrayList<String> getCommands(){
        return null;
    }

}