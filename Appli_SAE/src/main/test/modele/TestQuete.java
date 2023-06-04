package modele;

import org.junit.jupiter.api.Assertions;

public class TestQuete {
    Quete q = new Quete("");

    public void testQuete(){
        String ligne = "1|(1, 2)|()|60|100|Quête de test";
        Quete q1 = new Quete(ligne);

        Assertions.assertEquals(1, q1.getNumero());
        Assertions.assertArrayEquals(new int[]{1, 2}, q1.getPos());
        Assertions.assertArrayEquals(new int[0], q1.getPrecond1());
        Assertions.assertArrayEquals(new int[0], q1.getPrecond2());
        Assertions.assertEquals(60, q1.getDuree());
        Assertions.assertEquals(100, q1.getExperience());
        Assertions.assertEquals("Quête de test", q1.getIntitule());
    }
    public void testExtraitPos(){
        Assertions.assertArrayEquals(new int[]{1, 2}, q.extraitPos("(1,2)"));
        Assertions.assertArrayEquals(new int[]{-1, 2}, q.extraitPos("(-1,2)"));
        Assertions.assertArrayEquals(new int[]{1, -2}, q.extraitPos("(1,-2)"));
        Assertions.assertArrayEquals(new int[]{-1, -2}, q.extraitPos("(-1,-2)"));
    }

    public void testExtraitPrecond(){
        Assertions.assertArrayEquals(new int[][]{new int[]{}, new int[]{}}, q.extraitPrecond("()"));
        Assertions.assertArrayEquals(new int[][]{new int[]{3}, new int[]{}}, q.extraitPrecond("(3,)"));
        Assertions.assertArrayEquals(new int[][]{new int[]{3,4}, new int[]{}}, q.extraitPrecond("((3,4),)"));
        Assertions.assertArrayEquals(new int[][]{new int[]{3,4}, new int[]{2}}, q.extraitPrecond("((3,4),(2,)"));
        Assertions.assertArrayEquals(new int[][]{new int[]{3,4}, new int[]{2,8}}, q.extraitPrecond("((3,4),(2,8)"));
    }

    public void testFormatPrecond(){
        Assertions.assertEquals("(1,2)", q.formatPrecond(new int[]{}));
        Assertions.assertEquals("(1,2)", q.formatPrecond(new int[]{7}));
        Assertions.assertEquals("(1,2)", q.formatPrecond(new int[]{3,7}));
    }
}
