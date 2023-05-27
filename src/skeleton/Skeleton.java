package skeleton;

import agent.CrazyVirus;
import agent.ForgetVirus;
import agent.FreezeVirus;
import agent.Vaccine;
import code.CrazyVirusCode;
import code.ForgetVirusCode;
import code.FreezeVirusCode;
import code.VaccineCode;
import equipment.Glove;
import equipment.Jacket;
import equipment.Sack;
import field.Bunker;
import field.Field;
import field.Laboratory;
import field.Storage;
import player.Virologist;

import java.util.ArrayList;

public class Skeleton {
    private Virologist v;
    private Field f1 = new Field(); // fieldNumber 0
    private Field f2 = new Storage(); // fieldNumber 1
    private Field f3 = new Laboratory(new VaccineCode()); // fieldNumber 2
    private Field f4 = new Bunker(new Glove()); // fieldNumber 3

    public Skeleton() {
        init();
    }

    /**
     * skeleton.Skeleton m�k�d�s�nek
     */
    private void init() {
        Main.printMethod("skeleton.Skeleton.init", true);
        var nb1 = new ArrayList<Field>();
        nb1.add(f2);
        nb1.add(f3);
        nb1.add(f4);

        var nb2 = new ArrayList<Field>();
        nb2.add(f1);
        nb2.add(f3);
        nb2.add(f4);

        var nb3 = new ArrayList<Field>();
        nb3.add(f1);
        nb3.add(f2);
        nb3.add(f4);

        var nb4 = new ArrayList<Field>();
        nb4.add(f1);
        nb4.add(f2);
        nb4.add(f3);

        f1.setNeighbours(nb1);
        f2.setNeighbours(nb2);
        f3.setNeighbours(nb3);
        f4.setNeighbours(nb4);

        v = new Virologist(f1);

        v.learnCode(new VaccineCode());
        v.learnCode(new FreezeVirusCode());
        v.learnCode(new ForgetVirusCode());
        v.learnCode(new CrazyVirusCode());
        Main.printMethod("return", false);
    }

    public void VirologistStepsOnField() {
        System.out.println("VirologistStepsOnField()");
        v.move(1);

        v.move(f2.getNumber());

        System.out.println("EO VirologistStepsOnField()");
    }

    /**
     * Use-case le�r�son  VirologistStepsOnStorage, de ez tal�l�bb
     */
    public void ActionOnStorage(){
        System.out.println("ActionOnStorage();");
        v.move(1); ///�tl�p a t�rol�ra

        System.out.println("AminoAcidBeforeMethod: "+ v.getAminoAcid());
        System.out.println("NucleotideBeforeMethod: "+ v.getNucleotide());
        v.action();
        System.out.println("AminoAcidAfterMethod: "+ v.getAminoAcid());
        System.out.println("NucleotideAfterMethod: "+ v.getNucleotide());

    }

    /**
     * Use-case le�r�son  VirologistStepsOnLaboratory, de ez tal�l�bb
     */
    public void ActionOnLaboratory() {
        System.out.println("ActionOnLaboratory();");
        v.move(2);  // �tl�p a laborra

        int volt = v.countOfLearnedCodes();

        v.action();     // Action h�v�s, k�dtanul�st kell eredm�nyeznie

        int lett = v.countOfLearnedCodes();

        System.out.println("kor�bban " + volt + " k�dot ismert, most" + lett + " darabot.");
        System.out.println("EO ActionOnLaboratory();");
    }

    public void VirologistRobsVirologist(){
        var f1 = new Field();
        var v = new Virologist(f1);
        var otherVirologist = new Virologist(f1);


        v.robVirologist(otherVirologist);
    }

    public void VirologistPicksUpGloves(){
        System.out.println("VirologistPicksUpGloves();");

        f4 = new Bunker(new Glove());  // Hozz�adom az f4-hez a kesztuty

        init(); // ujra init

        v.move(3); // �tl�p a bunkerre

        v.action();  // Action

        System.out.println("EO VirologistPicksUpGloves();");
    }

    public void VirologistPicksUpJacket(){
        System.out.println("VirologistPicksUpJacket();");

        f4 = new Bunker(new Jacket());  // Hozz�adom az f4-hez a kabatot

        init(); // ujra init

        v.move(3); // �tl�p a bunkerre

        v.action();  // Action

        System.out.println("EO VirologistPicksUpJacket();");
    }

    public void VirologistPicksUpSack(){
        System.out.println("VirologistPicksUpSack();");

        f4 = new Bunker(new Sack());  // Hozz�adom az f4-hez a zsakot

        init(); // ujra init

        v.move(3); // �tl�p a bunkerre

        v.action();  // Action

        System.out.println("EO VirologistPicksUpSack();");
    }

    public void VirologistCreatesVaccineAgent(){
        System.out.println("VirologistCreatesVaccineAgent();");
        v.createAgent(0); // �gens elk�sz�t�se
    }

    public void VirologistCreatesFreezeAgent(){
        System.out.println("VirologistCreatesFreezeAgent();");
        v.createAgent(1);
    }

    public void VirologistCreatesForgetAgent(){
        System.out.println("VirologistCreatesForgetAgent();");
        v.createAgent(2);
    }

    public void VirologistCreatesCrazyAgent(){
        System.out.println("VirologistCreatesCrazyAgent();");
        v.createAgent(3);
    }

    public void VirologistUsesCrazyAgent(){
        System.out.println("VirologistUsesCrazyAgent();");
        init();
        var p = new Virologist(f1);

        v.useAgent(p, new CrazyVirus());

    }

    public void VirologistUsesVaccineAgent(){
        System.out.println("VirologistUsesAgent();");
        init();
        var p = new Virologist(f1);

        v.useAgent(p, new Vaccine());

    }

    public void VirologistUsesForgetAgent(){
        System.out.println("VirologistUsesForgetAgent();");
        init();
        var p = new Virologist(f1);

        v.useAgent(p, new ForgetVirus());

    }

    public void VirologistUsesFreezeAgent() {
        System.out.println("VirologistUsesFreezeAgent();");
        init();
        var p = new Virologist(f1);

        v.useAgent(p, new FreezeVirus());
    }

    public void FrozenVirologistTriesToMove() {
        System.out.println("FrozenVirologistTriesToMove();");
        init();

        var otherVirologist = new Virologist(f1);
        //megfert�zi v-t
        otherVirologist.useAgent(v, new FreezeVirus());
        //megpr�b�l mozogni f2-re de nem tud
        v.move(f2.getNumber());

    }

    public void CrazyVirologistTriesToMove() {
        System.out.println("CrazyVirologistTriesToMove();");
        init();

        var otherVirologist = new Virologist(f1);
        //megfert�zi v-t
        otherVirologist.useAgent(v, new CrazyVirus());
        //megpr�b�l mozogni f2-re de random ir�nyba mozdul el
        v.move(f2.getNumber());

    }
}
