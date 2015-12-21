import com.vvv.merge.Merger
import org.junit.Test
import kotlin.test.assertEquals

class MergeTest{
    @Test
    fun testCase1() {
        val src = arrayOf("line1", "line2", "line3")
        val ver1 = arrayOf("line1", "line2", "line3")
        val ver2 = arrayOf("line1", "line2", "line3")

        val merger = Merger(src, ver1, ver2)
        var result = merger.Merge();

        assertEquals("line1", result[0])
        assertEquals("line2", result[1])
        assertEquals("line3", result[2])
    }

    @Test
    fun testCase2() {
        val src = arrayOf("line1", "line2", "line3")
        val ver1 = arrayOf("line1", "line2", "line3", "line4")
        val ver2 = arrayOf("line1", "line2", "line3")

        val merger = Merger(src, ver1, ver2)
        var result = merger.Merge();

        assertEquals("line1", result[0])
        assertEquals("line2", result[1])
        assertEquals("line3", result[2])
        assertEquals("line4", result[3])
    }

    @Test
    fun testCase7() {
        val src = arrayOf(
                "{",
                "class Test{",
                "\n",
                "}",
                "}"
        )
        val ver1 = arrayOf(
                "{",
                "class Test{",
                "public void Func1(){",
                "}",
                "}",
                "}"
        )
        val ver2 = arrayOf(
                "{",
                "class Test{",
                "public void Func2(){",
                "}",
                "}",
                "}"
        )

        val merger = Merger(src, ver1, ver2)
        var result = merger.Merge();

        assertEquals("{", result[0]);
        assertEquals("class Test{", result[1]);
        assertEquals("public void Func1(){", result[2]);
        assertEquals("}", result[3]);
        assertEquals("public void Func2(){", result[4]);
        assertEquals("}", result[5]);
        assertEquals("}", result[6]);
        assertEquals("}", result[7]);
    }
}
