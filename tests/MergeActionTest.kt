import com.vvv.merge.*
import org.junit.Assert.*
import org.junit.Test

class MergeActionTest{
    @Test
    fun testConstructor() {
        val action = MergeAction(0);
        assertTrue(action.mergePosition == 0);
        assertTrue(action.lines.size == 0);
    }

    @Test
    fun testisEqualContent() {
        val action = MergeAction(0);
        val equalAction = MergeAction(0);
        action.lines.add(Line("test"));
        equalAction.lines.add(Line("test"));

        val notEqualAction1 = MergeAction(1);
        notEqualAction1.lines.add(Line("test"));

        val notEqualAction2 = MergeAction(0);
        notEqualAction1.lines.add(Line("test"));
        notEqualAction1.lines.add(Line("test1"));

        assertTrue(action.isEqualContents(equalAction));
        assertFalse(action.isEqualContents(notEqualAction1));
        assertFalse(action.isEqualContents(notEqualAction2));
    }
}