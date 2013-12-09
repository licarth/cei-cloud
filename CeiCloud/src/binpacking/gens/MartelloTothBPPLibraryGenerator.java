package binpacking.gens;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;

import binpacking.BPP;
import binpacking.BPPInstance;
import common.generator.LibraryGenerator;
import common.problem.ProblemInputDataException;

public class MartelloTothBPPLibraryGenerator implements LibraryGenerator<BPP>{

	List<BPP> problems = new ArrayList<BPP>();
	
	@Override
	public List<BPPInstance> generateInstances() throws ProblemInputDataException {
		ArrayList<BPPInstance> instances = new ArrayList<BPPInstance>();

		// Directory path here
		String path = "./resources/instances_lib/MartelloToth";

		File folder = new File(path);
		File[] listOfFiles = folder.listFiles(); 
		Arrays.sort(listOfFiles);

		for (int i = 0; i < listOfFiles.length; i++) 
		{
			File file = listOfFiles[i];
			if (file.isFile()) 
			{
				instances.add(generateInstance(listOfFiles[i]));
			}
		}
		return instances;
	}

	private BPPInstance generateInstance(File file) throws ProblemInputDataException {

		ArrayList<Integer> itemSizes = new ArrayList<Integer>();
		BPP p = null;
		BPPInstance pInstance = null;
		
		String fileName = file.getName();
		if (fileName.endsWith(".bpp") || fileName.endsWith(".BPP"))
		{
			//Process file name to get problems parameters.
//			System.out.println(fileName);
			Pattern patt = Pattern.compile("^N([0-9])C([0-9])W([0-9])_([A-Z]).*");
			Matcher m = patt.matcher(fileName);

			int fileNameNumOfItems = 0;
			
			if (m.find()) {
//				System.out.println(m.group(1)+m.group(2)+m.group(3)+m.group(4));
				//Define the problem
				int binSize;
				switch (new Integer(m.group(2))) {
				case 1:
					binSize = 100;
					break;
				case 2:
					binSize = 120;
					break;
				case 3:
					binSize = 150;
					break;

				default:
					throw new ProblemInputDataException("Can't read bin size from file name.");
				}
				
				p = new BPP(binSize);
				p.setItemMaxSize(100);
				
				switch (new Integer(m.group(1))) {
				case 1:	fileNameNumOfItems = 50;	break;
				case 2:	fileNameNumOfItems = 100;	break;
				case 3:	fileNameNumOfItems = 200;	break;
				case 4:	fileNameNumOfItems = 500;	break;

				default:
					throw new ProblemInputDataException("Can't read number of items from file name.");
				}
				int z = new Integer(m.group(3));
				switch (z) {
				case 1:
					p.setItemMinSize(1);
					break;
				default:
					p.setItemMinSize(10*z);
					break;
				}
				if (problems.contains(p)){
					p = problems.get(problems.indexOf(p));
				}
			}

			//	Lis le fichier. chaque ligne = nouvel item.
			BufferedReader br;
			int headerNumOfItems = 0;
			try {
				br = new BufferedReader(new FileReader(file));
				String line;
				int lineCount = 1;
				while ((line = br.readLine()) != null) {
					int n = new Integer(line);
					if (lineCount == 1) {
						headerNumOfItems = n;
						if (headerNumOfItems != fileNameNumOfItems) {
							throw new ProblemInputDataException("Number of items in file name and number of items annouced in file header are different !"); 
						}
					}
					else if (lineCount == 2) {
//						br.close();
						if (p.getBinSize() != n) throw new ProblemInputDataException("Bin size in file and in file name are different !");
					}
					else itemSizes.add(new Integer(line));
					lineCount++;
				}
				if (headerNumOfItems != itemSizes.size()){
//					br.close();
					throw new ProblemInputDataException("Number of items in file name and number of items really in file are different !");
				}
				br.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				pInstance = new BPPInstance(p, itemSizes);
				pInstance.setName(fileName);
			} catch (ProblemInputDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
//		System.out.println(pInstance);
		return pInstance;
	}

}
