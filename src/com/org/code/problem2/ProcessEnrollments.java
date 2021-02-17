package com.org.code.problem2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Class process the csv files and separate the enrollees by the provider
 * 
 * ex: <<provider>>_specific_data.csv
 * 
 * @author sjeejula
 *
 */
public class ProcessEnrollments {

	public static void main(String[] args) {
		String datadir = "/Users/sree/eclipse-workspace/Excercises/src/resouces/";

		List<String> files = getFiles(datadir + "input/");

		if (files.size() > 0) {
			processFiles(files, datadir + "output/");
		} else {
			log("No files process at this time.");
		}
	}

	private static List<String> getFiles(String datadir) {
		log("Started getting the files");

		List<String> files = new ArrayList<String>();
		String endswithText = ".csv";

		log(String.format("Retrieving all the csv files from %s", datadir));
		try {
			Stream<Path> p = Files.list(Paths.get(datadir)).filter(path -> path.toString()
					.substring(path.toString().lastIndexOf(File.separator) + 1).endsWith(endswithText));
			p.forEach(file -> files.add(file.toString()));
		} catch (IOException e) {
			String msg = String.format("Error occured while retrieving the files from :%s. Error is %s", datadir,
					e.getMessage());
			log(msg);
		}

		return files;
	}

	private static void processFiles(List<String> files, String datadir) {
		log(String.format("No. of files to process are:%s", files.size()));

		for (String fileName : files) {
			try {
				log("Reading the file:" + fileName);

				List<Enrollee> elist = new ArrayList<Enrollee>();

				Stream<String> lines = null;
				try {
					lines = Files.lines(Paths.get(fileName));

					Stream<Object> f = lines.map(line -> line.split(","));
					f.forEach(p1 -> {
						Object[] p = (Object[]) p1;
						elist.add(new Enrollee(p[0].toString(), p[1].toString(), p[2].toString(), p[3].toString(),
								p[4].toString()));
					});
				} finally {
					lines.close();
				}

				log("Total no. of enrolless are :" + elist.size());

				Map<String, List<Enrollee>> m1 = elist.stream().collect(Collectors.groupingBy(Enrollee::getInsCompany));
				m1.forEach((k, v) -> {
					String ofile = datadir + k + "_specific_enrollees.csv";
					log("Creating file with filename:" + ofile);
					Path path = Paths.get(ofile);
					try (BufferedWriter writer = Files.newBufferedWriter(path)) {
						Collection<Enrollee> v1 = v.stream().collect(Collectors.groupingBy(Enrollee::getUserId,
								Collectors.collectingAndThen(
										Collectors.maxBy(Comparator.comparing(Enrollee::getVersion)), Optional::get)))
								.values();
						v1.stream().sorted(new EnrolleeSort()).collect(Collectors.toList()).forEach(enrollee -> {
							try {
								writer.write(enrollee.toString() + "\n");
							} catch (Exception e) {
								log(String.format("Error occured while writing data into file. " + "Error is :%s ",
										e.getMessage()));
							}
						});
					} catch (Exception e) {
						log(e.toString());
					}
				});
			} catch (IOException e) {
				String msg = String.format("Error occured while processingthe file %s. Error is %s", fileName,
						e.getMessage());
				log(msg);
			}
		}
	}

	private static void log(String msg) {
		System.out.println(msg);
	}
}
