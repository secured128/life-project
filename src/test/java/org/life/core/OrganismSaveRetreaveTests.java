package org.life.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import life.organism.OrganismUtil;
import life.organism.bacteria.TestBacteria;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;


public class OrganismSaveRetreaveTests extends AbstractTest {

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
