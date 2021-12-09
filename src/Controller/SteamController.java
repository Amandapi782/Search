
package Controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SteamController {
	
	private static final String ARQ_STEAM = "C:\\TEMP\\btc.json";
	private static final String ARQ_NEW = "C:\\TEMP\\dados.csv";
	
	
	public void geraArquivo(String symbol, String arquivo, String diretorio) throws IOException{	

		File arq = new File(ARQ_STEAM);
		
			if(arq.exists() && arq.isFile()) {
				StringBuffer sb = arquivoParaBuffer(symbol, arq);
				
				File dir = new File(diretorio);
				if(dir.exists() && dir.isDirectory()) {
					File newArq = new File(dir, arquivo);
					if(!newArq.exists()) {
						bufferParaArquivo(sb, newArq);
					}else {
						busca(symbol);
					
					}
				} else {
					throw new IOException("Diretório de destino não encontrado");
				}
			}  else {
				throw new IOException("Arquivo não encontrado");
			}
	  
	}
	
	private void bufferParaArquivo(StringBuffer sb, File newArq) throws IOException{
		FileWriter fw = new FileWriter(newArq, false);
		PrintWriter pw = new PrintWriter(fw);
		pw.write(sb.toString());
		pw.flush();
		pw.close();
		pw.close();
	}
	
	private StringBuffer arquivoParaBuffer(String symbol, File arq) throws FileNotFoundException, IOException{
		StringBuffer sb = new StringBuffer();
		FileInputStream fis = new FileInputStream(arq);
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		String linha = br.readLine();
		linha = br.readLine();
		while(linha != null) {
			String [] vetLinha = linha.split(",");
			if(linha.contains("symbol")) {
				sb.append(vetLinha[0]+";");
			}else if (linha.contains("lastPrice")) {
				sb.append(vetLinha[0] + ";\n");
			}
			linha = br.readLine();
		}
		
		br.close();
		isr.close();
		fis.close();
		return sb;
	}
	
	public void busca(String symbol) throws IOException{
		File arq = new File(ARQ_NEW);
		
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis = new FileInputStream(arq);
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			String linha = br.readLine();
			while(linha != null) {
				 String[] vetLinha = linha.split(";");
				 if(linha.contains(symbol)) {
					System.out.println(vetLinha[0]+ " |\t" +vetLinha[1]);
				}
				
				 linha = br.readLine();
			}
			
			br.close();
			isr.close();
			fis.close();
	
		}else {
			throw new IOException("Arquivo não encontrado");
		}
	}

	
}