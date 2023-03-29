package com.eecs3311.model.Goals;

import com.eecs3311.persistence.Goals.GoalStub;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GoalModelTest {

    @BeforeEach
    void setup(){}

    @Test
    void testGoalAttributes(){
        IGoalModel goal1 = GoalStub.getInstance().getGoalDB().get(0);
        assertEquals(goal1.getGoalInfo(), "0 / 10");
        assertEquals(goal1.getLevel(), 1);
        assertEquals(goal1.getUID(), 1);
        assertEquals(goal1.getNumOfBooksReads(), 0);
        assertEquals(goal1.getGoalInfo(), goal1.getNumOfBooksReads()+" / "+goal1.getLevel()*10);
    }

    @AfterEach
    void cleanUp(){}

}
