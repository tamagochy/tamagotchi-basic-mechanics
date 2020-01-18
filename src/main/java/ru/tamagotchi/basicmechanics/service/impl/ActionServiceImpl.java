package ru.tamagotchi.basicmechanics.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.tamagotchi.basicmechanics.dao.ActionDao;
import ru.tamagotchi.basicmechanics.domain.Action;
import ru.tamagotchi.basicmechanics.service.api.ActionService;

import java.util.List;

/**
 * Created by makar
 * 09.10.2018 18:38
 */
@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionDao dao;

    @Override
    public List<Action> getActions() {
        return dao.findAll();
    }
}
