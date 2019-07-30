import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SaxTest {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        File file = new File("test.xml");
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser = factory.newSAXParser();
        EventHandler handler = new EventHandler();
        saxParser.parse(file,handler);
    }

    static class EventHandler extends DefaultHandler{
        boolean isComputabled,math,english;
        int count;
        double mathSum,englishSum,personSum;
        StringBuffer numberContent,otherContent;
        @Override
        public void endDocument() throws SAXException {
            System.out.println("");
            System.out.println("共有"+count+"学生");
            System.out.println("数学平均成绩:"+mathSum/count);
            System.out.println("英语平均成绩:"+englishSum/count);
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            numberContent = new StringBuffer();
            otherContent = new StringBuffer();
            System.out.println("<"+qName+">");
            if (qName.endsWith("成绩"))
                isComputabled = true;
            if (qName.startsWith("数学"))
                math = true;
            if (qName.startsWith("英语"))
                english = true;
            if (qName.equals("学生")){
                personSum = 0;
                count++;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            System.out.println("</"+qName+">");
            if (isComputabled){
                String numberstr = new String(numberContent);
                numberstr = numberstr.trim();
                double d = Double.parseDouble(numberstr);
                personSum = personSum + d;
                if (math)
                    mathSum = mathSum + d;
                if (english)
                    englishSum = englishSum +d;
            }
            isComputabled = false;
            math = false;
            english = false;
            if (qName.equals("学生"))
                System.out.println("学生总成绩："+personSum);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String text = new String(ch,start,length);
            if (isComputabled)
                numberContent.append(text);
            System.out.println(text);
        }
    }
}
