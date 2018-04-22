package com.ravi.home;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Scanner;

public class KNNStat {
	
	public static void main(String[] args) throws FileNotFoundException {
		//Scanner scanner = new Scanner(new File("/Users/ravirane/Desktop/GMU/CS584/myWork/hw3/GoodBad.csv"));
		Scanner scanner = new Scanner(new File("./GoodBad.csv"));
		double tp = 0;
		double tn = 0;
		double fp = 0;
		double fn = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String parts[] = line.split(",");
			String actual = parts[parts.length - 2];
			String predicted = parts[parts.length - 1];
			if(actual != null && predicted != null) {
				if(actual.equalsIgnoreCase(predicted)) {
					if(actual.equalsIgnoreCase("GOOD")) {
						tp++;
					} else {
						tn++;
					}
				} else {
	                if(actual.equalsIgnoreCase("GOOD")) {
						fn++;
					} else {
						fp++;
					}
				}	
			}
		}
		printResult(tp, tn, fp, fn);
		scanner.close();
	}

	private static void printResult(double TP, double TN, double FP, double FN) {
		double accuracy = (TP+TN)/(TP+FP+FN+TN);
		double precision = TP/(TP+FP);
		double recall = TP/(TP+FN);
		double f1 = 2*(recall * precision) / (recall + precision);
		try {
            //File statText = new File("/Users/ravirane/Desktop/GMU/CS584/myWork/hw3/result.txt");
			File statText = new File("./result.txt");
            FileOutputStream is = new FileOutputStream(statText);
            OutputStreamWriter osw = new OutputStreamWriter(is);    
            Writer w = new BufferedWriter(osw);
            w.write("-------------------" + "\n");
            w.write("TP - " + TP + "\n");
            w.write("TN - " + TN + "\n");
            w.write("FP - " + FP + "\n");
            w.write("FN - " + FN + "\n");
            w.write("-------------------" + "\n");
            w.write("Accuracy - " + accuracy + "\n");
            w.write("Precision - " + precision + "\n");
            w.write("Recall - " + recall + "\n");
            w.write("F1 - " + f1 + "\n");
            w.write("-------------------" + "\n");
            w.close();
        } catch (IOException e) {
            System.err.println("Problem writing to the file statsTest.txt");
        }
	}

}
