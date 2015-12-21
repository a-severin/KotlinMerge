import com.vvv.merge.*
import org.junit.Assert.*
import org.junit.Test

class FindPositionsTests {

    private fun _createSourceLines(src: Array<String>): Array<SourceLine> {
        return src.map { it -> SourceLine(it) }.toTypedArray();
    }

    private fun _createVersionLines(src: Array<String>): Array<VersionLine> {
        return src.map { it -> VersionLine(it) }.toTypedArray();
    }

    @Test
    fun testCase1() {
        val src = _createSourceLines(arrayOf("line1", "line2", "line3"));
        val ver = _createVersionLines(arrayOf("line1", "line2", "line3"));

        FindPositions(src, ver);

        assertEquals(0, ver[0].sourcePosition);
        assertEquals(1, ver[1].sourcePosition);
        assertEquals(2, ver[2].sourcePosition);
    }

    @Test
    fun testCase2() {
        val src = _createSourceLines(arrayOf("line1", "line2", "line3"));
        var ver = _createVersionLines(arrayOf("line1", "line2", "line3", "line4"));

        FindPositions(src, ver);

        assertEquals(0, ver[0].sourcePosition);
        assertEquals(1, ver[1].sourcePosition);
        assertEquals(2, ver[2].sourcePosition);
        assertEquals(-1, ver[3].sourcePosition);
    }

    @Test
    fun testCase3() {
        val src = _createSourceLines(arrayOf("line1", "line3", "line4"));
        var ver = _createVersionLines(arrayOf("line1", "line2", "line3", "line4"));

        FindPositions(src, ver);

        assertEquals(0, ver[0].sourcePosition);
        assertEquals(-1, ver[1].sourcePosition);
        assertEquals(1, ver[2].sourcePosition);
        assertEquals(2, ver[3].sourcePosition);
    }

    @Test
    fun testCase4() {
        val src = _createSourceLines(arrayOf("line1", "line2", "line3", "line4"));
        var ver = _createVersionLines(arrayOf("line1", "line2", "line01", "line02", "line3", "line4"));

        FindPositions(src, ver);

        assertEquals(0, ver[0].sourcePosition);
        assertEquals(1, ver[1].sourcePosition);
        assertEquals(-1, ver[2].sourcePosition);
        assertEquals(-1, ver[3].sourcePosition);
        assertEquals(2, ver[4].sourcePosition);
        assertEquals(3, ver[5].sourcePosition);
    }

    @Test
    fun testCase5() {
        val src = _createSourceLines(arrayOf());
        var ver = _createVersionLines(arrayOf("line1", "line2"));

        FindPositions(src, ver);

        assertEquals(-1, ver[0].sourcePosition);
        assertEquals(-1, ver[1].sourcePosition);
    }

    @Test
    fun testCase6() {
        val src = _createSourceLines(arrayOf("line1", "line2", "line3"));
        var ver = _createVersionLines(arrayOf("line01", "line2", "line3"));

        FindPositions(src, ver);

        assertEquals(-1, ver[0].sourcePosition);
        assertEquals(1, ver[1].sourcePosition);
        assertEquals(2, ver[2].sourcePosition);
    }

    @Test
    fun testCase7() {
        val src = _createSourceLines(arrayOf("line1", "line2"));
        var ver = _createVersionLines(arrayOf("line1", "line2", "line2"));

        FindPositions(src, ver);

        assertEquals(0, ver[0].sourcePosition);
        assertEquals(-1, ver[1].sourcePosition);
        assertEquals(1, ver[2].sourcePosition);
    }

    @Test
    fun testCase8() {
        val src = _createSourceLines(arrayOf("{", "class Test{", "\n", "}", "}"));
        var ver = _createVersionLines(arrayOf("{", "class Test{", "public void Func1(){}", "}", "}"));

        FindPositions(src, ver);

        assertEquals(0, ver[0].sourcePosition);
        assertEquals(1, ver[1].sourcePosition);
        assertEquals(-1, ver[2].sourcePosition);
        assertEquals(3, ver[3].sourcePosition);
        assertEquals(4, ver[4].sourcePosition);
    }

    @Test
    fun testCase9() {
        val src = _createSourceLines(arrayOf("{", "\t{", "\t}", "}"));
        var ver = _createVersionLines(arrayOf("{", "\t{", "something", "\t}", "}"));

        FindPositions(src, ver);

        assertEquals(0, ver[0].sourcePosition);
        assertEquals(1, ver[1].sourcePosition);
        assertEquals(-1, ver[2].sourcePosition);
        assertEquals(2, ver[3].sourcePosition);
        assertEquals(3, ver[4].sourcePosition);
    }

    @Test
    fun testCase10() {
        val src = _createSourceLines(arrayOf(
                "{",
                "class Test{",
                "\n",
                "}",
                "}"
        ));
        var ver = _createVersionLines(arrayOf(
                "{",
                "class Test{",
                "public void Func1(){",
                "}",
                "}",
                "}"
        ));

        FindPositions(src, ver);

        assertEquals(0, ver[0].sourcePosition);
        assertEquals(1, ver[1].sourcePosition);
        assertEquals(-1, ver[2].sourcePosition);
        assertEquals(-1, ver[3].sourcePosition);
        assertEquals(3, ver[4].sourcePosition);
        assertEquals(4, ver[5].sourcePosition);
    }
}