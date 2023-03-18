package org_structure;

import java.io.File;
import java.io.IOException;

public class OrgStructureParserImplTest {
    public static void main(String[] args) throws IOException {
        OrgStructureParser parser = new OrgStructureParserImpl();

        parser.parseStructure(new File("src/org_structure/resources/employees.csv"));

    }
}
