package ru.mipt.bit.platformer.controller.interfaces;

import ru.mipt.bit.platformer.controller.DirectionKeyBoardAction;

public interface ActionVisitor {

    org.awesome.ai.Action visit(DirectionKeyBoardAction directionAction);
}
