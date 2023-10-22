package ru.mipt.bit.platformer.mapper;

import org.awesome.ai.state.GameState;
import org.awesome.ai.state.immovable.Obstacle;
import org.awesome.ai.state.movable.Bot;
import org.awesome.ai.state.movable.Orientation;
import org.awesome.ai.state.movable.Player;
import ru.mipt.bit.platformer.entity.Tank;
import ru.mipt.bit.platformer.entity.Tree;
import ru.mipt.bit.platformer.entity.interfces.ObstacleEntity;
import ru.mipt.bit.platformer.entity.interfces.PlayerEntity;
import ru.mipt.bit.platformer.mapper.interfaces.BotEntityMapper;
import ru.mipt.bit.platformer.mapper.interfaces.ObstacleEntityMapper;
import ru.mipt.bit.platformer.mapper.interfaces.PlayerEntityMapper;

import java.util.List;
import java.util.stream.Collectors;

public class GameStateMapper {

    private static final PlayerEntityMapper playerMapper = new TankToPlayerMapper();
    private static final BotEntityMapper botMapper = new TankToBotMapper();
    private static final ObstacleEntityMapper obstacleMapper = new TreeToObstacleMapper();

    public static GameState mapToGameState(List<ObstacleEntity> trees, PlayerEntity playerTank, List<PlayerEntity> botTanks, int levelWidth, int levelHeight) {
        List<Obstacle> obstacles = trees.stream()
                .map(obstacleMapper::mapToObstacle)
                .collect(Collectors.toList());

        List<Bot> bots = botTanks.stream()
                .map(botMapper::mapToBot)
                .collect(Collectors.toList());

        Player player = playerMapper.mapToPlayer(playerTank);

        return GameState.builder()
                .obstacles(obstacles)
                .bots(bots)
                .player(player)
                .levelWidth(levelWidth)
                .levelHeight(levelHeight)
                .build();
    }
}
