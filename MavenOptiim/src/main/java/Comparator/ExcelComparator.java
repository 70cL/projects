package Comparator;

import EmployeeInfo.Person;
import Log.LogInfo;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ExcelComparator {

    private final String [] firstRow = {"AD", "SOYAD", "DOĞUM TARİHİ", "DOĞUM YERİ", "MAİL",
            "TELEFON", "DURUM", "ÇALIŞMA DURUMU", "ÜNİVERSİTE"};

    private final LogInfo logInfo = new LogInfo();
    private List<Person> personList;
    private List<Person> personList2;

    public void read()
    {
        try (
                InputStream excel1 = new FileInputStream("InputData//Excel1.xlsx");
                InputStream excel2 = new FileInputStream("InputData//Excel2.xlsx")
        )
        {
            personList = new ArrayList<>();
            personList2 = new ArrayList<>();

            Workbook wb1 = WorkbookFactory.create(excel1);
            Workbook wb2 = WorkbookFactory.create(excel2);
            Sheet sheet1 = wb1.getSheetAt(0);
            Sheet sheet2 = wb2.getSheetAt(0);

            listFiller(sheet1, sheet2);

        } catch (IOException e) {
            logInfo.error("Hata oluştu");

        }
    }

    private void listFiller(Sheet sheet1, Sheet sheet2) {

        int iterate;

        for(int i = 1; i <= sheet2.getLastRowNum(); i++) {

            int cellNum = sheet2.getRow(0).getLastCellNum();

            Row row2 = sheet2.getRow(i);

            ArrayList<Cell> cellArrayList = new ArrayList<>();

            iterate = 0;

            for(int k = 0; k < cellNum; k++) {

                Cell cell1 = row2.getCell(k , Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cellArrayList.add(cell1);

            }

            DataFormatter df = new DataFormatter();

            personList2.add(new Person(cellArrayList.get(iterate++).getStringCellValue(),
                    cellArrayList.get(iterate++).getStringCellValue(), cellArrayList.get(iterate++).getStringCellValue(),
                    cellArrayList.get(iterate++).getStringCellValue(), cellArrayList.get(iterate++).getStringCellValue(),
                    df.formatCellValue(cellArrayList.get(iterate++)),
                    cellArrayList.get(iterate++).getStringCellValue(), cellArrayList.get(iterate).getStringCellValue())
            );
        }

        logInfo.Infos("Excel 2 okundu.");

        logInfo.Infos(personList2.toString());

        logInfo.Infos("*********************************");

        for(int i = 1; i <= sheet1.getLastRowNum(); i++) {

            int cellNum = sheet1.getRow(0).getLastCellNum();

            Row row1 = sheet1.getRow(i);

            ArrayList<Cell> cellArrayList = new ArrayList<>();

            iterate = 0;

            for (int k = 0; k < cellNum; k++) {
                Cell cell1 = row1.getCell(k, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cellArrayList.add(cell1);
            }

            DataFormatter df = new DataFormatter();

            personList.add(new Person(cellArrayList.get(iterate++).getStringCellValue(),
                    cellArrayList.get(iterate++).getStringCellValue(), cellArrayList.get(iterate++).getStringCellValue(),
                    cellArrayList.get(iterate++).getStringCellValue(), df.formatCellValue(cellArrayList.get(iterate++)),
                    cellArrayList.get(iterate).getStringCellValue())
            );
        }

        logInfo.Infos("Excel 1 okundu.");

        logInfo.Infos(personList.toString());

        logInfo.Infos("*********************************");
    }

    public void write() throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet spreadsheet = workbook.createSheet( "Employee Info");

        try {
            fillTheFirstRow(spreadsheet);
        }

        catch (ArrayIndexOutOfBoundsException ex)
        {
            logInfo.error("ArrayIndex hatası");
        }

        try {
            createCells(spreadsheet);
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            logInfo.error("ArrayIndex hatası");
        }

        fillExcelWithPersons(spreadsheet);

        writeToExcel(workbook);


    }

    private void fillExcelWithPersons(XSSFSheet spreadsheet) {

        int rowNumber = 1;

        logInfo.Infos("Karşılaştırma başladı");

        for(int i = 0; i < personList.size(); i++)
        {
            for(int k = 0; k < personList2.size(); k++)
            {
                if(personList.get(i).getName().equals(personList2.get(k).getName()) && personList.get(i).getSurName().equals(personList2.get(k).getSurName())) {
                    if (personList.get(i).getMail().equals(personList2.get(k).getMail()) || personList.get(i).getPhone().equals(personList2.get(k).getPhone())) {

                        for(int j = 0; j < firstRow.length; j++)
                        {
                            String valueToAssign = personList.get(i).getPersonalInfoAsArray()[j].isEmpty() ?
                                    personList2.get(i).getPersonalInfoAsArray()[j] : personList.get(i).getPersonalInfoAsArray()[j];

                            if(valueToAssign.isEmpty())
                                valueToAssign = "Veri Bulunamadı";

                            spreadsheet.getRow(rowNumber).getCell(j).setCellValue(valueToAssign);
                        }

                        rowNumber++;

                    }
                }

            }
        }

        logInfo.Infos("Karşılaştırma bitti");
    }

    private void createCells(XSSFSheet spreadsheet) {
        XSSFRow row;
        for(int i = 1; i < personList.size() + 1; i++) {
            row = spreadsheet.createRow(i);
            for (int k = 0; k < firstRow.length; k++) {
                row.createCell(k).setCellType(CellType.STRING);
            }
        }

        logInfo.Infos("Hücreler yaratıldı");
    }

    private void fillTheFirstRow(XSSFSheet spreadsheet) {
        XSSFRow row;
        for(int i = 0; i < 1; i++) {
            row = spreadsheet.createRow(i);
            for (int k = 0; k < firstRow.length; k++) {
                Cell cell = row.createCell(k);
                cell.setCellValue(firstRow[k]);
            }
        }
    }

    private void writeToExcel(XSSFWorkbook workbook) throws IOException {

        if(!Files.isDirectory(Path.of("Report")))
        {
            Files.createDirectories(Path.of("Report"));
            logInfo.Infos("Dosya yolu yaratıldı");
        }

        logInfo.Infos("Dosya yolu mevcut");

        FileOutputStream out = new FileOutputStream(
                new File("Report//result.xlsx"));

        workbook.write(out);
        out.close();
        logInfo.Infos("Excele yazıldı");
    }
}
