package ru.mipt.bit.platformer.controller.interfaces;

import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;

public interface ShootingAction extends Action {

    void execute(PlayerEntity tankEntity);

}
