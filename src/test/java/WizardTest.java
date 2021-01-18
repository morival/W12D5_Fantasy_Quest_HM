import items.magic.Spell;
import items.magic.SpellName;
import org.junit.Before;
import org.junit.Test;
import units.heroes.mages.Wizard;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WizardTest {

    Spell spell;
    Wizard wizard;

    @Before
    public void setUp() {
        spell = new Spell(SpellName.EARTHQUAKE, 100, 100);
        wizard = new Wizard("Wizard", 24, 3, 450, 0, 285);

    }

    @Test
    public void hasName() {
        assertEquals("Wizard", wizard.getName());
    }

    @Test
    public void hasAttack() {
        assertEquals(24, wizard.getAttack());
    }

    @Test
    public void hasDefence() {
        assertEquals(3, wizard.getDefence());
    }

    @Test
    public void hasHP() {
        assertEquals(450, wizard.getHp());
    }

    @Test
    public void startsWithNoGold() {
        assertEquals(0, wizard.getGold());
    }

    @Test
    public void hasMana() {
        assertEquals(285, wizard.getMana());
    }

    @Test
    public void hasSpellsInMagicBook() {
        assertEquals( 3, wizard.magicBookCount());
    }

    @Test
    public void canAddSpellsToMagicBook() {
        wizard.addSpell(spell);
        assertEquals(4, wizard.magicBookCount());
    }

    @Test
    public void canRemoveSpellFromMagicBook() {
        wizard.addSpell(spell);
        wizard.removeSpell(spell);
        assertEquals(3, wizard.magicBookCount());
    }

    @Test
    public void canSelectSpellFromMagicBook() {
        assertEquals(SpellName.LIGHTNING, wizard.getMagicBook().get(0).getSpellName());
    }

    @Test
    public void canGetRandomSpell() {
        assertNotEquals(null, wizard.getRandomSpell().getSpellName());
    }

    @Test
    public void canGetCheapestManaSpellCost() {
        assertEquals(30, wizard.getCheapestManaSpellCost());
    }

    @Test
    public void canReduceMana() {
        wizard.reduceMana(wizard.getMagicBook().get(0));
        assertEquals(215, wizard.getMana());
    }
}
