package ru.mipt.bit.paltformer.controller;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import ru.mipt.bit.platformer.controller.DirectionKeyBoardAction;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DirectionKeyBoardActionTest {

    private final DirectionKeyBoardAction direction;
    private final GridPoint2 startPoint;
    private final GridPoint2 expected;

    public DirectionKeyBoardActionTest(DirectionKeyBoardAction direction, GridPoint2 startPoint, GridPoint2 expected) {
        this.direction = direction;
        this.startPoint = startPoint;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {DirectionKeyBoardAction.UP, new GridPoint2(1, 1), new GridPoint2(1, 2)},
                {DirectionKeyBoardAction.DOWN, new GridPoint2(1, 1), new GridPoint2(1, 0)},
                {DirectionKeyBoardAction.RIGHT, new GridPoint2(1, 1), new GridPoint2(2, 1)},
                {DirectionKeyBoardAction.LEFT, new GridPoint2(1, 1), new GridPoint2(0, 1)}
        });
    }

    @Test
    public void testDirectionMoves() {
        GridPoint2 result = direction.apply(startPoint);
        assertEquals(expected, result);
    }
}