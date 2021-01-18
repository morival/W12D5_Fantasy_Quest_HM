import org.junit.Before;
import org.junit.Test;
import units.heroes.mages.Wizard;

import static org.junit.Assert.assertEquals;

public class WizardTest {

    Wizard wizard;

    @Before
    public void setUp() {
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

}
