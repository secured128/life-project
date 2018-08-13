package org.life.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import life.CoreApplication;
import life.organism.OrganismUtil;
import life.organism.bacteria.TestBacteria;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * Created by amnona on 6/22/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CoreApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrganismSaveRetreaveTests {

    private static final File srcDnaTextFile = new File(OrganismSaveRetreaveTests.class.getClassLoader().getResource("TestOrganismTemplateDnaStrand.txt").getFile());
    private static final File srcJsonFile = new File(OrganismSaveRetreaveTests.class.getClassLoader().getResource("TestOrganism.json").getFile());

    @Test
    public void saveAndRetrieveOrganizm() throws Exception {
        TestBacteria testBacteria = new ObjectMapper().readValue(srcJsonFile, TestBacteria.class);
        OrganismUtil.saveOrganism(testBacteria, srcDnaTextFile);
        TestBacteria testBacteria1 = (TestBacteria) OrganismUtil.readOrganism(TestBacteria.class);
        Assert.assertTrue("Organisms should be identical", testBacteria.equals(testBacteria1));
    }


}
