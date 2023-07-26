package com.techelevator.techadventure.model;

import java.math.BigDecimal;

public class Interaction {

    //TODO create model for an Interaction between an employer and user, or event and user.
    
    private int transferID;
    private BigDecimal amount;
    private String senderName;
    private int senderUserID;
    private String destinationName;
    private int destinationUserID;
    private int status;
    private int type;
}
