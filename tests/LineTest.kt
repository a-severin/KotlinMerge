import com.vvv.merge.Line
import org.junit.Assert.*
import org.junit.Test

class LineTest{
    @Test
    fun testContstructor() {
        val line = Line("test ");

        assertTrue(line.content == "test ");
        assertTrue(line.trimedContent == "test");
    }
}