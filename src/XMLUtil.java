import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class XMLUtil {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String file = scanner.nextLine();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setValidating(true);
        try {
            DocumentBuilder builder= documentBuilderFactory.newDocumentBuilder();
            MyHandler handler = new MyHandler();
            builder.setErrorHandler(handler);
            Document document = builder.parse(file);
            if(handler.errorMessage == null){
                System.out.println(file+"有效");
            }else {
                System.out.println(file+"无效");
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class MyHandler extends DefaultHandler{
        String errorMessage = null;
        public void error(SAXParseException e){
            errorMessage = e.getMessage();
            System.out.println("一般错误："+errorMessage);
        }

        public void fatalError(SAXParseException e){
            errorMessage = e.getMessage();
            System.out.println("致命错误："+errorMessage);
        }
    }

}
