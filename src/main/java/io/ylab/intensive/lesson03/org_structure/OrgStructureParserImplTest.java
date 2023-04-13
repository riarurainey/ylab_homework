package io.ylab.intensive.lesson03.org_structure;

import java.io.File;
import java.io.IOException;

public class OrgStructureParserImplTest {

    public static void main(String[] args) throws IOException {
        OrgStructureParser parser = new OrgStructureParserImpl();

        Employee boss = parser.parseStructure(new File("src/ylab_homework_03.org_structure/resources/employees.csv"));
        System.out.println(boss);
        System.out.println("Прямые подчиненные босса: " + boss.getSubordinate());

    }
}
