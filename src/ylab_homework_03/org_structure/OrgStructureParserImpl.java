package ylab_homework_03.org_structure;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrgStructureParserImpl implements OrgStructureParser {

    @Override
    public Employee parseStructure(File csvFile) {
        List<String> parseStrings = parseFromFile(csvFile);
        List<Employee> employeeList = parseEmployees(parseStrings);
        return getBoss(employeeList);
    }

    private Employee getBoss(List<Employee> employeeList) {
        for (Employee employee : employeeList) {
            if (employee.getBossId() == null) {
                return employee;
            }
        }
        return null;
    }

    private List<Employee> parseEmployees(List<String> parseStrings) {
        List<Employee> employeeList = new ArrayList<>();

        for (String str : parseStrings) {
            String[] arr = str.split(";");
            Employee employee = new Employee(
                    Long.parseLong(arr[0]),
                    arr[1].isEmpty() ? null : Long.parseLong(arr[1]),
                    arr[2],
                    arr[3]

            );
            employeeList.add(employee);
        }

        Map<Long, Employee> employeeMap = employeeList.stream()
                .collect(Collectors.toMap(Employee::getId, Function.identity()));

        Map<Long, List<Employee>> bossEmMap = employeeList.stream().
                filter(employee -> employee.getBossId() != null)
                .collect(Collectors.groupingBy(Employee::getBossId));


        employeeList.forEach(employee -> {
            Long bossId = employee.getBossId();
            if (bossId != null) {
                Employee boss = employeeMap.get(bossId);
                if (boss != null) {
                    employee.setBoss(boss);
                }
            }
            employee.setSubordinate(bossEmMap.get(employee.getId()));
        });

        return employeeList;
    }


    private List<String> parseFromFile(File csvFile) {
        String line;
        List<String> parseStrings = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty() && !line.startsWith("id")) {
                    parseStrings.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parseStrings;
    }
}
