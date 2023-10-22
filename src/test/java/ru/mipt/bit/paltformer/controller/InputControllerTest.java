package ru.mipt.bit.paltformer.controller;

import com.badlogic.gdx.math.GridPoint2;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.mipt.bit.platformer.controller.DirectionKeyBoardAction;
import ru.mipt.bit.platformer.entity.Tank;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class InputControllerTest {

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
    public void testPlayerDoesNotMove_WhenSpotIsOccupied() {
        when(action.apply(eq(new GridPoint2(0, 0)))).thenReturn(new GridPoint2(1, 1));
        when(action.getRotation()).thenReturn(90f);

        GameEntity occupiedEntity = mock(GameEntity.class);
        when(occupiedEntity.getCoordinates()).thenReturn(new GridPoint2(1, 1));
        gameEntities.add(occupiedEntity);

        tank.updatePlayerMovement(action, gameEntities, 1f);

        assertEquals(new GridPoint2(0, 0), tank.getDestinationCoordinates());
    }

    @Test
    public void testPlayerMoves_WhenAllConditionsMet() {
        when(action.apply(eq(new GridPoint2(0, 0)))).thenReturn(new GridPoint2(1, 1));
        when(action.getRotation()).thenReturn(90f);

        tank.updatePlayerMovement(action, gameEntities, 1f);

        assertEquals(new GridPoint2(1, 1), tank.getDestinationCoordinates());
        assertEquals(90f, tank.getRotation(), 0.001f);
    }

    @Test
    public void testPlayerDoesNotMove_WhenPlayerEntityOccupiesSpace() {
        when(action.apply(eq(new GridPoint2(0, 0)))).thenReturn(new GridPoint2(2, 2));

        PlayerEntity playerEntity = mock(PlayerEntity.class);
        when(playerEntity.getDestinationCoordinates()).thenReturn(new GridPoint2(2, 2));

        gameEntities.add(playerEntity);

        tank.updatePlayerMovement(action, gameEntities, 1f);

        assertEquals(new GridPoint2(0, 0), tank.getDestinationCoordinates());
    }

    @Test
    public void testPlayerDoesNotMove_WhenGameEntityOccupiesSpace() {
        when(action.apply(eq(new GridPoint2(0, 0)))).thenReturn(new GridPoint2(3, 3));

        GameEntity occupiedEntity = mock(GameEntity.class);
        when(occupiedEntity.getCoordinates()).thenReturn(new GridPoint2(3, 3));

        gameEntities.add(occupiedEntity);

        tank.updatePlayerMovement(action, gameEntities, 1f);

        assertEquals(new GridPoint2(0, 0), tank.getDestinationCoordinates());
    }

    @Test
    public void testPlayerMoves_WhenNoEntityOccupiesSpace() {
        when(action.apply(eq(new GridPoint2(0, 0)))).thenReturn(new GridPoint2(4, 4));

        tank.updatePlayerMovement(action, gameEntities, 1f);

        assertEquals(new GridPoint2(4, 4), tank.getDestinationCoordinates());
    }
}



