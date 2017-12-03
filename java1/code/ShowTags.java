import java.io.*;
import java.util.*;

class ShowTags{
  public static void main(String[] args)throws IOException{
     String content = readFile("grassland.htm");
		HashMap <String, Integer> map = new HashMap<String, Integer>();
		String checkTag = "";
		for(int i = 0; i < content.length(); i++)
		{
			boolean flag = true;
			checkTag = "";
			if(content.charAt(i) == '<' && content.charAt(i+1) != ' ')
			{
				while(content.charAt(i)!= '>' && content.charAt(i)!= ' ')
				{
					checkTag += content.charAt(i++);
					if(content.charAt(i) == '<')
					{
						flag = false;
						break;
					}
				}
				if(!flag)
					break;
				checkTag += '>';
				Iterator it = map.keySet().iterator();
				while(it.hasNext())
				{
					String key = (String) it.next();
					Integer value = map.get(key);
					if(key.equalsIgnoreCase(checkTag))
					{
						flag = false;
						value += 1;
						map.put(key, value);
						break;
					}
				}
				if(flag) map.put(checkTag.toUpperCase(), 1);
			}

		}
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key1 = (String) it.next();
			System.out.print(key1+":"+map.get(key1)+"\t");
		}
		System.out.println();
  }

  static String readFile(String fileName) throws IOException{
    	StringBuilder sb = new StringBuilder("");
	int c1;
	FileInputStream f1= new FileInputStream(fileName);		
	InputStreamReader in = new InputStreamReader(f1, "UTF-8");

	while ((c1 = in.read()) != -1) {
	  sb.append((char) c1);
	}        
        return sb.toString();
  }
}
