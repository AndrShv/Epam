package org.example;

class Report {
    public void generate() {
        System.out.println("Generating report...");
    }
}

class ReportPrinter {
    public void print(Report report) {
        System.out.println("Printing report...");

    }
}

class ReportSaver {
    public void save(Report report) {
        System.out.println("Saving report...");
    }
}

public class SingleResponsibilityPrinciple {
    public static void main(String[] args) {
        Report report = new Report();
        ReportPrinter printer = new ReportPrinter();
        ReportSaver saver = new ReportSaver();

        report.generate();
        printer.print(report);
        saver.save(report);
    }
}
