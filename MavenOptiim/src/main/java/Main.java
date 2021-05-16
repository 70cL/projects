import Comparator.ExcelComparator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ExcelComparator comparator = new ExcelComparator();

        comparator.read();
        comparator.write();
    }
}
