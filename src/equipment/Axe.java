package equipment;

import player.Virologist;
import skeleton.Main;

/**
 * Megöli a virológust, egyszer használható
 *
 */
public class Axe extends Equipment{

    public void apply(Virologist v) {
        Main.printMethod("equipment.Axe.apply()", true);
        Main.printMethod("Return", false);
    }
}
