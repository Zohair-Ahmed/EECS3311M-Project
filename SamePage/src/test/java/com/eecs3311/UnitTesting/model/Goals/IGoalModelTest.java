package com.eecs3311.UnitTesting.model.Goals;

import com.eecs3311.model.Goals.GoalModel;
import com.eecs3311.model.Goals.IGoalModel;
import com.eecs3311.persistence.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GoalModelTest {

    @BeforeEach
    void setup(){
        Database.setIsUsingStubDB(true);
    }

    @Test
    void testGoalAttributes(){
        IGoalModel goal1 = new GoalModel(1);
        assertEquals(goal1.getGoalInfo(), "0 / 10");
        assertEquals(goal1.getLevel(), 1);
        assertEquals(goal1.getUID(), 1);
        assertEquals(goal1.getNumOfBooksRead(), 0);
        assertEquals(goal1.getGoalInfo(), goal1.getNumOfBooksRead()+" / "+goal1.getLevel()*10);
    }

    @AfterEach
    void cleanUp(){}

}