package ru.mipt.bit.paltformer.entity;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.mipt.bit.platformer.controller.DirectionKeyBoardAction;
import ru.mipt.bit.platformer.entity.Tank;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ DirectionKeyBoardAction.class })
public class TankTest {

    @Mock
    private GameEntity gameEntity;
    @Mock
    private DirectionKeyBoardAction action;

    private Tank tank;

    private final List<GameEntity> gameEntities = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);

        tank = new Tank(new GridPoint2(0, 0));
    }

    @Test
    public void givenMoveTo_whenNewCoordinates_thenUpdateDestinationCoordinates() {
        GridPoint2 newCoordinates = new GridPoint2(1, 1);

        tank.moveTo(newCoordinates);

        assertEquals(newCoordinates, tank.getDestinationCoordinates());
        assertEquals(0, tank.getMovementProgress(), 0.001);
    }

    @Test
    public void givenUpdateState_whenMovementCompleted_thenSetCurrentCoordinatesToDestination() {
        GridPoint2 newCoordinates = new GridPoint2(1, 1);
        tank.moveTo(newCoordinates);

        tank.updateState(1.0f);

        assertEquals(newCoordinates, tank.getCoordinates());
    }

    @Test
    public void testUpdatePlayerMovement_ActionNotNullAndMovementComplete_ShouldMoveToNewCoordinates() {
        when(action.apply(any(GridPoint2.class))).thenReturn(new GridPoint2(1, 1));
        when(action.getRotation()).thenReturn(90f);

        tank.updateState(1f);

        tank.updatePlayerMovement(action, gameEntities, 1f);

        assertEquals(new GridPoint2(1, 1), tank.getDestinationCoordinates());
        assertEquals(90f, tank.getRotation(), 0.001f);
    }

}

