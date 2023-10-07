package ru.mipt.bit.paltformer.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.mipt.bit.platformer.controller.InputController;
import ru.mipt.bit.platformer.controller.DirectionKeyBoardAction;
import ru.mipt.bit.platformer.controller.ShootingAction;
import ru.mipt.bit.platformer.controller.interfaces.Action;
import ru.mipt.bit.platformer.entity.interfces.GameEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class InputControllerTest {

    @Mock
    private PlayerEntity playerEntity;
    @Mock
    private Action action;

    private final InputController inputController = new InputController();
    private final List<GameEntity> gameEntities = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenExecuteTriggeredActions_whenActionTriggered_thenExecute() throws NoSuchFieldException, IllegalAccessException {
        when(action.isTriggered()).thenReturn(true);

        Field field = InputController.class.getDeclaredField("actions");
        field.setAccessible(true);

        List<Action> actions = (List<Action>) field.get(inputController);
        actions.add(action);

        inputController.checkIsTriggeredKeyAndExecuteCommand(playerEntity, gameEntities, 1f);

        verify(action).execute(playerEntity, gameEntities, 1f);
    }

    @Test
    public void whenInitActions_thenShouldInitializeActions() throws NoSuchFieldException, IllegalAccessException {
        inputController.initActions();

        Field actionsField = InputController.class.getDeclaredField("actions");
        actionsField.setAccessible(true);

        List<Action> actions = (List<Action>) actionsField.get(inputController);

        int expectedActionCount = DirectionKeyBoardAction.values().length + ShootingAction.values().length;

        assertEquals(expectedActionCount, actions.size());
    }
}

