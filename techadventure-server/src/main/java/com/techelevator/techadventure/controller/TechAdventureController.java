package com.techelevator.techadventure.controller;

import com.techelevator.techadventure.dao.JdbcUserDao;

import java.math.BigDecimal;
import java.security.Principal;

import org.springframework.web.bind.annotation.*;

import com.techelevator.techadventure.dao.InteractionDao;
import com.techelevator.techadventure.dao.UserDao;

public class TechAdventureController {

    private UserDao userDao;
    private InteractionDao interactionDao;

    public TechAdventureController(UserDao userDao, InteractionDao ineInteractionDao) {
        this.userDao = userDao;
        this.interactionDao = ineInteractionDao;
    }

    @RequestMapping(path = "/points", method = RequestMethod.GET)
    public BigDecimal getPoints(Principal principal)
    {
        return userDao.getPoints(principal.getName());
    }

}
