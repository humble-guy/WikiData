

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;

import javax.security.auth.login.FailedLoginException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection.Response;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;



import org.json.*;

public class myProgram {
	
	public static void main(String[] args) throws Exception
	{
		
		
		String password="prisonbreak";
		String username = "pingaldixit";
		
	/*	Wiki wk = new Wiki("wikidata.org");
		wk.login(username, password);*/
		Response res = (Response) Jsoup.connect("https://www.wikidata.org/w/index.php?title=Special:UserLogin&returnto=Wikidata%3AMain+Page")
	            .method(Method.POST)
	            .timeout(10000)
	            .execute();

		//System.out.println(res.cookies());
	   String sessionID = res.cookie("wikidatawikiSession");
	   System.out.println("Session id:"+sessionID);
		
		
		
		
		String searchData;
		String url = "https://www.wikidata.org/w/api.php?action=query&format=xml&meta=tokens&type=login";
		
		
		//org.jsoup.nodes.Document loginData =  Jsoup.connect(url).data("action","query").data("format","xml","meta","tokens","type","login").cookie("wikidatawikiSession",sessionID).get();
		org.jsoup.nodes.Document loginData =  Jsoup.connect(url).data("action","query").data("format","xml","meta","tokens","type","login").cookies(res.cookies()).get();

		Elements e = loginData.select("tokens");
		String loginToken = e.attr("logintoken");
		
		
		Connection.Response loginForm = Jsoup.connect(url)
				.data("action","query")
				.data("format","xml","meta","tokens","type","login")
		        //.cookies(loginCookies)
		        .method(Connection.Method.POST)
		        .execute();
		

		Map<String, String> loginCookies  = loginForm.cookies();
		System.out.println(loginCookies);
		
		
		/*res = (Response) Jsoup.connect("https://www.wikidata.org/w/api.php?")
				.data("action","login").data("format","xml","lgname",username,"lgpassword",password,"lgtoken",loginToken).cookie("wikidatawikiSession",sessionID)
	            .method(Method.POST)
	            .timeout(10000)
	            .execute();
       
        System.out.println(res.cookies());*/
      //  sessionID = res.cookie("centralauth_Session");
 	   //System.out.println("Session id:"+sessionID);
 	   
 	   
        
       /* url = "https://www.wikidata.org/w/api.php?";
        org.jsoup.nodes.Document loginData1 =  Jsoup.connect(url).data("action","login").data("format","xml","lgname",username,"lgpassword",password,"lgtoken",loginToken).cookies(res.cookies()).post();
        System.out.println(loginData1);
        
        //e = loginData1.select("login");
        sessionID = e.attr("sessionid");
       // searchData = getInfoFromUrl(url);
       // System.out.println(searchData);
		*/
		String searchTerm;
		Scanner input = new Scanner(System.in);
		System.out.println("Enter item to search:");
		searchTerm  = input.nextLine();
		
		url = "https://www.wikidata.org/w/api.php?";
		 loginData =  Jsoup.connect(url).data("action","query").data("format","xml","meta","tokens","type","csrf").cookies(loginCookies).get();
		 e = loginData.select("tokens");
		 System.out.println(loginData);
		 String csrfToken = e.attr("csrftoken");  
		 System.out.println("CSRF Token:"+ csrfToken);
		
		
		
		/*url = "https://www.wikidata.org/w/api.php?action=wbsearchentities&search="+searchTerm+"&language=en&format=xml&callback=?";
		loginData1 =  Jsoup.connect(url).data("action","wbsearchentities").data("format","xml","search",searchTerm,"language","en").cookies(res.cookies()).get();
		e = loginData1.select("entity");
		String s = e.attr("id");
		System.out.println(s);*/
		
		//searchData = getInfoFromUrl(url);
		
		
		/*Document xmlDoc = loadXMLFromString(searchData);
		Element root = xmlDoc.getDocumentElement();
		NodeList nl = parseXml(root,"entity");
		String[] id = new String[nl.getLength()];
		for(int i=0;i<nl.getLength();i++)
		{
			Element temp = (Element) nl.item(i);
			String qid = temp.getAttribute("id");
			boolean result = checkIfLabelIsPresent(qid,"mr");
			if(!result)
			{
				//means label is not present
				System.out.println("Entity id:"+qid);
				s = GetLabelInOtherLanguages(qid);
				System.out.println(s);
				//String token = generateToken();
				String token;
				
				 url = "https://www.wikidata.org/w/api.php?";
				 loginData =  Jsoup.connect(url).data("action","query").data("format","xml","meta","tokens","type","csrf").cookies(res.cookies()).get();
				 e = loginData.select("tokens");
				 System.out.println(loginData);
				 String csrfToken = e.attr("csrftoken");  
				 
				 
				 System.out.println("CSRF Token:"+csrfToken);
				 
				 
				 
				 //Wiki wk = new Wiki("wikidata.org");
				// Wiki wk = new Wiki();
				   //wk.login(username, password);
				 //csrfToken = wk.getToken("csrf");
				 System.out.println("CSRF Token:"+csrfToken);
				
				
				System.out.println("The token is missing from this.Please write it:");
				String inputLabel = input.nextLine();
				//System.out.println("Token:"+token);
				//setLabel(qid,token,inputLabel,"mr");
				
				
				
				
				url = "https://www.wikidata.org/w/api.php?";
				org.jsoup.nodes.Document loginData2 = Jsoup.connect(url).data("action","wbsetlabel").data("format","xml","id",qid,"token",csrfToken,"language","mr","value",inputLabel).cookie("wikidatawikiSession",sessionID).get();  
				e = loginData2.select("api");
				 String result1 = e.attr("success");  
				 System.out.println("Editing Result:"+result1);
				
				
				
				
			}
			
		}*/

		
		String qid = null;
		
		
			url = "https://www.wikidata.org/w/api.php?action=wbgetentities&format=xml&ids=" + qid;
			searchData = getInfoFromUrl(url);
		
		
		
	
	}
	
	
	public static String getInfoFromUrl(String wikiUrl)
	{
		String searchData = null;
		try {
			searchData = Jsoup.connect(wikiUrl).ignoreContentType(true).execute().body();
			System.out.println(Jsoup.connect(wikiUrl).ignoreContentType(true).execute().body());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return searchData;
	}
	
	
	
	public static boolean checkIfLabelIsPresent(String id,String lang) throws Exception
	{
		String url = "https://www.wikidata.org/w/api.php?action=wbgetentities&format=xml&ids=" + id;
		String data = getInfoFromUrl(url);
		Document xmlDoc = loadXMLFromString(data);
		Element root =  xmlDoc.getDocumentElement();
		NodeList nl = parseXml(root,"label");
		boolean flag = false;
		for(int i=0;i<nl.getLength();i++)
		{
			Element temp = (Element) nl.item(i);
			 if(temp.getAttribute("language").equals(lang))
			 {
				 flag = true;
			 }
		}
		return flag;
		
	}
	
	public static String GetLabelInOtherLanguages(String id) throws Exception
	{
		String url = "https://www.wikidata.org/w/api.php?action=wbgetentities&format=xml&ids=" + id;
		String data = getInfoFromUrl(url);
		Document xmlDoc = loadXMLFromString(data);
		Element root =  xmlDoc.getDocumentElement();
		NodeList nl = parseXml(root,"label");
		String s = null;

		for(int i=0;i<nl.getLength();i++)
		{
			
			Element temp = (Element) nl.item(i);
			 
			 
				 s+=temp.getAttribute("language") +"   "+ temp.getAttribute("value")+"\n";
			 
		}
		return s;
	}
	
	 public static boolean setLabel(String id,String token,String value,String lang) throws Exception
     {
             String url = "https://www.wikidata.org/w/api.php?action=wbsetlabel&format=xml&id="+id+"&token="+token+"&language="+lang+"&value="+value;
              String searchData = getInfoFromUrl(url);
             Document xmlDoc = loadXMLFromString(searchData);
             Element root =  xmlDoc.getDocumentElement();
             if(root.getAttribute("success").equals("1"))
                     return true;
             else
                     return false;
     }
	
	
	public static String generateToken() throws Exception
    {
            String url = "https://www.wikidata.org/w/api.php?action=query&format=xml&meta=tokens";
            String data = getInfoFromUrl(url);
            Document xmlDoc = loadXMLFromString(data);
            Element root =  xmlDoc.getDocumentElement();
            Element el = (Element)parseXml(root,"tokens").item(0);
          return  el.getAttribute("csrftoken");
            
    }
	
	
	

public static NodeList parseXml( Element ele,String str){
        NodeList nodelist=ele.getElementsByTagName(str);
         return nodelist;
}
	
	public static Document loadXMLFromString(String xml) throws Exception
	{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    InputSource is = new InputSource(new StringReader(xml));
	    return (Document) builder.parse(is);
	}

}
