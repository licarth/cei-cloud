package binpacking.gens;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import VSCIFP.algs.Item;
import binpacking.BPP;
import binpacking.OptimalKnownBPPInstance;
import common.generator.LibraryGenerator;
import common.generator.OptimalLibraryGenerator;
import common.generator.OptimalRandomGenerator;
import common.problem.InputDataException;

/**
 * Generator that create instances described by Martello & Toth.
 * 
 * @author thomas
 *
 */
public class MartelloTothBPPLibraryGenerator extends OptimalLibraryGenerator<BPP, OptimalKnownBPPInstance>{

	public MartelloTothBPPLibraryGenerator(BPP problem) {
		super(problem);
		// TODO Auto-generated constructor stub
	}

	List<BPP> problems = new ArrayList<BPP>();
	
	@Override
	public List<OptimalKnownBPPInstance> generateInstances() throws InputDataException {
		ArrayList<OptimalKnownBPPInstance> instances = new ArrayList<OptimalKnownBPPInstance>();

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

	private OptimalKnownBPPInstance generateInstance(File file) throws InputDataException {

		ArrayList<Item> itemSizes = new ArrayList<>();
		BPP p = null;
		OptimalKnownBPPInstance pInstance = null;
		
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
					throw new InputDataException("Can't read bin size from file name.");
				}
				
				p = new BPP(binSize);
				p.setItemMaxSize(100);
				
				switch (new Integer(m.group(1))) {
				case 1:	fileNameNumOfItems = 50;	break;
				case 2:	fileNameNumOfItems = 100;	break;
				case 3:	fileNameNumOfItems = 200;	break;
				case 4:	fileNameNumOfItems = 500;	break;

				default:
					throw new InputDataException("Can't read number of items from file name.");
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
							throw new InputDataException("Number of items in file name and number of items annouced in file header are different !"); 
						}
					}
					else if (lineCount == 2) {
//						br.close();
						if (p.getBinSize() != n) throw new InputDataException("Bin size in file and in file name are different !");
					}
					else itemSizes.add(new Item(Integer.valueOf(line)));
					lineCount++;
				}
				if (headerNumOfItems != itemSizes.size()){
//					br.close();
					throw new InputDataException("Number of items in file name and number of items really in file are different !");
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
				pInstance = new OptimalKnownBPPInstance(p, itemSizes, optimals.get(fileName.split("\\.")[0]));
				pInstance.setName(fileName);
			} catch (InputDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
//		System.out.println(pInstance);
		return pInstance;
	}
	
	public static HashMap<String,Integer> optimals = new HashMap<String, Integer>(){{
		put("N1C1W1_A", 25);
		put("N1C1W1_B", 31);
		put("N1C1W1_C", 20);
		put("N1C1W1_D", 28);
		put("N1C1W1_E", 26);
		put("N1C1W1_F", 27);
		put("N1C1W1_G", 25);
		put("N1C1W1_H", 31);
		put("N1C1W1_I", 25);
		put("N1C1W1_J", 26);
		put("N1C1W1_K", 26);
		put("N1C1W1_L", 33);
		put("N1C1W1_M", 30);
		put("N1C1W1_N", 25);
		put("N1C1W1_O", 32);
		put("N1C1W1_P", 26);
		put("N1C1W1_Q", 28);
		put("N1C1W1_R", 25);
		put("N1C1W1_S", 28);
		put("N1C1W1_T", 28);
		put("N1C1W2_A", 29);
		put("N1C1W2_B", 30);
		put("N1C1W2_C", 33);
		put("N1C1W2_D", 31);
		put("N1C1W2_E", 36);
		put("N1C1W2_F", 30);
		put("N1C1W2_G", 30);
		put("N1C1W2_H", 33);
		put("N1C1W2_I", 35);
		put("N1C1W2_J", 34);
		put("N1C1W2_K", 35);
		put("N1C1W2_L", 31);
		put("N1C1W2_M", 30);
		put("N1C1W2_N", 33);
		put("N1C1W2_O", 29);
		put("N1C1W2_P", 33);
		put("N1C1W2_Q", 36);
		put("N1C1W2_R", 34);
		put("N1C1W2_S", 37);
		put("N1C1W2_T", 38);
		put("N1C1W4_A", 35);
		put("N1C1W4_B", 40);
		put("N1C1W4_C", 36);
		put("N1C1W4_D", 38);
		put("N1C1W4_E", 38);
		put("N1C1W4_F", 32);
		put("N1C1W4_G", 37);
		put("N1C1W4_H", 40);
		put("N1C1W4_I", 35);
		put("N1C1W4_J", 37);
		put("N1C1W4_K", 41);
		put("N1C1W4_L", 35);
		put("N1C1W4_M", 41);
		put("N1C1W4_N", 39);
		put("N1C1W4_O", 34);
		put("N1C1W4_P", 38);
		put("N1C1W4_Q", 34);
		put("N1C1W4_R", 38);
		put("N1C1W4_S", 36);
		put("N1C1W4_T", 42);
		put("N1C2W1_A", 21);
		put("N1C2W1_B", 26);
		put("N1C2W1_C", 23);
		put("N1C2W1_D", 21);
		put("N1C2W1_E", 17);
		put("N1C2W1_F", 22);
		put("N1C2W1_G", 21);
		put("N1C2W1_H", 23);
		put("N1C2W1_I", 27);
		put("N1C2W1_J", 27);
		put("N1C2W1_K", 24);
		put("N1C2W1_L", 25);
		put("N1C2W1_M", 26);
		put("N1C2W1_N", 21);
		put("N1C2W1_O", 15);
		put("N1C2W1_P", 21);
		put("N1C2W1_Q", 24);
		put("N1C2W1_R", 23);
		put("N1C2W1_S", 22);
		put("N1C2W1_T", 22);
		put("N1C2W2_A", 24);
		put("N1C2W2_B", 27);
		put("N1C2W2_C", 29);
		put("N1C2W2_D", 24);
		put("N1C2W2_E", 33);
		put("N1C2W2_F", 26);
		put("N1C2W2_G", 29);
		put("N1C2W2_H", 23);
		put("N1C2W2_I", 25);
		put("N1C2W2_J", 25);
		put("N1C2W2_K", 29);
		put("N1C2W2_L", 30);
		put("N1C2W2_M", 30);
		put("N1C2W2_N", 26);
		put("N1C2W2_O", 29);
		put("N1C2W2_P", 23);
		put("N1C2W2_Q", 30);
		put("N1C2W2_R", 25);
		put("N1C2W2_S", 24);
		put("N1C2W2_T", 26);
		put("N1C2W4_A", 29);
		put("N1C2W4_B", 32);
		put("N1C2W4_C", 30);
		put("N1C2W4_D", 28);
		put("N1C2W4_E", 30);
		put("N1C2W4_F", 32);
		put("N1C2W4_G", 30);
		put("N1C2W4_H", 30);
		put("N1C2W4_I", 35);
		put("N1C2W4_J", 30);
		put("N1C2W4_K", 32);
		put("N1C2W4_L", 31);
		put("N1C2W4_M", 31);
		put("N1C2W4_N", 32);
		put("N1C2W4_O", 30);
		put("N1C2W4_P", 28);
		put("N1C2W4_Q", 33);
		put("N1C2W4_R", 35);
		put("N1C2W4_S", 38);
		put("N1C2W4_T", 29);
		put("N1C3W1_A", 16);
		put("N1C3W1_B", 16);
		put("N1C3W1_C", 17);
		put("N1C3W1_D", 19);
		put("N1C3W1_E", 16);
		put("N1C3W1_F", 20);
		put("N1C3W1_G", 15);
		put("N1C3W1_H", 19);
		put("N1C3W1_I", 17);
		put("N1C3W1_J", 16);
		put("N1C3W1_K", 17);
		put("N1C3W1_L", 17);
		put("N1C3W1_M", 17);
		put("N1C3W1_N", 20);
		put("N1C3W1_O", 16);
		put("N1C3W1_P", 19);
		put("N1C3W1_Q", 20);
		put("N1C3W1_R", 21);
		put("N1C3W1_S", 16);
		put("N1C3W1_T", 18);
		put("N1C3W2_A", 19);
		put("N1C3W2_B", 20);
		put("N1C3W2_C", 22);
		put("N1C3W2_D", 20);
		put("N1C3W2_E", 21);
		put("N1C3W2_F", 23);
		put("N1C3W2_G", 23);
		put("N1C3W2_H", 23);
		put("N1C3W2_I", 19);
		put("N1C3W2_J", 22);
		put("N1C3W2_K", 21);
		put("N1C3W2_L", 21);
		put("N1C3W2_M", 21);
		put("N1C3W2_N", 22);
		put("N1C3W2_O", 21);
		put("N1C3W2_P", 18);
		put("N1C3W2_Q", 19);
		put("N1C3W2_R", 19);
		put("N1C3W2_S", 21);
		put("N1C3W2_T", 22);
		put("N1C3W4_A", 21);
		put("N1C3W4_B", 22);
		put("N1C3W4_C", 24);
		put("N1C3W4_D", 21);
		put("N1C3W4_E", 23);
		put("N1C3W4_F", 21);
		put("N1C3W4_G", 23);
		put("N1C3W4_H", 23);
		put("N1C3W4_I", 23);
		put("N1C3W4_J", 22);
		put("N1C3W4_K", 24);
		put("N1C3W4_L", 20);
		put("N1C3W4_M", 21);
		put("N1C3W4_N", 21);
		put("N1C3W4_O", 22);
		put("N1C3W4_P", 25);
		put("N1C3W4_Q", 25);
		put("N1C3W4_R", 22);
		put("N1C3W4_S", 22);
		put("N1C3W4_T", 24);
		put("N2C1W1_A", 48);
		put("N2C1W1_B", 49);
		put("N2C1W1_C", 46);
		put("N2C1W1_D", 50);
		put("N2C1W1_E", 58);
		put("N2C1W1_F", 50);
		put("N2C1W1_G", 60);
		put("N2C1W1_H", 52);
		put("N2C1W1_I", 62);
		put("N2C1W1_J", 59);
		put("N2C1W1_K", 55);
		put("N2C1W1_L", 55);
		put("N2C1W1_M", 46);
		put("N2C1W1_N", 48);
		put("N2C1W1_O", 48);
		put("N2C1W1_P", 54);
		put("N2C1W1_Q", 46);
		put("N2C1W1_R", 56);
		put("N2C1W1_S", 45);
		put("N2C1W1_T", 52);
		put("N2C1W2_A", 64);
		put("N2C1W2_B", 61);
		put("N2C1W2_C", 68);
		put("N2C1W2_D", 74);
		put("N2C1W2_E", 65);
		put("N2C1W2_F", 65);
		put("N2C1W2_G", 73);
		put("N2C1W2_H", 70);
		put("N2C1W2_I", 67);
		put("N2C1W2_J", 67);
		put("N2C1W2_K", 72);
		put("N2C1W2_L", 62);
		put("N2C1W2_M", 65);
		put("N2C1W2_N", 64);
		put("N2C1W2_O", 64);
		put("N2C1W2_P", 68);
		put("N2C1W2_Q", 65);
		put("N2C1W2_R", 67);
		put("N2C1W2_S", 66);
		put("N2C1W2_T", 66);
		put("N2C1W4_A", 73);
		put("N2C1W4_B", 71);
		put("N2C1W4_C", 77);
		put("N2C1W4_D", 82);
		put("N2C1W4_E", 73);
		put("N2C1W4_F", 77);
		put("N2C1W4_G", 71);
		put("N2C1W4_H", 75);
		put("N2C1W4_I", 73);
		put("N2C1W4_J", 74);
		put("N2C1W4_K", 70);
		put("N2C1W4_L", 75);
		put("N2C1W4_M", 72);
		put("N2C1W4_N", 71);
		put("N2C1W4_O", 80);
		put("N2C1W4_P", 67);
		put("N2C1W4_Q", 75);
		put("N2C1W4_R", 70);
		put("N2C1W4_S", 80);
		put("N2C1W4_T", 70);
		put("N2C2W1_A", 42);
		put("N2C2W1_B", 50);
		put("N2C2W1_C", 40);
		put("N2C2W1_D", 42);
		put("N2C2W1_E", 40);
		put("N2C2W1_F", 49);
		put("N2C2W1_G", 45);
		put("N2C2W1_H", 46);
		put("N2C2W1_I", 45);
		put("N2C2W1_J", 42);
		put("N2C2W1_K", 41);
		put("N2C2W1_L", 49);
		put("N2C2W1_M", 44);
		put("N2C2W1_N", 43);
		put("N2C2W1_O", 50);
		put("N2C2W1_P", 46);
		put("N2C2W1_Q", 49);
		put("N2C2W1_R", 41);
		put("N2C2W1_S", 43);
		put("N2C2W1_T", 39);
		put("N2C2W2_A", 52);
		put("N2C2W2_B", 56);
		put("N2C2W2_C", 53);
		put("N2C2W2_D", 51);
		put("N2C2W2_E", 54);
		put("N2C2W2_F", 48);
		put("N2C2W2_G", 53);
		put("N2C2W2_H", 53);
		put("N2C2W2_I", 49);
		put("N2C2W2_J", 56);
		put("N2C2W2_K", 50);
		put("N2C2W2_L", 52);
		put("N2C2W2_M", 54);
		put("N2C2W2_N", 51);
		put("N2C2W2_O", 50);
		put("N2C2W2_P", 50);
		put("N2C2W2_Q", 54);
		put("N2C2W2_R", 51);
		put("N2C2W2_S", 58);
		put("N2C2W2_T", 56);
		put("N2C2W4_A", 57);
		put("N2C2W4_B", 60);
		put("N2C2W4_C", 65);
		put("N2C2W4_D", 61);
		put("N2C2W4_E", 60);
		put("N2C2W4_F", 57);
		put("N2C2W4_G", 61);
		put("N2C2W4_H", 61);
		put("N2C2W4_I", 58);
		put("N2C2W4_J", 60);
		put("N2C2W4_K", 59);
		put("N2C2W4_L", 57);
		put("N2C2W4_M", 60);
		put("N2C2W4_N", 63);
		put("N2C2W4_O", 62);
		put("N2C2W4_P", 60);
		put("N2C2W4_Q", 62);
		put("N2C2W4_R", 56);
		put("N2C2W4_S", 55);
		put("N2C2W4_T", 57);
		put("N2C3W1_A", 35);
		put("N2C3W1_B", 35);
		put("N2C3W1_C", 35);
		put("N2C3W1_D", 37);
		put("N2C3W1_E", 34);
		put("N2C3W1_F", 35);
		put("N2C3W1_G", 33);
		put("N2C3W1_H", 35);
		put("N2C3W1_I", 34);
		put("N2C3W1_J", 33);
		put("N2C3W1_K", 36);
		put("N2C3W1_L", 35);
		put("N2C3W1_M", 31);
		put("N2C3W1_N", 32);
		put("N2C3W1_O", 35);
		put("N2C3W1_P", 35);
		put("N2C3W1_Q", 34);
		put("N2C3W1_R", 33);
		put("N2C3W1_S", 36);
		put("N2C3W1_T", 35);
		put("N2C3W2_A", 41);
		put("N2C3W2_B", 43);
		put("N2C3W2_C", 41);
		put("N2C3W2_D", 41);
		put("N2C3W2_E", 39);
		put("N2C3W2_F", 39);
		put("N2C3W2_G", 41);
		put("N2C3W2_H", 38);
		put("N2C3W2_I", 44);
		put("N2C3W2_J", 43);
		put("N2C3W2_K", 42);
		put("N2C3W2_L", 41);
		put("N2C3W2_M", 43);
		put("N2C3W2_N", 41);
		put("N2C3W2_O", 45);
		put("N2C3W2_P", 41);
		put("N2C3W2_Q", 41);
		put("N2C3W2_R", 40);
		put("N2C3W2_S", 43);
		put("N2C3W2_T", 43);
		put("N2C3W4_A", 43);
		put("N2C3W4_B", 45);
		put("N2C3W4_C", 42);
		put("N2C3W4_D", 44);
		put("N2C3W4_E", 47);
		put("N2C3W4_F", 45);
		put("N2C3W4_G", 44);
		put("N2C3W4_H", 44);
		put("N2C3W4_I", 44);
		put("N2C3W4_J", 43);
		put("N2C3W4_K", 47);
		put("N2C3W4_L", 45);
		put("N2C3W4_M", 44);
		put("N2C3W4_N", 45);
		put("N2C3W4_O", 45);
		put("N2C3W4_P", 45);
		put("N2C3W4_Q", 46);
		put("N2C3W4_R", 42);
		put("N2C3W4_S", 42);
		put("N2C3W4_T", 46);
		put("N3C1W1_A", 105);
		put("N3C1W1_B", 114);
		put("N3C1W1_C", 99);
		put("N3C1W1_D", 108);
		put("N3C1W1_E", 98);
		put("N3C1W1_F", 113);
		put("N3C1W1_G", 111);
		put("N3C1W1_H", 104);
		put("N3C1W1_I", 100);
		put("N3C1W1_J", 108);
		put("N3C1W1_K", 102);
		put("N3C1W1_L", 97);
		put("N3C1W1_M", 106);
		put("N3C1W1_N", 93);
		put("N3C1W1_O", 98);
		put("N3C1W1_P", 108);
		put("N3C1W1_Q", 98);
		put("N3C1W1_R", 99);
		put("N3C1W1_S", 100);
		put("N3C1W1_T", 102);
		put("N3C1W2_A", 125);
		put("N3C1W2_B", 126);
		put("N3C1W2_C", 125);
		put("N3C1W2_D", 139);
		put("N3C1W2_E", 132);
		put("N3C1W2_F", 123);
		put("N3C1W2_G", 132);
		put("N3C1W2_H", 129);
		put("N3C1W2_I", 126);
		put("N3C1W2_J", 126);
		put("N3C1W2_K", 120);
		put("N3C1W2_L", 136);
		put("N3C1W2_M", 136);
		put("N3C1W2_N", 136);
		put("N3C1W2_O", 127);
		put("N3C1W2_P", 126);
		put("N3C1W2_Q", 135);
		put("N3C1W2_R", 123);
		put("N3C1W2_S", 130);
		put("N3C1W2_T", 136);
		put("N3C1W4_A", 149);
		put("N3C1W4_B", 149);
		put("N3C1W4_C", 146);
		put("N3C1W4_D", 148);
		put("N3C1W4_E", 142);
		put("N3C1W4_F", 140);
		put("N3C1W4_G", 148);
		put("N3C1W4_H", 141);
		put("N3C1W4_I", 140);
		put("N3C1W4_J", 142);
		put("N3C1W4_K", 147);
		put("N3C1W4_L", 148);
		put("N3C1W4_M", 149);
		put("N3C1W4_N", 148);
		put("N3C1W4_O", 143);
		put("N3C1W4_P", 143);
		put("N3C1W4_Q", 146);
		put("N3C1W4_R", 145);
		put("N3C1W4_S", 145);
		put("N3C1W4_T", 146);
		put("N3C2W1_A", 91);
		put("N3C2W1_B", 82);
		put("N3C2W1_C", 84);
		put("N3C2W1_D", 85);
		put("N3C2W1_E", 87);
		put("N3C2W1_F", 88);
		put("N3C2W1_G", 87);
		put("N3C2W1_H", 87);
		put("N3C2W1_I", 87);
		put("N3C2W1_J", 87);
		put("N3C2W1_K", 77);
		put("N3C2W1_L", 91);
		put("N3C2W1_M", 85);
		put("N3C2W1_N", 91);
		put("N3C2W1_O", 82);
		put("N3C2W1_P", 88);
		put("N3C2W1_Q", 82);
		put("N3C2W1_R", 82);
		put("N3C2W1_S", 89);
		put("N3C2W1_T", 83);
		put("N3C2W2_A", 107);
		put("N3C2W2_B", 105);
		put("N3C2W2_C", 104);
		put("N3C2W2_D", 107);
		put("N3C2W2_E", 116);
		put("N3C2W2_F", 106);
		put("N3C2W2_G", 102);
		put("N3C2W2_H", 117);
		put("N3C2W2_I", 102);
		put("N3C2W2_J", 107);
		put("N3C2W2_K", 110);
		put("N3C2W2_L", 105);
		put("N3C2W2_M", 108);
		put("N3C2W2_N", 105);
		put("N3C2W2_O", 107);
		put("N3C2W2_P", 107);
		put("N3C2W2_Q", 105);
		put("N3C2W2_R", 110);
		put("N3C2W2_S", 107);
		put("N3C2W2_T", 107);
		put("N3C2W4_A", 113);
		put("N3C2W4_B", 112);
		put("N3C2W4_C", 132);
		put("N3C2W4_D", 114);
		put("N3C2W4_E", 110);
		put("N3C2W4_F", 115);
		put("N3C2W4_G", 122);
		put("N3C2W4_H", 113);
		put("N3C2W4_I", 115);
		put("N3C2W4_J", 120);
		put("N3C2W4_K", 117);
		put("N3C2W4_L", 116);
		put("N3C2W4_M", 120);
		put("N3C2W4_N", 117);
		put("N3C2W4_O", 113);
		put("N3C2W4_P", 122);
		put("N3C2W4_Q", 118);
		put("N3C2W4_R", 123);
		put("N3C2W4_S", 118);
		put("N3C2W4_T", 119);
		put("N3C3W1_A", 66);
		put("N3C3W1_B", 71);
		put("N3C3W1_C", 69);
		put("N3C3W1_D", 63);
		put("N3C3W1_E", 68);
		put("N3C3W1_F", 69);
		put("N3C3W1_G", 65);
		put("N3C3W1_H", 69);
		put("N3C3W1_I", 68);
		put("N3C3W1_J", 65);
		put("N3C3W1_K", 63);
		put("N3C3W1_L", 68);
		put("N3C3W1_M", 71);
		put("N3C3W1_N", 69);
		put("N3C3W1_O", 66);
		put("N3C3W1_P", 72);
		put("N3C3W1_Q", 73);
		put("N3C3W1_R", 66);
		put("N3C3W1_S", 68);
		put("N3C3W1_T", 70);
		put("N3C3W2_A", 84);
		put("N3C3W2_B", 81);
		put("N3C3W2_C", 82);
		put("N3C3W2_D", 79);
		put("N3C3W2_E", 79);
		put("N3C3W2_F", 81);
		put("N3C3W2_G", 81);
		put("N3C3W2_H", 82);
		put("N3C3W2_I", 79);
		put("N3C3W2_J", 83);
		put("N3C3W2_K", 83);
		put("N3C3W2_L", 82);
		put("N3C3W2_M", 83);
		put("N3C3W2_N", 77);
		put("N3C3W2_O", 82);
		put("N3C3W2_P", 80);
		put("N3C3W2_Q", 76);
		put("N3C3W2_R", 79);
		put("N3C3W2_S", 80);
		put("N3C3W2_T", 79);
		put("N3C3W4_A", 89);
		put("N3C3W4_B", 88);
		put("N3C3W4_C", 88);
		put("N3C3W4_D", 87);
		put("N3C3W4_E", 85);
		put("N3C3W4_F", 84);
		put("N3C3W4_G", 94);
		put("N3C3W4_H", 84);
		put("N3C3W4_I", 92);
		put("N3C3W4_J", 88);
		put("N3C3W4_K", 89);
		put("N3C3W4_L", 90);
		put("N3C3W4_M", 88);
		put("N3C3W4_N", 87);
		put("N3C3W4_O", 87);
		put("N3C3W4_P", 86);
		put("N3C3W4_Q", 87);
		put("N3C3W4_R", 87);
		put("N3C3W4_S", 84);
		put("N3C3W4_T", 85);
		put("N4C1W1_A", 240);
		put("N4C1W1_B", 262);
		put("N4C1W1_C", 241);
		put("N4C1W1_D", 246);
		put("N4C1W1_E", 272);
		put("N4C1W1_F", 265);
		put("N4C1W1_G", 259);
		put("N4C1W1_H", 251);
		put("N4C1W1_I", 262);
		put("N4C1W1_J", 288);
		put("N4C1W1_K", 253);
		put("N4C1W1_L", 258);
		put("N4C1W1_M", 246);
		put("N4C1W1_N", 256);
		put("N4C1W1_O", 258);
		put("N4C1W1_P", 271);
		put("N4C1W1_Q", 277);
		put("N4C1W1_R", 254);
		put("N4C1W1_S", 261);
		put("N4C1W1_T", 256);
		put("N4C1W2_A", 317);
		put("N4C1W2_B", 328);
		put("N4C1W2_C", 319);
		put("N4C1W2_D", 327);
		put("N4C1W2_E", 310);
		put("N4C1W2_F", 321);
		put("N4C1W2_G", 307);
		put("N4C1W2_H", 315);
		put("N4C1W2_I", 304);
		put("N4C1W2_J", 311);
		put("N4C1W2_K", 311);
		put("N4C1W2_L", 316);
		put("N4C1W2_M", 330);
		put("N4C1W2_N", 311);
		put("N4C1W2_O", 319);
		put("N4C1W2_P", 317);
		put("N4C1W2_Q", 319);
		put("N4C1W2_R", 319);
		put("N4C1W2_S", 312);
		put("N4C1W2_T", 323);
		put("N4C1W4_A", 368);
		put("N4C1W4_B", 349);
		put("N4C1W4_C", 365);
		put("N4C1W4_D", 359);
		put("N4C1W4_E", 373);
		put("N4C1W4_F", 369);
		put("N4C1W4_G", 362);
		put("N4C1W4_H", 359);
		put("N4C1W4_I", 359);
		put("N4C1W4_J", 368);
		put("N4C1W4_K", 371);
		put("N4C1W4_L", 355);
		put("N4C1W4_M", 360);
		put("N4C1W4_N", 363);
		put("N4C1W4_O", 351);
		put("N4C1W4_P", 363);
		put("N4C1W4_Q", 359);
		put("N4C1W4_R", 370);
		put("N4C1W4_S", 359);
		put("N4C1W4_T", 355);
		put("N4C2W1_A", 210);
		put("N4C2W1_B", 213);
		put("N4C2W1_C", 213);
		put("N4C2W1_D", 200);
		put("N4C2W1_E", 215);
		put("N4C2W1_F", 203);
		put("N4C2W1_G", 211);
		put("N4C2W1_H", 215);
		put("N4C2W1_I", 209);
		put("N4C2W1_J", 202);
		put("N4C2W1_K", 210);
		put("N4C2W1_L", 209);
		put("N4C2W1_M", 217);
		put("N4C2W1_N", 210);
		put("N4C2W1_O", 212);
		put("N4C2W1_P", 212);
		put("N4C2W1_Q", 210);
		put("N4C2W1_R", 212);
		put("N4C2W1_S", 210);
		put("N4C2W1_T", 212);
		put("N4C2W2_A", 253);
		put("N4C2W2_B", 254);
		put("N4C2W2_C", 249);
		put("N4C2W2_D", 258);
		put("N4C2W2_E", 257);
		put("N4C2W2_F", 272);
		put("N4C2W2_G", 252);
		put("N4C2W2_H", 255);
		put("N4C2W2_I", 262);
		put("N4C2W2_J", 256);
		put("N4C2W2_K", 259);
		put("N4C2W2_L", 263);
		put("N4C2W2_M", 261);
		put("N4C2W2_N", 264);
		put("N4C2W2_O", 253);
		put("N4C2W2_P", 266);
		put("N4C2W2_Q", 257);
		put("N4C2W2_R", 256);
		put("N4C2W2_S", 273);
		put("N4C2W2_T", 256);
		put("N4C2W4_A", 293);
		put("N4C2W4_B", 281);
		put("N4C2W4_C", 295);
		put("N4C2W4_D", 295);
		put("N4C2W4_E", 287);
		put("N4C2W4_F", 304);
		put("N4C2W4_G", 291);
		put("N4C2W4_H", 296);
		put("N4C2W4_I", 287);
		put("N4C2W4_J", 300);
		put("N4C2W4_K", 289);
		put("N4C2W4_L", 297);
		put("N4C2W4_M", 287);
		put("N4C2W4_N", 299);
		put("N4C2W4_O", 293);
		put("N4C2W4_P", 301);
		put("N4C2W4_Q", 293);
		put("N4C2W4_R", 300);
		put("N4C2W4_S", 293);
		put("N4C2W4_T", 287);
		put("N4C3W1_A", 164);
		put("N4C3W1_B", 165);
		put("N4C3W1_C", 166);
		put("N4C3W1_D", 158);
		put("N4C3W1_E", 165);
		put("N4C3W1_F", 164);
		put("N4C3W1_G", 169);
		put("N4C3W1_H", 170);
		put("N4C3W1_I", 167);
		put("N4C3W1_J", 169);
		put("N4C3W1_K", 164);
		put("N4C3W1_L", 163);
		put("N4C3W1_M", 167);
		put("N4C3W1_N", 176);
		put("N4C3W1_O", 169);
		put("N4C3W1_P", 167);
		put("N4C3W1_Q", 176);
		put("N4C3W1_R", 167);
		put("N4C3W1_S", 168);
		put("N4C3W1_T", 173);
		put("N4C3W2_A", 203);
		put("N4C3W2_B", 203);
		put("N4C3W2_C", 201);
		put("N4C3W2_D", 201);
		put("N4C3W2_E", 200);
		put("N4C3W2_F", 209);
		put("N4C3W2_G", 201);
		put("N4C3W2_H", 201);
		put("N4C3W2_I", 198);
		put("N4C3W2_J", 204);
		put("N4C3W2_K", 204);
		put("N4C3W2_L", 201);
		put("N4C3W2_M", 198);
		put("N4C3W2_N", 198);
		put("N4C3W2_O", 209);
		put("N4C3W2_P", 202);
		put("N4C3W2_Q", 199);
		put("N4C3W2_R", 194);
		put("N4C3W2_S", 196);
		put("N4C3W2_T", 195);
		put("N4C3W4_A", 216);
		put("N4C3W4_B", 215);
		put("N4C3W4_C", 218);
		put("N4C3W4_D", 215);
		put("N4C3W4_E", 219);
		put("N4C3W4_F", 222);
		put("N4C3W4_G", 222);
		put("N4C3W4_H", 219);
		put("N4C3W4_I", 224);
		put("N4C3W4_J", 213);
		put("N4C3W4_K", 215);
		put("N4C3W4_L", 219);
		put("N4C3W4_M", 216);
		put("N4C3W4_N", 225);
		put("N4C3W4_O", 226);
		put("N4C3W4_P", 219);
		put("N4C3W4_Q", 222);
		put("N4C3W4_R", 214);
		put("N4C3W4_S", 216);
		put("N4C3W4_T", 216);
	}};


}
