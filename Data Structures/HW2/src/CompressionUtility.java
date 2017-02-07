import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.util.jar.*;
import java.util.zip.CRC32;
/**
 * 
 */

/**
 * @author noahfrankel
 *
 */
public class CompressionUtility {

		private static void fileCompressor(String zipFileOrJarFile, String dir) throws Exception {
			File file = new File(dir);
			FileOutputStream fos = new FileOutputStream(zipFileOrJarFile);
			if (zipFileOrJarFile.endsWith(".zip")) {
				ZipOutputStream zos = new ZipOutputStream(fos);
				System.out.println("Creating : " + zipFileOrJarFile);
				compressToZip(file, zos);
				zos.close();
			}
			else if (zipFileOrJarFile.endsWith(".jar")) {
				JarOutputStream jos = new JarOutputStream(fos);
				System.out.println("creating :" + zipFileOrJarFile);
				compressToJar(file, jos);
				jos.close();
			}
		}

		//recursive method to write a file to a ZipFile
		public static void compressToZip(File dir, ZipOutputStream zos) throws IOException {
			File[] files = dir.listFiles(); // returns an array of pathnames in the directory
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					compressToZip(files[i], zos);
				}
				FileInputStream fis = new FileInputStream(files[i].getAbsolutePath());
				System.out.println("Compressing " + files[i].getAbsolutePath());
				ZipEntry zipEntry = new ZipEntry(files[i].getAbsolutePath());
				zos.putNextEntry(zipEntry);
				zos.closeEntry();
				fis.close();

			}
			
		}
		//recursive method to write a file to a JarFile
		public static void compressToJar(File dir, JarOutputStream jos) throws IOException {
			File[] files = dir.listFiles(); 
			for (int i=0; i<files.length; i++) {
				if (files[i].isDirectory()) {
					compressToJar(files[i], jos);
				}
				FileInputStream fis = new FileInputStream(files[i].getAbsolutePath());
				System.out.println("Compressing " + files[i].getAbsolutePath());
				JarEntry jarEntry = new JarEntry(files[i].getAbsolutePath());
				jos.putNextEntry(jarEntry);
				jos.close();
				fis.close();
			}
		}
		public static long checksum(File file) throws IOException {
			File[] files = file.listFiles();
			CRC32 crc = new CRC32();
			for (int i = 0; i < files.length; i++) {
				FileInputStream fis = new FileInputStream(files[i]);
				int x;
				while ((x = fis.read()) != -1) {
					crc.update(x);
				}
				fis.close();
			}
			return crc.getValue();
			
		}
	public static void main(String[] args) throws Exception {
		File file = new File("report.txt");
		FileWriter writer = new FileWriter(file);
		BufferedWriter buf = new BufferedWriter(writer);
		File[] files = new File(args[1]).listFiles();
		for (int i = 0; i < files.length; i++) {
			buf.write(files[i] + "." + args[2]);
			buf.newLine();
		}
		buf.close();
		fileCompressor(args[0], args[1]);		
	}
}
