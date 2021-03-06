package com.codecool.scc;

import com.codecool.scc.config.AppContext;
import com.codecool.scc.services.SimpleCsvConverter;
import com.codecool.scc.view.OutputFormat;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;

public class ConverterApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(AppContext.class);
        SimpleCsvConverter scc = (SimpleCsvConverter) appContext.getBean("simpleCsvConverter");
        OutputFormat outputFormat;
        File file;

        switch (args.length) {

            case 0:
                System.out.println("No input file defined");
                break;

            case 1:
                file = new File(args[0]);
                scc.convert(file);
                break;

            case 2:
                try {
                    outputFormat = OutputFormat.valueOf(args[0].toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid format type. Enter json, xml or table");
                    break;
                }
                file = new File(args[1]);
                scc.convert(file, outputFormat);
                break;

            default:
                System.out.println("Invalid number of arguments. Enter only one or two arguments");
        }

    }
}
